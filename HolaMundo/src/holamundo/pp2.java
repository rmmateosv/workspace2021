package holamundo;

import java.util.Scanner;

public class pp2 {
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
			System.out.println("5-Salir");
			System.out.println("6-Salir");
			
			opcion = t.nextInt();t.nextLine();
		}while(opcion!=0);
	}

}
