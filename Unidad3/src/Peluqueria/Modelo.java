package Peluqueria;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.Resource;
import org.xmldb.api.base.ResourceIterator;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.CollectionManagementService;
import org.xmldb.api.modules.XPathQueryService;



public class Modelo {
	private Collection coleccion = null;
	private String url = "xmldb:exist://localhost:8080/exist/xmlrpc/db",
		usuario="admin",
		clave="admin";
	public Modelo() {
		
		Class driver;
		try {
			driver = Class.forName("org.exist.xmldb.DatabaseImpl");
			Database db = (Database) driver.getDeclaredConstructor().newInstance();
			DatabaseManager.registerDatabase(db);
			
			Collection padre = DatabaseManager.getCollection(url,usuario,clave);
			if(padre!=null) {
				//Obtenemos colección peluquería
				coleccion = padre.getChildCollection("peluqueria");
				if(coleccion == null) {
					//Creamos servicio para crear colección peluquería
					CollectionManagementService servicio = 
							(CollectionManagementService) 
							padre.getService("CollectionManagementService", "1.0");
					coleccion = servicio.createCollection("peluqueria");
					if(coleccion!=null) {
						//Subir documentos xml
						//Clientes lo hemos creado a mano
						File fichero = new File("clientes.xml");
						Resource recurso = 
								coleccion.createResource(fichero.getName(), "XMLResource");
						recurso.setContent(fichero);
						coleccion.storeResource(recurso);
						//Citas lo hacemos todo desde java
						crearFicheroCitas();
						//Subimos fichero a la colección
						fichero = new File("citas.xml");
						recurso = 
								coleccion.createResource(fichero.getName(), "XMLResource");
						recurso.setContent(fichero);
						coleccion.storeResource(recurso);
					}
					else {
						System.out.println("Error: No se ha podido crear la colección");
					}
				}
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	private void crearFicheroCitas() {
		// TODO Auto-generated method stub
		BufferedWriter fichero = null;
		try {
			fichero = new BufferedWriter(
					new FileWriter("citas.xml",false));
			fichero.write("<citas></citas>");			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			if(fichero!=null) {
				try {
					fichero.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
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
	public ArrayList<Cliente> obtenerClientes() {
		// TODO Auto-generated method stub
		ArrayList<Cliente> resultado = new ArrayList();
		try {
			XPathQueryService servicio = (XPathQueryService)
					coleccion.getService("XPathQueryService", "1.0");
			ResourceSet r =  servicio.query("for $c in //cliente " + 
					"	return data($c/@dni)");
			ResourceIterator dnis  = r.getIterator();
			while(dnis.hasMoreResources()) {
				Cliente c = new Cliente();
				c.setDni(dnis.nextResource().getContent().toString());
				
				//Obtenemos el nombre del cliente
				ResourceSet r2 = 
						servicio.query("//cliente[@dni='"+c.getDni()+"']/nombre/text()");
				ResourceIterator nombre = r2.getIterator();
				if(nombre.hasMoreResources()) {
					c.setNombre(nombre.nextResource().getContent().toString());
				}
				//Obtenemos el teléfono del cliente
				r2 = 
						servicio.query("//cliente[@dni='"+c.getDni()+"']/telefono/text()");
				ResourceIterator telefono = r2.getIterator();
				if(telefono.hasMoreResources()) {
					c.setTelefono(telefono.nextResource().getContent().toString());
				}
				resultado.add(c);
			}
			
		} catch (XMLDBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return resultado;
	}
}
