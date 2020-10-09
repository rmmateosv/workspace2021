package cooperativaControlador;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;


import cooperativaModelo.FicheroProveedores;





public class PrincipalProveedores {

	private static Scanner t = new java.util.Scanner(System.in); 
	private static FicheroProveedores fp = 
			new FicheroProveedores("proveedores.xml");
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int opcion = 0;
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		
		do {
			System.out.println("Introduce una opción");
			System.out.println("0-Salir");
			System.out.println("1-Alta Proveedor");
			System.out.println("2-Mostrar Proveedores");
			System.out.println("3-Modificar fecha último pedido");
			System.out.println("3-Borrar Proveedores "
					+ "(crea fichero con los que están de baja)!!!!!");
			
			
			opcion = t.nextInt();t.nextLine();
			
			
			switch(opcion){
				case 1:
					
					break;
				case 2:
					fp.mostrarProveedores();					
					break;
				
			}
			
		}while(opcion!=0);
	}

}
