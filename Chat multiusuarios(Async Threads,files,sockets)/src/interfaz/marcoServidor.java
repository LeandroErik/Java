package interfaz;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import clases.archivoTextoPlano;
import hilo.hiloServidor;
import socket.socketServer;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Color;

public class marcoServidor extends JFrame {

	private JPanel contentPane;
	private socketServer servidor=new socketServer();
	private archivoTextoPlano archivo;
	private Thread hiloServidor;
	private JTextField textPuerto;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					marcoServidor frame = new marcoServidor();
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
	public marcoServidor() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 233, 131);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblSeervidor = new JLabel("Servidor ");
		lblSeervidor.setBounds(85, -3, 96, 14);
		panel.add(lblSeervidor);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(-23, 11, 254, 2);
		panel.add(separator);
		
		textPuerto = new JTextField();
		textPuerto.setBounds(10, 34, 192, 20);
		panel.add(textPuerto);
		textPuerto.setColumns(10);
		
		JLabel lblPuerto = new JLabel("Puerto");
		lblPuerto.setBounds(10, 21, 46, 14);
		panel.add(lblPuerto);
		
		JLabel lblComprobacion = new JLabel();
		lblComprobacion.setForeground(Color.RED);
		lblComprobacion.setFont(new Font("Arial", Font.ITALIC, 12));
		lblComprobacion.setBounds(10, 52, 30, 14);
		panel.add(lblComprobacion);
		
		JButton btnIniciar = new JButton("Iniciar");
		
		JButton btnCerrar = new JButton("Cerrar");
		
		
		btnIniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					lblComprobacion.setText("");
					btnIniciar.setForeground(Color.black);
					
					int puerto=Integer.parseInt(textPuerto.getText());
					//INICIAR EL HILO ESCUCHA
					hiloServidor=new Thread (new hiloServidor(servidor,puerto,btnCerrar,btnIniciar));
					hiloServidor.start();
				} catch (NumberFormatException e) {
					//lblComprobacion.setText("error");
					btnIniciar.setForeground(Color.RED);
				}
				
			}
		});
		btnIniciar.setBounds(111, 65, 91, 23);
		panel.add(btnIniciar);
		
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				hiloServidor.interrupt();
				btnCerrar.setEnabled(false);
				btnIniciar.setEnabled(true);
				try {
					servidor.CerrarIO();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		btnCerrar.setEnabled(false);
		btnCerrar.setBounds(10, 65, 91, 23);
		panel.add(btnCerrar);
		
		
		
	}

}
