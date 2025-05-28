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
public class InitCreationRecoursAmiableUCConverter extends
		ValueObjectConverterAbst {
	ConverterTools converterTools = ConverterTools.getInstance();
	private Logger logger = Logger.getLogger("loggerSINAT");
	@SuppressWarnings("rawtypes")
	public Object doConvertItemsToValueObject(List listeItems) {

		if (listeItems == null || listeItems.isEmpty()) {
			return null;
		}
		Map map = new HashMap();

		try {
			List listBanques = (List) listeItems.get(0);
			List listBanquesVO = converterTools
					.convertToListObjectVO(listBanques);

			map.put("listBanques", listBanquesVO);
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
