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
public class InitCreationRecoursEntrantUCConverter extends
		ValueObjectConverterAbst {
	private Logger logger = Logger.getLogger("loggerSINAT");
	@SuppressWarnings("rawtypes")
	public Object doConvertItemsToValueObject(List listeItems) {

		if (listeItems == null || listeItems.isEmpty()) {
			return null;
		}
		Map map = new HashMap();

		try {
			List listTypeJuridiction = (List) listeItems.get(0);
			List listTypeJuridictionVO = ConverterTools.getInstance()
					.convertToListObjectVO(listTypeJuridiction);
			List listNatureDossier = (List) listeItems.get(1);
			List listNatureDossierVO = ConverterTools.getInstance()
					.convertToListObjectVO(listNatureDossier);
			List listNatureProcedure = (List) listeItems.get(2);
			List listNatureProcedureVO = ConverterTools.getInstance()
					.convertToListObjectVO(listNatureProcedure);
			List listGestionnaire = (List) listeItems.get(3);
			List listGestionnaireVO = ConverterTools.getInstance()
					.convertToListObjectVO(listGestionnaire);

			map.put("listTypeJuridictionVO", listTypeJuridictionVO);
			map.put("listNatureDossierVO", listNatureDossierVO);
			map.put("listNatureProcedureVO", listNatureProcedureVO);
			map.put("listGestionnaireVO", listGestionnaireVO);
			

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
