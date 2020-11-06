package Gimnasio;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

			opcion = t.nextInt();
			t.nextLine();

			switch (opcion) {
				case 1:
					incribirseActividad();
					break;
				case 2:
					
				
					break;
			}
		}while (opcion != 0);
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

			opcion = t.nextInt();
			t.nextLine();

			switch (opcion) {
				case 1:
					altaCliente();
					break;
				case 2:
					
				
					break;
			}
		}while (opcion != 0);
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
