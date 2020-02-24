package clases;

import java.io.Serializable;

public class cliente implements Serializable {

	String nombre;
	 String ipServer;
	int puertoServer;
	public cliente() {
	
	}
	public cliente(String ipLocal, int puertoLocal) {
		this.ipServer = ipLocal;
		this.puertoServer = puertoLocal;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getIpLocal() {
		return ipServer;
	}
	public void setIpLocal(String ipLocal) {
		this.ipServer = ipLocal;
	}
	public int getPuertoLocal() {
		return puertoServer;
	}
	public void setPuertoLocal(int  puertoLocal) {
		this.puertoServer = puertoLocal;
	}

	
}
