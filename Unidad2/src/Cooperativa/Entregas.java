package Cooperativa;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Entregas {
	private int codigo;
	private Socio socio;
	private Frutas fruta;
	private Date fecha;
	private float kilos, precio;
	
	
	public Entregas() {
		
	}
	
	public void mostrar() {
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		
		System.out.println("Codigo:"+codigo + 
				"\tSocio:"+socio.getNif()+ " "+ socio.getNombre() +
				"\tFruta:"+fruta.getCodigo()+ " "+fruta.getNombre() +
				"\tFecha:" +  formato.format(fecha) +
				"\tKilos:"+kilos+
				"\tPrecio:"+precio);
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	public Socio getSocio() {
		return socio;
	}

	public void setSocio(Socio socio) {
		this.socio = socio;
	}
	
	public Frutas getFruta() {
		return fruta;
	}

	public void setFruta(Frutas fruta) {
		this.fruta = fruta;
	}
	
	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	public float getKilos() {
		return kilos;
	}

	public void setKilos(float kilos) {
		this.kilos = kilos;
	}
	
	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}
	
	
}
