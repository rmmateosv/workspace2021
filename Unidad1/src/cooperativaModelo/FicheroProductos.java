package cooperativaModelo;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FicheroProductos {
	private String nombre;

	public FicheroProductos(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int obtenerNuevoCodigo() {
		// TODO Auto-generated method stub
		int resultado=0;
		//Contamos el n� de l�neas del fichero
		
		//Declaramos fichero para lectura
		DataInputStream fichero = null;
		
		try {
			//Abrimos
			fichero = new DataInputStream(new FileInputStream(nombre));
			
			//Recorremos el fichero contando las l�neas. Hay que leer toda la l�nea
			//ya que son ficheros de acceso secuencial
			//Hacemos un bucle infinito. El fin de fichero se detecta
			//porque se produce la excepci�n EOFException
			while(true) {
				//Leemos el c�digo
				fichero.readInt();
				
			}
		}
		
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (EOFException e) {
			// TODO: handle exception
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return resultado;
	}
	
	
}
