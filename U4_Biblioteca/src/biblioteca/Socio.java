package biblioteca;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table
public class Socio implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(nullable = false)
	private int id;
	@Column(nullable = false, unique=true)
	private String nif;
	@Column(nullable = false)
	private String nombre;
	@Column(nullable = false)
	private boolean sancionado= false;
	
	
	
	public void mostrar() {
		System.out.println("Id:"+id+
				"\tNIF:" + nif +
				"\tNombre:" + nombre +
				"\tSancionado:"+ sancionado);
	}
	public Socio(int id, String nif, String nombre, boolean sancionado) {
		super();
		this.id = id;
		this.nif = nif;
		this.nombre = nombre;
		this.sancionado = sancionado;
	}
	public Socio() {
		super();
	}
	
	//Lista préstamos 
	
	
}
