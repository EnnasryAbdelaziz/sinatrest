package eai.devass.sinistreat.appli.usecase.ihm.contentieux;

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
public class InitCreationAudienceUCConverter extends ValueObjectConverterAbst {
	private Logger logger = Logger.getLogger("loggerSINAT");
	ConverterTools converterTools = ConverterTools.getInstance();
	@SuppressWarnings("rawtypes")
	public Object doConvertItemsToValueObject(List listeItems) {
		
		if (listeItems == null || listeItems.isEmpty()) {
			return null;
		}
		Map map= new HashMap();
		
		try {
			List listNatureAudience = (List) listeItems.get(0);
			List listNatureAudienceVO = converterTools.convertToListObjectVO(listNatureAudience);
			List listeNatureDecision = (List) listeItems.get(1);
			List listeNatureDecisionVO = converterTools.convertToListObjectVO(listeNatureDecision);
			List listSuiteJugement = (List) listeItems.get(2);
			List listSuiteJugementVO = converterTools.convertToListObjectVO(listSuiteJugement);
			List listeTiersSaisi = (List) listeItems.get(3);
			List listeTiersSaisiVO = converterTools.convertToListObjectVO(listeTiersSaisi);
			List listeBanque = (List) listeItems.get(4);
			List listeBanqueVO = converterTools.convertToListObjectVO(listeBanque);
			
			map.put("listNatureAudienceVO", listNatureAudienceVO);
			map.put("listNatureDecisionVO", listeNatureDecisionVO);
			map.put("listSuiteJugementVO", listSuiteJugementVO);
			map.put("listeTiersSaisiVO", listeTiersSaisiVO);
			map.put("listeBanqueVO", listeBanqueVO);
		
			
		} catch (Exception e) {
			logger.error("problème technique",e);
		}
		
		return map;
		
		
	}

	public List doConvertValueObjectToItems(Object vo) throws ValidationUnitaireException, ValidationException {
		return null;
				
	}

    
    

   
}
