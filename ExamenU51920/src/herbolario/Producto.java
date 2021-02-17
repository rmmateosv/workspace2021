package herbolario;

import java.util.ArrayList;

public class Producto {
	private int codigo;
	private String nombre;
	private DatosNutricion info;
	ArrayList<Integer> precios;
	public Producto() {
		super();
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public DatosNutricion getInfo() {
		return info;
	}
	public void setInfo(DatosNutricion info) {
		this.info = info;
	}
	public ArrayList<Integer> getPrecios() {
		return precios;
	}
	public void setPrecios(ArrayList<Integer> precios) {
		this.precios = precios;
	}
	
	
}
