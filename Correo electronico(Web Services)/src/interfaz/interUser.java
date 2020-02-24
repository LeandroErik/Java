package interfaz;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import DAO.MensajeDAO;
import DAO.MensajeDaoImpl;
import DAO.UsuarioDAO;
import DAO.UsuarioDaoImpl;
import clases.Mensaje;
import clases.Usuario;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class interUser extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
	private String[] titulos = {"Emisor","Receptor", "Asunto", "Mensaje"};
	private UsuarioDAO userdao = new UsuarioDaoImpl();
	private MensajeDAO msjdao = new MensajeDaoImpl();
	ArrayList<Mensaje> msjs = new ArrayList<>();

	/**
	 * Create the panel.
	 */
	public interUser(marcoCliente marco, Usuario user) {
		setLayout(null);
		
		JLabel lblBienvenido = new JLabel("Bienvenido: " + user.getCorreo());
		lblBienvenido.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		lblBienvenido.setBounds(145, 11, 178, 23);
		add(lblBienvenido);
		
		JButton btnOutBox = new JButton("InBox");
		btnOutBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				marco.setSize(460,300);
				marco.setContentPane(new Recibidos(marco, user));
				marco.validate();
			}
		});
		btnOutBox.setBounds(10, 45, 95, 23);
		add(btnOutBox);
		
		JButton btnEliminados = new JButton("Eliminados");
		btnEliminados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				marco.setSize(460,300);
				marco.setContentPane(new Elilminados(marco, user));
				marco.validate();
			}
		});
		btnEliminados.setBounds(115, 45, 95, 23);
		add(btnEliminados);
		
		JButton btnCerrarSesion = new JButton("Cerrar Sesion");
		btnCerrarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				marco.setSize(250,150);
				marco.setContentPane(new inicio(marco));
				marco.validate();
			}
		});
		btnCerrarSesion.setBounds(317, 266, 123, 23);
		add(btnCerrarSesion);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 79, 430, 176);
		add(scrollPane);
		
		// REALIZO EL MODELO DE LA TABLA
		
		try {
			msjs = msjdao.MsjEnviados(user.getId());
		} catch (Exception e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}
		String[][] matris = new String[msjs.size()][4];
		int i = 0;
		for (Mensaje msj : msjs) {
			matris[i][0] = msj.getEmisor();
			matris[i][1] = msj.getReceptor();
			matris[i][2] = msj.getAsunto(); 
			matris[i][3] = msj.getTexto();
			i++;
		}
		DefaultTableModel modelo = new DefaultTableModel(matris, titulos);
		
		// FINALIZO EL MODELO
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(modelo);
		
		
		JButton btnSeleccionar = new JButton("Seleccionar");
		btnSeleccionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int posicion = table.getSelectedRow();
				Mensaje msj = msjs.get(posicion);
				marco.setSize(250,310);
				marco.setContentPane(new DetMsj(marco, user, msj));
				marco.validate();
				
			}
		});
		btnSeleccionar.setBounds(10, 266, 101, 23);
		add(btnSeleccionar);
		
		JButton btnCambiarContrasea = new JButton("Cambiar contrase\u00F1a");
		btnCambiarContrasea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				marco.setSize(270,200);
				marco.setContentPane(new CamContra(marco, user));
				marco.validate();
			}
		});
		btnCambiarContrasea.setBounds(141, 266, 152, 23);
		add(btnCambiarContrasea);
		
		JButton btnRedactar = new JButton("Redactar");
		btnRedactar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				marco.setSize(250,310);
				marco.setContentPane(new NuevoMensaje(marco, user));
				marco.validate();
			}
		});
		btnRedactar.setBounds(234, 45, 89, 23);
		add(btnRedactar);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int posicion = table.getSelectedRow();
				System.out.println(posicion);
				try {
					msjdao.eliminarMensaje(msjs.get(posicion).getId());
				} catch (Exception e) {
					// TODO Bloque catch generado automáticamente
					e.printStackTrace();
				}
			}
		});
		btnEliminar.setBounds(351, 45, 89, 23);
		add(btnEliminar);

	}
}
