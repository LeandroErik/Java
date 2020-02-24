package DAO;

import clases.Usuario;

public interface UsuarioDAO {

	public boolean Registrar(Usuario user) throws Exception;
	
	public boolean ValidarUser(String correo, String contra) throws Exception;
	
	public void ModificarContra(String contra, int id) throws Exception;
	
	public Usuario darUser(String correo, String contra) throws Exception;
	
	public int darID(String correo) throws  Exception;
}
