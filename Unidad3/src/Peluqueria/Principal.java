package Peluqueria;

import java.util.ArrayList;
import java.util.Scanner;



	public class Principal {
	private static Scanner t = new java.util.Scanner(System.in); 
	private static Modelo bd = new Modelo();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if(bd.getColeccion()!=null) {
			int opcion = 0;
			
			
			do {
				System.out.println("Introduce una opción");
				System.out.println("0-Salir");
				System.out.println("1-Alta Cita");
				System.out.println("2-Mostrar Citas de una fecha");
				System.out.println("3-Modificar fecha cita");
				System.out.println("4-Borrar cita");
				System.out.println("5-Mostrar citas de un cliente");
				
				opcion = t.nextInt();t.nextLine();
				int codigo;
				switch(opcion){
					case 1:
						altaCita();
						break;
					
				}
			}while(opcion!=0);
			bd.cerrar();
		}
		else {
			System.out.println("Error: No se ha conectado con la bd");
		}
	}

	private static void altaCita() {
		// TODO Auto-generated method stub
		ArrayList<Cliente> clientes  = bd.obtenerClientes();
	}

}
