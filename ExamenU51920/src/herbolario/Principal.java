package herbolario;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Principal {
	
	private static Scanner t = new java.util.Scanner(System.in); 
	private static Modelo bd = new Modelo();
	;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if(bd.getConexion()!=null) {
			int opcion = 0;						
			do {
				System.out.println("Introduce una opción");
				System.out.println("0-Salir");
				System.out.println("1-Alta producto");
				System.out.println("2-Crear precio");
				System.out.println("3-Crear venta");
				System.out.println("4-EStadística venta");
				System.out.println("5-Importar producto");
				System.out.println("6-Crear venta OO");
				System.out.println("7-Mostrar Ventas Producto");
				
				
				opcion = t.nextInt();t.nextLine();
				int codigo;
				
				switch(opcion){
					case 1:
						altaProducto();
						break;
					case 2:
						crearPrecio();
						break;
					case 3:
						crearVenta();
						break;
					case 4:
						estadistica();
						break;
					case 5:
						
						break;
					case 6:
						
						break;
					case 7:
						
						break;
					
					
				}
			}while(opcion!=0);
			bd.cerrar();
		}
		else {
			System.out.println("No hay conexión con la BD");
		}


}
	private static void estadistica() {
		// TODO Auto-generated method stub
		ArrayList<Object[]> datos = bd.obtenerEstadistica();
		for(Object[] o: datos) {
			System.out.println("Producto:"+ o[0] + 
					"\tUdesVendidas:" + o[1] +
					"\tImporteToal:" + o[2]);
		}
	}
	private static void crearVenta() {
		// TODO Auto-generated method stub
		mostrarProductos();
		System.out.println("Introduce código");
		int codigo = t.nextInt(); t.nextLine();
		Producto p = bd.obtenerProducto(codigo);
		if(p!=null) {
			if(p.getPrecios().size()==0) {				
				System.out.println("No se puede crear venta porque no hay precio");
			}
			else {
				Venta v = new Venta();
				v.setProducto(p);
				v.setFecha(new Date());
				System.out.println("Cantidad");
				v.setCantidad(t.nextInt()); t.nextLine();
				v.setPrecio(v.getCantidad()*p.getPrecios().get(p.getPrecios().size()-1));
				if(!bd.crearVenta(v)) {
					System.out.println("Error al crear la venta");
				}
			}
		
		}
		else {
		System.out.println("Error, producto no existe");
		}
	}
	private static void crearPrecio() {
		// TODO Auto-generated method stub
		mostrarProductos();
		System.out.println("Introduce código");
		int codigo = t.nextInt(); t.nextLine();
		Producto p = bd.obtenerProducto(codigo);
		if(p!=null) {
			System.out.println("Introduce precio");
			int precio = t.nextInt(); t.nextLine();
			if(!bd.addPrecio(p,precio)) {
				System.out.println("Error al añadir el precio");
			}
			mostrarProductos();
		}
		else {
		System.out.println("Error, producto no existe");
		}
	}
	private static void altaProducto() {
		// TODO Auto-generated method stub
		Producto p = new Producto();
		System.out.println("Nombre:");
		p.setNombre(t.nextLine());
		p.setInfo(new DatosNutricion());
		System.out.println("Kcal:");
		p.getInfo().setKcal(t.nextInt()); t.nextLine();
		System.out.println("Grasas:");
		p.getInfo().setGrasas(t.nextInt()); t.nextLine();
		System.out.println("Hidratos:");
		p.getInfo().setHidratos(t.nextInt()); t.nextLine();
		if(!bd.altaProducto(p)) {
			System.out.println("Error al crear el producto");
		}
		mostrarProductos();
		
	}
	private static void mostrarProductos() {
		// TODO Auto-generated method stub
		ArrayList<Producto> productos = bd.obtenerProductos();
		for(Producto p:productos) {
			p.mostrar();
		}
	}

	
}
