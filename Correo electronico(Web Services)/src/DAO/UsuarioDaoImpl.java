package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import clases.Usuario;
import conexion.conectar;

public class UsuarioDaoImpl extends conectar implements UsuarioDAO {

	@Override
	public boolean Registrar(Usuario user) throws Exception {
		// TODO Apéndice de método generado automáticamente
		conectado();
		PreparedStatement val = con.prepareStatement("Select * From usuarios where correo = ?");
		val.setString(1, user.getCorreo());
		ResultSet rt = val.executeQuery();
		if(!rt.next())
		{
			PreparedStatement st = con.prepareStatement("Insert into usuarios(nombre, apellido, correo, contrasenia) value(?, ?, ?, ?)");
			st.setString(1, user.getNombre());
			st.setString(2, user.getApellido());
			st.setString(3, user.getCorreo());
			st.setString(4, user.getContrasenia());
			st.executeUpdate();
			cerrar();
			return true;
		}
		cerrar();
		return false;
		
	}

	@Override
	public boolean ValidarUser(String correo, String contra) throws Exception {
		// TODO Apéndice de método generado automáticamente
		conectado();
		PreparedStatement val = con.prepareStatement("Select * From usuarios where correo = ? and contrasenia = ?");
		val.setString(1, correo);
		val.setString(2, contra);
		ResultSet rt = val.executeQuery();
		if (rt.next())
		{
			cerrar();
			return true;
		}
		cerrar();
		return false;
	}

	@Override
	public void ModificarContra(String contra, int id) throws Exception {
		// TODO Apéndice de método generado automáticamente
		conectado();
		PreparedStatement st = con.prepareStatement("Update usuarios set contrasenia = ? where idUsuario = ?");
		st.setString(1, contra);
		st.setInt(2, id);
		st.executeUpdate();
		cerrar();
	}

	@Override
	public Usuario darUser(String correo, String contra) throws Exception {
		// TODO Apéndice de método generado automáticamente
		conectado();
		Usuario user = null;
		PreparedStatement val = con.prepareStatement("Select * From usuarios where correo = ? and contrasenia = ?");
		val.setString(1, correo);
		val.setString(2, contra);
		ResultSet rt = val.executeQuery();
		while(rt.next())
		{
			String nom = rt.getString("nombre");
			String ape = rt.getString("apellido");
			int id = rt.getInt("idUsuario");
			user = new Usuario(nom, ape, correo, contra);
			user.setId(id);
		}
		return user;
	}

	@Override
	public int darID(String correo) throws Exception {
		// TODO Apéndice de método generado automáticamente
		int id = 0;
		conectado();
		PreparedStatement st = con.prepareStatement("Select * From Usuarios where correo = ?");
		st.setString(1, correo);
		ResultSet rt = st.executeQuery();
		while(rt.next())
		{
			id = rt.getInt("idUsuario");
		}
		return id;
	}

}
