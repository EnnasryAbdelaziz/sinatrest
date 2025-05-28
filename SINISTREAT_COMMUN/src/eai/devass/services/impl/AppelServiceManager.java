package eai.devass.services.impl;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import ma.co.omnidata.framework.services.businessInterface.IResult;
import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.services.businessInterface.impl.ErrorMessageItem;
import ma.co.omnidata.framework.services.core.ServicesFactory;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;

import com.rmawatanya.reglement.application.metier.usecases.services.quittance.ISearchQuittance;

import eai.devass.commun.appli.converter.ConvertorTools;
import eai.devass.commun.appli.exeptions.ExceptionMetier;
import eai.devass.commun.appli.exeptions.IMessageException;
import eai.devass.services.IAppelService;
import eai.devass.services.ServicesExternes;

/**
 * 
 * @author elhacham
 * 
 */
public class AppelServiceManager implements IAppelService, IMessageException {

	private Logger logger = Logger.getLogger("loggerGSR");
	private ConvertorTools cTools = ConvertorTools.getInstance();
	private String USERID = "userId";
	
	private boolean convert = true;

	private Object getService(String serviceName) throws ExceptionMetier {
		try {
			return ServicesFactory.getService("&"
					+ serviceName);
		} catch (Exception e) {
			logger.error(EXP_INIT_SERVICE + serviceName, e);
			throw new ExceptionMetier(EXP_APPEL_SERVICE,e);
		}
	}

	
	
	
	/**
	 * Appel service générique
	 */
	private Object appelServiceImpl(String serviceName, String methodName,
			Object objectIn, String idUser,HashMap params) throws ExceptionMetier {
		try {
			// Conversion BO a VO
			Object VOOut =  objectIn;
			if (this.isConvert()) {
				 VOOut = convertObject(objectIn);
			}
			// Object service = getService(serviceName);
			FactoryBean service = (FactoryBean) getService(serviceName);
			Method method;
			IResult result;
			if (service instanceof RmiProxyFactoryBean) {
				Class classPun = ((RmiProxyFactoryBean) service)
						.getServiceInterface();
				try {
					// Default
					// Obtenir methode avec param Long
					method = classPun.getDeclaredMethod(methodName,
							VOOut.getClass(), long.class);
					result = (IResult) method.invoke(service.getObject(),
							VOOut, Long.valueOf(idUser));
				} catch (NoSuchMethodException e) {
					try {
						// Default
						// Obtenir methode avec param String
						method = classPun.getDeclaredMethod(methodName,
								VOOut.getClass(), String.class);
						result = (IResult) method.invoke(service.getObject(),
								VOOut, idUser);
					} catch (NoSuchMethodException e1) {
						try {
							// Cas d'appel avec trois parametres
							//HashMap params = new HashMap();
							params.put(USERID, idUser);
							params.put("contextModification", true);
							
							method = classPun
									.getDeclaredMethod(methodName,
											VOOut.getClass(), HashMap.class,
											long.class);
							// ////
//							((QuittanceVO) ((PositionnementVO) VOOut)
//									.getRefQuittance().get(0))
//									.setNatureQuittance(NatureQuittance.REGLEMENT);
//							((PositionnementVO) VOOut)
//									.setTypeDestinataire(TypeDestinataire.INTERMEDIAIRE);
//							// ///
							result = (IResult) method.invoke(
									service.getObject(), VOOut, params, 2);
						} 	catch (NoSuchMethodException e2) {
							try{
								//HashMap params = new HashMap();
								params.put(USERID,idUser);
							
								method = classPun.getDeclaredMethod(methodName,
										HashMap.class,long.class);
								result = (IResult) method.invoke(service.getObject(), params, 1);
							}catch(NoSuchMethodException e3){
								try{
							// Default
							// Cas recherche
							//HashMap map = new HashMap();
									params.put(ISearchQuittance.CONTEXT,
									ISearchQuittance.CONTEXT_SERVICE);
							method = classPun.getDeclaredMethod(methodName,
									VOOut.getClass(), HashMap.class);
							result = (IResult) method.invoke(
									service.getObject(), VOOut, params);
							}catch (NoSuchMethodException e4){
						
									// Default
									// Obtenir methode avec param Long
									method = classPun.getDeclaredMethod(methodName,
											VOOut.getClass());
									result = (IResult) method.invoke(service.getObject(),
											VOOut);
							}
							}
						}
					}
				}
			} else {
				logger.error(EXP_SERVICE_NONRMI + serviceName);
				throw new ExceptionMetier(EXP_APPEL_SERVICE);
			}

			// Traitement de result
			if (result == null) {
				logger.error(EXP_RESULT_NULL + serviceName);
				throw new ExceptionMetier(EXP_APPEL_SERVICE);
			}
			if (result.hasErrorMessages()) {
				ErrorMessageItem errorMessage = ((ErrorMessageItem) result
						.getMessagesItem().get(0));
				String codeError = errorMessage.getCodeMessage();
				if (errorMessage.getValues() != null
						&& errorMessage.getValues().length != 0) {
					codeError += ": " + errorMessage.getValues()[0].toString();
				}
				logger.error(EXP_APPEL_SERVICE + " " + codeError);
				throw new ExceptionMetier(getMessege(codeError));
			}
			if (result.getValueObject() == null) {
				logger.error(EXP_RESULT_NULL + serviceName);
				throw new ExceptionMetier(EXP_APPEL_SERVICE);
			}
			// Conversion du VO??
			return result.getValueObject();
			
		} catch (SecurityException e) {
			logger.error(EXP_SECURITY_SERVICE + serviceName, e);
			throw new ExceptionMetier(EXP_APPEL_SERVICE,e);
		} catch (NoSuchMethodException e) {
			logger.error(EXP_NOSUCHMETHOD_SERVICE + methodName, e);
			throw new ExceptionMetier(EXP_APPEL_SERVICE,e);
		} catch (Exception e) {
			logger.error(EXP_APPEL_SERVICE + serviceName, e);
			throw new ExceptionMetier(EXP_APPEL_SERVICE + " : " + e.getMessage(),e);
		}
	}
	
	
	
	
	
	/**
	 * Appel service générique
	 */
	private Object appelServiceImpl(String serviceName, String methodName,
			Object objectIn, String idUser) throws ExceptionMetier {
		try {
			// Conversion BO a VO
			Object VOOut =  objectIn;
			if (this.isConvert()) {
				 VOOut = convertObject(objectIn);
			}
			// Object service = getService(serviceName);
			FactoryBean service = (FactoryBean) getService(serviceName);
			Method method;
			IResult result;
			if (service instanceof RmiProxyFactoryBean) {
				Class classPun = ((RmiProxyFactoryBean) service)
						.getServiceInterface();
				try {
					// Default
					// Obtenir methode avec param Long
					method = classPun.getDeclaredMethod(methodName,
							VOOut.getClass(), long.class);
					result = (IResult) method.invoke(service.getObject(),
							VOOut, Long.valueOf(idUser));
				} catch (NoSuchMethodException e) {
					try {
						// Default
						// Obtenir methode avec param String
						method = classPun.getDeclaredMethod(methodName,
								VOOut.getClass(), String.class);
						result = (IResult) method.invoke(service.getObject(),
								VOOut, idUser);
					} catch (NoSuchMethodException e1) {
						try {
							// Cas d'appel avec trois parametres
							HashMap params = new HashMap();
							params.put(USERID, idUser);
							params.put("contextModification", true);
							
							method = classPun
									.getDeclaredMethod(methodName,
											VOOut.getClass(), HashMap.class,
											long.class);
							// ////
//							((QuittanceVO) ((PositionnementVO) VOOut)
//									.getRefQuittance().get(0))
//									.setNatureQuittance(NatureQuittance.REGLEMENT);
//							((PositionnementVO) VOOut)
//									.setTypeDestinataire(TypeDestinataire.INTERMEDIAIRE);
//							// ///
							result = (IResult) method.invoke(
									service.getObject(), VOOut, params, 2);
						} 	catch (NoSuchMethodException e2) {
							try{
								HashMap params = new HashMap();
								params.put(USERID,idUser);
							
								method = classPun.getDeclaredMethod(methodName,
										HashMap.class,long.class);
								result = (IResult) method.invoke(service.getObject(), params, 1);
							}catch(NoSuchMethodException e3){
								try{
							// Default
							// Cas recherche
							HashMap map = new HashMap();
							map.put(ISearchQuittance.CONTEXT,
									ISearchQuittance.CONTEXT_SERVICE);
							method = classPun.getDeclaredMethod(methodName,
									VOOut.getClass(), HashMap.class);
							result = (IResult) method.invoke(
									service.getObject(), VOOut, map);
							}catch (NoSuchMethodException e4){
						
									// Default
									// Obtenir methode avec param Long
									method = classPun.getDeclaredMethod(methodName,
											VOOut.getClass());
									result = (IResult) method.invoke(service.getObject(),
											VOOut);
							}
							}
						}
					}
				}
			} else {
				logger.error(EXP_SERVICE_NONRMI + serviceName);
				throw new ExceptionMetier(EXP_APPEL_SERVICE);
			}

			// Traitement de result
			if (result == null) {
				logger.error(EXP_RESULT_NULL + serviceName);
				throw new ExceptionMetier(EXP_APPEL_SERVICE);
			}
			if (result.hasErrorMessages()) {
				ErrorMessageItem errorMessage = ((ErrorMessageItem) result
						.getMessagesItem().get(0));
				String codeError = errorMessage.getCodeMessage();
				if (errorMessage.getValues() != null
						&& errorMessage.getValues().length != 0) {
					codeError += ": " + errorMessage.getValues()[0].toString();
				}
				logger.error(EXP_APPEL_SERVICE + " " + codeError);
				throw new ExceptionMetier(getMessege(codeError));
			}
			if (result.getValueObject() == null) {
				logger.error(EXP_RESULT_NULL + serviceName);
				throw new ExceptionMetier(EXP_APPEL_SERVICE);
			}
			// Conversion du VO??
			return result.getValueObject();
			
		} catch (SecurityException e) {
			logger.error(EXP_SECURITY_SERVICE + serviceName, e);
			throw new ExceptionMetier(EXP_APPEL_SERVICE,e);
		} catch (NoSuchMethodException e) {
			logger.error(EXP_NOSUCHMETHOD_SERVICE + methodName, e);
			throw new ExceptionMetier(EXP_APPEL_SERVICE,e);
		} catch (Exception e) {
			logger.error(EXP_APPEL_SERVICE + serviceName, e);
			throw new ExceptionMetier(EXP_APPEL_SERVICE + " : " + e.getMessage(),e);
		}
	}

	private IValueObject convertObject(Object objectIn) throws ExceptionMetier {
		try {
			return (IValueObject) cTools.convertToObject(objectIn);
		} catch (Exception e) {
			logger.error(EXP_CONVERT_OBJECT, e);
			throw new ExceptionMetier(EXP_APPEL_SERVICE,e);
		}
	}

	
	
	public Object appelService(ServicesExternes servicesExternes,
			Object objectIn, String idUser,HashMap map) throws ExceptionMetier {

		if (servicesExternes == null) {
			throw new ExceptionMetier(EXP_INFO_SERVICE);
		}

		String serviceName = servicesExternes.getServiceName();
		String methodName = servicesExternes.getMethodeName();
		if (serviceName == null || methodName == null) {
			throw new ExceptionMetier(EXP_INFO_SERVICE);
		}
		return appelServiceImpl(serviceName, methodName, objectIn, idUser,map);
	}
	
	
	
	
	public Object appelService(ServicesExternes servicesExternes,
			Object objectIn, String idUser) throws ExceptionMetier {

		if (servicesExternes == null) {
			throw new ExceptionMetier(EXP_INFO_SERVICE);
		}

		String serviceName = servicesExternes.getServiceName();
		String methodName = servicesExternes.getMethodeName();
		if (serviceName == null || methodName == null) {
			throw new ExceptionMetier(EXP_INFO_SERVICE);
		}
		return appelServiceImpl(serviceName, methodName, objectIn, idUser);
	}

	public void setConvert(boolean convert) {
		this.convert = convert;
	}

	public boolean isConvert() {
		return convert;
	}
	
	private String getMessege(String codeError) {
		
		if(codeError == null) {
			return "";
		}
		if(codeError.indexOf(": ") > 0) {
			return codeError.substring(codeError.indexOf(": ") + 1,
					codeError.length());
			
		} else {
			return codeError;
		}
		
	}
}
