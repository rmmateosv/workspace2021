package ejercicios;

import java.io.File;
import java.util.Scanner;



public class Ejercicio2 {
	private static Scanner t = new java.util.Scanner(System.in); 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		System.out.println("Introduce carpeta");
		File carpeta = new File(t.nextLine());
		
		if (carpeta.exists() && carpeta.isDirectory()) {
			mostrar(carpeta);
		
		}
		else {
			System.out.println("Error: No existe o no es carpeta");
		}
	}
	private static void mostrar(File carpeta) {
		// TODO Auto-generated method stub
		// Muestro el nombre de la carpeta
		System.out.println(carpeta.getName());
		
		//Accedemos al contenido
		File[] contenido = carpeta.listFiles();
		for(int i=0;i<contenido.length;i++) {
			
			if(contenido[i].isDirectory()) {
				mostrar(contenido[i]);
			}
			else {
				System.out.println(contenido[i].getName());
			}
		}
	}

}
