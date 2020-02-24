package socket;

import java.io.IOException;
import java.net.ServerSocket;

import javax.swing.JTextArea;

public class socketServer extends socketTcp {

	private ServerSocket server;
	
	public void Escuchar(int puerto,JTextArea area) throws IOException
	{
		
		server = new ServerSocket(puerto);
		socket = server.accept();
	}
	
	public void CerrarIO() throws IOException
	{
		socket.close();
		server.close();
	}
}
