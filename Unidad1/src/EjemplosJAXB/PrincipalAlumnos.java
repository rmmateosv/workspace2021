package EjemplosJAXB;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import cooperativaModelo.FicheroSocios;
import cooperativaModelo.Socio;

public class PrincipalAlumnos {

	private static Scanner t = new java.util.Scanner(System.in); 
	private static String nombre = "alumnos.xml";
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int opcion = 0;
		
		do {
			System.out.println("Introduce una opción");
			System.out.println("0-Salir");
			System.out.println("1-Crear Alumno");
			System.out.println("2-Mostrar alumnos");
			System.out.println("3-Modificar Nombre");
			System.out.println("4-Borrar alumno");
			
			
			opcion = t.nextInt();t.nextLine();
			
			switch(opcion){
				case 1:
					opcion1();				
					break;
				case 2:
					opcion2();
					break;
				case 3:
					opcion3();
					break;
				case 4:
					opcion4();
					break;
				
			}
			
		}while(opcion!=0);
	}

	private static void opcion4() {
		// TODO Auto-generated method stub
		File fichero = new File(nombre);
		if(fichero.exists()) {
			Alumnos xml = unmarshall(fichero);
			for(Alumno a:xml.getLista_alumnos()) {
				a.mostrar();
			}
			Alumno alumno = new Alumno();
			System.out.println("Introduce DNI");
			alumno.setDni(t.nextLine());
						
			for(Alumno a:xml.getLista_alumnos()) {
				if(a.getDni().equalsIgnoreCase(alumno.getDni())) {
					xml.getLista_alumnos().remove(a);					
					marshall(fichero, xml);
					break;
				}
			}
		}
	}

	private static void opcion3() {
		// TODO Auto-generated method stub
		File fichero = new File(nombre);
		if(fichero.exists()) {
			Alumnos xml = unmarshall(fichero);
			for(Alumno a:xml.getLista_alumnos()) {
				a.mostrar();
			}
			Alumno alumno = new Alumno();
			System.out.println("Introduce DNI");
			alumno.setDni(t.nextLine());
			System.out.println("Nuevo nombre");
			alumno.setNombre(t.nextLine());
			
			for(Alumno a:xml.getLista_alumnos()) {
				if(a.getDni().equalsIgnoreCase(alumno.getDni())) {
					a.setNombre(alumno.getNombre());
					marshall(fichero, xml);
					break;
				}
			}
		}
		
	}

	private static void opcion2() {
		// TODO Auto-generated method stub
		File fichero = new File(nombre);
		if(fichero.exists()) {
			Alumnos xml = unmarshall(fichero);
			for(Alumno a:xml.getLista_alumnos()) {
				a.mostrar();
			}
		}
		
	}

	private static void opcion1() {
		// TODO Auto-generated method stub
		File fichero = new File(nombre);
		
		Alumnos xml=null;
		
		Alumno alumno = new Alumno();
		System.out.println("Introduce el dni");
		alumno.setDni(t.nextLine());
		System.out.println("Introduce el nombre");
		alumno.setNombre(t.nextLine());
		
		if(fichero.exists()) {
			xml = unmarshall(fichero);
			xml.getLista_alumnos().add(alumno);
			marshall(fichero,xml);
		}
		else {
			xml = new Alumnos();
			xml.getLista_alumnos().add(alumno);
			marshall(fichero,xml);
		}
	}

	private static void marshall(File fichero, Alumnos xml) {
		// TODO Auto-generated method stub
		try {
			JAXBContext contexto = JAXBContext.newInstance(Alumnos.class);
			Marshaller m = contexto.createMarshaller();
			m.marshal(xml,fichero);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static Alumnos unmarshall(File fichero) {
		// TODO Auto-generated method stub
		Alumnos resultado = null;
		
		try {
			JAXBContext contexto = JAXBContext.newInstance(Alumnos.class);
			Unmarshaller um = contexto.createUnmarshaller();
			resultado = (Alumnos) um.unmarshal(fichero);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return resultado;
	}

}
