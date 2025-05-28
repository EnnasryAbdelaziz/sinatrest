package eai.devass.sinistreat.appli.utils.serialisation;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

import eai.devass.sinistreat.appli.utils.ConverterTools;
import eai.devass.sinistreat.appli.valueobjects.serialisation.GlobalVO;
import eai.devass.sinistreat.appli.valueobjects.serialisation.ResultVO;

/* @author kchakib : 1 nov. 10 */
public class ZXstreamGlobalConverter implements Converter {

	ConverterTools converterTools = ConverterTools.getInstance();
	
	public void marshal(Object object, HierarchicalStreamWriter writer, MarshallingContext context) {
		
		GlobalVO globalVO = (GlobalVO) object;
		writer.startNode(globalVO.getEntete().getClass().getSimpleName().toLowerCase());
		context.convertAnother(globalVO.getEntete(), new XstreamSimpleConverter());
		writer.endNode();
		
		ResultVO resultVO = (ResultVO) globalVO.getObject();
		writer.startNode("object");
		writer.addAttribute("class", resultVO.getClass().getName());
		
		Map result = resultVO.getResult();
		Set<Entry<Object, Object>> setOfRows=result.entrySet();
		for ( Map.Entry<Object, Object> entry : setOfRows) {
			Object key = entry.getKey();
			Object value = entry.getValue();
			if(value == null){
				continue;
			}
			// Vérifier si c'est une liste
			if(converterTools.isCollection(value.getClass())) {
				List listInMap = (List) value;
				if(listInMap.isEmpty()) {
					continue;
				}
				
				// start node
				writer.startNode(key.toString());
				for(Object objectInList : listInMap) {
					writer.startNode(objectInList.getClass().getSimpleName());
					context.convertAnother(objectInList, new XstreamSimpleConverter());
					writer.endNode();
				}
				
				// End node        		
        		writer.endNode();
			}
			
			// Si c'est reference d'objet
			else if(converterTools.isReference(value.getClass())) {
				writer.startNode(value.getClass().getSimpleName());
        		context.convertAnother(value, new XstreamSimpleConverter());
        		writer.endNode();
			}
		}
		
		writer.endNode();
		if (globalVO.getPager()!=null) {
			writer.startNode(globalVO.getPager().getClass().getSimpleName().toLowerCase().replaceAll("vo", ""));
			context.convertAnother(globalVO.getPager(), new XstreamSimpleConverter());
			writer.endNode();
		}
		
	}

	public Object unmarshal(HierarchicalStreamReader arg0, UnmarshallingContext arg1) {
		return null;
	}

	public boolean canConvert(Class clazz) {
		if(clazz.equals(GlobalVO.class)) {
			return true;
		} else {
			return false;
		}
	}

}


