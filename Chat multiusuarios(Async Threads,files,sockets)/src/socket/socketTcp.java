package socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;

public class socketTcp {


	protected Socket socket= new Socket();
	
	private DataInputStream recibirDatos;
	private DataOutputStream enviarDatos;
	
	private ObjectInputStream recibirObj;
	private ObjectOutputStream enviarObj;
	
	private String puertolocal="";
	private String puertoremoto="";
	private String DireccionLocal;
	private String DireccionRemota;
	
	public Socket getSocket() {
		return socket;
	}
	public void setSocket(Socket socket) {
		this.socket = socket;
	}
	public boolean seConecto(){
		return socket.isConnected();
	}
	public boolean seDesconecto(){
		return socket.isClosed();
	}
	
	public void Enviar(String msj) throws IOException
	{
		enviarDatos = new DataOutputStream(socket.getOutputStream());
		enviarDatos.writeUTF(msj);
		enviarDatos.flush();
	}
	public String Recibir() throws SocketException,EOFException,Exception
	{
		recibirDatos = new DataInputStream(socket.getInputStream());
		String recibido = recibirDatos.readUTF();
		return recibido; 
	}
	public void EnviarObj(Object obj) throws Exception
	{
		enviarObj = new ObjectOutputStream(socket.getOutputStream());
		enviarObj.writeObject(obj);
		enviarObj.flush();
	}
	public Object RecibirObj() throws IOException, ClassNotFoundException
	{
		recibirObj = new ObjectInputStream(socket.getInputStream());
		Object  recibido = recibirObj.readObject();
		return recibido;
	}
	public void CerrarIO() throws IOException
	{
		recibirDatos.close();
		enviarDatos.close();
		recibirObj.close();
		enviarObj.close();
	}
	
	public int getPuertolocal() {
		return socket.getLocalPort();
	}
	public void setPuertolocal(String puertolocal) {
		this.puertolocal = puertolocal;
	}
	public int getPuertoremoto() {
		return socket.getPort();
	}
	public void setPuertoremoto(String puertoremoto) {
		this.puertoremoto = puertoremoto;
	}
	public InetAddress getDireccionLocal() {
		return socket.getLocalAddress();
	}
	public void setDireccionLocal(String direccionLocal) {
		DireccionLocal = direccionLocal;
	}
	public InetAddress getDireccionRemota() {
		return socket.getInetAddress();
	}
	public void setDireccionRemota(String direccionRemota) {
		DireccionRemota = direccionRemota;
	}
}
