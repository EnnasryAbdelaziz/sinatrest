package eai.devass.sinistreat.appli.utils.serialisation;

import java.lang.reflect.Field;
import java.util.List;

import org.apache.commons.beanutils.BeanUtilsBean;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

import eai.devass.sinistreat.appli.utils.ConverterTools;

/* @author kchakib : 1 nov. 10 */
public class ZXstreamSimpleConverter implements Converter {

	ConverterTools converterTools = ConverterTools.getInstance();
	
    public void marshal(Object value, HierarchicalStreamWriter writer,
            MarshallingContext context) {
				
		Field[] fields = converterTools.getAllFields(value.getClass());
        for(Field field : fields) {
        	
        	// Get value
        	Object valueField = null;
        	try {
        		valueField = BeanUtilsBean.getInstance().getPropertyUtils()
						.getProperty(value, field.getName());
        		
        	} catch(Exception e) {
        		continue;
        	}
        	
        	if(valueField == null) {
				continue;
			}
        	
        	// C'est une liste
        	if(converterTools.isCollection(field.getType())) {
        		List listRefObject = (List) valueField;
        		if(listRefObject.isEmpty()) {
					continue;
				}
        		
        		Object firstElement = listRefObject.get(0);
        		        		
        		// Start node
        		writer.startNode(field.getName());
        		for(Object objectInList : listRefObject) {
        			writer.startNode(firstElement.getClass().getSimpleName());
        			context.convertAnother(objectInList, this);
        			writer.endNode();
        		}
        		
        		// End node
        		writer.endNode();
        		
        	}
        	
        	// C'est une reference d'objet
        	else if(converterTools.isReference(field.getType())) {
        		writer.startNode(field.getName());
        		context.convertAnother(valueField, this);
        		writer.endNode();
        	}
        	
        	else {
	        	// C'est un attribut simple
        		writer.startNode(field.getName());
	            writer.setValue(valueField.toString());
	            writer.endNode();
        	}
        }
	}

    public Object unmarshal(HierarchicalStreamReader arg0,
            UnmarshallingContext arg1) {
		return null;
	}

	public boolean canConvert(Class clazz) {
		return true;
	}

}
