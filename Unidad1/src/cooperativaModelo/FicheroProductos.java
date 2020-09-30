package cooperativaModelo;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

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
		
		File f = new File(nombre);
		if(f.exists()) {
			//Contamos el nº de líneas del fichero
			
			//Declaramos fichero para lectura
			DataInputStream fichero = null;
			
			try {
				//Abrimos
				fichero = new DataInputStream(new FileInputStream(nombre));
				
				//Recorremos el fichero contando las líneas. Hay que leer toda la línea
				//ya que son ficheros de acceso secuencial
				//Hacemos un bucle infinito. El fin de fichero se detecta
				//porque se produce la excepción EOFException
				while(true) {
					//Leemos el código
					resultado = fichero.readInt(); //Codigo
					fichero.readUTF(); // Nombre
					fichero.readInt(); //stock
					fichero.readFloat(); //Precio
					fichero.readLong(); //Fecha Alta
					fichero.readBoolean(); //Descatalogado				
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
		}
		
		return resultado+1;
	}

	public boolean crearProducto(Producto p) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		
		//Declaramos el fichero de productos para 
		DataOutputStream fichero=null;
		
		
		try {
			//Abrimos para añadir registros append = true!!!!!
			fichero = new DataOutputStream(new FileOutputStream(nombre,true));
			//Escribimos el producto
			fichero.writeInt(p.getCodigo()); //Codigo
			fichero.writeUTF(p.getNombre()); //Nombre
			fichero.writeInt(p.getStock()); //Stock
			fichero.writeFloat(p.getPrecio()); //Precio
			fichero.writeLong(p.getFechaAlta()); //Fecha Alta
			fichero.writeBoolean(p.isDescatalogado()); //Descatalogado
			
			resultado =true;
			
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

	public ArrayList<Producto> obtenerProductos() {
		// TODO Auto-generated method stub
		ArrayList<Producto> resultado = new ArrayList<>();
		
		DataInputStream fichero = null;
		
		try {
			fichero=new DataInputStream(new FileInputStream(nombre));
			while(true) {
				Producto p = new Producto();
				p.setCodigo(fichero.readInt());
				p.setNombre(fichero.readUTF());
				p.setStock(fichero.readInt());
				p.setPrecio(fichero.readFloat());
				p.setFechaAlta(fichero.readLong());
				p.setDescatalogado(fichero.readBoolean());
				resultado.add(p);
				
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (EOFException e) {
			// TODO: handle exception
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

	public boolean modificarStock(Producto p, int cantidad) {
		// TODO Auto-generated method stub
		boolean resultado=false;
		
		DataInputStream fOriginal = null;
		DataOutputStream fTemporal = null;
		
		try {
			fOriginal = new DataInputStream(new FileInputStream(nombre));
			fTemporal = new DataOutputStream(
					new FileOutputStream("productos.tmp",false));
			
			//Recorremos los productos
			while(true) {
				//Leemos producto
				int codigo=fOriginal.readInt();
				if(codigo==p.getCodigo()) {
					fTemporal.writeInt(codigo);
					fTemporal.writeUTF(fOriginal.readUTF());
					fTemporal.writeInt(fOriginal.readInt()+cantidad);//Stock Modificado!!
					fTemporal.writeFloat(fOriginal.readFloat());
					fTemporal.writeLong(fOriginal.readLong());
					fTemporal.writeBoolean(fOriginal.readBoolean());
					resultado = true;
				}
				else {
					fTemporal.writeInt(codigo);
					fTemporal.writeUTF(fOriginal.readUTF());
					fTemporal.writeInt(fOriginal.readInt());
					fTemporal.writeFloat(fOriginal.readFloat());
					fTemporal.writeLong(fOriginal.readLong());
					fTemporal.writeBoolean(fOriginal.readBoolean());
					
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
		}
		finally {
			if(fOriginal!=null) {
				try {
					fOriginal.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(fTemporal!=null) {
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
		File temporal = new File("productos.tmp");
		if(temporal.exists()) {
			if(!temporal.renameTo(original)) {
				System.out.println("erro, no se puede renombrar el fichero temporal");
			}
		}
		
		return resultado;
	}
	
	
}
