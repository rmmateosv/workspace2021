package IES;

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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GeneratorType;
@Entity
@Table(name="alumnos")
public class Alumnos implements Serializable{
	@Column(nullable = false)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable = false)
	private String curso; 
	@Column(unique = true, nullable = false)
	private String nif;
	@Column(nullable = false)
	private String nombre;
	
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "alumno")	
	private Direccion direccion;
	
	//Relación 1 a muchos entre alumno y nota
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "id.alumno")
	private List<Notas> notas = new ArrayList();
	
	
	public List<Notas> getNotas() {
		return notas;
	}

	public void setNotas(List<Notas> notas) {
		this.notas = notas;
	}
	
	

	public Direccion getDireccion() {
		return direccion;
	}

	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}

	public void mostrar() {
		System.out.print("Alumno:" + id + 
				"\tCurso:"+curso + 
				"\tNif:"+nif+
				"\tnombre:"+nombre);
		if(direccion!=null) {
			System.out.println("\tCalle:" + direccion.getCalle() + 
					"\tCP:"+direccion.getCp());
		}
		else {
			System.out.println("");
		}
		System.out.println("Notas del alumno");
		for(Notas n: notas) {
			System.out.println("Asignatura:"+n.getId().getAsig().getNombreL()+
					"\tNota:"+n.getNota());
		}
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
