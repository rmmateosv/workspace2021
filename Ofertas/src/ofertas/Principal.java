package ofertas;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Principal {
	private static Modelo bd = new Modelo();
	private static Scanner t = new java.util.Scanner(System.in);
	private static String usuario, tipo;
	private static SimpleDateFormat formato = 
			new SimpleDateFormat("dd/MM/yyyy");
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if(bd.getConexion()!=null) {
			System.out.println("Introduce usuario");
			usuario = t.nextLine();
			System.out.println("Introduce clave");
			String clave = t.nextLine();
			tipo = bd.login(usuario,clave);
			if(tipo.equals("NE")) {
				System.out.println("Error en usuario o clave");
				
			}
			else {
				switch(tipo.toUpperCase()){
				case "A":
					menuAdmin();
					break;
				case "E":
					menuEstablecimiento();
					break;
				case "C":
					menuCliente();
					break;
				}
					
			}
			bd.cerrar();
		}
		else {
			System.out.println("No hay conexión con la BD");
		}
	}

	private static void menuCliente() {
		// TODO Auto-generated method stub
		System.out.println("Menú Cliente");
		int opcion = 0;
		do {
			System.out.println("Introduce una opción");
			
			

			opcion = t.nextInt();
			t.nextLine();

			switch (opcion) {
				case 1:
					
					break;
				
			}
		}while (opcion != 0);
	}

	private static void menuEstablecimiento() {
		// TODO Auto-generated method stub
		System.out.println("Menú Establecimiento");
		int opcion = 0;
		do {
			System.out.println("Introduce una opción");
			System.out.println("0-Salir");
			System.out.println("1-Alta oferta");
			System.out.println("2-Modificar Oferta");
			System.out.println("3-borrar oferta");
			System.out.println("4-consultar ofertas");
			
			

			opcion = t.nextInt();
			t.nextLine();

			switch (opcion) {
				case 1:					
				try {
					System.out.println("Fecha Inicio");
					Date fechaI = formato.parse(t.nextLine());
					System.out.println("Fecha Fin");
					Date fechaF = formato.parse(t.nextLine());
					System.out.println("Descripción");
					String desc = t.nextLine();
					if(!bd.altaOferta(usuario, fechaI, fechaF, desc)) {
						System.out.println("Error al dar de alta la oferta");
					}
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
					
					break;
				case 2:
					ArrayList<Object[]> ofertas = bd.obtenerOfertas(usuario);
					for(Object[] o: ofertas) {
						System.out.println("Id:"+o[0] + 
								"\tFechaI:"+formato.format(o[1])+
								"\tFechaF:"+formato.format(o[2])+
								"\tDescripción:"+o[4]);
					}
					try {
						System.out.println("Nº oferta a modificar");
						int id = t.nextInt();t.nextLine();
						System.out.println("Fecha Inicio");
						Date fechaI = formato.parse(t.nextLine());
						System.out.println("Fecha Fin");
						Date fechaF = formato.parse(t.nextLine());
						System.out.println("Descripción");
						String desc = t.nextLine();
						if(!bd.modificarOferta(usuario,id,fechaI,fechaF,desc)) {
							System.out.println("Error al modificar la oferta");
						}
					}catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					break;
				
			}
		}while (opcion != 0);
	}

	private static void menuAdmin() {
		// TODO Auto-generated method stub
		System.out.println("Menú Administrador");
		int opcion = 0;
		do {
			System.out.println("Introduce una opción");
			System.out.println("0-Salir");
			System.out.println("1-Alta usuario");
			
			

			opcion = t.nextInt();
			t.nextLine();

			switch (opcion) {
				case 1:
					System.out.println("Nombre de usuario");
					String us = t.nextLine();
					System.out.println("Contraseña");
					String pwd = t.nextLine();
					System.out.println("Tipo de usuario");
					String tipo = t.nextLine();
					
					if(!bd.altaUsuario(us, pwd, tipo)) {
						System.out.println("Error al crear el usuario");
					}
					
					break;
				
			}
		}while (opcion != 0);
	}

}
