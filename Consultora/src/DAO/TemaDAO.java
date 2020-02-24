package DAO;

import java.util.List;

import Clases.Tema;

public interface TemaDAO {
	public List<Tema> buscartema(String palClav) ;
	
	public void crearTema(Tema tema);
	
	public void EliminarTema(Tema tema) ;
	
	public void ModificarTema(Tema tema);
	
	public List<Tema> listar() throws Exception;
}
