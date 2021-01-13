package biblioteca;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Modelo {
	


	private EntityManager conexion=null;
	
	public Modelo() {
		try {
			EntityManagerFactory emf = 
					Persistence.createEntityManagerFactory("UP_Biblioteca");
			conexion = emf.createEntityManager();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}		
	}

	public EntityManager getConexion() {
		return conexion;
	}

	public void setConexion(EntityManager conexion) {
		this.conexion = conexion;
	}
	
	public void cerrar() {
		try {
			conexion.close();			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
}
