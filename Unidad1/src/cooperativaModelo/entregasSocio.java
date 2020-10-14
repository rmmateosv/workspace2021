package cooperativaModelo;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType(propOrder = {"socio","lista_entregas","total"})
public class entregasSocio {
	
	private String socio;
	private ArrayList<Entregas> lista_entregas;
	private float total;
	
	public entregasSocio() {
		super();
	}
	@XmlElement(name="socio")
	public String getSocio() {
		return socio;
	}
	public void setSocio(String socio) {
		this.socio = socio;
	}
	@XmlElementWrapper(name="lista_entregas")
	@XmlElement(name="entrega")
	public ArrayList<Entregas> getLista_entregas() {
		return lista_entregas;
	}
	public void setLista_entregas(ArrayList<Entregas> lista_entregas) {
		this.lista_entregas = lista_entregas;
	}
	@XmlElement(name="total")
	public float getTotal() {
		return total;
	}
	public void setTotal(float total) {
		this.total = total;
	}
	
	
}
