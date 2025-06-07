package eai.devass.sinistreat.appli.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import ma.co.omnidata.framework.services.core.ServicesFactory;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.log4j.Logger;

import eai.devass.sinistreat.appli.authentification.Profil;
import eai.devass.sinistreat.appli.authentification.Utilisateur;
import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.manager.ParametrageManager;
import eai.devass.sinistreat.appli.utils.auth.SasServicesTools;
import eai.devass.sinistreat.appli.utils.entites.IParam;
import eai.devass.sinistreat.appli.utils.entites.IProfil;
import eai.devass.sinistreat.appli.utils.entites.ITypeUtilisateur;
import eai.devass.sinistreat.appli.valueobjects.parametrage.EntiteOrgVO;

//import eai.devass.sinistreat.appli.valueobjects.parametrage.PrestataireVO.TypePrestataire;

/**
 * 
 * @author
 * 
 */
public class Tools {

	private static Logger logger = Logger.getLogger("loggerSINAT");
	public static Properties props;
	private static Tools instance;

	public synchronized static Tools getInstance() {
		if (instance == null) {
			instance = new Tools();
		}

		return instance;
	}

	public static void reset(Object object) {

		List<Field> fields = new ArrayList<Field>();
		try {
			fields = GetAllFields(object);
		} catch (Exception e1) {
		}
		for (Field field : fields) {
			if (field.getType().equals(String.class)) {
				try {
					BeanUtilsBean.getInstance().setProperty(object,
							field.getName(), "");
				} catch (Exception e) {
				}
			}
			// if (field.getType().equals(TypePrestataire.class)) {
			// try {
			// BeanUtilsBean.getInstance().setProperty(object,
			// field.getName(),null);
			// } catch (Exception e) {
			// }
			// }

			if (field.getType().equals(Collection.class)) {
				try {
					BeanUtilsBean.getInstance().setProperty(object,
							field.getName(), new ArrayList());
				} catch (Exception e) {
				}
			}
			if (field.getType().equals(List.class)) {
				try {
					BeanUtilsBean.getInstance().setProperty(object,
							field.getName(), new ArrayList());
				} catch (Exception e) {
				}
			}
			//wmos: correction sonar 29/09/2014
//			try {
//				// if (isValueObject(field)) {
//				// reset(BeanUtilsBean.getInstance().getPropertyUtils().getProperty(object,field.getName()));
//				// //=Class.forName(field.getName());
//				// }
//			} catch (Exception e) {
//			}

			// if(field.getType().equals(Map.class))
			// {
			// try {
			// BeanUtilsBean.getInstance().setProperty(object, field.getName(),
			// new HashMap());
			// } catch(Exception e) {}
			// }
		}

	}

	public static void resetAll(Object object) {

		List<Field> fields = new ArrayList<Field>();
		try {
			fields = GetAllFields(object);
		} catch (Exception e1) {
		}
		for (Field field : fields) {
			try {
				if (field.getType().equals(String.class)) {

					BeanUtilsBean.getInstance().setProperty(object,
							field.getName(), "");

				} else {
					BeanUtilsBean.getInstance().setProperty(object,
							field.getName(), null);

				}
			} catch (Exception e) {
			}
		}

	}

	public static boolean isNullObjectFields(Object object) {

		List<Field> fields = new ArrayList<Field>();
		try {
			fields = GetAllFields(object);
		} catch (Exception e1) {
		}
		Object value = null;

		for (Field field : fields) {

			if (field.getType().equals(String.class)) {
				try {
					value = BeanUtilsBean.getInstance().getProperty(object,
							field.getName());
					if (value != null && !value.equals("")) {
						return false;
					}
				} catch (Exception e) {
				}
			}

			if (field.getType().equals(Collection.class)) {
				try {
					Collection collection = (Collection) BeanUtilsBean
							.getInstance().getPropertyUtils()
							.getProperty(object, field.getName());
					for (Object obj : collection) {
						boolean isNull = isNullObjectFields(obj);
						if (!isNull) {
							return false;
						}
					}
				} catch (Exception e) {
				}
			}
			if (field.getType().equals(List.class)) {
				try {
					List list = (List) BeanUtilsBean.getInstance()
							.getPropertyUtils()
							.getProperty(object, field.getName());
					for (Object obj : list) {
						boolean isNull = isNullObjectFields(obj);
						if (!isNull) {
							return false;
						}
					}
				} catch (Exception e) {
				}
			}
			try {
				value = BeanUtilsBean.getInstance().getPropertyUtils()
						.getProperty(object, field.getName());
				if (isReference(value.getClass())) {
					isNullObjectFields(value);
				}
			} catch (Exception e) {
			}
		}
		return true;
	}

	public static boolean setNullToAllNullableReferenceFields(Object object) {

		List<Field> fields = new ArrayList<Field>();
		try {
			fields = GetAllFields(object);
		} catch (Exception e1) {
		}
		Object value = null;
		for (Field field : fields) {

			try {
				value = BeanUtilsBean.getInstance().getPropertyUtils()
						.getProperty(object, field.getName());
				if (isReference(value.getClass())) {
					if (isNullObjectFields(value)) {
						BeanUtilsBean.getInstance().getPropertyUtils()
								.setProperty(object, field.getName(), null);
					}
				}
			} catch (Exception e) {
			}
		}
		return true;
	}

	private static boolean isReference(Class clazz) {
		if (clazz.isPrimitive()) {
			return false;
		}
		if (clazz.isEnum()) {
			return false;
		}
		Package pakage = clazz.getPackage();
		if (pakage == null || pakage.getName().startsWith("java")
				|| pakage.getName().startsWith("org")) {
			return false;
		}
		return true;
	}

	// retourne tous les attributs d'un object
	public static List<Field> GetAllFields(Object object)
			throws IllegalAccessException, InvocationTargetException,
			NoSuchMethodException, Exception {
		Class clazz = object.getClass();
		Field[] fields = clazz.getDeclaredFields();
		List<Field> allFields = new ArrayList<Field>();

		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			allFields.add(field);
		}

		while ((clazz = clazz.getSuperclass()) != null) {
			try {
				fields = clazz.getDeclaredFields();
				for (int i = 0; i < fields.length; i++) {
					Field field = fields[i];
					allFields.add(field);
				}
			} catch (SecurityException e) {
			}

		}

		return allFields;

	}

	public static Utilisateur getConnectedUser(HttpServletRequest request) {
		try {
			Utilisateur user = (Utilisateur) request.getSession().getAttribute(
					IParam.UTILISATEUR);
			//System.out.println("user :" + user);
			logger.info("user :" + user);
			return user;
		} catch (Exception e) {
			logger.error("Error Connected User", e);
			return null;
		}
	}

	public static Utilisateur getTestUser() {
		try {
			Utilisateur user = new Utilisateur("GESTAUTO", "gestionnaire");
			user.addProfil(new Profil(IProfil.GESTIONNAIRE));
			EntiteOrgVO entiteOrgVO = new EntiteOrgVO();
			entiteOrgVO.setCode("W9560");
			user.setRefEntite(entiteOrgVO);
			return user;
		} catch (Exception e) {
			logger.error("Error Test User", e);
			return null;
		}
	}

	public static Utilisateur getUtilisateurByCode(String codeUser)
			throws Exception {
		Utilisateur user = new Utilisateur();
		ParametrageManager parametrageManager = (ParametrageManager) ServicesFactory
				.getService(ParametrageManager.class);

		// appel au service SAS et ASAL pour autentifiction Recuperation des
		// ressources.
		// autentifiction
		// recuperation de l'utilisateur et de ses ressources

		user.setCodeUser(codeUser);
		user.setActif("1");
		user = (Utilisateur) parametrageManager.findObject(user);
		if (user == null) {
			throw new FonctionnelleException("FWK.SECURITY.FWK.USER.UNFOUND");
		}
		if (!user.getTypeUser().equals(ITypeUtilisateur.EXTERNE)) {
			Utilisateur userSAS = SasServicesTools.getInstance()
					.getUtilisateurWithResources(
							user.getCodeUser().toUpperCase());
			if (userSAS != null) {
				user.setRefEntite(userSAS.getRefEntite());
				user.setRefFonctionnalite(userSAS.getRefFonctionnalite());
				user.setRefProfils(userSAS.getRefProfils());
				user.setNomUser(userSAS.getNomUser());
			}
		}
		return user;
	}

	public static String convertToXMLGrid(List<? extends Object> list,
			String[] fieldNames, Map<String, String> gridFormatterMethods,
			boolean repeatitems) {

		String userdata = "<userdata name=\"hidden\">"
				+ !isFieldExiste("responsable", fieldNames) + "</userdata>\n";
		String XML = "<?xml version=\"1.0\" encoding=\"iso-8859-1\"?><rows>\n"
				+ "<page>1</page>\n" + "<total>" + list.size() / 2
				+ "</total>\n" + "<records>" + list.size() + "</records>\n"
				+ userdata;
		String cell = "cell";
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(XML);

		for (Object o : list) {
			int i = 0;
			for (String fieldName : fieldNames) {
				try {
					String value = BeanUtilsBean.getInstance().getProperty(o,
							fieldName);
					if (value == null) {
						value = "";
					}
					if (i == 0) {
						stringBuilder.append("<row id=\"").append(value)
								.append("\">\n");
					}
					// utiliser le tag cell ou le nom du champs
					if (!repeatitems) {
						cell = fieldName;
					}
					// Si c'est un champ qui doit etre formaté
					if (gridFormatterMethods.containsKey(fieldName)) {
						String formatterMethod = gridFormatterMethods
								.get(fieldName);
						Class toolsClass = Class
								.forName("eai.devass.missionnement.utils.Tools");
						Class methodTypes[] = new Class[2];
						methodTypes[0] = String.class;
						methodTypes[1] = String.class;
						Method m = toolsClass.getMethod(formatterMethod,
								methodTypes);
						Tools tools = new Tools();
						Object arglist[] = new Object[2];
						arglist[0] = new String(value);
						arglist[1] = new String(cell);
						Object returnedObj = m.invoke(tools, arglist);

						stringBuilder.append((String) returnedObj);

					} else {
						// XML+="<cell>"+value+"</cell>\n";
						stringBuilder.append("<").append(cell).append(">")
								.append(value).append("</").append(cell)
								.append(">\n");
					}
					i++;

				} catch (Exception e) {
					// TODO Auto-generated catch block
					logger.error("Error convert To XML Grid", e);
				}
			}
			// specific to the MissionGrid
			//wmos: correction sonar 29/09/2014
//			try {
//				// XML+=formatAction(BeanUtilsBean.getInstance().getProperty(o,"numMission"),"actions");
//			} catch (Exception e) {
//				logger.error("Error création transaction", e);
//			}

			stringBuilder.append("</row>\n");
		}
		return stringBuilder.append("</rows>\n").toString();
	}

	public static String convertToXMLGridRecherche(List<? extends Object> list,
			String[] fieldNames, Map<String, String> gridFormatterMethods,
			boolean repeatitems) {

		String XML = "<?xml version=\"1.0\" encoding=\"iso-8859-1\"?><rows>\n"
				+ "<page>1</page>\n" + "<total>" + list.size() / 2
				+ "</total>\n" + "<records>" + list.size() + "</records>\n";
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(XML);
		String cell = "cell";
		int j = 0;
		for (Object o : list) {
			//System.out.println("j :" + j);
			logger.info("j :" + j);
			int i = 0;
			for (String fieldName : fieldNames) {
				try {
					String value = BeanUtilsBean.getInstance().getProperty(o,
							fieldName);
					if (value == null) {
						value = "";
					}
					if (i == 0) {
						stringBuilder.append("<row id=\"").append(value)
								.append("\">\n");
					}
					if (!repeatitems) {
						cell = fieldName;
					}
					// Si c'est un champ qui doit etre formaté
					if (gridFormatterMethods.containsKey(fieldName)) {
						String formatterMethod = gridFormatterMethods
								.get(fieldName);
						Class toolsClass = Class
								.forName("eai.devass.missionnement.utils.Tools");
						Class methodTypes[] = new Class[2];
						methodTypes[0] = String.class;
						methodTypes[1] = String.class;
						Method m = toolsClass.getMethod(formatterMethod,
								methodTypes);
						Tools tools = new Tools();
						Object arglist[] = new Object[2];
						arglist[0] = new String(value);
						arglist[1] = new String(cell);
						Object returnedObj = m.invoke(tools, arglist);

						stringBuilder.append((String) returnedObj);

					} else {
						// XML+="<cell>"+value+"</cell>\n";
						stringBuilder.append("<").append(cell).append(">")
								.append(value).append("</").append(cell)
								.append(">\n");
					}
					i++;

				} catch (Exception e) {
					// TODO Auto-generated catch block
					logger.error("Error convert To XML Grid Recherche", e);
				}
			}
			j++;
			// specific to the MissionGrid
			
			//wmos: correction sonar 29/09/2014
//			try {
//				// XML+=formatAction(BeanUtilsBean.getInstance().getProperty(o,"numMission"));
//			} catch (Exception e) {
//				logger.error("Error création transaction", e);
//			}

			stringBuilder.append("</row>\n");
		}
		return stringBuilder.append("</rows>\n").toString();
	}

	// Recherche & consultation
	public String formatNumMission(String value) {
		String XML = "<cell>&lt;a href=\"javascript:showDetailMission('"
				+ value + "','0')\" class=\"\"&gt;";// ui-icon
													// ui-icon-circle-plus
		// XML += value;
		XML += "&lt;/a&gt; &lt;a href=\"javascript:showDetailMission('" + value
				+ "','0')\" &gt;";
		XML += value;
		XML += "&lt;/a&gt;</cell>\n";
		return XML;
	}

	// concerne mission à traiter/ cloturer / soumises
	public String formatNumMission(String value, String cell) {
		String XML = "<" + cell + ">" + value + "</" + cell + ">\n";
		if (value != null && !"".equals(value)) {
			XML = "<" + cell + ">&lt;a href=\"javascript:showDetailMission('"
					+ value + "','1')\" class=\"\"&gt;";// ui-icon
														// ui-icon-circle-plus
			// XML += value;
			XML += "&lt;/a&gt; &lt;a href=\"javascript:showDetailMission('"
					+ value + "','1')\" &gt;";
			XML += value;
			XML += "&lt;/a&gt;</" + cell + ">\n";
		}
		return XML;
	}

	public String formatNumMissionRech(String value, String cell) {
		String XML = "<" + cell + ">" + value + "</" + cell + ">\n";
		if (value != null && !"".equals(value)) {
			XML = "<" + cell + ">&lt;a href=\"javascript:showDetailMission('"
					+ value + "','0')\" class=\"\"&gt;";// ui-icon
														// ui-icon-circle-plus
			// XML += value;
			XML += "&lt;/a&gt; &lt;a href=\"javascript:showDetailMission('"
					+ value + "','0')\" &gt;";
			XML += value;
			XML += "&lt;/a&gt;</" + cell + ">\n";
		}
		return XML;
	}

	public String formatNumQuittance(String value, String cell) {
		String XML = "<" + cell + ">" + value + "</" + cell + ">\n";
		if (value != null && !"".equals(value)) {
			XML = "<" + cell + ">&lt;a href=\"javascript:showDetailQuittance('"
					+ value + "','0')\" class=\"\"&gt;";// ui-icon
														// ui-icon-circle-plus
			// XML += value;
			XML += "&lt;/a&gt; &lt;a href=\"javascript:showDetailQuittance('"
					+ value + "','0')\" &gt;";
			XML += value;
			XML += "&lt;/a&gt;</" + cell + ">\n";
		}
		return XML;
	}

	public static String formatAction(String value) {

		return "<cell>&lt;select width= \"150px\" onclick=\"getActions('"
				+ value
				+ "',this)\" id='action"
				+ value
				+ "' onchange=\"getPage(this,'"
				+ value
				+ "')\" &gt;&lt;option value=\"\"&gt;************&lt;/option&gt;&lt;/select&gt;</cell>";

	}

	public static String formatAction(String value, String cell) {

		return "<"
				+ cell
				+ ">&lt;select width= \"150px\" onclick=\"getActions('"
				+ value
				+ "',this)\" id='action"
				+ value
				+ "' onchange=\"getPage(this,'"
				+ value
				+ "')\" &gt;&lt;option value=\"\"&gt;************&lt;/option&gt;&lt;/select&gt;</"
				+ cell + ">";

	}

	public static byte[] convertObjectToByteData(Object object)
			throws IOException {

		// Serialize to a byte array
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutput out = new ObjectOutputStream(bos);
		out.writeObject(object);
		out.close();
		// Get the bytes of the serialized object
		byte[] buf = bos.toByteArray();

		return buf;
	}

	public static boolean isFieldExiste(String field, String[] fieldNames) {
		boolean exist = false;
		for (String fieldName : fieldNames) {
			if (fieldName == field) {
				exist = true;
			}
		}
		return exist;
	}

	public static String convertToXMLGridRechercheOld(
			List<? extends Object> list, String[] fieldNames,
			Map<String, String> gridFormatterMethods) {

		String XML = "<?xml version=\"1.0\" encoding=\"iso-8859-1\"?><rows>\n"
				+ "<page>1</page>\n" + "<total>" + list.size() / 2
				+ "</total>\n" + "<records>" + list.size() + "</records>\n";
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(XML);
		for (Object o : list) {
			int i = 0;
			for (String fieldName : fieldNames) {
				try {
					String value = BeanUtilsBean.getInstance().getProperty(o,
							fieldName);
					if (value == null) {
						value = "";
					}
					if (i == 0) {
						stringBuilder.append("<row id=\"").append(value)
								.append("\">\n");
					}
					// Si c'est un champ qui doit etre formaté
					if (gridFormatterMethods.containsKey(fieldName)) {
						String formatterMethod = gridFormatterMethods
								.get(fieldName);
						Class toolsClass = Class
								.forName("eai.devass.missionnement.utils.Tools");
						Class methodTypes[] = new Class[1];
						methodTypes[0] = String.class;
						Method m = toolsClass.getMethod(formatterMethod,
								methodTypes);
						Tools tools = new Tools();
						Object arglist[] = new Object[1];
						arglist[0] = new String(value);
						Object returnedObj = m.invoke(tools, arglist);

						stringBuilder.append((String) returnedObj);

					} else {
						stringBuilder.append("<cell>").append(value)
								.append("</cell>\n");
					}
					i++;

				} catch (Exception e) {
					// TODO Auto-generated catch block
					logger.error("Error convert To XML Grid Recherche Old", e);
				}
			}
			// specific to the MissionGrid
			
			//wmos: correction sonar 29/09/2014
//			try {
//				// XML+=formatAction(BeanUtilsBean.getInstance().getProperty(o,"numMission"));
//			} catch (Exception e) {
//				logger.error("Error création transaction", e);
//			}

			stringBuilder.append("</row>\n");
		}
		return stringBuilder.append("</rows>\n").toString();
	}

}
