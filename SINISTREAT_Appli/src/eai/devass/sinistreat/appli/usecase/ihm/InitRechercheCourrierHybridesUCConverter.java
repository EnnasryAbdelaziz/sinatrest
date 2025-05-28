package eai.devass.sinistreat.appli.usecase.ihm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import eai.devass.sinistreat.appli.modele.parametrage.CourrierHybride;
import eai.devass.sinistreat.appli.utils.ConverterTools;
import eai.devass.sinistreat.appli.valueobjects.parametrage.CourrierHybrideVO;
import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.businessInterface.impl.ValueObjectConverterAbst;
import ma.co.omnidata.framework.services.validation.ValidationUnitaireException;

public class InitRechercheCourrierHybridesUCConverter extends ValueObjectConverterAbst {

	private Logger logger = Logger.getLogger("loggerSINAT");
	ConverterTools converterTools = ConverterTools.getInstance();

	
	public Object doConvertItemsToValueObject(List listeItems) {
		
		if (listeItems == null || listeItems.isEmpty()) {
			return null;
		}
		Map map= new HashMap();
		try {
			List listResultCourrierHybrides = (List)listeItems.get(0);
			
			
			List listCourrierHybrideVO = ConverterTools.getInstance().convertToListObjectVO(listResultCourrierHybrides);		
			map.put("listCourrierHybrideVO", listCourrierHybrideVO);

		} catch (Exception e) {
			logger.error("problème technique",e);
		}
		
		
		return map;
		
		
	}

	public List doConvertValueObjectToItems(Object vo)
			throws ValidationUnitaireException, ValidationException {
		List listOut = new ArrayList();
		CourrierHybrideVO sinvo = (CourrierHybrideVO) vo;
		try {
			CourrierHybride sin = (CourrierHybride) converterTools.convertToObjectBO(sinvo);
			listOut.add(sin);
		} catch (Exception e) {
			Logger.getLogger("loggerGSR").fatal("Erreur lors de la conversion !", e);
			throw new ValidationUnitaireException(e.getMessage());
		}
		return listOut;
	}

    
    

   
}
