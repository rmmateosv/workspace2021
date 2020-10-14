package cooperativaControlador;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import cooperativaModelo.Entregas;
import cooperativaModelo.FicheroEntregas;
import cooperativaModelo.FicheroFrutas;
import cooperativaModelo.FicheroSocios;
import cooperativaModelo.Frutas;
import cooperativaModelo.Socio;





public class PrincipalEntregas {

	private static Scanner t = new java.util.Scanner(System.in); 
	private static FicheroEntregas fe = new FicheroEntregas("entregas.txt");
	private static FicheroSocios fs = new FicheroSocios("socios.txt");
	private static FicheroFrutas ff = new FicheroFrutas("frutas.txt");

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int opcion = 0;
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		
		do {
			System.out.println("Introduce una opción");
			System.out.println("0-Salir");
			System.out.println("1-Registrar Entrega");
			System.out.println("2-Mostrar Entregas");
			System.out.println("3-Modificar precio");
			System.out.println("4-Borrar Entrega!!!!!");
			System.out.println("5-Generar Entregas Socio (xml)");
			
			
			opcion = t.nextInt();t.nextLine();
			Entregas e;
			int codigo;
			ArrayList<Entregas> lE;
			switch(opcion){
				case 1:
					e = new Entregas();
					ArrayList<Socio> lS = fs.obtenerSocios();
					for(Socio s:lS) {
						s.mostrar();
					}
					System.out.println("Introduce el Nif del Socio:");
					e.setSocio(fs.obtenerSocio(t.nextLine()));
					if(e.getSocio()!=null) {
						//Mostramos frutas
						ArrayList<Frutas> lF = ff.obtenerFrutas();
						for(Frutas f:lF) {
							f.mostrar();
						}
						System.out.println("Introduce el código de fruta");
						e.setFruta(ff.obtenerFruta(t.nextInt())); t.nextLine();
						if(e.getFruta()!=null) {
							e.setFecha(new Date());
							System.out.println("Introduce kilos");
							e.setKilos(t.nextFloat()); t.nextLine();
							System.out.println("Introduce precio Kg");
							e.setPrecio(t.nextFloat()); t.nextLine();
							if(!fe.crearEntrega(e)) {
								System.out.println("Error al crear la entrega");
							}
						}
						else {
							System.out.println("Error: La fruta no existe");
						}
					}
					else {
						System.out.println("Error: El socio no existe");
					}
					
					break;
				case 2:
					lE = fe.obtenerEntregas();
					for(Entregas en:lE) {
						en.setSocio(fs.obtenerSocio(en.getSocio().getNif()));
						en.setFruta(ff.obtenerFruta(en.getFruta().getCodigo()));
						en.mostrar();
					}
					
					break;
				case 3:
					System.out.println("Introduce código de entrega");
					codigo = t.nextInt(); t.nextLine();
					e = fe.obtenerEntrega(codigo);
					if(e!=null) {
						System.out.println("Nuevo precio");
						e.setPrecio(t.nextFloat()); t.nextLine();
						if(!fe.modificarEntrega(e)) {
							System.out.println("Error al modificar la entrega");
						}
					}
					else {
						System.out.println("Error, la entrega no existe");
					}
					break;
				case 4:
					
					System.out.println("Introduce código de entrega");
					codigo = t.nextInt(); t.nextLine();
					e = fe.obtenerEntrega(codigo);
					if(e!=null) {
						
						if(!fe.borrarEntrega(e)) {
							System.out.println("Error al modificar la entrega");
						}
					}
					else {
						System.out.println("Error, la entrega no existe");
					}
					break;
				case 5:
					ArrayList<Socio> lSocios = fs.obtenerSocios();
					for(Socio s:lSocios) {
						s.mostrar();
					}
					System.out.println("Introduce dni de socio");
					String codigoS = t.nextLine();
					Socio s = fs.obtenerSocio(codigoS);
					if(s!=null) {
						lE = fe.obtenerEntregas(s.getNif());
						for(Entregas en:lE) {
							en.setSocio(s);
							en.setFruta(ff.obtenerFruta(en.getFruta().getCodigo()));
						}
						fe.generarXML(s,lE);
						
					}
					else {
						System.out.println("Error, el socio no existe");
					}
					
					
					break;
				
			}
			
		}while(opcion!=0);
	}

}
