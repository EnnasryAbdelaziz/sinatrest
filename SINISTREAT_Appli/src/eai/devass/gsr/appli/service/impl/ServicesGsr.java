package eai.devass.gsr.appli.service.impl;

import java.util.Date;
import java.util.HashMap;

import ma.co.omnidata.framework.services.businessInterface.IResult;
import ma.co.omnidata.framework.services.businessInterface.impl.ErrorMessageItem;
import ma.co.omnidata.framework.services.businessInterface.impl.FacadeServiceUseCase;
import ma.co.omnidata.framework.services.businessInterface.impl.Result;

import org.apache.log4j.Logger;

import eai.devass.gsr.appli.service.pub.IServicesGsr;
import eai.devass.gsr.appli.usecase.metier.mouvements.EnregisterMouvementUC;
import eai.devass.gsr.appli.valueobjects.metier.mouvements.MouvementVO;

public class ServicesGsr implements IServicesGsr {

	private transient Logger logger = Logger.getLogger("loggerGSR");
	public IResult enregisterMouvement(MouvementVO mouvementVO, HashMap params) throws Exception {
		
		Date dateDebut = new Date();
		logger.info("Début d'appelle du service GSR; "+dateDebut.getTime()+" ;ms");
		
		try {
			FacadeServiceUseCase useCase = new EnregisterMouvementUC();
			Date dateFin = new Date();
			IResult result = useCase.executer(mouvementVO, params);
			logger.info("Fin d'appelle du service GSR; "+(dateFin.getTime() - dateDebut.getTime()) + " ;ms");
			return result;
			
		
		} catch(Exception e) {
			IResult result = new Result();
			result.addMessageItem(new ErrorMessageItem(e.getMessage()));
			Date dateFin = new Date();
			logger.info("Fin d'appelle du service GSR; "+(dateFin.getTime() - dateDebut.getTime()) + " ;ms");
			return result;
			
		}
		
		
	}

}
