package interfaz;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import DAO.UsuarioDAO;
import DAO.UsuarioDaoImpl;
import clases.Usuario;

import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class inicio extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textCorreo;
	private JPasswordField passwordField;
	private UsuarioDAO userdao = new UsuarioDaoImpl();

	/**
	 * Create the panel.
	 */
	public inicio(marcoCliente marco) {
		setLayout(null);
		
		JLabel lblIngreseCorreo = new JLabel("Ingrese correo:");
		lblIngreseCorreo.setBounds(10, 11, 86, 14);
		add(lblIngreseCorreo);
		
		JLabel lblIngreseContrasea = new JLabel("Ingrese contrase\u00F1a:");
		lblIngreseContrasea.setBounds(10, 36, 108, 14);
		add(lblIngreseContrasea);
		
		JLabel lblCorreoOContrasea = new JLabel("correo o contrase\u00F1a incorrecta");
		lblCorreoOContrasea.setForeground(Color.RED);
		lblCorreoOContrasea.setBounds(44, 61, 166, 14);
		lblCorreoOContrasea.setVisible(false);
		add(lblCorreoOContrasea);
		
		textCorreo = new JTextField();
		textCorreo.setBounds(147, 8, 86, 20);
		add(textCorreo);
		textCorreo.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(147, 33, 86, 20);
		add(passwordField);
		
		JButton btnIngresar = new JButton("Ingresar");
		btnIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StringBuffer sb = new StringBuffer();
				for (char c : passwordField.getPassword()) {
					sb.append(c);
				}
				try {
					if(userdao.ValidarUser(textCorreo.getText(), sb.toString()))
					{
						marco.setSize(460,330);
						marco.setContentPane(new interUser(marco, userdao.darUser(textCorreo.getText(), sb.toString())));
						marco.validate();
					}
					else
					{
						lblCorreoOContrasea.setVisible(true);
					}
				} catch (Exception e1) {
					// TODO Bloque catch generado automáticamente
					e1.printStackTrace();
				}
			}
		});
		btnIngresar.setBounds(7, 86, 89, 23);
		add(btnIngresar);
		
		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				marco.setSize(270,300);
				marco.setContentPane(new Registrar(marco));
				marco.validate();
			}
		});
		btnRegistrar.setBounds(144, 86, 89, 23);
		add(btnRegistrar);
	}
}
