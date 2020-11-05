package Gimnasio;

public class Cliente {
	private int id;
	private String dni, nombre, apellidos, telefono, usuario;
	private boolean baja=false;
	
	public void mostrar() {
		System.out.println("Cliente:" + id + 
				"\tDNI:" + dni +
				"\tNombre:" + nombre +
				"\tApellidos:" + apellidos + 
				"\tTelefono:" + telefono);
	}
	
	public Cliente() {
		super();
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public boolean isBaja() {
		return baja;
	}
	public void setBaja(boolean baja) {
		this.baja = baja;
	}
	
	
}
