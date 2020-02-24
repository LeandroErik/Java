package clases;

import java.time.LocalDate;

public class Mensaje {

	private int id;
	private String asunto;
	private String texto;
	private int eliminadoE;
	private int eliminadoR;
	private int idEmisor;
	private int idReceptor;
	private String emisor;
	private String receptor;
	private LocalDate fecha;
	
	public Mensaje(String asunto, String texto, int eliminadoE, int eliminadoR, int idEmisor, int idReceptor, LocalDate fecha) {
		super();
		this.asunto = asunto;
		this.texto = texto;
		this.eliminadoE = eliminadoE;
		this.eliminadoR = eliminadoR;
		this.idEmisor = idEmisor;
		this.idReceptor = idReceptor;
		this.fecha = fecha;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAsunto() {
		return asunto;
	}

	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public int getEliminadoE() {
		return eliminadoE;
	}

	public void setEliminadoE(int eliminadoE) {
		this.eliminadoE = eliminadoE;
	}

	public int getEliminadoR() {
		return eliminadoR;
	}

	public void setEliminadoR(int eliminadoR) {
		this.eliminadoR = eliminadoR;
	}

	public int getIdEmisor() {
		return idEmisor;
	}

	public void setIdEmisor(int idEmisor) {
		this.idEmisor = idEmisor;
	}

	public int getIdReceptor() {
		return idReceptor;
	}

	public void setIdReceptor(int idReceptor) {
		this.idReceptor = idReceptor;
	}

	public String getEmisor() {
		return emisor;
	}

	public void setEmisor(String emisor) {
		this.emisor = emisor;
	}

	public String getReceptor() {
		return receptor;
	}

	public void setReceptor(String receptor) {
		this.receptor = receptor;
	}
}
