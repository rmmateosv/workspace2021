package ColeccionVentas;

import java.lang.reflect.InvocationTargetException;

import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.Resource;
import org.xmldb.api.base.ResourceIterator;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XPathQueryService;

public class Modelo {
	
	private Collection coleccion = null;
	private String url = "xmldb:exist://localhost:8080/exist/xmlrpc/db/ventas",
		usuario="admin",
		clave="admin";
	public Modelo() {
		try {
			Class driver = Class.forName("org.exist.xmldb.DatabaseImpl");
			Database db = (Database) driver.getDeclaredConstructor().newInstance();
			DatabaseManager.registerDatabase(db);
			
			coleccion = DatabaseManager.getCollection(url,usuario,clave);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	public Collection getColeccion() {
		return coleccion;
	}
	public void setColeccion(Collection coleccion) {
		this.coleccion = coleccion;
	}
	public void cerrar() {
		try {
			coleccion.close();
		} catch (XMLDBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void mostrarProductos() {
		// TODO Auto-generated method stub
		try {
			XPathQueryService servicio = 
					(XPathQueryService) 
					coleccion.getService("XPathQueryService", "1.0");
			ResourceSet r = servicio.query("/productos/product");
			ResourceIterator nodos = r.getIterator();
			while(nodos.hasMoreResources()) {
				Resource nodo = nodos.nextResource();
				System.out.println(nodo.getContent());
			}
			
		} catch (XMLDBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
