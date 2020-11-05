package Gimnasio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Modelo {
	private String url = "jdbc:mysql://localhost:3306/gimnasio?serverTimezone=Europe/Madrid",
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

	public String comprobarUsuario(String p_usuario, String p_clave) {
		// TODO Auto-generated method stub
		String resultado = "NE"; //NE = no existe
		
		try {
			PreparedStatement sentencia = 
					conexion.prepareStatement("select * from usuarios "
							+ "where usuario = ? and "
							+ "clave = sha2(?,512)");
			sentencia.setString(1, p_usuario);
			sentencia.setString(2, p_clave);
			
			ResultSet r = sentencia.executeQuery();
			if(r.next()) {
				resultado = r.getString(3);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return resultado;
	}

	public Cliente obtenerCliente(String dni) {
		// TODO Auto-generated method stub
		Cliente resultado = null;
		
		try {
			PreparedStatement sentencia = 
					conexion.prepareStatement("select * from cliente "
							+ "where dni = ?");
			sentencia.setString(1, dni);
			ResultSet r = sentencia.executeQuery();
			if(r.next()) {
				resultado = new Cliente();
				resultado.setId(r.getInt(1));
				resultado.setUsuario(r.getString(2));
				resultado.setDni(r.getString(3));
				resultado.setNombre(r.getString(4));
				resultado.setApellidos(r.getString(5));
				resultado.setTelefono(r.getString(6));
				resultado.setBaja(r.getBoolean(7));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return resultado;
	}

	public boolean altaCliente(Cliente c) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		
		try {
			//Vamos a insertar en tabla usuarios y clientes => HACER TRANSACCIÓN!!
			conexion.setAutoCommit(false);
			
			//Insertamos usuarios
			PreparedStatement sentencia = 
				conexion.prepareStatement("insert into usuarios "
						+ "values (?,sha2(?,512),?)");
			sentencia.setString(1, c.getUsuario());
			sentencia.setString(2, c.getUsuario());
			sentencia.setString(3, "C");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return resultado;
	}
}
