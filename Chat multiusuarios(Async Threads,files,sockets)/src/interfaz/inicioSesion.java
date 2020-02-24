package interfaz;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.mysql.fabric.Server;

import clases.cliente;

import socket.socketClient;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.EOFException;
import java.net.SocketException;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.Box;
import javax.swing.JSeparator;

public class inicioSesion extends JPanel {
	private JTextField textNombre;
	private JFrame marco;
	private JPasswordField passwordField;
	private socketClient cliente;
	private cliente user;
	
	
	//medidas 183x250

	public inicioSesion(JFrame marco,socketClient cliente) {
		setLayout(null);
		
		JLabel lblIniciarSesion = new JLabel("Iniciar sesion");
		lblIniciarSesion.setBounds(89, 11, 116, 14);
		add(lblIniciarSesion);
		
		textNombre = new JTextField();
		textNombre.setBounds(10, 62, 209, 20);
		add(textNombre);
		textNombre.setColumns(10);
		
		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				marco.setSize(250,312);
				marco.setContentPane(new registroUsuario(marco,cliente));
				marco.validate();
				marco.setVisible(true);
			}
		});
		btnRegistrar.setBounds(24, 149, 91, 23);
		add(btnRegistrar);
		
		JLabel lblNombreDeUsuario = new JLabel("Nombre de usuario");
		lblNombreDeUsuario.setBounds(10, 46, 195, 14);
		add(lblNombreDeUsuario);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(10, 108, 209, 20);
		add(passwordField);
		
		JLabel lblContrasea = new JLabel("Contrase\u00F1a");
		lblContrasea.setBounds(10, 93, 195, 14);
		add(lblContrasea);
		
		JButton btnIngresar = new JButton("Ingresar");
		btnIngresar.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
				//Falata hacer la validacion por la base de datos
					marco.setSize(250,205);
					marco.setContentPane(new inicioCliente(marco,cliente));
					marco.validate();
					marco.setVisible(true);
			}
		});
		btnIngresar.setBounds(125, 149, 91, 23);
		add(btnIngresar);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(239, 26, -242, 2);
		add(separator);
		

	}
}
