package Cooperativa;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;



public class Principal {

	private static Scanner t = new java.util.Scanner(System.in); 
	private static Modelo bd = new Modelo();
	private static SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy"); 

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int opcion = 0;
		if(bd.getConexion()!=null) {
			do {
				System.out.println("Introduce una opci�n");
				System.out.println("0-Salir");
				System.out.println("1-Mostrar Socios");			
				System.out.println("2-Mostrar Socios por nombre/parte del nombre");
				System.out.println("3-Crear Socio");
				System.out.println("4-Aumentar Saldo");
				System.out.println("5-Borrar Socio (falla si tiene entregas)");
				System.out.println("6-Mostrar socios por fecha alta");
				System.out.println("7-Crear entrega (Mostrar socios y frutas y "
						+ "comprobar que existen antes de crear la entrega)");
				System.out.println("8-Mostrar entregas de un socio cuyo nombre (o parte) "
						+ "se pasa par�metro");
				System.out.println("9-Mostrar estad�stica socio");
				System.out.println("10-Borrar socio");
				
				
				opcion = t.nextInt(); t.nextLine();	
				ArrayList<Socio> socios;
				Socio socio;
				switch(opcion){
					case 1:
						socios = bd.obtenerSocios();
						for(Socio s:socios) {
							s.mostrar();
						}
						break;
					case 2:
						System.out.println("Introduce nombre/parte del nombre");
						String patron = t.nextLine();
						socios = bd.obtenerSocios(patron);
						for(Socio s:socios) {
							s.mostrar();
						}
						break;
					case 3:
						socio = new Socio();
						System.out.println("DNI");						
						socio.setNif(t.nextLine());
						if(bd.obtenerSocio(socio.getNif())==null) {
							System.out.println("Nombre");
							socio.setNombre(t.nextLine());
							if(!bd.insertarSocio(socio)){
								System.out.println("Error al crear el socio");
							}
						}
						else {
							System.out.println("Error, dni ya existe");
						}
						break;
					case 4:
						socio = new Socio();
						System.out.println("DNI");						
						socio.setNif(t.nextLine());
						socio = bd.obtenerSocio(socio.getNif());
						if(socio!=null) {		
							System.out.println("Incremento");
							float cantidad = t.nextFloat();t.nextLine();
							if(!bd.modificarSaldo(socio,cantidad)) {
								System.out.println("Error al modificar el saldo");
							}
						}
						else {
							System.out.println("Socio no existe");
						}
						break;
					case 5:
						socio = new Socio();
						System.out.println("DNI");						
						socio.setNif(t.nextLine());
						socio = bd.obtenerSocio(socio.getNif());
						if(socio!=null) {							
							if(!bd.borrarSocio(socio)) {
								System.out.println("Error al borrar el socio");
							}
						}
						else {
							System.out.println("Socio no existe");
						}
						break;
					case 6:
						System.out.println("Introduce fecha (dd/mm/yyyy)");
						String fecha = t.nextLine();
						try {
							Date fechaAlta = formato.parse(fecha);
							socios = bd.obtenerSocios(fechaAlta);
							for(Socio s:socios) {
								s.mostrar();
							}
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							System.out.println("Fecha incorrecta");
						}
						
						break;
					case 9:
						socio = new Socio();
						System.out.println("DNI");						
						socio.setNif(t.nextLine());
						socio = bd.obtenerSocio(socio.getNif());
						if(socio!=null) {							
							ArrayList<Object[]> estadistica=bd.obtenerEstadistica(socio.getNif());
							for(Object[] fila:estadistica) {
								System.out.println("CodigoF:"+fila[0] +
										"\tNombre:"+fila[1] + 
										"\tNumEntregas:"+fila[2]+
										"\tTotalKg:"+fila[3] +
										"\tPrecioMedio:"+fila[4]);
							}
						}
						else {
							System.out.println("Socio no existe");
						}
						break;
					
				}
				
			}while(opcion!=0);
			//Cerramos la conexi�n con la BD.
			bd.cerrar();
		}
		else {
			System.out.println("No se ha podido conectar con la BD");
		}
	}

}