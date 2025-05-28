package eai.devass.sinistreat.appli.usecase.parametrage.ihm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.businessInterface.impl.ValueObjectConverterAbst;
import ma.co.omnidata.framework.services.validation.ValidationUnitaireException;
import eai.devass.sinistreat.appli.utils.ConverterTools;
import eai.devass.sinistreat.appli.utils.Fonctions;

public class InitRechercherSocietesAssuranceUCConverter extends
		ValueObjectConverterAbst {

	protected Fonctions functions = new Fonctions();
	ConverterTools converterTools = ConverterTools.getInstance();
	private Logger logger = Logger.getLogger("loggerSINAT");
	@SuppressWarnings("rawtypes")
	public Object doConvertItemsToValueObject(List listeItems) {

		if (listeItems == null || listeItems.isEmpty()) {
			return null;
		}
		Map map = new HashMap();

		try {
			List listeAssurances = (List) listeItems.get(0);
			List AssurancesVO = converterTools
					.convertToListObjectVO(listeAssurances);
			map.put("listAssurancesVO", AssurancesVO);
		} catch (Exception e) {
			logger.error("problème technique",e);
		}
		return map;
	}

	public List doConvertValueObjectToItems(Object vo)
			throws ValidationUnitaireException, ValidationException {
		return null;
	}
}