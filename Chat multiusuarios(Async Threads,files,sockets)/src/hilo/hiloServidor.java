package hilo;

import java.awt.Color;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.BindException;
import java.net.*;
import java.net.SocketException;
import java.util.ArrayList;
import javax.swing.*;

import archivos.archivoBinario;
import archivos.archivoTextoPlano;
import socket.*;
import clases.*;


public class hiloServidor implements Runnable{
	private socketServer server = new socketServer();
	
	private String nombreCliente;
	private int puerto;
	private ArrayList<sala> salasChat=new ArrayList<>();
	
	private archivoTextoPlano archivo=new archivoTextoPlano();
	private archivoBinario archivoBin=new archivoBinario();
	
	private JButton btnCerrar;
	private JButton btnIniciar;
	private JTextField textPerto;
	
	private Thread hiloCliente ;
	private mensaje msg=new mensaje();
	
	public hiloServidor(socketServer server,int puerto,JButton btnCerrar,JButton btnIniciar,JTextField textPuerto) {
		this.textPerto=textPuerto;
		this.server=server;
		this.puerto=puerto;
		this.btnCerrar=btnCerrar;
		this.btnIniciar=btnIniciar;
		
	}
	public void run() {
	    	  try {
	    		  //Servidor iniciado
				server.Escuchar(puerto);
				
				btnIniciar.setEnabled(false);
				btnCerrar.setEnabled(true);
				textPerto.setEnabled(false);
				
				System.out.println("Escuchando");
				archivo.escribirArchivo("/Server]: Servidor iniciado");
				
				archivoBin.escribirServer(puerto);
				
				while(true) {
					//Acepta a calquier cliente que se conecte
					server.Aceptar();
					
					btnCerrar.setEnabled(true);
					/*Inicio del hilo que pertenece al cliente el cual escuchara los emnsajes que se envien */
					
					hiloCliente =new Thread(new hiloEscucha(server.getSocket(),salasChat));
					hiloCliente.start();
					
                    archivo.escribirArchivo("/Server]: Nuevo cliente aceptado ("+nombreCliente+").");
					
				}
				
			}catch (NumberFormatException e1) {
				archivo.escribirArchivo("/ERROR-Server]: Excepcion catcheada : NumberFormatException");
				btnIniciar.setForeground(Color.RED);
				}
			catch (BindException e2) {
				archivo.escribirArchivo("/ERROR-Server]: Error al iniciar servidor.");
				btnIniciar.setForeground(Color.RED);
			}catch(IllegalArgumentException e3) {
				archivo.escribirArchivo("/ERROR-Server]: Puerto invalido.");
				btnIniciar.setForeground(Color.RED);
			}catch(SocketException e3) {
				archivo.escribirArchivo("/ERROR-Server]:Socket Cerrado.");
				
			}catch(IOException e3) {
				archivo.escribirArchivo("/ERROR-Server]: Excepcion catcheada : "+e3.getMessage());
				
			}catch (Exception e) {
	    		  archivo.escribirArchivo("/ERROR-Server]: Excepcion catcheada : "+e.getMessage());
	    		  
			}
	    	
	    	  
		}

		
	}

