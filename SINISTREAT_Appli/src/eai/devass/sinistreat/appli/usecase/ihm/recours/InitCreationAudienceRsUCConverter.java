package eai.devass.sinistreat.appli.usecase.ihm.recours;

/* @author joundi : 1 Nov. 10 */
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.businessInterface.impl.ValueObjectConverterAbst;
import ma.co.omnidata.framework.services.validation.ValidationUnitaireException;
import eai.devass.sinistreat.appli.utils.ConverterTools;
import eai.devass.sinistreat.appli.utils.Fonctions;


@SuppressWarnings("unchecked")
public class InitCreationAudienceRsUCConverter extends ValueObjectConverterAbst {
	private Logger logger = Logger.getLogger("loggerSINAT");
	protected Fonctions functions = new Fonctions();
	ConverterTools converterTools = ConverterTools.getInstance();
	@SuppressWarnings("rawtypes")
	public Object doConvertItemsToValueObject(List listeItems) {
		
		if (listeItems == null || listeItems.isEmpty()) {
			return null;
		}
		Map map= new HashMap();
		
		try {
			List listNatureAudience =(List)listeItems.get(0);	
			List listNatureAudienceVO = converterTools.convertToListObjectVO(listNatureAudience);
			
			map.put("listNatureAudienceVO", listNatureAudienceVO);		
		
			
		} catch (Exception e) {
			logger.error("problème technique",e);
		}
		
		
		return map;
		
		
	}

	public List doConvertValueObjectToItems(Object vo) throws ValidationUnitaireException, ValidationException {
		return null;
				
	}

    
    

   
}
