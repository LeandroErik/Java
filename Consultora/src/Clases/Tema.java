package Clases;

import java.time.*;

public class Tema {
	private int id;
	private String palclave;
	private String codAlf;
	private String describcion;
	private LocalDate desde;
	private LocalDate hasta;
	private Seguimiento seg;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPalclave() {
		return palclave;
	}
	public void setPalclave(String palclave) {
		this.palclave = palclave;
	}
	public String getCodAlf() {
		return codAlf;
	}
	public void setCodAlf(String codAlf) {
		this.codAlf = codAlf;
	}
	public String getDescribcion() {
		return describcion;
	}
	public void setDescribcion(String describcion) {
		this.describcion = describcion;
	}
	public LocalDate getDesde() {
		return desde;
	}
	public void setDesde(LocalDate desde) {
		this.desde = desde;
	}
	public LocalDate getHasta() {
		return hasta;
	}
	public void setHasta(LocalDate hasta) {
		this.hasta = hasta;
	}
	public Seguimiento getSeg() {
		return seg;
	}
	public void setSeg(Seguimiento seg) {
		this.seg = seg;
	}
	
	
}
