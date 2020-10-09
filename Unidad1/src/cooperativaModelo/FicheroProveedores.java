package cooperativaModelo;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
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
	
	
}
