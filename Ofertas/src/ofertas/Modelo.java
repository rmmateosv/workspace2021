package ofertas;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import com.mysql.cj.x.protobuf.MysqlxDatatypes.Array;



public class Modelo {
	private Connection conexion;
	private String url = 
		"jdbc:mysql://localhost:3306/ofertas?serverTimezone=Europe/Madrid", 
			usuario = "usuario",		
			clave = "usuario";
	

	public Modelo() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conexion = DriverManager.getConnection(url, usuario, clave);
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
	public String login(String p_usuario, String clave2) {
		// TODO Auto-generated method stub
		String resultado = "";
		try {
			CallableStatement sentencia = 
					conexion.prepareCall("{? = call comprobarUsuario(?, ?)}");
			sentencia.registerOutParameter(1, Types.VARCHAR);
			sentencia.setString(2, p_usuario);
			sentencia.setString(3, clave2);
			sentencia.executeUpdate();
			resultado = sentencia.getString(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}
	public boolean altaUsuario(String us, String pwd, String tipo) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		try {
			PreparedStatement sentencia = 
				conexion.prepareStatement("insert into usuarios values (?,sha2(?,0),?)");
			sentencia.setString(1, us);
			sentencia.setString(2, pwd);
			sentencia.setString(3, tipo);
			int r = sentencia.executeUpdate();
			if(r==1) {
				resultado = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}
	public boolean altaOferta(String establecimieto, java.util.Date fechaI, java.util.Date fechaF, String desc) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		try {
			PreparedStatement sentencia = 
				conexion.prepareStatement("insert into "
						+ "ofertas values (null,?,?,?,?)");
			sentencia.setDate(1, new Date(fechaI.getTime()));
			sentencia.setDate(2, new Date(fechaF.getTime()));
			sentencia.setString(3, establecimieto);
			sentencia.setString(4, desc);
			int r = sentencia.executeUpdate();
			if(r==1) {
				resultado = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}
	public ArrayList<Object[]> obtenerOfertas(String usuario2) {
		// TODO Auto-generated method stub
		ArrayList<Object[]> resultado = new ArrayList<>();
		try {
			PreparedStatement sentencia = 
				conexion.prepareStatement("select * from ofertas "
						+ "where establec = ?");
			
			sentencia.setString(1, usuario2);
			ResultSet r = sentencia.executeQuery();
			while(r.next()) {
				Object[] o = new Object[5];
				o[0] = r.getInt(1);
				o[1] = r.getDate(2);
				o[2] = r.getDate(3);
				o[3] = r.getString(4);
				o[4] = r.getString(5);
				resultado.add(o);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}
	public boolean modificarOferta(String establecimieto, int id, java.util.Date fechaI, java.util.Date fechaF, String desc) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		try {
			PreparedStatement sentencia = 
				conexion.prepareStatement("update ofertas "
						+ "set fechaI = ?, fechaF = ?, descrip = ? "
						+ "where id = ? and establec = ?");
			sentencia.setDate(1, new Date(fechaI.getTime()));
			sentencia.setDate(2, new Date(fechaF.getTime()));
			sentencia.setString(3, desc);
			sentencia.setInt(4, id);
			sentencia.setString(5, establecimieto);
			int r = sentencia.executeUpdate();
			if(r==1) {
				resultado = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}
	
}
