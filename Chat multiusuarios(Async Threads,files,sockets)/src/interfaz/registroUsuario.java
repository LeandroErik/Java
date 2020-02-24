package interfaz;

import javax.swing.*;

import socket.socketClient;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class registroUsuario extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private socketClient cliente;
	//282x250

	/**
	 * Create the panel.
	 */
	public registroUsuario(JFrame marco,socketClient cliente) {
		setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(10, 61, 218, 20);
		add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(10, 107, 218, 20);
		add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnRegistar = new JButton("Registrar");
		btnRegistar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			 	marco.setSize(250,212);
				marco.setContentPane(new inicioSesion(marco,cliente));
				marco.validate();
				marco.setVisible(true);
				
			}
		});
		btnRegistar.setBounds(137, 248, 91, 23);
		add(btnRegistar);
		
		JButton btnAtras = new JButton("Atras");
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				  	marco.setSize(250,212);
					marco.setContentPane(new inicioSesion(marco,cliente));
					marco.validate();
					marco.setVisible(true);
			}
		});
		btnAtras.setBounds(0, 0, 53, 23);
		add(btnAtras);
		
		JLabel lblRegistrarse = new JLabel("Registrarse");
		lblRegistrarse.setBounds(82, 11, 116, 14);
		add(lblRegistrarse);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(10, 45, 169, 14);
		add(lblNombre);
		
		JLabel lblNombreDeUsuario = new JLabel("Nombre de usuario");
		lblNombreDeUsuario.setBounds(10, 92, 190, 14);
		add(lblNombreDeUsuario);
		
		JLabel lblContrasea = new JLabel("Contrase\u00F1a");
		lblContrasea.setBounds(10, 138, 218, 14);
		add(lblContrasea);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(10, 155, 218, 20);
		add(passwordField);
		
		JLabel lblConfrimarContrasea = new JLabel("Confrimar contrase\u00F1a");
		lblConfrimarContrasea.setBounds(10, 186, 218, 14);
		add(lblConfrimarContrasea);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(10, 203, 218, 20);
		add(passwordField_1);

	}

}
