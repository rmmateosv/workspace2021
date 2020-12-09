package IES;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table
public class Notas implements Serializable{
	@EmbeddedId
	private ClaveNotas id;
	@Column(nullable = false )
	private int nota;
	
	public void mostrar() {
		System.out.println("Alumno:" + id.getAlumno().getId() + "-" + id.getAlumno().getNombre()+
				"\tAsignatura:"+ id.getAsig().getNombreL() +
				"\tNota:"+nota);		
	}
	
	public Notas() {
		super();
	}

	public Notas(ClaveNotas id, int nota) {
		super();
		this.id = id;
		this.nota = nota;
	}

	public ClaveNotas getId() {
		return id;
	}

	public void setId(ClaveNotas id) {
		this.id = id;
	}

	public int getNota() {
		return nota;
	}

	public void setNota(int nota) {
		this.nota = nota;
	}

	
}
