package eai.devass.commun.appli.rg;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.StringTokenizer;
import java.util.TreeSet;

import ma.co.omnidata.framework.services.businessInterface.IMessageItem;
import eai.devass.commun.appli.modele.EntiteBO;
import eai.devass.commun.appli.util.CommonUtils;

@SuppressWarnings("all")
public class RegleGestionUtils {

	private static ThreadLocal<RegleGestionUtils> threadInstance = new ThreadLocal();
	private final static String PREFIX_METHODE = "regleGestion";
	private final static String PREFIX_PACKAGE_RG = "eai.devass.gsr.appli.reglegestion.";
	private Map mapObjectBo;
	private CommonUtils commonUtils = CommonUtils.getInstance();

	public static RegleGestionUtils getInstance() {

		RegleGestionUtils currGestionUtils = threadInstance.get();
		if (currGestionUtils == null) {
			currGestionUtils = new RegleGestionUtils();
			threadInstance.set(currGestionUtils);
		}
		return currGestionUtils;
	}

	public void runRegleGestionObjet(Map mapObjectBo) throws Exception {

		Method[] methodArray = null;
		SortedSet<Method> methods = null;
		Class clazzRG = null;
		Object objectRG = null;
		if (mapObjectBo.get("outMapMessage") == null) {
			mapObjectBo.put("outMapMessage",
					new HashMap<String, IMessageItem>());
		}
		Collection listEntiteBo = mapObjectBo.values();
		Boolean runRegleGesion = false;
		try {
			for (Object objectBo : listEntiteBo) {

				if (!(objectBo instanceof EntiteBO)) {
					continue;
				}

				runRegleGesion = ((EntiteBO) objectBo).getRunRegleGestion();
				if (runRegleGesion != null && !runRegleGesion) {
					continue;
				}

				// Récuperer la classe ObjectRG et les methodes des regles de
				// gestions de l'objet
				try {
					clazzRG = getClassRGImpl((EntiteBO) objectBo);
					if (clazzRG == null) {
						continue;
					}

				} catch (Exception e) {
					continue;
				}

				// Par defaut, c'est un objet persistant
				((EntiteBO) objectBo).setPersistObject(true);
				objectRG = clazzRG.newInstance();
				methodArray = clazzRG.getMethods();
				methods = new TreeSet<Method>(new MethodComparator());
				methods.addAll(Arrays.asList(methodArray));
				for (Method methodRegleGestion : methods) {
					if (!methodRegleGestion.getName()
							.startsWith(PREFIX_METHODE)) {
						continue;
					}

					//Verifier si on doit SKIPer la methode!!
					if(!skipMethode((EntiteBO) objectBo, methodRegleGestion, objectRG)) {
						methodRegleGestion.invoke(objectRG, (EntiteBO) objectBo,
								mapObjectBo);
					}
					
					
				}
			}

		} catch (InvocationTargetException e) {
			throw (Exception) e.getCause();
		} catch (Exception e) {
			throw e;
		}

		this.mapObjectBo = mapObjectBo;

	}

	private Class getClassRGImpl(EntiteBO entiteBO) throws Exception {

		Class clazzRG = null;
		String contextRG = entiteBO.getContextRegleGestionEnum().getContext();
		if (contextRG == null) {
			contextRG = ContextRegleGestion.DEFAULT.getContext();
		}

		try {
			clazzRG = Class.forName(PREFIX_PACKAGE_RG + contextRG + "."
					+ entiteBO.getClass().getSimpleName() + "RG");

		} catch (Exception e) {
			clazzRG = Class.forName(PREFIX_PACKAGE_RG + contextRG + "."
					+ entiteBO.getClass().getSuperclass().getSimpleName()
					+ "RG");
		}

		return clazzRG;
	}

	private boolean skipMethode(EntiteBO entiteBO, Method method, 
			Object objectRG) throws Exception {
		
		ASkipRG skipRG = (ASkipRG) method.getAnnotation(ASkipRG.class);
		if(skipRG == null) {
			return false;
		}
		
		String property = skipRG.property();
		String valueSt = skipRG.value();
		String beanSt = skipRG.bean();
		String isEmty = skipRG.isEmty();
		
		if (commonUtils.isEmpty(property)) {
			return false;
		}
		
		Object bean = entiteBO;
		if("this".equals(beanSt)) {
			bean = objectRG;
		}
		
		// Recuperer la valeur du property
		Object value = null;
		try {
			value = commonUtils.getValueFromObject(bean, property);
					
		} catch(Exception e) {
			throw new Exception("Impossible de recepérer la proprerty : "
					+ property + ", de l'objet : " + bean.getClass().getSimpleName());
		}
		
		// isEmty
		if("true".equals(isEmty)) {
			if(value == null) {
				return true;
			}
			
			if(value instanceof String) {
				if(commonUtils.isEmpty((String) value)) {
					return true;
				}
			} else if(commonUtils.isCollection(value.getClass())) {
				if(commonUtils.isEmpty((List) value)) {
					return true;
				} else {
					return false;
				}
			}
		}
		
		// value
		if(!commonUtils.isEmpty(valueSt)) {
			if(value instanceof Boolean) {
				if("true".equals(valueSt) && ((Boolean) value)) {
					return true;
				}
				if("false".equals(valueSt) && !((Boolean) value)) {
					return true;
				}
				
			} else if(value instanceof String) {
				// 2 cas : string simple ou tableau de string (st1|st2)
				if(valueSt.indexOf("|") >= 0) {
					StringTokenizer tokenizer = new StringTokenizer(
							(String) valueSt, "|");
					while(tokenizer.hasMoreTokens()) {
						if(value.equals(tokenizer.nextToken())) {
							return true;
						}
					}
				}
				
				// String simple
				if(valueSt.equals((String) value)) {
					return true;
				}
			} else if(value instanceof Long) {
				if(valueSt.equals(String.valueOf(value))) {
					return true;
				}
			} 
		}
		
		return false;
	}
	
	public Map getMapObjectBo() {
		return mapObjectBo;
	}

}
