package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conectar {

	private String URL = "jdbc:mysql://localhost:3306/tpla" + "?useSSL=false&useTimezone=true&serverTimezone=UTC";
	private String pass = "admin";
	private String user = "root";
	private String jdbc = "com.mysql.cj.jdbc.Driver";
	
	protected Connection con;
	
	public void conectado() throws Exception
	{
		Class.forName(jdbc);
		con = DriverManager.getConnection(URL, user, pass);
	}
	
	public void cerrar() throws SQLException
	{
		if (con!=null)
		{
			if(!con.isClosed())
			{
				con.close();
			}
		}
	}
}
