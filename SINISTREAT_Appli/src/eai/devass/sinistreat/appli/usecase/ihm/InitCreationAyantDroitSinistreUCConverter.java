package eai.devass.sinistreat.appli.usecase.ihm;

/* @author joundi : 1 Nov. 10 */
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

@SuppressWarnings("unchecked")
public class InitCreationAyantDroitSinistreUCConverter extends ValueObjectConverterAbst {
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
			Sinistre sin = (Sinistre)listeItems.get(3);
			List listResultBO4 = (List)listeItems.get(4);
			
			List listDegreParenteVO = ConverterTools.getInstance().convertToListObjectVO(listResultBO0);		
			List listVilleVO = ConverterTools.getInstance().convertToListObjectVO(listResultBO1);
			List listAyantDroitVO = ConverterTools.getInstance().convertToListObjectVO(listResultBO2);
			List listNatureEcheanceVO = ConverterTools.getInstance().convertToListObjectVO(listResultBO4);
			SinistreVO sinvo = (SinistreVO)ConverterTools.getInstance().convertToObjectVOWithoutList(sin);
			
			map.put("listDegreParenteVO", listDegreParenteVO);	
			map.put("listVilleVO", listVilleVO);
			map.put("listAyantDroitVO", listAyantDroitVO);
			map.put("SinistreVO", sinvo);
			map.put("listNatureEcheanceVO", listNatureEcheanceVO);
			
		} catch (Exception e) {
			logger.error("problème technique",e);
		}
		
		
		return map;
		
		
	}

	public List doConvertValueObjectToItems(Object vo) throws ValidationUnitaireException, ValidationException {
		return null;
				
	}

    
    

   
}
