package Alumnos;

import java.text.SimpleDateFormat;

public class Profesor extends Persona{
	private String especialidad;

	public void mostrar() {
		System.out.println("Código:" + getCodigo() +
				"\tNombre:" + getNombre() +
				"\tDirección:" + getDireccion().getNombreV() +" "
				+ getDireccion().getNumero()+
				"\tEspecilidad:"+especialidad);
	}
	public String getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}

	public Profesor() {
		super();
	}
	
	
}
