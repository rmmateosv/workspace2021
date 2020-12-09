package IES;

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
public class Asignaturas implements Serializable{
	@Column
	@Id
	private String nombreC;
	@Column(nullable = false)
	private String nombreL;
		
	//Relación 1 a muchos entre asignatura y nota
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "id.asig")
	private List<Notas> notas = new ArrayList();
	
	public List<Notas> getNotas() {
		return notas;
	}
	public void setNotas(List<Notas> notas) {
		this.notas = notas;
	}
	public void mostrar() {
		System.out.println("Siglas:" + nombreC +
				"\tNombre"+ nombreL);
	}
	public Asignaturas() {
		super();
	}
	public Asignaturas(String nombreC, String nombreL) {
		super();
		this.nombreC = nombreC;
		this.nombreL = nombreL;
	}
	public String getNombreC() {
		return nombreC;
	}
	public void setNombreC(String nombreC) {
		this.nombreC = nombreC;
	}
	public String getNombreL() {
		return nombreL;
	}
	public void setNombreL(String nombreL) {
		this.nombreL = nombreL;
	}
	
	

}
