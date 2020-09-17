package ejercicios;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
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
					ejercicio2();
					break;
				case 3:
					ejercicio3();
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
	private static void ejercicio3() {
		// TODO Auto-generated method stub
		//Pedir nombre fichero o carpeta
		System.out.println("Introduce nombre de fichero o carpeta");
		String nombre = t.nextLine();
		
		//Creamos objeto File para acceder a su información
		File fichero  = new File(nombre);
		
		//comprobamos si existe
		if(fichero.exists()) {
			//Comprobamos si es carpeta o fichero
			if(fichero.isDirectory()) {
				//Accedemos a su cotenido
				File[] contenido= fichero.listFiles();
				//Recorremos contenido para mostra la información
				for(int i=0;i<contenido.length;i++) {
					
				}
			}
			else {
				System.out.println("El nombre corresponde a un fichero");
			}
			
			
		}
		else {
			System.out.println("El nombre no existe");
		}
	}

	private static void ejercicio2() {
		// TODO Auto-generated method stub
		//Pedir nombre fichero o carpeta
		System.out.println("Introduce nombre de fichero o carpeta");
		String nombre = t.nextLine();
		
		//Creamos objeto File para acceder a su información
		File fichero  = new File(nombre);
		
		//comprobamos si existe
		if(fichero.exists()) {
			//Comprobamos si es carpeta o fichero
			if(fichero.isDirectory()) {
				System.out.println("Carpeta");
			}
			else {
				System.out.println("Fichero");
			}
			
			//Mostramos fecha
			//Crear un formato de fecha
			SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
			String fecha = formato.format(new Date(fichero.lastModified()));
			
			System.out.println("Fecha última modificación:"+ fecha);
			
			//Mostramos el tamaño
			System.out.println("Tamaño:"+fichero.length());
		}
		else {
			System.out.println("El nombre no existe");
		}
	}

	private static void ejercicio1() {
		// TODO Auto-generated method stub
		//Mostrar la ruta absoluta de la carpeta en la que está
		//el programa.
		
		//Declaramos objeto File
		//. es la carpeta actual
		File fichero = new File(".");
		//Mostramos la ruta absoluta
		System.out.println("Ruta Absoluta:" + fichero.getAbsolutePath());
		
	}

}
