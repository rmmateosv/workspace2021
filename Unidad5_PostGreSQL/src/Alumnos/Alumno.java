package Alumnos;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Alumno extends Persona{
	private Date fechaM;

	public void mostrar() {
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yy");
		System.out.println("Código:" + getCodigo() +
				"\tNombre:" + getNombre() +
				"\tDirección:" + getDireccion().getNombreV() +" "
				+ getDireccion().getNumero()+
				"\tFechaMatricula:"+df.format(fechaM));
	}
	
	public Alumno() {
		super();
	}

	public Date getFechaM() {
		return fechaM;
	}

	public void setFechaM(Date fechaM) {
		this.fechaM = fechaM;
	}
	
	
}
