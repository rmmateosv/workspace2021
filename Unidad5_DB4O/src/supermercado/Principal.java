package supermercado;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
				System.out.println("4-Modificar stock");		
				System.out.println("5-Borrar producto");
				System.out.println("6-Mostrar productos por nombre");
				System.out.println("7-Mostrar productos por rango de stock");	
				System.out.println("8-Crear Compra");
				System.out.println("9-Mostrar Compra");
				
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
					case 4:
						modificarStock();
						break;
					case 5:
						borrarProducto();
						break;
					case 6:
						mostrarProductosPorNombre();
						break;
					case 7:
						mostrarProductosPorStock();
						break;
					case 8:
						crearCompra();
						break;
					case 9:
						mostrarCompra();
						break;
					
				}
			}while(opcion!=0);
			bd.cerrar();
		}
		else {
			System.out.println("No hay conexión con la BD");
		}


}

	private static void mostrarCompra() {
		// TODO Auto-generated method stub
		ArrayList<Compra> compras = bd.obtenerCompras();
		for(Compra c:compras) {
			c.mostrar(false);
		}
		System.out.println("Introduce código de compra");
		int codigo = t.nextInt(); t.nextLine();
		
		
	}

	private static void crearCompra() {
		// TODO Auto-genera
		mostrarClientes();
		System.out.println("Introduce Nif");
		String nif = t.nextLine();
		Cliente c=null;
		c=bd.obtenerCliente(nif);
		if(c==null) {
			System.out.println("El cliente no existe. ¿Desea Crearlo (S/N)?");
			String confirma = t.nextLine();
			if(confirma.equalsIgnoreCase("S")) {
				c=new Cliente();
				c.setNif(nif);
				System.out.println("Nombre Cliente");
				c.setNombre(t.nextLine());
				if(!bd.crearCliente(c)) {
					System.out.println("Error al crear el cliente");
				}
				else {
					c = bd.obtenerCliente(nif);
				}
			}
		}
		if(c!=null) {
			crearCompra(c);
		}
		else {
			System.out.println("Error, no se puede crear la compra si no hay cliente");
		}
		
	}

	private static void crearCompra(Cliente c) {
		// TODO Auto-generated method stub
		Compra compra = new Compra();
		compra.setCliente(c);
		compra.setCodigo(bd.obtenerNuevoCodigoCompra());
		compra.setFecha(new Date());
		int maslineas = 0;
		do {
			mostrarProductos();
			String codigo = t.nextLine();
			Producto p = bd.obtenerProducto(codigo);
			if(p!=null) {
				System.out.println("Introduce cantidad");
				int cantidad = t.nextInt();t.nextLine();
				if(p.getStock()>= cantidad) {
					DetalleCompra linea = new DetalleCompra();
					linea.setCompra(compra);
					linea.setProducto(p);
					linea.setCantidad(cantidad);
					linea.setPrecioUdad(p.getPrecio());
					p.setStock(p.getStock()-cantidad);
					compra.getDetalle().add(linea);
				}
				else {
					System.out.println("Error, no hay stock suficiente");
				}
			}
			else
			{
				System.out.println("Error, producto no existe");
			}
			System.out.println("¿Desea añadir otra línea a la compra? (0:No/1:Sí)");			
			maslineas= t.nextInt();t.nextLine();
		}while(maslineas!=0);
		if(!compra.getDetalle().isEmpty()) {
			if(!bd.crearCompra(compra)) {
				System.out.println("Error al crear la compra");
			}
		}
	}

	private static void mostrarClientes() {
		// TODO Auto-generated method stub
		ArrayList<Cliente> clientes = bd.obtenerClientes();
		for(Cliente c:clientes) {
			c.mostrar();
		}
	}

	private static void mostrarProductosPorStock() {
		// TODO Auto-generated method stub
		System.out.println("Introduce stock mínimo");
		int min = t.nextInt(); t.nextLine();
		System.out.println("Introduce stock máximo");
		int max = t.nextInt(); t.nextLine();
		ArrayList<Producto> productos = bd.obtenerProductosStock(min, max);
		for(Producto p: productos) {
			p.mostrar();
		}
	}

	private static void mostrarProductosPorNombre() {
		// TODO Auto-generated method stub
		System.out.println("Introduce parte del nombre");
		String texto = t.nextLine();
		ArrayList<Producto> productos = bd.obtenerProductosNombre(texto);
		for(Producto p: productos) {
			p.mostrar();
		}
	}

	private static void borrarProducto() {
		// TODO Auto-generated method stub
		mostrarProductos();
		System.out.println("Código de producto a modificar");
		String codigo = t.nextLine();
		Producto p = bd.obtenerProducto(codigo);
		if(p!=null) {			
			if(!bd.borrarProducto(p)) {
				System.out.println("Error al borrar el producto");
			}
		}
		else {
			System.out.println("Error, el producto no existe");
		}
	}

	private static void modificarStock() {
		// TODO Auto-generated method stub
		mostrarProductos();
		System.out.println("Código de producto a modificar");
		String codigo = t.nextLine();
		Producto p = bd.obtenerProducto(codigo);
		if(p!=null) {
			System.out.println("Número de unidades a variar stock");
			p.setStock(p.getStock() + t.nextInt()); t.nextLine();
			if(!bd.actualizarProducto(p)) {
				System.out.println("Error al modificar el producto");
			}
		}
		else {
			System.out.println("Error, el producto no existe");
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
			if(!bd.actualizarProducto(p)) {
				System.out.println("Error al crear el producto");
			}
		}
		else {
			System.out.println("Error, ya existe un producto con ese código");
		}
	}
}
