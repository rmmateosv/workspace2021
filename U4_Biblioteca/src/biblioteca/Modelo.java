package biblioteca;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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

	public boolean yaPrestado(Socio s, Libro l) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		try {
			Query consulta = conexion.createQuery("from Prestamo where clave.libro = ?1 and clave.socio = ?2 and fechaDevolReal is null");
			consulta.setParameter(1, l);
			consulta.setParameter(2, s);
			List<Prestamo> p = consulta.getResultList();
			if(p.size()>0) {
				resultado = true;
			}
						
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return resultado;
	}

	public boolean prestar(Socio s, Libro l) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		try {
			conexion.getTransaction().begin();
			
			l.setNumEjemplares(l.getNumEjemplares()-1);
			
			Prestamo p = new Prestamo();
			Date fecha = new Date();
			ClavePrestamo cp = new ClavePrestamo(l, s, fecha);
			p.setClave(cp);
			
			Calendar c = Calendar.getInstance();
			c.setTime(fecha);
			c.add(Calendar.DATE, 7);
			p.setFechaDevolPrevista(c.getTime());
			
			conexion.persist(p);
						
			conexion.getTransaction().commit();
			resultado = true;
			conexion.clear();
						
		}
		catch (Exception e) {
			conexion.getTransaction().rollback();
			// TODO: handle exception
			e.printStackTrace();
		}
		return resultado;
	}

	public boolean devolverPrestamo(Socio s, Libro l) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		try {
			//Obtener el préstamos que se devuelve
			Query consulta = conexion.createQuery("from Prestamo "
					+ "where clave.libro = ?1 and clave.socio = ?2 and fechaDevolReal is null");
			
			
			consulta.setParameter(1, l);
			consulta.setParameter(2, s);
			List<Prestamo> lp = consulta.getResultList();
			if(lp.size()>0) {
					
				Prestamo p = lp.get(0);
				
				conexion.getTransaction().begin();
				consulta = conexion.createQuery("update Prestamo "
						+ "set fechaDevolReal = ?1 where clave.libro = ?2 and clave.socio = ?3 and fechaDevolReal is null");
				Date fechaDevol =  new Date();
				consulta.setParameter(1, fechaDevol);
				consulta.setParameter(2, l);
				consulta.setParameter(3, s);
				int r = consulta.executeUpdate();
				if(r==1) {
					consulta = conexion.createQuery("update Libro "
							+ "set numEjemplares = numEjemplares + 1 where isbn = ?1");
					consulta.setParameter(1, l.getIsbn());
					
					r = consulta.executeUpdate();
					if(r==1) {
						resultado = true;
					}
				}
				//Sancionar al socio si es necesario
				if(fechaDevol.getTime()>p.getFechaDevolPrevista().getTime()) {
					s.setSancionado(true);
				}
				conexion.getTransaction().commit();
				conexion.clear();
			}
						
		}
		catch (Exception e) {
			// TODO: handle exception
			conexion.getTransaction().rollback();
			e.printStackTrace();
		}
		return resultado;
	}

	public List<Object[]> obtenerPendienteSocios() {
		// TODO Auto-generated method stub
		List<Object[]> resultado = new ArrayList<Object[]>();
		try {
			Query consulta = conexion.createQuery("select clave.socio, count(*) from Prestamo p where fechaDevolReal is null "
					+ "group by p.clave.socio");
			resultado = consulta.getResultList();
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return resultado;
	}

	public List<Long> obtenerPendientes() {
		// TODO Auto-generated method stub
		List<Long> resultado = new ArrayList<Long>();
		try {
			Query consulta = conexion.createQuery("select count(*) from Prestamo p where fechaDevolReal is null");
			resultado = consulta.getResultList();
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return resultado;
	}

	public List<Object[]> obtenerInfoBiblio() {
		// TODO Auto-generated method stub
		List<Object[]> resultado = new ArrayList<Object[]>();
		try {
			Query consulta = conexion.createQuery("select count(*), sum (numEjemplares) from Libro");
			resultado = consulta.getResultList();
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return resultado;
	}

	public boolean borrarLibro(Libro l) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		try {
				conexion.getTransaction().begin();
				conexion.remove(l);
				conexion.getTransaction().commit();
				conexion.clear();
				resultado = true;
		}
		catch (Exception e) {
			// TODO: handle exception
			conexion.getTransaction().rollback();
			e.printStackTrace();
		}
		return resultado;
	}

	public boolean borrarSocio(Socio s) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		try {
			conexion.getTransaction().begin();
			//Borramos préstamos
			for(Prestamo p: s.getPrestamos()) {
				conexion.remove(p);
			}
			conexion.remove(s);
			conexion.getTransaction().commit();
			conexion.clear();
			resultado = true;
		}
		catch (Exception e) {
			// TODO: handle exception
			conexion.getTransaction().rollback();
			e.printStackTrace();
		}
		return resultado;
	}

	

}
