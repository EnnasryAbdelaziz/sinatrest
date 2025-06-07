package eai.devass.sinistreat.appli.utils.serialisation;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;

import org.apache.log4j.Logger;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

import eai.devass.sinistreat.appli.authentification.Utilisateur;
import eai.devass.sinistreat.appli.utils.ConverterTools;
import eai.devass.sinistreat.appli.utils.Fonctions;
import eai.devass.sinistreat.appli.valueobjects.serialisation.Entete;
import eai.devass.sinistreat.appli.valueobjects.serialisation.GlobalVO;
import eai.devass.sinistreat.appli.valueobjects.serialisation.PagerVO;
import eai.devass.sinistreat.appli.valueobjects.serialisation.ResultVO;

/* @author kchakib : 1 nov. 10 */
@SuppressWarnings("all")
public class XstreamGlobalConverter implements Converter {

	//Tools commonTools = Tools.getInstance();
	Logger logger = Logger.getLogger("loggerSINAT");
	ConverterTools converterTools = ConverterTools.getInstance();
	protected Fonctions functions = new Fonctions();

	public void marshal(Object object, HierarchicalStreamWriter writer,
			MarshallingContext context) {

		GlobalVO globalVO = (GlobalVO) object;
		writer.startNode(globalVO.getEntete().getClass().getSimpleName()
				.toLowerCase());
		context.convertAnother(globalVO.getEntete(),
				new XstreamSimpleConverter());
		writer.endNode();

		ResultVO resultVO = (ResultVO) globalVO.getObject();
		writer.startNode("object");
		writer.addAttribute("class", resultVO.getClass().getName());

		Map result = resultVO.getResult();
		//sonar Dead store to local variable 
		//Collection keys = result.keySet();
		
		
		Set<Entry<Object, Object>> setOfRows=result.entrySet();
		for ( Map.Entry<Object, Object> entry : setOfRows) {
			Object key = entry.getKey();
			Object value = entry.getValue();
			if (value == null){
				continue;
			}
			// Vérifier si c'est une liste
			if (converterTools.isCollection(value.getClass())) {
				List listInMap = (List) value;
				if (listInMap.isEmpty()){
					continue;
				}
				// start node
				writer.startNode(key.toString());
				for (Object objectInList : listInMap) {
					writer.startNode(objectInList.getClass().getSimpleName());
					context.convertAnother(objectInList,
							new XstreamSimpleConverter());
					writer.endNode();
				}

				// End node
				writer.endNode();
			}

			// Si c'est reference d'objet
			else if (converterTools.isReference(value.getClass())) {
				//correction sonar Call to equals() comparing different types
				if (value.getClass().equals(PagerVO.class)){
					continue;
				}
				writer.startNode(value.getClass().getSimpleName());
				context.convertAnother(value, new XstreamSimpleConverter());
				writer.endNode();
			}
			// Si string ou boolean
			else if (value.getClass().getName()
					.equals(String.class.getName())
					|| value.getClass().getName()
							.equals(Boolean.class.getName())) {
				writer.startNode(key.toString());
				writer.setValue(value.toString());
				writer.endNode();
        		
			} else {
				writer.startNode(String.valueOf(key));
        		writer.setValue(String.valueOf(value));
        		writer.endNode();
			}
		}

		writer.endNode();

		if (globalVO.getPager() != null) {
			writer.startNode(globalVO.getPager().getClass().getSimpleName()
					.toLowerCase().replaceAll("vo", ""));
			context.convertAnother(globalVO.getPager(),
					new XstreamSimpleConverter());
			writer.endNode();
		}

	}

	public Object unmarshal(HierarchicalStreamReader reader,
			UnmarshallingContext context) {

		GlobalVO globalVO = new GlobalVO();
		while (reader.hasMoreChildren()) {
			reader.moveDown();

			try {
				if ("entete".equals(reader.getNodeName())) {
					Entete entete = new Entete();
					context.put("curObject", entete);
					Object object = context.convertAnother(globalVO,
							entete.getClass(), new XstreamSimpleConverter());
					globalVO.setEntete(entete);

				} else if ("user".equals(reader.getNodeName())) {
					Utilisateur user = new Utilisateur();
					context.put("curObject", user);
					Object object = context.convertAnother(globalVO,
							user.getClass(), new XstreamSimpleConverter());
					globalVO.setUser(user);
				} else if ("object".equals(reader.getNodeName())) {
					String clazz = reader.getAttribute("class");
					if (clazz == null)
					{
						return null;
					}

					Object curObject = Class.forName(clazz).newInstance();
					context.put("curObject", curObject);
					Object object = context.convertAnother(globalVO,
							Class.forName(clazz), new XstreamSimpleConverter());
					globalVO.setObject((IValueObject) curObject);
				} else if ("pager".equals(reader.getNodeName())) {
					PagerVO pager = new PagerVO();
					context.put("curObject", pager);
					Object object = context.convertAnother(globalVO,
							pager.getClass(), new XstreamSimpleConverter());
					globalVO.setPager(pager);
				}

			} catch (Exception e) {
				logger.fatal(e);
			}

			reader.moveUp();
		}

		return globalVO;
	}

	public boolean canConvert(Class clazz) {
		if (clazz.equals(GlobalVO.class))
		{
			return true;
		} else
		{
			return false;
		}
	}

}
