package interfaz;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import hilo.hilo;
import socket.socketServer;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.BindException;
import java.net.SocketException;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import java.awt.Color;
import java.awt.Font;

public class ventanaServer extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textPuerto;
	private JTextField textMensaje;
	private socketServer server = new socketServer();
	private hilo hil;
	private Thread thread;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ventanaServer frame = new ventanaServer();
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
	public ventanaServer() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 320);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblPuerto = new JLabel("PUERTO");
		lblPuerto.setFont(new Font("Arial", Font.BOLD, 11));
		lblPuerto.setForeground(Color.BLACK);
		lblPuerto.setBounds(10, 11, 46, 14);
		contentPane.add(lblPuerto);
		
		textPuerto = new JTextField();
		textPuerto.setBounds(66, 8, 139, 20);
		contentPane.add(textPuerto);
		textPuerto.setColumns(10);
		
		textMensaje = new JTextField();
		textMensaje.setEnabled(false);
		textMensaje.setBounds(30, 248, 274, 20);
		contentPane.add(textMensaje);
		textMensaje.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 36, 383, 201);
		contentPane.add(scrollPane);
		
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		scrollPane.setViewportView(textArea);
		
		
		
		JButton btnEnviar = new JButton("enviar");
		
		JButton btnEscuchar = new JButton("Escuchar");
		
		JButton btnCerrar = new JButton("Cerrar");
		
		btnEnviar.setEnabled(false);
		btnEnviar.setBackground(Color.WHITE);
		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					
					server.Enviar(textMensaje.getText());
					if(server.seConecto()==true){
					textArea.append("[YO] : "+ textMensaje.getText()+"\n");
					textMensaje.setText("");
					}
			} catch (SocketException e) {
				textArea.setText("");
				textArea.append("CLIENTE DESCONECTADO\n");
				
				btnEnviar.setEnabled(false);
				btnCerrar.setEnabled(false);
				textMensaje.setEnabled(false);
				textPuerto.setText("");
				textPuerto.setEnabled(true);
				btnEscuchar.setEnabled(true);
				try {
					server.CerrarIO();
				} catch (IOException e1) {
					
				}
			}catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		btnEnviar.setBounds(314, 249, 89, 23);
		contentPane.add(btnEnviar);
		
		
		btnCerrar.setBackground(Color.WHITE);
		btnCerrar.setEnabled(false);
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					server.Enviar("SERVER CERRADO");
					
					server.CerrarIO();
					hil.detener();
					
					btnEnviar.setEnabled(false);
					btnCerrar.setEnabled(false);
					textMensaje.setEnabled(false);
					
					textPuerto.setEnabled(true);
					textPuerto.setText("");
					btnEscuchar.setEnabled(true);
					textArea.setText("");
					
				} 
				catch (IOException e1) {	
				}
			}
		});
		btnCerrar.setBounds(314, 7, 89, 23);
		contentPane.add(btnCerrar);
		
		
		btnEscuchar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				
						
				try {
					textArea.setText("");
					
					if(textPuerto.getText()!=null) {
						
					server.Escuchar(Integer.parseInt(textPuerto.getText()),textArea);
					
					if(server.seConecto()==true) {
						
					textArea.setText("");
					textArea.setFont(new Font("Arial", Font.PLAIN, 13));
					textArea.setForeground(Color.BLACK);
					textArea.append("Cliente conectado\n");
					
					btnEnviar.setEnabled(true);
					btnCerrar.setEnabled(true);
					textMensaje.setEnabled(true);
					
					textPuerto.setEnabled(false);
					btnEscuchar.setEnabled(false);
					
					hil = new hilo(server, textArea);
					thread = new Thread(hil);
					thread.start();
					
				
					
					}
					}
				}catch (NumberFormatException e1) {
					textArea.setFont(new Font("Arial", Font.PLAIN, 13));
					textArea.setForeground(Color.red);
					textArea.append("Reingrese el PUERTO\n");
					
					}
				catch (BindException e2) {
					textArea.setFont(new Font("Arial", Font.PLAIN, 13));
					textArea.setForeground(Color.red);
					textArea.append("el PUERTO esta en uso\n");
				
				}catch(IllegalArgumentException e3) {
					textArea.setFont(new Font("Arial", Font.PLAIN, 13));
					textArea.setForeground(Color.red);
					textArea.append("PUERTO INVALIDO\n");
					
				}catch(IOException e3) {
					e3.printStackTrace();
					
				}
			
			}
		});
		btnEscuchar.setBounds(215, 7, 89, 23);
		contentPane.add(btnEscuchar);
		
}
}
