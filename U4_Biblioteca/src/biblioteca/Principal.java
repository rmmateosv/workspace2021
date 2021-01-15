package biblioteca;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class Principal {
	
	private static Scanner t = new java.util.Scanner(System.in); 
	private static Modelo bd = new Modelo();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if(bd.getConexion()!=null) {
			int opcion = 0;						
			do {
				System.out.println("Introduce una opción");
				System.out.println("0-Salir");
				System.out.println("1-Crear Socio");
				System.out.println("2-Crear Libro");
				System.out.println("3-Modificar Ejemplares");
				System.out.println("4-Crear préstamo");
				System.out.println("5-Devolver préstamo");
				System.out.println("6-Mostrar socio");
				System.out.println("7-Mostrar número préstamos pendientes de un socio");
				System.out.println("8-Mostrar número préstamos pendientes de devolución");
				System.out.println("9-Mostrar el número de libros y el número de ejemplares totales de la biblioteca");
				System.out.println("10-Borrar libro");
				System.out.println("11-Borrar socio");
				
				opcion = t.nextInt();t.nextLine();
				int codigo;
				
				switch(opcion){
					case 1:
						crearSocio();
						break;
					case 2:
						crearLibro();
						break;
					case 3:
						modificarEjem();
						break;
					case 5:
						devolverPrestamo();
						break;
					case 6:
						mostrarSocio();
						break;

					
				}
			}while(opcion!=0);
			bd.cerrar();
		}
		else {
			System.out.println("No hay conexión con la BD");
		}
	}

	private static void devolverPrestamo() {
		// TODO Auto-generated method stub
		mostrarSocios();
		System.out.println("Introduce dni:");
		Socio s = bd.obtenerSocio(t.nextLine());
		if(s!=null) {
			//Mostrar préstamos sin devolver
			for(Prestamo p:s.getPrestamos()) {
				if(p.getFechaDevolReal()==null) {
					p.mostrar();
				}
			}
			System.out.println("Introduce ISBN");
			//Comprobar si hay un préstamo pendiente de ese libro
			Libro l = bd.obtenerLibro(t.nextLine());
			if(l!=null) {
				if(bd.yaPrestado(s, l)) {
					if(!bd.devolverPrestamo(s,l)) {
						System.out.println("Error al devolver el prétamo");
					}
				}
				else {
					System.out.println("Error, no existe el préstamo pendiente de devolución");
				}
			}
			else {
				System.out.println("Error: Libro no existe");
			}
		}
		else {
			System.out.println("Error: No existe el socio");
		}

	}

	private static void mostrarSocio() {
		// TODO Auto-generated method stub
		mostrarSocios();
		System.out.println("Introduce dni:");
		Socio s = bd.obtenerSocio(t.nextLine());
		if(s!=null) {
			s.mostrar(true);
		}
	}

	private static void crearPrestamo() {
		// TODO Auto-generated method stub
		mostrarSocios();
		System.out.println("Introduce dni:");
		Socio s = bd.obtenerSocio(t.nextLine());
		if(s!=null) {
			if(s.isSancionado()) {
				System.out.println("Error: Socio sancionado");
			}
			else {
				//Comprobamos préstamos sin devolver
				int contador=0;
				for(Prestamo p:s.getPrestamos()) {
					if(p.getFechaDevolReal()==null) {
						contador++;
					}
				}
				if(contador>=2) {
					System.out.println("Error: Tiene más de dos préstamos sin devolver");
				}
				else {
					mostrarLibros();
					System.out.println("Introduce ISBN");
					Libro l = bd.obtenerLibro(t.nextLine());
					if(l!=null) {
						//Comprobamos que haya ejemplares
						if(l.getNumEjemplares()>0) {
							//Comprobar si dos veces el mismo libro
							if(!bd.yaPrestado(s,l)) {
								if(!bd.prestar(s,l)) {
									System.out.println("Error: Error al registrar el préstamo");
								}
							}
							else {
								System.out.println("Error: No puedes tener prestado dos veces el mismo libro");
							}
						}
						else {
							System.out.println("Error: No hay ejemplares suficientes");
						}
					}
					else {
						System.out.println("Error: libro no existe");
					}
				}
				
			}
		}
		else {
			System.out.println("Error: No existe el socio");
		}
	}

	private static void modificarEjem() {
		// TODO Auto-generated method stub
	
		mostrarLibros();
		System.out.println("Introduce ISBN");
		String isbn = t.nextLine();
		Libro l = bd.obtenerLibro(isbn);
		if(l!=null) {
			System.out.println("Introduce número de ejemplares");
			int num = t.nextInt();t.nextLine();
			if(!bd.modificarEjemplares(l,num)) {
				System.out.println("Error al modificar los ejemplares "
						+ "del libro " + l.getIsbn());
			}
		}
		else {
			System.out.println("Error: No existe el libro");
		}

	
	}

	private static void crearLibro() {
		// TODO Auto-generated method stub
		
		System.out.println("Introduce el ISBN");
		String isbn=t.nextLine();
		Libro l = bd.obtenerLibro(isbn);
		if(l==null) {
			l = new Libro();
			l.setIsbn(isbn);
			System.out.println("Introduce el título");
			l.setTitulo(t.nextLine());
			System.out.println("Introduce el número de ejemplares");
			l.setNumEjemplares(t.nextInt());t.nextLine();
			if(!bd.crearLibro(l)) {
				System.out.println("Error al crear el libro");
			}
			else mostrarLibros();
			
		}
		else {
			System.out.println("Error: Ya existe un libro con ese ISBN");
		}
		
	}

	private static void crearSocio() {
		// TODO Auto-generated method stub
		Socio s = new Socio();
		
		System.out.println("Introduce el nif del sociio");
		String nif = t.nextLine();
		s= bd.obtenerSocio(nif);
		if(s==null) {
			s = new Socio();
			s.setNif(nif);
			System.out.println("Introduce el nombre");
			s.setNombre(t.nextLine());
			s.setSancionado(false);
			if(!bd.crearSocio(s)){
				System.out.println("Error al crear el socio");
			}
			else mostrarSocios();
		}
		else {
			System.out.println("Ya existe socio con ese nif");
		}
		
		
		
	}
	private static void mostrarLibros() {
		// TODO Auto-generated method stub
		try {
			List<Libro> libros = bd.obtenerLibros();
			for(Libro l:libros) {
				l.mostrar();
			}
			
			
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	private static void mostrarSocios() {
		// TODO Auto-generated method stub
		try {
			List<Socio> socios = bd.obtenerSocios();
			for(Socio s:socios) {
				s.mostrar(false);
			}
			
			
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
