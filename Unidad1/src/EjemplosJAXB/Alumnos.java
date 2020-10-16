package EjemplosJAXB;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType(propOrder = {"curso","lista_alumnos"})
public class Alumnos {
	private String curso;
	private ArrayList<Alumno> lista_alumnos = new ArrayList<>();
	
	public Alumnos() {
		super();
	}
	@XmlElement(name="curso")
	public String getCurso() {
		return curso;
	}
	public void setCurso(String curso) {
		this.curso = curso;
	}
	@XmlElementWrapper(name="lista_alumnos")
	@XmlElement(name="alumno")
	public ArrayList<Alumno> getLista_alumnos() {
		return lista_alumnos;
	}
	public void setLista_alumnos(ArrayList<Alumno> lista_alumnos) {
		this.lista_alumnos = lista_alumnos;
	}
	
}
