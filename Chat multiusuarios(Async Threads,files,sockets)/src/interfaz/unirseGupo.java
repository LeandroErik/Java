package interfaz;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import clases.mensaje;
import socket.socketClient;

import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class unirseGupo extends JPanel {
	private JTable table;

	/**
	 * Create the panel.
	 */
	public unirseGupo(socketClient cliente,JFrame marco) {
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 25, 230, 134);
		add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnUnirse = new JButton("Unirse");
		btnUnirse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Agregar al usuario en el array de sockets de una salaChat
				mensaje msgUnirse=new mensaje(4, "Cualquiera");
				try {
					cliente.EnviarObj(msgUnirse);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnUnirse.setBounds(149, 170, 91, 23);
		add(btnUnirse);

	}
}
