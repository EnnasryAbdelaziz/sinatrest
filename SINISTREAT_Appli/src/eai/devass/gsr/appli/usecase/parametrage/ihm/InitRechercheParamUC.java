package eai.devass.gsr.appli.usecase.parametrage.ihm;

import java.util.HashMap;
import java.util.StringTokenizer;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.services.core.ServicesFactory;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.log4j.Logger;
import org.hibernate.exception.ConstraintViolationException;

import eai.devass.commun.appli.uc.SimpleBaseUC;
import eai.devass.commun.appli.util.CommonUtils;
import eai.devass.gsr.appli.manager.metier.transverse.TransverseManager;
import eai.devass.gsr.appli.valueobjects.parametrage.ParamVO;
import eai.devass.sinistreat.appli.exception.FonctionnelleException;

public class InitRechercheParamUC extends SimpleBaseUC {

	private Logger logger = Logger.getLogger("loggerSINAT");
	
	protected void execute(IValueObject entite, HashMap params) throws Exception {
		// Récupérer la class de l'objet BO correspondant à l'objet VO
		TransverseManager transverseManager = (TransverseManager) ServicesFactory
				.getService(TransverseManager.class);
		
		try {
			
			ParamVO paramVO = (ParamVO) entite;
			String libelle = paramVO.getLibelle();
			if(CommonUtils.isEmpty(libelle)) {
				return ;
			}

			StringTokenizer listParms = new StringTokenizer(libelle, "|");
			//Class clazz = null;
			//String className = null;
			String paramClass = null;
			while(listParms.hasMoreTokens()) {
				try {
					paramClass = listParms.nextToken();
					//className = getClassName(paramClass);
					//clazz = Class.forName(className);
					paramToConverter.put("list" + getNameObjectVO(paramClass),
							transverseManager.getSimilarObject(getInstanceObject(paramClass)));
					
				} catch(Exception e) {
					logger.fatal("Impossible de charger la liste des " + paramClass, e);
				}
			}
			addResultItem(paramToConverter);
			
		} catch (ConstraintViolationException e) {
			throw new FonctionnelleException(e);

		}
	}	
	
	
	private String getNameObjectVO(String className) {
		
		StringTokenizer stringTokenizer = new StringTokenizer(className, ".");
		String nameObject = null;
		while (stringTokenizer.hasMoreElements()) {
			nameObject = stringTokenizer.nextToken(); 
		}
		
		if(nameObject.indexOf("(") < 0) {
			return nameObject + "VO";
		}
		
		return nameObject.substring(0, nameObject.indexOf("(")) + "VO";
	}
	
	
	private Object getInstanceObject(String paramName) throws Exception {
		
		if(paramName.indexOf("(") < 0) {
			return Class.forName(paramName).newInstance();
		}
		
		int indexPara = paramName.indexOf("(");
		String className = paramName.substring(0, indexPara);
		String idParams = paramName.substring(indexPara + 1,
				paramName.length() - 1);
		
		Object object = Class.forName(className).newInstance();
		BeanUtilsBean.getInstance().setProperty(object, "id", Long.valueOf(idParams));
		
		return object;
	}
	
//	private String getClassName(String className) {
//		
//		if(className.indexOf("(") < 0) {
//			return className;
//		}
//		
//		return className.substring(0, className.indexOf("("));
//	}
	
	
	public boolean isTransactionnal() {
		return false;
	}

}