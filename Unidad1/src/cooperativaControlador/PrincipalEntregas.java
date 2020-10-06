package cooperativaControlador;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

import cooperativaModelo.FicheroEntregas;
import cooperativaModelo.FicheroFrutas;
import cooperativaModelo.FicheroSocios;
import cooperativaModelo.Frutas;
import cooperativaModelo.Socio;

public class PrincipalEntregas {

	private static Scanner t = new java.util.Scanner(System.in); 
	private static FicheroEntregas fe = new FicheroEntregas("entregas.txt");

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int opcion = 0;
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		
		do {
			System.out.println("Introduce una opción");
			System.out.println("0-Salir");
			System.out.println("1-Registrar Entrega");
			System.out.println("2-Mostrar Entregas");
			System.out.println("3-Modificar precio");
			System.out.println("3-Borrar Entrega!!!!!");
			
			
			opcion = t.nextInt();t.nextLine();
			Frutas f;
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
