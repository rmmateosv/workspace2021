package supermercado;

import com.db4o.Db4o;
import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

public class Modelo {
	ObjectContainer conexion=null;

	public Modelo() {
		try {
			conexion=Db4oEmbedded.openFile("supermercado.4o");
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

	public Producto obtenerProducto(String codigo) {
		// TODO Auto-generated method stub
		Producto resultado = null;
		try {
			Producto pBuscado = new Producto();
			pBuscado.setCodigo(codigo);
			ObjectSet<Producto> r = conexion.queryByExample(pBuscado);
			if(r.hasNext()) {
				resultado = r.next();
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return resultado;
	}
}
