package Alumnos;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Nota {
	Alumno alumno;
	Asig asig;
	ArrayList<TipoNota> notas = new ArrayList<>();
	
	public void mostrar() {
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		System.out.println("Alumno:" + alumno.getCodigo() + " "+ alumno.getNombre() + 
				"\tAsig:"+asig.getCodigo());
		System.out.println("Notas del alumno:");
		for(TipoNota n:notas) {
			System.out.println("Fecha Examen:"+df.format(n.getFecha()) 
			+ "\tNota:"+n.getNota());
		}
	}
	public Nota() {
		super();
	}
	public Alumno getAlumno() {
		return alumno;
	}
	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}
	public Asig getAsig() {
		return asig;
	}
	public void setAsig(Asig asig) {
		this.asig = asig;
	}
	public ArrayList<TipoNota> getNotas() {
		return notas;
	}
	public void setNotas(ArrayList<TipoNota> notas) {
		this.notas = notas;
	}
	
	
}
