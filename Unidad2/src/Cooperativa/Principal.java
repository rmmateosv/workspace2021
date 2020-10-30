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
		if (bd.getConexion() != null) {
			do {
				System.out.println("Introduce una opción");
				System.out.println("0-Salir");
				System.out.println("1-Mostrar Socios");
				System.out.println("2-Mostrar Socios por nombre/parte del nombre");
				System.out.println("3-Crear Socio");
				System.out.println("4-Aumentar Saldo");
				System.out.println("5-Borrar Socio (falla si tiene entregas)");
				System.out.println("6-Mostrar socios por fecha alta");
				System.out.println("7-(solucion Celia) Crear entrega (Mostrar socios y frutas y "
						+ "comprobar que existen antes de crear la entrega)");
				System.out.println("8-(solucion Celia) Mostrar entregas de un socio cuyo nombre (o parte) " + "se pasa parámetro");
				System.out.println("9-Mostrar estadística socio");
				System.out.println("10-Borrar socio");

				opcion = t.nextInt();
				t.nextLine();
				ArrayList<Socio> socios;
				ArrayList<Frutas> frutas;
				ArrayList<Entregas> entregas;

				Socio socio;
				Frutas fruta;
				Entregas entrega;

				switch (opcion) {
				case 1:
					socios = bd.obtenerSocios();
					for (Socio s : socios) {
						s.mostrar();
					}
					break;
				case 2:
					System.out.println("Introduce nombre/parte del nombre");
					String patron = t.nextLine();
					socios = bd.obtenerSocios(patron);
					for (Socio s : socios) {
						s.mostrar();
					}
					break;
				case 3:
					socio = new Socio();
					System.out.println("DNI");
					socio.setNif(t.nextLine());
					if (bd.obtenerSocio(socio.getNif()) == null) {
						System.out.println("Nombre");
						socio.setNombre(t.nextLine());
						if (!bd.insertarSocio(socio)) {
							System.out.println("Error al crear el socio");
						}
					} else {
						System.out.println("Error, dni ya existe");
					}
					break;
				case 4:
					socio = new Socio();
					System.out.println("DNI");
					socio.setNif(t.nextLine());
					socio = bd.obtenerSocio(socio.getNif());
					if (socio != null) {
						System.out.println("Incremento");
						float cantidad = t.nextFloat();
						t.nextLine();
						if (!bd.modificarSaldo(socio, cantidad)) {
							System.out.println("Error al modificar el saldo");
						}
					} else {
						System.out.println("Socio no existe");
					}
					break;
				case 5:
					socio = new Socio();
					System.out.println("DNI");
					socio.setNif(t.nextLine());
					socio = bd.obtenerSocio(socio.getNif());
					if (socio != null) {
						if (!bd.borrarSocio(socio)) {
							System.out.println("Error al borrar el socio");
						}
					} else {
						System.out.println("Socio no existe");
					}
					break;
				case 6:
					System.out.println("Introduce fecha (dd/mm/yyyy)");
					String fecha = t.nextLine();
					try {
						Date fechaAlta = formato.parse(fecha);
						socios = bd.obtenerSocios(fechaAlta);
						for (Socio s : socios) {
							s.mostrar();
						}
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						System.out.println("Fecha incorrecta");
					}

					break;
				case 7:
					entrega = new Entregas();
					System.out.println("CREAR ENTREGA");

					// Mostramos los socios
					socios = bd.obtenerSocios();
					for (Socio s : socios) {
						s.mostrar();
					}

					// COgemos el nif del socio
					System.out.println("1º Introduzca el NIF del socio");
					String codigoS = t.nextLine();
					socio = bd.obtenerSocio(codigoS);

					// Si el socio existe
					if (socio != null) {
						entrega.setSocio(new Socio());
						entrega.getSocio().setNif(codigoS);

						// Mostramos las frutas
						frutas = bd.obtenerFrutas();
						for (Frutas f : frutas) {
							f.mostrar();
						}
						// Cogemos el codigo de la fruta
						System.out.println("2º Introduzca el código de la fruta");
						int codigoF = t.nextInt();
						t.nextLine();
						fruta = bd.obtenerFruta(codigoF);

						// Si existe la fruta
						if (fruta != null) {
							entrega.setFruta(new Frutas());
							entrega.getFruta().setCodigo(codigoF);

							// Pedimos el resto de campos de entrega
							System.out.println("Introduce los kilos de la entrega:");
							entrega.setKilos(t.nextFloat());
							t.nextLine();
							System.out.println("Introduce el precio del kilo:");
							entrega.setPrecio(t.nextFloat());
							t.nextLine();

							if (!bd.crearEntrega(entrega)) {
								System.out.println("ERROR: no se pudo crear la entrega");
							}

						} else {
							System.out.println("ERROR: la fruta no existe");
						}
					} else {
						System.out.println("ERROR: ese socio no existe");
					}
					break;

				case 8:
					socios = bd.obtenerSocios();
					for (Socio s : socios) {
						s.mostrar();
					}

					System.out.println("Introduzca el nombre/parte del nombre del socio:");
					String nombre = t.nextLine();
					entregas = bd.obtenerEntregas(nombre);
					for (Entregas e : entregas) {
						e.mostrar();
					}
					break;

				case 9:
					socio = new Socio();
					System.out.println("DNI");
					socio.setNif(t.nextLine());
					socio = bd.obtenerSocio(socio.getNif());
					if (socio != null) {
						ArrayList<Object[]> estadistica = bd.obtenerEstadistica(socio.getNif());
						for (Object[] fila : estadistica) {
							System.out.println("CodigoF:" + fila[0] + "\tNombre:" + fila[1] + "\tNumEntregas:" + fila[2]
									+ "\tTotalKg:" + fila[3] + "\tPrecioMedio:" + fila[4]);
						}
					} else {
						System.out.println("Socio no existe");
					}
					break;
				case 10:
					socio = new Socio();
					System.out.println("DNI");
					socio.setNif(t.nextLine());
					socio = bd.obtenerSocio(socio.getNif());
					if (socio != null) {
						if (bd.funcionBorrarSocio(socio) == -1) {
							System.out.println("Error, el socio tiene entregas");
						}
					} else {
						System.out.println("Socio no existe");
					}
					break;

				}

			} while (opcion != 0);
			// Cerramos la conexión con la BD.
			bd.cerrar();
		} else {
			System.out.println("No se ha podido conectar con la BD");
		}
	}

}
