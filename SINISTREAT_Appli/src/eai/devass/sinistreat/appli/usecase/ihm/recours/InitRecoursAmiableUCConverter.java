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


@SuppressWarnings("unchecked")
public class InitRecoursAmiableUCConverter extends ValueObjectConverterAbst {

	ConverterTools converterTools = ConverterTools.getInstance();
	private Logger logger = Logger.getLogger("loggerSINAT");
	@SuppressWarnings("rawtypes")
	public Object doConvertItemsToValueObject(List listeItems) {
		
		if (listeItems == null || listeItems.isEmpty()) {
			return null;
		}
		Map map= new HashMap();
		
		try {
			List listBanques =(List)listeItems.get(0);
			List listBanquesVO = converterTools.convertToListObjectVO(listBanques);
			List listPronosticRC =(List)listeItems.get(1);
			List listPronosticRCVO = converterTools.convertToListObjectVO(listPronosticRC);
			List listEtatProposition =(List)listeItems.get(2);
			List listEtatPropositionVO = converterTools.convertToListObjectVO(listEtatProposition);
			List listEtatReponse =(List)listeItems.get(3);
			List listEtatReponseVO = converterTools.convertToListObjectVO(listEtatReponse);
			List listDecision =(List)listeItems.get(4);
			List listDecisionVO = converterTools.convertToListObjectVO(listDecision);
			map.put("listBanques", listBanquesVO);
			map.put("listPronosticRC", listPronosticRCVO);	
			map.put("listeEtatProposition", listEtatPropositionVO);	
			map.put("listeEtatReponse", listEtatReponseVO);	
			map.put("listeDecision", listDecisionVO);	
		} catch (Exception e) {
			logger.error("problème technique",e);
		}
		
		
		return map;
		
		
	}

	public List doConvertValueObjectToItems(Object vo) throws ValidationUnitaireException, ValidationException {
		return null;
				
	}

    
    

   
}
