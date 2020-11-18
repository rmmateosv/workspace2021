package ColeccionVentas;


import java.util.Scanner;



public class Principal {
	
	private static Scanner t = new java.util.Scanner(System.in); 
	private static Modelo bd = new Modelo();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if(bd.getColeccion()!=null) {
			int opcion = 0;
			
			
			do {
				System.out.println("Introduce una opción");
				System.out.println("0-Salir");
				System.out.println("1-Mostrar Productos");
				System.out.println("2-Crear Productos");
				System.out.println("3-Modificar Productos");
				System.out.println("4-Borrar Productos");
				System.out.println("5-Mostrar facturas de un cliente");
				System.out.println("6-Mostrar Producto");
				
				opcion = t.nextInt();t.nextLine();
				
				switch(opcion){
					case 1:
						bd.mostrarProductos();
						break;
					case 6:
						System.out.println("Introduce código de producto");
						bd.mostrarProducto(t.nextInt());t.nextLine();
						break;
				}
			}while(opcion!=0);
			bd.cerrar();
		}
		else {
			System.out.println("Error: No se ha conectado con la bd");
		}
	}

}
