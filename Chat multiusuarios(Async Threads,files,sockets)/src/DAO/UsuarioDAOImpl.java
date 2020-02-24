package DAO;

import java.sql.PreparedStatement;

import clases.cliente;
import conexion.conexion;

public class UsuarioDAOImpl extends conexion implements UsuarioDAO {

	@Override
	public void RegistrarUser(cliente user) throws Exception {
		// TODO Apéndice de método generado automáticamente
		conectar();
		PreparedStatement st = con.prepareStatement("Insert into usuarios() Value ()");
		
		st.executeUpdate();
		cerrar();

	}

	@Override
	public void ModificarUser(cliente user) throws Exception {
		// TODO Apéndice de método generado automáticamente
		conectar();
		PreparedStatement st = con.prepareStatement("Update usuarios   where ");
		
		st.executeUpdate();
		cerrar();

	}

	@Override
	public boolean ValidarUser(cliente user) throws Exception {
		// TODO Apéndice de método generado automáticamente
		conectar();
		PreparedStatement st = con.prepareStatement("Select * From usuarios where ");
		
		st.executeUpdate();
		cerrar();
		
		return false;
	}

	@Override
	public void EliminarUser(cliente user) throws Exception {
		// TODO Apéndice de método generado automáticamente
		conectar();
		PreparedStatement st = con.prepareStatement("Delete usuarios where");
		
		st.executeUpdate();
		cerrar();
	}

}