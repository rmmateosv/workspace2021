package Gimnasio;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Recibo {
	private int id;
	private Date fechaE, fechaP;
	private float cuantia;
	private boolean pagado;
	
	public void mostrar() {
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		System.out.println("Recibo:"+id +
				"\tEmisión:"+formato.format(fechaE) + 
				"\tFecha Pago:"+(pagado?formato.format(fechaP):"")+
				"\tImporte:"+cuantia + 
				"\tPagado:"+pagado);
	}
	public Recibo() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getFechaE() {
		return fechaE;
	}
	public void setFechaE(Date fechaE) {
		this.fechaE = fechaE;
	}
	public Date getFechaP() {
		return fechaP;
	}
	public void setFechaP(Date fechaP) {
		this.fechaP = fechaP;
	}
	public float getCuantia() {
		return cuantia;
	}
	public void setCuantia(float cuantia) {
		this.cuantia = cuantia;
	}
	public boolean isPagado() {
		return pagado;
	}
	public void setPagado(boolean pagado) {
		this.pagado = pagado;
	}
	
	
	
}
