package archivos;

import java.io.Serializable;

public class confiCliente implements Serializable {
	String ip;
	int puerto;
	public confiCliente() {
	}
	
	public confiCliente(String ip, int puerto) {
		this.ip = ip;
		this.puerto = puerto;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getPuerto() {
		return puerto;
	}

	public void setPuerto(int puerto) {
		this.puerto = puerto;
	}

}
