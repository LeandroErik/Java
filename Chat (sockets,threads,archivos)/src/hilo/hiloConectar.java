package hilo;

import java.io.EOFException;
import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;

import javax.swing.JTextArea;

import clases.archivoTextoPlano;
import socket.*;

public class hiloConectar implements Runnable {
	socketTcp clienteActual=new socketTcp();
	protected ArrayList<Socket> clientes = new ArrayList<Socket>();
	ArrayList<String> nombres = new ArrayList<String>();;
	
	private String NombreActual;
	private Socket ClienteOriginal= new Socket();
	private JTextArea txtArea;

	
	private archivoTextoPlano archivo=new archivoTextoPlano();
	
	public hiloConectar(ArrayList<Socket> clientes, ArrayList<String> nombres,Socket clienteActual,JTextArea area) {
		this.clientes=clientes;
		this.nombres=nombres;
		this.txtArea=area;
		ClienteOriginal=clienteActual;
		this.clienteActual.setSocket(clienteActual);
		
	}
	public void run() {
	    	try {
				NombreActual=clienteActual.Recibir();
			} 
	    	catch (Exception e) {
				e.printStackTrace();
			}
	    
	    for(Socket Cliente : clientes) {
	    	if(Cliente!=ClienteOriginal) {
			clienteActual.setSocket(Cliente);
			try {
				clienteActual.Enviar(NombreActual+" se ha unido a la conversacion.");
			} catch (Exception e) {
				
			}
	    	}
		}
	    archivo.escribirArchivo("/Cliente]: "+NombreActual+" se ha unido a la sala.");
		clienteActual.setSocket(ClienteOriginal);
		String Mensaje = null;
		boolean Valido=true;
		
		do {
			try {
				Mensaje=clienteActual.Recibir();
				Mensaje=NombreActual+": "+Mensaje;
				
				for(Socket Cliente1 : clientes) {
					if(Cliente1!=ClienteOriginal) {
					clienteActual.setSocket(Cliente1);
					clienteActual.Enviar(Mensaje);
				}
				}
				txtArea.append(NombreActual+" ha enviado un mensaje.\n");
				clienteActual.setSocket(ClienteOriginal);
				archivo.escribirArchivo("/Server]: Mensaje reenviado a todos los clientes.");
			}
			catch (SocketException e1) {
				
				for(Socket Cliente2 : clientes) {
					if(Cliente2!=ClienteOriginal) {
					clienteActual.setSocket(Cliente2);
					try {
						clienteActual.Enviar("SERVER CERRADO");
						
					} catch (Exception E) {
						System.out.println("/Cliente]: error al cerrar sala.");
					}
					}
				}
				archivo.escribirArchivo("/Server]: Cerrando sala.");
				try {
					ClienteOriginal.close();
				} catch (IOException E) {
				}
				Valido=false;
				
		}catch (EOFException e) {
				for(Socket Cliente2 : clientes) {
					if(Cliente2!=ClienteOriginal) {
					clienteActual.setSocket(Cliente2);
					try {
						clienteActual.Enviar(NombreActual+" ha abandonado la sala.");
					}
					catch (Exception e1) {
						
					}
					}
					
				}
				//archivo.escribirArchivo("/Cliente]: "+NombreActual+" ha abandonado la sala.");
				if(clienteActual.seDesconecto()==false) {
					txtArea.append(NombreActual+" ha abandonado la sala.\n");
				}
				
				nombres.remove(NombreActual);
				clientes.remove(ClienteOriginal);
				
				try {
					ClienteOriginal.close();
				} catch (IOException e1) {
					archivo.escribirArchivo("/ERROR-Cliente]: "+NombreActual+" error al cerrar el socket.");
				}
				
				Valido=false;
			}
			catch(ConcurrentModificationException e2) {
				Valido=false;
			}catch(Exception e2) {
				Valido=false;
			}
		}while(Valido==true);
	    
	}
}
