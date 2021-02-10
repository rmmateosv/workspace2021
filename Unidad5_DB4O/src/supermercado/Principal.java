package supermercado;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class Principal {
	private static Scanner t = new java.util.Scanner(System.in); 
	private static Modelo bd = new Modelo();
	private static SimpleDateFormat df = new SimpleDateFormat("ddMMyyyy");
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if(bd.getConexion()!=null) {
			int opcion = 0;						
			do {
				System.out.println("Introduce una opción");
				System.out.println("0-Salir");
				System.out.println("1-Crear Producto");
				System.out.println("2-Mostrar productos");			
				System.out.println("3-Mostrar productos por tipo");		
				
				opcion = t.nextInt();t.nextLine();
				int codigo;
				
				switch(opcion){
					case 1:
						crearProducto();
						break;
					case 2:
						mostrarProductos();
						break;
					case 3:
						buscarProductoPorTipo();
						break;
					
				}
			}while(opcion!=0);
			bd.cerrar();
		}
		else {
			System.out.println("No hay conexión con la BD");
		}


}

	private static void buscarProductoPorTipo() {
		// TODO Auto-generated method stub
		System.out.println("Introduce el tipo (* para todos los tipos)");
		String tipo = t.nextLine();
		ArrayList<Producto> productos = bd.obtenerProductos(tipo);
		for(Producto p: productos) {
			p.mostrar();
		}
		
	}

	private static void mostrarProductos() {
		// TODO Auto-generated method stub
		ArrayList<Producto> productos = bd.obtenerProductos();
		for(Producto p: productos) {
			p.mostrar();
		}
	}

	private static void crearProducto() {
		// TODO Auto-generated method stub
		System.out.println("Introduce código");
		String codigo = t.nextLine();
		Producto p = bd.obtenerProducto(codigo);
		if(p==null) {
			p = new Producto();
			p.setCodigo(codigo);
			System.out.println("Nombre:");
			p.setNombre(t.nextLine());
			System.out.println("Tipo:");
			p.setTipo(t.nextLine());
			System.out.println("Precio:");
			p.setPrecio(t.nextFloat());t.nextLine();
			System.out.println("Stock:");
			p.setStock(t.nextInt());t.nextLine();
			if(!bd.crearProducto(p)) {
				System.out.println("Error al crear el producto");
			}
		}
		else {
			System.out.println("Error, ya existe un producto con ese código");
		}
	}
}
