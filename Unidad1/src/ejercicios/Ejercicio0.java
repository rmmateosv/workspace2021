package ejercicios;

import java.io.File;
import java.io.IOException;
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
					ejercicio4();
					break;
				case 5:
					ejercicio5();
					break;
				case 6:
					ejercicio6();
					break;
			}
			
		}while(opcion!=0);
	}
	private static void ejercicio6() {
		// TODO Auto-generated method stub
		System.out.println("Introduce el nombre del fichero a renombrar");
		String nombre = t.nextLine();
		System.out.println("Introduce el nuevo nombre del fichero");
		String nuevo = t.nextLine();
		
		//Creamos dos objetos File
		File original = new File(nombre);
		File destino = new File(nuevo);
		
		//Comprobamos que existen
		if(original.exists()) {
			
			if(destino.exists()) {
				System.out.println("Error: El fichero destino ya existe");
			}
			else {
				//Renombramos
				if(!original.renameTo(destino)) {
					System.out.println("Error al renombrar");
				}
			}
		}
		else {
			System.out.println("No existe el fichero a renombrar");
		}
	}
	private static void ejercicio5() {
		// TODO Auto-generated method stub
		//Pedir el nombre de la carpeta a crear
		System.out.println("Introduce nombre fichero");
		String nombre = t.nextLine();
		
		//Creamos el objeto FILE a ese fichero
		File fichero = new File(nombre);
		//Coprobamos si existe
		if(fichero.exists()) {
			System.out.println("Error: el fichero ya existe");
		}
		else {
			try {
				if(!fichero.createNewFile()) {
					System.out.println("Error: No se ha podido crear");
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("Error de E/S, no se ha podido crear el fichero");
			}
		}
	}
	private static void ejercicio4() {
		// TODO Auto-generated method stub
		//Pedir el nombre de la carpeta a crear
		System.out.println("Introduce nombre carpeta");
		String nombre = t.nextLine();
		
		//Creamos el objeto FILE a esa carpeta
		File carpeta = new File(nombre);
		//Coprobamos si existe
		if(carpeta.exists()) {
			System.out.println("Error: La carpeta ya existe");
		}
		else {
			if(!carpeta.mkdir()) {
				System.out.println("Error: No se ha podido crear");
			}
		}
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
					//Nombre del contenido
					System.out.print(contenido[i].getName());
					//Si carpeta d, si no -
					if(contenido[i].isDirectory()) {
						System.out.print("\td");
					}
					else {
						System.out.print("\t-");
					}
					//Si es de lectura r, si no -
					if(contenido[i].canRead()) {
						System.out.print("\tr");
					}
					else {
						System.out.print("\t-");
					}
					//Si es de escritura w, si no -
					if(contenido[i].canWrite()) {
						System.out.print("w");
					}
					else {
						System.out.print("-");
					}
					//Si es de ejecucuón x, si no -
					if(contenido[i].canExecute()) {
						System.out.println("x");
					}
					else {
						System.out.println("-");
					}
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
