package eai.devass.sinistreat.appli.usecase.ihm;

/* @author joundi : 1 Nov. 10 */
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.businessInterface.impl.ValueObjectConverterAbst;
import ma.co.omnidata.framework.services.validation.ValidationUnitaireException;
import eai.devass.sinistreat.appli.utils.ConverterTools;


@SuppressWarnings("unchecked")
public class InitConsultationSinistreUCConverter extends ValueObjectConverterAbst {
	private Logger logger = Logger.getLogger("loggerSINAT");
	public Object doConvertItemsToValueObject(List listeItems) {
		
		if (listeItems == null || listeItems.isEmpty()) {
			return null;
		}
		Map map= new HashMap();
		try {
			List listResultBO0 = (List)listeItems.get(0);
			List listResultBO1 = (List)listeItems.get(1);
			List listSituationVO = ConverterTools.getInstance().convertToListObjectVO(listResultBO0);
			List listNationaliteVO = ConverterTools.getInstance().convertToListObjectVO(listResultBO1);
			
			map.put("listSituationVO", listSituationVO);		
			map.put("listNationaliteVO", listNationaliteVO);
			
		} catch (Exception e) {
			logger.error("problème technique",e);
		}
		
		
		return map;
		
		
	}

	public List doConvertValueObjectToItems(Object vo) throws ValidationUnitaireException, ValidationException {
		return null;
				
	}

    
    

   
}
