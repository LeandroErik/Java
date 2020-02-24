package interfaz;

import javax.swing.JPanel;

import clases.Usuario;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JPasswordField;

import DAO.UsuarioDAO;
import DAO.UsuarioDaoImpl;

import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CamContra extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPasswordField passwordFieldActual;
	private JPasswordField passwordFieldNueva;
	private JPasswordField passwordFieldConfirmacion;
	private UsuarioDAO userdao = new UsuarioDaoImpl();

	/**
	 * Create the panel.
	 */
	public CamContra(marcoCliente marco, Usuario user) {
		setLayout(null);
		
		JLabel lblCambiarContrasea = new JLabel("Cambiar contrase\u00F1a");
		lblCambiarContrasea.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCambiarContrasea.setBounds(62, 11, 150, 17);
		add(lblCambiarContrasea);
		
		JLabel lblContraseaActual = new JLabel("Contrase\u00F1a actual");
		lblContraseaActual.setBounds(10, 39, 100, 14);
		add(lblContraseaActual);
		
		JLabel lblNeuvaContrasea = new JLabel("Neuva Contrase\u00F1a");
		lblNeuvaContrasea.setBounds(10, 70, 100, 14);
		add(lblNeuvaContrasea);
		
		JLabel lblConfirmeContrasea = new JLabel("Confirme contrase\u00F1a");
		lblConfirmeContrasea.setBounds(10, 95, 107, 14);
		add(lblConfirmeContrasea);
		
		passwordFieldActual = new JPasswordField();
		passwordFieldActual.setBounds(145, 39, 116, 17);
		add(passwordFieldActual);
		
		passwordFieldNueva = new JPasswordField();
		passwordFieldNueva.setBounds(145, 68, 116, 17);
		add(passwordFieldNueva);
		
		passwordFieldConfirmacion = new JPasswordField();
		passwordFieldConfirmacion.setBounds(145, 93, 116, 17);
		add(passwordFieldConfirmacion);
		
		JLabel lblContraseaIncorrecta = new JLabel("Contrase\u00F1a incorrecta");
		lblContraseaIncorrecta.setForeground(Color.RED);
		lblContraseaIncorrecta.setBounds(145, 55, 116, 14);
		add(lblContraseaIncorrecta);
		lblContraseaIncorrecta.setVisible(false);
		
		JLabel lblContraseasNoIguales = new JLabel("Contrase\u00F1as no iguales");
		lblContraseasNoIguales.setForeground(Color.RED);
		lblContraseasNoIguales.setBounds(139, 109, 122, 14);
		add(lblContraseasNoIguales);
		lblContraseasNoIguales.setVisible(false);
		
		JButton btnCambiar = new JButton("Cambiar");
		btnCambiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				StringBuffer sb = new StringBuffer();
				for(char c : passwordFieldActual.getPassword())
				{
					sb.append(c);
				}
				if(user.getContrasenia().equals(sb.toString()))
				{
					if(Comparar(passwordFieldNueva, passwordFieldConfirmacion))
					{
						sb = new StringBuffer();
						for(char c : passwordFieldNueva.getPassword())
						{
							sb.append(c);
						}
						try {
							userdao.ModificarContra(sb.toString(), user.getId());
							marco.setSize(460,330);
							marco.setContentPane(new interUser(marco, user));
							marco.validate();
						} catch (Exception e) {
							// TODO Bloque catch generado automáticamente
							e.printStackTrace();
						}
					}
					else
					{
						lblContraseasNoIguales.setVisible(true);
					}
				}
				else
				{
					lblContraseaIncorrecta.setVisible(true);
				}
			}
		});
		btnCambiar.setBounds(172, 140, 89, 23);
		add(btnCambiar);
		
		JButton btnAtras = new JButton("Atras");
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				marco.setSize(460,330);
				marco.setContentPane(new interUser(marco, user));
				marco.validate();
			}
		});
		btnAtras.setBounds(10, 140, 89, 23);
		add(btnAtras);

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
		return false;
	}
}
