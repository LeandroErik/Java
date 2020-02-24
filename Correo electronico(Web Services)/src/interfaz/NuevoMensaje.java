package interfaz;

import javax.swing.JPanel;

import clases.Mensaje;
import clases.Usuario;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;

import DAO.MensajeDAO;
import DAO.MensajeDaoImpl;
import DAO.UsuarioDAO;
import DAO.UsuarioDaoImpl;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.awt.event.ActionEvent;

public class NuevoMensaje extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textCorreo;
	private JTextField textAsunto;
	private UsuarioDAO userdao = new UsuarioDaoImpl();
	private MensajeDAO msjdao = new MensajeDaoImpl();

	/**
	 * Create the panel.
	 */
	public NuevoMensaje(marcoCliente marco, Usuario user) {
		setLayout(null);
		
		JLabel lblNuevoMensaje = new JLabel("Nuevo mensaje");
		lblNuevoMensaje.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNuevoMensaje.setBounds(66, 11, 122, 17);
		add(lblNuevoMensaje);
		
		JLabel lblPara = new JLabel("Para:");
		lblPara.setBounds(10, 44, 46, 14);
		add(lblPara);
		
		JLabel lblAsunto = new JLabel("Asunto:");
		lblAsunto.setBounds(10, 69, 46, 14);
		add(lblAsunto);
		
		JLabel lblTexto = new JLabel("Texto:");
		lblTexto.setBounds(10, 94, 46, 14);
		add(lblTexto);
		
		textCorreo = new JTextField();
		textCorreo.setBounds(66, 39, 162, 20);
		add(textCorreo);
		textCorreo.setColumns(10);
		
		textAsunto = new JTextField();
		textAsunto.setBounds(66, 66, 162, 20);
		add(textAsunto);
		textAsunto.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(66, 94, 162, 141);
		add(scrollPane);
		
		JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		
		JButton btnEnviar = new JButton("Enviar");
		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(userdao.darID(textCorreo.getText())!=0)
					{
						int idReceptor = userdao.darID(textCorreo.getText());
						String asunto = textAsunto.getText();
						String texto = textArea.getText();
						LocalDate fecha = LocalDate.now();
						Mensaje msj = new Mensaje(asunto, texto, 0, 0, user.getId(), idReceptor, fecha);
						msjdao.nuevoMensaje(msj);
						marco.setSize(460,330);
						marco.setContentPane(new interUser(marco, user));
						marco.validate();
					}
					else
					{
						System.out.println("correo incorrecto");
					}
				} catch (Exception e1) {
					// TODO Bloque catch generado automáticamente
					e1.printStackTrace();
				}
			}
		});
		btnEnviar.setBounds(139, 246, 89, 23);
		add(btnEnviar);
		
		JButton btnAtras = new JButton("Atras");
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				marco.setSize(460,330);
				marco.setContentPane(new interUser(marco, user));
				marco.validate();
			}
		});
		btnAtras.setBounds(10, 246, 89, 23);
		add(btnAtras);

	}
}
