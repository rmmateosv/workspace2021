package supermercado;

import java.text.SimpleDateFormat;
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
				
				opcion = t.nextInt();t.nextLine();
				int codigo;
				
				switch(opcion){
					case 1:
						crearProducto();
						break;
					
					
				}
			}while(opcion!=0);
			bd.cerrar();
		}
		else {
			System.out.println("No hay conexión con la BD");
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
			System.out.println("PRecio:");
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
