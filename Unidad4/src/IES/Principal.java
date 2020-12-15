package IES;

import java.util.ArrayList;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class Principal {
	
	private static Scanner t = new java.util.Scanner(System.in); 
	private static Modelo bd = new Modelo();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if(bd.getConexion()!=null) {
			int opcion = 0;						
			do {
				System.out.println("Introduce una opción");
				System.out.println("0-Salir");
				System.out.println("1-Insertar alumno");
				System.out.println("2-Mostrar alumnos");
				System.out.println("3-Insertar Asignatura");
				System.out.println("4-Mostrar Asignaturas");
				System.out.println("5-Crear Nota");
				System.out.println("6-Mostrar Notas");
				
				opcion = t.nextInt();t.nextLine();
				int codigo;
				
				switch(opcion){
					case 1:
						altaAlumno();
						break;
					case 2:
						mostrarAlumnos();
						break;
					case 3:
						crearAsig();
						break;
					case 4:
						mostrarAsig();
						break;
				}
			}while(opcion!=0);
			bd.cerrar();
		}
		else {
			System.out.println("No hay conexión con la BD");
		}
	}

	private static void mostrarAsig() {
		// TODO Auto-generated method stub
		ArrayList<Asignaturas> asig= bd.obtenerAsig();
		for(Asignaturas a:asig) {
			a.mostrar();
		}
	}

	private static void crearAsig() {
		// TODO Auto-generated method stub
		Asignaturas a;
		System.out.println("Introduce nombre corto");
		String nombrec = t.nextLine();
		a=bd.obtenerAsig(nombrec);
		if(a==null) {
			a = new Asignaturas();
			a.setNombreC(nombrec);
			System.out.println("Introduce nombre asignatura");
			a.setNombreL(t.nextLine());
			if(!bd.altaAsig(a)) {
				System.out.println("Error al crear la asignatura");
			}
		}
		else {
			System.out.println("Error, ya existe una asignatura con ese código");
		}
	}

	private static void mostrarAlumnos() {
		// TODO Auto-generated method stub
		ArrayList<Alumnos> alumnos= bd.obtenerAlumnos();
		for(Alumnos a:alumnos) {
			a.mostrar();
		}
	}

	private static void altaAlumno() {
		// TODO Auto-generated method stub
		Alumnos a;
		System.out.println("NIF");
		String nif = t.nextLine();
		a = bd.obtenerAlumno(nif);
		if(a==null) {
			a= new Alumnos();
			a.setNif(nif);
			System.out.println("Nombre");
			a.setNombre(t.nextLine());
			System.out.println("Curso");
			a.setCurso(t.nextLine());
			if(!bd.altaAlumno(a)) {
				System.out.println("Error al crear el alumno");
			}
			else {
				System.out.println("Se ha creado el alumno con id:" + a.getId());
			}
		}
		else {
			System.out.println("Error: Ya existe un alumno con ese dni");
		}
	}

}
