package eai.devass.sinistreat.appli.usecase.ihm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.businessInterface.impl.ValueObjectConverterAbst;
import ma.co.omnidata.framework.services.validation.ValidationUnitaireException;

import org.apache.log4j.Logger;

import eai.devass.sinistreat.appli.modele.metier.reglement.Reglement;
import eai.devass.sinistreat.appli.utils.ConverterTools;
import eai.devass.sinistreat.appli.utils.Fonctions;
import eai.devass.sinistreat.appli.valueobjects.metier.reglement.ReglementVO;

public class InitValidationReglementSuperieurUCConverter extends
		ValueObjectConverterAbst {

	protected Fonctions functions = new Fonctions();
	private ConverterTools converterTools = ConverterTools.getInstance();
	private Logger logger = Logger.getLogger("loggerSINAT");
	public Object doConvertItemsToValueObject(List listeItems) {

		if (listeItems == null || listeItems.isEmpty()) {
			return null;
		}

		Map mapOut = new HashMap();

		try {
			List<Reglement> listReglement = (List<Reglement>) listeItems.get(0);
			List<ReglementVO> listReglementVO = (List<ReglementVO>) ConverterTools
					.getInstance().convertToListObjectVOWithoutList(
							listReglement);
			mapOut.put("listReglementVO", listReglementVO);

			// Code SAS sup
			String codeSasSup = (String) listeItems.get(1);
			mapOut.put("codeSasSup", codeSasSup);

		} catch (Exception e) {
			logger.error("probl�me technique",e);
		}
		return mapOut;

	}

	public List doConvertValueObjectToItems(Object vo)
			throws ValidationUnitaireException, ValidationException {
		List listOut = new ArrayList();
		ReglementVO reglementVO = (ReglementVO) vo;
		try {
			Reglement reglement = (Reglement) converterTools
					.convertToObjectBO(reglementVO);
			listOut.add(reglement);

		} catch (Exception e) {
			Logger.getLogger("loggerGSR").fatal(
					"Erreur lors de la conversion !", e);
		}
		return listOut;
	}
}
