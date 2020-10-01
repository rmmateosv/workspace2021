package cooperativaModelo;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class FicheroFrutas {
	private String nombre;

	public FicheroFrutas(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int obtenerCodigo() {
		// TODO Auto-generated method stub
		int resultado = 0;
		
		File f = new File(nombre);
		if(f.exists()) {
			
			ObjectInputStream fichero = null;
			
			try {
				fichero=new ObjectInputStream(new FileInputStream(nombre));
				while(true) {
					Frutas fr = (Frutas) fichero.readObject();
					resultado = fr.getCodigo();
				}
			} 
			catch (EOFException e) {
				// TODO: handle exception
			}
			catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally {
				if(fichero!=null) {
					try {
						fichero.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		return resultado+1;
	}

	public boolean regristrarFruta(Frutas f) {
		// TODO Auto-generated method stub
		boolean resultado =false;
		ObjectOutputStream fichero = null;
		try {
			//comprobamos si existe el fichero
			File fich = new File(nombre);
			if(fich.exists()) {
				
			}
			else {
				//Abrimos el fichero para a�adir regsitros
				fichero = new ObjectOutputStream(new FileOutputStream(nombre, true));
			}
			
			//A�adimos la fruta
			fichero.writeObject(f);
			resultado = true;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			if(fichero!=null) {
				try {
					fichero.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return resultado;
	}
	
	
}
