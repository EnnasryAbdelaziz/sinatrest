package eai.devass.sinistreat.appli.usecase.metier.mission;

import java.util.ArrayList;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.businessInterface.impl.ValueObjectConverterAbst;
import ma.co.omnidata.framework.services.validation.ValidationUnitaireException;

import org.apache.log4j.Logger;

import eai.devass.recoursentrant.application.utils.entites.IConstantes;
import eai.devass.sinistreat.appli.utils.ConverterTools;
import eai.devass.sinistreat.appli.utils.Fonctions;
import eai.devass.sinistreat.appli.utils.exception.IMessageException;
import eai.devass.sinistreat.appli.valueobjects.metier.mission.MissionATVO;

public class RechercheRefNatMissionUCConverter extends  ValueObjectConverterAbst implements IMessageException,IConstantes {

	protected Fonctions functions = new Fonctions();	
	ConverterTools converterTools = ConverterTools.getInstance();
	private Logger logger = Logger.getLogger("loggerSINAT");
	public Object doConvertItemsToValueObject(List listeItems) {
						
	
		if (listeItems == null || listeItems.isEmpty()) {
			return null;
		}
		List listRefNatMissionVO=null;
		try {
			listRefNatMissionVO = (List)listeItems.get(0);

		} catch (Exception e) {
			logger.error("probl�me technique",e);
		}
		
		
		return listRefNatMissionVO;
	}

	public List doConvertValueObjectToItems(Object vo) throws ValidationUnitaireException, ValidationException {
		
		List listOut = new ArrayList();
		MissionATVO missvo = (MissionATVO) vo;
		try {
			listOut.add(converterTools.convertToObjectBO(missvo));
		} catch (Exception e) {
			Logger.getLogger("loggerGSR").fatal("Erreur lors de la conversion !", e);
			throw new ValidationUnitaireException(e.getMessage());
		}

		return listOut;
	}
	

}
