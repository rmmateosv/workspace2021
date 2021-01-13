package Vista;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Cotrolador.accionBotones;

public class DatosLibro extends JPanel{
	private JTextField isbn, titulo, numEjem;

	public DatosLibro(accionBotones acciones) {
		super();
		
		JLabel lIsbn = new JLabel("ISBN");
		this.add(lIsbn);
		
		isbn = new JTextField();
		isbn.setColumns(50);
		isbn.setText("");
		this.add(isbn);
		
		JLabel lTitulo = new JLabel("Título");
		this.add(lTitulo);
		
		titulo = new JTextField();
		titulo.setColumns(50);
		titulo.setText("");
		this.add(titulo);
		
		JLabel lEjem = new JLabel("Nº Ejemplares");
		this.add(lEjem);
		
		numEjem= new JTextField();
		numEjem.setColumns(5);
		numEjem.setText("");
		this.add(numEjem);
		
		//Botón LimpiarL
		JButton blimpiarL = new JButton();
		blimpiarL.setText("Limpiar");
		blimpiarL.setActionCommand("limpiarL");
		blimpiarL.addActionListener(acciones);
		this.add(blimpiarL);
		//Botón Insertar
		JButton bInertarL = new JButton();
		bInertarL.setText("Insertar");
		bInertarL.setActionCommand("insertarL");
		bInertarL.addActionListener(acciones);
		this.add(bInertarL);
		//Botón Modificar
		JButton bModificarL = new JButton();
		bModificarL.setText("Modificar");
		bModificarL.setActionCommand("modificarL");
		bModificarL.addActionListener(acciones);
		this.add(bModificarL);
		//Botón Borrar
		JButton bBorrarL = new JButton();
		bBorrarL.setText("Borrar");
		bBorrarL.setActionCommand("borrarL");
		bBorrarL.addActionListener(acciones);
		this.add(bBorrarL);
		// TODO Auto-generated constructor stub
	}

	public JTextField getIsbn() {
		return isbn;
	}

	public void setIsbn(JTextField isbn) {
		this.isbn = isbn;
	}

	public JTextField getTitulo() {
		return titulo;
	}

	public void setTitulo(JTextField titulo) {
		this.titulo = titulo;
	}

	public JTextField getNumEjem() {
		return numEjem;
	}

	public void setNumEjem(JTextField numEjem) {
		this.numEjem = numEjem;
	}
	
	
}
