package EjemplosJAXB;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = {"nombre"})
public class Alumno {
	private String dni;
	private String nombre;
	public Alumno() {
		super();
	}
	@XmlAttribute(name="dni")
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	@XmlElement(name="nombre")
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public void mostrar() {
		System.out.println("Alumno: "+dni + "\t"+ nombre);
	}
	
}
