package Alumnos;

public class Asig {
	String codigo, descrip;

	
	public void mostrar() {
		System.out.println("Asignatura:" + codigo + " " + descrip);
	}
	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescrip() {
		return descrip;
	}

	public void setDescrip(String descrip) {
		this.descrip = descrip;
	}

	public Asig() {
		super();
	}
	
	
}
