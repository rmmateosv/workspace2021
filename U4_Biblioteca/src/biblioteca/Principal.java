package biblioteca;

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
				System.out.println("1-Crear Socio");
				System.out.println("2-Crear Libro");
				System.out.println("3-Modificar Ejemplares");
				System.out.println("4-Crear préstamo");
				System.out.println("5-Devolver préstamo");
				System.out.println("6-Mostrar socio");
				System.out.println("7-Mostrar número préstamos pendientes de un socio");
				System.out.println("8-Mostrar número préstamos pendientes de devolución");
				System.out.println("9-Mostrar el número de libros y el número de ejemplares totales de la biblioteca");
				System.out.println("10-Borrar libro");
				System.out.println("11-Borrar socio");
				
				opcion = t.nextInt();t.nextLine();
				int codigo;
				
				switch(opcion){
					case 1:
						
						break;
					
					
				}
			}while(opcion!=0);
			bd.cerrar();
		}
		else {
			System.out.println("No hay conexión con la BD");
		}
	}

}
