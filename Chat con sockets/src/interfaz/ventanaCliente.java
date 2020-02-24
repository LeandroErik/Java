package interfaz;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import hilo.hilo;
import socket.socketClient;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import java.awt.Color;
import java.awt.Font;

public class ventanaCliente extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textIP;
	private JTextField textPuerto;
	private JTextField textMensaje;
	private socketClient cliente = new socketClient();
	private hilo hil;
	private Thread thread;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ventanaCliente frame = new ventanaCliente();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ventanaCliente() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 320);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblIp = new JLabel("IP");
		lblIp.setBounds(10, 11, 46, 14);
		contentPane.add(lblIp);
		
		textIP = new JTextField();
		textIP.setBounds(66, 8, 119, 20);
		contentPane.add(textIP);
		textIP.setColumns(10);
		
		JLabel lblPuerto = new JLabel("Puerto");
		lblPuerto.setBounds(10, 36, 46, 14);
		contentPane.add(lblPuerto);
		
		textPuerto = new JTextField();
		textPuerto.setBounds(66, 33, 119, 20);
		contentPane.add(textPuerto);
		textPuerto.setColumns(10);
		
		textMensaje = new JTextField();
		textMensaje.setEnabled(false);
		textMensaje.setBounds(10, 250, 300, 20);
		contentPane.add(textMensaje);
		textMensaje.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 61, 396, 173);
		contentPane.add(scrollPane);
		
		JTextArea textArea = new JTextArea();
		
	
		
		textArea.setBackground(Color.WHITE);
		textArea.setForeground(Color.BLACK);
		textArea.setEditable(false);
		scrollPane.setViewportView(textArea);
		
		JButton btnCerrar = new JButton("Cerrar");
		
		JButton btnEnviar = new JButton("enviar");
		
		JButton btnConectar = new JButton("Conectar");
		
		btnCerrar.setEnabled(false);
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					cliente.CerrarIO();
					hil.detener();
				
					btnConectar.setEnabled(true);
					textIP.setEnabled(true);
					textPuerto.setEnabled(true);
					textIP.setText("");
					textPuerto.setText("");
					
					btnCerrar.setEnabled(false);
					btnEnviar.setEnabled(false);
					textMensaje.setEnabled(false);
					textArea.setText("");
				}
				catch (SocketException e) {
					textArea.append("CLIENTE DESCONECTO");
					btnConectar.setEnabled(true);
					textIP.setEnabled(true);
					textPuerto.setEnabled(true);
					textIP.setText("");
					textPuerto.setText("");
					
					btnCerrar.setEnabled(false);
					btnEnviar.setEnabled(false);
					textMensaje.setEnabled(false);
					textArea.setText("");
					
				}catch (IOException e) {
					
				}
			}
		});
		btnCerrar.setBounds(221, 32, 89, 23);
		contentPane.add(btnCerrar);
		
	
		btnEnviar.setEnabled(false);
		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
				
					cliente.Enviar(textMensaje.getText());
					if(cliente.seDesconecto()!=true) {
					textArea.append("[YO] : " + textMensaje.getText()+"\n");
					textMensaje.setText("");
				}
				}catch(SocketException e){
					textArea.setText("");
					textArea.append("SERVER CERRADO");
					
					
					btnConectar.setEnabled(true);
					textIP.setEnabled(true);
					
					textPuerto.setEnabled(true);
					textPuerto.setText("");
					
					btnCerrar.setEnabled(false);
					btnEnviar.setEnabled(false);
					textMensaje.setEnabled(false);
				
			}catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		btnEnviar.setBounds(320, 249, 89, 23);
		contentPane.add(btnEnviar);
		
	
		btnConectar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//boolean b=true;
				
					
				try {
					
					cliente.Conectar(textIP.getText(), Integer.parseInt(textPuerto.getText()));		
				
					if(cliente.seConecto()){
						textArea.setText("");
						textArea.setForeground(Color.black);
					textArea.append("Se conecto a "+textIP.getText()+" en puerto "+textPuerto.getText()+"\n");
					
					btnConectar.setEnabled(false);
					textIP.setEnabled(false);
					textPuerto.setEnabled(false);
					
					btnCerrar.setEnabled(true);
					btnEnviar.setEnabled(true);
					textMensaje.setEnabled(true);
					hil = new hilo(cliente, textArea);
					thread = new Thread(hil);
					thread.start();
					
					}
				} catch (NumberFormatException E) {
					textArea.setFont(new Font("Arial", Font.PLAIN, 13));
					textArea.setForeground(Color.red);
					textArea.append("INGRESE UN NUMERO EN EL PUERTO,PORFAVOR\n");
					
				} catch (UnknownHostException E) {
					textArea.setFont(new Font("Arial", Font.PLAIN, 13));
					textArea.setForeground(Color.red);
					textArea.append("REINGRESE LA IP,PORFAVOR\n");
					}	
					
			catch (SocketException E) {
				textArea.setFont(new Font("Arial", Font.PLAIN, 13));
				textArea.setForeground(Color.red);
				textArea.append("SOCKET NO ENCONTRADO\n");
				
			}catch (Exception e) {
					e.printStackTrace();
					
				}		

			}
			
		});
		btnConectar.setBounds(221, 7, 89, 23);
		contentPane.add(btnConectar);
		
		
		
		
	}
}
