package supermercado;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Compra {
	private int codigo;
	private Date fecha;
	private Cliente cliente;
	public Compra() {
		super();
	}
	
	public void mostrar() {
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		System.out.println("Código:" + codigo + 
				"\tFecha:"+df.format(fecha)+ 
				"\tCliente:" + cliente.getNif() + " " +cliente.getNombre());
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	
}
