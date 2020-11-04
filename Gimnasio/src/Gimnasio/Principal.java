package Gimnasio;

import java.text.SimpleDateFormat;
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
				default:
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
	}

}
