

import Cotrolador.accionBotones;
import Cotrolador.accionListaPrestamos;
import Vista.Inicio;
import biblioteca.Modelo;

public class Principal {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Ventana de la aplicación
		Inicio ventana=null;
		
		//Componente de acceso y manipulación a la BD Biblioteca
		Modelo biblioteca = new Modelo();
		
		if(biblioteca.getEm()!=null) {
			//Controlador de acciones de botones
			accionBotones acciones = new accionBotones(biblioteca);
			accionListaPrestamos accionesP = new accionListaPrestamos(biblioteca);
			// Apertura de la ventana principal
			ventana = new Inicio(acciones, accionesP);
			acciones.setVentana(ventana);
			accionesP.setVentana(ventana);
			System.out.println("");
		}
		
	}

}
