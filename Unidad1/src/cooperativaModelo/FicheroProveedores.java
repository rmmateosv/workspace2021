package cooperativaModelo;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

public class FicheroProveedores {
	private String nombre;
	private SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");

	public FicheroProveedores(String nombre) {
		super();
		this.nombre = nombre;
	}

	public ArrayList<Proveedor> obtenerProveedores() {
		// TODO Auto-generated method stub
		ArrayList<Proveedor> resultado = new ArrayList<>();
		try {
			//Creamos el árbol del documento
			DocumentBuilder documentoC  = 
					DocumentBuilderFactory.newInstance().newDocumentBuilder();
			//Cargamos el fichero en el árbol
			Document documento = documentoC.parse(nombre);
			
			//Obtener el nodo raiz del documento
			Element raiz = documento.getDocumentElement();
			
			
			//Obtenemos los hijos de la raiz (Elemento Proveedor)
			NodeList proveedores = raiz.getChildNodes();
			for(int i=0; i<proveedores.getLength();i++) {
				Proveedor p = new Proveedor();
				
				//RELLENAMOS EL CÓDGIO DEL PROVEEDOR
				//Obtenemos los atributos del elemento proveedor
				NamedNodeMap atributos = proveedores.item(i).getAttributes();
				Element n = (Element) proveedores.item(i);
				//Obtenemos el atributo 0, que es el código
				p.setCodigo(Integer.parseInt(n.getAttribute("id")));
				
				//OBTENEMOS LOS HIJOS DEL ELEMENTO PROVEEDOR
				//PARA RELLENAR EL RESTO DE ATRIBUTOS DE p
				NodeList datosProvedor = proveedores.item(i).getChildNodes();
				//Nombre: Obtenemos el valor del nodo hijo (textnode) del nodo nombre
				p.setNombre(datosProvedor.item(0).getChildNodes().item(0).getNodeValue());
				//Fecha
				String fecha = datosProvedor.item(2).getChildNodes().item(0).getNodeValue();
				p.setFecha_pedido(formato.parse(fecha));
				//Baja
				p.setBaja(Boolean.parseBoolean(datosProvedor.item(3)
						.getChildNodes().item(0).getNodeValue()));
				//Teléfonos
				NodeList telefonos = datosProvedor.item(1).getChildNodes();
				for(int j=0;j<telefonos.getLength();j++) {
					p.getTelefonos().add(telefonos.item(j).getChildNodes()
							.item(0).getNodeValue());
				}
				//Añadimos el p a resultado
				resultado.add(p);
			}

			
			
			
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}

	public int obtenerNuevoCodigo() {
		// TODO Auto-generated method stub
		int resultado = 0;
		
		File fichero = new File(nombre);
		if(!fichero.exists()) {
			resultado = 1;
		}
		else {			
			try {
				//Cargamos el fichero en un árbol DOM
				Document documento = DocumentBuilderFactory.
						newInstance().newDocumentBuilder().parse(nombre);
				//Obtenemos raíz
				Element raiz = documento.getDocumentElement();
				//Obtenemos el último nodo provedor
				Element ultimo = (Element) raiz.getLastChild();
				resultado = Integer.parseInt(ultimo.getAttribute("id"))+1;
				
			
			} catch (SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParserConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return resultado;
	}

	public boolean altaProveedor(Proveedor pr) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		try {
			Document documento = DocumentBuilderFactory.
					newInstance().newDocumentBuilder().parse(nombre);
			Element raiz = documento.getDocumentElement();
			
			//Creamos el nodo elemento proveedor
			Element proveedor = documento.createElement("proveedor");
			//Añadimos proveedor a la raiz
			raiz.appendChild(proveedor);
			//Rellenamos el atributo id de proveedor
			proveedor.setAttribute("id", String.valueOf(pr.getCodigo()));
			
			//Nombre
			Element nombre = documento.createElement("nombre");
			proveedor.appendChild(nombre);
			//Nodo Text con el valor del nombre
			Text textoNombre = documento.createTextNode(pr.getNombre());
			nombre.appendChild(textoNombre);
			
			//Teléfonos
			Element telefonos = documento.createElement("telefonos");
			proveedor.appendChild(telefonos);
			
			//Teléfono
			Element telefono = documento.createElement("telefono");
			telefonos.appendChild(telefono);
			//Texto con el tlf
			Text textoTelf = documento.createTextNode(pr.getTelefonos().get(0));
			telefono.appendChild(textoTelf);
			
			//Fecha
			Element fecha = documento.createElement("fecha_pedido");
			proveedor.appendChild(fecha);
			//Nodo Text con el valor de la fecha
			Text textoFecha = documento.createTextNode(formato.format(pr.getFecha_pedido()));
			fecha.appendChild(textoFecha);
			
			//Baja
			Element baja = documento.createElement("baja");
			proveedor.appendChild(baja);
			//Nodo Text con el valor baja
			Text textoBaja = documento.createTextNode(Boolean.toString(pr.isBaja()));
			baja.appendChild(textoBaja);
			
			//Guardar el DOM en un fichero
			guardarDOM(documento);
			
			resultado = true;
			
			
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return resultado;
	}

	private void guardarDOM(Document documento) {
		// TODO Auto-generated method stub
		
		try {
			//Establecemos el árbol a guardar
			Source fuente = new DOMSource(documento);
			//Establecemos el fichero destion
			Result destino = new StreamResult(new File(nombre));
			//Creamos el transformador
			Transformer t = TransformerFactory.newInstance().newTransformer();
			//Creamos el nuevo fichero
			t.transform(fuente, destino);
			
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerFactoryConfigurationError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	public Proveedor obtenerProveedor(int codigo) {
		// TODO Auto-generated method stub
		Proveedor resultado = null;
		
			Document documento;
			try {
				documento = DocumentBuilderFactory.
						newInstance().newDocumentBuilder().parse(nombre);
				Element raiz = documento.getDocumentElement();
				//Obtenemos los elementos proveedor
				NodeList proveedores = raiz.getChildNodes();
				for(int i=0;i<proveedores.getLength();i++) {
					//comprobamos si el código es el buscado
					int codigoXML = Integer.parseInt(
							proveedores.item(i)
							.getAttributes().item(0)
							.getNodeValue());
					if(codigoXML == codigo) {
						resultado = new Proveedor();
						resultado.setCodigo(codigoXML);
						//Nombre
						resultado.setNombre(proveedores.item(i)
								.getChildNodes().item(0)
								.getChildNodes().item(0).getNodeValue());
						//Telefonos
						for(int j=0;j<proveedores.item(i)
								.getChildNodes().item(1)
								.getChildNodes().getLength();j++) {
							resultado.getTelefonos().add(proveedores.item(i)
									.getChildNodes().item(1)
									.getChildNodes().item(j)
									.getChildNodes().item(0) //!!!!
									.getNodeValue());
						}
						//Fecha
						resultado.setFecha_pedido(formato.parse(proveedores.item(i)
								.getChildNodes().item(2)
								.getChildNodes().item(0).getNodeValue())
								);
						//Baja
						resultado.setBaja(Boolean.parseBoolean(proveedores.item(i)
								.getChildNodes().item(3)
								.getChildNodes().item(0).getNodeValue()));
						
						return resultado;
						
					}
				}
			} catch (SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParserConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (DOMException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		
		return resultado;
	}
	
	
}
