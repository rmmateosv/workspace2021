package ligaB;

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
					Persistence.createEntityManagerFactory("UP_baloncesto");
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

	public List<Partido> obtenerPartidos() {
		// TODO Auto-generated method stub
		List<Partido> resultado = new ArrayList();
		try {
			Query consulta = conexion.createQuery("from Partido");
			resultado = consulta.getResultList();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return resultado;
	}

	public Partido obtenerPartido(int codigo) {
		// TODO Auto-generated method stub
		Partido resultado=null;
		try {
			resultado = conexion.find(Partido.class, codigo);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return resultado;
	}

	public List<TipoAccion> obtenerTipoAcciones() {
		// TODO Auto-generated method stub
		List<TipoAccion> resultado = new ArrayList();
		try {
			Query consulta = conexion.createQuery("from TipoAccion");
			resultado = consulta.getResultList();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return resultado;
	}

	public TipoAccion obtenerTipoAcccion(String tipo) {
		// TODO Auto-generated method stub
		TipoAccion resultado=null;
		try {
			resultado = conexion.find(TipoAccion.class, tipo);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return resultado;
	}

	public Jugador obtenerJugador(int codigo) {
		// TODO Auto-generated method stub
		Jugador resultado=null;
		try {
			resultado = conexion.find(Jugador.class, codigo);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return resultado;
	}

	public boolean registrarAccion(TipoAccion tipo, Partido partidoSel, Jugador j) {
		// TODO Auto-generated method stub
		boolean resultado=false;
		try {
			conexion.getTransaction().begin();
			Accion a = new Accion();
			a.setTipo(tipo);
			a.setJugador(j);
			a.setPartido(partidoSel);
			a.setAnulada(false);
			conexion.persist(a);
			conexion.getTransaction().commit();
			conexion.clear();
			resultado = true;
			
		} catch (Exception e) {
			conexion.getTransaction().rollback();
			// TODO: handle exception
			e.printStackTrace();
		}
		return resultado;
	}

	public List<Accion> obtenerAcciones(Partido partidoSel) {
		// TODO Auto-generated method stub
		List<Accion> resultado = new ArrayList();
		try {
			Query consulta = conexion.createQuery("from Accion where partido = ?1");
			consulta.setParameter(1, partidoSel);
			resultado = consulta.getResultList();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return resultado;
	}

	public Accion obtenerAcccion(int codigo) {
		// TODO Auto-generated method stub
		Accion resultado=null;
		try {
			resultado = conexion.find(Accion.class, codigo);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return resultado;
	}

	public boolean anularAcción(Accion a) {
		// TODO Auto-generated method stub
		boolean resultado=false;
		try {
			conexion.getTransaction().begin();
			a.setAnulada(true);
			conexion.getTransaction().commit();
			conexion.clear();
			resultado = true;
			
		} catch (Exception e) {
			conexion.getTransaction().rollback();
			// TODO: handle exception
			e.printStackTrace();
		}
		return resultado;
	}

	public boolean borrarPartido(Partido p) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		try {
			conexion.getTransaction().begin();			
			Query consulta = conexion.createQuery("delete from Partido where codigo = ?1");
			consulta.setParameter(1, p.getCodigo());
			int ok = consulta.executeUpdate();
			if(ok==1) {
				conexion.getTransaction().commit();
				conexion.clear();
				resultado = true;
			}
		} catch (Exception e) {
			conexion.getTransaction().rollback();
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return resultado;
	}

	public List<Object[]> obtenerEstadistica(Partido partidoSel) {
		// TODO Auto-generated method stub
		List<Object[]> resultado = new ArrayList();
		try {
			Query consulta = conexion.createNativeQuery("call obtenerEstadistica(?1)");
			consulta.setParameter(1, partidoSel.getCodigo());
			resultado = consulta.getResultList();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return resultado;
	}
}
