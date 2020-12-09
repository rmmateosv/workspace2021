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
@Table(name="alumnos")
public class Alumnos implements Serializable{
	@Column(nullable = false)
	@Id
	private int id;
	@Column(nullable = false)
	private String curso; 
	@Column(unique = true, nullable = false)
	private String nif;
	@Column(nullable = false)
	private String nombre;
	
	//Relaci�n 1 a muchos entre alumno y nota
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "id.alumno")
	private List<Notas> notas = new ArrayList();
	
	
	public List<Notas> getNotas() {
		return notas;
	}

	public void setNotas(List<Notas> notas) {
		this.notas = notas;
	}

	public void mostrar() {
		System.out.println("Alumno:" + id + 
				"\tCurso:"+curso + 
				"\tNif:"+nif+
				"\tnombre"+nombre);
	}
	
	public Alumnos() {
		super();
	}
	
	public Alumnos(int id, String curso, String nif, String nombre) {
		super();
		this.id = id;
		this.curso = curso;
		this.nif = nif;
		this.nombre = nombre;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCurso() {
		return curso;
	}
	public void setCurso(String curso) {
		this.curso = curso;
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
	
	
	
}
