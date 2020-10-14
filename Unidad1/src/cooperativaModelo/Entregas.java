package cooperativaModelo;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = {"fruta","kilos","precio"})
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
	@XmlAttribute(name="codigo")
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
	@XmlElement(name="fruta")
	public Frutas getFruta() {
		return fruta;
	}

	public void setFruta(Frutas fruta) {
		this.fruta = fruta;
	}
	@XmlAttribute(name="fecha")
	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	@XmlElement(name="kilos")
	public float getKilos() {
		return kilos;
	}

	public void setKilos(float kilos) {
		this.kilos = kilos;
	}
	@XmlElement(name="precio")
	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}
	
	
}
