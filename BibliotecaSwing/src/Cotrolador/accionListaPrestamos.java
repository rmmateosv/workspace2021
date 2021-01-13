package Cotrolador;

import java.awt.CardLayout;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Vista.DatosPrestamo;
import Vista.Inicio;
import biblioteca.Modelo;
import biblioteca.Prestamo;

public class accionListaPrestamos implements ListSelectionListener{
	private Modelo bibilioteca; 
	Inicio ventana;

	public accionListaPrestamos(Modelo m) {
		super();
		// TODO Auto-generated constructor stub
		bibilioteca = m;
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub
		//Obtenemos el layout sobre el que se está trabajando
		CardLayout layout = (CardLayout) ventana.getCentro().getLayout();
		JTable tPrestamos = ventana.getCentro().getvPrestamos().gettPrestamos();
		DatosPrestamo datosP  = ventana.getCentro().getvPrestamos().getDatosP();
		
		if(!tPrestamos.getSelectionModel().isSelectionEmpty()){
			//REcuperamos el código del libro seleccionado
			String fechaRD;
			if(tPrestamos.getModel().getValueAt(tPrestamos.getSelectedRow(), 7) == null) {
				fechaRD = "";
			}
			else {
				fechaRD = tPrestamos.getModel().getValueAt(tPrestamos.getSelectedRow(), 7).toString();
			}
			datosP.getT_isbn().setText(tPrestamos.getModel().getValueAt(tPrestamos.getSelectedRow(), 0).toString());
			datosP.getTitulo().setText(tPrestamos.getModel().getValueAt(tPrestamos.getSelectedRow(), 1).toString());
			datosP.getFechaP().setText(tPrestamos.getModel().getValueAt(tPrestamos.getSelectedRow(), 5).toString());
			datosP.getFechaDevolPrevista().setText(tPrestamos.getModel().getValueAt(tPrestamos.getSelectedRow(), 6).toString());
			datosP.getFechaDevolReal().setText(fechaRD);
			
		}
	}

	public Modelo getBibilioteca() {
		return bibilioteca;
	}

	public void setBibilioteca(Modelo bibilioteca) {
		this.bibilioteca = bibilioteca;
	}

	public Inicio getVentana() {
		return ventana;
	}

	public void setVentana(Inicio ventana) {
		this.ventana = ventana;
	}

}
