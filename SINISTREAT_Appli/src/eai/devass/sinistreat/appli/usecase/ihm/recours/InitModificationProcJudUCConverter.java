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
public class InitModificationProcJudUCConverter extends
		ValueObjectConverterAbst {
	private Logger logger = Logger.getLogger("loggerSINAT");
	ConverterTools converterTools = ConverterTools.getInstance();

	@SuppressWarnings("rawtypes")
	public Object doConvertItemsToValueObject(List listeItems) {

		if (listeItems == null || listeItems.isEmpty()) {
			return null;
		}
		Map map = new HashMap();

		try {
			List listTypeJuridictionVO = converterTools
					.convertToListObjectVO((List) listeItems.get(0));
			List listNatureProcedureVO = converterTools
					.convertToListObjectVO((List) listeItems.get(1));
			List listeNatureDecisionVO = converterTools
					.convertToListObjectVO((List) listeItems.get(2));
			List listSuiteJugementVO = converterTools
					.convertToListObjectVO((List) listeItems.get(3));
			List listNatureDossierVO = converterTools
					.convertToListObjectVO((List) listeItems.get(4));
			List listPronosticRCVO = converterTools
					.convertToListObjectVO((List) listeItems.get(5));
			map.put("listTypeJuridictionVO", listTypeJuridictionVO);
			map.put("listNatureProcedureVO", listNatureProcedureVO);
			map.put("listNatureDecisionVO", listeNatureDecisionVO);
			map.put("listSuiteJugementVO", listSuiteJugementVO);
			map.put("listNatureDossierVO", listNatureDossierVO);
			map.put("listPronosticRC", listPronosticRCVO);

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
