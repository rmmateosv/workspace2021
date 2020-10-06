package cooperativaModelo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

//ESTRUCTURA DEL FICHERO DE ENTREGAS
//Codigo int => 4B
//NIF Socio String de 9 caracteres=> 18B
//Codigo Fruta int => 4B
//Fecha long => 8B
//Kilos float =>4B
//Importe float => 4B
//TAMAÑO DE CADA ENTREGA => 42B
public class FicheroEntregas {
	private String nombre;

	public FicheroEntregas(String nombre) {
		super();
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public boolean crearEntrega(Entregas e) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		
		
		//Declaramos fichero de acceso aleatorio
		RandomAccessFile fichero = null;
		
		//Abrimos el fichero para leer y escribir
		try {
			
			fichero = new RandomAccessFile(nombre, "rw");
			
			//Obtenemos el código de la última entrega
			//Paso 1: Nos movemos al final del fichero
			//Nos ponemos al principio del último registro si hay registros
			if(fichero.length()==0) {
				e.setCodigo(1);
			}
			else {
				fichero.seek(fichero.length()-42);
				//Leemos el código del último registro
				int codigo = fichero.readInt();
				e.setCodigo(codigo+1);
			}
					
			//Nos ponemos al final del fichero para guardar la nueva entrega
			fichero.seek(fichero.length());
			fichero.writeInt(e.getCodigo());
			//Hacemos que el dni tenga longitud fija de 9
			StringBuffer dni = new StringBuffer(e.getSocio().getNif());
			dni.setLength(9);
			fichero.writeChars(dni.toString());
			fichero.writeInt(e.getFruta().getCodigo());
			fichero.writeLong(e.getFecha().getTime());
			fichero.writeFloat(e.getKilos());
			fichero.writeFloat(e.getPrecio());
			resultado=true;	
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			System.out.println("Fichero aún no existe");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		finally {
			if(fichero!=null) {
				try {
					fichero.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		return resultado;
	}
	
	
}
