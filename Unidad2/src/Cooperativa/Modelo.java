package Cooperativa;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;


import java.sql.Date;

public class Modelo {
	
	private String url = "jdbc:mysql://localhost:3306/cooperativa?serverTimezone=Europe/Madrid&allowMultiQueries=true",
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

	public ArrayList<Socio> obtenerSocios(String patron) {
		// TODO Auto-generated method stub
		ArrayList<Socio> resultado  = new ArrayList<>();
		
		try {
			//Declaramos la sentencia a ejecutar SENTENCIA CON PARÁMETROS
			PreparedStatement sentencia = 
					conexion.prepareStatement("select * from socio "
							+ "where nombre like ?");
			//Pasar datos a los parámetros
			sentencia.setString(1, "%"+patron+"%");						
			
			//Ejecutamos la sentencia y guardamos el resultado en r
			ResultSet r = sentencia.executeQuery();
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

	public boolean insertarSocio(Socio socio) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		
		try {
			PreparedStatement sentencia  = 
					conexion.prepareStatement("insert into socio values "
							+ "(?,?,curdate(),default,default)");
			//Registrar paámetros
			sentencia.setString(1, socio.getNif());
			sentencia.setString(2, socio.getNombre());
			//Ejecutar sentencia
			//fila contendrá el nº de registros que se han insertado
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

	public ArrayList<Object[]> obtenerEstadistica(String nif) {
		// TODO Auto-generated method stub
		ArrayList<Object[]> resultado = new ArrayList();
		
		try {
			CallableStatement sentencia = conexion.prepareCall("{call estadistica(?)}");
			sentencia.setString(1, nif);
			
			ResultSet r = sentencia.executeQuery();
			
			while(r.next()) {
				Object[] fila = new Object[5];
				fila[0] = r.getInt(1);
				fila[1] = r.getString(2);
				fila[2] = r.getInt(3);
				fila[3] = r.getFloat(4);
				fila[4] = r.getFloat(5);
				
				resultado.add(fila);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return resultado;
	}

	public int funcionBorrarSocio(Socio socio) {
		// TODO Auto-generated method stub
		int resultado = 0;
		
		try {
			CallableStatement sentencia = 
					conexion.prepareCall("{? = call borrar_socio(?)}");
			//Indicamos el tipo del parámetro que devuelve la función
			sentencia.registerOutParameter(1, Types.INTEGER);
			sentencia.setString(2, socio.getNif());
			
			sentencia.executeUpdate();
			resultado = sentencia.getInt(1);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}
	public boolean crearEntrega(Entregas entrega) {
		boolean resultado = false;
		try {
			//curdate() nos coge la fecha actual en lenguaje mysql
			PreparedStatement sentencia = 
					conexion.prepareStatement("insert into entrega values(null, ?, ?, curdate(), ?, ?)");
			
			//Pasamos parametros
			sentencia.setString(1, entrega.getSocio().getNif());
			sentencia.setInt(2, entrega.getFruta().getCodigo());
			sentencia.setFloat(3,  entrega.getKilos());
			sentencia.setFloat(4, entrega.getPrecio());
			
			//Ejecutamos la sentencia
			//Nos devuelve un int con 0 si no funciona u otro numero con las filas que se han insertado
			int fila = sentencia.executeUpdate();
			if(fila == 1) {
				resultado = true;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return resultado;
	}

	public ArrayList<Entregas> obtenerEntregas(String nombre) {
		ArrayList<Entregas> resultado = new ArrayList<>();
		
		try {
			PreparedStatement sentencia = conexion.prepareStatement("select e.*, s.*, f.* "
													+"from entrega e join socio s on e.socio = s.nif "
													+"join fruta f on e.fruta = f.codigo "
													+"where s.nombre like ?");
			
			sentencia.setString(1, "%"+nombre+"%");
			
			ResultSet r = sentencia.executeQuery();
			
			while(r.next()) {
				Entregas e = new Entregas();
				
				e.setCodigo(r.getInt(1));
				e.setSocio(obtenerSocio(r.getString(2)));
				e.setFruta(new Frutas());
				e.getFruta().setCodigo(3);
				e.setFecha(r.getDate(4));
				e.setKilos(r.getFloat(5));
				e.setPrecio(r.getFloat(6));
				
				resultado.add(e);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return resultado;
	}
	public Frutas obtenerFruta(int codigoF) {
		Frutas resultado = null;
		
		try {
			PreparedStatement sentencia = conexion.prepareStatement("select * from fruta "
															+ "where codigo = ?");
			
			sentencia.setInt(1, codigoF);
			ResultSet r = sentencia.executeQuery();
			if(r.next()) {
				resultado = new Frutas();
				
				resultado.setCodigo(r.getInt(1));
				resultado.setNombre(r.getString(2));
				resultado.setFechaIT(r.getDate(3));
				resultado.setNumAlmacen(r.getInt(4));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}

	public ArrayList<Frutas> obtenerFrutas() {
		ArrayList<Frutas> resultado = new ArrayList<>();
		
		try {
			Statement sentencia = conexion.createStatement();
			
			ResultSet r = sentencia.executeQuery("select * from fruta");
			
			while(r.next()) {
				Frutas f = new Frutas();
				
				f.setCodigo(r.getInt(1));
				f.setNombre(r.getString(2));
				f.setFechaIT(r.getDate(3));
				f.setNumAlmacen(r.getInt(4));
				
				resultado.add(f);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return resultado;
	}

	public int crearEntregaConSocio(Entregas entrega) {
		// TODO Auto-generated method stub
		int resultado = -1;
		
		
		try {
			//Iniciar transacción
			conexion.setAutoCommit(false);
			//Creamos socio
			PreparedStatement sentencia = 
					conexion.prepareStatement("insert into socio values "
							+ "(?,?,curdate(),default,default)");
			sentencia.setString(1, entrega.getSocio().getNif());
			sentencia.setString(2, entrega.getSocio().getNombre());
			
			int r = sentencia.executeUpdate();
			if(r==1) {
				//Crear entrega
				sentencia = conexion.prepareStatement("insert into entrega values "
						+ "(null,?,?,curdate(),?,?)", Statement.RETURN_GENERATED_KEYS);
				sentencia.setString(1, entrega.getSocio().getNif());
				sentencia.setInt(2, entrega.getFruta().getCodigo());
				sentencia.setFloat(3, entrega.getKilos());
				sentencia.setFloat(4, entrega.getPrecio());
				
				r = sentencia.executeUpdate();
				if(r==1) {
					conexion.commit();
					//Recuperar el código de la entrega
					ResultSet codigos = sentencia.getGeneratedKeys();
					if(codigos.next()) {
						resultado = codigos.getInt(1);
					}
					
				}
				else {
					//Deshacemos la creación del socio
					conexion.rollback();
				}
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return resultado;
	}

	public boolean ejecutarScript(String script) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		
		try {
			System.out.println(script);
			Statement sentencia = conexion.createStatement();
			sentencia.executeUpdate(script);
			resultado=true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return resultado;
	}

	public void MetadatosGenerales() {
		// TODO Auto-generated method stub
		try {
			DatabaseMetaData datos = conexion.getMetaData();
			System.out.println("Servidor:\t"+datos.getDatabaseProductName());
			System.out.println("Versión::\t"+datos.getDatabaseProductVersion());
			System.out.println("Usuario::\t"+datos.getUserName());
			System.out.println("URL:\t"+datos.getURL());
			System.out.println("Bases de datos:");
			ResultSet basesD = datos.getCatalogs();			
			while(basesD.next()) {
				System.out.println(basesD.getString(1));
			}
			System.out.println("Tablas de la bd cooperativa:");
			System.out.println("BD\t\tNombre\t\tTipo");
			ResultSet tablas = datos.getTables("cooperativa", null, null, null);
			while(tablas.next()) {
				System.out.println(tablas.getString(1)+"\t"+
						tablas.getString(3)+"\t\t"+
						tablas.getString(4));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void MetadatosConsulta(String consulta) {
		// TODO Auto-generated method stub
		try {
			Statement sentencia = conexion.createStatement();
			ResultSet r = sentencia.executeQuery(consulta);
			//Metadatos
			ResultSetMetaData datos = r.getMetaData();
			System.out.println("Nº de columnas:"+datos.getColumnCount());
			System.out.println("Campo\t\tTipo");
			for(int i=1;i<=datos.getColumnCount();i++) {
				System.out.println(datos.getColumnName(i)+
						"\t\t"+datos.getColumnTypeName(i));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
