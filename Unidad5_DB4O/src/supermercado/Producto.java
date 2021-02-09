package supermercado;

public class Producto {
	private String codigo;
	private String nombre;
	private String tipo;
	private float precio;
	private int stock;
	
	private void mostrar() {
		System.out.println("Codigo:" + codigo +
				"\tNombre:" + nombre +
				"\tTipo:" + tipo +
				"\tPrecio" + precio +
				"tstock:" + stock);
	}
	public Producto() {
		super();
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public float getPrecio() {
		return precio;
	}
	public void setPrecio(float precio) {
		this.precio = precio;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	
	
}
