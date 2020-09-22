package cooperativaControlador;

import java.util.ArrayList;
import java.util.Scanner;

import cooperativaModelo.FicheroSocios;
import cooperativaModelo.Socio;

public class Principal {

	private static Scanner t = new java.util.Scanner(System.in); 
	private static FicheroSocios fs = new FicheroSocios("socios.txt");

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int opcion = 0;
		
		do {
			System.out.println("Introduce una opción");
			System.out.println("0-Salir");
			System.out.println("1-Crear Socio");
			System.out.println("2-Mostrar Socios");
			
			opcion = t.nextInt();t.nextLine();
			Socio s;
			switch(opcion){
				case 1:
					s = new Socio();
					System.out.println("Introduce el NIF");
					s.setNif(t.nextLine());
					System.out.println("Introduce el Nombre");
					s.setNombre(t.nextLine());
					fs.crearSocio(s);					
					break;
				case 2:
					ArrayList<Socio> socios;
					socios = fs.obtenerSocios();
					for(Socio s1:socios) {
						s1.mostrar();
					}
					
					break;
				case 3:
					
					break;
				case 4:
					
					break;
				case 5:
					
					break;
				case 6:
					
					break;
			}
			
		}while(opcion!=0);
	}

}
