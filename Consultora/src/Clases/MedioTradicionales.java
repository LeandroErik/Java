package Clases;

public class MedioTradicionales extends Medio {
 
	private int cant_min_PorDia;
	private int cant_HorarioCentral;
	private int cant_TapaDiario;
	private int cant_NoTaDiario;
	
	public boolean trascendencia() {
		
		return cant_TapaDiario>1 && cant_HorarioCentral>1&& cant_min_PorDia>60;
	}

	public int getCant_min_PorDia() {
		return cant_min_PorDia;
	}


	public void setCant_min_PorDia(int cant_min_PorDia) {
		this.cant_min_PorDia = cant_min_PorDia;
	}


	public int getCant_HorarioCentral() {
		return cant_HorarioCentral;
	}


	public void setCant_HorarioCentral(int cant_HorarioCentral) {
		this.cant_HorarioCentral = cant_HorarioCentral;
	}


	public int getCant_TapaDiario() {
		return cant_TapaDiario;
	}


	public void setCant_TapaDiario(int cant_TapaDiario) {
		this.cant_TapaDiario = cant_TapaDiario;
	}


	public int getCant_NoTaDiario() {
		return cant_NoTaDiario;
	}


	public void setCant_NoTaDiario(int cant_NoTaDiario) {
		this.cant_NoTaDiario = cant_NoTaDiario;
	}


	public String getApreciacion() {
		return apreciacion;
	}


	public void setApreciacion(String apreciacion) {
		this.apreciacion = apreciacion;
	}


	private String apreciacion; 
	
	
	
}
