package Alumnos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
	public ArrayList<Profesor> obtenerProfes() {
		// TODO Auto-generated method stub
		ArrayList<Profesor> resultado = new ArrayList<>();
		Statement consulta;
		try {
			consulta = conexion.createStatement();
			ResultSet rs = consulta.executeQuery("select codigo, nombre, "
					+ "(direccion).tipoV, (direccion).nombreV, "
					+ "(direccion).numero, (direccion).cp, especialidad from profesores");
			while(rs.next()) {
				Profesor p = new Profesor();
				p.setCodigo(rs.getInt(1));
				p.setNombre(rs.getString(2));
				p.setDireccion(new Direccion(rs.getString(3),rs.getString(4),rs.getInt(5),rs.getInt(6)));
				p.setEspecialidad(rs.getString(7));
				resultado.add(p);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return resultado;
	}
	public boolean insertarAlumno(Alumno a) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		try {
			PreparedStatement consulta = conexion.prepareStatement("insert into alumnos values "
					+ "(default,?,(?,?,?,?),?)");
			consulta.setString(1, a.getNombre());
			consulta.setString(2, a.getDireccion().getTipoV());
			consulta.setString(3, a.getDireccion().getNombreV());
			consulta.setInt(4, a.getDireccion().getNumero());
			consulta.setInt(5, a.getDireccion().getCp());
			consulta.setDate(6, new java.sql.Date(a.getFechaM().getTime()));
			if(consulta.executeUpdate()==1) {
				resultado = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}
	public boolean modficarDireccion(Alumno a) {
		// TODO Auto-generated method stub
		boolean resultado = true;
		PreparedStatement consulta;
		try {
			consulta = conexion.prepareStatement("update alumnos set "
					+ "direccion = (?,?,?,?) "
					+ "where codigo = ?");
			consulta.setString(1, a.getDireccion().getTipoV());
			consulta.setString(2, a.getDireccion().getNombreV());
			consulta.setInt(3, a.getDireccion().getNumero());
			consulta.setInt(4, a.getDireccion().getCp());
			consulta.setInt(5, a.getCodigo());
			
			if(consulta.executeUpdate()==1) {
				resultado = true;			
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}
	public Alumno obtenerAlumno(int codigo) {
		// TODO Auto-generated method stub
		Alumno resultado = null;
		PreparedStatement consulta;
		try {
			consulta = conexion.prepareStatement("select codigo, nombre, "
					+ "(direccion).tipoV, (direccion).nombreV, "
					+ "(direccion).numero, (direccion).cp, fechaM from alumnos "
					+ "where codigo = ?");
			consulta.setInt(1, codigo);
			ResultSet rs = consulta.executeQuery();
			if(rs.next()) {
				resultado = new Alumno();
				resultado.setCodigo(rs.getInt(1));
				resultado.setNombre(rs.getString(2));
				resultado.setDireccion(new Direccion(rs.getString(3),rs.getString(4),rs.getInt(5),rs.getInt(6)));
				resultado.setFechaM(new Date(rs.getDate(7).getTime()));				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return resultado;
	}
	public boolean borrarAlumno(Alumno a) {
		// TODO Auto-generated method stub
		boolean resultado = true;
		PreparedStatement consulta;
		try {
			consulta = conexion.prepareStatement("delete from alumnos "
					+ "where codigo = ?");
			
			consulta.setInt(1, a.getCodigo());
			
			if(consulta.executeUpdate()==1) {
				resultado = true;			
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}
}
