package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.time.*;

import Clases.Tema;
import conexion.conexion;

public class TemaDAOImpl extends conexion implements TemaDAO {

	@Override
	public List<Tema> buscartema(String palClav) {
		
		return null;
	}

	@Override
	public void crearTema(Tema tema) {
		conectar();
		try {
			PreparedStatement st = con.prepareStatement("insert into temas(codg, palabra_clave, fecha_inicio, fecha_final, descripcion_tema) value(?, ?, ?, ?, ?)");
			st.setString(1, tema.getCodAlf());
			st.setString(2, tema.getPalclave());
			Date fHasta = Date.valueOf(tema.getHasta());
			st.setDate(3, fHasta);
			Date fDesde = Date.valueOf(tema.getDesde());
			st.setDate(4, fDesde);
			st.setString(5, tema.getDescribcion());
			st.execute();
			try {
				cerrar();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void EliminarTema(Tema tema) {
		conectar();
		try {
			PreparedStatement st = con.prepareStatement("delete into temas(codg, palabra_clave, fecha_inicio, fecha_final, descripcion_tema) value(?, ?, ?, ?, ?)");
			st.setString(1, tema.getCodAlf());
			st.setString(2, tema.getPalclave());
			Date fHasta = Date.valueOf(tema.getHasta());
			st.setDate(3, fHasta);
			Date fDesde = Date.valueOf(tema.getDesde());
			st.setDate(4, fDesde);
			st.setString(5, tema.getDescribcion());
			st.execute();
			try {
				cerrar();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void ModificarTema(Tema tema) {
		
		
	}

	@Override
	public List<Tema> listar() throws Exception {
		ArrayList<Tema> temas=new ArrayList<>();
		try
		{
			this.conectar();
			PreparedStatement st = (PreparedStatement) con.prepareStatement("Select* From temas");
			ResultSet rt=st.executeQuery();
			while(rt.next())
			{
				Tema tem=new Tema();
				tem.setId(rt.getInt("id_temas"));
				tem.setCodAlf(rt.getString("codg"));
				tem.setDescribcion(rt.getString("descripcion_tema"));
				LocalDate Desde = rt.getDate("fecha_inicio").toLocalDate();
				tem.setDesde(Desde);
				LocalDate hasta = rt.getDate("fecha_final").toLocalDate();
				tem.setHasta(hasta);
				temas.add(tem);
			}
			rt.close();
			st.close();
			cerrar();
		}
		catch (Exception e)
		{
			throw e;
		}
		return temas;
	}
	
	
}
