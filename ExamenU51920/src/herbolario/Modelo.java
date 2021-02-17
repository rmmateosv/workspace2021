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
	
}
