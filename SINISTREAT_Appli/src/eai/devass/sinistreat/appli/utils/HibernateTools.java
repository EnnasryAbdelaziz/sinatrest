package eai.devass.sinistreat.appli.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.sql.Blob;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;

import ma.co.omnidata.framework.services.core.ServicesFactory;
import ma.co.omnidata.framework.services.dao.IPersistenceService;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.persister.entity.JoinedSubclassEntityPersister;


public class HibernateTools {
	
	private static HibernateTools instance;
	private Map tableClassName = new HashMap();
	private Logger logger = Logger.getLogger("loggerSINAT");
	private IPersistenceService dao = (IPersistenceService) ServicesFactory.getService(IPersistenceService.class);
		
	public static HibernateTools getInstance(){
		if (instance == null) {
			instance = new HibernateTools();
		}
		
		return instance; 
	}
	
	public HibernateTools() {
		loadTableClassName();
	}
	
	private void loadTableClassName() {
		try {
			SessionFactory factory = ((Session)dao.getSession()).getSessionFactory();
			Collection collection = factory.getAllClassMetadata().values();
			Iterator iterator = collection.iterator();
			while(iterator.hasNext()) {
				Object object = iterator.next();
				if( object instanceof JoinedSubclassEntityPersister) {				
					JoinedSubclassEntityPersister entityPersister = (JoinedSubclassEntityPersister) object;
					tableClassName.put(entityPersister.getTableName(), entityPersister.getName());
				}
			}
			
		} catch (Exception e) {
			logger.error("problème technique",e);
		}
	}
	
	public String getClassName(String tableName) {
		
		if(tableName == null || "".equals(tableName)) {
			return null;
		}
		
		return (String) tableClassName.get(tableName.toUpperCase());
	}
	
	/**
	 * 04/11/2009 : lazy la liste des mouvements cheques, cette fonction sera
	 * utilisé pour la synchronisation des mouvement cheque ds la base de
	 * données centrale
	 */
	public void lazyCollection(List listObjet, String property) throws Exception {
		
		for(Object object : listObjet) {
			lazyOneObject(object, property);
		}
		
	}
	
	public void lazyOneObject(Object object, String property) throws Exception {
		
		// ajouter l heritage 
		Field[] fields = getAllFields(object.getClass());
		for(Field field : fields) {
			String name = field.getName();
			if(property.equals(name)) {
				
				/** Deux cas de figure : liste d'objet ou ref d'objet */
				if(isCollection(field.getType())) {
					List listObjet = (List) BeanUtilsBean.getInstance().getPropertyUtils().getProperty(object, property);
					for(Object objList : listObjet) {
						if(objList != null) {
							objList.toString();
						}
					}
					
				} else if(isReference(field.getType())) {
					Object refObject = BeanUtilsBean.getInstance().getPropertyUtils().getProperty(object, property);
					if(refObject != null) {
						refObject.toString();
					}
				}
					
			}
		}
	}
	
	public boolean isReference(Class clazz) {
		if (clazz.isPrimitive()) {
			return false;
		}
		Package pakage = clazz.getPackage();
		if (pakage == null || pakage.getName().startsWith("java")) {
			return false;
		}
		return true;
	}
	
	private boolean isCollection(Class type) {
		// le type est l'interface Collection
		if (type.toString().equalsIgnoreCase(Collection.class.toString())) {
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
	
	/** Cette methode est utilisée SearchPositionnementUC */
	public Object convertToSerialisableObject(Object object) {
		
		Field[] fields = getAllFields(object.getClass());
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			
			/** ref d'objet */
			if(isReference(field.getType())) {
				try {
					Object refObject = BeanUtilsBean.getInstance().getPropertyUtils().getProperty(object, field.getName());
					
					/** Construire le nouveau objet qui remplacera refObject */
					Object refObjectRemplace = field.getType().newInstance();
					
					/** Recuperer le ID objet */
					Object idObject = BeanUtilsBean.getInstance().getPropertyUtils().getProperty(refObject, "identifiant");
					BeanUtilsBean.getInstance().getPropertyUtils().setProperty(refObjectRemplace, "identifiant", idObject);
					
					/** Mettre a jour object*/
					BeanUtilsBean.getInstance().getPropertyUtils().setProperty(object, field.getName(), refObjectRemplace);
					
				} catch(Exception e) { continue; }
			}
			
			if(isCollection(field.getType())) {
				List newList = new ArrayList();
				try {
					List refList = (List) BeanUtilsBean.getInstance().getPropertyUtils().getProperty(object, field.getName());
					for (int j = 0; j < refList.size(); j++) {
						newList.add(convertToSerialisableObject(refList.get(j)));
					}
					
					/** Mettre à jour object */
					BeanUtilsBean.getInstance().getPropertyUtils().setProperty(object, field.getName(), newList);
				
				} catch(Exception e) {continue;}
				
			}
			
			if(field.getType().equals(Date.class) || field.getType().equals(Timestamp.class)) {
				try {
					Calendar cal = new GregorianCalendar();
					Date refDate = (Date) BeanUtilsBean.getInstance().getPropertyUtils().getProperty(object, field.getName());
					cal.setTime(refDate);
					
					/** Mettre à jour object */
					BeanUtilsBean.getInstance().getPropertyUtils().setProperty(object, field.getName(), cal.getTime());
				
				} catch(Exception e) {continue;}
			}
				
		}
		
		return object;
	}
	public Object convertToSerialisableObject(Object object,String nameId) {
		
		Field[] fields = getAllFields(object.getClass());
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			
			/** ref d'objet */
			if(isReference(field.getType())) {
				try {
					Object refObject = BeanUtilsBean.getInstance().getPropertyUtils().getProperty(object, field.getName());
					
					/** Construire le nouveau objet qui remplacera refObject */
					Object refObjectRemplace = field.getType().newInstance();
					
					/** Recuperer le ID objet */
					Object idObject = BeanUtilsBean.getInstance().getPropertyUtils().getProperty(refObject, nameId);
					BeanUtilsBean.getInstance().getPropertyUtils().setProperty(refObjectRemplace, nameId, idObject);
					
					/** Mettre a jour object*/
					BeanUtilsBean.getInstance().getPropertyUtils().setProperty(object, field.getName(), refObjectRemplace);
					
				} catch(Exception e) { continue; }
			}
			
			if(isCollection(field.getType())) {
				List newList = new ArrayList();
				try {
					List refList = (List) BeanUtilsBean.getInstance().getPropertyUtils().getProperty(object, field.getName());
					for (int j = 0; j < refList.size(); j++) {
						newList.add(convertToSerialisableObject(refList.get(j)));
					}
					
					/** Mettre à jour object */
					BeanUtilsBean.getInstance().getPropertyUtils().setProperty(object, field.getName(), newList);
				
				} catch(Exception e) {continue;}
				
			}
			
			if(field.getType().equals(Date.class) || field.getType().equals(Timestamp.class)) {
				try {
					Calendar cal = new GregorianCalendar();
					Date refDate = (Date) BeanUtilsBean.getInstance().getPropertyUtils().getProperty(object, field.getName());
					cal.setTime(refDate);
					
					/** Mettre à jour object */
					BeanUtilsBean.getInstance().getPropertyUtils().setProperty(object, field.getName(), cal.getTime());
				
				} catch(Exception e) {continue;}
			}
				
		}
		
		return object;
	}
	
	private Field[] getAllFields(Class clazz) {
		
		Field[] fields = clazz.getDeclaredFields();
		Class extendedClass = clazz.getSuperclass();
		Field[] fieldsExtendedClass = extendedClass.getDeclaredFields();
		
		int count = fields.length;
		int count2 = fieldsExtendedClass.length;
		
		Field[] allFields = new Field[count + count2];
		// Correction Sonar:WMOS-->30/10/2014
		System.arraycopy(fields, 0, allFields, 0, fields.length);
		
		// for (int i = 0; i < fields.length; i++) {
		// allFields[i] = fields[i];
		// }
		
		//Fin correction
		
		for (int i = 0; i < fieldsExtendedClass.length; i++) {
			allFields[i + count] = fieldsExtendedClass[i];
		}
		
		return allFields;
	}
	
	/** Pour le Blob,sauvgarder un fichier ds la DB, utiliser dans l'objet FileBanque */
	public Blob createBlob(byte[] byteFile) {
		
		return Hibernate.createBlob(byteFile);
	}

	public byte[] toByteArray(Blob fromBlob) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			return toByteArrayImpl(fromBlob, baos);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			if (baos != null) {
				try {
					baos.close();
				} catch (IOException ex) {
				}
			}
		}
	}
	
	protected byte[] toByteArrayImpl(Blob fromBlob, ByteArrayOutputStream baos)
			throws SQLException, IOException {
		byte[] buf = new byte[4000];
		InputStream is = fromBlob.getBinaryStream();
		try {
			for (;;) {
				int dataSize = is.read(buf);
				if (dataSize == -1) {
					break;
				}
				baos.write(buf, 0, dataSize);
			}
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException ex) {
		
				}
			}
		}
		return baos.toByteArray();
	}
	
	public String getIdentifierPropertyName(Class Clazz) throws Exception {

		if (Clazz == null) {
			return null;
		}
		SessionFactory factory = ((Session) dao.getSession()).getSessionFactory();
		ClassMetadata classMetadata = factory.getClassMetadata(Clazz);

		return classMetadata.getIdentifierPropertyName();
	}
	
	

}
