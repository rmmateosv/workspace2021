package Cooperativa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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

	public ArrayList<Socio> obtenerSocios() {
		// TODO Auto-generated method stub
		ArrayList<Socio> resultado  = new ArrayList<>();
		
		try {
			//Declaramos la sentencia a ejecutar
			Statement sentencia = conexion.createStatement();
			//Ejecutamos la sentencia y guardamos el resultado en r
			ResultSet r = sentencia.executeQuery("select * from socio");
			//Recorremos r, es una lista con los socios que devuelve el select
			while(r.next()) {
				Socio s = new Socio();
				//Recuperamos el dni como un string que está en la posición 1
				//La primera posción es la 1 NO LA 0!!
				s.setNif(r.getString(1));
				//Recuperomos el nombre como un string cuyo campo en la tabla se llama nombre.
				//Es lo mismo usar posición que nombre de campo 
				s.setNombre(r.getString("nombre"));
				s.setFechAlta(r.getDate(3));
				s.setSaldo(r.getFloat("saldo"));
				s.setBaja(r.getBoolean(5));
				
				resultado.add(s);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return resultado;
	}
			
}
