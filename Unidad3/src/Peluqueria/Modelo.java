package Peluqueria;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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
	SimpleDateFormat formatoDia = new SimpleDateFormat("yyyy-MM-dd");
	SimpleDateFormat formatoHora = new SimpleDateFormat("hh:mm");
	SimpleDateFormat formatofecha = new SimpleDateFormat("yyyy-MM-dd hh:mm");
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
	public Cliente obtenerCliente(String dni) {
		// TODO Auto-generated method stub
		Cliente resultado = null;
		try {
			XPathQueryService servicio = (XPathQueryService)
					coleccion.getService("XPathQueryService", "1.0");
			ResourceSet r =  servicio.query("for $c in //cliente[@dni='"+dni+"'] " + 
					"	return data($c/@dni)");
			ResourceIterator dnis  = r.getIterator();
			if(dnis.hasMoreResources()) {
				resultado = new Cliente();
				resultado.setDni(dnis.nextResource().getContent().toString());
				
				//Obtenemos el nombre del cliente
				ResourceSet r2 = 
						servicio.query("//cliente[@dni='"+resultado.getDni()+"']/nombre/text()");
				ResourceIterator nombre = r2.getIterator();
				if(nombre.hasMoreResources()) {
					resultado.setNombre(nombre.nextResource().getContent().toString());
				}
				//Obtenemos el teléfono del cliente
				r2 = 
						servicio.query("//cliente[@dni='"+resultado.getDni()+"']/telefono/text()");
				ResourceIterator telefono = r2.getIterator();
				if(telefono.hasMoreResources()) {
					resultado.setTelefono(telefono.nextResource().getContent().toString());
				}
			}
			
		} catch (XMLDBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}
	public boolean altaCliente(Cliente c) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		try {
			XPathQueryService servicio = (XPathQueryService)
					coleccion.getService("XPathQueryService", "1.0");
			servicio.query("update insert "
					+ "<cliente dni='"+c.getDni()+"'>"
					+"<nombre>"+c.getNombre()+"</nombre>"
					+"<telefono>"+c.getTelefono()+"</telefono>"
					+ "</cliente> "
					+ "into /clientes");
			resultado = true;
		} catch (XMLDBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}
	public boolean crearCita(Cita cita) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		cita.setId(obtenerNuevoIdCita());
		XPathQueryService servicio;
		try {
			servicio = (XPathQueryService)
					coleccion.getService("XPathQueryService", "1.0");
			servicio.query("update insert "
					+ "<cita id='"+cita.getId()+"'>"
					+"<fecha>"+formatoDia.format(cita.getFecha())+"</fecha>"
					+"<hora>"+formatoHora.format(cita.getFecha())+"</hora>"
					+"<cliente>"+cita.getDni()+"</cliente>"
					+"<servicio>"+cita.getServicio()+"</servicio>"
					+"<tiempo>"+cita.getTiempo()+"</tiempo>"
					+ "</cita> "
					+ "into /citas");
			resultado = true;
		} catch (XMLDBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return resultado;
	}
	private int obtenerNuevoIdCita() {
		// TODO Auto-generated method stub
		int resultado = 1;
		try {
			XPathQueryService servicio = (XPathQueryService)
					coleccion.getService("XPathQueryService", "1.0");
			ResourceSet r = servicio.query("for $c in //cita[last()]" + 
					"	return data($c/@id)");
			ResourceIterator fila = r.getIterator();
			if(fila.hasMoreResources()) {
				resultado=Integer.parseInt(fila.nextResource().getContent().toString())+1;										
			}
		} catch (XMLDBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}
	public ArrayList<Cita> obtenerCitas(Date fecha) {
		// TODO Auto-generated method stub
		ArrayList<Cita> resultado = new ArrayList();
		
		try {
			XPathQueryService servicio = (XPathQueryService)
					coleccion.getService("XPathQueryService", "1.0");
			ResourceSet r = servicio.query("data(//cita[fecha='"+
					formatoDia.format(fecha)+"']/@id)");
			ResourceIterator citas = r.getIterator();
			while(citas.hasMoreResources()) {
				Cita c = new Cita();
				c.setId(Integer.parseInt(citas.nextResource().getContent().toString()));
				//Obtenemos hora
				ResourceSet r2 = servicio.query("data(//cita[@id='"+c.getId()+"']/hora)");
				ResourceIterator contenido = r2.getIterator();
				if(contenido.hasMoreResources()) {
					String fechaConHora = formatoDia.format(fecha) + " " + 
							contenido.nextResource().getContent().toString();
					c.setFecha(formatofecha.parse(fechaConHora));
				}
				//Dni cliente
				r2 = servicio.query("data(//cita[@id='"+c.getId()+"']/cliente)");
				contenido = r2.getIterator();
				if(contenido.hasMoreResources()) {
					c.setDni(contenido.nextResource().getContent().toString());
				}
				//Servicio
				r2 = servicio.query("data(//cita[@id='"+c.getId()+"']/servicio)");
				contenido = r2.getIterator();
				if(contenido.hasMoreResources()) {
					c.setServicio(contenido.nextResource().getContent().toString());
				}
				//tiempo
				r2 = servicio.query("data(//cita[@id='"+c.getId()+"']/tiempo)");
				contenido = r2.getIterator();
				if(contenido.hasMoreResources()) {
					c.setTiempo(Float.parseFloat(contenido.nextResource().getContent().toString()));
				}
				resultado.add(c);
				
				
			}
			
		} catch (XMLDBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return resultado;
	}
	public ArrayList<Cita> obtenerCitas() {
		// TODO Auto-generated method stub
		ArrayList<Cita> resultado = new ArrayList();
		
		try {
			XPathQueryService servicio = (XPathQueryService)
					coleccion.getService("XPathQueryService", "1.0");
			ResourceSet r = servicio.query("data(//cita/@id)");
			ResourceIterator citas = r.getIterator();
			while(citas.hasMoreResources()) {
				Cita c = new Cita();
				c.setId(Integer.parseInt(citas.nextResource().getContent().toString()));
				//Obtenemos fecha
				ResourceSet r2 = servicio.query("data(//cita[@id='"+c.getId()+"']/fecha)");
				ResourceIterator contenido = r2.getIterator();
				String fechaConHora="";
				if(contenido.hasMoreResources()) {					
					fechaConHora = contenido.nextResource().getContent().toString();
				}
				//Obtenemos hora
				r2 = servicio.query("data(//cita[@id='"+c.getId()+"']/hora)");
				contenido = r2.getIterator();
				if(contenido.hasMoreResources()) {					
					fechaConHora +=  " "+
							contenido.nextResource().getContent().toString();
					c.setFecha(formatofecha.parse(fechaConHora));
				}
				//Dni cliente
				r2 = servicio.query("data(//cita[@id='"+c.getId()+"']/cliente)");
				contenido = r2.getIterator();
				if(contenido.hasMoreResources()) {
					c.setDni(contenido.nextResource().getContent().toString());
				}
				//Servicio
				r2 = servicio.query("data(//cita[@id='"+c.getId()+"']/servicio)");
				contenido = r2.getIterator();
				if(contenido.hasMoreResources()) {
					c.setServicio(contenido.nextResource().getContent().toString());
				}
				//tiempo
				r2 = servicio.query("data(//cita[@id='"+c.getId()+"']/tiempo)");
				contenido = r2.getIterator();
				if(contenido.hasMoreResources()) {
					c.setTiempo(Float.parseFloat(contenido.nextResource().getContent().toString()));
				}
				resultado.add(c);
				
				
			}
			
		} catch (XMLDBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return resultado;
	}
	public Cita obtenerCitas(int id) {
		// TODO Auto-generated method stub
		Cita resultado = null;
		
		try {
			XPathQueryService servicio = (XPathQueryService)
					coleccion.getService("XPathQueryService", "1.0");
			ResourceSet r = servicio.query("data(//cita[@id='"+id+"'])");
			ResourceIterator citas = r.getIterator();
			if(citas.hasMoreResources()) {
				Cita c = new Cita();
				c.setId(id);
				//Obtenemos fecha
				ResourceSet r2 = servicio.query("data(//cita[@id='"+c.getId()+"']/fecha)");
				ResourceIterator contenido = r2.getIterator();
				String fechaConHora="";
				if(contenido.hasMoreResources()) {					
					fechaConHora = contenido.nextResource().getContent().toString();
				}
				//Obtenemos hora
				r2 = servicio.query("data(//cita[@id='"+c.getId()+"']/hora)");
				contenido = r2.getIterator();
				if(contenido.hasMoreResources()) {					
					fechaConHora +=  " "+
							contenido.nextResource().getContent().toString();
					c.setFecha(formatofecha.parse(fechaConHora));
				}
				//Dni cliente
				r2 = servicio.query("data(//cita[@id='"+c.getId()+"']/cliente)");
				contenido = r2.getIterator();
				if(contenido.hasMoreResources()) {
					c.setDni(contenido.nextResource().getContent().toString());
				}
				//Servicio
				r2 = servicio.query("data(//cita[@id='"+c.getId()+"']/servicio)");
				contenido = r2.getIterator();
				if(contenido.hasMoreResources()) {
					c.setServicio(contenido.nextResource().getContent().toString());
				}
				//tiempo
				r2 = servicio.query("data(//cita[@id='"+c.getId()+"']/tiempo)");
				contenido = r2.getIterator();
				if(contenido.hasMoreResources()) {
					c.setTiempo(Float.parseFloat(contenido.nextResource().getContent().toString()));
				}
				resultado=c;
				
				
			}
			
		} catch (XMLDBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return resultado;
	}
	public boolean modificarCita(Cita c) {
		// TODO Auto-generated method stub
		boolean resultado=false;
		try {
			XPathQueryService servicio = (XPathQueryService)
					coleccion.getService("XPathQueryService", "1.0");
			//Modicamos nodo fecha
			servicio.query("update replace //cita[@id='"+c.getId()+"']/fecha "
					+ "with <fecha>"+formatoDia.format(c.getFecha())+"</fecha>");
			//Modicamos nodo hora
			servicio.query("update replace //cita[@id='"+c.getId()+"']/hora "
					+ "with <hora>"+formatoHora.format(c.getFecha())+"</hora>");
			resultado=true;
			
		} catch (XMLDBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}
	public boolean borrarCita(Cita c) {
		// TODO Auto-generated method stub
		boolean resultado=false;
		try {
			XPathQueryService servicio = (XPathQueryService)
					coleccion.getService("XPathQueryService", "1.0");
			//Borramos nodo cita
			servicio.query("update delete //cita[@id='"+c.getId()+"']");
			
			resultado=true;
			
		} catch (XMLDBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}
	public void mostrarCitasCliente(String dni) {
		// TODO Auto-generated method stub
		try {
			XPathQueryService servicio = (XPathQueryService)
					coleccion.getService("XPathQueryService", "1.0");
			
			ResourceSet r = servicio.query("for $c in //cita,\r\n" + 
					"    $cli in //cliente[@dni=$c/cliente]\r\n" + 
					"where $c/cliente = \""+dni+"\"\r\n" + 
					"return <cita fecha=\"{$c/fecha}\" hora=\"{$c/hora}\">\r\n" + 
					"	<nombreCliente>{data($cli/nombre)}</nombreCliente>\r\n" + 
					"	{$c/servicio}\r\n" + 
					"	{$c/tiempo}\r\n" + 
					"</cita>");
			
			ResourceIterator citas = r.getIterator();
			while(citas.hasMoreResources()) {
				System.out.println(citas.nextResource().getContent().toString());
			}
			
			
			
		} catch (XMLDBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
