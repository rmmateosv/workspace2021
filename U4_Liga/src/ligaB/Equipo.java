package ligaB;

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
public class Equipo implements Serializable{
	@Id
	@Column(nullable = false)
	private String nombre;
	@Column(nullable = false)
	private String localidad;
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "local")
	private List<Partido> partidosLocal = new ArrayList();
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "visitante")
	private List<Partido> partidosVisitante = new ArrayList();
	
	public void mostrar() {
		System.out.println("Nombre:" + nombre + "\tLocalidad:" + localidad);
	}
	public Equipo(String nombre, String localidad, List<Partido> partidosLocal, List<Partido> partidosVisitante) {
		super();
		this.nombre = nombre;
		this.localidad = localidad;
		this.partidosLocal = partidosLocal;
		this.partidosVisitante = partidosVisitante;
	}
	public Equipo() {
		super();
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getLocalidad() {
		return localidad;
	}
	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}
	public List<Partido> getPartidosLocal() {
		return partidosLocal;
	}
	public void setPartidosLocal(List<Partido> partidosLocal) {
		this.partidosLocal = partidosLocal;
	}
	public List<Partido> getPartidosVisitante() {
		return partidosVisitante;
	}
	public void setPartidosVisitante(List<Partido> partidosVisitante) {
		this.partidosVisitante = partidosVisitante;
	}
	
	
}
