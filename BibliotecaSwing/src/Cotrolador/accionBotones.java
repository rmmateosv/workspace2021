package Cotrolador;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Vista.DatosPrestamo;
import Vista.Inicio;
import biblioteca.Libro;
import biblioteca.Modelo;
import biblioteca.Prestamo;
import biblioteca.Socio;

public class accionBotones implements ActionListener {

	Modelo biblioteca;
	Inicio ventana;

	public accionBotones(Modelo m) {
		super();
		this.biblioteca = m;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		// Obtener códgio de acción
		String accion = e.getActionCommand();

		// Obtenemos el layout sobre el que se está trabajando
		CardLayout layout = (CardLayout) ventana.getCentro().getLayout();
		Libro l;
		Socio s;
		Prestamo p;
		List<Libro> libros;
		List<Socio> socios;
		List<Prestamo> prestamos;
		switch (accion) {
		case "salir":
			biblioteca.cerrar();
			System.exit(0);
		//Panel libros
		case "libros":
			libros = biblioteca.obtenerLibros();
			ventana.getCentro().getvLibros().rellenarTablaLibros(libros);
			layout.show(ventana.getCentro(), "libros");
			break;
		case "limpiarL":
			ventana.getCentro().getvLibros().getDatosL().getIsbn().setText("");
			ventana.getCentro().getvLibros().getDatosL().getTitulo().setText("");
			ventana.getCentro().getvLibros().getDatosL().getNumEjem().setText("");
			break;
		case "insertarL":
			// Chequeamos que los camos están rellenos
			if (!ventana.getCentro().getvLibros().getDatosL().getIsbn().getText().toString().equals("")
					&& !ventana.getCentro().getvLibros().getDatosL().getTitulo().getText().toString().equals("")
					&& !ventana.getCentro().getvLibros().getDatosL().getNumEjem().getText().toString().equals("")) {

				l = new Libro();
				l.setIsbn(ventana.getCentro().getvLibros().getDatosL().getIsbn().getText().toString());
				l = biblioteca.obtenerLibro(l);
				if (l == null) {
					l = new Libro();
					l.setIsbn(ventana.getCentro().getvLibros().getDatosL().getIsbn().getText().toString());
					l.setTitulo(ventana.getCentro().getvLibros().getDatosL().getTitulo().getText().toString());
					l.setNumEjemplares(Integer
							.parseInt(ventana.getCentro().getvLibros().getDatosL().getNumEjem().getText().toString()));

					if (biblioteca.altaLibro(l)) {
						JOptionPane.showMessageDialog(null, "Libro Insertado");
						// Limpiamos campos
						ventana.getCentro().getvLibros().getDatosL().getIsbn().setText("");
						ventana.getCentro().getvLibros().getDatosL().getTitulo().setText("");
						ventana.getCentro().getvLibros().getDatosL().getNumEjem().setText("");
						// Actualizamos tabla
						libros = biblioteca.obtenerLibros();
						ventana.getCentro().getvLibros().rellenarTablaLibros(libros);
					} else {
						JOptionPane.showMessageDialog(null, "Error al insertar el libro");
					}
				} else {
					JOptionPane.showMessageDialog(null, "Error, ya existe el libro");
				}

			} else {
				JOptionPane.showMessageDialog(null, "Rellena todos los campos");
			}
			break;
		case "modificarL":
			l = new Libro();
			l.setIsbn(ventana.getCentro().getvLibros().getDatosL().getIsbn().getText().toString());
			l = biblioteca.obtenerLibro(l);
			if (l != null) {
				l.setTitulo(ventana.getCentro().getvLibros().getDatosL().getTitulo().getText().toString());
				l.setNumEjemplares(Integer
						.parseInt(ventana.getCentro().getvLibros().getDatosL().getNumEjem().getText().toString()));

				if (biblioteca.modificarLibro(l)) {
					JOptionPane.showMessageDialog(null, "Libro Modificado");
					// Limpiamos campos
					ventana.getCentro().getvLibros().getDatosL().getIsbn().setText("");
					ventana.getCentro().getvLibros().getDatosL().getTitulo().setText("");
					ventana.getCentro().getvLibros().getDatosL().getNumEjem().setText("");
					// Actualizamos tabla
					libros = biblioteca.obtenerLibros();
					ventana.getCentro().getvLibros().rellenarTablaLibros(libros);
				} else {
					JOptionPane.showMessageDialog(null, "Error al modificar el libro");
				}
			} else {
				JOptionPane.showMessageDialog(null, "Error, no existe libro");
			}
			break;
		case "borrarL":
			l = new Libro();
			l.setIsbn(ventana.getCentro().getvLibros().getDatosL().getIsbn().getText().toString());
			l = biblioteca.obtenerLibro(l);
			if (l != null) {
				if (l.getPrestamos().size() == 0) {
					if (biblioteca.borrarLibro(l)) {
						JOptionPane.showMessageDialog(null, "Libro Borrado");
						// Limpiamos campos
						ventana.getCentro().getvLibros().getDatosL().getIsbn().setText("");
						ventana.getCentro().getvLibros().getDatosL().getTitulo().setText("");
						ventana.getCentro().getvLibros().getDatosL().getNumEjem().setText("");
						// Actualizamos tabla
						libros = biblioteca.obtenerLibros();
						ventana.getCentro().getvLibros().rellenarTablaLibros(libros);
					} else {
						JOptionPane.showMessageDialog(null, "Error al borrar el libro");
					}
				} else {
					JOptionPane.showMessageDialog(null, "Error,el libro tiene préstamos");
				}
			} else {
				JOptionPane.showMessageDialog(null, "Error, no existe libro");
			}
			break;
		case "socios":
			socios = biblioteca.obtenerSocios();
			ventana.getCentro().getvSocios().rellenarTablaSocios(socios);
			layout.show(ventana.getCentro(), "socios");
			break;
		case "limpiarS":
			ventana.getCentro().getvSocios().getDatosS().getId().setText("");
			ventana.getCentro().getvSocios().getDatosS().getNif().setText("");
			ventana.getCentro().getvSocios().getDatosS().getNombre().setText("");
			ventana.getCentro().getvSocios().getDatosS().getFechaSancion().setText("");
			break;
		case "insertarS":
			// Chequear datos rellenos
			if (!ventana.getCentro().getvSocios().getDatosS().getNif().getText().toString().equals("")
					&& !ventana.getCentro().getvSocios().getDatosS().getNombre().getText().toString().equals("")) {
				s = new Socio();
				s.setNif(ventana.getCentro().getvSocios().getDatosS().getNif().getText().toString());
				s = biblioteca.obtenerSocio(s);
				if (s == null) {
					s = new Socio();
					s.setNif(ventana.getCentro().getvSocios().getDatosS().getNif().getText().toString());
					s.setNombre(ventana.getCentro().getvSocios().getDatosS().getNombre().getText().toString());
					if (biblioteca.crearSocio(s)) {
						JOptionPane.showMessageDialog(null, "Socio Insertado");
						// Limpiamos campos
						ventana.getCentro().getvSocios().getDatosS().getId().setText("");
						ventana.getCentro().getvSocios().getDatosS().getNif().setText("");
						ventana.getCentro().getvSocios().getDatosS().getNombre().setText("");
						ventana.getCentro().getvSocios().getDatosS().getFechaSancion().setText("");
						// Actualizamos tabla
						socios = biblioteca.obtenerSocios();
						ventana.getCentro().getvSocios().rellenarTablaSocios(socios);
					} else {
						JOptionPane.showMessageDialog(null, "Error al insertar el socio");
					}
				} else {
					JOptionPane.showMessageDialog(null, "Error ya existe un socio con ese nif");
				}
			} else {
				JOptionPane.showMessageDialog(null, "Debes rellenar datos");
			}
			break;
		case "modificarS":
			s = new Socio();
			s.setNif(ventana.getCentro().getvSocios().getDatosS().getNif().getText().toString());
			s = biblioteca.obtenerSocio(s);
			if (s != null) {
				s.setNombre(ventana.getCentro().getvSocios().getDatosS().getNombre().getText().toString());
				if (biblioteca.modificarSocio(s)) {
					JOptionPane.showMessageDialog(null, "Socio Modificado");
					// Limpiamos campos
					ventana.getCentro().getvSocios().getDatosS().getId().setText("");
					ventana.getCentro().getvSocios().getDatosS().getNombre().setText("");
					ventana.getCentro().getvSocios().getDatosS().getNif().setText("");
					ventana.getCentro().getvSocios().getDatosS().getFechaSancion().setText("");
					// Actualizamos tabla
					socios = biblioteca.obtenerSocios();
					ventana.getCentro().getvSocios().rellenarTablaSocios(socios);
				} else {
					JOptionPane.showMessageDialog(null, "Error al modificar el socio");
				}
			} else {
				JOptionPane.showMessageDialog(null, "Error, no existe socio");
			}
			break;
		case "borrarS":
			s = new Socio();
			s.setNif(ventana.getCentro().getvSocios().getDatosS().getNif().getText().toString());
			s = biblioteca.obtenerSocio(s);
			if (s != null) {
				if (s.getPrestamos().size() == 0) {
					if (biblioteca.borrarSocio(s)) {
						JOptionPane.showMessageDialog(null, "Socio Borrado");
						// Limpiamos campos
						ventana.getCentro().getvSocios().getDatosS().getId().setText("");
						ventana.getCentro().getvSocios().getDatosS().getNombre().setText("");
						ventana.getCentro().getvSocios().getDatosS().getNif().setText("");
						ventana.getCentro().getvSocios().getDatosS().getFechaSancion().setText("");
						// Actualizamos tabla
						socios = biblioteca.obtenerSocios();
						ventana.getCentro().getvSocios().rellenarTablaSocios(socios);
					} else {
						JOptionPane.showMessageDialog(null, "Error al borrar el socio");
					}
				} else {
					JOptionPane.showMessageDialog(null, "Error,el socio tiene préstamos");
				}
			} else {
				JOptionPane.showMessageDialog(null, "Error, no existe Socio");
			}
			break;
		case "prestamos":
			socios = biblioteca.obtenerSocios();
			libros = biblioteca.obtenerLibros();
			ventana.getCentro().getvPrestamos().rellenarComboSocios(socios);
			s = biblioteca
					.obtenerSocio((Socio) ventana.getCentro().getvPrestamos().getDatosP().getNif().getSelectedItem());
			ventana.getCentro().getvPrestamos().rellenarTablaPrestamos(s.getPrestamos());
			ventana.getCentro().getvPrestamos().rellenarComboIsbn(libros);
			layout.show(ventana.getCentro(), "prestamos");
			break;
		case "cambiarSocio":
			if (ventana.getCentro().getvPrestamos().getDatosP().getNif().getSelectedItem() != null) {
				s = biblioteca.obtenerSocio(
						(Socio) ventana.getCentro().getvPrestamos().getDatosP().getNif().getSelectedItem());
				ventana.getCentro().getvPrestamos().rellenarTablaPrestamos(s.getPrestamos());
			}
			break;
		case "insertarP":
			s = biblioteca
					.obtenerSocio((Socio) ventana.getCentro().getvPrestamos().getDatosP().getNif().getSelectedItem());
			l = biblioteca
					.obtenerLibro((Libro) ventana.getCentro().getvPrestamos().getDatosP().getIsbn().getSelectedItem());
			if (s.getFechaSancion() == null || s.getFechaSancion().getTime() < new Date().getTime()) {
				long numPrestamosPtes = biblioteca.obtenerPrestPtes(s);
				if (numPrestamosPtes <= 1) {
					if (l.getNumEjemplares() > 0) {
						if (biblioteca.crearPrestamo(s, l)) {
							s = biblioteca.obtenerSocio(s);
							ventana.getCentro().getvPrestamos().rellenarTablaPrestamos(s.getPrestamos());
							JOptionPane.showMessageDialog(null, "Préstamos registrado");
						} else {
							JOptionPane.showMessageDialog(null, "Error al crear el préstamo");
						}
					} else {
						JOptionPane.showMessageDialog(null, "Error, no hay ejemplares");
					}
				} else {
					JOptionPane.showMessageDialog(null,
							"Error, el socio tiene más al menos dos préstamos pentdientes de devolución");
				}
			} else {
				JOptionPane.showMessageDialog(null, "Error el socio está sancionado");
			}

			break;
		case "devolverP":
			s = biblioteca
					.obtenerSocio((Socio) ventana.getCentro().getvPrestamos().getDatosP().getNif().getSelectedItem());
			DatosPrestamo datosP = ventana.getCentro().getvPrestamos().getDatosP();
			for (Prestamo p1 : s.getPrestamos()) {
				if (p1.getId().getLibro().getIsbn().contentEquals(datosP.getT_isbn().getText())
						&& p1.getId().getFechaP().toString().contentEquals(datosP.getFechaP().getText())
						&& p1.getFechaDevolReal() == null) {
					if (biblioteca.devolverPrestamo(p1)) {
						JOptionPane.showMessageDialog(null, "Préstamo devuelto");
						s = biblioteca.obtenerSocio(s);
						ventana.getCentro().getvPrestamos().rellenarTablaPrestamos(s.getPrestamos());
					} else {
						JOptionPane.showMessageDialog(null, "Error al devolver el préstamo");
					}
					break;
				}
			}
			break;
		}
	}

	public Modelo getBiblioteca() {
		return biblioteca;
	}

	public void setBiblioteca(Modelo biblioteca) {
		this.biblioteca = biblioteca;
	}

	public Inicio getVentana() {
		return ventana;
	}

	public void setVentana(Inicio ventana) {
		this.ventana = ventana;
	}

}
