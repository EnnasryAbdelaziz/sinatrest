package eai.devass.commun.appli.historisation;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.hibernate.proxy.HibernateProxy;

import eai.devass.commun.appli.util.CommonUtils;

@SuppressWarnings("all")
public class JournalisationUtils {

	private static ThreadLocal<JournalisationUtils> threadInstance = new ThreadLocal();
	private CommonUtils commonUtils = CommonUtils.getInstance();

	public static JournalisationUtils getInstance() {

		JournalisationUtils currGestionUtils = threadInstance.get();
		if (currGestionUtils == null) {
			currGestionUtils = new JournalisationUtils();
			threadInstance.set(currGestionUtils);
		}
		return currGestionUtils;
	}

	public void lazyObject(Object object, Object pere) throws Exception {

		try {
			
			if(object == null) {
				throw new Exception("L'objet ne peut être null !!!");
			}
			
			Field[] fields = commonUtils.getAllFields(object.getClass(), new Field[]{});
			ALazy aLazy = null;
			boolean lazyProperty = true;
			Object value = null;
			for(Field curField : fields) {
				aLazy = (ALazy) curField.getAnnotation(ALazy.class);
				if(aLazy == null) {
					continue;
				}
				
				lazyProperty = aLazy.lazy();
				if(lazyProperty) {
					continue;
				}
				
				// Recuperer la valeur 
				try {
					value = BeanUtilsBean.getInstance().getPropertyUtils()
							.getProperty(object, curField.getName());
					
				} catch(Exception e) {
					// On fait rien, c normal !!
					continue;
				}
				
				// Si la valeur est null, on quitt
				if(value == null) {
					continue;
				}
				
				// Lazy la property
				if(value instanceof HibernateProxy) {
					value = ((HibernateProxy) value).getHibernateLazyInitializer()
							.getImplementation();
				}
				
				// Pour eviter les relation cyclique
				if(value.equals(pere)) {
					continue;
				}
				
				// si c'est une reference d'objet
				if(commonUtils.isReference(curField.getType())) {
					lazyObject(value, object);
					BeanUtilsBean.getInstance().setProperty(object,
							curField.getName(), value);
				}
				
				// Si c'est une liste
				if(commonUtils.isCollection(curField.getType())) {
					List listObj = (List) value;
					List listObjNew = new ArrayList();
					for(Object curObject : listObj) {
						if(curObject instanceof HibernateProxy) {
							curObject = ((HibernateProxy) curObject).getHibernateLazyInitializer()
									.getImplementation();
						}
						lazyObject(curObject, object);
						listObjNew.add(curObject);
					}
					BeanUtilsBean.getInstance().setProperty(object,
							curField.getName(), listObjNew);
				}
			}
			
		} catch (InvocationTargetException e) {
			throw (Exception) e.getCause();
		} catch (Exception e) {
			throw e;
		}
		
	}

	
}
