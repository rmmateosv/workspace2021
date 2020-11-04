package Biblioteca;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Principal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			
			Connection conexion = 
					DriverManager.getConnection("jdbc:ucanaccess://biblioteca.mdb");
			
			Statement sentencia = conexion.createStatement();
			ResultSet r =  sentencia.executeQuery("select * from libros");
			while(r.next()) {
				System.out.println("Id:"+r.getInt(1)+
						"\tTitulo:"+r.getString(2)+
						"\tTema:"+r.getString(3));
			}
			
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
