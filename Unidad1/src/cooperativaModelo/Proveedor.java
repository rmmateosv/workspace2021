package cooperativaModelo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Proveedor {
	private int codigo;
	private String nombre;
	private ArrayList<String> telefonos= new ArrayList<>();
	private Date fecha_pedido;
	private boolean baja=false;
	
	public boolean isBaja() {
		return baja;
	}

	public void setBaja(boolean baja) {
		this.baja = baja;
	}

	public void mostrar() {
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		System.out.println("Código:" + codigo +
				"\tNombre:" + nombre + 
				"\tFecha Ul. Pedido:" + formato.format(fecha_pedido));
		for(String t: telefonos) {
			System.out.println("Teléfono:"+t);
		}
	}
	
	public Proveedor() {
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

	public ArrayList<String> getTelefonos() {
		return telefonos;
	}

	public void setTelefonos(ArrayList<String> telefonos) {
		this.telefonos = telefonos;
	}

	public Date getFecha_pedido() {
		return fecha_pedido;
	}

	public void setFecha_pedido(Date fecha_pedido) {
		this.fecha_pedido = fecha_pedido;
	}
	
	
}
