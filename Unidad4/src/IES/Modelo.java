package IES;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class Modelo {
	private EntityManager conexion=null;

	public Modelo() {
		try {
			EntityManagerFactory emf = 
					Persistence.createEntityManagerFactory("UP_alumnos");
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

	public Alumnos obtenerAlumno(String nif) {
		// TODO Auto-generated method stub
		Alumnos resultado = null;
		try {
			Query consulta = conexion.createQuery("from Alumnos "
					+ "where nif = ?3");
			consulta.setParameter(3, nif);
			List<Alumnos> r = consulta.getResultList();
			if(r.size()==1) {
				resultado=r.get(0);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return resultado;
	}

	public boolean altaAlumno(Alumnos a) {
		// TODO Auto-generated method stub
		boolean resultado=false;
		try {
			//Iniciamos una transacci�n
			conexion.getTransaction().begin();
			//Hacemos el insert en la tabla alumnos
			conexion.persist(a);
			
			conexion.getTransaction().commit();
			resultado = true;
			
		} catch (Exception e) {
			// TODO: handle exception
			conexion.getTransaction().rollback();
			e.printStackTrace();
		}
		return resultado;
	}

	public ArrayList<Alumnos> obtenerAlumnos() {
		// TODO Auto-generated method stub
		ArrayList<Alumnos> resultado = new ArrayList();
		try {
			
			Query consulta = conexion.createQuery("from Alumnos");
			resultado = (ArrayList<Alumnos>) consulta.getResultList();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return resultado;
	}

	public Asignaturas obtenerAsig(String nombrec) {
		// TODO Auto-generated method stub
		Asignaturas resultado=null;
		try {
			resultado = conexion.find(Asignaturas.class, nombrec);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return resultado;
	}

	public boolean altaAsig(Asignaturas a) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		try {
			conexion.getTransaction().begin();
			conexion.persist(a);
			conexion.getTransaction().commit();
			resultado = true;
			
		} catch (Exception e) {
			// TODO: handle exception
			conexion.getTransaction().rollback();
			e.printStackTrace();
		}
		return resultado;
	}

	public ArrayList<Asignaturas> obtenerAsig() {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		ArrayList<Asignaturas> resultado = new ArrayList();
		try {
			
			Query consulta = conexion.createQuery("from Asignaturas");
			resultado = (ArrayList<Asignaturas>) consulta.getResultList();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return resultado;
	}
}
