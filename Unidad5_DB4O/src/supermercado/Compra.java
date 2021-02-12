package supermercado;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Compra {
	private int codigo;
	private Date fecha;
	private Cliente cliente;
	private List<DetalleCompra> detalle = new ArrayList<DetalleCompra>();
	
	public Compra() {
		super();
	}
	
	public void mostrar() {
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		System.out.println("Código:" + codigo + 
				"\tFecha:"+df.format(fecha)+ 
				"\tCliente:" + cliente.getNif() + " " +cliente.getNombre());
		System.out.println("Detalle Compra:");
		for(DetalleCompra d:detalle) {
			d.mostrar();
		}
	}
	
	public List<DetalleCompra> getDetalle() {
		return detalle;
	}

	public void setDetalle(List<DetalleCompra> detalle) {
		this.detalle = detalle;
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
