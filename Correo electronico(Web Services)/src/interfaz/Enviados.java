package interfaz;

import javax.swing.JPanel;

import clases.Mensaje;
import clases.Usuario;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import DAO.MensajeDAO;
import DAO.MensajeDaoImpl;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Enviados extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
	private MensajeDAO msjdao = new MensajeDaoImpl();
	private String [] titulos = {"Emisor","Receptor", "Asunto", "Mensaje"};
	private ArrayList<Mensaje> msjs = new ArrayList<>();

	/**
	 * Create the panel.
	 */
	public Enviados(marcoCliente marco, Usuario user) {
		setLayout(null);
		
		JLabel lblMensajesEnviado = new JLabel("Mensajes enviados:");
		lblMensajesEnviado.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblMensajesEnviado.setBounds(137, 11, 160, 24);
		add(lblMensajesEnviado);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 47, 430, 185);
		add(scrollPane);
		
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
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(modelo);
		
		JButton btnAtras = new JButton("Atras");
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				marco.setSize(460,330);
				marco.setContentPane(new interUser(marco, user));
				marco.validate();
			}
		});
		btnAtras.setBounds(10, 243, 89, 23);
		add(btnAtras);
		
		JButton btnSeleccionar = new JButton("Seleccionar");
		btnSeleccionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int posicion = table.getSelectedRow();
				Mensaje msj = msjs.get(posicion);
				marco.setSize(250,310);
				marco.setContentPane(new DetMsj(marco, user, msj));
				marco.validate();
			}
		});
		btnSeleccionar.setBounds(179, 243, 97, 23);
		add(btnSeleccionar);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int posicion = table.getSelectedRow();
				Mensaje msj = msjs.get(posicion);

				try {
					msjdao.eliminarMensaje(msj.getId());
					marco.repaint();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnEliminar.setBounds(351, 243, 89, 23);
		add(btnEliminar);

	}
}
