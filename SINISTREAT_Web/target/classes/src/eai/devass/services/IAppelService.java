package eai.devass.services;

import eai.devass.commun.appli.exeptions.ExceptionMetier;

/**
 * Interface pour appel services externes
 * 
 * @author elhacham
 * 
 */
public interface IAppelService {
	public Object appelService(ServicesExternes servicesExternes, 
			Object objectIn, String idUser) throws ExceptionMetier;
	public void setConvert(boolean convert);

}
