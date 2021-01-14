package biblioteca;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

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
	public boolean crearSocio(Socio s) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		
		EntityTransaction t = null;
		try {
			t=conexion.getTransaction();
			t.begin();
			conexion.persist(s);
			t.commit();
			resultado=true;
		}
		catch (Exception e) {
			// TODO: handle exception
			if(t!=null) {
				t.rollback();
			}
			e.printStackTrace();
		}
		return resultado;
	}
	public Socio obtenerSocio(String nif) {
		// TODO Auto-generated method stub
		Socio resultado = null;
		try {
			//Hacemos select para comprobar si existe
			Query c = conexion.createQuery("from Socio "
					+ "where nif = ?1");
			c.setParameter(1, nif);
			List<Socio> r = c.getResultList();
			if(r.size()==1) {
				resultado=r.get(0);
			}
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		return resultado;
	}
	public boolean crearLibro(Libro l) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		
			EntityTransaction t = null;
			try {
				t=conexion.getTransaction();
				t.begin();
				conexion.persist(l);
				t.commit();
				resultado=true;
			}
			catch (Exception e) {
				// TODO: handle exception
				if(t!=null) {
					t.rollback();
					e.printStackTrace();
				}
			}
		
		
		return resultado;
	}
	public Libro obtenerLibro(String isbn) {
		// TODO Auto-generated method stub
		Libro resultado = null;
		try {
			//Recuperamos el libro de la BD por su clave
			resultado = conexion.find(Libro.class, isbn);
						
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return resultado;
	}

	public List<Libro> obtenerLibros() {
		// TODO Auto-generated method stub
		List<Libro> resultado = new ArrayList<Libro>();
		
		try {
			Query consulta = conexion.createQuery("from Libro");
			resultado = consulta.getResultList();
						
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return resultado;
	}
	public List<Socio> obtenerSocios() {
		// TODO Auto-generated method stub
		List<Socio> resultado = new ArrayList<Socio>();
		
		try {
			Query consulta = conexion.createQuery("from Socio");
			resultado = consulta.getResultList();
						
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return resultado;
	}
	public boolean modificarEjemplares(Libro l,int num) {
		// TODO Auto-generated method stub
		boolean resultado=false;
		EntityTransaction t = null;
		try {
			
			t= conexion.getTransaction();
			t.begin();
			l.setNumEjemplares(l.getNumEjemplares()+num);
			t.commit();
			resultado=true;
			
			
		}
		catch (Exception e) {
			// TODO: handle exception
			t.rollback();
			e.printStackTrace();
		}
		return resultado;
	}

}
