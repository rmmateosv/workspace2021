package Alumnos;

import java.util.ArrayList;
import java.util.Scanner;

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
				System.out.println("1-Mostrar alumnos");
				System.out.println("2-Mostrar profesores");
				System.out.println("3-Insertar alumno");
				System.out.println("4-Mostrar personas");
				System.out.println("5-Modificar la dirección de un alumno");
				System.out.println("6-Borrar alumno");
				
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
					
				}
			}while(opcion!=0);
			bd.cerrar();
		}
		else {
			System.out.println("No hay conexión con la BD");
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
		
	}

	private static void insertarAlumno() {
		// TODO Auto-generated method stub
		
	}

	private static void mostrarPersonas() {
		// TODO Auto-generated method stub
		
	}

	private static void modificarDirAlumno() {
		// TODO Auto-generated method stub
		
	}

	private static void borrarAlumno() {
		// TODO Auto-generated method stub
		
	}
}
