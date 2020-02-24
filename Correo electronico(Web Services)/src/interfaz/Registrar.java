
package interfaz;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;

import DAO.UsuarioDAO;
import DAO.UsuarioDaoImpl;
import clases.Usuario;

import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class Registrar extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textNom;
	private JTextField textApe;
	private JTextField textCorreo;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private UsuarioDAO userdao = new UsuarioDaoImpl();

	/**
	 * Create the panel.
	 */
	public Registrar(marcoCliente marco) {
		setLayout(null);
		
		JLabel lblRegistrar = new JLabel("Registrar");
		lblRegistrar.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblRegistrar.setBounds(76, 11, 86, 19);
		add(lblRegistrar);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(10, 41, 56, 14);
		add(lblNombre);
		
		JLabel lblApellido = new JLabel("Apellido:");
		lblApellido.setBounds(10, 79, 56, 14);
		add(lblApellido);
		
		JLabel lblCorreo = new JLabel("Correo:");
		lblCorreo.setBounds(10, 123, 46, 14);
		add(lblCorreo);
		
		JLabel lblContrasea = new JLabel("Contrase\u00F1a:");
		lblContrasea.setBounds(10, 167, 70, 14);
		add(lblContrasea);
		
		JLabel lblConfirmeContrasea = new JLabel("Confirme contrase\u00F1a:");
		lblConfirmeContrasea.setBounds(10, 192, 114, 14);
		add(lblConfirmeContrasea);
		
		textNom = new JTextField();
		textNom.setBounds(141, 38, 114, 20);
		add(textNom);
		textNom.setColumns(10);
		
		textApe = new JTextField();
		textApe.setBounds(141, 76, 114, 20);
		add(textApe);
		textApe.setColumns(10);
		
		textCorreo = new JTextField();
		textCorreo.setBounds(141, 120, 70, 20);
		add(textCorreo);
		textCorreo.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(141, 164, 114, 20);
		add(passwordField);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(141, 189, 114, 20);
		add(passwordField_1);
		
		JLabel lblSoloLetras = new JLabel("Solo letras");
		lblSoloLetras.setForeground(Color.RED);
		lblSoloLetras.setBounds(141, 57, 70, 14);
		lblSoloLetras.setVisible(false);
		add(lblSoloLetras);
		
		JLabel lblSoloLetras_1 = new JLabel("Solo letras");
		lblSoloLetras_1.setForeground(Color.RED);
		lblSoloLetras_1.setBounds(141, 95, 70, 14);
		lblSoloLetras_1.setVisible(false);
		add(lblSoloLetras_1);
		
		JLabel lblYaExistente = new JLabel("Ya existente");
		lblYaExistente.setForeground(Color.RED);
		lblYaExistente.setBounds(141, 139, 70, 14);
		lblYaExistente.setVisible(false);
		add(lblYaExistente);
		
		JLabel lblContraseaNoIgual = new JLabel("Contrase\u00F1a no igual");
		lblContraseaNoIgual.setForeground(Color.RED);
		lblContraseaNoIgual.setBounds(141, 211, 114, 14);
		lblContraseaNoIgual.setVisible(false);
		add(lblContraseaNoIgual);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(SoloLetras(textNom))
				{
					if(SoloLetras(textApe))
					{
						if(Comparar(passwordField, passwordField_1))
						{
							if(validar(textCorreo))
							{
							
								StringBuffer sb = new StringBuffer();
								for (char c : passwordField.getPassword()) {
									sb.append(c);
								}
								String nom = textNom.getText();
								String ape = textApe.getText();
								String correo = textCorreo.getText() + "@mail.com";
								Usuario userR = new Usuario(nom, ape, correo, sb.toString());
								try {
									if(userdao.Registrar(userR))
									{
										marco.setSize(250, 150);
										marco.setContentPane(new inicio(marco));
										marco.validate();
									}
									else
									{
										lblContraseaNoIgual.setVisible(true);
									}
								} catch (Exception e) {
									// TODO Bloque catch generado automáticamente
									e.printStackTrace();
								}
								
							}
							else
							{
								lblSoloLetras_1.setVisible(true);
							}
						}
						else
						{
							lblContraseaNoIgual.setVisible(true);
						}
					}
					else
					{
						lblSoloLetras_1.setVisible(true);
					}
				}
				else
				{
					lblSoloLetras.setVisible(true);
				}
				
			}
		});
		btnAceptar.setBounds(10, 236, 89, 23);
		add(btnAceptar);
		
		JButton btnAtras = new JButton("Atras");
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				marco.setSize(250, 150);
				marco.setContentPane(new inicio(marco));
				marco.validate();
			}
		});
		btnAtras.setBounds(166, 236, 89, 23);
		add(btnAtras);
		
		JLabel lblmail = new JLabel("@mail");
		lblmail.setBounds(221, 123, 46, 14);
		add(lblmail);

	}

	public boolean SoloLetras(JTextField tf)
	{
		for (char c : tf.getText().toCharArray()) {
			if (!Character.isLetter(c))
			{
				return false;
			}
		}
		return true;
	}
	
	public boolean Comparar(JPasswordField pas, JPasswordField passRep)
	{
		StringBuffer pass = new StringBuffer();
		StringBuffer passR = new StringBuffer();
		for (char c : pas.getPassword()) {
			pass.append(c);
		}
		for (char c : passRep.getPassword()) {
			passR.append(c);
		}
		if (passR.toString().equals(pass.toString()))
		{
			return true;
		}
		return true;
	}
	
	public boolean validar(JTextField text)
	{
		for(char c : text.getText().toCharArray())
		{
			if(!Character.isLetter(c) && c != '.' && !Character.isDigit(c))
			{
				return false;
			}
		}
		return true;
	}
}
