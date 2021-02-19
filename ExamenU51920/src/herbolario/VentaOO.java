package herbolario;

import java.text.SimpleDateFormat;
import java.util.Date;

public class VentaOO {
	private String codigo;
	private Date fecha;
	private ProductoOO producto;
	private int cantidad;
	private int precio;
	
	public void mostrar() {
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		System.out.println("Venta:" + codigo + 
				"\tFEcha:" + df.format(fecha) + 
				"\t Producto:" + producto.getCodigo() + " " + producto.getNombre() +
				"\tCantidad:" + cantidad + 
				"\tPrecio:"+ precio);
		
	}
	public VentaOO() {
		super();
	}
	public VentaOO(String codigo) {
		super();
		this.codigo = codigo;
	}
	
	public VentaOO(ProductoOO producto) {
		super();
		this.producto = producto;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public ProductoOO getProducto() {
		return producto;
	}
	public void setProducto(ProductoOO producto) {
		this.producto = producto;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public int getPrecio() {
		return precio;
	}
	public void setPrecio(int precio) {
		this.precio = precio;
	}
	
	
}
