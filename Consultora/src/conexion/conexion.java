package conexion;

import java.sql.*;

public class conexion {
	private String JDK="com.mysql.jdbc.Driver";
	private String URL="jdbc:mysql://localhost/zenda";
	private String user="root"; 
	private String pass="admin";
	protected Connection con;
	
	public void conectar() 
	{
		try
		{
			Class.forName(JDK);
			con = DriverManager.getConnection(URL,user,pass);
		}
		catch (Exception e) {
			
		}
	}
	public void cerrar() throws Exception
	{
		if(con!=null)
		{
			if(!con.isClosed())
			{
				con.close();
			}
		}
	}
}
