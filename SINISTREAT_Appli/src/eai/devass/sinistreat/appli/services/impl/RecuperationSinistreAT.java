package eai.devass.sinistreat.appli.services.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import ma.co.omnidata.framework.services.businessInterface.IResult;
import ma.co.omnidata.framework.services.businessInterface.OMNIFacade;
import ma.co.omnidata.framework.services.businessInterface.impl.ErrorMessageItem;
import ma.co.omnidata.framework.services.securite.impl.OMNIAction;
import eai.devass.commun.appli.exeptions.ExceptionMetier;
import eai.devass.sinistreat.appli.services.pub.IRecuperationSinistreService;
import eai.devass.sinistreat.appli.valueobjects.metier.sinistre.SinistreVO;

@SuppressWarnings("all")
public class RecuperationSinistreAT implements IRecuperationSinistreService {

	private final static String consulterSinistreUC = "112";
	public static Logger logger = Logger.getLogger("SINAT");

	public IResult getDossierSinistre(String numeroSinistre,
			Map param) throws Exception {
		Date dateDebut = new Date();
		logger.info("Début d'appelle du service de récupération des dossiers sinistres pour le besoin de l'application GED AT; "+dateDebut.getTime()+ numeroSinistre + " ;ms");
		
		OMNIAction action = new OMNIAction();
		
		action.setActionId(consulterSinistreUC);

		// INvok UC
		SinistreVO sinistreVO = new SinistreVO(numeroSinistre);
		HashMap params = new HashMap();
		params.put("completeConversion", true);
		OMNIFacade facade = new OMNIFacade();
		IResult result = facade.invokeService(null, action, sinistreVO, params);
		
		Date dateFin = new Date();
		logger.info("Fin d'appelle du service de récupération des dossiers sinistres pour le besoin de l'application GED AT; "+(dateFin.getTime() - dateDebut.getTime()) + " ;ms");
		return result;
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
