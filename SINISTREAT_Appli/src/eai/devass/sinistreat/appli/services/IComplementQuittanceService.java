package eai.devass.sinistreat.appli.services;

import ma.co.omnidata.framework.services.businessInterface.IResult;

public interface IComplementQuittanceService  {
	
	/**
	 * Recuperer certaines informations AT necessaires pour la synchro quittance
	 * @param numeroQuittance
	 * @param params
	 * @return
	 * @throws Exception
	 */public IResult getComplementQuittance(String numeroQuittance) throws Exception;
}
