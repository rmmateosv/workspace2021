package Cooperativa;

import java.util.ArrayList;
import java.util.Scanner;



public class Principal {

	private static Scanner t = new java.util.Scanner(System.in); 
	private static Modelo bd = new Modelo();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int opcion = 0;
		if(bd.getConexion()!=null) {
			do {
				System.out.println("Introduce una opción");
				System.out.println("0-Salir");
				System.out.println("1-Mostrar Socios");			
				System.out.println("2-Mostrar Socios por nombre/parte del nombre");
				System.out.println("3-Mostrar Socios por nombre/parte del nombre");
				
				opcion = t.nextInt(); t.nextLine();	
				ArrayList<Socio> socios;
				switch(opcion){
					case 1:
						socios = bd.obtenerSocios();
						for(Socio s:socios) {
							s.mostrar();
						}
						break;
					case 2:
						System.out.println("Introduce nombre/parte del nombre");
						String patron = t.nextLine();
						socios = bd.obtenerSocios(patron);
						for(Socio s:socios) {
							s.mostrar();
						}
						break;
					case 3:
						
						break;
					case 4:
										
						break;
					
				}
				
			}while(opcion!=0);
			//Cerramos la conexión con la BD.
			bd.cerrar();
		}
		else {
			System.out.println("No se ha podido conectar con la BD");
		}
	}

}
