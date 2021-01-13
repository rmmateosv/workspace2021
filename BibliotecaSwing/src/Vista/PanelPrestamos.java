package Vista;

import java.awt.BorderLayout;
import java.awt.Font;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import Cotrolador.accionBotones;
import Cotrolador.accionListaPrestamos;
import biblioteca.Libro;
import biblioteca.Modelo;
import biblioteca.Prestamo;
import biblioteca.Socio;


public class PanelPrestamos extends JPanel {
	private DefaultTableModel modeloPrestamo;
	private DatosPrestamo datosP;

	
	private JTable tPrestamos;	

	public PanelPrestamos(accionBotones acciones, accionListaPrestamos accionesP) {
		super();
		
		this.setLayout(new BorderLayout(0,0));
		
		JLabel titulo = new JLabel("Gestion de Préstamos");
		titulo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(titulo, BorderLayout.NORTH);
		
		datosP = new DatosPrestamo(acciones);
		this.add(datosP,BorderLayout.CENTER);
		
			
		
		//TAbla con libros - Definición de datos de la tabla
		//Columnas
		String[] columnas = {"Isbn","Título","Id Socio","NIF","Nombre","Fecha Préstamo","Fecha Prevista Devolción","Fecha Real Devolución"};
		//CEldas no editables
		modeloPrestamo = new DefaultTableModel(columnas, 0) {
			public boolean isCellEditable(int row, int column)
		    	{
			      return false;//This causes all cells to be not editable
			    }

		};
		tPrestamos =  new JTable(modeloPrestamo);
		tPrestamos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tPrestamos.getSelectionModel().addListSelectionListener(accionesP);
	
		JScrollPane tabla = new JScrollPane(tPrestamos);
		add(tabla, BorderLayout.SOUTH);
	}
	
	
	
	public DefaultTableModel getModeloPrestamo() {
		return modeloPrestamo;
	}



	public void setModeloPrestamo(DefaultTableModel modeloPrestamo) {
		this.modeloPrestamo = modeloPrestamo;
	}



	public DatosPrestamo getDatosP() {
		return datosP;
	}



	public void setDatosP(DatosPrestamo datosP) {
		this.datosP = datosP;
	}



	public void rellenarTablaPrestamos(List<Prestamo> lPrestamos){
		//Borramos los datos de la tabla libros
		int filas = modeloPrestamo.getRowCount();
		if(filas>0){
			for(int i=filas-1;i>=0;i--){
				modeloPrestamo.removeRow(i);
			}
		}
		//modelo.fireTableDataChanged();
		//Rellenamos de nuevo la tabla libros
		
		

		for(int i=0;i<lPrestamos.size();i++){
			Prestamo p = lPrestamos.get(i);
			Object[] fila = {p.getId().getLibro().getIsbn(),
					p.getId().getLibro().getTitulo(),
					p.getId().getSocio().getId(),
					p.getId().getSocio().getNif(),
					p.getId().getSocio().getNombre(),
					p.getId().getFechaP(),
					p.getFechaDevolPrevista(),
					p.getFechaDevolReal()};
			modeloPrestamo.addRow(fila);
		}
		

	}


	public void rellenarComboSocios(List<Socio> lSocios) {
		datosP.getNif().removeAllItems();
		for(Socio s:lSocios) {
		datosP.getNif().addItem(s);
		}
	}
	
	public void rellenarComboIsbn(List<Libro> lLibros) {
		datosP.getIsbn().removeAllItems();
		for(Libro l:lLibros) {
		datosP.getIsbn().addItem(l);
		}
	}
	public JTable gettPrestamos() {
		return tPrestamos;
	}



	public void settPrestamos(JTable tPrestamos) {
		this.tPrestamos = tPrestamos;
	}


}
