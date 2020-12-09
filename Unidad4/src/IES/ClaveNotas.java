package IES;

import java.io.Serializable;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


public class ClaveNotas implements Serializable{
	@ManyToOne
	@JoinColumn(referencedColumnName = "id")
	private Alumnos alumno;
	@ManyToOne
	@JoinColumn(referencedColumnName = "nombreC")
	private Asignaturas asig;
	
	public ClaveNotas() {
		super();
	}
	public ClaveNotas(Alumnos alumno, Asignaturas asig) {
		super();
		this.alumno = alumno;
		this.asig = asig;
	}
	public Alumnos getAlumno() {
		return alumno;
	}
	public void setAlumno(Alumnos alumno) {
		this.alumno = alumno;
	}
	public Asignaturas getAsig() {
		return asig;
	}
	public void setAsig(Asignaturas asig) {
		this.asig = asig;
	}
	
	
}
