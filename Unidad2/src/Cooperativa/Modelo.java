package Cooperativa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Modelo {
	
	private String url = "jdbc:mysql://localhost:3306/cooperativa?serverTimezone=Europe/Madrid",
			usuario ="usuario",
			clave = "usuario";
	
	Connection conexion = null;

	public Modelo() {
		//Establecemos la conexión con la BD
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conexion = DriverManager.getConnection(url,usuario,clave);
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("No se encuentra el Driver de conexión");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Parámetros de conexión incorrectos");
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
			
}
