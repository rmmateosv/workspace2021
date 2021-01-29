package ligaB;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class Principal {
	
	private static Scanner t = new java.util.Scanner(System.in); 
	private static Modelo bd = new Modelo();
	private static Partido partidoSel; 
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if(bd.getConexion()!=null) {
			int opcion = 0;						
			do {
				System.out.println("Introduce una opción");
				System.out.println("0-Salir");
				System.out.println("1-Seleccionar Partido");
				System.out.println("2-Registrar Acción");
				System.out.println("3-Anular Acción");
				System.out.println("4-Borrar Partido");
				System.out.println("5-Mostrar Estadística");
				
				opcion = t.nextInt();t.nextLine();
				int codigo;
				
				switch(opcion){
					case 1:
						seleccionarPartido();
						break;
					case 2:
						RegistrarAccion();
						break;
					case 3:
						AnularAccion();
						break;
					case 4:
						BorrarPartido();
						break;
					case 5:
						mostrarEstadistica();
						break;

					
				}
			}while(opcion!=0);
			bd.cerrar();
		}
		else {
			System.out.println("No hay conexión con la BD");
		}
	}

	private static void mostrarEstadistica() {
		// TODO Auto-generated method stub
		if(partidoSel==null) {
			System.out.println("Error:Selecciona partido");
		}
		else {
			List<Object[]> estadistica = bd.obtenerEstadistica(partidoSel);
			for(Object[] linea:estadistica) {
				System.out.println("Puntos Local:" + linea[0]+
						"\tPuntos Visitante:" + linea[1]+
						"\tCanastas 1 punto Local:" + linea[2]+
						"\tCanastas 1 punto Visitante:" + linea[3]+
						"\tCanastas 2 puntos Local:" + linea[4]+
						"\tCanastas 2 puntos Visitante:" + linea[5]+
						"\tCanastas 3 punto Local:" + linea[6]+
						"\tCanastas 3 punto Visitante:" + linea[7]);
			}
		}
	}

	private static void BorrarPartido() {
		// TODO Auto-generated method stub
		List<Partido> partidos = bd.obtenerPartidos();
		for(Partido p:partidos) {
			p.mostrar();
		}
		System.out.println("Introduce código");
		int codigo = t.nextInt();t.nextLine();
		Partido p = bd.obtenerPartido(codigo);
		if(p==null) {
			System.out.println("No existe el partido");
		}
		else {
			if(!p.getAcciones().isEmpty()) {
				System.out.println("Error, no se puede borrar el partido porque tiene acciones");
			}
			else {
				if(!bd.borrarPartido(p)) {
					System.out.println("Error al borrar el partido");
				}
			}
		}
	}

	private static void AnularAccion() {
		// TODO Auto-generated method stub
		
		if(partidoSel==null) {
			System.out.println("Error:Selecciona partido");
		}
		else {
			List<Accion> acciones = bd.obtenerAcciones(partidoSel);
			for(Accion a: partidoSel.getAcciones()){
				a.mostrar();
			}
			System.out.println("Introduce acción");
			int codigo = t.nextInt(); t.nextLine();
			Accion a = bd.obtenerAcccion(codigo);
			if(a==null) {
				System.out.println("Error, la acción no existe");
			}
			else {
				//Chequeamos que la acción introducida sea igual al partido seleccionado
				if(a.getPartido()==partidoSel) {
					if(!bd.anularAcción(a)) {
						System.out.println("Error al anular la acción");
					}
				}
			}
		}
	}

	private static void RegistrarAccion() {
		// TODO Auto-generated method stub
		if(partidoSel==null) {
			System.out.println("Error:Selecciona partido");
		}
		else {
			List<TipoAccion> tipos = bd.obtenerTipoAcciones();
			for(TipoAccion ta: tipos){
				ta.mostrar();
			}
			System.out.println("Introduce tipo acción");
			String tipo = t.nextLine();
			TipoAccion ta = bd.obtenerTipoAcccion(tipo);
			if(ta==null) {
				System.out.println("Error, el tipo de acción no existe");
			}
			else {
				//Mostrar jugadores de los equipos
				System.out.println("Jugadores Equipo Local");
				for(Jugador j:partidoSel.getLocal().getJugadores()) {
					j.mostrar();
				}
				System.out.println("Jugadores Equipo Visitante");
				for(Jugador j:partidoSel.getVisitante().getJugadores()) {
					j.mostrar();
				}
				System.out.println("Introduce código de jugador");
				int codigo = t.nextInt(); t.nextLine();
				
				Jugador j = bd.obtenerJugador(codigo);
				//Comprobamos que el jugador sea de uno de los equipos que juega el partido
				if(j!=null && (j.getEquipo()==partidoSel.getLocal() || j.getEquipo()==partidoSel.getVisitante())) {
					if(!bd.registrarAccion(ta,partidoSel,j)) {
						System.out.println("Error al registrar la acción");
					}
				}
				else {
					System.out.println("El jugador no pertenece a los equipos del partido");
				}
			}
		}
	}

	private static void seleccionarPartido() {
		// TODO Auto-generated method stub
		List<Partido> partidos = bd.obtenerPartidos();
		for(Partido p:partidos) {
			p.mostrar();
		}
		System.out.println("Introduce código");
		int codigo = t.nextInt();t.nextLine();
		partidoSel = bd.obtenerPartido(codigo);
		if(partidoSel==null) {
			System.out.println("Error al seleccionar el partido");
		}
		else {
			System.out.println("Partido Seleccionado:");
			partidoSel.mostrar();
		}
	}

}
