package Peluqueria;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Cita {
	private int id;
	private Date fecha;
	private String dni,servicio;
	private float tiempo;
	public Cita() {
		super();
	}
	
	public void mostrar() {
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy hh:mm");
		System.out.println("Cita:"+id + 
				"\tFecha:"+formato.format(fecha) + 
				"\tCliente:"+dni+
				"\tServicio:"+servicio+
				"\tTiempo:"+tiempo);
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getServicio() {
		return servicio;
	}
	public void setServicio(String servicio) {
		this.servicio = servicio;
	}
	public float getTiempo() {
		return tiempo;
	}
	public void setTiempo(float tiempo) {
		this.tiempo = tiempo;
	}
	
	
}
