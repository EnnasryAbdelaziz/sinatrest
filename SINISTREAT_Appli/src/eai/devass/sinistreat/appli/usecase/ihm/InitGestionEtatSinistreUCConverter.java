package eai.devass.sinistreat.appli.usecase.ihm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.businessInterface.impl.ValueObjectConverterAbst;
import ma.co.omnidata.framework.services.validation.ValidationUnitaireException;
import eai.devass.sinistreat.appli.modele.metier.sinistre.Sinistre;
import eai.devass.sinistreat.appli.utils.ConverterTools;
import eai.devass.sinistreat.appli.valueobjects.metier.sinistre.SinistreVO;

public class InitGestionEtatSinistreUCConverter extends ValueObjectConverterAbst{
	private Logger logger = Logger.getLogger("loggerSINAT");
	public Object doConvertItemsToValueObject(List listeItems) {
		
		if (listeItems == null || listeItems.isEmpty()) {
			return null;
		}
		Map map= new HashMap();
		try {
			List listResultBO0 = (List)listeItems.get(0);
			List listResultBO1 = (List)listeItems.get(1);
			Sinistre sin = (Sinistre)listeItems.get(2);
			List listEtatSinistreActuelVO = ConverterTools.getInstance().convertToListObjectVO(listResultBO0);
			List listEtatSinistreCibleVO = ConverterTools.getInstance().convertToListObjectVO(listResultBO1);
			SinistreVO sinvo = (SinistreVO)ConverterTools.getInstance().convertToObjectVOWithoutList(sin);
			map.put("listEtatSinistreActuel", listEtatSinistreActuelVO);
			map.put("listEtatSinistreCible", listEtatSinistreCibleVO);
			map.put("SinistreVO", sinvo);
		
			
		} catch (Exception e) {
			logger.error("problème technique",e);
		}
		
		
		return map;
		
		
	}

	public List doConvertValueObjectToItems(Object vo) throws ValidationUnitaireException, ValidationException {
		return null;
				
	}

    
    

	

}
