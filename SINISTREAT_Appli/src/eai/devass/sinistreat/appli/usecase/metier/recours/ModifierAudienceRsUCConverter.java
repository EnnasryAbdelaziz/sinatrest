package eai.devass.sinistreat.appli.usecase.metier.recours;

import java.util.ArrayList;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.businessInterface.impl.ValueObjectConverterAbst;
import ma.co.omnidata.framework.services.validation.ValidationUnitaireException;

import org.apache.log4j.Logger;

import eai.devass.sinistreat.appli.modele.metier.contentieux.AudienceJugement;
import eai.devass.sinistreat.appli.utils.ConverterTools;
import eai.devass.sinistreat.appli.utils.Fonctions;
import eai.devass.sinistreat.appli.utils.exception.IMessageException;
import eai.devass.sinistreat.appli.valueobjects.metier.contentieux.AudienceJugementVO;

public class ModifierAudienceRsUCConverter extends ValueObjectConverterAbst
		implements IMessageException {
	protected Fonctions functions = new Fonctions();
	ConverterTools converterTools = ConverterTools.getInstance();
	private Logger logger = Logger.getLogger("loggerSINAT");

	public Object doConvertItemsToValueObject(List listeItems) {

	if (listeItems == null || listeItems.isEmpty()) {
		return null;
	}
	AudienceJugementVO audienceVO = null;
	AudienceJugement audience = null;
	try {
		audience = (AudienceJugement) listeItems.get(0);
		audienceVO = (AudienceJugementVO) converterTools.convertToObjectVO(audience);
	} catch (Exception e) {
		logger.error("problème technique",e);
	}

	return audienceVO;
}

public List doConvertValueObjectToItems(Object vo)
		throws ValidationUnitaireException, ValidationException {

	List listOut = new ArrayList();

	AudienceJugementVO audienceVO = (AudienceJugementVO) vo;
	AudienceJugement audience=null;
	try {
		
		audience = (AudienceJugement) converterTools.convertToObjectBO(audienceVO);
		listOut.add(audience);
	} catch (Exception e) {
		Logger.getLogger("loggerGSR").fatal("Erreur lors de la conversion !", e);
		throw new ValidationUnitaireException(e.getMessage());
	}

	return listOut;
}
}
