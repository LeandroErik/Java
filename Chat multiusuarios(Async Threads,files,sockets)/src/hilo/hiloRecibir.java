package hilo;

import java.io.IOException;


import javax.swing.JButton;
import javax.swing.JTextArea;

import socket.socketTcp;

public class hiloRecibir implements Runnable{

	private socketTcp socket;
	private String mensaje;
	private JTextArea text;
	private JButton btnCerrar;
	private boolean ejecutar = true;
	
	public hiloRecibir(socketTcp socket, JTextArea textArea,JButton btnCerrar) {
		this.socket = socket;
		this.text = textArea;
		this.btnCerrar=btnCerrar;
	}
	@Override
	public void run() {
		while(ejecutar)
		{
			try {
				mensaje = socket.Recibir();
				if(mensaje.equals("SERVER CERRADO")) {
					btnCerrar.doClick();
				}
				text.append(mensaje+"\n");
				
			}catch (Exception e) {
				try {
					socket.CerrarIO();
				} catch (IOException e1) {
					System.out.println("Error al cerrar socket.");
				}
				detener();
			}
		}
	}
	public void detener()
	{
		ejecutar = false;
	}
}
