package DAO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Clases.*;
import conexion.conexion;

public class OperadorDAOImpl extends conexion implements OperadorDAO {

	@Override
	public List<Operador> listar() throws Exception {

		ArrayList<Operador> Operadores=new ArrayList<>();
		try
		{
			this.conectar();
			PreparedStatement st = con.prepareStatement("Select* From operador");
			ResultSet rt=st.executeQuery();
			while(rt.next()) 
			{
				Operador op=new Operador();
				op.setId(rt.getInt("id_operador"));
				op.setNombre(rt.getString("nombre_operador"));
				
				Operadores.add(op);
			}
			rt.close();
			st.close();
			cerrar();
		}
		catch (Exception e)
		{
			throw e;
		}
		return Operadores;
	}
	
}
