package clases;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class archivoBinario {

	private cliente cliente=new cliente();
	private FileOutputStream fos ;
	private ObjectOutputStream oos ;
	
	private FileInputStream fis;
	private ObjectInputStream ois;

	public void crearArchivo(String archivo) {
		try {
		 fos = new FileOutputStream(archivo+".dat", true);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}	
	
	}

	public void iniciarArchivo(String nombre) throws IOException {
		
		fos = new FileOutputStream(nombre+".dat", false);
		oos = new ObjectOutputStream(fos);
		
	}
	public void escribirCliente(String ip,int puerto) throws IOException
	{
		cliente=new cliente(ip,puerto);
		oos.writeObject(cliente);
		
	}
	public void escribirServer(int puerto) throws IOException
	{
		oos.writeInt(puerto);
		
	}
	public cliente recibirCliente() throws IOException, ClassNotFoundException,EOFException {
			cliente user=new cliente();
	
			fis = new FileInputStream("binary_Cliente.dat");
			ois = new ObjectInputStream(fis);
			
			user =(cliente) ois.readObject();
			
		return user;
	}
		
	public int recibirServer() throws IOException {
			fis = new FileInputStream("binary_Server.dat");
			ois = new ObjectInputStream(fis);
			int puerto =ois.readInt();
			
		return puerto;
	}
	
	public void cerrarArchivo() {
		try {
			oos.close();
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
