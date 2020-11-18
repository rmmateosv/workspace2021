package ColeccionVentas;


import java.util.Scanner;



public class Principal {
	
	private static Scanner t = new java.util.Scanner(System.in); 
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int opcion = 0;
		
		
		do {
			System.out.println("Introduce una opción");
			System.out.println("0-Salir");
			System.out.println("1-Mostrar Clientes");
			System.out.println("2-Crear Cliente");
			System.out.println("3-Modificar Cliente");
			System.out.println("4-Borrar Cliente");
			System.out.println("5-Mostrar facturas de un cliente");
			
			opcion = t.nextInt();t.nextLine();
			
			switch(opcion){
				case 1:
					break;
			}
		}while(opcion!=0);
	}

}
