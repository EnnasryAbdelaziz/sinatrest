package eai.devass.commun.appli.uc;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.services.businessInterface.impl.FacadeServiceUseCase;
import ma.co.omnidata.framework.services.core.ServicesFactory;
import ma.co.omnidata.framework.services.dao.IPersistenceService;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.log4j.Logger;
import org.hibernate.Session;

import eai.devass.commun.appli.converter.ConvertorTools;
import eai.devass.commun.appli.exeptions.ExceptionMetier;
import eai.devass.commun.appli.rg.RegleGestionUtils;
/** @author kchakib */

@SuppressWarnings("all")
public abstract class MouvementBaseUC extends FacadeServiceUseCase {

	
	private RegleGestionUtils regleGestionUtils = RegleGestionUtils.getInstance();
	private ConvertorTools convertorTools = ConvertorTools.getInstance();
	protected Logger logger = Logger.getLogger("loggerGSR");
	protected abstract void execute(Map params) throws Exception;
	private final static String classMouvement = "eai.devass.gsr.appli.modele.metier.mouvements.Mouvement";
	private final static String classListVO = "eai.devass.gsr.appli.valueobjects.parametrage.ListVO";
	private final static String runGenerateQuittance = "getGenererQuittance";
	private final static String runEmissionQuittance = "getEmissionQuittance";
	private final static String quittanceGsrProperty = "quittanceGsr";
	private final static String operateurProperty = "operateur";
	
	
	
	@Override
	protected void doExecuter(IValueObject valueObject, HashMap params) throws Exception {
		
		logger.debug("on est dans : " + this.getClass());
		
		try {
		
			//Récuperer l'opertateur
			String operateur = null; // (String) getProperty(object, operateurProperty);
			if(operateur == null) {
				System.out.println("throw new ExceptionMetier(Opertaur obligatoire)");
				operateur = "1";
			}
			
			// Appliquer les regles de gestion pour la liste des BO apres
			// conversion
			Map mapEntiteBO = populateMouvement(valueObject);
			if(mapEntiteBO.isEmpty()) {
				return;
			}
	
			// Merger les deux maps, et appliquer les regler de gestions objets
			mapEntiteBO.putAll((params != null) ? params : new HashMap());
			regleGestionUtils.runRegleGestionObjet(mapEntiteBO);
			mapEntiteBO = (HashMap) regleGestionUtils.getMapObjectBo();
			
			// Call UC
			this.execute(mapEntiteBO);
			
		} catch(ExceptionMetier e) {
			logger.fatal(e.getMessage(), e);
			throw e;
			
		} catch(Exception e) {
			logger.fatal("Probléme téchnique", e);
			throw new Exception("Probléme téchnique");
		}
		
	}
	
	protected Session getSession() throws Exception {
		
		IPersistenceService dao = (IPersistenceService) ServicesFactory.getService(IPersistenceService.class);
		return (Session) dao.getSession();
		
		
	}
	
	protected Object getObject(Map map, Class classObject) {
		
		if(map == null || map.isEmpty()) {
			return null;
		}
		
		Set<Entry> setObjects = map.entrySet();
		for(Entry cuEntry : setObjects) {
			if(cuEntry.getValue().getClass().equals(classObject)) {
				return cuEntry.getValue();
			}
		}
		
		return null;
	}
	
	protected List getObjects(Map map, Class classObject) {
		
		if(map == null || map.isEmpty()) {
			return null;
		}
		
		List listOut = new ArrayList();
		Set<Entry> setObjects = map.entrySet();
		for(Entry cuEntry : setObjects) {
			if(cuEntry.getValue().getClass().equals(classObject)) {
				listOut.add(cuEntry.getValue());
			}
		}
		
		return listOut;
	}
	
	private Map populateMouvement(Object valueObject) throws Exception {
		
		Map mapOut = new HashMap();
		
		// Verifer si c'est une liste des mouvements VO
		Object object = null;
		if(instanceOfListVO(valueObject)) {
			List listObj = (List) getProperty(valueObject, "listObjects");
			if(listObj == null || listObj.isEmpty()) {
				return mapOut;
			}
						
			for(Object curMvtVO : listObj) {
				object = convertorTools.convertToObject(curMvtVO);
				if(object == null) {
					continue;
				}
				
				if(!instanceOfMouvement(object.getClass())) {
					throw new ExceptionMetier(
							"Impossible de convertir l'objet, il faut un objet de type Mouvement");
				}
				
				mapOut.put(object, object);
			}
			
		} else {
			object = convertorTools.convertToObject(valueObject);
			if(object == null) {
				throw new ExceptionMetier(
					"Impossible de convertir l'objet, il faut un objet de type Mouvement");
			}
			
			if(!instanceOfMouvement(object.getClass())) {
				throw new ExceptionMetier(
						"Impossible de convertir l'objet, il faut un objet de type Mouvement");
			}
			mapOut.put(object, object);
		}
		
		return mapOut;
		
	}
	
	private boolean instanceOfMouvement(Class clazz) {
		
		try {
			//Class clazz = Class.forName(classMouvement);
//			if(classMouvement.equals(clazz)) {
//				return true;
//			}
			
			if(clazz.getSimpleName().equals("Object")) {
				return false;
			}
			
			if(clazz.getName().equals(classMouvement)) {
				return true;
			} 
			
			return instanceOfMouvement(clazz.getSuperclass());
			
			
//			if(clazz.getSuperclass().equals(classMouvement)) {
//				return true;
//				
//			} else {
//				return false;
//			}
			
		} catch(Exception e) {
			return false;
		}
		
	}
	
	private boolean instanceOfListVO(Object object) {
		
		try {
			if(classListVO.equals(object.getClass().getName())) {
				return true;
			}
			
			return false;
			
		} catch(Exception e) {
			return false;
		}
		
	}
	
	private boolean generationQuittance(Object object) throws ExceptionMetier {
		
		try {
			Method runGeneratQtc = object.getClass().getMethod(runGenerateQuittance);
			Boolean b = (Boolean) runGeneratQtc.invoke(object);
			return (b != null && b);
			
		} catch(Exception e) {
			throw new ExceptionMetier("Impossible de vérifier la génération de la quittance !");
		}
		
	}
	
	private boolean emissionQuittance(Object object) throws ExceptionMetier {
		
		try {
			Method runGeneratQtc = object.getClass().getMethod(runEmissionQuittance);
			Boolean b = (Boolean) runGeneratQtc.invoke(object);
			return (b != null && b);
			
		} catch(Exception e) {
			throw new ExceptionMetier("Impossible de vérifier la génération de la quittance !");
		}
		
	}
	
	private Object getProperty(Object mouvement, String property) throws ExceptionMetier {
		try {
			
			return BeanUtilsBean.getInstance().getPropertyUtils().getProperty(
					mouvement, property);
			
		} catch(Exception e) {
			throw new ExceptionMetier("Impossible de récupérer la valeur du " + property);
		}
	}
	
	private void setProperty(Object object, String property, Object value) throws ExceptionMetier {
		try {
			
			BeanUtilsBean.getInstance().getPropertyUtils().setProperty(object,
					property, value);
			
		} catch(Exception e) {
			throw new ExceptionMetier("Impossible de setter la valeur " + value
					+ " du " + property);
		}
	}
	
	

}
