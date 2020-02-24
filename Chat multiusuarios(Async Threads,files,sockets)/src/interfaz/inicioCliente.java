package interfaz;

import javax.swing.*;

import clases.mensaje;
import socket.socketClient;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class inicioCliente extends JPanel {
	private mensaje msgCrearGrupo;
	private JTable table;

	/**
	 * Create the panel.
	 */
	public inicioCliente(JFrame marco,socketClient cliente) {
		setLayout(null);
		
		JLabel lblMisGrupos = new JLabel("Mis grupos");
		lblMisGrupos.setBounds(10, 43, 102, 14);
		add(lblMisGrupos);
		
		JButton btnCrearGrupo = new JButton("Crear"+ " grupo");
		btnCrearGrupo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String confirmacion="no";
				try {
					do {
						//Solicitando el nombre del grupo
						String nombreGrupo=JOptionPane.showInputDialog("Ingrese nombre del grupo");
						msgCrearGrupo=new mensaje(4,nombreGrupo);
						//Enviando la peticion al hiloEscucha(que le pega al servidor) para crear un grupo
						cliente.EnviarObj(msgCrearGrupo);
						//Esperando respuesta de que si se puedo crear del tipo string
						confirmacion=cliente.Recibir();
						
					} while (confirmacion.equals("no"));
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		btnCrearGrupo.setBounds(134, 53, 106, 23);
		add(btnCrearGrupo);
		
		JButton btnUnirseAUn = new JButton("Unirse a un grupo");
		btnUnirseAUn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
			}
		});
		btnUnirseAUn.setBounds(134, 87, 106, 23);
		add(btnUnirseAUn);
		
		JButton btnSalirDeUn = new JButton("Salir de \r\nun grupo");
		btnSalirDeUn.setBounds(134, 126, 106, 23);
		add(btnSalirDeUn);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 33, 250, 2);
		add(separator);
		
		JLabel lblBienvenido = new JLabel("Bienvenido ");
		lblBienvenido.setBounds(65, 11, 96, 14);
		add(lblBienvenido);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 72, 114, 102);
		add(scrollPane);
		String[] columNames= {"Nombre"};
		String [][] data={ {"hola"},{"hola1"}};
		
		table = new JTable(data,columNames);
		table.setCellSelectionEnabled(true);
		table.setEnabled(false);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount()==2) {
					JTable target=(JTable)e.getSource();
					int row=target.getSelectedRow();
					String nomSala="Hola1";
					marco.setSize(250,223);
					marco.setContentPane(new chatCliente(cliente,nomSala,marco));
					marco.validate();
					marco.setVisible(true);
					
					
				}
			}
		});
		scrollPane.setViewportView(table);

	}
}
