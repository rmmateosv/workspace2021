package herbolario;

import java.util.ArrayList;
import java.util.List;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.config.EmbeddedConfiguration;




public class ModeloOO {
	ObjectContainer conexion=null;

	public ModeloOO() {
		try {
			EmbeddedConfiguration config = Db4oEmbedded.newConfiguration();
			conexion=Db4oEmbedded.openFile(config,"herbolario.4o");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}		
	}

	public ObjectContainer getConexion() {
		return conexion;
	}

	public void setConexion(ObjectContainer conexion) {
		this.conexion = conexion;
	}
	
	public void cerrar() {
		try {
			conexion.close();
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}

	public boolean crearProductos(Producto p) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		try {
			ProductoOO pOO = new ProductoOO();
			pOO.setCodigo("P"+p.getCodigo());
			pOO.setNombre(p.getNombre());
			pOO.setPrecios(p.getPrecios());
			conexion.store(pOO);
			conexion.commit();
			resultado = true;
			
		} catch (Exception e) {
			// TODO: handle exception
			conexion.rollback();
			e.printStackTrace();
		}
		return resultado;
	}

	public List<ProductoOO> obtenerProductos() {
		// TODO Auto-generated method stub
		List<ProductoOO> resultado = new ArrayList();
		try {			
			ObjectSet<ProductoOO> r= conexion.queryByExample(new ProductoOO());		
			while(r.hasNext()) {
				resultado.add(r.next());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultado;
	}

	public ProductoOO obtenerProducto(String codigo) {
		// TODO Auto-generated method stub
		ProductoOO resultado = null;
		try {			
			ObjectSet<ProductoOO> r = conexion.queryByExample(new ProductoOO(codigo));
			if(r.hasNext()) {
				resultado = r.next();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultado;
	}

	public boolean existeVenta(String codigo) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		try {			
			ObjectSet<VentaOO> r = conexion.queryByExample(new VentaOO(codigo));
			if(r.hasNext()) {
				resultado = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultado;
	}

	public boolean crearVenta(VentaOO v) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		try {			
			conexion.store(v);
			conexion.commit();
			resultado=true;
		} catch (Exception e) {
			conexion.rollback();
			e.printStackTrace();
		}
		return resultado;
		
	}

	public List<VentaOO> obtenerVentas() {
		// TODO Auto-generated method stub
		List<VentaOO> resultado = new ArrayList();
		try {			
			ObjectSet<VentaOO> r= conexion.queryByExample(new VentaOO());		
			while(r.hasNext()) {
				resultado.add(r.next());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultado;
	}

	public List<VentaOO> obtenerVentasProducto(ProductoOO p) {
		// TODO Auto-generated method stub
		List<VentaOO> resultado = new ArrayList();
		try {			
			
			ObjectSet<VentaOO> r= conexion.queryByExample(new VentaOO(p));		
			while(r.hasNext()) {
				resultado.add(r.next());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultado;
	}
}
