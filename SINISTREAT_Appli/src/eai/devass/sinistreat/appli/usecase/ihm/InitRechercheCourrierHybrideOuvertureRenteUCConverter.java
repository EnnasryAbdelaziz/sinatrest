package eai.devass.sinistreat.appli.usecase.ihm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;


import eai.devass.sinistreat.appli.modele.parametrage.CourrierHybrideOuvertureRente;

import eai.devass.sinistreat.appli.utils.ConverterTools;

import eai.devass.sinistreat.appli.valueobjects.parametrage.CourrierHybrideOuvertureRenteVO;


import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.businessInterface.impl.ValueObjectConverterAbst;
import ma.co.omnidata.framework.services.validation.ValidationUnitaireException;

public class InitRechercheCourrierHybrideOuvertureRenteUCConverter extends ValueObjectConverterAbst {

	private Logger logger = Logger.getLogger("loggerSINAT");
	ConverterTools converterTools = ConverterTools.getInstance();

	public Object doConvertItemsToValueObject(List listeItems) {
		
		if (listeItems == null || listeItems.isEmpty()) {
			return null;
		}
		Map map= new HashMap();
		try {
			List listResultCourrierHybrideOuvertureRente = (List)listeItems.get(0);
		
			List listCourrierHybrideOuvertureRenteVO = ConverterTools.getInstance().convertToListObjectVO(listResultCourrierHybrideOuvertureRente);		
			map.put("listCourrierHybrideOuvertureRenteVO", listCourrierHybrideOuvertureRenteVO);

		} catch (Exception e) {
			logger.error("problème technique",e);
		}
		
		return map;
	}

	public List doConvertValueObjectToItems(Object vo)
			throws ValidationUnitaireException, ValidationException {
		List listOut = new ArrayList();
		CourrierHybrideOuvertureRenteVO sinvo = (CourrierHybrideOuvertureRenteVO) vo;
		try {
			CourrierHybrideOuvertureRente sin = (CourrierHybrideOuvertureRente) converterTools.convertToObjectBO(sinvo);
			listOut.add(sin);
		} catch (Exception e) {
			Logger.getLogger("loggerGSR").fatal("Erreur lors de la conversion !", e);
			throw new ValidationUnitaireException(e.getMessage());
		}
		return listOut;
	}

    
    

   
}
