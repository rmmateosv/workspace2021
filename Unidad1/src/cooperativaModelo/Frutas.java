package cooperativaModelo;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Frutas implements Serializable{
	private int codigo;
	private String nombre;
	private Date fechaIT;
	private int numAlmacen;
	public Frutas() {
		super();
	}
	
	public void mostrar() {
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		System.out.println("Código:"+codigo + 
				"\tNombre:" + nombre+
				"\tFechaIT:" + formato.format(fechaIT)+
				"\tNumAlmacen:" + numAlmacen);
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Date getFechaIT() {
		return fechaIT;
	}
	public void setFechaIT(Date fechaIT) {
		this.fechaIT = fechaIT;
	}
	public int getNumAlmacen() {
		return numAlmacen;
	}
	public void setNumAlmacen(int numAlmacen) {
		this.numAlmacen = numAlmacen;
	}

	
	
	
}
