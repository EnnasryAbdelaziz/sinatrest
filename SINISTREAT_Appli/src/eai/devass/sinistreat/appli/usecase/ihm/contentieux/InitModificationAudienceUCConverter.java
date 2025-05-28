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
public class InitModificationAudienceUCConverter extends ValueObjectConverterAbst {
	ConverterTools converterTools = ConverterTools.getInstance();
	private Logger logger = Logger.getLogger("loggerSINAT");
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
			List listSortJugement = (List) listeItems.get(3);
			List listSortJugementVO = converterTools.convertToListObjectVO(listSortJugement);
			List listTiersSaisi = (List) listeItems.get(4);
			List listTiersSaisiVO = converterTools.convertToListObjectVO(listTiersSaisi);
			List listBanque = (List) listeItems.get(5);
			List listBanqueVO = converterTools.convertToListObjectVO(listBanque);
			List listTypeRente = (List) listeItems.get(6);
			List listTypeRenteVO = converterTools.convertToListObjectVO(listTypeRente);
			List listCieCondamnee = (List) listeItems.get(7);
			List listCieCondamneeVO = converterTools.convertToListObjectVO(listCieCondamnee);
			List listGestionnaireTraitement = (List) listeItems.get(8);
			List listGestionnaireTraitementVO = converterTools.convertToListObjectVO(listGestionnaireTraitement);
			map.put("listNatureAudienceVO", listNatureAudienceVO);
			map.put("listNatureDecisionVO", listeNatureDecisionVO);
			map.put("listSuiteJugementVO", listSuiteJugementVO);
			map.put("listSortJugementVO", listSortJugementVO);
			map.put("listTiersSaisiVO", listTiersSaisiVO);
			map.put("listBanqueVO", listBanqueVO);
			map.put("listTypeRenteVO", listTypeRenteVO);
			map.put("listCieCondamneeVO", listCieCondamneeVO);
			map.put("listGestionnaireTraitementVO", listGestionnaireTraitementVO);
		} catch (Exception e) {
			logger.error("problème technique",e);
		}
		
		
		return map;
		
		
	}

	public List doConvertValueObjectToItems(Object vo) throws ValidationUnitaireException, ValidationException {
		return null;
				
	}

    
    

   
}
