package eai.devass.sinistreat.appli.utils;

/* @author kchakib : 4 oct. 10 */
import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.lang.ClassUtils;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.collection.PersistentBag;
import org.hibernate.proxy.HibernateProxy;

import eai.devass.commun.appli.util.CommonUtils;

@SuppressWarnings("all")
public class ConverterTools {

	private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	private static ConverterTools instance;
	//private Map currentConverterObject = new HashMap();
	private final static String MODEL_PACKAGE = "modele";
	private final static String VALUEOBJECT_PACKAGE = "valueobjects";

	public static ConverterTools getInstance() {
		if (instance == null) {
			return new ConverterTools();
		} else {
			return instance;
		}
	}

	private IValueObject convertToObjectVO(Object objectBO, Class classObjectVO) {

		if (objectBO == null) {
			return null;
		}
		String[] propertiesToConvert = null;
		String[] propertiesToConvertChild = null;
		IValueObject objectVO = null;
		try {
			
		
			objectVO = (IValueObject) classObjectVO.newInstance();
			List listRefObjectVO = null;
			try {
				propertiesToConvert = (String[]) BeanUtilsBean.getInstance()
						.getPropertyUtils()
						.getProperty(objectBO, "propertiesToConvert");
			} catch (Exception e) {
			}
			Field[] fieldsVO = getAllFields(classObjectVO);
			for (Field curField : fieldsVO) {
				try {
					String nameFieldVO = curField.getName();

					if ("serialVersionUID".equals(nameFieldVO)) {
						continue;
					}
					// Convertir seulement les property declarés dans
					// propertiesToConvert
					if (!convertProperties(propertiesToConvert, nameFieldVO)) {
						continue;
					}
					Object value = null;
					try {
						value = BeanUtilsBean.getInstance().getPropertyUtils()
								.getProperty(objectBO, nameFieldVO);

					} catch (NoSuchMethodException e) {
						continue;
					} catch (Exception e) {
						// logger.error("problème technique",e);
						continue;
					}

					if (value == null || value.equals("")) {
						continue;
					}

					// il faut un traitement pour les Date, les list et les ref
					// d'objet
					if (Date.class.equals(value.getClass())
							|| Timestamp.class.equals(value.getClass())) {

						BeanUtils.setProperty(objectVO, nameFieldVO,
								dateFormat.format((Date) value));
					} else if (Calendar.class.equals(value.getClass()
							.getSuperclass())) {
						BeanUtils
								.setProperty(objectVO, nameFieldVO, dateFormat
										.format(((Calendar) value).getTime()));
					}

					// les ENUM
					else if (curField.getType().isEnum()) {
						BeanUtilsBean.getInstance().setProperty(
								objectVO,
								nameFieldVO,
								getValueEnum(String.valueOf(value),
										nameFieldVO, curField.getType()));
					}

					// Ref d'objet
					else if (isReference(value.getClass())) {
						propertiesToConvertChild = getConvertPropertiesChild(
								propertiesToConvert, nameFieldVO);
						if (propertiesToConvertChild != null) {
							BeanUtilsBean.getInstance().setProperty(value,
									"propertiesToConvert",
									propertiesToConvertChild);
						}
						/*
						 * Pour resoudre le probleme d'une boucle cyclique sur
						 * les refernces d'objets, dans le cas d'une relation
						 * bidirectionnelle
						 */
						if (value instanceof HibernateProxy) {
							value = ((HibernateProxy) value)
									.getHibernateLazyInitializer()
									.getImplementation();
						}
						if (getNameProperty(value, objectBO) != null) {
							BeanUtils.setProperty(
									objectVO,
									nameFieldVO,
									convertToObjectBOSimple(value,
											getClassObjectVO(value), objectBO));
						} else {
							BeanUtils.setProperty(
									objectVO,
									nameFieldVO,
									convertToObjectVO(value,
											getClassObjectVO(value)));
						}

					}

					// LEs collections
					else if (isCollection(value.getClass())) {
						List listRefObjectBO = (List) value;
						if (listRefObjectBO.isEmpty()) {
							continue;
						}

						listRefObjectVO = new ArrayList();
						if (getNameProperty(listRefObjectBO.get(0), objectBO) != null) {
							for (Object refObjectBO : listRefObjectBO) {
								listRefObjectVO
										.add(convertToObjectBOSimple(
												refObjectBO,
												getClassObjectVO(refObjectBO),
												objectBO));
							}
						} else {
							for (Object refObjectBO : listRefObjectBO) {
								listRefObjectVO.add(convertToObjectVO(
										refObjectBO,
										getClassObjectVO(refObjectBO)));
							}
						}
						BeanUtils.setProperty(objectVO, nameFieldVO,
								listRefObjectVO);

					} else {
						BeanUtils.setProperty(objectVO, nameFieldVO, value);
					}

				} catch (Exception e) {
					// logger.error("problème technique",e);
				}
			}

		} catch (Exception e) {

		}

		return objectVO;
	}

	private IValueObject convertToObjectVOWithoutList(Object objectBO,
			Class classObjectVO) {

		if (objectBO == null) {
			return null;
		}
		String[] propertiesToConvert = null;
		String[] propertiesToConvertChild = null;
		IValueObject objectVO = null;
		try {

			objectVO = (IValueObject) classObjectVO.newInstance();
			Field[] fieldsVO = getAllFields(classObjectVO);
			try {
				propertiesToConvert = (String[]) BeanUtilsBean.getInstance()
						.getPropertyUtils()
						.getProperty(objectBO, "propertiesToConvert");
			} catch (Exception e) {
			}

			String nameFieldVO = null;
			for (int i = 0; i < fieldsVO.length; i++) {
				try {
					nameFieldVO = fieldsVO[i].getName();
					if ("serialVersionUID".equals(nameFieldVO)) {
						continue;
					}

					// Convertir seulement les property declarés dans
					// propertiesToConvert
					if (!convertProperties(propertiesToConvert, nameFieldVO)) {
						continue;
					}

					Object value = null;
					try {
						value = BeanUtilsBean.getInstance().getPropertyUtils()
								.getProperty(objectBO, nameFieldVO);

					} catch (NoSuchMethodException e) {
						continue;
					}

					catch (Exception e) {
						// logger.error("problème technique",e);
					}
					try {
						if (value == null || value.equals("")) {
							continue;
						}
					} catch (ObjectNotFoundException e) {
						continue;
					}

					/*
					 * il faut un traitement pour les Date, les list et les ref
					 * d'objet
					 */
					if (Date.class.equals(value.getClass())
							|| Timestamp.class.equals(value.getClass())) {
						BeanUtils.setProperty(objectVO, nameFieldVO,
								dateFormat.format((Date) value));
					}
					
					else if (GregorianCalendar.class.equals(value.getClass())) {
						
						Calendar calendar=(Calendar) value;
						Date date = calendar.getTime();
						BeanUtils.setProperty(objectVO, nameFieldVO,
								dateFormat.format(date));
					}

					// les ENUM
					else if (fieldsVO[i].getType().isEnum()) {
						BeanUtilsBean.getInstance().setProperty(
								objectVO,
								nameFieldVO,
								getValueEnum(String.valueOf(value),
										nameFieldVO, fieldsVO[i].getType()));

					}

					else if (isReference(value.getClass())) {
						/*
						 * Pour resoudre le probleme d'une boucle cyclique sur
						 * les refernces d'objets, dans le cas d'une relation
						 * bidirectionnelle
						 */

						propertiesToConvertChild = getConvertPropertiesChild(
								propertiesToConvert, nameFieldVO);
						if (propertiesToConvertChild != null) {
							BeanUtilsBean.getInstance().setProperty(value,
									"propertiesToConvert",
									propertiesToConvertChild);
						}

						if (value instanceof HibernateProxy) {
							value = ((HibernateProxy) value)
									.getHibernateLazyInitializer()
									.getImplementation();
						}
						if (getNameProperty(value, objectBO) != null) {
							BeanUtils.setProperty(
									objectVO,
									nameFieldVO,
									convertToObjectBOSimple(value,
											getClassObjectVO(value), objectBO));
						} else {
							BeanUtils.setProperty(
									objectVO,
									nameFieldVO,
									convertToObjectVOWithoutList(value,
											getClassObjectVO(value)));
						}

					} else if (isCollection(value.getClass())) {
						continue;

					} else {
						BeanUtils.setProperty(objectVO, nameFieldVO, value);
					}
				} catch (Exception e) {
					// logger.error("problème technique",e);
				}
			}

		} catch (Exception e) {
			// logger.error("problème technique",e);

		}

		return objectVO;
	}

	private Object convertToObjectBO(IValueObject objectVO, Class classObjectBO) {

		if (objectVO == null) {
			return null;
		}

		Object objectBO = null;
		try {

			objectBO = classObjectBO.newInstance();
			Object currentObjectBO = null;

			// récupérer tous les Field y compris l'héritage !!!
			Field[] fieldsBO = getAllFields(classObjectBO);
			for (int i = 0; i < fieldsBO.length; i++) {
				String nameFieldBO = fieldsBO[i].getName();
				if ("serialVersionUID".equals(nameFieldBO)) {
					continue;
				}

				Object value = null;
				try {
					value = BeanUtilsBean.getInstance().getPropertyUtils()
							.getProperty(objectVO, nameFieldBO);
				} catch (NoSuchMethodException e) {
					continue;
				}

				if (value == null || value.equals("")) {
					continue;
				}

				try {
					/*
					 * il faut un traitement pour les Date, les list et les ref
					 * d'objet
					 */
					if (Date.class.equals(fieldsBO[i].getType())
							|| Timestamp.class.equals(fieldsBO[i].getType())) {
						try {

							BeanUtils.setProperty(objectBO, nameFieldBO,
									dateFormat.parse((String) value));
						} catch (Exception e) {
							SimpleDateFormat dateFormat2 = new SimpleDateFormat(
									"HH:mm");
							BeanUtils.setProperty(objectBO, nameFieldBO,
									dateFormat2.parse((String) value));
						}
					}

					// Controle sur les type Enum
					else if (value.getClass().isEnum()) {
						BeanUtilsBean.getInstance().setProperty(objectBO,
								nameFieldBO, String.valueOf(value));

					}

					else if (isReference(value.getClass())) {
						/*
						 * Pour resoudre le probleme d'une boucle cyclique sur
						 * les refernces d'objets, dans le cas d'une relation
						 * bidirectionnelle
						 */
						if (getNameProperty(value, objectVO) != null) {
							currentObjectBO = convertToObjectBOSimple(
									(IValueObject) value,
									getClassObjectBO(value), objectVO);
						}

						else {
							if (isConvertible(value)) {
								currentObjectBO = convertToObjectBO(
										(IValueObject) value,
										getClassObjectBO(value));
							} else {
								currentObjectBO = value;
							}
						}

						BeanUtils.setProperty(objectBO, nameFieldBO,
								currentObjectBO);

					} else if (isCollection(value.getClass())) {
						List listRefObjectBO = new ArrayList();
						List listRefObjectVO = (List) value;
						if (listRefObjectVO.isEmpty()) {
							continue;
						}

						if (getNameProperty(listRefObjectVO.get(0), objectVO) != null) {
							for (Object refObjectVO : listRefObjectVO) {
								listRefObjectBO
										.add(convertToObjectBOSimple(
												(IValueObject) refObjectVO,
												getClassObjectBO(refObjectVO),
												objectVO));
							}
							;
						} else {
							for (Object refObjectVO : listRefObjectVO) {
								listRefObjectBO.add(convertToObjectBO(
										(IValueObject) refObjectVO,
										getClassObjectBO(refObjectVO)));
							}
						}

						BeanUtils.setProperty(objectBO, nameFieldBO,
								listRefObjectBO);

					} else {
						BeanUtilsBean.getInstance().setProperty(objectBO,
								nameFieldBO, value);
					}
				} catch (Exception e) {
					// logger.error("problème technique",e);
				}
			}

		} catch (Exception e) {
			// logger.error("problème technique",e);
		}

		return objectBO;
	}

	public Object convertToObjectBO(IValueObject objectVO) throws Exception {

		Class classObjectBO = getClassObjectBO(objectVO);

		// Initialiser le map 'currentConverterObject'
		// currentConverterObject = new HashMap();

		return convertToObjectBO(objectVO, classObjectBO);

	}

	public Object convertToObjectVO(Object objectBO) throws Exception {

		Class classObjectVO = getClassObjectVO(objectBO);

		// Initialiser le map 'currentConverterObject'
		// currentConverterObject = new HashMap();

		return convertToObjectVO(objectBO, classObjectVO);

	}

	// public Object convertToObjectVOWithoutListGSR(Object objectBO)
	// throws Exception {
	//
	// Class classObjectVO = getClassObjectVO(objectBO);
	//
	// // Initialiser le map 'currentConverterObject'
	// //currentConverterObject = new HashMap();
	//
	// return convertToObjectVOWithoutListGSR(objectBO, classObjectVO);
	//
	// }

	public Object convertToObjectVOWithoutList(Object objectBO)
			throws Exception {

		Class classObjectVO = getClassObjectVO(objectBO);

		// Initialiser le map 'currentConverterObject'
		// currentConverterObject = new HashMap();

		return convertToObjectVOWithoutList(objectBO, classObjectVO);

	}

	public List convertToListObjectVO(List listOject) throws Exception {

		List listObjectOut = new ArrayList();
		String[] propertiesToConvert = null;
		if (listOject != null && !listOject.isEmpty()) {
			Object firstObject = listOject.get(0);
			// Pour propertiesToConvert
			try {
				propertiesToConvert = (String[]) BeanUtilsBean.getInstance()
						.getPropertyUtils()
						.getProperty(firstObject, "propertiesToConvert");

			} catch (Exception e) {
			}
			// Cas de liste de type long, int et bool etc...
			// Return directement la valeur String
			if (isPrimitive(listOject.get(0))
					|| listOject.get(0).getClass().equals(String.class)) {
				for (Object objectBO : listOject) {
					listObjectOut.add(String.valueOf(objectBO));
				}
			} else {
				for (Object objectBO : listOject) {
					if (propertiesToConvert != null) {
						BeanUtilsBean.getInstance().setProperty(objectBO,
								"propertiesToConvert", propertiesToConvert);
					}

					listObjectOut.add(convertToObjectVO(objectBO));
				}
			}
		}
		return listObjectOut;

	}

	public List convertToListObjectVOWithoutList(List listOject)
			throws Exception {

		List listObjectOut = new ArrayList();
		if (CommonUtils.getInstance().isEmpty(listOject)) {
			return listObjectOut;
		}

		String[] propertiesToConvert = null;
		Object firstObject = listOject.get(0);
		// Pour propertiesToConvert
		try {
			propertiesToConvert = (String[]) BeanUtilsBean.getInstance()
					.getPropertyUtils()
					.getProperty(firstObject, "propertiesToConvert");

		} catch (Exception e) {
		}

		for (Object objectBO : listOject) {

			if (propertiesToConvert != null) {
				BeanUtilsBean.getInstance().setProperty(objectBO,
						"propertiesToConvert", propertiesToConvert);
			}

			listObjectOut.add(convertToObjectVOWithoutList(objectBO));
		}
		return listObjectOut;

	}

	public List convertToListObjectBO(List<IValueObject> listOjectVO)
			throws Exception {

		List listObjectOut = new ArrayList();
		for (IValueObject objectVO : listOjectVO) {
			listObjectOut.add(convertToObjectBO(objectVO));
		}
		return listObjectOut;

	}

	public List convertToListObjectBOBis(List listOjectVO) throws Exception {

		List listObjectOut = new ArrayList();
		for (Object objectVO : listOjectVO) {

			listObjectOut.add(convertToObjectBO(((IValueObject) objectVO)));
		}
		return listObjectOut;

	}

	public boolean isCollection(Class type) {
		// le type est l'interface Collection
		if (type.toString().equalsIgnoreCase(Collection.class.toString())) {
			return true;
		}

		// le type est l'interface Collection
		if (type.toString().equalsIgnoreCase(PersistentBag.class.toString())) {
			return true;
		} else if (type.isInterface()) {
			if (type.toString().equalsIgnoreCase(List.class.toString())) {
				return true;
			}
			if (type.toString().equalsIgnoreCase(Set.class.toString())) {
				return true;
			}
		}
		// le type est une classe qui implémente Collection, List, Set ou
		// SortedSet
		else {
			Class[] interfaces = type.getInterfaces();
			int intLength = interfaces.length;
			for (int i = 0; i < intLength; i++) {
				if (interfaces[i].toString().equalsIgnoreCase(
						Collection.class.toString())) {
					return true;
				}
				if (interfaces[i].toString().equalsIgnoreCase(
						List.class.toString())) {
					return true;
				}
				if (interfaces[i].toString().equalsIgnoreCase(
						Set.class.toString())) {
					return true;
				}
				if (interfaces[i].toString().equalsIgnoreCase(
						SortedSet.class.toString())) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean isReference(Class clazz) {
		if (clazz.isPrimitive()) {
			return false;
		}
		Package pakage = clazz.getPackage();
		if (pakage == null || pakage.getName().startsWith("java")
				|| pakage.getName().startsWith("org")) {
			return false;
		}
		return true;
	}

	private Class getClassObjectVO(Object objectBO) throws Exception {

		String classObjectBOName = objectBO.getClass().getName();
		int position = classObjectBOName.indexOf("$");
		if (position != -1) {
			classObjectBOName = classObjectBOName.substring(0, position);
		}
		String classObjectVOName;
		if (isConvertible(objectBO)) {
			classObjectVOName = classObjectBOName.replaceAll(MODEL_PACKAGE,
					VALUEOBJECT_PACKAGE) + "VO";
		} else {
			classObjectVOName = classObjectBOName;
		}

		return Class.forName(classObjectVOName);
	}

	public Class getClassObjectBO(Object objectVO) throws Exception {

		String classObjectBOName = objectVO.getClass().getName()
				.replaceAll("VO", "");
		return Class.forName(classObjectBOName.replaceAll(VALUEOBJECT_PACKAGE,
				MODEL_PACKAGE));
	}

	/**
	 * Récupérer tous les Field d'une classe, y compris celles dans l'héritage
	 * 
	 * @param clazz
	 * @return
	 */
	public Field[] getAllFields(Class clazz) {

		// Field[] fieldsDeclared = clazz.getDeclaredFields();
		// Field[] fieldsSuperClass = clazz.getSuperclass().getDeclaredFields();
		//
		// Field[] fields = new Field[fieldsDeclared.length
		// + fieldsSuperClass.length];
		// int i = 0;
		// for (Field field : fieldsDeclared) {
		// fields[i++] = field;
		// }
		//
		// for (Field field : fieldsSuperClass) {
		// fields[i++] = field;
		// }
		return CommonUtils.getInstance().getAllFields(clazz, new Field[] {});

	}

	private String getNameProperty(Object childObject, Object parentOject) {

		// Récuperer la classe d'implementation de l'objet "childObject"
		Class clazzChild = childObject.getClass();

		// Dans le cas ou l'objet "parentOject" est de type "HibernateProxy"
		// (lazy=true), il faut récuperer la class d'implementation sans les "$"
		if (childObject instanceof HibernateProxy) {
			try {
				clazzChild = Class.forName(clazzChild.getName().substring(0,
						clazzChild.getName().indexOf("$")));
			} catch (Exception e) {
			}
		}

		// Récuperer la classe d'implementation de l'objet "parentOject"
		Class classParentOject = parentOject.getClass();

		// Dans le cas ou l'objet "parentOject" est de type "HibernateProxy"
		// (lazy=true), il faut récuperer la class d'implementation sans les "$"
		if (parentOject instanceof HibernateProxy) {
			try {
				classParentOject = Class.forName(parentOject
						.getClass()
						.getName()
						.substring(0,
								parentOject.getClass().getName().indexOf("$")));

			} catch (Exception e) {
			}
		}

		Field[] fields = CommonUtils.getInstance().getAllFields(clazzChild,
				new Field[] {});// clazzChild.getDeclaredFields();
		Object curObject = null;
		for (Field field : fields) {
			if (field.getType().equals(classParentOject)) {
				try {
					// Récuperer la valeur de l'attribut 'field.getName' pour le
					// comparer avec parentObject
					curObject = BeanUtilsBean.getInstance().getPropertyUtils()
							.getProperty(childObject, field.getName());
					if (curObject == null) {
						return null;
					}

					if (curObject instanceof HibernateProxy) {
						curObject = ((HibernateProxy) curObject)
								.getHibernateLazyInitializer()
								.getImplementation();
					}

					if (curObject.equals(parentOject)) {
						return field.getName();
					}

				} catch (Exception e) {
				}
			}
		}

		return null;
	}

	private Object getValueEnum(String value, String property,
			Class enumProperty) throws Exception {

		Object[] enums = enumProperty.getEnumConstants();
		for (Object curEnum : enums) {
			if (curEnum.toString().equals(value)) {
				return curEnum;
			}
		}

		return null;
	}

	private boolean isConvertible(Object object) {
		String classObjectName = object.getClass().getName();

		int position = classObjectName.indexOf("$");
		if (position != -1) {
			classObjectName = classObjectName.substring(0, position);
		}
		Class clazz = null;
		;
		try {
			clazz = Class.forName(classObjectName);
		} catch (ClassNotFoundException e) {
			// logger.error("problème technique",e);
		}
		if (clazz != null) {
			Class[] interfaces = clazz.getInterfaces();
			for (Class intrface : interfaces) {
				if (intrface.equals(IInconvertible.class)) {
					return false;
				}
			}
		}

		return true;

	}

	public Field getField(Class clazz, String nomField) {

		return CommonUtils.getInstance().getField(clazz, nomField);

	}

	public boolean isPrimitive(Object objectIn) {
		return objectIn != null
				&& (objectIn.getClass().isPrimitive() || ClassUtils
						.wrapperToPrimitive(objectIn.getClass()) != null);
	}

	private boolean convertProperties(String[] propertiesToConvert,
			String nameFieldVO) {

		if (propertiesToConvert == null || propertiesToConvert.length == 0) {
			return true;
		}

		for (String curProperty : propertiesToConvert) {
			if (curProperty != null && curProperty.indexOf(nameFieldVO) == 0) {
				return true;
			}
		}

		return false;
	}

	private String[] getConvertPropertiesChild(String[] propertiesToConvert,
			String nameFieldVO) {
		if (propertiesToConvert == null || propertiesToConvert.length == 0) {
			return null;
		}

		String[] propertiesToConvertChild = new String[propertiesToConvert.length];
		int i = 0;
		for (String curProperty : propertiesToConvert) {
			if (curProperty != null && curProperty.indexOf(nameFieldVO) >= 0) {
				propertiesToConvertChild[i++] = curProperty
						.substring(nameFieldVO.length() + 1);
			}
		}

		return propertiesToConvertChild;
	}

	private Object convertToObjectBOSimple(Object objOrig, Class classDest,
			Object objectVOPere) {

		if (objOrig == null) {
			return null;
		}

		Object objDest = null;
		try {

			objDest = classDest.newInstance();

			// récupérer tous les Field y compris l'héritage !!!
			Field[] fieldsBO = getAllFields(classDest);
			for (int i = 0; i < fieldsBO.length; i++) {

				String nameFieldBO = fieldsBO[i].getName();
				if ("serialVersionUID".equals(nameFieldBO)) {
					continue;
				}

				Object value = null;
				try {
					value = BeanUtilsBean.getInstance().getPropertyUtils()
							.getProperty(objOrig, nameFieldBO);

				} catch (NoSuchMethodException e) {
					continue;
				}

				if (value == null || value.equals("")) {
					continue;
				}

				/*
				 * il faut un traitement pour les Date, les list et les ref
				 * d'objet
				 */
				if (Date.class.equals(fieldsBO[i].getType())
						|| Timestamp.class.equals(fieldsBO[i].getType())) {
					try {
						BeanUtils.setProperty(objDest, nameFieldBO,
								dateFormat.parse((String) value));
					} catch (Exception e) {
						SimpleDateFormat dateFormat2 = new SimpleDateFormat(
								"HH:mm");
						BeanUtils.setProperty(objDest, nameFieldBO,
								dateFormat2.parse((String) value));
					}
				}

				// Controle sur les type Enum
				else if (value.getClass().isEnum()) {
					BeanUtilsBean.getInstance().setProperty(objDest,
							nameFieldBO, String.valueOf(value));

				} else if (isReference(value.getClass())) {
					if (value.equals(objectVOPere)) {
						continue;

					} else {
						if (value instanceof IValueObject) {
							BeanUtils.setProperty(
									objDest,
									nameFieldBO,
									convertToObjectBOSimple(value,
											getClassObjectBO(value), objOrig));
						} else {
							BeanUtils.setProperty(
									objDest,
									nameFieldBO,
									convertToObjectBOSimple(value,
											getClassObjectVO(value), objOrig));
						}
					}
				} else if (isCollection(value.getClass())) {
					continue;

				}

				else if (String.class.equals(fieldsBO[i].getType())) {
					if (Date.class.equals(value.getClass())
							|| Timestamp.class.equals(value.getClass())) {
						try {
							BeanUtils.setProperty(objDest, nameFieldBO,
									dateFormat.format((Date) value));
						} catch (Exception e) {
							SimpleDateFormat dateFormat2 = new SimpleDateFormat(
									"HH:mm");
							BeanUtils.setProperty(objDest, nameFieldBO,
									dateFormat2.format((Date) value));
						}

					} else {
						BeanUtilsBean.getInstance().setProperty(objDest,
								nameFieldBO, value);
					}

				} else {
					BeanUtilsBean.getInstance().setProperty(objDest,
							nameFieldBO, value);
				}
			}

		} catch (Exception e) {
			// logger.error("problème technique",e);
		}

		return objDest;
	}

}
