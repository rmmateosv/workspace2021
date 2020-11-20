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
	public void mostrarProducto(int codigo) {
		// TODO Auto-generated method stub
		try {
			XPathQueryService servicio = 
					(XPathQueryService) 
					coleccion.getService("XPathQueryService", "1.0");
			ResourceSet r = servicio.query("/productos/product[codigo=" + 
					codigo+"]");
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
	public boolean insertarProducto(String cat, float precio, String nombre, int stock) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		int codigo = obtenerNuevoCodigoP();
		
		try {
			XPathQueryService servicio = (XPathQueryService) 
					coleccion.getService("XPathQueryService", "1.0");
			
			ResourceSet r = servicio.query("update insert "
					+ "<product categoria='"+cat+"' pvp='"+precio+"'>" +
					"<codigo>"+codigo+"</codigo>"+
					"<nombre>"+nombre+"</nombre>"+
					"<stock>"+stock+"</stock>"+
					"</product> "
					+ "into /productos");
			resultado = true;
			
		} catch (XMLDBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return resultado;
	}
	private int obtenerNuevoCodigoP() {
		// TODO Auto-generated method stub
		int resultado = 1;
		
		try {
			XPathQueryService servicio = (XPathQueryService) 
					coleccion.getService("XPathQueryService", "1.0");
			ResourceSet r = 
					servicio.query("/productos/product[last()]/codigo/text()");
			ResourceIterator nodo = r.getIterator();
			if(nodo.hasMoreResources()) {
				resultado = Integer.parseInt(nodo.nextResource().getContent().toString())+1;
			}
			
		} catch (XMLDBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return resultado;
	}
	public int obtenerStock(int codigo) {
		// TODO Auto-generated method stub
		int resultado = -1;
		try {
			XPathQueryService servicio = 
					(XPathQueryService) 
					coleccion.getService("XPathQueryService", "1.0");
			ResourceSet r = servicio.query("/productos/product[codigo=" + 
					codigo+"]/stock/text()");
			ResourceIterator nodos = r.getIterator();
			if(nodos.hasMoreResources()) {
				resultado = Integer.parseInt(nodos.nextResource().getContent().toString());
			}
			
		} catch (XMLDBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}
	public boolean modificarStock(int codigo, int cantidad) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		try {
			XPathQueryService servicio = 
					(XPathQueryService) 
					coleccion.getService("XPathQueryService", "1.0");
			servicio.query("update replace /productos/product[codigo="+codigo+"]/stock " +
					"with <stock>"+cantidad+"</stock>");
			resultado=true;
			
			
		} catch (XMLDBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}
	public boolean existe(int codigo) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		try {
			XPathQueryService servicio = 
					(XPathQueryService) 
					coleccion.getService("XPathQueryService", "1.0");
			ResourceSet r = servicio.query("/productos/product[codigo=" + 
					codigo+"]");
			ResourceIterator nodos = r.getIterator();
			if(nodos.hasMoreResources()) {
				resultado = true;
			}
			
		} catch (XMLDBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}
	public boolean borrarProducto(int codigo) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		try {
			XPathQueryService servicio = 
					(XPathQueryService) 
					coleccion.getService("XPathQueryService", "1.0");
			servicio.query("update delete /productos/product[codigo="+codigo+"]");
			resultado=true;
			
			
		} catch (XMLDBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}
	public void mostrarFacturas(int codigo) {
		// TODO Auto-generated method stub
		try {
			XPathQueryService servicio = 
					(XPathQueryService) 
					coleccion.getService("XPathQueryService", "1.0");
			ResourceSet r = servicio.query("for $f in /facturas/factura[numcliente="+codigo+"],\r\n" + 
					"    $c in /clientes/clien[@numero=$f/numcliente]\r\n" + 
					"return <factura numCliente=\"{$c/@numero}\" codigoF=\"{$f/@numero}\">\r\n" + 
					"    {$c/nombre}\r\n" + 
					"    {$f/fecha}\r\n" + 
					"</factura>");
			ResourceIterator facturas = r.getIterator();
			while(facturas.hasMoreResources()) {
				System.out.println(facturas.nextResource().getContent());
			}
			
			
			
		} catch (XMLDBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
