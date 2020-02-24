package conexion;

import java.sql.*;

public class conectar {

	private String URL="jdbc:mysql://localhost/mydb";
	private String pass="admin";
	private String user="root";
	private String jdbc="com.mysql.jdbc.Driver";
	
	protected Connection con;
	
	public void conectado() throws Exception
	{
		Class.forName(jdbc);
		con = DriverManager.getConnection(URL, user,pass);
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
