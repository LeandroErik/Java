package clases;

import java.io.*;
import java.util.Calendar;


public class archivoTextoPlano {
	
	Calendar calendario=Calendar.getInstance();
	String ddhhmmss=calendario.get(Calendar.DAY_OF_MONTH)+"-"+calendario.get(Calendar.MONTH)+"-"+calendario.get(Calendar.YEAR)+" "+calendario.get(Calendar.HOUR)+":"+calendario.get(Calendar.MINUTE)+":"+calendario.get(Calendar.SECOND)+":"+calendario.get(Calendar.MILLISECOND);
	
	private FileWriter fw ;
	private BufferedWriter bw ;
	public archivoTextoPlano() {
		
	}
	public void escribirArchivo(String mensaje) {
		try {
			fw= new FileWriter("log.txt", true);
			
			bw= new BufferedWriter(fw);
			bw.write("["+ddhhmmss+mensaje);
			
			bw.newLine();
			
			bw.close();
			
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		}
	
	}
		
		
		


