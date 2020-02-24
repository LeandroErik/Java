package hilo;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

import clases.cliente;
import clases.mensaje;
import clases.sala;
import socket.socketClient;
import socket.socketServer;
import socket.socketTcp;

public class hiloEscucha implements Runnable{
	
	private socketTcp clienteActual=new socketTcp();
	private Socket socketActual=new Socket();
	
	private ArrayList<sala>salasChat=new ArrayList<sala>();
	private ArrayList<Socket> clientesSala=new ArrayList<>();
	private mensaje mensajeRecibido;
	
public hiloEscucha(Socket socketActual, ArrayList<sala> salasChat) {
	this.socketActual = socketActual;
	this.salasChat = salasChat;
	clienteActual.setSocket(socketActual);
}

/*1:validar registro
 *2:reenviar mensaje a la sala
 *3:Cambiar contraseña 
 *4:Crear Grupo
 * */
	@Override
	public void run() {
		while (true) {
			try {
				mensajeRecibido=(mensaje) clienteActual.RecibirObj();
				
				switch (mensajeRecibido.getId()) {
				case 1:/*Validar usuario*/
					
					break;
				case 2:/*Reenviar mensaje*/
					for (sala salaActual : salasChat) {
						if(salaActual.getNombreSala().equals(mensajeRecibido.getNombreSala())) {
							clientesSala=salaActual.getClientes();
							for (Socket actual : clientesSala) {
								clienteActual.setSocket(actual);
								clienteActual.Enviar(mensajeRecibido.getMensaje());
							}
							
						}
					}
					break;
				case 3:/*Cambiar contraseña*/
					
					break;
				case 4:/*Crear grupo*/
	
					break;




				default:
					break;
				}
				
				
				
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
				
			}
			
			
			
		}


		
	}

	

