package cooperativaModelo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class FicheroSocios {
	String nombre;

	// Formato de fecha
	SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

	public FicheroSocios(String nombre) {
		super();
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void crearSocio(Socio s) {

		BufferedWriter fichero = null;

		try {
			// PASO 1: ABRIR FICHERO
			// Declarmos el fichero para escribir en él
			// Abrimos el fichero con el nombre y el atributo
			// append a true, para que se añada información y no
			// se borre el fichero cada vez que se abre
			fichero = new BufferedWriter(new FileWriter(nombre, true));

			// PASO 2: ESCRIBIR LOS DATOS DEL SOCIO
			// SEPARADOS POR ;
			fichero.write(s.getNif() + ";");
			fichero.write(s.getNombre() + ";");
			fichero.write(formato.format(s.getFechAlta()) + ";");
			fichero.write(s.getSaldo() + ";");
			fichero.write(s.isBaja() + "");
			// Marcamos el fin de línea
			fichero.newLine();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Error: No se puede abrir el fichero " + "para crear un socio");
			e.printStackTrace();
		} finally {
			// PASO 3: CERRAMOS EL FICHERO
			try {
				if (fichero != null)
					fichero.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public ArrayList<Socio> obtenerSocios() {

		ArrayList<Socio> resultado = new ArrayList<>();

		BufferedReader fichero = null;

		try {
			// PASO1: ABRIR EL FICHERO DE SOCIOS PARA LECTURA
			// Declarmos un objeto de la clase BufferedReader
			fichero = new BufferedReader(new FileReader(nombre));

			// PASO2: RECORRO EL FICHERO LEYENDO LÍNEA A LÍNEA HASTA
			// LLEGAR AL FINAL. EL FINAL SE DETECTA CUANDO LEEMOS NULL
			String linea;
			while ((linea = fichero.readLine()) != null) {
				String[] campos = linea.split(";");

				Socio s = new Socio();
				s.setNif(campos[0]);
				s.setNombre(campos[1]);
				s.setFechAlta(formato.parse(campos[2]));
				s.setSaldo(Float.parseFloat(campos[3]));
				s.setBaja(Boolean.parseBoolean(campos[4]));
				resultado.add(s);
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Error: No existe el fichero");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Error: No se puede leer del fichero");
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			System.out.println("Error: fecha incorrecta");
		} finally {
			// PASO 3: CERRAR FICHERO
			try {
				if (fichero != null)
					fichero.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return resultado;
	}

	public void modificarSaldo(Socio s, float importe) {
		// El socio que se pasa por parámetro tiene el
		// DNI del socio al que se va a sumar el importe

		BufferedReader fOriginal = null;
		BufferedWriter fTemporal = null;

		try {
			// PASO 1: Abrir ficheros. El fichero socios.txt
			// se abre para lectura. Se crea un fichero socios.tmp
			// para realizar la modificación.
			fOriginal = new BufferedReader(new FileReader(nombre));
			// El fichero temporal, siempre se sobreescribe (append=false)
			fTemporal = new BufferedWriter(new FileWriter("socios.tmp", false));

			// PASO 2: Leemos la información del fichero original
			// y la pasamos al fichero temporal. Solamente modificamos
			// el saldo del socio que se pasa por parámetro
			String linea;
			while ((linea = fOriginal.readLine()) != null) {
				// Divido la línea en campos para acceder a los datos del socio
				String[] campos = linea.split(";");
				// Comprobamos si el DNI es el del socio que hay que modificar
				if (campos[0].equals(s.getNif())) {
					// Modificamos el saldo
					s.setSaldo(s.getSaldo() + importe);

					// Escribir los datos del socio con el saldo modificado
					fTemporal.write(campos[0] + ";");
					fTemporal.write(campos[1] + ";");
					fTemporal.write(campos[2] + ";");
					fTemporal.write(s.getSaldo() + ";");
					fTemporal.write(campos[4] + "\n");
				} else {
					// Escribimos en el fichero en el fichero temporal
					// linea, tal cual se ha leído
					fTemporal.write(linea);
					fTemporal.newLine();
				}
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// PASO 3: CERRAR FICHEROS Y RENOMBRAR
			try {
				if (fOriginal != null)
					fOriginal.close();
				if (fTemporal != null)
					fTemporal.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// Creamos objetos FILE a los ficheros para poder borrar y renombrar
			File original = new File(nombre);
			File temporal = new File("socios.tmp");

			// Comprobamos que existe y borramos socios.txt
			if (original.exists()) {
				if (!original.delete()) {
					System.out.println("Error: No se puede borrar el ficheros socios.txt");
				}
			}
			// Comprobamos que existe y renombramos socios.tmp
			if (temporal.exists()) {
				if (!temporal.renameTo(original)) {
					System.out.println("Error: No se pude renombrar temporal a original");
				}
			}
		}
	}

	public Socio obtenerSocio(String dni) {
		// TODO Auto-generated method stub
		Socio resultado = null;

		// Fichero para lectura
		BufferedReader fichero = null;
		try {
			fichero = new BufferedReader(new FileReader(nombre));

			// Leemos línea a línea hasta encontrar el socio buscado
			String linea;
			while ((linea = fichero.readLine()) != null) {
				// Dividimos la línea en campos
				String[] campos = linea.split(";");

				// Comprobamos si el dni es el buscado
				if (campos[0].equals(dni)) {
					// Creamos objeto resultado y rellenamos sus datos
					resultado = new Socio();
					resultado.setNif(campos[0]);
					resultado.setNombre(campos[1]);
					resultado.setFechAlta(formato.parse(campos[2]));
					resultado.setSaldo(Float.parseFloat(campos[3]));
					resultado.setBaja(Boolean.parseBoolean(campos[4]));
					return resultado;
				}

			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
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

	public void borrarSocio(Socio s) {
		// TODO Auto-generated method stub
		// El socio que se pasa por parámetro tiene el
		// DNI del socio que se va a borrar

		BufferedReader fOriginal = null;
		BufferedWriter fTemporal = null;

		try {
			// PASO 1: Abrir ficheros. El fichero socios.txt
			// se abre para lectura. Se crea un fichero socios.tmp
			// para realizar la modificación.
			fOriginal = new BufferedReader(new FileReader(nombre));
			// El fichero temporal, siempre se sobreescribe (append=false)
			fTemporal = new BufferedWriter(new FileWriter("socios.tmp", false));

			// PASO 2: Leemos la información del fichero original
			// y la pasamos al fichero temporal, todo excepto el socio a borrar
			String linea;
			while ((linea = fOriginal.readLine()) != null) {
				// Divido la línea en campos para acceder a los datos del socio
				String[] campos = linea.split(";");
				// Comprobamos si el DNI es el del socio que hay que borrar
				if (!campos[0].equals(s.getNif())) {
					// Escribimos en el fichero en el fichero temporal
					// linea, tal cual se ha leído
					fTemporal.write(linea);
					fTemporal.newLine();
				}
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// PASO 3: CERRAR FICHEROS Y RENOMBRAR
			try {
				if (fOriginal != null)
					fOriginal.close();
				if (fTemporal != null)
					fTemporal.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// Creamos objetos FILE a los ficheros para poder borrar y renombrar
			File original = new File(nombre);
			File temporal = new File("socios.tmp");

			// Comprobamos que existe y borramos socios.txt
			if (original.exists()) {
				if (!original.delete()) {
					System.out.println("Error: No se puede borrar el ficheros socios.txt");
				}
			}
			// Comprobamos que existe y renombramos socios.tmp
			if (temporal.exists()) {
				if (!temporal.renameTo(original)) {
					System.out.println("Error: No se pude renombrar temporal a original");
				}
			}
		}
	}

	public void bajaSocio(Socio s) {
		// TODO Auto-generated method stub
		
		BufferedReader fOriginal = null;
		BufferedWriter fTemporal = null;

		try {
			// PASO 1: Abrir ficheros. El fichero socios.txt
			// se abre para lectura. Se crea un fichero socios.tmp
			// para realizar la modificación.
			fOriginal = new BufferedReader(new FileReader(nombre));
			// El fichero temporal, siempre se sobreescribe (append=false)
			fTemporal = new BufferedWriter(new FileWriter("socios.tmp", false));

			// PASO 2: Leemos la información del fichero original
			// y la pasamos al fichero temporal. Solamente modificamos
			// el saldo del socio que se pasa por parámetro
			String linea;
			while ((linea = fOriginal.readLine()) != null) {
				// Divido la línea en campos para acceder a los datos del socio
				String[] campos = linea.split(";");
				// Comprobamos si el DNI es el del socio que hay que modificar
				if (campos[0].equals(s.getNif())) {
					// Escribir los datos del socio con baja=true
					fTemporal.write(campos[0] + ";");
					fTemporal.write(campos[1] + ";");
					fTemporal.write(campos[2] + ";");
					fTemporal.write(campos[3] + ";");
					fTemporal.write(Boolean.toString(true) + "\n");
				} else {
					// Escribimos en el fichero en el fichero temporal
					// linea, tal cual se ha leído
					fTemporal.write(linea);
					fTemporal.newLine();
				}
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// PASO 3: CERRAR FICHEROS Y RENOMBRAR
			try {
				if (fOriginal != null)
					fOriginal.close();
				if (fTemporal != null)
					fTemporal.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// Creamos objetos FILE a los ficheros para poder borrar y renombrar
			File original = new File(nombre);
			File temporal = new File("socios.tmp");

			// Comprobamos que existe y borramos socios.txt
			if (original.exists()) {
				if (!original.delete()) {
					System.out.println("Error: No se puede borrar el ficheros socios.txt");
				}
			}
			// Comprobamos que existe y renombramos socios.tmp
			if (temporal.exists()) {
				if (!temporal.renameTo(original)) {
					System.out.println("Error: No se pude renombrar temporal a original");
				}
			}
		}
	}
}
