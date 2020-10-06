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
			System.out.println("3-Borrar Entrega!!!!!");
			
			
			opcion = t.nextInt();t.nextLine();
			Entregas e;
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
					
					
					break;
				case 3:
					
					break;
				case 4:
					
				
					break;
				
			}
			
		}while(opcion!=0);
	}

}
