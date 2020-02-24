package interfaz;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import clases.archivoBinario;
import clases.archivoTextoPlano;

import hilo.hiloServidor;

import socket.socketServer;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.SocketException;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import java.awt.Color;
import java.awt.Font;

public class ventanaServer extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JTextField textPuerto;
	
	private socketServer server = new socketServer();
	private int puerto=0;
	private boolean hayBin=true;
	private Thread hilo;
	
	private archivoTextoPlano archivo=new archivoTextoPlano();
	
	private archivoBinario archivoBin=new archivoBinario();
	

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

	public ventanaServer() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 290);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(32, 178, 170));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 36, 383, 201);
		contentPane.add(scrollPane);
		
		JTextArea textArea = new JTextArea();
		textArea.setBackground(new Color(255, 255, 255));
		textArea.setEditable(false);
		scrollPane.setViewportView(textArea);
		try {
			puerto=archivoBin.recibirServer();		
		} catch (FileNotFoundException e4) {
			archivo.escribirArchivo("/ERROR-Server]: Archivo binary_Server no encontrado.");
			hayBin=false;
		}
		catch (EOFException e5) {
			archivo.escribirArchivo("/ERROR-Server]: Excepcion catcheada "+e5.getMessage());
			
		}catch (IOException e4) {
			archivo.escribirArchivo("/ERROR-Server]: Excepcion catcheada "+e4.getMessage());
			
		}
		
		JLabel lblPuerto = new JLabel("PUERTO");
		lblPuerto.setFont(new Font("Arial", Font.BOLD, 11));
		lblPuerto.setForeground(Color.BLACK);
		lblPuerto.setBounds(10, 11, 46, 14);
		contentPane.add(lblPuerto);
		
		textPuerto = new JTextField();
		if(hayBin==true) {
			if(puerto!=0) {
			textPuerto.setText(""+puerto);
			textArea.setText("Ultimo puerto: "+puerto);
			}
		}else {
			textArea.setText("Archivo de configuracion no encontrado.\n");
			textArea.append("Archivo de configuracion creado.\n");
				archivoBin.crearArchivo("binary_Server");
				archivo.escribirArchivo("/Server] : binary_Server creado.");
				try {
					archivoBin.iniciarArchivo("binary_Server");
					archivoBin.escribirServer(0);
					archivoBin.cerrarArchivo();
				} catch (IOException e1) {
					archivo.escribirArchivo("/ERROR-Server]: Excepcion catcheada "+e1.getMessage());
				}	
		
		}
		textPuerto.setBounds(66, 8, 139, 20);
		contentPane.add(textPuerto);
		textPuerto.setColumns(10);
		
		JButton btnEscuchar = new JButton("Escuchar");
		btnEscuchar.setForeground(Color.BLACK);
		btnEscuchar.setBackground(new Color(128, 128, 128));
		
		JButton btnCerrar = new JButton("Cerrar");
		
		btnEscuchar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
			puerto=Integer.parseInt(textPuerto.getText());
			//INICIAR EL HILO ESCUCHA
			hilo=new Thread (new hiloServidor(server,puerto, textArea,btnCerrar,btnEscuchar,textPuerto));
			hilo.start();
			
				}catch(NumberFormatException e3) {
					archivo.escribirArchivo("/ERROR-Server]: Formato no valido como puerto");
					textArea.setText("");
					textArea.append("Formato no valido, reingrese porfavor\n");
				} catch (Exception e2) {
					archivo.escribirArchivo("/ERROR-Server]: Excepcion catcheada "+e2.getMessage());
				}
			}
			
		});
		btnEscuchar.setBounds(215, 7, 89, 23);
		contentPane.add(btnEscuchar);
		
		btnCerrar.setBackground(Color.WHITE);
		btnCerrar.setEnabled(false);
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					//Escribiendo LOG.txt
					archivo.escribirArchivo("/server]: Server cerrado.");
					
					server.Enviar("SERVER CERRADO");
					
					server.CerrarIO();
					
					hilo.interrupt();
					btnCerrar.setEnabled(false);
					btnEscuchar.setEnabled(true);
					
					textPuerto.setEnabled(true);
					textPuerto.setText("");
					
					textArea.setText("");
					
				} 
				catch (SocketException e2) {	
					archivo.escribirArchivo("/ERROR-Server]: Socket Cerrado.");
					btnCerrar.setEnabled(false);
					btnEscuchar.setEnabled(true);
					textPuerto.setText("");
					textPuerto.setEnabled(true);
					
					textArea.setText("");
				}
				catch (IOException e1) {	
					archivo.escribirArchivo("/ERROR-Server]: Excepcion catcheada : "+e1.getMessage());
				}
			}
		});
		btnCerrar.setBounds(314, 7, 89, 23);
		contentPane.add(btnCerrar);
		
		
		
}
	
}
