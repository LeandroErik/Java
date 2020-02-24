package hilo;

import java.awt.Color;
import java.awt.Font;

import java.io.IOException;
import java.net.BindException;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;

import javax.swing.*;
import socket.*;

public class hiloServidor implements Runnable{
	private socketServer server = new socketServer();
	private ArrayList<Socket> ConexionesClientes = new ArrayList<>();
	private ArrayList<String> NombresClientes= new ArrayList<>();
	String nombreCliente;
	private int puerto=0;
	
	private JTextArea txtarea;
	private JButton cerrar;
	private JButton escuchar;
	private JTextField campoPuerto;
	
	private boolean nomValido=true;
	
	public hiloServidor(socketServer server,int puerto,JTextArea area,JButton cerrar,JButton escuchar,JTextField campoPuerto) {
		this.server=server;
		this.puerto=puerto;
		this.txtarea=area;
		this.cerrar=cerrar;
		this.escuchar=escuchar;
		this.campoPuerto=campoPuerto;
	}
	public void run() {
	    	  try {
	    		  
	    		
	    		txtarea.setForeground(Color.black);
	    		 
				server.Escuchar(puerto);
								
				txtarea.setText("Escuchando en el puerto : "+puerto+"...\n");
				escuchar.setEnabled(false);
				campoPuerto.setEnabled(false);
			
				while(true) {
					server.Aceptar();
					
					cerrar.setEnabled(true);
					do {
						nomValido=true;
						nombreCliente=server.Recibir();
						
						for (String nomActual : NombresClientes) {
						if(nombreCliente.equals(nomActual)) {
							nomValido=false;
						
							
						}
					}
						if(nomValido==true) {
							server.Enviar("si");
						}else {
							txtarea.append("Cliente nombre repetido.\n");
						server.Enviar("no");
						}
						
					}while(nomValido==false);
					
					NombresClientes.add(nombreCliente);
					
					
					ConexionesClientes.add(server.getSocket());
					
					new Thread(new hiloConectar(ConexionesClientes, NombresClientes, server.getSocket(), txtarea)).start();
							
					txtarea.append(nombreCliente+" se ha conectado.\n");
					
				}
				
			}catch (NumberFormatException e1) {
				
				txtarea.setFont(new Font("Arial", Font.PLAIN, 13));
				txtarea.setForeground(Color.red);
				txtarea.append("Reingrese el puerto.\n");
				
				cerrar.setEnabled(false);
				escuchar.setEnabled(true);
				campoPuerto.setText("");
				campoPuerto.setEnabled(true);
				
				}
			catch (BindException e2) {
				txtarea.setText("");
				escuchar.setEnabled(true);
				campoPuerto.setEnabled(true);
				campoPuerto.setText("");
				txtarea.setFont(new Font("Arial", Font.PLAIN, 13));
				txtarea.setForeground(Color.red);
				txtarea.append("El puerto ya esta en uso.\n");
				
			}catch(IllegalArgumentException e3) {
				txtarea.setFont(new Font("Arial", Font.PLAIN, 13));
				txtarea.setForeground(Color.red);
				txtarea.append("Puerto invalido,reingrese porfavor.\n");
				cerrar.setEnabled(false);
				escuchar.setEnabled(true);
				campoPuerto.setText("");
				campoPuerto.setEnabled(true);
			}catch(SocketException e3) {
				
			}catch(IOException e3) {
				
			}catch (Exception e) {
				
			}
	    	
	    	  
		}

		
	}

