package DAO;

import clases.cliente;

public interface UsuarioDAO {

	public void RegistrarUser(cliente user) throws Exception;
	
	public void ModificarUser(cliente user) throws Exception;
	
	public boolean ValidarUser(cliente user) throws Exception;
	
	public void EliminarUser(cliente user) throws Exception;
}
