package ventanas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import Clases.Tema;
import DAO.TemaDAO;
import DAO.TemaDAOImpl;

public class Inicio extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JTable table;
	private TemaDAO temDao= new TemaDAOImpl();
	

	public Inicio(marco mar) {
		setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(119, 39, 86, 20);
		add(textField);
		textField.setColumns(10);
		
		JButton btnBuscar = new JButton("Buscar:");
		btnBuscar.setBounds(20, 38, 89, 23);
		add(btnBuscar);
		
		JButton btnCrearTema = new JButton("Crear Tema");
		btnCrearTema.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mar.setContentPane(new NuevoTema(mar));
				mar.validate();
			} 
		});
		btnCrearTema.setBounds(20, 206, 89, 23);
		add(btnCrearTema);
		
		JButton btnEliminarTema = new JButton("Eliminar Tema");
		btnEliminarTema.setBounds(151, 206, 114, 23);
		add(btnEliminarTema);
		
		JButton btnModificarTema = new JButton("Modificar Tema");
		btnModificarTema.setBounds(307, 206, 105, 23);
		add(btnModificarTema);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(119, 91, 182, 82);
		add(scrollPane);
		
		table = new JTable();
		String[] list = new String[5];
		int i=0;
		try {
			for (Tema t: temDao.listar()) {
				list[i]=t.getPalclave();
				i++;
		}
		} catch (Exception e2) {
			
			e2.printStackTrace();
		}
		try {
			table.setModel(new DefaultTableModel(
				new Object[][] {
					{list[0]},
					{list[1]},
					{list[2]},
					{list[3]},
					
				},
				new String[] {
					"Temas"
				}
			));
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		scrollPane.setViewportView(table);
		
	}
}
