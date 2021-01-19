package biblioteca;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
	
	//Préstamos de un Socio
	@OneToMany(cascade = CascadeType.PERSIST, mappedBy = "clave.socio")
	private List<Prestamo> prestamos = new ArrayList<Prestamo>();
	
	
	public void mostrar(boolean mostarPrestamos) {
		System.out.println("Id:"+id+
				"\tNIF:" + nif +
				"\tNombre:" + nombre +
				"\tSancionado:"+ sancionado);
		if(mostarPrestamos) {
			System.out.println("Préstamos realizados:");
			for(Prestamo p: prestamos) {
				p.mostrar();
			}
		}
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
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNif() {
		return nif;
	}
	public void setNif(String nif) {
		this.nif = nif;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public boolean isSancionado() {
		return sancionado;
	}
	public void setSancionado(boolean sancionado) {
		this.sancionado = sancionado;
	}
	public List<Prestamo> getPrestamos() {
		return prestamos;
	}
	public void setPrestamos(List<Prestamo> prestamos) {
		this.prestamos = prestamos;
	}
	
	
	
	
}
