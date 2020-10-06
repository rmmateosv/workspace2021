package cooperativaControlador;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

import cooperativaModelo.FicheroFrutas;
import cooperativaModelo.FicheroSocios;
import cooperativaModelo.Frutas;
import cooperativaModelo.Socio;

public class PrincipalFrutas {

	private static Scanner t = new java.util.Scanner(System.in); 
	private static FicheroFrutas ft = new FicheroFrutas("frutas.txt");

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int opcion = 0;
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		
		do {
			System.out.println("Introduce una opción");
			System.out.println("0-Salir");
			System.out.println("1-Registrar Fruta");
			System.out.println("2-Mostrar Frutas");
			System.out.println("3-Modificar Fecha Inicio Temporada");
			
			
			opcion = t.nextInt();t.nextLine();
			Frutas f;
			switch(opcion){
				case 1:			
					try {
						f = new Frutas();
						System.out.println("Nombre");
						f.setNombre(t.nextLine());
						System.out.println("Fecha Inicio Temporada dd/MM/yyyy");
						f.setFechaIT(formato.parse(t.nextLine()));
						f.setCodigo(ft.obtenerCodigo());
						if(!ft.regristrarFruta(f)) {
							System.out.println("Error al registrar la fruta");
						}
						
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						System.out.println("Fecha incorrecta");
					}
					break;
				case 2:
					ArrayList<Frutas> listaF;
					listaF= ft.obtenerFrutas();
					for(Frutas fr:listaF) {
						fr.mostrar();
					}
					
					break;
				case 3:
					try {
						System.out.println("Introduce código");
						int codigo = t.nextInt(); t.nextLine();
						f = ft.obtenerFruta(codigo);
						if(f!=null) {
							System.out.println("Introduce nueva fecha");
							f.setFechaIT(formato.parse(t.nextLine()));
							if(!ft.modificarFruta(f)) {
								System.out.println("Error al modificar la fecha");
							}							
						}
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				case 4:
					
				
					break;
				
			}
			
		}while(opcion!=0);
	}

}
