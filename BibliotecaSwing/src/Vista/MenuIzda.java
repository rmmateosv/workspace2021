package Vista;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import Cotrolador.accionBotones;
import biblioteca.Principal;

public class MenuIzda extends JPanel{

	public MenuIzda(accionBotones accion) {
		super();
		// TODO Auto-generated constructor stub
		try {
			this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
			setBackground(Color.lightGray);
			//Botón Libros
			JButton bLibros = new JButton();
			bLibros.setIcon(new ImageIcon("img/libros.png","Libros"));
			bLibros.setActionCommand("libros");
			bLibros.addActionListener(accion);
			bLibros.setPreferredSize(new Dimension(100, 100));
			this.add(bLibros);
			
			//Botón Socios
			JButton bSocios = new JButton();
			bSocios.setIcon(new ImageIcon("img/socios.png"));
			bSocios.setActionCommand("socios");
			bSocios.addActionListener(accion);
			
			this.add(bSocios);
			
			JButton bPrestamos = new JButton();
			bPrestamos.setIcon(new ImageIcon("img/prestamo.jpg"));
			bPrestamos.setActionCommand("prestamos");
			bPrestamos.addActionListener(accion);
			
			this.add(bPrestamos);
			
			JButton bSalir = new JButton();
			bSalir.setIcon(new ImageIcon("img/salir.png")); 
			bSalir.setActionCommand("salir");
			bSalir.addActionListener(accion);
			
			this.add(bSalir);
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
	}
	
}
