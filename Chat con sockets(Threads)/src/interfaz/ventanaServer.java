package interfaz;

//tp6
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import hilo.hiloServidor;

import socket.socketServer;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
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
	private int puerto;
	private Thread hilo;
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
		setBounds(100, 100, 450, 320);
		contentPane = new JPanel();
		contentPane.setBackground(Color.GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 36, 383, 201);
		contentPane.add(scrollPane);
		
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		scrollPane.setViewportView(textArea);
		
		JLabel lblPuerto = new JLabel("PUERTO");
		lblPuerto.setFont(new Font("Arial", Font.BOLD, 11));
		lblPuerto.setForeground(Color.BLACK);
		lblPuerto.setBounds(10, 11, 46, 14);
		contentPane.add(lblPuerto);
		
		textPuerto = new JTextField();
		textPuerto.setBounds(66, 8, 139, 20);
		contentPane.add(textPuerto);
		textPuerto.setColumns(10);
		
		JButton btnEscuchar = new JButton("Escuchar");
		btnEscuchar.setForeground(Color.BLACK);
		btnEscuchar.setBackground(Color.WHITE);
		
		JButton btnCerrar = new JButton("Cerrar");
		
		btnEscuchar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
			puerto=Integer.parseInt(textPuerto.getText());
			//INICIAR EL HILO ESCUCHA
			hilo=new Thread (new hiloServidor(server,puerto, textArea,btnCerrar,btnEscuchar,textPuerto));
			hilo.start();
			
				}catch(NumberFormatException e3) {
					
					textArea.setText("");
					textArea.append("Formato no valido, reingrese porfavor\n");
				} catch (Exception e2) {
					
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
					btnCerrar.setEnabled(false);
					btnEscuchar.setEnabled(true);
					textPuerto.setText("");
					textPuerto.setEnabled(true);
					
					textArea.setText("");
				}
				catch (IOException e1) {	

				}
			}
		});
		btnCerrar.setBounds(314, 7, 89, 23);
		contentPane.add(btnCerrar);
		
		
		
}
	
}
