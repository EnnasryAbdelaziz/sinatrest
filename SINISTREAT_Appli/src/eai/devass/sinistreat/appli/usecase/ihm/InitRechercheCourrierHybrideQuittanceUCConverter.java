package eai.devass.sinistreat.appli.usecase.ihm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.lowagie.text.ListItem;

import eai.devass.sinistreat.appli.modele.metier.sinistre.Sinistre;
import eai.devass.sinistreat.appli.modele.parametrage.CourrierHybride;
import eai.devass.sinistreat.appli.modele.parametrage.CourrierHybrideQuittance;
import eai.devass.sinistreat.appli.utils.ConverterTools;
import eai.devass.sinistreat.appli.valueobjects.metier.sinistre.SinistreVO;
import eai.devass.sinistreat.appli.valueobjects.parametrage.CourrierHybrideQuittanceVO;
import eai.devass.sinistreat.appli.valueobjects.parametrage.CourrierHybrideVO;
import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.businessInterface.impl.ValueObjectConverterAbst;
import ma.co.omnidata.framework.services.validation.ValidationUnitaireException;

public class InitRechercheCourrierHybrideQuittanceUCConverter extends ValueObjectConverterAbst {

	private Logger logger = Logger.getLogger("loggerSINAT");
	ConverterTools converterTools = ConverterTools.getInstance();

	public Object doConvertItemsToValueObject(List listeItems) {
		
		if (listeItems == null || listeItems.isEmpty()) {
			return null;
		}
		Map map= new HashMap();
		try {
			List listResultCourrierHybrideQuittance = (List)listeItems.get(0);
		
			List listCourrierHybrideQuittanceVO = ConverterTools.getInstance().convertToListObjectVO(listResultCourrierHybrideQuittance);		
			map.put("listCourrierHybrideQuittanceVO", listCourrierHybrideQuittanceVO);

		} catch (Exception e) {
			logger.error("problème technique",e);
		}
		
		return map;
	}

	public List doConvertValueObjectToItems(Object vo)
			throws ValidationUnitaireException, ValidationException {
		List listOut = new ArrayList();
		CourrierHybrideQuittanceVO sinvo = (CourrierHybrideQuittanceVO) vo;
		try {
			CourrierHybrideQuittance sin = (CourrierHybrideQuittance) converterTools.convertToObjectBO(sinvo);
			listOut.add(sin);
		} catch (Exception e) {
			Logger.getLogger("loggerGSR").fatal("Erreur lors de la conversion !", e);
			throw new ValidationUnitaireException(e.getMessage());
		}
		return listOut;
	}

    
    

   
}
