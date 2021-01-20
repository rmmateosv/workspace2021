package ligaB;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
@Entity
@Table
public class Partido implements Serializable{
	@Id
	@Column(nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int codigo;
	@ManyToOne
	@JoinColumn(name="local", referencedColumnName = "nombre")
	private Equipo local;
	@ManyToOne
	@JoinColumn(name="visitante", referencedColumnName = "nombre")
	private Equipo visitante;
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "partido")
	private List<Accion> acciones = new  ArrayList();
	
	
	public void mostrar() {
		System.out.println("Código:" + codigo +
				"\tEquipo Local:" + local.getNombre() + 
				"\tEquipo Visitante:" + visitante.getNombre());
	}
	public Partido(int codigo, Equipo local, Equipo visitante, List<Accion> acciones) {
		super();
		this.codigo = codigo;
		this.local = local;
		this.visitante = visitante;
		this.acciones = acciones;
	}
	public Partido() {
		super();
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public Equipo getLocal() {
		return local;
	}
	public void setLocal(Equipo local) {
		this.local = local;
	}
	public Equipo getVisitante() {
		return visitante;
	}
	public void setVisitante(Equipo visitante) {
		this.visitante = visitante;
	}
	public List<Accion> getAcciones() {
		return acciones;
	}
	public void setAcciones(List<Accion> acciones) {
		this.acciones = acciones;
	}
	
	
}
