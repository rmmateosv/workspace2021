package biblioteca;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;



@Entity
@Table
public class Prestamo implements Serializable{
	@EmbeddedId
	private ClavePrestamo clave;
	@Column(nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaDevolPrevista;
	@Column(nullable=true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaDevolReal;
	
	
	
	public void mostrar() {
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yy hh:mm");
		System.out.println("Libro:"+ clave.getLibro().getIsbn() +
				"\tSocio:" + clave.getSocio().getId() + " " + clave.getSocio().getNombre() +
				"\tFecha Préstamo:" + df.format(clave.getFechaP()) +
				"\tFecha Prevista Devolución:"+  df.format(fechaDevolPrevista) +
				"\tFecha Real Devolución:"+ (fechaDevolReal==null?"":df.format(fechaDevolReal)));
	}
	public Prestamo(ClavePrestamo clave, Date fechaDevolPrevista, Date fechaDevolReal) {
		super();
		this.clave = clave;
		this.fechaDevolPrevista = fechaDevolPrevista;
		this.fechaDevolReal = fechaDevolReal;
	}
	public Prestamo() {
		super();
	}
	public ClavePrestamo getClave() {
		return clave;
	}
	public void setClave(ClavePrestamo clave) {
		this.clave = clave;
	}
	public Date getFechaDevolPrevista() {
		return fechaDevolPrevista;
	}
	public void setFechaDevolPrevista(Date fechaDevolPrevista) {
		this.fechaDevolPrevista = fechaDevolPrevista;
	}
	public Date getFechaDevolReal() {
		return fechaDevolReal;
	}
	public void setFechaDevolReal(Date fechaDevolReal) {
		this.fechaDevolReal = fechaDevolReal;
	}
	

	
}
