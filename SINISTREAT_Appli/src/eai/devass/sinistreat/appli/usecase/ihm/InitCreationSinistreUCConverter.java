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
public class InitCreationSinistreUCConverter extends ValueObjectConverterAbst {

	private Logger logger = Logger.getLogger("loggerSINAT");
	
	public Object doConvertItemsToValueObject(List listeItems) {
		
		if (listeItems == null || listeItems.isEmpty()) {
			return null;
		}
		Map map= new HashMap();
		try {
			List listResultBO0 = (List)listeItems.get(0);
			List listResultBO1 = (List)listeItems.get(1);
			List listResultBO2 = (List)listeItems.get(2);
			List listResultBO3 = (List)listeItems.get(3);
			List listResultBO4 = (List)listeItems.get(4);
			List listResultBO5 = (List)listeItems.get(5);
			List listResultBO6 = (List)listeItems.get(6);
			List listResultBO7 = (List)listeItems.get(7);			
			List listResultBO8 = (List)listeItems.get(8);
			List listResultBO9 = (List)listeItems.get(9);
			List listResultBO10 = (List)listeItems.get(10);
			List listResultBO11 = (List)listeItems.get(11);
			List listResultBO12 = (List)listeItems.get(12);
			
			List listTypeAccidentVO = ConverterTools.getInstance().convertToListObjectVO(listResultBO0);		
			List listGarantieVO = ConverterTools.getInstance().convertToListObjectVO(listResultBO1);
			List listZoneVO = ConverterTools.getInstance().convertToListObjectVO(listResultBO2);
			List listCauseVO = ConverterTools.getInstance().convertToListObjectVO(listResultBO3);
			List listVilleVO = ConverterTools.getInstance().convertToListObjectVO(listResultBO4);
			List listTypeMaladieVO = ConverterTools.getInstance().convertToListObjectVO(listResultBO5);
			List listPoliceUnivVO = ConverterTools.getInstance().convertToListObjectVO(listResultBO6);
			List listSituationVO = ConverterTools.getInstance().convertToListObjectVO(listResultBO7);
			List listNationaliteVO = ConverterTools.getInstance().convertToListObjectVO(listResultBO8);
			List listPaysVO = ConverterTools.getInstance().convertToListObjectVO(listResultBO9);
			List listTypeSuiviVO = ConverterTools.getInstance().convertToListObjectVO(listResultBO10);
			List listTypeDeclarationVO = ConverterTools.getInstance().convertToListObjectVO(listResultBO11);
			List listSourceDeclarationVO = ConverterTools.getInstance().convertToListObjectVO(listResultBO12);
			
			map.put("listTypeAccidentVO", listTypeAccidentVO);
			map.put("listGarantieVO", listGarantieVO);	
			map.put("listZoneVO", listZoneVO);	
			map.put("listCauseVO", listCauseVO);	
			map.put("listVilleVO", listVilleVO);	
			map.put("listTypeMaladieVO", listTypeMaladieVO);
			map.put("listPoliceUnivVO", listPoliceUnivVO);	
			map.put("listSituationVO", listSituationVO);		
			map.put("listNationaliteVO", listNationaliteVO);
			map.put("listPaysVO", listPaysVO);
			map.put("listTypeSuiviVO", listTypeSuiviVO);
			map.put("listTypeDeclarationVO", listTypeDeclarationVO);
			map.put("listSourceDeclarationVO", listSourceDeclarationVO);
			
		} catch (Exception e) {
			logger.error("problème technique",e);
		}
		
		
		return map;
		
		
	}

	public List doConvertValueObjectToItems(Object vo) throws ValidationUnitaireException, ValidationException {
		return null;
				
	}

    
    

   
}
