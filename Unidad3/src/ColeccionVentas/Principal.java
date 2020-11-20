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
				int codigo;
				switch(opcion){
					case 1:
						bd.mostrarProductos();
						break;
					case 6:
						System.out.println("Introduce código de producto");
						bd.mostrarProducto(t.nextInt());t.nextLine();
						break;
					case 2:
						System.out.println("Categoría");
						String cat = t.nextLine();
						System.out.println("Precio");
						float precio = t.nextFloat(); t.nextLine();
						System.out.println("Nombre");
						String nombre = t.nextLine();
						System.out.println("Stock");
						int stock = t.nextInt(); t.nextLine();
						if(!bd.insertarProducto(cat,precio,nombre,stock)) {
							System.out.println("Error al insertar el producto");
						}
						break;
					case 3:
						bd.mostrarProductos();
						System.out.println("Introduce código producto a modificar");
						codigo = t.nextInt();t.nextLine();
						int stockActual = bd.obtenerStock(codigo);
						if(stockActual!=-1) {
							System.out.println("Introduce cantidad a incrementar");
							int cantidad = t.nextInt();t.nextLine();
							if(!bd.modificarStock(codigo,stockActual+cantidad)) {
								System.out.println("Error al modificar el producto");
							}
						}
						else {
							System.out.println("Error, el producto no existe");
						}
						break;
					case 4:
						bd.mostrarProductos();
						System.out.println("Introduce código producto a modificar");
						codigo = t.nextInt();t.nextLine();						
						if(bd.existe(codigo)) {							
							if(!bd.borrarProducto(codigo)) {
								System.out.println("Error al borrar el producto");
							}
						}
						else {
							System.out.println("Error, el producto no existe");
						}
						break;
					case 5:
						System.out.println("Introduce código cliente");
						codigo = t.nextInt(); t.nextLine();
						bd.mostrarFacturas(codigo);
				}
			}while(opcion!=0);
			bd.cerrar();
		}
		else {
			System.out.println("Error: No se ha conectado con la bd");
		}
	}

}
