package supermercado;

public class DetalleCompra {
	private Compra compra;
	private Producto producto;
	private int cantidad;
	private float precioUdad;
	
	public void mostrar() {
		System.out.println("Producto:" + producto.getNombre() + 
				"\tCantidad:" + cantidad + 
				"\tPrecioUdad:" + precioUdad);
	}
	public DetalleCompra() {
		super();
	}
	public Compra getCompra() {
		return compra;
	}
	public void setCompra(Compra compra) {
		this.compra = compra;
	}
	public Producto getProducto() {
		return producto;
	}
	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public float getPrecioUdad() {
		return precioUdad;
	}
	public void setPrecioUdad(float precioUdad) {
		this.precioUdad = precioUdad;
	}
	
	
}
