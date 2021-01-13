package Vista;

import java.awt.BorderLayout;
import java.awt.Font;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import Cotrolador.accionBotones;
import biblioteca.Libro;

public class PanelLibros  extends JPanel{
	private DefaultTableModel modeloLibro;
	private DatosLibro datosL;
	
	public PanelLibros(accionBotones acciones) {
		super();
		// TODO Auto-generated constructor stub
		this.setLayout(new BorderLayout(0,0));
		
		JLabel titulo = new JLabel("Gestion de Libros");
		titulo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(titulo, BorderLayout.NORTH);
		
		datosL = new DatosLibro(acciones);
		this.add(datosL,BorderLayout.CENTER);
		
		JTable tLibros;		
		
		//TAbla con libros - Definición de datos de la tabla
		//Columnas
		String[] columnas = {"ISBN","Título","Nº Ejemplares"};
		//CEldas no editables
		modeloLibro = new DefaultTableModel(columnas, 0) {
			public boolean isCellEditable(int row, int column)
		    	{
			      return false;//This causes all cells to be not editable
			    }

		};
		tLibros =  new JTable(modeloLibro);
		tLibros.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tLibros.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				if(!tLibros.getSelectionModel().isSelectionEmpty()){
					//REcuperamos el código del libro seleccionado
					
					datosL.getIsbn().setText(tLibros.getModel().getValueAt(tLibros.getSelectedRow(), 0).toString());
					datosL.getTitulo().setText(tLibros.getModel().getValueAt(tLibros.getSelectedRow(), 1).toString());
					datosL.getNumEjem().setText(tLibros.getModel().getValueAt(tLibros.getSelectedRow(), 2).toString());
					
				}
			}
		});

		JScrollPane tabla = new JScrollPane(tLibros);
		add(tabla, BorderLayout.SOUTH);

		

	}
	
	public void rellenarTablaLibros(List<Libro> lLibros){
		//Borramos los datos de la tabla libros
		int filas = modeloLibro.getRowCount();
		if(filas>0){
			for(int i=filas-1;i>=0;i--){
				modeloLibro.removeRow(i);
			}
		}
		//modelo.fireTableDataChanged();
		//Rellenamos de nuevo la tabla libros
		
		

		for(int i=0;i<lLibros.size();i++){
			Libro l = lLibros.get(i);
			Object[] fila = {l.getIsbn(),
					l.getTitulo(),
					Integer.toString(l.getNumEjemplares())};
			modeloLibro.addRow(fila);
		}
		

	}

	public DatosLibro getDatosL() {
		return datosL;
	}

	public void setDatosL(DatosLibro datosL) {
		this.datosL = datosL;
	}


}
