package eai.devass.sinistreat.appli.utils.exception;

import java.text.MessageFormat;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

import ma.co.omnidata.framework.services.businessInterface.IMessageItem;
import ma.co.omnidata.framework.services.businessInterface.IResult;
import ma.co.omnidata.framework.services.businessInterface.impl.MessageItem;
import ma.co.omnidata.framework.services.core.ServicesFactory;
import ma.co.omnidata.framework.services.dao.IPersistenceService;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.persister.entity.SingleTableEntityPersister;
import org.hibernate.tuple.entity.EntityMetamodel;

import eai.devass.sinistreat.appli.utils.xmlparsing.XmlParsingHbmTools;


/* @author kchakib : 4 oct. 10 */

@SuppressWarnings("all")
public class ExceptionTools {
	
	private static ExceptionTools instance;
	private final static String ORA_01400 = "ORA-01400";
	private final static String ORA_00001 = "ORA-00001";
	private final static String ORA_02291 = "ORA-02291";
	private ResourceBundle resourceBundle = ResourceBundle.getBundle("msgErrors");
	//private DecimalFormat decimalFormat = new DecimalFormat("000");
	private XmlParsingHbmTools xmlParsingHbmTools = XmlParsingHbmTools.getInstance();
	
	
	public static ExceptionTools getInstance(){
		if (instance == null) {
			instance = new ExceptionTools();
		}
		
		return instance; 
	}
	
	/**
	 * Récuperer le message personnaliser correspand au code erreur
	 * @param e
	 * @return
	 */
	public String[] getValuesMessage(ConstraintViolationException e) {
		
		Throwable exceptionCause = e.getCause();
		
		//	Récupérer le code et le message d'error [codeError : message explicite] 
		StringTokenizer messageError = new StringTokenizer(exceptionCause.getMessage(), ":");
		String codeError = messageError.nextToken();
		String message = messageError.nextToken();
		
		if(ORA_01400.equals(codeError)) {
			return formattedErrorMessageORA_01400(message);
		}
		
		if(ORA_00001.equals(codeError)) {
			return formattedErrorMessageORA_00001(message);
		}
		
		if(ORA_02291.equals(codeError)) {
			return formattedErrorMessageORA_02291(message);
		}
		
		return null;
		
	}
	
	/**
	 * Récuperer le code de l'erreur
	 * @param e
	 * @return
	 */
	public String getCodeError(ConstraintViolationException e) {
		
		Throwable exceptionCause = e.getCause();
		
		//	Récupérer le code et le message d'error [codeError : message explicite] 
		StringTokenizer messageError = new StringTokenizer(exceptionCause.getMessage(), ":");
		return messageError.nextToken();
		
	}
	
	/**
	 *org.hibernate.exception.ConstraintViolationException: Could not execute JDBC batch update
	 * Caused by : java.sql.BatchUpdateException: ORA-01400: impossible d'insérer NULL dans ("MISS"."PRM_NATURE_MISSION"."CODDOMAINEACTIVITE")
	 * @param exceptionCause
	 * @return
	 */private String[] formattedErrorMessageORA_01400(String messageError) {
		
		// Récupérer les 3 elements : [schéma, nom de la table, champ]
		StringTokenizer elementsMessage = new StringTokenizer(messageError, ".");
		elementsMessage.nextToken();
		String tableName = elementsMessage.nextToken().replaceAll("\"", "");
		String colonneName = elementsMessage.nextToken().replaceAll("\"", "")
				.replace(")", "").replaceAll("\n", "");
		
		String[] infosClass =  getInformationClass(tableName, colonneName);
		if(infosClass == null) {
			infosClass = new String[] { colonneName, tableName };
		}
		
		return infosClass;
	}
	
	/**
	 * org.hibernate.exception.ConstraintViolationException: Could not execute JDBC batch update
	 * Caused by: java.sql.BatchUpdateException: ORA-00001: unique constraint (MISSION.PK_PRM_ETAT) violated
	 * @param exceptionCause
	 * @return
	 */private String[] formattedErrorMessageORA_00001(String messageError) {
		
		/// Récupérer les 3 elements : [schéma, nom de la table, champ]
		StringTokenizer elementsMessage = new StringTokenizer(messageError, ".");
		elementsMessage.nextToken();
		String contraintName = elementsMessage.nextToken().replaceAll("\"", "");
				
		return new String[] { contraintName.replaceAll("PK_", "(").replaceAll(
				"violated", "") };
	}

	 /**
	 * org.hibernate.exception.ConstraintViolationException: Could not execute JDBC batch update
	 * Caused by: java.sql.BatchUpdateException: ORA-02291: violation de contrainte d'intégrité (MISS.FK_PRM_TARI_TARIFPRES_PRM_PRES) - 
	 * clé parent introuvable
	 * @param exceptionCause
	 * @return
	 */private String[] formattedErrorMessageORA_02291(String messageError) {
		
		/// Récupérer les 3 elements : [schéma, nom de la table, champ]
		StringTokenizer elementsMessage = new StringTokenizer(messageError, ".");
		elementsMessage.nextToken();
		String msgContraint = elementsMessage.nextToken();
		String contraintName = msgContraint.substring(0, msgContraint.indexOf(")"));
				
		return new String[] { contraintName };
	}
		 
	private String[] getInformationClass(String tableName, String colonneName) {
		try {
			IPersistenceService dao = (IPersistenceService) ServicesFactory.getService(IPersistenceService.class);
			SessionFactory factory = ((Session) dao.getSession())
					.getSessionFactory();
			
						
			Collection collection = factory.getAllClassMetadata().values();
			Iterator iterator = collection.iterator();
			while (iterator.hasNext()) {
				Object object = iterator.next();
				if(object instanceof SingleTableEntityPersister) {
					SingleTableEntityPersister entityPersister = (SingleTableEntityPersister) object;
					if(tableName.equals(entityPersister.getTableName())) { 
						EntityMetamodel entityMetamodel = entityPersister.getEntityMetamodel();
											
						// Récuperer le nom de la classe et vérifier son package (parametrage ou metier)
						String mappingPath = "parametrage";
						String className = Class.forName(entityMetamodel.getName()).getSimpleName();
						if(entityMetamodel.getName().indexOf("metier") > 0) {
							mappingPath = "metier";
						}
						
						String property = xmlParsingHbmTools.getPropertyName(colonneName, 
								className, mappingPath);
						if(property == null) {
							return null;
						} else {
							return new String[]{property, className};
						}
					}
				}
			}
			
			return null;

		} catch (Exception e) {
			return null;
		}
	}
	
//	/**
//	 * Récupérer la valeur de la cle déja enregistré dans le fichier properties des erreurs
//	 * @param args
//	 * @return
//	 */public String getMessageError(Class clazzParentError, int numErr, String ...args) {
//		
//		String key = "ERR_" + clazzParentError.getSimpleName() + "_"
//				+ decimalFormat.format(numErr);
//		
//		if (args.length == 0)
//			return MessageFormat.format(resourceBundle.getString(key), null);
//
//		else
//			return MessageFormat.format(resourceBundle.getString(key), args);
//				
//	}
	 
//	 /**
//	 * Récupérer la valeur de la cle déja enregistré dans le fichier properties des erreurs
//	 * @param args
//	 * @return
//	 */public String getMessageError(String key, int numErr, String ...args) {
//		
//		String allkey = "ERR_" + key + "_" + decimalFormat.format(numErr);
//		
//		if (args.length == 0)
//			return MessageFormat.format(resourceBundle.getString(allkey), null);
//
//		else
//			return MessageFormat.format(resourceBundle.getString(allkey), args);
//				
//	}
	 
	 /**
	 * Récupérer la valeur de la cle déja enregistré dans le fichier properties des erreurs
	 * @param args
	 * @return
	 */
	public String getMessageError(String key, String ...args) {
		
		if (args.length == 0) {
			return MessageFormat.format(resourceBundle.getString(key), null);
		} else {
			return MessageFormat.format(resourceBundle.getString(key), args);
		}
				
	}
	 
	/** Message personalisé d'une exception technique */
	public void getListErrorMessageItem(IResult resultIn) {
			/** Recuperer la liste des erreurs */
			List<IMessageItem> listErrorMessage = resultIn.getMessagesItem();
				if(listErrorMessage != null && !listErrorMessage.isEmpty()) {
				for(IMessageItem messageItem : listErrorMessage) {
					if( ((MessageItem)messageItem).getCodeMessage() == null) {
						continue;
					}
					((MessageItem)messageItem).setCodeMessage(resourceBundle.getString(((MessageItem)messageItem).getCodeMessage()));	
					}
				}
	}
}


