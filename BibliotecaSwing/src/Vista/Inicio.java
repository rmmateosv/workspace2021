package Vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;

import Cotrolador.accionBotones;
import Cotrolador.accionListaPrestamos;

public class Inicio extends JFrame{
	
	private Centro centro;
	

	public Inicio(accionBotones acciones, accionListaPrestamos accionesP) throws HeadlessException {
		super();
		this.setTitle("Gestión Biblioteca");
		// TODO Auto-generated constructor stub
		
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		//Establecemos que se hace al cerrar
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		//Creamos un contenedor de tipo BorderLayout
		JPanel contenedor = new JPanel();
		contenedor.setLayout(new BorderLayout());
		this.setContentPane(contenedor);
		
		//Parte superior - Título de la aplicación
		JLabel titulo = new JLabel("BIBLIOTECA");
		titulo.setFont(new Font("Tahoma", Font.PLAIN, 24));
		titulo.setForeground(Color.BLUE);
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		titulo.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		contenedor.add(titulo,BorderLayout.NORTH);
		
		//Parte izquierda - Menú Libros/Socios/Préstamos
		MenuIzda izda = new MenuIzda(acciones);
		contenedor.add(izda,BorderLayout.WEST);
		
		//Parte central - Panel Centro -> CardLayout con todas las ventanas
		centro = new Centro(acciones,accionesP);
		contenedor.add(centro,BorderLayout.CENTER);
		this.setVisible(true);
	}

	



	public Centro getCentro() {
		return centro;
	}

	public void setCentro(Centro centro) {
		this.centro = centro;
	}
	
}
