package supermercado;

public class Cliente {
	private String nif;
	private String nombre;
	
	public void mostrar() {
		System.out.println("Nif:" + nif + " " + nombre);
	}
	public Cliente() {
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
	
	
}
