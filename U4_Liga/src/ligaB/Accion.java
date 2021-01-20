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
	
}
