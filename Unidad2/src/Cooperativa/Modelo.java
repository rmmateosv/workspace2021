package Cooperativa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Date;

public class Modelo {
	
	private String url = "jdbc:mysql://localhost:3306/cooperativa?serverTimezone=Europe/Madrid",
			usuario ="usuario",
			clave = "usuario";
	
	Connection conexion = null;

	public Modelo() {
		//Establecemos la conexi�n con la BD
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conexion = DriverManager.getConnection(url,usuario,clave);
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("No se encuentra el Driver de conexi�n");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Par�metros de conexi�n incorrectos");
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
				//Recuperamos el dni como un string que est� en la posici�n 1
				//La primera posci�n es la 1 NO LA 0!!
				s.setNif(r.getString(1));
				//Recuperomos el nombre como un string cuyo campo en la tabla se llama nombre.
				//Es lo mismo usar posici�n que nombre de campo 
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

	public ArrayList<Socio> obtenerSocios(String patron) {
		// TODO Auto-generated method stub
		ArrayList<Socio> resultado  = new ArrayList<>();
		
		try {
			//Declaramos la sentencia a ejecutar SENTENCIA CON PAR�METROS
			PreparedStatement sentencia = 
					conexion.prepareStatement("select * from socio "
							+ "where nombre like ?");
			//Pasar datos a los par�metros
			sentencia.setString(1, "%"+patron+"%");						
			
			//Ejecutamos la sentencia y guardamos el resultado en r
			ResultSet r = sentencia.executeQuery();
			//Recorremos r, es una lista con los socios que devuelve el select
			while(r.next()) {
				Socio s = new Socio();
				//Recuperamos el dni como un string que est� en la posici�n 1
				//La primera posci�n es la 1 NO LA 0!!
				s.setNif(r.getString(1));
				//Recuperomos el nombre como un string cuyo campo en la tabla se llama nombre.
				//Es lo mismo usar posici�n que nombre de campo 
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

	public boolean insertarSocio(Socio socio) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		
		try {
			PreparedStatement sentencia  = 
					conexion.prepareStatement("insert into socio values "
							+ "(?,?,curdate(),default,default)");
			//Registrar pa�metros
			sentencia.setString(1, socio.getNif());
			sentencia.setString(2, socio.getNombre());
			//Ejecutar sentencia
			//fila contendr� el n� de registros que se han insertado
			int fila =  sentencia.executeUpdate();
			if(fila==1) {
				resultado=true;
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}
	
	public Socio obtenerSocio(String nif) {
		Socio resultado = null;
		
		try {
			PreparedStatement sentencia = 
					conexion.prepareStatement("select * from socio "
							+ "where nif = ?");
			sentencia.setString(1, nif);
			ResultSet r = sentencia.executeQuery();
			if(r.next()) {
				resultado = new Socio();				
				resultado.setNif(r.getString(1));				
				resultado.setNombre(r.getString(2));
				resultado.setFechAlta(r.getDate(3));
				resultado.setSaldo(r.getFloat(4));
				resultado.setBaja(r.getBoolean(5));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}

	public boolean modificarSaldo(Socio socio, float cantidad) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		try {
			PreparedStatement sentencia = 
					conexion.prepareStatement("update socio "
							+ "set saldo = saldo + ? "
							+ "where nif = ?");
			sentencia.setFloat(1, cantidad);
			sentencia.setString(2, socio.getNif());
			
			int filas = sentencia.executeUpdate();
			if(filas==1) {
				resultado = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}

	public boolean borrarSocio(Socio socio) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		try {
			PreparedStatement sentencia = 
					conexion.prepareStatement("delete from socio "
							+ "where nif = ?");
			sentencia.setString(1, socio.getNif());
			
			int filas = sentencia.executeUpdate();
			if(filas==1) {
				resultado = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}

	public ArrayList<Socio> obtenerSocios(java.util.Date fechaAlta) {
		// TODO Auto-generated method stub
		ArrayList<Socio> resultado = new ArrayList();
		
		try {
			PreparedStatement sentencia = 
					conexion.prepareStatement("select * from socio "
							+ "where fechaAlta = ?");
			//Creamos fecha de java.sql.Date a partir de fecha de java.util.Date
			sentencia.setDate(1, new Date(fechaAlta.getTime()));
			ResultSet r = sentencia.executeQuery();
			while(r.next()) {
				Socio s = new Socio();			
				s.setNif(r.getString(1));				
				s.setNombre(r.getString(2));
				s.setFechAlta(r.getDate(3));
				s.setSaldo(r.getFloat(4));
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
