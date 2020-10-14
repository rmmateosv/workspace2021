package cooperativaModelo;

import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Date;

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

		// Declaramos fichero de acceso aleatorio
		RandomAccessFile fichero = null;

		// Abrimos el fichero para leer y escribir
		try {

			fichero = new RandomAccessFile(nombre, "rw");

			// Obtenemos el código de la última entrega
			// Paso 1: Nos movemos al final del fichero
			// Nos ponemos al principio del último registro si hay registros
			if (fichero.length() == 0) {
				e.setCodigo(1);
			} else {
				fichero.seek(fichero.length() - 42);
				// Leemos el código del último registro
				int codigo = fichero.readInt();
				e.setCodigo(codigo + 1);
			}

			// Nos ponemos al final del fichero para guardar la nueva entrega
			fichero.seek(fichero.length());
			fichero.writeInt(e.getCodigo());
			// Hacemos que el dni tenga longitud fija de 9
			StringBuffer dni = new StringBuffer(e.getSocio().getNif());
			dni.setLength(9);
			fichero.writeChars(dni.toString());
			fichero.writeInt(e.getFruta().getCodigo());
			fichero.writeLong(e.getFecha().getTime());
			fichero.writeFloat(e.getKilos());
			fichero.writeFloat(e.getPrecio());
			resultado = true;
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			System.out.println("Fichero aún no existe");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally {
			if (fichero != null) {
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

	public ArrayList<Entregas> obtenerEntregas() {
		// TODO Auto-generated method stub
		ArrayList<Entregas> resultado = new ArrayList<Entregas>();

		RandomAccessFile fichero = null;

		try {
			fichero = new RandomAccessFile(nombre, "r");
			while (true) {
				Entregas e = new Entregas();
				e.setCodigo(fichero.readInt());
				// Nif Socio
				e.setSocio(new Socio());
				String nif = "";
				for (int i = 0; i < 9; i++) {
					nif += fichero.readChar();
				}
				e.getSocio().setNif(nif.trim());
				// Código Fruta
				e.setFruta(new Frutas());
				e.getFruta().setCodigo(fichero.readInt());
				// Fecha
				e.setFecha(new Date(fichero.readLong()));
				// Kilos
				e.setKilos(fichero.readFloat());
				// Importe
				e.setPrecio(fichero.readFloat());
				resultado.add(e);

			}
		} catch (EOFException e) {
			// TODO: handle exception
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally {
			if (fichero != null) {
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

	public Entregas obtenerEntrega(int codigo) {
		// TODO Auto-generated method stub
		Entregas resultado = null;

		RandomAccessFile fichero = null;

		try {
			fichero = new RandomAccessFile(nombre, "r");
			while (true) {
				int codigoF = fichero.readInt();
				if (codigoF == codigo) {
					resultado = new Entregas();
					resultado.setCodigo(codigoF);
					resultado.setSocio(new Socio());

					// Leemos los 18B del nif
					byte[] texto = new byte[18];
					fichero.read(texto, 0, 18);
					resultado.getSocio().setNif(new String(texto));

					resultado.setFruta(new Frutas());
					resultado.getFruta().setCodigo(fichero.readInt());

					resultado.setFecha(new Date(fichero.readLong()));

					resultado.setKilos(fichero.readFloat());
					resultado.setPrecio(fichero.readFloat());

					return resultado;

				} else {
					// saltamos al siguiente registro, para ello
					// avanzamos 38B => 42-4 que ya he leído del código
					fichero.seek(fichero.getFilePointer() + 38);
				}
			}
		} catch (EOFException e) {
			// TODO: handle exception
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (fichero != null) {
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

	public boolean modificarEntrega(Entregas entrega) {
		// TODO Auto-generated method stub
		boolean resultado = false;

		RandomAccessFile fichero = null;

		try {
			fichero = new RandomAccessFile(nombre, "rw");
			while (true) {
				int codigoF = fichero.readInt();
				if (codigoF == entrega.getCodigo()) {
					// Nos desplazamos hasta el precio
					// tenemos que avanzar desde donde estamos
					// 34B => 18+4+8+4
					fichero.seek(fichero.getFilePointer() + 34);
					// Escribimos el nuevo precio
					fichero.writeFloat(entrega.getPrecio());
					resultado = true;
					return resultado;

				} else {
					// saltamos al siguiente registro, para ello
					// avanzamos 38B => 42-4 que ya he leído del código
					fichero.seek(fichero.getFilePointer() + 38);
				}
			}
		} catch (EOFException e) {
			// TODO: handle exception
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (fichero != null) {
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

	public boolean borrarEntrega(Entregas entrega) {
		// TODO Auto-generated method stub
		boolean resultado = false;

		RandomAccessFile fOriginal = null;
		DataOutputStream fTemporal= null;

		try {
			fOriginal = new RandomAccessFile(nombre, "r");
			fTemporal = new DataOutputStream(new FileOutputStream("entregas.tmp",false));
			while (true) {
				int codigoF = fOriginal.readInt();
				if (codigoF == entrega.getCodigo()) {
					// Nos desplazamos hasta el siguiente registro sin escribir nada 
					// en el temporañ
					fOriginal.seek(fOriginal.getFilePointer() + 38);
					resultado = true;

				} else {
					//Leemos el resto del registro y pasamos todo al temporal
					fTemporal.writeInt(codigoF);
					byte[] resto = new byte[38];
					fOriginal.read(resto, 0, 38);
					fTemporal.write(resto);
				}
			}
		} catch (EOFException e) {
			// TODO: handle exception
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (fOriginal != null) {
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
		
		File fO = new File(nombre);
		if(fO.exists()) {
			if(!fO.delete()) {
				System.out.println("Error al borrar el fichero original");
			}
		}
		File fT = new File("entregas.tmp");
		if(fT.exists()) {
			if(!fT.renameTo(fO)) {
				System.out.println("Error al renombrar el fichero original");
			}
		}
		return resultado;
	}

	public ArrayList<Entregas> obtenerEntregas(String pNif) {
		// TODO Auto-generated method stub
		ArrayList<Entregas> resultado = new ArrayList();
		RandomAccessFile fichero = null;

		try {
			fichero = new RandomAccessFile(nombre, "r");
			while (true) {
				Entregas e = new Entregas();
				e.setCodigo(fichero.readInt());
				// Nif Socio
				e.setSocio(new Socio());
				String nif = "";
				for (int i = 0; i < 9; i++) {
					nif += fichero.readChar();
				}
				e.getSocio().setNif(nif.trim());
				if(e.getSocio().getNif().equals(pNif)) {
					// Código Fruta
					e.setFruta(new Frutas());
					e.getFruta().setCodigo(fichero.readInt());
					// Fecha
					e.setFecha(new Date(fichero.readLong()));
					// Kilos
					e.setKilos(fichero.readFloat());
					// Importe
					e.setPrecio(fichero.readFloat());
					resultado.add(e);
				}
				else {
					fichero.seek(fichero.getFilePointer()+20);
				}

			}
		} catch (EOFException e) {
			// TODO: handle exception
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally {
			if (fichero != null) {
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

	public void generarXML(Socio s, ArrayList<Entregas> lE) {
		// TODO Auto-generated method stub
		
	}

}
