package eai.devass.sinistreat.appli.services.pub;

import java.util.Map;

import ma.co.omnidata.framework.services.businessInterface.IResult;

@SuppressWarnings("all")
public interface IRecuperationSinistreService  {
	
	/**
	 * Recuperer le dossier sinistre via le numero de sinistre (besoin GED AT)
	 * @param numeroSinistre
	 * @param params
	 * @return
	 * @throws Exception
	 */
	
	public IResult getDossierSinistre(String numeroSinistre, Map params) throws Exception;
	
}
