package eai.devass.sinistreat.appli.usecase.parametrage.ihm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import eai.devass.sinistreat.appli.utils.ConverterTools;
import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.businessInterface.impl.ValueObjectConverterAbst;
import ma.co.omnidata.framework.services.validation.ValidationUnitaireException;

public class InitRecherchePieceReglementUCConverter extends ValueObjectConverterAbst  {
	private Logger logger = Logger.getLogger("loggerSINAT");
	@Override
	public Object doConvertItemsToValueObject(List listeItems) {
		
		if (listeItems == null || listeItems.isEmpty()) {
			return null;
		}
		Map map= new HashMap();
		try {
			List listResultBO0 = (List)listeItems.get(0);

			

			List listPieceReglementVO = ConverterTools.getInstance().convertToListObjectVO(listResultBO0);


			map.put("listPieceReglementVO", listPieceReglementVO);	

			} catch (Exception e) {
			logger.error("problème technique",e);
		}	
		return map;
	}

	@Override
	public List doConvertValueObjectToItems(Object arg0)
			throws ValidationUnitaireException, ValidationException {
		
		return null;
	}

}