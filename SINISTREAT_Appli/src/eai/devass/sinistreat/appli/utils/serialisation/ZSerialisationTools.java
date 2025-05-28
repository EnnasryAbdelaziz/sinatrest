package eai.devass.sinistreat.appli.utils.serialisation;

import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import eai.devass.sinistreat.appli.valueobjects.metier.reglement.DetailReglementVO;
import eai.devass.sinistreat.appli.valueobjects.metier.reglement.OrdonnancementVO;
import eai.devass.sinistreat.appli.valueobjects.serialisation.GlobalVO;

public class ZSerialisationTools {
	private static String FLUX_NODE = "flux";

	public static String serialize(GlobalVO globalVO) throws Exception {
		XStream xstream = new XStream(new DomDriver());
		// Utiliser le converter de l'objet GlobalVO
		xstream.registerConverter(new XstreamGlobalConverter());
		xstream.alias(FLUX_NODE, GlobalVO.class);
		String xml = xstream.toXML(globalVO);
		return xml;
	}

	public static GlobalVO deserialize(String xml) {
		GlobalVO vo = null;
		// Instanciation de la classe XStream
		XStream xstream = new XStream(new DomDriver());

		initialiseXStream(xstream);
		// objet vo
		vo = (GlobalVO) xstream.fromXML(xml);

		return vo;
	}

	private static void initialiseXStream(XStream xstream) {
		Properties properties = new Properties();
        try {
			xstream.alias(FLUX_NODE, GlobalVO.class);		
			xstream.alias("detailReglement", DetailReglementVO.class);
			xstream.alias("refOrdonnancement", OrdonnancementVO.class);	
			
            InputStream inputStream = ZSerialisationTools.class
                    .getClassLoader().getResourceAsStream(
                            "serialisation.properties");
			properties.load(inputStream);
			Enumeration em = properties.keys();
			
			while(em.hasMoreElements()){
				String alias = (String)em.nextElement();
                xstream.alias(alias,
                        Class.forName((String) properties.get(alias)));
			}
		
        } catch (Exception e) {
		}
	}
	
}
