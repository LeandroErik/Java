package hilo;

import java.io.IOException;

import javax.swing.JTextArea;

import socket.socketTcp;

public class hilo implements Runnable{

	private socketTcp socket;
	private String mensaje;
	private JTextArea text;
	private boolean ejecutar = true;
	
	public hilo(socketTcp socket, JTextArea text) {
		this.socket = socket;
		this.text = text;
	}

	@Override
	public void run() {
		while(ejecutar)
		{
			try {
				mensaje = "[EL] : "+socket.Recibir()+"\n" ;
				text.append(mensaje);
			} catch (IOException e) {
				detener();
			}
		}
	}
	public void detener()
	{
		ejecutar = false;
	}
}
