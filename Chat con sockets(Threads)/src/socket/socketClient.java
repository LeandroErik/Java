package socket;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;


public class socketClient extends socketTcp {

	public void Conectar(String IP, int puerto) throws UnknownHostException, IOException
	{
		socket = new Socket(IP, puerto);
		
		
	}
	
	public void CerrarIO() throws IOException
	{
		socket.close();
	}
}
