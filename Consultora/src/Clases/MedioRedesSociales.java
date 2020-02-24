package Clases;

public class MedioRedesSociales extends Medio {
	private int cant_publicidadapoyo;
	private int cantidad_pub_rechazo;
	private int cant_pub_neutro;
	private int cant_replicas;
	private int cant_mg;


	@Override
			public boolean trascendencia() {
				int suma=0;
				suma=suma +getCant_publicidadapoyo()+getCantidad_pub_rechazo()+getCant_pub_neutro();
				return getCant_replicas()>15000 && suma>500;
			}


	public int getCant_publicidadapoyo() {
		return cant_publicidadapoyo;
	}


	public void setCant_publicidadapoyo(int cant_publicidadapoyo) {
		this.cant_publicidadapoyo = cant_publicidadapoyo;
	}


	public int getCantidad_pub_rechazo() {
		return cantidad_pub_rechazo;
	}


	public void setCantidad_pub_rechazo(int cantidad_pub_rechazo) {
		this.cantidad_pub_rechazo = cantidad_pub_rechazo;
	}


	public int getCant_pub_neutro() {
		return cant_pub_neutro;
	}


	public void setCant_pub_neutro(int cant_pub_neutro) {
		this.cant_pub_neutro = cant_pub_neutro;
	}


	public int getCant_replicas() {
		return cant_replicas;
	}


	public void setCant_replicas(int cant_replicas) {
		this.cant_replicas = cant_replicas;
	}


	public int getCant_mg() {
		return cant_mg;
	}


	public void setCant_mg(int cant_mg) {
		this.cant_mg = cant_mg;
	}
	
}
