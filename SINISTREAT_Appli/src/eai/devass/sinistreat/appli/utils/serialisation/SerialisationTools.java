package eai.devass.sinistreat.appli.utils.serialisation;

import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import eai.devass.sinistreat.appli.valueobjects.serialisation.GlobalVO;


public class SerialisationTools {
	
	private static String FLUX_NODE = "flux";
	
	public static String objectToXml(GlobalVO globalVO) throws Exception {
		
		XStream xstream = new XStream(new DomDriver());

		// Utiliser le converter de l'objet GlobalVO
		xstream.registerConverter(new XstreamGlobalConverter());
		xstream.alias(FLUX_NODE, GlobalVO.class);
		return xstream.toXML(globalVO);
		
	}

	public static GlobalVO xmlToObject(String xml) {
		
		GlobalVO vo = null;
		// Instanciation de la classe XStream
		XStream xstream = new XStream(new DomDriver());
		xstream.registerConverter(new XstreamGlobalConverter());
		xstream.alias(FLUX_NODE, GlobalVO.class);		
		initialiseXStream(xstream);
		
		// objet vo
		vo = (GlobalVO) xstream.fromXML(xml);

		return vo;
	}

	private static void initialiseXStream(XStream xstream) {
		Properties properties = new Properties();
		try 
		{	
			InputStream inputStream = SerialisationTools.class.getClassLoader().getResourceAsStream("serialisation.properties");
			properties.load(inputStream);
			Enumeration em = properties.keys();
			
			while(em.hasMoreElements()){
				String alias = (String)em.nextElement();
				xstream.alias(alias, Class.forName((String) properties.get(alias)));
			}
		} 
		catch (Exception e) 
		{
		}
	}
	
}
