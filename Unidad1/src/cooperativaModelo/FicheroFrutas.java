package cooperativaModelo;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

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
				fichero = new  MiObjectOutputStream(
						new FileOutputStream(nombre,true));
			}
			else {
				//Abrimos el fichero para añadir regsitros
				fichero = new ObjectOutputStream(new FileOutputStream(nombre,true));
			}
			
			//Añadimos la fruta
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

	public ArrayList<Frutas> obtenerFrutas() {
		// TODO Auto-generated method stub
		ArrayList<Frutas> resultado = new ArrayList<>();
		ObjectInputStream fichero = null;
		try {
			fichero = new ObjectInputStream(new FileInputStream(nombre));
			while(true) {
				Frutas f = (Frutas)fichero.readObject();
				resultado.add(f);
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
		return resultado;
	}

	public Frutas obtenerFruta(int codigo) {
		// TODO Auto-generated method stub
		Frutas resultado = null;
		
		ObjectInputStream fichero = null;
		try {
			fichero = new ObjectInputStream(new FileInputStream(nombre));
			while(true) {
				Frutas f = (Frutas)fichero.readObject();
				if(codigo==f.getCodigo()) {
					resultado = f;
					return resultado;
				}
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
		
		return resultado;
	}

	public boolean modificarFruta(Frutas f) {
		// TODO Auto-generated method stub
		boolean resultado=false;
		
		ObjectInputStream fOriginal = null;
		ObjectOutputStream fTemporal = null;
		
		try {
			fOriginal = new ObjectInputStream(new FileInputStream(nombre));
			fTemporal = new ObjectOutputStream(new FileOutputStream("frutas.tmp",false));
			while(true) {
				Frutas fr = (Frutas)fOriginal.readObject();
				if(fr.getCodigo()==f.getCodigo()) {
					fTemporal.writeObject(f);
					resultado = true;
				}
				else {
					fTemporal.writeObject(fr);
				}
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
			if(fOriginal!=null) {
				try {
					fOriginal.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}if(fTemporal!=null) {
				try {
					fTemporal.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		File original = new File(nombre);
		if(original.exists()) {
			if(!original.delete()) {
				System.out.println("error, no se ha borrado el fichero original");
			}
		}
		File temporal = new File("frutas.tmp");
		if(temporal.exists()) {
			if(!temporal.renameTo(original)) {
				System.out.println("erro, no se puede renombrar el fichero temporal");
			}
		}
		return resultado;
	}
	
	
}
