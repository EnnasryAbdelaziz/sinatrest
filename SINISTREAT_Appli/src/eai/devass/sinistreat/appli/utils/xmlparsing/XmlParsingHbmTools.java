package eai.devass.sinistreat.appli.utils.xmlparsing;

import java.io.File;
import java.net.URL;
import java.util.Iterator;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;


/* @author kchakib : 10 déc. 10 */
public class XmlParsingHbmTools {
	
	private static XmlParsingHbmTools instance;
	private Document document;
	private Element racine;
	
	public static XmlParsingHbmTools getInstance(){
		if (instance == null) {
			instance = new XmlParsingHbmTools();
		}
		
		return instance; 
	}
	
	public String getPropertyName(String nameColumn, String className, String mappingPath) {
		
		// On crée une instance de SAXBuilder
		SAXBuilder sxb = new SAXBuilder();
		sxb.setEntityResolver(new EntityResolverImpl());
		
		try {
			// Récupérer le path physique du fichier hbm
			URL urlFileHbm = this.getClass().getResource("/mapping/" + mappingPath + "/" + className +".hbm.xml");
			document = sxb.build(new File(urlFileHbm.getFile()));
		
		} catch (Exception e) {
			return null;
		}

		// On initialise un nouvel élément racine avec l'élément racine du
		// document.
		racine = document.getRootElement();

		// Méthode définie dans la partie 3.2. de cet article
		return findPropertName(nameColumn);
	}
	
	private String findPropertName(String columnFilter) {
		
		// On crée une List contenant tous les noeuds "etudiant" de l'Element racine
		Element elmClass = racine.getChild("class");
		
		List listAllProperty = elmClass.getChildren();
		String curColumnName = null;
		
		// On crée un Iterator sur notre liste
		Iterator i = listAllProperty.iterator();
		while (i.hasNext()) {
			// On recrée l'Element courant à chaque tour de boucle afin de
			// pouvoir utiliser les méthodes propres aux Element comme :
			// selectionner un noeud fils, modifier du texte, etc...
			Element courant = (Element) i.next();
			
			// Ne pas prendre en concideration l'element 'id'
			if("id".equals(courant.getName())) {
				continue;
			}
			
			// On affiche le nom de l'element courant
			try {
				// Cas1 : courant est de type "bag" relation 'one-to-many'
				if("bag".equals(courant.getName())) {
					// Cas2 : "colum" est un child de "property"
					if(courant.getChild("key") != null) {
						curColumnName = courant.getChild("key").getAttributeValue("column");
						if(columnFilter.equals(curColumnName)) {
							return courant.getAttributeValue("name");
						}
					}
				} else {
					// Pour les autres : 'property' et 'many-to-one'
					if(courant.getAttribute("column") != null) {
						curColumnName = courant.getAttributeValue("column");
						if(columnFilter.equals(curColumnName)) {
							return courant.getAttributeValue("name");
						}
					}
					
					// Cas2 : "colum" est un child de "property"
					if(courant.getChild("column") != null) {
						curColumnName = courant.getChild("column").getAttributeValue("name");
						if(columnFilter.equals(curColumnName)) {
							return courant.getAttributeValue("name");
						}
					}
				}
				
			} catch(Exception e) {
				continue;
			}
		}
		
		return null;
	}
	

}


