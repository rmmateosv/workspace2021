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
import biblioteca.Libro;
import biblioteca.Socio;

public class PanelSocios  extends JPanel{
	private DefaultTableModel modeloSocio;
	private DatosSocio datosS;
	public PanelSocios(accionBotones acciones) {
		super();
		
this.setLayout(new BorderLayout(0,0));
		
		JLabel titulo = new JLabel("Gestion de Socios");
		titulo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(titulo, BorderLayout.NORTH);
		
		datosS = new DatosSocio(acciones);
		this.add(datosS,BorderLayout.CENTER);
		
		JTable tSocios;		
		
		//TAbla con libros - Definición de datos de la tabla
		//Columnas
		String[] columnas = {"Id","NIF","Nombre","Fecha Sanción"};
		//CEldas no editables
		modeloSocio = new DefaultTableModel(columnas, 0) {
			public boolean isCellEditable(int row, int column)
		    	{
			      return false;//This causes all cells to be not editable
			    }

		};
		tSocios =  new JTable(modeloSocio);
		tSocios.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tSocios.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				if(!tSocios.getSelectionModel().isSelectionEmpty()){
					//REcuperamos el código del libro seleccionado
					String fecha;
					if(tSocios.getModel().getValueAt(tSocios.getSelectedRow(), 3) == null) {
						fecha = "";
					}
					else {
						fecha = tSocios.getModel().getValueAt(tSocios.getSelectedRow(), 3).toString();
					}
					datosS.getId().setText(tSocios.getModel().getValueAt(tSocios.getSelectedRow(), 0).toString());
					datosS.getNif().setText(tSocios.getModel().getValueAt(tSocios.getSelectedRow(), 1).toString());
					datosS.getNombre().setText(tSocios.getModel().getValueAt(tSocios.getSelectedRow(), 2).toString());
					datosS.getFechaSancion().setText(fecha);
					
				}
			}
		});

		JScrollPane tabla = new JScrollPane(tSocios);
		add(tabla, BorderLayout.SOUTH);
	}
	public DefaultTableModel getModeloSocio() {
		return modeloSocio;
	}
	public void setModeloSocio(DefaultTableModel modeloSocio) {
		this.modeloSocio = modeloSocio;
	}
	public DatosSocio getDatosS() {
		return datosS;
	}
	public void setDatosS(DatosSocio datosS) {
		this.datosS = datosS;
	}
	
	public void rellenarTablaSocios(List<Socio> lSocios){
		//Borramos los datos de la tabla libros
		int filas = modeloSocio.getRowCount();
		if(filas>0){
			for(int i=filas-1;i>=0;i--){
				modeloSocio.removeRow(i);
			}
		}
		//modelo.fireTableDataChanged();
		//Rellenamos de nuevo la tabla libros
		
		

		for(int i=0;i<lSocios.size();i++){
			Socio s = lSocios.get(i);
			Object[] fila = {s.getId(),
					s.getNif(),
					s.getNombre(),
					s.getFechaSancion()};
			modeloSocio.addRow(fila);
		}
		

	}

}
