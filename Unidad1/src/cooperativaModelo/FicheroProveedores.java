package cooperativaModelo;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class FicheroProveedores {
	private String nombre;

	public FicheroProveedores(String nombre) {
		super();
		this.nombre = nombre;
	}

	public void mostrarProveedores() {
		// TODO Auto-generated method stub
		try {
			//Creamos el árbol del documento
			DocumentBuilder documentoC  = 
					DocumentBuilderFactory.newInstance().newDocumentBuilder();
			//Cargamos el fichero en el árbol
			Document documento = documentoC.parse(nombre);
			
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
