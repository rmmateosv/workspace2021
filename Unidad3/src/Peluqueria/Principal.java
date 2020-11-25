package Peluqueria;

import java.util.ArrayList;
import java.util.Scanner;



	public class Principal {
	private static Scanner t = new java.util.Scanner(System.in); 
	private static Modelo bd = new Modelo();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if(bd.getColeccion()!=null) {
			int opcion = 0;
			
			
			do {
				System.out.println("Introduce una opci�n");
				System.out.println("0-Salir");
				System.out.println("1-Alta Cita");
				System.out.println("2-Mostrar Citas de una fecha");
				System.out.println("3-Modificar fecha cita");
				System.out.println("4-Borrar cita");
				System.out.println("5-Mostrar citas de un cliente");
				
				opcion = t.nextInt();t.nextLine();
				int codigo;
				switch(opcion){
					case 1:
						altaCita();
						break;
					
				}
			}while(opcion!=0);
			bd.cerrar();
		}
		else {
			System.out.println("Error: No se ha conectado con la bd");
		}
	}

	private static void altaCita() {
		// TODO Auto-generated method stub
		ArrayList<Cliente> clientes  = bd.obtenerClientes();
		for(Cliente c:clientes) {
			c.mostrar();
		}
		
		System.out.println("Introduce dni o ** para nuevo cliente");
		String dni = t.nextLine();
		if(dni.equals("**")) {
			Cliente c = new Cliente();
			System.out.println("DNI");
			c.setDni(t.nextLine());
			if(bd.obtenerCliente(c.getDni())==null) {
				System.out.println("Nombre");
				c.setNombre(t.nextLine());
				System.out.println("Tel�fono");
				c.setTelefono(t.nextLine());			
				if(!bd.altaCliente(c)) {
					System.out.println("Error al dar de alta el cliente");
				}
			}
			else {
				System.out.println("Error ya existe un cliente con ese dni");
			}
		}
		else {
			
		}
	}

}
