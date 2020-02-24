package DAO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;

import clases.Mensaje;
import conexion.conectar;

public class MensajeDaoImpl extends conectar implements MensajeDAO {

	@Override
	public void nuevoMensaje(Mensaje msj) throws Exception {
		// TODO Apéndice de método generado automáticamente
		conectado();
		PreparedStatement st = con.prepareStatement("Insert into Mensajes(asunto, texto, eliminarEmisor, eliminarReceptor, fecha, idUsuarioEmisor, idUsuarioReceptor) value (?, ?, ?, ?, ?, ?, ?)");
		st.setString(1, msj.getAsunto());
		st.setString(2, msj.getTexto());
		st.setInt(3, msj.getEliminadoE());
		st.setInt(4, msj.getEliminadoR());
		Date fecha = Date.valueOf(msj.getFecha());
		st.setDate(5, fecha);
		st.setInt(6, msj.getIdEmisor());
		st.setInt(7, msj.getIdReceptor());
		st.executeUpdate();
		cerrar();

	}

	@Override
	public void eliminarMensaje(int id) throws Exception {
		// TODO Apéndice de método generado automáticamente
		conectado();
		PreparedStatement eli = con.prepareStatement("Select * From mensajes where idMensaje = "+id+"");
		ResultSet rt = eli.executeQuery();
		while(rt.next())
		{	
			if(rt.getInt("eliminarEmisor") == 0)
			{
				PreparedStatement st = con.prepareStatement("Update Mensajes set eliminarEmisor = ? where idMensaje = ?");
				st.setInt(1, 1);
				st.setInt(2, id);
				st.executeUpdate();
			}
			else
			{
				PreparedStatement st = con.prepareStatement("Update Mensajes set eliminarEmisor = ? where idMensaje = ?");
				st.setInt(1, 2);
				st.setInt(2, id);
				st.executeUpdate();
			}
		}
		rt.close();
		cerrar();

	}

	@Override
	public ArrayList<Mensaje> MsjEnviados(int id) throws Exception {
		// TODO Apéndice de método generado automáticamente
		ArrayList<Mensaje> mensajesEnv = new ArrayList<>();
		conectado();
		PreparedStatement st = con.prepareStatement("Select * From Mensajes Where idUsuarioEmisor = ? and eliminarEmisor = 0");
		st.setInt(1, id);
		ResultSet rt = st.executeQuery();
		while(rt.next())
		{
			int idM = rt.getInt("idMensaje");
			String asunto = rt.getString("Asunto");
			String texto = rt.getString("texto");
			int eliminarEmisor = rt.getInt("eliminarEmisor"); 
			int eliminarReceptor = rt.getInt("eliminarReceptor");
			int idUsuarioEmisor = rt.getInt("idUsuarioEmisor"); 
			int idUsuarioReceptor = rt.getInt("idUsuarioReceptor");
			LocalDate fecha = rt.getDate("fecha").toLocalDate();
			
			Mensaje msj = new Mensaje(asunto, texto, eliminarEmisor, eliminarReceptor, idUsuarioEmisor, idUsuarioReceptor, fecha);
			
			msj.setEmisor(corroUser(idUsuarioEmisor));
			msj.setReceptor(corroUser(idUsuarioReceptor));
			msj.setId(idM);
			mensajesEnv.add(msj);
		}
		cerrar();
		return mensajesEnv;
	}

	@Override
	public ArrayList<Mensaje> MsjRecibidos(int id) throws Exception {
		// TODO Apéndice de método generado automáticamente
		ArrayList<Mensaje> mensajesRec = new ArrayList<>();
		conectado();
		PreparedStatement st = con.prepareStatement("Select * From Mensajes Where idUsuarioReceptor = ? and eliminarReceptor = 0");
		st.setInt(1, id);
		ResultSet rt = st.executeQuery();
		while(rt.next())
		{
			int idM = rt.getInt("idMensaje");
			String asunto = rt.getString("Asunto");
			String texto = rt.getString("texto");
			int eliminarEmisor = rt.getInt("eliminarEmisor"); 
			int eliminarReceptor = rt.getInt("eliminarReceptor");
			int idUsuarioEmisor = rt.getInt("idUsuarioEmisor"); 
			int idUsuarioReceptor = rt.getInt("idUsuarioReceptor");
			LocalDate fecha = rt.getDate("fecha").toLocalDate();
			
			Mensaje msj = new Mensaje(asunto, texto, eliminarEmisor, eliminarReceptor, idUsuarioEmisor, idUsuarioReceptor, fecha);
			
			msj.setEmisor(corroUser(idUsuarioEmisor));
			msj.setReceptor(corroUser(idUsuarioReceptor));
			msj.setId(idM);
			mensajesRec.add(msj);
		}
		cerrar();
		return mensajesRec;
	}

	@Override
	public ArrayList<Mensaje> MsjEliminados(int id) throws Exception {
		// TODO Apéndice de método generado automáticamente
		ArrayList<Mensaje> mensajesEli = new ArrayList<>();
		conectado();
		PreparedStatement st = con.prepareStatement("Select * From Mensajes Where (idUsuarioReceptor = ? and eliminarReceptor = 1) or (idUsuarioEmisor = ? and eliminarEmisor = 1)");
		st.setInt(1, id);
		st.setInt(2, id);
		ResultSet rt = st.executeQuery();
		while(rt.next())
		{
			int idM = rt.getInt("idMensaje");
			String asunto = rt.getString("Asunto");
			String texto = rt.getString("texto");
			int eliminarEmisor = rt.getInt("eliminarEmisor");
			int eliminarReceptor = rt.getInt("eliminarReceptor");
			int idUsuarioEmisor = rt.getInt("idUsuarioEmisor"); 
			int idUsuarioReceptor = rt.getInt("idUsuarioReceptor");
			LocalDate fecha = rt.getDate("fecha").toLocalDate();
			
			Mensaje msj = new Mensaje(asunto, texto, eliminarEmisor, eliminarReceptor, idUsuarioEmisor, idUsuarioReceptor, fecha);
			
			msj.setEmisor(corroUser(idUsuarioEmisor));
			msj.setReceptor(corroUser(idUsuarioReceptor));
			msj.setId(idM);
			
			mensajesEli.add(msj);
		}
		cerrar();
		return mensajesEli;
	}

	public String corroUser(int id) throws Exception
	{
		String correo = null;
		conectado();
		PreparedStatement st = con.prepareStatement("Select * From Usuarios where idUsuario = ?");
		st.setInt(1, id);
		ResultSet rt = st.executeQuery();
		while(rt.next())
		{
			correo = rt.getString("correo");
		}
		cerrar();
		return correo;
	}

	@Override
	public ArrayList<Mensaje> Mensajes(int id) throws Exception {
		// TODO Apéndice de método generado automáticamente
		ArrayList<Mensaje> mensajes = new ArrayList<>();
		conectado();
		PreparedStatement st = con.prepareStatement("Select * From Mensajes Where idUsuarioReceptor = ? or idUsuarioEmisor = ?");
		st.setInt(1, id);
		st.setInt(2, id);
		ResultSet rt = st.executeQuery();
		while(rt.next())
		{
			int idM = rt.getInt("idMensaje");
			String asunto = rt.getString("Asunto");
			String texto = rt.getString("texto");
			int eliminarEmisor = rt.getInt("eliminarEmisor");
			int eliminarReceptor = rt.getInt("eliminarReceptor");
			int idUsuarioEmisor = rt.getInt("idUsuarioEmisor"); 
			int idUsuarioReceptor = rt.getInt("idUsuarioReceptor");
			LocalDate fecha = rt.getDate("fecha").toLocalDate();
			
			Mensaje msj = new Mensaje(asunto, texto, eliminarEmisor, eliminarReceptor, idUsuarioEmisor, idUsuarioReceptor, fecha);
			
			msj.setEmisor(corroUser(idUsuarioEmisor));
			msj.setReceptor(corroUser(idUsuarioReceptor));
			msj.setId(idM);
			
			mensajes.add(msj);
		}
		cerrar();
		return mensajes;
	}
}
