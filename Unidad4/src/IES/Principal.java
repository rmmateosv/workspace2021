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
				System.out.println("6-Mostrar Aprobados de asignatura");
				System.out.println("7-Mostrar estadística de notas de una asignatura");
				System.out.println("8-Aprobado general");
				System.out.println("9-Borrar alumno");
				
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
					case 5:
						crearNota();
						break;
					case 6:
						mostrarAprobados();
						break;
					case 7:
						mostrarEstadisticaAsig();
						break;
					case 8:
						aprobadoGeneral();
						break;
					case 9:
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

	

	private static void borrarAlumno() {
		// TODO Auto-generated method stub
		mostrarAlumnos();
		System.out.println("Introduce código de alumno");
		int id = t.nextInt();t.nextLine();
		Alumnos a = bd.obtenerAlumno(id);
		if(a!=null) {
			if(!bd.borrarAlumno(a)) {
				System.out.println("Error, no se puede borrar el alumno");
			}
		}
		else {
			System.out.println("Error, alumno no existe");
		}
	}



	private static void aprobadoGeneral() {
		// TODO Auto-generated method stub
		if(!bd.aprobadoGeneral()) {
			System.out.println("Error, no se puede dar aprobado general");
		}
	}



	private static void mostrarEstadisticaAsig() {
		// TODO Auto-generated method stub
		mostrarAsig();
		System.out.println("Inotroduce nombre corto de asignatura");
		String nombre = t.nextLine();
		Asignaturas a = bd.obtenerAsig(nombre);
		if(a!=null) {
			
			ArrayList<Object[]> estadistica = bd.obtenerEstadistica(a);
			for(Object[] o: estadistica) {
				System.out.println("Asignatura:"+  o[0]+
						"\tNºAlumnos:"+ o[1]+
						"\tNotaMáx:"+o[2]+
						"\tNotaMin:"+o[3]+
						"\tNotaMedia:"+o[4]);
			}
		}
		else {
			System.out.println("Error, la asignatura no existe");
		}
	}

	private static void mostrarAprobados() {
		// TODO Auto-generated method stub
		mostrarAsig();
		System.out.println("Inotroduce nombre corto de asignatura");
		String nombre = t.nextLine();
		Asignaturas a = bd.obtenerAsig(nombre);
		if(a!=null) {
			ArrayList<Notas> notas = bd.obtenerAprobados(a);
			for(Notas n : notas) {
				n.mostrar();
			}
		}
		else {
			System.out.println("Error, la asignatura no existe");
		}
	}

	private static void crearNota() {
		// TODO Auto-generated method stub
		mostrarAlumnos();
		Alumnos a;
		Asignaturas as;
		System.out.println("Introduce código alumno");
		int id = t.nextInt();t.nextLine();
		
		a=bd.obtenerAlumno(id);
		if(a!=null) {
			mostrarAsig();
			System.out.println("Introduce nombre corto asignatura");
			String nombrec = t.nextLine();
			as = bd.obtenerAsig(nombrec);
			if(as!=null) {
				Notas n = bd.obtenerNota(a, as);
				if(n==null) {
					System.out.println("Introduce nota");
					n = new Notas();
					n.setId(new ClaveNotas(a, as));
					n.setNota(t.nextInt()); t.nextLine();
					if(!bd.altaNota(n)) {
						System.out.println("Error al crear la nota");
					}
				}
				else {
					System.out.println("Introduce nota");
					int numero = t.nextInt(); t.nextLine();
					if(!bd.modificarNota(n, numero)) {
						System.out.println("Error al modificar la nota");
					}
					
				}
				
			}
			else {
				System.out.println("Error, no existe la asignatura");
			}
		}
		else {
			System.out.println("Error, no existe el alumno");
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
			
			Direccion d = new Direccion();
			System.out.println("Calle");
			d.setCalle(t.nextLine());
			System.out.println("Código Postal");
			d.setCp(t.nextLine());
			d.setAlumno(a);
			a.setDireccion(d);
			
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
