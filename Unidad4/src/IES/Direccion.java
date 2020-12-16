package IES;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table
public class Direccion implements Serializable{
	@Column(nullable = false)
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private int id;
	@OneToOne
	@JoinColumn(referencedColumnName = "id", nullable = false)
	private Alumnos alumno;
	@Column(nullable = false)
	private String calle;
	@Column(nullable = false)
	private String cp;
	public Direccion() {
		super();
	}
	public Direccion(int id, Alumnos alumno, String calle, String cp) {
		super();
		this.id = id;
		this.alumno = alumno;
		this.calle = calle;
		this.cp = cp;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Alumnos getAlumno() {
		return alumno;
	}
	public void setAlumno(Alumnos alumno) {
		this.alumno = alumno;
	}
	public String getCalle() {
		return calle;
	}
	public void setCalle(String calle) {
		this.calle = calle;
	}
	public String getCp() {
		return cp;
	}
	public void setCp(String cp) {
		this.cp = cp;
	}
	
	
}
