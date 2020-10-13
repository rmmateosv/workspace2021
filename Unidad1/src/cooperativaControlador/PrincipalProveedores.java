package cooperativaControlador;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import cooperativaModelo.FicheroProveedores;
import cooperativaModelo.Proveedor;

public class PrincipalProveedores {

	private static Scanner t = new java.util.Scanner(System.in);
	private static FicheroProveedores fp = new FicheroProveedores("proveedores.xml");

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int opcion = 0;
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

		do {
			System.out.println("Introduce una opción");
			System.out.println("0-Salir");
			System.out.println("1-Alta Proveedor");
			System.out.println("2-Mostrar Proveedores");
			System.out.println("3-Modificar fecha último pedido");
			System.out.println("3-Borrar Proveedores " + "(crea fichero con los que están de baja)!!!!!");

			opcion = t.nextInt();
			t.nextLine();

			Proveedor pr;
			switch (opcion) {
			case 1:
				try {
					pr = new Proveedor();
					System.out.println("Introduce el nombre");
					pr.setNombre(t.nextLine());
					System.out.println("Teléfono");
					pr.getTelefonos().add(t.nextLine());
					pr.setFecha_pedido(formato.parse("01/01/1900"));
					pr.setBaja(false);

					pr.setCodigo(fp.obtenerNuevoCodigo());

					if (!fp.altaProveedor(pr)) {
						System.out.println("Error al dar de alta el proveedor");
					}
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 2:
				ArrayList<Proveedor> listaP = fp.obtenerProveedores();
				for (Proveedor p : listaP) {
					p.mostrar();
				}
				break;
			case 3:

				try {
					System.out.println("Código proveedor:");
					int codigo = t.nextInt();
					t.nextLine();
					pr = fp.obtenerProveedor(codigo);
					if (pr != null) {
						System.out.println("Nueva fecha (dd/mm/yyyy)");
						pr.setFecha_pedido(formato.parse(t.nextLine()));
						pr.mostrar();
						//fp.modificarFecha(pr);
					} else {
						System.out.println("Error, el proveedor no existe");
					}
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;

			}

		} while (opcion != 0);
	}

}
