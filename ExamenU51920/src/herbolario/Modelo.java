package herbolario;

import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Modelo {
	Connection conexion = null;
	String url = "jdbc:postgresql://localhost:5432/herbolario",
			usuario="postgres",
			clave="admin";
	
	public Modelo() {
		try {
			Class.forName("org.postgresql.Driver");
			conexion = DriverManager.getConnection(url,usuario,clave);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public Connection getConexion() {
		return conexion;
	}
	public void setConexion(Connection conexion) {
		this.conexion = conexion;
	}

	public void cerrar() {
		try {
			conexion.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public boolean altaProducto(Producto p) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		try {
			PreparedStatement sentencia = conexion.prepareStatement("insert into producto "
					+ "values (default,?,(?,?,?),array[]::integer[])");
			sentencia.setString(1, p.getNombre());
			sentencia.setInt(2, p.getInfo().getKcal());
			sentencia.setInt(3, p.getInfo().getGrasas());
			sentencia.setInt(4, p.getInfo().getHidratos());
			int r = sentencia.executeUpdate();
			if(r==1) {
				resultado=true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}
	public ArrayList<Producto> obtenerProductos() {
		// TODO Auto-generated method stub
		ArrayList<Producto> resultado = new ArrayList();
		try {
			Statement sentencia = conexion.createStatement();
			ResultSet rs = sentencia.executeQuery("select codigo, nombre, (info).kcal, "
					+ "(info).grasa, (info).hidratos, precios from producto");
			while(rs.next()) {
				Producto p = new Producto();
				p.setCodigo(rs.getInt(1));
				p.setNombre(rs.getString(2));
				p.setInfo(new DatosNutricion(rs.getInt(3),rs.getInt(4),rs.getInt(5)));
				//Pasar el array de la bd al array list de la clase
				Array arrayPrecios = rs.getArray(6);
				Integer[] precios = (Integer[]) arrayPrecios.getArray();
				for(int i=0;i<precios.length;i++) {
					p.getPrecios().add(precios[i]);
				}
				resultado.add(p);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}
	public Producto obtenerProducto(int codigo) {
		// TODO Auto-generated method stub
		Producto  resultado = null;
		try {
			PreparedStatement sentencia = conexion.prepareStatement("select codigo, nombre, "
					+ "(info).kcal, "
					+ "(info).grasa, (info).hidratos, precios from producto "
					+ "where codigo = ?");
			sentencia.setInt(1, codigo);
			ResultSet rs = sentencia.executeQuery();
			if(rs.next()) {
				Producto p = new Producto();
				p.setCodigo(rs.getInt(1));
				p.setNombre(rs.getString(2));
				p.setInfo(new DatosNutricion(rs.getInt(3),rs.getInt(4),rs.getInt(5)));
				//Pasar el array de la bd al array list de la clase
				Array arrayPrecios = rs.getArray(6);
				Integer[] precios = (Integer[]) arrayPrecios.getArray();
				for(int i=0;i<precios.length;i++) {
					p.getPrecios().add(precios[i]);
				}
				resultado = p;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}
	public boolean addPrecio(Producto p, int precio) {
		// TODO Auto-generated method stub
		boolean  resultado = false;
		try {
			PreparedStatement sentencia = conexion.prepareStatement("update producto "
					+ "set precios = array_cat(precios, array[?]::integer[]) "
					+ "where codigo = ?");
			sentencia.setInt(1, precio);
			sentencia.setInt(2, p.getCodigo());
			int ok = sentencia.executeUpdate();
			if(ok==1) {
				resultado = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}
	public boolean crearVenta(Venta v) {
		// TODO Auto-generated method stub
		boolean  resultado = false;
		try {
			PreparedStatement sentencia = conexion.prepareStatement("insert into venta "
					+ "values (default, ?,?,?,?)");
			sentencia.setDate(1, new java.sql.Date(v.getFecha().getTime()));
			sentencia.setInt(2, v.getProducto().getCodigo());
			sentencia.setInt(3, v.getCantidad());
			sentencia.setInt(4, v.getPrecio());
			int ok = sentencia.executeUpdate();
			if(ok==1) {
				resultado = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}
	public ArrayList<Object[]> obtenerEstadistica() {
		// TODO Auto-generated method stub
		ArrayList<Object[]> resultado = new ArrayList();
		try {
			Statement sentencia = conexion.createStatement();
			ResultSet rs = sentencia.executeQuery("select producto, sum(cantidad), sum(precio) "
					+ "from venta "
					+ "group by producto");
			while(rs.next()) {
				Object [] datos = new Object[3];
				datos[0] = rs.getInt(1);
				datos[1] = rs.getInt(2);
				datos[2] = rs.getInt(3);
				resultado.add(datos);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}
	
}
