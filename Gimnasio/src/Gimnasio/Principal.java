package Gimnasio;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;





public class Principal {
	
	private static Scanner t = new java.util.Scanner(System.in);
	private static Modelo bd = new Modelo();
	private static SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
	private static String usuario;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		if(bd.getConexion()!=null) {
			//Pedimos usuario y clave
			System.out.println("Usuario:");
			usuario = t.nextLine();
			System.out.println("Clave");
			String tipo = bd.comprobarUsuario(usuario,t.nextLine());
			switch(tipo){
				case "A":
					menuAdmin();
					break;
				case "C":
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
			c.setApellidos(t.nextLine());
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
