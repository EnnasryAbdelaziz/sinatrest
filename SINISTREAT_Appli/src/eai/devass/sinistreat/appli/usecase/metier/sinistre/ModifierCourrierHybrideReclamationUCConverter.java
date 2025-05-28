package eai.devass.sinistreat.appli.usecase.metier.sinistre;

import java.util.ArrayList;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.impl.ValueObjectConverterAbst;
import ma.co.omnidata.framework.services.validation.ValidationUnitaireException;

import org.apache.log4j.Logger;

import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.modele.parametrage.CourrierHybrideReclamation;
import eai.devass.sinistreat.appli.utils.ConverterTools;
import eai.devass.sinistreat.appli.utils.Fonctions;
import eai.devass.sinistreat.appli.utils.exception.IMessageException;
import eai.devass.sinistreat.appli.valueobjects.parametrage.CourrierHybrideReclamationVO;

public class ModifierCourrierHybrideReclamationUCConverter extends ValueObjectConverterAbst
		implements IMessageException {

	protected Fonctions functions = new Fonctions();
	ConverterTools converterTools = ConverterTools.getInstance();

	public Object doConvertItemsToValueObject(List listeItems) {

		if (listeItems == null || listeItems.isEmpty()) {
			return null;
		}

		CourrierHybrideReclamationVO ayvo = new CourrierHybrideReclamationVO();
		try {
			CourrierHybrideReclamation ay = (CourrierHybrideReclamation) listeItems.get(0);
			ayvo = (CourrierHybrideReclamationVO) converterTools
					.convertToObjectVOWithoutList(ay);

		} catch (Exception e) {
			Logger.getLogger("loggerGSR").fatal("Erreur lors de la conversion !", e);
		}

		return ayvo;
	}

	public List doConvertValueObjectToItems(Object vo)
			throws ValidationUnitaireException {

		List listOut = new ArrayList();
		CourrierHybrideReclamationVO ayvo = (CourrierHybrideReclamationVO) vo;
		try {

			CourrierHybrideReclamation ay = (CourrierHybrideReclamation) converterTools.convertToObjectBO(ayvo);
			if (ay.getIdLRH() == null) {
				throw new FonctionnelleException(EXP_DATE_DECES_OBLIGATOIRE);
			}
			listOut.add(ay);
		} catch (Exception e) {
			Logger.getLogger("loggerGSR").fatal("Erreur lors de la conversion !", e);
			throw new ValidationUnitaireException(e.getMessage());
		}

		return listOut;
	}

}
