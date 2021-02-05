package Alumnos;

import java.util.Date;

public class TipoNota {
	Date fecha;
	int nota;
	public TipoNota(Date fecha, int nota) {
		super();
		this.fecha = fecha;
		this.nota = nota;
	}
	
	public TipoNota() {
		super();
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public int getNota() {
		return nota;
	}
	public void setNota(int nota) {
		this.nota = nota;
	}
	
	
}
