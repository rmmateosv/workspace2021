package Vista;

import java.awt.CardLayout;

import javax.swing.JPanel;

import Cotrolador.accionBotones;
import Cotrolador.accionListaPrestamos;
import biblioteca.Modelo;

public class Centro extends JPanel{
	private PanelPortada vPortada;
	private PanelPrestamos vPrestamos;
	private PanelSocios vSocios;
	private PanelLibros vLibros;
	public Centro(accionBotones acciones, accionListaPrestamos accionesP) {
		super();
		// TODO Auto-generated constructor stub
		//CardLayout: Permite tener varios JPanel y viusalizar sólo 1
		this.setLayout(new CardLayout(20,20));
		//Portada
		vPortada = new PanelPortada();
		this.add(vPortada);
		//Libros
		vLibros = new PanelLibros(acciones);
		this.add(vLibros,"libros");	
		//Prestamos
		vPrestamos = new PanelPrestamos(acciones, accionesP);
		this.add(vPrestamos,"prestamos");
		//Socios
		vSocios = new PanelSocios(acciones);
		this.add(vSocios,"socios");
			
	}
	public PanelPrestamos getvPrestamos() {
		return vPrestamos;
	}
	public void setvPrestamos(PanelPrestamos vPrestamos) {
		this.vPrestamos = vPrestamos;
	}
	public PanelSocios getvSocios() {
		return vSocios;
	}
	public void setvSocios(PanelSocios vSocios) {
		this.vSocios = vSocios;
	}
	public PanelLibros getvLibros() {
		return vLibros;
	}
	public void setvLibros(PanelLibros vLibros) {
		this.vLibros = vLibros;
	}
	public PanelPortada getvPortada() {
		return vPortada;
	}
	public void setvPortada(PanelPortada vPortada) {
		this.vPortada = vPortada;
	}
	

}
