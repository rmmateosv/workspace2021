package Peluqueria;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Principal {
	private static Scanner t = new java.util.Scanner(System.in);
	private static Modelo bd = new Modelo();
	private static SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy hh:mm");

	private static ArrayList<Cita> citas;
	private static ArrayList<Cliente> clientes;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if (bd.getColeccion() != null) {
			int opcion = 0;

			do {
				System.out.println("Introduce una opci�n");
				System.out.println("0-Salir");
				System.out.println("1-Alta Cita");
				System.out.println("2-Mostrar Citas de una fecha");
				System.out.println("3-Modificar fecha cita");
				System.out.println("4-Borrar cita");
				System.out.println("5-Mostrar citas de un cliente");

				opcion = t.nextInt();
				t.nextLine();
				int codigo;
				switch (opcion) {
				case 1:
					altaCita();
					break;
				case 2:
					mostrarCitas();
					break;
				case 3:
					modificarFecha();
					break;
				case 4:
					borrarCita();
					break;
				case 5:
					mostrarCitasCliente();
					break;

				}
			} while (opcion != 0);
			bd.cerrar();
		} else {
			System.out.println("Error: No se ha conectado con la bd");
		}
	}

	private static void mostrarCitasCliente() {
		// TODO Auto-generated method stub
		clientes = bd.obtenerClientes();
		if (clientes.isEmpty()) {
			System.out.println("Error, no existen clientes");
		} else {
			for (Cliente c : clientes) {
				c.mostrar();
			}
			System.out.println("Introduce dni");
			String dni = t.nextLine();
			if (bd.obtenerCliente(dni) != null) {
				bd.mostrarCitasCliente(dni);
			} else {
				System.out.println("Error,  no existe el cliente");
			}
		}

	}

	private static void borrarCita() {
		// TODO Auto-generated method stub
		citas = bd.obtenerCitas();
		for (Cita c : citas) {
			c.mostrar();
		}
		System.out.println("Introduce la cita a borrar");
		int id = t.nextInt();
		t.nextLine();
		Cita c = bd.obtenerCitas(id);
		if (c != null) {
			if (!bd.borrarCita(c)) {
				System.out.println("Error al borrar la cita");
			}
		} else {
			System.out.println("Error: la cita no existe");
		}
	}

	private static void modificarFecha() {
		// TODO Auto-generated method stub
		citas = bd.obtenerCitas();
		for (Cita c : citas) {
			c.mostrar();
		}
		System.out.println("Introduce la cita a modificar");
		int id = t.nextInt();
		t.nextLine();
		Cita c = bd.obtenerCitas(id);
		if (c != null) {
			System.out.println("Introduce nueva fecha dd/mm/yyyy hh:mm");
			try {
				c.setFecha(formato.parse(t.nextLine()));
				if (!bd.modificarCita(c)) {
					System.out.println("Error al modificar la cita");
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			System.out.println("Error: la cita no existe");
		}
	}

	private static void mostrarCitas() {
		// TODO Auto-generated method stub

		try {
			System.out.println("Introduce fecha dd/mm/yyyy");
			Date fecha = formato.parse(t.nextLine());
			citas = bd.obtenerCitas(fecha);
			for (Cita c : citas) {
				c.mostrar();
			}

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void altaCita() {
		// TODO Auto-generated method stub
		try {
			clientes = bd.obtenerClientes();
			for (Cliente c : clientes) {
				c.mostrar();
			}

			System.out.println("Introduce dni o ** para nuevo cliente");
			String dni = t.nextLine();
			Cliente c;
			if (dni.equals("**")) {
				c = new Cliente();
				System.out.println("DNI");
				c.setDni(t.nextLine());
				if (bd.obtenerCliente(c.getDni()) == null) {
					System.out.println("Nombre");
					c.setNombre(t.nextLine());
					System.out.println("Tel�fono");
					c.setTelefono(t.nextLine());
					if (!bd.altaCliente(c)) {
						System.out.println("Error al dar de alta el cliente");
					}
				} else {
					System.out.println("Error ya existe un cliente con ese dni");
				}
			} else {
				c = bd.obtenerCliente(dni);

			}
			if (c != null) {
				Cita cita = new Cita();
				cita.setDni(c.getDni());
				System.out.println("Fecha de cita dd/mm/yyyy hh:mm");
				cita.setFecha(formato.parse(t.nextLine()));
				System.out.println("Servicio");
				cita.setServicio(t.nextLine());
				System.out.println("Tiempo");
				cita.setTiempo(t.nextFloat());
				t.nextLine();
				if (!bd.crearCita(cita)) {
					System.out.println("Error al crear la cita");
				}

			} else {
				System.out.println("Cliente no existe");
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
