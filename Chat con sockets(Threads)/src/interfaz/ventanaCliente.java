package interfaz;
//tp6
import java.io.*;
import java.awt.EventQueue;
import clases.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import hilo.hiloRecibir;
import socket.socketClient;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.net.SocketException;
import java.net.UnknownHostException;

import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import java.awt.Color;
import java.awt.Font;


public class ventanaCliente extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textIP;
	private JTextField textPuerto;
	private JTextField textMensaje;
	JTextArea textArea;
	private socketClient cliente = new socketClient();
	
	private hiloRecibir hil;
	private Thread thread;
	private String nombre;
	private String mensaje;	
	
	private String ip;
	private int puerto;
	private boolean nomValido=true;
	
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

	public ventanaCliente() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 439, 345);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 101, 396, 173);
		contentPane.add(scrollPane);
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		scrollPane.setViewportView(textArea);
		
		JLabel lblIp = new JLabel("IP");
		lblIp.setBounds(10, 42, 46, 14);
		contentPane.add(lblIp);
		
		textIP = new JTextField();
		textIP.setBounds(66, 39, 119, 20);
		contentPane.add(textIP);
		textIP.setColumns(10);
		
		JLabel lblPuerto = new JLabel("Puerto");
		lblPuerto.setBounds(10, 73, 46, 14);
		contentPane.add(lblPuerto);
		
		textPuerto = new JTextField();
		textPuerto.setBounds(66, 70, 119, 20);
		contentPane.add(textPuerto);
		textPuerto.setColumns(10);
		
		textMensaje = new JTextField();
		textMensaje.setEnabled(false);
		textMensaje.setBounds(10, 285, 300, 20);
		contentPane.add(textMensaje);
		textMensaje.setColumns(10);
		
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(10, 11, 46, 14);
		contentPane.add(lblNombre);
		
		JTextField textNombre;
		textNombre = new JTextField();
		textNombre.setBounds(66, 8, 119, 20);
		contentPane.add(textNombre);
		textNombre.setColumns(10);
		
		JButton btnCerrar = new JButton("Cerrar");
		
		JButton btnEnviar = new JButton("enviar");
		
		JButton btnConectar = new JButton("Conectar");
		
		btnConectar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
				nombre=textNombre.getText();
				try {
					cliente.setPuertoremoto(textPuerto.getText());
					cliente.setDireccionRemota(textIP.getText());
					
					cliente.Conectar(textIP.getText(), Integer.parseInt(textPuerto.getText()));
					
					ip=textIP.getText();
					puerto=Integer.parseInt(textPuerto.getText());
					
					//Enviando al server nombre (Hilo Server)
					String confirmacion="si";
					do{
						nomValido=true;
						nombre=textNombre.getText();
						cliente.Enviar(nombre);
						
						confirmacion=cliente.Recibir();
						
						if(confirmacion.equals("no")) {
							nomValido=false;
							if(nomValido==false) {
								textNombre.setText(JOptionPane.showInputDialog("Ingrese nombre de nuevo"));
							}
							textArea.setText("Nombre ya en uso.");
						}else {
							
							nomValido=true;
						}
						
					}while(nomValido==false);
					
					//Enviando al server(Hilo Conectar)
					cliente.Enviar(nombre);
					//hacer un verificacion de nombres que cuando haya un nombre duplicado envie una confirmacion al usuario
					textArea.setText("");
					textArea.setText("Esta usando la IP "+cliente.getSocket().getLocalAddress()+" y su puerto "+cliente.getSocket().getLocalPort()+". \n");
					textArea.append("Bienvenido :"+nombre+"\n");
						
					textArea.setForeground(Color.black);
					
					textIP.setEnabled(false);
					textPuerto.setEnabled(false);
					textNombre.setEnabled(false);
					
					btnConectar.setEnabled(false);
					btnCerrar.setEnabled(true);
					btnEnviar.setEnabled(true);
					textMensaje.setEnabled(true);
					
					//Iniciando hilo de recepcion de mensajes
					hil = new hiloRecibir(cliente, textArea,btnCerrar);
					thread = new Thread(hil);
					thread.start();
					
				
				} catch (NumberFormatException E) {
					textArea.setText("");
					textArea.setFont(new Font("Arial", Font.PLAIN, 13));
					textArea.setForeground(Color.red);
					textArea.append("Ingrese un numero, porfavor\n");
					
				} catch (UnknownHostException E1) {
					textArea.setText("");
					textArea.setFont(new Font("Arial", Font.PLAIN, 13));
					textArea.setForeground(Color.red);
					textArea.append("Reingrese la IP, porfavor\n");
					}	
					
			catch (SocketException E) {
				textArea.setText("");
				textArea.setFont(new Font("Arial", Font.PLAIN, 13));
				textArea.setForeground(Color.red);
				textArea.append("SOCKET NO ENCONTRADO\n");
				
			}catch (Exception e) {
				}		
			}
		});
		btnConectar.setBounds(195, 38, 89, 23);
		contentPane.add(btnConectar);
		
		btnEnviar.setEnabled(false);
		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					if(cliente.seConecto()) {
					mensaje=textMensaje.getText();
					cliente.Enviar(mensaje);
					
					
					textArea.append("["+nombre+"]:" + textMensaje.getText()+"\n");
					textMensaje.setText("");
				}
				}catch(SocketException e){
					textArea.setText("");
					textArea.append("SERVER CERRADO");
					
					btnConectar.setEnabled(true);
					textIP.setEnabled(true);
					textNombre.setEnabled(true);
					textNombre.setText("");
					
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
		btnEnviar.setBounds(319, 284, 89, 23);
		contentPane.add(btnEnviar);
		
		
		btnCerrar.setEnabled(false);
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					cliente.CerrarIO();
					thread.interrupt();
				
					btnConectar.setEnabled(true);
					textIP.setEnabled(true);
					textPuerto.setEnabled(true);
					textNombre.setEnabled(true);
					textIP.setText("");
					textPuerto.setText("");
					textNombre.setText("");
					
					btnCerrar.setEnabled(false);
					btnEnviar.setEnabled(false);
					textMensaje.setEnabled(false);
					textArea.setText("");
				}
				catch (SocketException e) {					
					textArea.append("SERVER NO ENCONTRADO");
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
		btnCerrar.setBounds(195, 67, 89, 23);
		contentPane.add(btnCerrar);
		
		
		
	}
}
