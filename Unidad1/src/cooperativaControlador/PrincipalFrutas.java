package cooperativaControlador;

import java.util.ArrayList;
import java.util.Scanner;

import cooperativaModelo.FicheroFrutas;
import cooperativaModelo.FicheroSocios;
import cooperativaModelo.Socio;

public class PrincipalFrutas {

	private static Scanner t = new java.util.Scanner(System.in); 
	private static FicheroFrutas ft = new FicheroFrutas("frutas.txt");

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int opcion = 0;
		
		do {
			System.out.println("Introduce una opción");
			System.out.println("0-Salir");
			System.out.println("1-Registrar Fruta");
			System.out.println("2-Mostrar Frutas");
			System.out.println("3-Modificar Fecha Inicio Temporada");
			System.out.println("5-Borrar Fruta!!!!!!!!");
			
			opcion = t.nextInt();t.nextLine();
			
			switch(opcion){
				case 1:
									
					break;
				case 2:
					
					
					break;
				case 3:
					
					break;
				case 4:
					
				
					break;
				
			}
			
		}while(opcion!=0);
	}

}
