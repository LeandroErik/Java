package socket;

import java.io.IOException;
import java.net.ServerSocket;



public class socketServer extends socketTcp {

	private ServerSocket server;
	
	public void Escuchar(int puerto) throws IOException
	{
		
		server = new ServerSocket(puerto);
	
	}
	public void Aceptar() throws IOException {
		
		socket = server.accept();
		
	}
	public void CerrarIO() throws IOException
	{
		socket.close();
		server.close();
	}
}
