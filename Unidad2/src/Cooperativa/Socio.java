package Cooperativa;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Socio {
	String nif,nombre;
	Date fechaAlta = new Date();
	float saldo=0;
	boolean baja=false;
	
	public Socio() {
		super();
	}

	public String getNif() {
		return nif;
	}

	public void setNif(String nif) {
		this.nif = nif;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Date getFechAlta() {
		return fechaAlta;
	}

	public void setFechAlta(Date fechAlta) {
		this.fechaAlta = fechAlta;
	}

	public float getSaldo() {
		return saldo;
	}

	public void setSaldo(float saldo) {
		this.saldo = saldo;
	}

	public boolean isBaja() {
		return baja;
	}

	public void setBaja(boolean baja) {
		this.baja = baja;
	}
	
	public void mostrar() {
		//Damos formato a la fecha de alta
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		
		System.out.println("Nif:" + nif + 
				"\tNombre:"+ nombre + 
				"\tFechaAlta" + formato.format(fechaAlta) +
				"\tSaldo" + saldo + 
				"\tBaja"+ baja);
	}
	
}
