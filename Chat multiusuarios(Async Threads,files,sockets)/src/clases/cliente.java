package clases;

import java.io.Serializable;
import java.util.ArrayList;

public class cliente{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nombre;
	private String password;
	private ArrayList<sala> salasChat=new ArrayList<>();
	
	public cliente() {
		
	}
	public cliente(String nombre, String password) {
		this.nombre = nombre;
		this.setPassword(password);
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public ArrayList<sala> getSalasChat() {
		return salasChat;
	}
	public void setSalasChat(ArrayList<sala> salasChat) {
		this.salasChat = salasChat;
	}

	
}
