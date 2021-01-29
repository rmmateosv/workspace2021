package biblioteca;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;



@Entity
@Table
public class Libro implements Serializable{
	
	@Id
	@Column( nullable = false)
	private String isbn;
	@Column(nullable = false)
	private int numEjemplares;
	@Column(nullable = false)
	private String titulo;
	
	//Lista de préstamos
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "clave.libro")
	private  List<Prestamo> prestamos = new ArrayList<>();
	
	
	public void mostrar() {
		System.out.println("Isbn:" + isbn +
				"\tTitulo:" + titulo +
				"\tNumejemplares:" + numEjemplares);
	}
	public Libro(String isbn, int numEjemplares, String titulo) {
		super();
		this.isbn = isbn;
		this.numEjemplares = numEjemplares;
		this.titulo = titulo;
	}
	public Libro() {
		super();
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public int getNumEjemplares() {
		return numEjemplares;
	}
	public void setNumEjemplares(int numEjemplares) {
		this.numEjemplares = numEjemplares;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public List<Prestamo> getPrestamos() {
		return prestamos;
	}
	public void setPrestamos(List<Prestamo> prestamos) {
		this.prestamos = prestamos;
	}
	
	
	
	
}
