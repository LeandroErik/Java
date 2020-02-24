package hilo;

import java.awt.Color;
import java.awt.Font;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.BindException;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;

import javax.swing.*;

import clases.archivoBinario;
import clases.archivoTextoPlano;
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
	
	private archivoTextoPlano archivo=new archivoTextoPlano();
	private archivoBinario archivoBin=new archivoBinario();
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
	    		  try {
	    				archivoBin.iniciarArchivo("binary_Server");
	    			}catch (FileNotFoundException e4) {
	    		archivo.escribirArchivo("/ERROR-Server]: Archivo binary_Server no encontrado");
	    			} catch (IOException e4) {
	    		archivo.escribirArchivo("/ERROR-Server]: Excepcion catcheada "+e4.getMessage());
	    			}
	    		
	    		txtarea.setForeground(Color.black);
	    		 
				server.Escuchar(puerto);
				
				archivo.escribirArchivo("/Server]: Servidor iniciado");
				
				archivoBin.escribirServer(puerto);
				archivoBin.cerrarArchivo();
				
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
							archivo.escribirArchivo("/ERROR-Cliente]: Cliente validado exitosamente.");
						}else {
							txtarea.append("Cliente nombre repetido.\n");
							archivo.escribirArchivo("/ERROR-Cliente]: Cliente repetido.");
						server.Enviar("no");
						}
						
					}while(nomValido==false);
					
					NombresClientes.add(nombreCliente);
					
					
					ConexionesClientes.add(server.getSocket());
					
					new Thread(new hiloConectar(ConexionesClientes, NombresClientes, server.getSocket(), txtarea)).start();
					
                    archivo.escribirArchivo("/Server]: Nuevo cliente aceptado ("+nombreCliente+").");
					
					txtarea.append(nombreCliente+" se ha conectado.\n");
					
				}
				
			}catch (NumberFormatException e1) {
				archivo.escribirArchivo("/ERROR-Server]: Excepcion catcheada : NumberFormatException");
				
				txtarea.setFont(new Font("Arial", Font.PLAIN, 13));
				txtarea.setForeground(Color.red);
				txtarea.append("Reingrese el puerto.\n");
				
				cerrar.setEnabled(false);
				escuchar.setEnabled(true);
				campoPuerto.setText("");
				campoPuerto.setEnabled(true);
				
				}
			catch (BindException e2) {
				archivo.escribirArchivo("/ERROR-Server]: Error al iniciar servidor.");
				txtarea.setText("");
				escuchar.setEnabled(true);
				campoPuerto.setEnabled(true);
				campoPuerto.setText("");
				txtarea.setFont(new Font("Arial", Font.PLAIN, 13));
				txtarea.setForeground(Color.red);
				txtarea.append("El puerto ya esta en uso.\n");
				
			}catch(IllegalArgumentException e3) {
				archivo.escribirArchivo("/ERROR-Server]: Puerto invalido.");
				txtarea.setFont(new Font("Arial", Font.PLAIN, 13));
				txtarea.setForeground(Color.red);
				txtarea.append("Puerto invalido,reingrese porfavor.\n");
				cerrar.setEnabled(false);
				escuchar.setEnabled(true);
				campoPuerto.setText("");
				campoPuerto.setEnabled(true);
			}catch(SocketException e3) {
				archivo.escribirArchivo("/ERROR-Server]:Socket Cerrado.");
				
			}catch(IOException e3) {
				archivo.escribirArchivo("/ERROR-Server]: Excepcion catcheada : "+e3.getMessage());
				
			}catch (Exception e) {
	    		  archivo.escribirArchivo("/ERROR-Server]: Excepcion catcheada : "+e.getMessage());
				
			}
	    	
	    	  
		}

		
	}

