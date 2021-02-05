package Alumnos;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class Principal {
	
	private static Scanner t = new java.util.Scanner(System.in); 
	private static Modelo bd = new Modelo();
	private static SimpleDateFormat df = new SimpleDateFormat("ddMMyyyy");
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if(bd.getConexion()!=null) {
			int opcion = 0;						
			do {
				System.out.println("Introduce una opción");
				System.out.println("0-Salir");
				System.out.println("1-Mostrar alumnos");
				System.out.println("2-Mostrar profesores");
				System.out.println("3-Insertar alumno");
				System.out.println("4-Mostrar personas");
				System.out.println("5-Modificar la dirección de un alumno");
				System.out.println("6-Borrar alumno");
				System.out.println("7-Mostrar las notas de todos los alumnos");
				System.out.println("8-Mostrar las notas de una asignatura");
				System.out.println("9-Poner nota");
				
				opcion = t.nextInt();t.nextLine();
				int codigo;
				
				switch(opcion){
					case 1:
						mostrarAlumnos();
						break;
					case 2:
						mostrarProfesor();
						break;
					case 3:
						insertarAlumno();
						break;
					case 4:
						mostrarPersonas();
						break;
					case 5:
						modificarDirAlumno();
						break;
					case 6:
						borrarAlumno();
						break;
					case 7:
						mostrarNotas();
						break;
					case 8:
						mostrarNotasAsig();
						break;
					
				}
			}while(opcion!=0);
			bd.cerrar();
		}
		else {
			System.out.println("No hay conexión con la BD");
		}


}

	private static void mostrarNotasAsig() {
		// TODO Auto-generated method stub
		ArrayList<Asig> asigs = bd.obtenerAsigs();
		for(Asig a:asigs) {
			a.mostrar();
		}
		System.out.println("Introduce código");
		String codigo = t.nextLine();
		//Devuelve de asigs, la asignatura cuyo código coincide con códio. Si no hay ninguno, devuelve null
		Asig a = asigs.stream().filter(as->as.getCodigo().equalsIgnoreCase(codigo)).findAny().orElse(null);
		if(a!=null) {
			ArrayList<Nota> notas = bd.obtenerNotasAsig(a);
			for(Nota n: notas) {
				n.mostrar();
			}
		}
		else {
			System.out.println("Error, no existe asignatura");
		}
		
	}

	private static void mostrarNotas() {
		// TODO Auto-generated method stub
		ArrayList<Nota> notas = bd.obtenerNotas();
		for(Nota n: notas) {
			n.mostrar();
		}
	}

	private static void mostrarAlumnos() {
		// TODO Auto-generated method stub
		ArrayList<Alumno> alumnos = bd.obtenerAlumnos();
		for(Alumno a:alumnos) {
			a.mostrar();
		}
	}

	private static void mostrarProfesor() {
		// TODO Auto-generated method stub
		ArrayList<Profesor> profesores = bd.obtenerProfes();
		for(Profesor p:profesores) {
			p.mostrar();
		}
	}

	private static void insertarAlumno() {
		
		// TODO Auto-generated method stub
		Alumno a = new Alumno();
		System.out.println("Nombre:");
		a.setNombre(t.nextLine());
		System.out.println("Tipo Via:");
		a.setDireccion(new Direccion());
		a.getDireccion().setTipoV(t.nextLine());
		System.out.println("Nombre Via:");
		a.getDireccion().setNombreV(t.nextLine());
		System.out.println("Número:");
		a.getDireccion().setNumero(t.nextInt());t.nextLine();
		System.out.println("CP:");
		a.getDireccion().setCp(t.nextInt());t.nextLine();
		System.out.println("Fecha de matrícula (ddMMyyyy):");		
		try {
			a.setFechaM(df.parse(t.nextLine()));
			if(!bd.insertarAlumno(a)) {
				System.out.println("Error al crear el alumno");
			}
			else {
				mostrarAlumnos();
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private static void mostrarPersonas() {
		// TODO Auto-generated method stub
		
	}

	private static void modificarDirAlumno() {
		// TODO Auto-generated method stub
		mostrarAlumnos();
		System.out.println("Introduce código");
		int codigo = t.nextInt(); t.nextLine();
		Alumno a  = bd.obtenerAlumno(codigo);
		if(a!=null) {
			System.out.println("Nuevo tipo de calle");
			a.getDireccion().setTipoV(t.nextLine());
			System.out.println("Nuevo nombre de calle");
			a.getDireccion().setNombreV(t.nextLine());
			System.out.println("Nuevo número");
			a.getDireccion().setNumero(t.nextInt());t.nextLine();
			System.out.println("Nuevo cp");
			a.getDireccion().setCp(t.nextInt());t.nextLine();
			if(!bd.modficarDireccion(a)) {
				System.out.println("Error al modificar la dirección");
			}
		}
		else {
			System.out.println("Error, alumno no existe");
		}
	}

	private static void borrarAlumno() {
		// TODO Auto-generated method stub
		mostrarAlumnos();
		System.out.println("Introduce código");
		int codigo = t.nextInt(); t.nextLine();
		Alumno a  = bd.obtenerAlumno(codigo);
		if(a!=null) {
			if(!bd.borrarAlumno(a)) {
				System.out.println("Error al borrar el alumnos");
			}
		}
		else {
			System.out.println("Error, alumno no existe");
		}
	}
}
