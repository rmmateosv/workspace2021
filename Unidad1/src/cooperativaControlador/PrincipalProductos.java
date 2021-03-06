package cooperativaControlador;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import cooperativaModelo.FicheroProductos;
import cooperativaModelo.Producto;



public class PrincipalProductos {

	private static Scanner t = new java.util.Scanner(System.in); 
	private static FicheroProductos fp = new FicheroProductos("productos.txt");
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int opcion = 0;
		
		do {
			System.out.println("Introduce una opci�n");
			System.out.println("0-Salir");
			System.out.println("1-Crear Producto");
			System.out.println("2-Mostrar Productos");
			System.out.println("3-Modificar stock");
			System.out.println("4-Descatalogar producto");
			
			opcion = t.nextInt();t.nextLine();
			Producto p;
			switch(opcion){
				case 1:
					p = new Producto();
					System.out.println("Nombre:");
					p.setNombre(t.nextLine());
					System.out.println("Precio:");
					p.setPrecio(t.nextFloat());t.nextLine();
					p.setCodigo(fp.obtenerNuevoCodigo());
					p.setStock(0);
					p.setFechaAlta(new Date().getTime());
					p.setDescatalogado(false);
					if(!fp.crearProducto(p)) {
						System.out.println("Error al crear el producto");
					}
					break;
				case 2:
					ArrayList<Producto> productos = fp.obtenerProductos();
					for(Producto mP:productos) {
						mP.mostrar();
					}
					break;
				case 3:
					System.out.println("C�digo de producto a modificar");
					p = new Producto();
					p.setCodigo(t.nextInt()); t.nextLine();
					System.out.println("Introduce el stock a sumar");
					int cantidad = t.nextInt();t.nextLine();
					if(!fp.modificarStock(p, cantidad)) {
						System.out.println("Error, el producto no existe "
								+ "o no se ha modificado");
					}
					
							
					break;
				case 4:
					System.out.println("C�digo de producto a descatalogar");
					p = new Producto();
					p.setCodigo(t.nextInt()); t.nextLine();
					if(!fp.descatalogar(p)) {
						System.out.println("Error, el producto no existe "
								+ "o no se ha descatalogado");
					}
					
							
					break;
				
				case 5:
				
					break;
				case 6:
					
					break;
			}
			
		}while(opcion!=0);
	}
}
