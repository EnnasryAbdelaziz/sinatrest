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
public class InitModificationRecoursSortantUCConverter extends ValueObjectConverterAbst {
	ConverterTools converterTools = ConverterTools.getInstance();
	private Logger logger = Logger.getLogger("loggerSINAT");
	@SuppressWarnings("rawtypes")
	public Object doConvertItemsToValueObject(List listeItems) {
		
		if (listeItems == null || listeItems.isEmpty()) {
			return null;
		}
		Map map= new HashMap();
		
		try {
			List listEtatRecours = (List)listeItems.get(0);
			List listEtatRecoursVO = converterTools.convertToListObjectVO(listEtatRecours);
			List listCompagnies = (List)listeItems.get(1);
			List listAssurancesVO = converterTools.convertToListObjectVO(listCompagnies);
			List listTypeAccident =  (List)listeItems.get(2);
			List listTypesAccidentVO = converterTools.convertToListObjectVO(listTypeAccident);
			List listPronosticRC =(List)listeItems.get(3);
			List listPronosticRCVO = converterTools.convertToListObjectVO(listPronosticRC);
			List listBanques =(List)listeItems.get(4);
			List listBanquesVO = converterTools.convertToListObjectVO(listBanques);
		
			
			map.put("listEtatRecours", listEtatRecoursVO);
			map.put("listCompagnies", listAssurancesVO);	
			map.put("listTypeAccident", listTypesAccidentVO);	
			map.put("listPronosticRC", listPronosticRCVO);
			map.put("listBanques", listBanquesVO);
		
			
		} catch (Exception e) {
			logger.error("problème technique",e);
		}
		
		
		return map;
		
		
	}

	public List doConvertValueObjectToItems(Object vo) throws ValidationUnitaireException, ValidationException {
		return null;
				
	}

    
    

   
}
