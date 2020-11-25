package Peluqueria;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.Resource;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.CollectionManagementService;



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
}
