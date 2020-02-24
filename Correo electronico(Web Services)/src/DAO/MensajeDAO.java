package DAO;

import java.util.ArrayList;

import clases.Mensaje;

public interface MensajeDAO {

	public void nuevoMensaje(Mensaje msj) throws Exception;
	
	public void eliminarMensaje(int id) throws Exception;
	
	public ArrayList<Mensaje> MsjEnviados(int id) throws Exception;
	
	public ArrayList<Mensaje> MsjRecibidos(int id) throws Exception;
	
	public ArrayList<Mensaje> MsjEliminados(int id) throws Exception;
	
	public ArrayList<Mensaje> Mensajes(int id) throws Exception;
}
