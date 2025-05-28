package eai.devass.sinistreat.appli.utils.serialisation;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.PropertyUtilsBean;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

import eai.devass.commun.appli.util.CommonUtils;
import eai.devass.gsr.appli.valueobjects.parametrage.ParamVO;
import eai.devass.sinistreat.appli.utils.ConverterTools;
import eai.devass.sinistreat.appli.utils.Fonctions;
import eai.devass.sinistreat.appli.valueobjects.parametrage.ParamATVO;

/* @author kchakib : 1 nov. 10 */

@SuppressWarnings("all")
public class XstreamSimpleConverter implements Converter {

	private ConverterTools converterTools = ConverterTools.getInstance();
	Logger logger = Logger.getLogger("loggerSINAT");	
	protected Fonctions functions = new Fonctions();
	private PropertyUtilsBean propertyUtilsBean = BeanUtilsBean.getInstance().getPropertyUtils();
	private CommonUtils commonUtils = CommonUtils.getInstance();

	public void marshal(Object value, HierarchicalStreamWriter writer,
			MarshallingContext context) {
		if (value.getClass().equals(String.class)
				|| converterTools.isPrimitive(value)) {
			writer.startNode("value");
			writer.setValue(String.valueOf(value));
			writer.endNode();
			return;
		} 
		
		Field[] fields = converterTools.getAllFields(value.getClass());
		for (Field field : fields) {

			// Get value
			Object valueField = null;
			try {
				valueField = propertyUtilsBean
						.getProperty(value, field.getName());
			} catch (Exception e) {
				continue;
			}
			if (valueField == null) {
				continue;
			}
			
			// C'est une liste
			if (converterTools.isCollection(field.getType())) {
				List listRefObject = (List) valueField;
				if (listRefObject.isEmpty()) {
					continue;
				}

				Object firstElement = listRefObject.get(0);

				// Start node
				writer.startNode(field.getName());
				writer.addAttribute("class", firstElement.getClass()
						.getName());
				for (Object objectInList : listRefObject) {
					writer.startNode(firstElement.getClass().getSimpleName());

					// Dans le cas d'une liste de String
					if (objectInList instanceof String) {
						writer.setValue((String) objectInList);
					} else {
						context.convertAnother(objectInList, this);
					}

					writer.endNode();
				}

				// End node
				writer.endNode();

			}

			// C'est une reference d'objet
			else if (converterTools.isReference(field.getType())) {
				writer.startNode(field.getName());
				writer.addAttribute("class", field.getType().getName());
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

	public Object unmarshal(HierarchicalStreamReader reader,
			UnmarshallingContext context) {

		Object curObject = context.get("curObject");
		Type typeField = null;
		String value = null;
		while (reader.hasMoreChildren()) {

			reader.moveDown();
			try {

				// GEt field from name node
				Field field = converterTools.getField(curObject.getClass(),
						reader.getNodeName());
				
				if (field == null) {
					reader.moveUp();
					continue;
				}

				// IS reference
				if (converterTools.isReference(field.getType())) {
					context.put("curObject", field.getType().newInstance());
					Object object = context.convertAnother(curObject,
							field.getType(), this);

					// Pour les objet instanceof IParamVO, il faut verifier l'id
					if (isNull(object)) {
						reader.moveUp();
						continue;
					}

					propertyUtilsBean.setProperty(curObject, 
							reader.getNodeName(), object);

				} else if (converterTools.isCollection(field.getType())) {
					String clazz = reader.getAttribute("class");
					if (clazz == null) {
						reader.moveUp();
						continue;
					}

					List list = new ArrayList();
					while (reader.hasMoreChildren()) {
						reader.moveDown();

						// Dans le cas d'une liste de String
						if ("java.lang.String".equals(clazz)) {
							list.add(reader.getValue());

						} else {
							context.put("curObject", Class.forName(clazz)
									.newInstance());
							Object object = context.convertAnother(curObject,
									field.getType(), this);

							list.add(object);
						}

						reader.moveUp();
					}
					propertyUtilsBean.setProperty(curObject, 
							reader.getNodeName(), list);

				} else {
					typeField = field.getGenericType();
					value = reader.getValue();
					if (StringUtils.isBlank(value)) {
						reader.moveUp();
						continue;
					}
					
					if (typeField.equals(Integer.class)
							|| typeField.equals(int.class)) {

						propertyUtilsBean.setProperty(curObject, 
								reader.getNodeName(), Integer.parseInt(value));
						
					} else if (typeField.equals(Long.class)
							|| typeField.equals(long.class)) {

						propertyUtilsBean.setProperty(curObject, 
								reader.getNodeName(), Long.parseLong(value));
						
					} else if (typeField.equals(Double.class)
							|| typeField.equals(double.class)) {

						propertyUtilsBean.setProperty(curObject, 
								reader.getNodeName(), Double.parseDouble(commonUtils
										.formatToDouble(value)));
						
					} else {
						propertyUtilsBean.setProperty(curObject, 
								reader.getNodeName(), reader.getValue());
					}

				}

			} catch (Exception e) {
				logger.fatal(e);
			}

			reader.moveUp();
		}
		return curObject;
	}

	public boolean canConvert(Class clazz) {
		return true;
	}

	private boolean isNull(Object object) {

		if (object instanceof ParamVO) {
			if (((ParamVO) object).getId() == 0) {
				return true;
			}
		}
		if (object instanceof ParamATVO) {
			if (commonUtils.isEmpty(((ParamATVO) object).getCode()) 
					|| ((ParamATVO) object).getCode().equals("0")) {
				return true;
			}
		}
		
		
		return false;
	}

}
