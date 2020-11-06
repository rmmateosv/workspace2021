package Gimnasio;

public class Actividad {
	private int id;
	private String nombre;
	private float coste;
	private String activa;
	
	public void mostrar() {
		System.out.println("Codigo:" + id + 
				"\tNombre:"+nombre + 
				"\tCoste:" + coste + 
				"\tActiva:" + activa);
	}
	public Actividad() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public float getCoste() {
		return coste;
	}
	public void setCoste(float coste) {
		this.coste = coste;
	}
	public String getActiva() {
		return activa;
	}
	public void setActiva(String activa) {
		this.activa = activa;
	}
	
	
}	
