package interfaz;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import socket.socketClient;

import java.awt.TextArea;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import clases.mensaje;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class chatCliente extends JPanel {
	private JTextField textMensaje;
	Thread hiloRecibir;
	private mensaje msgEnviar;
	/**
	 * 250x(203+20)
	 * Create the panel.
	 */
	public chatCliente(socketClient cliente,String nomSala,JFrame marco) {
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 37, 230, 125);
		add(scrollPane);
		
		JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		
		textMensaje = new JTextField();
		textMensaje.setBounds(10, 171, 181, 22);
		add(textMensaje);
		textMensaje.setColumns(10);
		
		JButton btnAtras = new JButton("<");
		
		JButton btnEnviar = new JButton(">");
		
		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String mensaje=textMensaje.getText();
					
					msgEnviar=new mensaje(2, nomSala, mensaje);
				
					cliente.EnviarObj(msgEnviar);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		btnEnviar.setBounds(194, 170, 46, 23);
		add(btnEnviar);
		
		JLabel lblBienvenido = new JLabel("Bienvenido ");
		lblBienvenido.setBounds(65, 11, 92, 14);
		add(lblBienvenido);
		//Iniciando el hilo Recibir del cliente
		hiloRecibir=new Thread(new hilo.hiloRecibir(cliente, textArea, btnAtras));
		
		
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnAtras.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnAtras.setForeground(Color.RED);
		btnAtras.setBounds(10, 7, 46, 23);
		add(btnAtras);
		hiloRecibir.start();
		
		

	}
}
