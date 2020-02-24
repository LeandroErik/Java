package interfaz;

import javax.swing.*;

import archivos.archivoBinario;
import archivos.confiCliente;
import clases.cliente;
import socket.socketClient;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.UnknownHostException;
import java.awt.event.ActionEvent;

public class conectarCliente extends JPanel {
	
	private JTextField textIP;
	private JTextField textPuerto;
	private socketClient cliente=new socketClient();
	
	private confiCliente user=new confiCliente();
	private String IP;
	private int puerto;
	
	private archivoBinario archivoBin=new archivoBinario();

	/**
	 * Create the panel.
	 */
	public conectarCliente(JFrame marco) {
		setLayout(null);
		//Lee el archivo de config_cliente 
		try {
			user=archivoBin.recibirCliente();
		
			System.out.println(user.getPuerto()+user.getIp());
		} catch (ClassNotFoundException | IOException e1) {
			e1.printStackTrace();
		}
		
		JLabel lblConectar = new JLabel("Conectar");
		lblConectar.setBounds(10, 9, 46, 14);
		add(lblConectar);
		
		textIP = new JTextField(user.getIp());
		textIP.setBounds(10, 49, 132, 20);
		add(textIP);
		textIP.setColumns(10);
		
		JLabel lbIp = new JLabel("IP");
		lbIp.setBounds(10, 34, 46, 14);
		add(lbIp);
		
		JLabel lbPuerto = new JLabel("Puerto");
		lbPuerto.setBounds(10, 74, 46, 14);
		add(lbPuerto);
		
		textPuerto = new JTextField(user.getPuerto()+"");
		textPuerto.setBounds(10, 89, 132, 20);
		add(textPuerto);
		textPuerto.setColumns(10);
		
		JButton btnConectar = new JButton("Conectar");
		btnConectar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
				try {
					IP=textIP.getText();
					puerto=Integer.parseInt(textPuerto.getText());

					cliente.Conectar(IP, puerto);
					System.out.println("Cliente conectado");
					
					if(cliente.seConecto()) {
						user=new confiCliente(textIP.getText(),Integer.parseInt(textPuerto.getText()));
						
						//Guardar el .dat del cliente
						archivoBin.escribirCliente(user);
						//Cambiando de venatana
					
						marco.setSize(250,212);
						marco.setContentPane(new inicioSesion(marco,cliente));
						marco.validate();
						marco.setVisible(true);
					}
				} catch (UnknownHostException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				marco.setSize(250,212);
				marco.setContentPane(new inicioSesion(marco,cliente));
				marco.validate();
				marco.setVisible(true);
			}
		});
		btnConectar.setBounds(148, 70, 91, 23);
		add(btnConectar);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(-1, 23, 251, 2);
		add(separator);

	}

}
