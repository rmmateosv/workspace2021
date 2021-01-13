package Vista;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Cotrolador.accionBotones;

public class DatosSocio extends JPanel{
	private JTextField id, nif, nombre, fechaSancion;

	public DatosSocio(accionBotones acciones) {
		super();
		
		JLabel lId = new JLabel("Id");
		this.add(lId);
		
		id = new JTextField();
		id.setColumns(10);
		id.setText("");
		id.setEnabled(false);
		this.add(id);
		
		JLabel lNif = new JLabel("NIF");
		this.add(lNif);
		
		nif = new JTextField();
		nif.setColumns(9);
		nif.setText("");
		this.add(nif);
		
		JLabel lNombre = new JLabel("Nombre");
		this.add(lNombre);
		
		nombre= new JTextField();
		nombre.setColumns(50);
		nombre.setText("");
		this.add(nombre);
		
		JLabel lFechaSancion = new JLabel("Fecha Sanción (yyyy-mm-dd)");
		this.add(lFechaSancion);
		
		fechaSancion= new JTextField();
		fechaSancion.setColumns(10);
		fechaSancion.setEnabled(false);
		this.add(fechaSancion);
		
		//Botón LimpiarL
		JButton bLimpiarS = new JButton();
		bLimpiarS.setText("Limpiar");
		bLimpiarS.setActionCommand("limpiarS");
		bLimpiarS.addActionListener(acciones);
		this.add(bLimpiarS);
		//Botón Insertar
		JButton bInertarS = new JButton();
		bInertarS.setText("Insertar");
		bInertarS.setActionCommand("insertarS");
		bInertarS.addActionListener(acciones);
		this.add(bInertarS);
		//Botón Modificar
		JButton bModificarS = new JButton();
		bModificarS.setText("Modificar");
		bModificarS.setActionCommand("modificarS");
		bModificarS.addActionListener(acciones);
		this.add(bModificarS);
		//Botón Borrar
		JButton bBorrarS = new JButton();
		bBorrarS.setText("Borrar");
		bBorrarS.setActionCommand("borrarS");
		bBorrarS.addActionListener(acciones);
		this.add(bBorrarS);
		// TODO Auto-generated constructor stub
	}

	public JTextField getId() {
		return id;
	}

	public void setId(JTextField id) {
		this.id = id;
	}

	public JTextField getNif() {
		return nif;
	}

	public void setNif(JTextField nif) {
		this.nif = nif;
	}

	public JTextField getNombre() {
		return nombre;
	}

	public void setNombre(JTextField nombre) {
		this.nombre = nombre;
	}

	public JTextField getFechaSancion() {
		return fechaSancion;
	}

	public void setFechaSancion(JTextField fechaSancion) {
		this.fechaSancion = fechaSancion;
	}

	
	
	
}
