package Alumnos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

public class Modelo {
	Connection conexion = null;
	String url = "jdbc:postgresql://localhost:5432/Alumnos",
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
	public ArrayList<Alumno> obtenerAlumnos() {
		// TODO Auto-generated method stub
		ArrayList<Alumno> resultado = new ArrayList<>();
		Statement consulta;
		try {
			consulta = conexion.createStatement();
			ResultSet rs = consulta.executeQuery("select codigo, nombre, "
					+ "(direccion).tipoV, (direccion).nombreV, "
					+ "(direccion).numero, (direccion).cp, fechaM from alumnos");
			while(rs.next()) {
				Alumno a = new Alumno();
				a.setCodigo(rs.getInt(1));
				a.setNombre(rs.getString(2));
				a.setDireccion(new Direccion(rs.getString(3),rs.getString(4),rs.getInt(5),rs.getInt(6)));
				a.setFechaM(new Date(rs.getDate(7).getTime()));
				resultado.add(a);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return resultado;
	}
}
