package herbolario;

import java.util.ArrayList;

public class ProductoOO {
	private String codigo;
	private String nombre;
	
	ArrayList<Integer> precios = new ArrayList();

	public ProductoOO() {
		super();
	}

	
	public ProductoOO(String codigo) {
		super();
		this.codigo = codigo;
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

	public ArrayList<Integer> getPrecios() {
		return precios;
	}

	public void setPrecios(ArrayList<Integer> precios) {
		this.precios = precios;
	}
	
	public void mostrar() {
		// TODO Auto-generated method stub
		System.out.println("-----------------------------------------------------------------");
		System.out.println("Código:"+ codigo + " " + nombre);
		System.out.println("Precios:");
		for(Integer i:precios) {
			System.out.println("Precio:" + i);
		}
		System.out.println("-----------------------------------------------------------------");
	}

}
