package ligaB;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
public class Accion implements Serializable{
	@Id
	@Column(nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int codigo;
	@ManyToOne
	@JoinColumn(name="partido",referencedColumnName = "codigo")
	private Partido partido;
	@ManyToOne
	@JoinColumn(name="tipo",referencedColumnName = "tipo")
	private TipoAccion tipo;
	@ManyToOne
	@JoinColumn(name="jugador",referencedColumnName = "codigo")
	private Jugador jugador;
	@Column(nullable = false)
	private boolean anulada;
	
	public void mostrar() {
		System.out.println("Codigo:" + codigo + 
				"\tPartido:"+partido.getCodigo() + 
				"\tJugador:"+jugador.getNombre() + 
				"\ttipoAcccion:"+tipo.getDescrip()+
				"\tAnulada:"+anulada);
	}
	public Accion(int codigo, Partido partido, TipoAccion tipo, Jugador jugador, boolean anulada) {
		super();
		this.codigo = codigo;
		this.partido = partido;
		this.tipo = tipo;
		this.jugador = jugador;
		this.anulada = anulada;
	}
	public Accion() {
		super();
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public Partido getPartido() {
		return partido;
	}
	public void setPartido(Partido partido) {
		this.partido = partido;
	}
	public TipoAccion getTipo() {
		return tipo;
	}
	public void setTipo(TipoAccion tipo) {
		this.tipo = tipo;
	}
	public Jugador getJugador() {
		return jugador;
	}
	public void setJugador(Jugador jugador) {
		this.jugador = jugador;
	}
	public boolean isAnulada() {
		return anulada;
	}
	public void setAnulada(boolean anulada) {
		this.anulada = anulada;
	}
	
	
	
}
