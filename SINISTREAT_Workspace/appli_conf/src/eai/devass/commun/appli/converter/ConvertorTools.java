package eai.devass.commun.appli.converter;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.lang.ClassUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.proxy.HibernateProxy;

import eai.devass.commun.appli.exeptions.ExceptionMetier;
import eai.devass.commun.appli.util.CommonUtils;
import eai.devass.commun.appli.util.DateUtils;
/** @author kchakib */


@SuppressWarnings("all")
public class ConvertorTools {
	
	private static ConvertorTools instance;
	private static final String DEFAULT_PATTERNDATE = "dd/MM/yyyy";
	private static final String TRUE = "true";
	private static final String FALSE = "false";
	private static final String DEFAULT_PATTERNDOUBLE = "#0.00";
	private static final String PCK_CUSTMORCONVERTER = "eai.devass.gsr.appli.valueobjects.customerconverter.";
	private CommonUtils commonUtils = CommonUtils.getInstance();
	private ThreadLocal<Boolean> inverseConvert = new ThreadLocal<Boolean>();
				
	public static ConvertorTools getInstance(){
		if (instance == null) {
			instance = new ConvertorTools();
		}
		
		return instance; 
	}
	
	/** Convert Object A to Objetc B */
	public Object convertToObject(Object object) throws Exception {
		
		Object entiteDest = null;
		inverseConvert.set(false);
		try {
			if(object == null) { 
				return null;
			}
			
			// REcuperer l'entite dest.
			AConverter annEntiteDest = object.getClass().getAnnotation(AConverter.class);
			if(annEntiteDest == null) {
				throw new Exception("L'annotaion AConverter Entite n'est pas déclarée");
			}
			
			String classNameEntiteDest = annEntiteDest.entiteDist();
			if(StringUtils.isBlank(classNameEntiteDest)) {
				throw new Exception("L'entité destinatride est obligatoire");
			}
			
			Object value = null;
			try {
				Class classEntiteDest = Class.forName(classNameEntiteDest);
				entiteDest = classEntiteDest.newInstance();
			
			} catch(Exception e) {
				throw new Exception("Class introuvable : " + classNameEntiteDest);
			}
			
			Field[] fields = commonUtils.getAllFields(object.getClass(), new Field[] {});			
			AConverter converterFiled = null;
			String customerConverter = null;
			String propertyDestName = null;
			String propertyOrigName = null;
			ICustomerConverter customerConverterObj = null;
			for(Field curField : fields) {
				converterFiled = curField.getAnnotation(AConverter.class);
				if(converterFiled == null) {
					continue;
				}
				
				// propertyDestName = val1|val2 ... 
				propertyDestName = converterFiled.propertyDist();
				if(StringUtils.isBlank(propertyDestName)) {
					continue;
				}
				
				propertyOrigName = converterFiled.propertyOrig();
				if(StringUtils.isBlank(propertyOrigName)) {
					propertyOrigName = curField.getName();
				}
				
				// Verifier si il a un custmer converters !!!
				value = null;
				customerConverter = converterFiled.customerConverter();
				if(!StringUtils.isBlank(customerConverter)) {
					try {
						customerConverterObj = getCustomerConverter(customerConverter);
						if(customerConverterObj == null) {
							continue;
						}
						value = customerConverterObj.getValuePropertyDest(object);
						BeanUtilsBean.getInstance().setProperty(entiteDest, propertyDestName, value);
						continue;
						
					} catch (Exception e) {
						continue;
					}
				}
				else {
					try {
						value = commonUtils.getValueFromObject(object, propertyOrigName);
						
					} catch(Exception e) {
						continue;
					}
				}
				
				// Null value
				if (value == null) {
					continue;
				}
				
				if(value instanceof String) {
					if(commonUtils.isEmpty(String.valueOf(value))) {
						continue;
					}
				}
				
				// Il faur setter la valeur dans la property dest
				try {
					setValuePropertyDest(entiteDest, propertyDestName,
							converterFiled.pattern(), value);
					
				} catch(Exception e) {
					continue;
				}
			}
			return entiteDest;
			
		} catch(Exception e) {
			throw e;
		}
	}
	
	
	/** Convert Object A to Objetc B, le mapping des conversion est declare dans objB */
	public Object inverseConvertToObject(Object objectA) throws Exception {
		
		Object objectB = null;
		inverseConvert.set(true);
		try {
			if(objectA == null) {
				return null;
			}
			
			AConverter annEntiteDest = objectA.getClass().getAnnotation(AConverter.class);
			if(annEntiteDest == null) {
				throw new Exception("L'annotaion AConverter Entite n'est pas déclarée");
			}
			
			String classNameEntiteMapping = annEntiteDest.entiteMapping();
			if(StringUtils.isBlank(classNameEntiteMapping)) {
				throw new Exception("L'entité de mapping est obligatoire");
			}
			
			Class clazz = null;
			try {
				clazz = Class.forName(classNameEntiteMapping);
				
			} catch(Exception e) {
				throw new ExceptionMetier("Class introuvable : " + classNameEntiteMapping);
			}
			
			objectB = clazz.newInstance();
			Field[] fields = commonUtils.getAllFields(clazz, new Field[] {});
			AConverter converterFiled = null;
			String customerConverter = null;
			String propertyDestName = null;
			String propertyOrigName = null;
			Object value = null;
			ICustomerConverter customerConverterObj = null;
			for(Field curField : fields) {
				converterFiled = curField.getAnnotation(AConverter.class);
				if(converterFiled == null) {
					continue;
				}
				
				// propertyDestName = val1|val2 ... 
				propertyDestName = converterFiled.propertyDist();
				if(StringUtils.isBlank(propertyDestName)) {
					continue;
				}
				
				propertyOrigName = converterFiled.propertyOrig();
				if(StringUtils.isBlank(propertyOrigName)) {
					propertyOrigName = curField.getName();
				}
				
				// Verifier si il a un custmer converters !!!
				value = null;
				customerConverter = converterFiled.customerConverter();
				if(!StringUtils.isBlank(customerConverter)) {
					try {
						customerConverterObj = getCustomerConverter(customerConverter);
						if(customerConverterObj == null) {
							continue;
						}
						value = customerConverterObj.getValuePropertyOrig(objectA);
					} catch (Exception e) {
						continue;
					}
					
				} else {
					try {
						value = commonUtils.getValueFromObject(objectA, propertyDestName);
						
					} catch(Exception e) {
						continue;
					}
				}
				
				if(value == null) {
					continue;
				}
				
				try {
					setValuePropertyDest(objectB, propertyOrigName, 
							converterFiled.pattern(), value);
					
				} catch(Exception e) {
					continue;
				}
			}
			
			return objectB;
			
		} catch(Exception e) {
			throw new Exception("Probléme lors de la converion : " + e.getMessage());
		}
		
		
		
	}
	
	
	private void setValuePropertyDest(Object entiteDest, String propertyName, String pattern,
			Object value) throws Exception {
		
		
		Field field = null;
				
		// Reference d'objet : @AConverter(propertyDest="refQuittance.numQuittance")
		if(propertyName.indexOf(".") > 0) {			
			StringTokenizer stTokenizer = new StringTokenizer(propertyName, ".");
			String nameObj = stTokenizer.nextToken();
			String nameProperty = propertyName.substring(nameObj.length() + 1, propertyName.length());			
			
			// Recuperer la valeur du obj, dans le ou il est null, il faut l'instancier
			Object valueObj = BeanUtilsBean.getInstance().getPropertyUtils()
					.getProperty(entiteDest, nameObj);
			if(valueObj == null) {
				Class classFielsObj = commonUtils.getField(entiteDest.getClass(), nameObj).getType();
				valueObj = classFielsObj.newInstance();
				BeanUtilsBean.getInstance().setProperty(entiteDest, nameObj, valueObj);
			}
			
			setValuePropertyDest(valueObj, nameProperty, pattern, value);
			
			
		} else {
			
			field = commonUtils.getField(entiteDest.getClass(), propertyName);
			if(field == null) {
				return;
			}
			
			Type typeField = field.getGenericType();
			if(typeField.equals(String.class)) {
				pattern = (pattern != null) ? pattern : DEFAULT_PATTERNDATE;
				if(inverseConvert.get()) {
					pattern = DEFAULT_PATTERNDATE;
				}
				
				if(value instanceof Date) {
					DateFormat dateFormat = new SimpleDateFormat(pattern);
					BeanUtilsBean.getInstance().setProperty(entiteDest,
							propertyName, dateFormat.format((Date) value));
					
				} else if(value instanceof Calendar) {
					DateFormat dateFormat = new SimpleDateFormat(pattern);
					BeanUtilsBean.getInstance().setProperty(entiteDest,
							propertyName, dateFormat.format(((Calendar) value).getTime()));
					
				} else {
					BeanUtilsBean.getInstance().setProperty(entiteDest,
							propertyName, String.valueOf(value));
				}
				
			} else if(typeField.equals(Integer.class)) {
				if(StringUtils.isNumeric(String.valueOf(value))) {
					BeanUtilsBean.getInstance().setProperty(entiteDest,
							propertyName, Integer.valueOf(String.valueOf(value)));
				}
				
			} else if(typeField.equals(Boolean.class)) {
				if(TRUE.equals(value)) {
					BeanUtilsBean.getInstance().setProperty(entiteDest,
							propertyName, true);
				}else if(FALSE.equals(value)) {
					BeanUtilsBean.getInstance().setProperty(entiteDest,
							propertyName, false);
				}
				
			} else if(typeField.equals(long.class) || typeField.equals(Long.class)) {
				if(StringUtils.isNumeric(String.valueOf(value))) {
					BeanUtilsBean.getInstance().setProperty(entiteDest,
							propertyName, Long.valueOf(String.valueOf(value)));
				}
				
			} else if(typeField.equals(Double.class)) {
				if(commonUtils.isDouble(String.valueOf(value))) {
					BeanUtilsBean.getInstance().setProperty(entiteDest,
							propertyName, commonUtils.stringToDouble(String.valueOf(value)));
				}
				
			} else if(typeField.equals(Date.class)) {
				if(value instanceof Date) {
					BeanUtilsBean.getInstance().setProperty(entiteDest,
							propertyName, value);
				} else {
					pattern = DateUtils.getPattern(String.valueOf(value));
					DateFormat dateFormat = new SimpleDateFormat(pattern);
					BeanUtilsBean.getInstance().setProperty(entiteDest,
							propertyName, dateFormat.parse(String.valueOf(value)));
				}
				
			} else if(typeField.equals(Calendar.class)) {
				if(value instanceof Calendar) {
					BeanUtilsBean.getInstance().setProperty(entiteDest,
							propertyName, value);
				} else {
					pattern = DateUtils.getPattern(String.valueOf(value));
					DateFormat dateFormat = new SimpleDateFormat(pattern);
					Calendar calendar = new GregorianCalendar();
					calendar.setTime(dateFormat.parse(String.valueOf(value)));
					BeanUtilsBean.getInstance().setProperty(entiteDest,
							propertyName, calendar);
				}
				
			} // les ENUM
			else if (field.getType().isEnum()) {
				BeanUtilsBean.getInstance().setProperty(entiteDest,
						propertyName, getValueEnum(String.valueOf(value), field.getType()) );
				
			} else if(commonUtils.isCollection(value.getClass())) { 
				List listOut = convertList((List) value);
				BeanUtilsBean.getInstance().setProperty(entiteDest,
						propertyName, listOut);
			
			} else if(commonUtils.isReference(value.getClass())) { 
				Object obj = convertObjet(value);
				BeanUtilsBean.getInstance().setProperty(entiteDest,
						propertyName, obj);
				
			} else {
				// en tente le cout, dans les autre cas, a contition, meme ref d'objet
				BeanUtilsBean.getInstance().setProperty(entiteDest,
					propertyName, value);
			}
			
		}
	}
	
	
	private ICustomerConverter getCustomerConverter(String className) {
		
		try {
			return (ICustomerConverter) Class.forName(
					PCK_CUSTMORCONVERTER + className).newInstance();
			
		} catch(Exception e) {
			return null;
		}
	}
	
	private List convertList(List list) {
		if(list.isEmpty()) {
			return null;
		}
		
		List listOut = new ArrayList();
		try {
			for(Object curObject : list) {
				if(inverseConvert.get()) {
					listOut.add(inverseConvertToObject(curObject));
				} else {
					listOut.add(convertToObject(curObject));
				}
			}
		} catch(Exception e) {
			return null;
		}
		
		return listOut;
	}
	
	private Object convertObjet(Object value) throws Exception {
		
		if(value instanceof HibernateProxy) {
			value = ((HibernateProxy) value).getHibernateLazyInitializer()
					.getImplementation();
		}
		
		if(inverseConvert.get()) {
			return inverseConvertToObject(value);
		} else {
			return convertToObject(value);
		}
	}
	
	public boolean isPrimitive(Object objectIn) {
		return objectIn != null
				&& (objectIn.getClass().isPrimitive() || ClassUtils
						.wrapperToPrimitive(objectIn.getClass()) != null);
	}
	
	private Object getValueEnum(String value, Class enumProperty) throws Exception {

		Object[] enums = enumProperty.getEnumConstants();
		for (Object curEnum : enums) {
			if (curEnum.toString().equals(value)) {
				return curEnum;
			}
		}

		return null;
	}

	
	
}
