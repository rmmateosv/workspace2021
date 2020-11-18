package Gimnasio;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;





public class Principal {
	
	private static Scanner t = new java.util.Scanner(System.in);
	private static Modelo bd = new Modelo();
	private static SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
	private static String usuario;
	private static Cliente cliente;
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		if(bd.getConexion()!=null) {
			//Pedimos usuario y clave
			System.out.println("Usuario:");
			usuario = t.nextLine();
			System.out.println("Clave");
			String clave = t.nextLine();
			String tipo = bd.comprobarUsuario(usuario, clave);
			switch(tipo){
				case "A":
					menuAdmin();
					break;
				case "C":
					if(clave.equalsIgnoreCase(usuario)) {
						System.out.println("Nueva contraseña:");
						clave = t.nextLine();
						if(!bd.cambiarClave(usuario,clave)){
							System.out.println("No se ha cambiado la contraseña");
						}
					}
					cliente = bd.obtenerCliente(usuario);
					menuCliente();
					break;
				case "NE":
					System.out.println("Error, datos incorrectos");
					break;	
			}
		}
		else {
			System.out.println("Error, no se ha podido conectaqr a la BD");
		}
	}
	private static void menuCliente() {
		// TODO Auto-generated method stub
		System.out.println("Usuario Cliente");
		int opcion = 0;
		do {
			System.out.println("Introduce una opción");
			System.out.println("0-Salir");
			System.out.println("1-Inscribirse en actividad");
			System.out.println("2-Mostrar mis actividades");
			System.out.println("3-Borrar actividad");
			System.out.println("4-Ver Recibos");
			

			opcion = t.nextInt();
			t.nextLine();

			switch (opcion) {
				case 1:
					incribirseActividad();
					break;
				case 2:
					
					mostrarMisActividades();
					break;
				case 3:
					borrarMiActivida();
					break;
				case 4:
					verRecibos();
					break;
			}
		}while (opcion != 0);
	}
	private static void verRecibos() {
		// TODO Auto-generated method stub
		System.out.println("¿Qué recibos deseas ver? (Pagado:P/"
				+ "Pendientes Pago:PP/Todos:T)");
		String tipo = t.nextLine();
		ArrayList<Recibo> recibos = bd.obtenerRecibos(cliente, tipo);
		for(Recibo r:recibos) {
			r.mostrar();
		}
	}
	private static void borrarMiActivida() {
		// TODO Auto-generated method stub
		mostrarMisActividades();
		System.out.println("Código Actividad a borrar");
		int codigo = t.nextInt();t.nextLine();
		if(!bd.borrarMiActividad(cliente,codigo)) {
			System.out.println("Error al borrarse de actividad");
		}
	}
	private static void mostrarMisActividades() {
		// TODO Auto-generated method stub
		ArrayList<Actividad> lista = bd.obtenerMisActividades(cliente);
		for(Actividad a:lista) {
			a.mostrar();
		}
	}
	private static void incribirseActividad() {
		// TODO Auto-generated method stub
		
		for(Actividad a:bd.obtenerActividades()) {
			a.mostrar();
		}
		System.out.println("Introduce código");
		int codigoA = t.nextInt();t.nextLine();
		if(bd.obtenerActividad(codigoA)!=null) {
			if(!bd.inscribir(cliente.getId(),codigoA)) {
				System.out.println("Error al inscribirse en actividad");
			}
		}
		else {
			System.out.println("Error actividad no existe o no está activa");
		}
	}
	private static void menuAdmin() {
		// TODO Auto-generated method stub
		System.out.println("Usuario Administrador");
		int opcion = 0;
		do {
			System.out.println("Introduce una opción");
			System.out.println("0-Salir");
			System.out.println("1-Alta Cliente");			
			System.out.println("2-Generar recibos");
			System.out.println("3-Pagar Recibo");
			System.out.println("4-Mostrar ingresos año");

			opcion = t.nextInt();
			t.nextLine();

			switch (opcion) {
				case 1:
					altaCliente();
					break;
				case 2:
					generarRecibos();
					
					break;
				case 3:
					pagarRecibo();
					break;
				case 4:
					mostrarIngresos();
					break;
			}
		}while (opcion != 0);
	}
	private static void mostrarIngresos() {
		// TODO Auto-generated method stub
		System.out.println("Introduce año:");
		int anio = t.nextInt();t.nextLine();
		ArrayList<Object[]> ingresos = bd.ObtenerIngresos(anio);
		for(int i=1;i<ingresos.size();i++) {
			System.out.println("DNI:" + ingresos.get(i)[0] +
					"\tNombre:"+ingresos.get(i)[1] +
					"\tImporte:"+ingresos.get(i)[2]);
		}
		System.out.println("Total Año:"+ingresos.get(0)[0]);
	}
	private static void pagarRecibo() {
		// TODO Auto-generated method stub
		for(Cliente c:bd.obtenerClientes()) {
			c.mostrar();
		}
		System.out.println("Introduce dni");
		Cliente c = bd.obtenerCliente(t.nextLine());
		if(c!=null) {
			for(Recibo r:bd.obtenerRecibos(c, "PP")) {
				r.mostrar();
			}
			System.out.println("Introduce Fecha de Recibo");
			Date fecha;
			try {
				fecha = formato.parse(t.nextLine());
				if(!bd.pagarRecibo(c,fecha)) {
					System.out.println("Error, no se ha pagado el recibo");
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				System.out.println("Error, fecha incorrecta");
			}	
		}
		else {
			System.out.println("Error, el cliente no existe");
		}
	}
	private static void generarRecibos() {
		// TODO Auto-generated method stub
		System.out.println("Mes");
		int mes = t.nextInt(); t.nextLine();
		System.out.println("Año");
		int anio = t.nextInt(); t.nextLine();
		if(bd.generarRecibos(mes,anio)<=0) {
			System.out.println("Error, los recibos pueden estar ya están generados");
		}
	}
	private static void altaCliente() {
		// TODO Auto-generated method stub
		System.out.println("DNI");
		Cliente c = new Cliente();
		c.setDni(t.nextLine());
		if(bd.obtenerCliente(c.getDni())==null) {
			System.out.println("Nombre");
			c.setNombre(t.nextLine());
			System.out.println("Apellidos");
			c.setApellidos(t.nextLine());
			System.out.println("Teléfono");
			c.setTelefono(t.nextLine());
			c.setUsuario(c.getDni());
			if(!bd.altaCliente(c)) {
				System.out.println("Error al dar de alta el cliente");
			}
			else {
				System.out.println("Cliente creado");
				c.mostrar();
			}
		}
	}

}
