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
				
				opcion = t.nextInt(); t.nextLine();			
				switch(opcion){
					case 1:
						ArrayList<Socio> socios = bd.obtenerSocios();
						for(Socio s:socios) {
							s.mostrar();
						}
						break;
					case 2:
						
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
