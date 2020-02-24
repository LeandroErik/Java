package Clases;

import java.util.ArrayList;

public class Seguimiento {
	private int id;
	private ArrayList<Medio> medio=new ArrayList<>();
	private Operador operador;
	public int getId() {
		return id; 
	}
	public void setId(int id) {
		this.id = id;
	}
	public ArrayList<Medio> getMedio() {
		return medio;
	}
	public void setMedio(ArrayList<Medio> medio) {
		this.medio = medio;
	}
	public Operador getOperador() {
		return operador;
	}
	public void setOperador(Operador operador) {
		this.operador = operador;
	}
	
}
