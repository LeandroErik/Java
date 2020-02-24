package clases;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

import socket.socketClient;

public class sala {
	
	private String nombreSala;

	private ArrayList<Socket>clientes =new ArrayList<>();
	
	public sala() {

	}
	public sala(String nombreSala,ArrayList<Socket> clientes) {
		this.nombreSala=nombreSala;
		this.clientes=clientes;
	}
	public String getNombreSala() {
		return nombreSala;
	}
	public void setNombreSala(String nombreSala) {
		this.nombreSala = nombreSala;
	}
	public ArrayList<Socket> getClientes() {
		return clientes;
	}
	public void setClientes(ArrayList<Socket> clientes) {
		this.clientes = clientes;
	}
	

}
