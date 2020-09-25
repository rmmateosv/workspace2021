package cooperativaModelo;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Producto {
	int codigo;
	String nombre;
	int stock;
	float precio;
	long fechaAlta;
	boolean descatalogado;
	
	
	public void mostrar() {
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		System.out.println("Código:"+codigo+
				"\tNombre:" + nombre +
				"\tStock:"+ stock + 
				"\tPrecio:" + precio +
				"\tFecha Alta:"+formato.format(new Date(fechaAlta)) +
				"\tDescatalogado:"+descatalogado);
	}
	public Producto() {
		super();
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
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public float getPrecio() {
		return precio;
	}
	public void setPrecio(float precio) {
		this.precio = precio;
	}
	public long getFechaAlta() {
		return fechaAlta;
	}
	public void setFechaAlta(long fechaAlta) {
		this.fechaAlta = fechaAlta;
	}
	public boolean isDescatalogado() {
		return descatalogado;
	}
	public void setDescatalogado(boolean descatalogado) {
		this.descatalogado = descatalogado;
	}
	
	
}
