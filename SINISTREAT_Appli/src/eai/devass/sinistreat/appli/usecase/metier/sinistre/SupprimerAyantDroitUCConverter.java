package eai.devass.sinistreat.appli.usecase.metier.sinistre;

import java.util.ArrayList;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.businessInterface.impl.ValueObjectConverterAbst;
import ma.co.omnidata.framework.services.validation.ValidationUnitaireException;

import org.apache.log4j.Logger;

import eai.devass.sinistreat.appli.modele.metier.sinistre.AyantDroit;
import eai.devass.sinistreat.appli.utils.ConverterTools;
import eai.devass.sinistreat.appli.utils.Fonctions;
import eai.devass.sinistreat.appli.utils.exception.IMessageException;
import eai.devass.sinistreat.appli.valueobjects.metier.sinistre.AyantDroitVO;

public class SupprimerAyantDroitUCConverter extends  ValueObjectConverterAbst implements IMessageException {

	protected Fonctions functions = new Fonctions();	
	ConverterTools converterTools = ConverterTools.getInstance();
	
	public Object doConvertItemsToValueObject(List listeItems) {
		
		
		if (listeItems == null || listeItems.isEmpty()) {
			return null;
		}
		
		AyantDroitVO ayvo= new AyantDroitVO();
		
		//wmos: correction sonar 29/09/2014
//		try {
//
//		} catch (Exception e) {
//			logger.error("problème technique",e);
//		}
		
		
		return ayvo;
	}

	public List doConvertValueObjectToItems(Object vo) throws ValidationUnitaireException, ValidationException {
		
		List listOut = new ArrayList();
		AyantDroitVO ayvo = (AyantDroitVO)vo;
		try{

			AyantDroit ay=(AyantDroit)converterTools.convertToObjectBO(ayvo);
			listOut.add(ay);
			
		} catch(Exception e) {
			Logger.getLogger("loggerGSR").fatal("Erreur lors de la conversion !", e);
			throw new ValidationUnitaireException(e.getMessage());
		}
						
		return listOut;
	}
	

}
