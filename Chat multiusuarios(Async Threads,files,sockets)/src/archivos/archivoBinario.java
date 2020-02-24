package archivos;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import clases.cliente;


public class archivoBinario {

	private FileOutputStream fos ;
	private ObjectOutputStream oos ;
	
	private FileInputStream fis;
	private ObjectInputStream ois;
	
	private confiCliente config=new confiCliente();

	public void crearArchivo(String archivo) {
		try {
		 fos = new FileOutputStream(archivo+".dat", true);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}	
	
	}

	public void escribirCliente(confiCliente usuario) throws IOException
	{
		fos = new FileOutputStream("cliente_config.dat", false);
		oos = new ObjectOutputStream(fos);
		oos.writeObject(usuario);
		
		oos.close();
		fos.close();
		
	}
	public void escribirServer(int puerto) throws IOException
	{
		fos = new FileOutputStream("server_config.dat", false);
		oos = new ObjectOutputStream(fos);
		oos.writeInt(puerto);
		
		oos.close();
		fos.close();
		
	}
	public confiCliente recibirCliente() throws IOException, ClassNotFoundException,EOFException {
		
			fis = new FileInputStream("cliente_config.dat");
			ois = new ObjectInputStream(fis);
			
			config =(confiCliente) ois.readObject();
			System.out.println("archivo"+config.getPuerto()+config.getIp());
			
		return config;
	}
		
	public int recibirServer() throws IOException {
			fis = new FileInputStream("server_config.dat");
			ois = new ObjectInputStream(fis);
			int puerto =ois.readInt();
			
		return puerto;
	}
	
}
