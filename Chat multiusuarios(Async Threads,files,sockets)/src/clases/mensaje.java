package clases;

public class mensaje {
	private int id;
	private String nombreSala;
	private String mensaje;
	
	private String nom_user;
	private String password;
	
	private String passAnterior;
	private String passActual;
	/*1:validar registro
	 *2:reenviar mensaje a la sala
	 *3:Cambiar contraseña 
	 *4:Crear Grupo
	 * */
	public mensaje() {
		
	}
	public mensaje(String nombre, String password,int id) {
		this.id=id;
		this.nom_user=nombre;
		this.password=password;
	}
	
	public mensaje(int id,String nombreSala, String mensaje) {
		this.id=id;
		this.nombreSala = nombreSala;
		this.mensaje = mensaje;
		this.id=id;
	}
	public mensaje(String passAnterior,int id,String passActual) {
		this.id=id;
		this.passAnterior = passAnterior;
		this.passActual = passActual;
		
	}
	public mensaje(int id,String nomGrupo) {
		this.id=id;
		this.nombreSala=nomGrupo;
		
	}
	
	
	public String getNombreSala() {
		return nombreSala;
	}
	public void setNombreSala(String nombreSala) {
		this.nombreSala = nombreSala;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom_user() {
		return nom_user;
	}

	public void setNom_user(String nom_user) {
		this.nom_user = nom_user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassAnterior() {
		return passAnterior;
	}

	public void setPassAnterior(String passAnterior) {
		this.passAnterior = passAnterior;
	}

	public String getPassActual() {
		return passActual;
	}

	public void setPassActual(String passActual) {
		this.passActual = passActual;
	}
	
	

}
