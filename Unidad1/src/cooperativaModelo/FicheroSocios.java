package cooperativaModelo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class FicheroSocios {
	String nombre;
	
	//Formato de fecha
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
		try {
			//PASO 1: ABRIR FICHERO
			//Declarmos el fichero para escribir en él
			//Abrimos el fichero con el nombre y el atributo 
			//append a true, para que se añada información y no 
			//se borre el fichero cada vez que se abre
			BufferedWriter fichero = new BufferedWriter(
					new FileWriter(nombre, true));
			
			//PASO 2: ESCRIBIR LOS DATOS DEL SOCIO
			//SEPARADOS POR ;
			fichero.write(s.getNif()+";");
			fichero.write(s.getNombre()+";");
			fichero.write(formato.format(s.getFechAlta())+";");
			fichero.write(s.getSaldo()+";");
			fichero.write(s.isBaja()+"");
			//Marcamos el fin de línea
			fichero.newLine();
			
			//PASO 3:  CERRAMOS EL FICHERO
			fichero.close();
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Error: No se puede abrir el fichero "
					+ "para crear un socio");
			e.printStackTrace();
		}
	}
	
	public ArrayList<Socio> obtenerSocios() {
		
		ArrayList<Socio> resultado = new ArrayList<>();
		
		
		try {
			//PASO1: ABRIR EL FICHERO DE SOCIOS PARA LECTURA
			//Declarmos un objeto de la clase BufferedReader
			BufferedReader fichero = new BufferedReader(
					new FileReader(nombre));
			
			//PASO2: RECORRO EL FICHERO LEYENDO LÍNEA A LÍNEA HASTA
			//LLEGAR AL FINAL. EL FINAL SE DETECTA CUANDO LEEMOS NULL
			String linea;
			while((linea=fichero.readLine())!=null) {
				String[] campos = linea.split(";");
				
				Socio s = new Socio();
				s.setNif(campos[0]);
				s.setNombre(campos[1]);
				s.setFechAlta(formato.parse(campos[2]));
				s.setSaldo(Float.parseFloat(campos[3]));
				s.setBaja(Boolean.parseBoolean(campos[4]));
				resultado.add(s);
			}
			
			//PASO 3: CERRAR FICHERO
			fichero.close();
			
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
		}
		
		return resultado;
	}
}
