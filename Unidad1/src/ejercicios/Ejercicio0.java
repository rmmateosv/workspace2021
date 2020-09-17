package ejercicios;

import java.io.File;
import java.util.Scanner;

public class Ejercicio0 {

	private static Scanner t = new java.util.Scanner(System.in); 

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int opcion = 0;
		do {
			System.out.println("Introduce una opción");
			System.out.println("0-Salir");
			System.out.println("1-Ruta absoluta carpeta actual");
			System.out.println("2-Info fichero/carpeta");
			System.out.println("3-Mostrar contenido carpeta");
			System.out.println("4-Crear carpeta");
			System.out.println("5-Crear fichero");
			System.out.println("6-Renombrar fichero");
			
			opcion = t.nextInt();t.nextLine();
			
			switch(opcion){
				case 1:
					ejercicio1();
					break;
				case 2:
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

	private static void ejercicio1() {
		// TODO Auto-generated method stub
		//Mostrar la ruta absoluta de la carpeta en la que está
		//el programa.
		
		//Declaramos objeto File
		//. es la carpeta actual
		File fichero = new File(".");
		
	}

}
