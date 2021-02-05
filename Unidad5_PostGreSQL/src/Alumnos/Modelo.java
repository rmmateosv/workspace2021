package Alumnos;

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
	String url = "jdbc:postgresql://localhost:5432/Alumnos",
			usuario="postgres",
			clave="admin";
	SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
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
	public ArrayList<Nota> obtenerNotas() {
		
		SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		// TODO Auto-generated method stub
		ArrayList<Nota> resultado = new ArrayList<>();
		Statement consulta;
		try {
			consulta = conexion.createStatement();
			ResultSet rs = consulta.executeQuery("select * from notas n inner join alumnos a "
					+ "on n.alumno = a.codigo "
					+ "inner join asig on n.asig = asig.codigo");
			while(rs.next()){
				Nota n = new Nota();
				n.setAlumno(new Alumno());
				n.getAlumno().setCodigo(rs.getInt(1));
				n.getAlumno().setNombre(rs.getString(5));
				n.setAsig(new Asig());
				n.getAsig().setCodigo(rs.getString(2));
				n.getAsig().setDescrip(rs.getString(9));
				
				Array datos = rs.getArray(3);
				String[][] notas = (String[][]) datos.getArray();
				
				for(int i=0;i<notas.length;i++) {
					TipoNota tn = new TipoNota();
					tn.setFecha(df.parse(notas[i][0]));
					tn.setNota(Integer.parseInt(notas[i][1]));
					n.getNotas().add(tn);
				}
				
				resultado.add(n);
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}
	public ArrayList<Asig> obtenerAsigs() {
		ArrayList<Asig> resultado = new ArrayList<>();
		
		// TODO Auto-generated method stub
		Statement consulta;
		try {
			consulta = conexion.createStatement();
			ResultSet rs = consulta.executeQuery("select * from asig");
			while(rs.next()) {
				Asig a = new Asig();
				a.setCodigo(rs.getString(1));
				a.setDescrip(rs.getString(2));
				resultado.add(a);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return resultado;
	}
	public ArrayList<Nota> obtenerNotasAsig(Asig a) {
		// TODO Auto-generated method stub
		
		// TODO Auto-generated method stub
		ArrayList<Nota> resultado = new ArrayList<>();
		PreparedStatement consulta;
		try {
			consulta = conexion.prepareStatement("select * from notas n inner join alumnos a "
					+ "on n.alumno = a.codigo "
					+ "inner join asig on n.asig = asig.codigo "
					+ "where n.asig = ?");
			consulta.setString(1, a.getCodigo());
			ResultSet rs = consulta.executeQuery();
			while(rs.next()){
				Nota n = new Nota();
				n.setAlumno(new Alumno());
				n.getAlumno().setCodigo(rs.getInt(1));
				n.getAlumno().setNombre(rs.getString(5));
				n.setAsig(new Asig());
				n.getAsig().setCodigo(rs.getString(2));
				n.getAsig().setDescrip(rs.getString(9));
				
				Array datos = rs.getArray(3);
				String[][] notas = (String[][]) datos.getArray();
				
				for(int i=0;i<notas.length;i++) {
					TipoNota tn = new TipoNota();
					tn.setFecha(df.parse(notas[i][0]));
					tn.setNota(Integer.parseInt(notas[i][1]));
					n.getNotas().add(tn);
				}				
				resultado.add(n);				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}
	public boolean crearNota(Nota n) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		PreparedStatement consulta;
		try {
			consulta = conexion.prepareStatement("select * from notas "
					+ "where alumno = ? and asig= ?");
			consulta.setInt(1, n.getAlumno().getCodigo());
			consulta.setString(2, n.getAsig().getCodigo());
			ResultSet rs = consulta.executeQuery();
			if(rs.next()) {
				//Update
			}
			else {
				//Insert
				consulta = conexion.prepareStatement("insert into notas "
						+ "values (?,?,array[array[?,?]])");
				consulta.setInt(1, n.getAlumno().getCodigo());
				consulta.setString(2, n.getAsig().getCodigo());
				consulta.setString(3, df.format(n.getNotas().get(0).getFecha()));
				consulta.setString(4, String.valueOf(n.getNotas().get(0).getNota()));
			}
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}
}
