package Peluqueria;

public class Cliente {
	private String dni, nombre, telefono;

	
	public Cliente() {
		super();
	}

	public void mostrar() {
		System.out.println("Cliente-> DNI:"+dni+
				"\tNombre:"+nombre+
				"\tTel�fono:" + telefono);
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

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	
	
}
