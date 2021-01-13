package Vista;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.ListDataListener;

import Cotrolador.accionBotones;
import biblioteca.Libro;
import biblioteca.Socio;

public class DatosPrestamo extends JPanel{
	private JComboBox isbn;
	private JComboBox nif;
	private JTextField  t_isbn, titulo, fechaP, fechaDevolPrevista, fechaDevolReal;

	public DatosPrestamo(accionBotones acciones) {
		super();
		
		
		JLabel lNif = new JLabel("NIF");
		this.add(lNif);
		
		nif= new  JComboBox();
		nif.addActionListener(acciones);
		nif.setActionCommand("cambiarSocio");
		this.add(nif);
		
		JLabel lLibro = new JLabel("Datos libro");
		this.add(lLibro);
		
		t_isbn = new JTextField();
		t_isbn.setColumns(50);
		t_isbn.setEnabled(false);
		this.add(t_isbn);
		titulo = new JTextField();
		titulo.setColumns(50);
		titulo.setEnabled(false);
		this.add(titulo);
		
		JLabel lFechaPrestamo = new JLabel("Fecha Préstamo");
		this.add(lFechaPrestamo);
		
		fechaP= new JTextField();
		fechaP.setColumns(10);
		fechaP.setEnabled(false);
		this.add(fechaP);
		
		JLabel lFechaPrevistaDevol = new JLabel("Fecha Prevista Devolución");
		this.add(lFechaPrevistaDevol);
		
		fechaDevolPrevista= new JTextField();
		fechaDevolPrevista.setColumns(10);
		fechaDevolPrevista.setEnabled(false);
		this.add(fechaDevolPrevista);
		
		JLabel lFechaRealDevol = new JLabel("Fecha Real Devolución");
		this.add(lFechaRealDevol);
		
		fechaDevolReal= new JTextField();
		fechaDevolReal.setColumns(10);
		fechaDevolReal.setEnabled(false);
		this.add(fechaDevolReal);
		
		//Botón Devolver
		JButton bModificarP = new JButton();
		bModificarP.setText("Devolver");
		bModificarP.setActionCommand("devolverP");
		bModificarP.addActionListener(acciones);
		this.add(bModificarP);
		
		
		JLabel lIsbn = new JLabel("Isbn");
		this.add(lIsbn);
		
		isbn= new  JComboBox();
		this.add(isbn);
		
		//Botón Crear Préstamo
		JButton bInertarP = new JButton();
		bInertarP.setText("Prestar");
		bInertarP.setActionCommand("insertarP");
		bInertarP.addActionListener(acciones);
		this.add(bInertarP);
		
		
		// TODO Auto-generated constructor stub
	}

	public JComboBox getIsbn() {
		return isbn;
	}

	public void setIsbn(JComboBox isbn) {
		this.isbn = isbn;
	}

	public JComboBox getNif() {
		return nif;
	}

	public void setNif(JComboBox nif) {
		this.nif = nif;
	}

	

	public JTextField getT_isbn() {
		return t_isbn;
	}

	public void setT_isbn(JTextField t_isbn) {
		this.t_isbn = t_isbn;
	}

	public JTextField getTitulo() {
		return titulo;
	}

	public void setTitulo(JTextField titulo) {
		this.titulo = titulo;
	}

	public JTextField getFechaP() {
		return fechaP;
	}

	public void setFechaP(JTextField fechaP) {
		this.fechaP = fechaP;
	}

	public JTextField getFechaDevolPrevista() {
		return fechaDevolPrevista;
	}

	public void setFechaDevolPrevista(JTextField fechaDevolPrevista) {
		this.fechaDevolPrevista = fechaDevolPrevista;
	}

	public JTextField getFechaDevolReal() {
		return fechaDevolReal;
	}

	public void setFechaDevolReal(JTextField fechaDevolReal) {
		this.fechaDevolReal = fechaDevolReal;
	}


	
	
	
	
}
