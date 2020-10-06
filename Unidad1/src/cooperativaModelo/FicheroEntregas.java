package cooperativaModelo;
//ESTRUCTURA DEL FICHERO DE ENTREGAS
//Codigo int => 4B
//NIF Socio String de 9 caracteres=> 18B
//Codigo Fruta int => 4B
//Fecha long => 8B
//Kilos float =>4B
//Importe float => 4B
//TAMAÑO DE CADA ENTREGA => 42B
public class FicheroEntregas {
	private String nombre;

	public FicheroEntregas(String nombre) {
		super();
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
}
