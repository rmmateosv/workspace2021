package Gimnasio;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;



public class Modelo {
	private String url = "jdbc:mysql://localhost:3306/gimnasio?serverTimezone=Europe/Madrid", usuario = "usuario",
			clave = "usuario";

	Connection conexion = null;

	public Modelo() {
		// Establecemos la conexión con la BD
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conexion = DriverManager.getConnection(url, usuario, clave);

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
		String resultado = "NE"; // NE = no existe

		try {
			PreparedStatement sentencia = conexion
					.prepareStatement("select * from usuarios " + "where usuario = ? and " + "clave = sha2(?,512)");
			sentencia.setString(1, p_usuario);
			sentencia.setString(2, p_clave);

			ResultSet r = sentencia.executeQuery();
			if (r.next()) {
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
			PreparedStatement sentencia = conexion.prepareStatement("select * from cliente " + "where dni = ?");
			sentencia.setString(1, dni);
			ResultSet r = sentencia.executeQuery();
			if (r.next()) {
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
			// Vamos a insertar en tabla usuarios y clientes => HACER TRANSACCIÓN!!
			conexion.setAutoCommit(false);

			// Insertamos usuarios
			PreparedStatement sentencia = conexion
					.prepareStatement("insert into usuarios " + "values (?,sha2(?,512),?)");
			sentencia.setString(1, c.getUsuario());
			sentencia.setString(2, c.getUsuario());
			sentencia.setString(3, "C");
			int r = sentencia.executeUpdate();
			if (r == 1) {
				sentencia = conexion.prepareStatement("insert into cliente " + "values (null,?,?,?,?,?,?)",
						Statement.RETURN_GENERATED_KEYS);
				sentencia.setString(1, c.getUsuario());
				sentencia.setString(2, c.getDni());
				sentencia.setString(3, c.getApellidos());
				sentencia.setString(4, c.getNombre());
				sentencia.setString(5, c.getTelefono());
				sentencia.setBoolean(6, c.isBaja());

				r = sentencia.executeUpdate();
				if (r == 1) {
					ResultSet auto = sentencia.getGeneratedKeys();
					if (auto.next()) {
						c.setId(auto.getInt(1));
					}
					conexion.commit();
					resultado = true;
				} else {
					conexion.rollback();
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return resultado;
	}

	public boolean cambiarClave(String usuario2, String clave2) {
		// TODO Auto-generated method stub
		boolean resultado = false;

		try {
			PreparedStatement sentencia = conexion
					.prepareStatement("update usuarios " + "set clave =  sha2(?,512) " + "where usuario = ?");
			sentencia.setString(1, clave2);
			sentencia.setString(2, usuario2);

			int r = sentencia.executeUpdate();
			if (r == 1) {
				resultado = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}

	public ArrayList<Actividad> obtenerActividades() {
		// TODO Auto-generated method stub
		ArrayList<Actividad> resultado = new ArrayList<>();

		try {
			Statement sentencia = conexion.createStatement();
			ResultSet r = sentencia.executeQuery("select * from actividad " + "where activa = 'ACTIVA'");
			while (r.next()) {
				Actividad a = new Actividad();
				a.setId(r.getInt(1));
				a.setNombre(r.getString(2));
				a.setCoste(r.getFloat(3));
				a.setActiva(r.getString(4));
				resultado.add(a);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return resultado;
	}

	public Actividad obtenerActividad(int codigoA) {
		// TODO Auto-generated method stub
		Actividad resultado = null;
		try {
			PreparedStatement sentencia = conexion
					.prepareStatement("select * from actividad " + "where id = ? and activa = 'ACTIVA'");
			sentencia.setInt(1, codigoA);
			ResultSet r = sentencia.executeQuery();
			if (r.next()) {
				resultado = new Actividad();
				resultado.setId(r.getInt(1));
				resultado.setNombre(r.getString(2));
				resultado.setCoste(r.getFloat(3));
				resultado.setActiva(r.getString(4));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}

	public boolean inscribir(int idCliente, int codigoA) {
		// TODO Auto-generated method stub
		boolean resultado = false;

		try {
			PreparedStatement sentencia = conexion.prepareStatement("insert into participa values " + "(?,?)");
			sentencia.setInt(2, idCliente);
			sentencia.setInt(1, codigoA);

			int r = sentencia.executeUpdate();
			if (r == 1) {
				resultado = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return resultado;
	}

	public ArrayList<Actividad> obtenerMisActividades(Cliente cliente) {
		// TODO Auto-generated method stub
		ArrayList<Actividad> resultado = new ArrayList<Actividad>();

		try {
			PreparedStatement sentencia = conexion.prepareStatement("select a.* " + "from actividad a join participa p "
					+ "on p.actividad_id = a.id " + "where cliente_id = ?");
			sentencia.setInt(1, cliente.getId());
			ResultSet r = sentencia.executeQuery();
			while (r.next()) {
				Actividad a = new Actividad();
				a.setId(r.getInt(1));
				a.setNombre(r.getString(2));
				a.setCoste(r.getFloat(3));
				a.setActiva(r.getString(4));
				resultado.add(a);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return resultado;
	}

	public boolean borrarMiActividad(Cliente cliente, int codigo) {
		// TODO Auto-generated method stub
		boolean resultado = false;

		try {
			PreparedStatement sentencia = conexion
					.prepareStatement("delete from participa " + "where actividad_id = ? and " + "cliente_id = ?");
			sentencia.setInt(1, codigo);
			sentencia.setInt(2, cliente.getId());
			int r = sentencia.executeUpdate();
			if (r == 1) {
				resultado = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}

	public ArrayList<Recibo> obtenerRecibos(Cliente cliente, String tipo) {
		// TODO Auto-generated method stub
		ArrayList<Recibo> resultado = new ArrayList<Recibo>();

		try {
			PreparedStatement sentencia = null;
			if (tipo.equalsIgnoreCase("P") || tipo.equalsIgnoreCase("PP")) {
				sentencia = 
						conexion.prepareStatement("select * from recibo "
								+ "where cliente_id = ? and "
								+ "pagado = ?");				
				if(tipo.equalsIgnoreCase("P")) {
					sentencia.setBoolean(2, true);
				}
				else {
					sentencia.setBoolean(2, false);
				}
			} else {
				sentencia = 
						conexion.prepareStatement("select * from recibo "
								+ "where cliente_id = ?");
			}
			sentencia.setInt(1, cliente.getId());
			ResultSet r = sentencia.executeQuery();
			while(r.next()) {
				Recibo recibo = new Recibo();
				recibo.setId(r.getInt(1));
				recibo.setFechaE(r.getDate(2));
				recibo.setFechaP(r.getDate(3));
				recibo.setCuantia(r.getFloat(4));
				recibo.setPagado(r.getBoolean(5));
				resultado.add(recibo);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return resultado;
	}

	public int generarRecibos(int mes, int anio) {
		// TODO Auto-generated method stub
		int resultado = -1;
		try {
			CallableStatement sentencia = 
					conexion.prepareCall("{? = call generar_recibos(?,?)}");
			sentencia.registerOutParameter(1, Types.INTEGER);
			
			sentencia.setInt(2, mes);
			sentencia.setInt(3, anio);
			
			sentencia.executeUpdate();
			
			resultado = sentencia.getInt(1);
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}
}
