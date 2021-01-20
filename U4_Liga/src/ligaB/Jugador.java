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
public class Jugador implements Serializable{
	@Id
	@Column(nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int codigo;
	@ManyToOne
	@JoinColumn(name="equipo", referencedColumnName = "nombre")
	private Equipo equipo;
	@Column(nullable = false)
	private  int dorsal;
	@Column(nullable = false)
	private String nombre;
	@Column(nullable = false)
	private  String tipo;
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "jugador")
	private List<Accion> acciones = new ArrayList<>();

	
	public void mostrar() {
		System.out.println("Codigo:" + codigo+
				"\tEquipo:" + equipo.getNombre()+
				"\tDorsal:" +  dorsal +
				"\tNombre:" + nombre + 
				"\tTipo:"+ tipo);
	}
	public Jugador() {
		super();
	}

	public Jugador(int codigo, Equipo equipo, int dorsal, String nombre, String tipo, List<Accion> acciones) {
		super();
		this.codigo = codigo;
		this.equipo = equipo;
		this.dorsal = dorsal;
		this.nombre = nombre;
		this.tipo = tipo;
		this.acciones = acciones;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public Equipo getEquipo() {
		return equipo;
	}

	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}

	public int getDorsal() {
		return dorsal;
	}

	public void setDorsal(int dorsal) {
		this.dorsal = dorsal;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public List<Accion> getAcciones() {
		return acciones;
	}

	public void setAcciones(List<Accion> acciones) {
		this.acciones = acciones;
	}
	
	
}
