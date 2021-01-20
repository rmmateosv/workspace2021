package ligaB;

import java.util.ArrayList;
import java.util.List;
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
				System.out.println("1-Seleccionar Partido");
				System.out.println("2-Registrar Acción");
				System.out.println("3-Anular Acción");
				System.out.println("4-Borrar Partido");
				System.out.println("5-Mostrar Estadística");
				
				opcion = t.nextInt();t.nextLine();
				int codigo;
				
				switch(opcion){
					case 1:
						seleccionarPartido();
						break;
					case 2:
						RegistrarAccion();
						break;
					case 3:
						AnularAccion();
						break;
					case 4:
						BorrarPartido();
						break;
					case 5:
						mostrarEstadistica();
						break;

					
				}
			}while(opcion!=0);
			bd.cerrar();
		}
		else {
			System.out.println("No hay conexión con la BD");
		}
	}

	private static void mostrarEstadistica() {
		// TODO Auto-generated method stub
		
	}

	private static void BorrarPartido() {
		// TODO Auto-generated method stub
		
	}

	private static void AnularAccion() {
		// TODO Auto-generated method stub
		
	}

	private static void RegistrarAccion() {
		// TODO Auto-generated method stub
		
	}

	private static void seleccionarPartido() {
		// TODO Auto-generated method stub
		
	}

}
