package biblioteca;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Libro implements Serializable{
	
	@Id
	@Column(nullable = false)
	private String isbn;
	@Column(nullable = false)
	private int numEjemplares;
	@Column(nullable = false)
	private String titulo;
	
	//Lista de préstamos
	
	
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
	
	
}
