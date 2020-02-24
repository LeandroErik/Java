package conexion;

import java.sql.*;

public class conexion {

	private String URL="jdbc:mysql://localhost/mydb";
	private String pass="admin";
	private String user="root";
	private String jdbc="com.mysql.jdbc.Driver";
	
	public Connection con;
	
	private PreparedStatement DeclaracionPreparada = null;
	
	public Connection conectar() {
		Connection Conexion = null;
		try
        {
            Class.forName(jdbc);
            Conexion = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb","root","admin");
           System.out.println("intentando conectarse");
        }
        
        catch (Exception e)
        {
            e.printStackTrace();
            System.out.println("Remote server could not be connected");
        }    
        
		return Conexion;
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
