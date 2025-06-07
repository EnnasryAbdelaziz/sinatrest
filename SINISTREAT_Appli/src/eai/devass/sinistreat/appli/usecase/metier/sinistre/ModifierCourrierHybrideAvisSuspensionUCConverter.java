package eai.devass.sinistreat.appli.usecase.metier.sinistre;

import java.util.ArrayList;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.impl.ValueObjectConverterAbst;
import ma.co.omnidata.framework.services.validation.ValidationUnitaireException;

import org.apache.log4j.Logger;

import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.modele.parametrage.CourrierHybrideAvisSuspension;
import eai.devass.sinistreat.appli.utils.ConverterTools;
import eai.devass.sinistreat.appli.utils.Fonctions;
import eai.devass.sinistreat.appli.utils.exception.IMessageException;
import eai.devass.sinistreat.appli.valueobjects.parametrage.CourrierHybrideAvisSuspensionVO;

public class ModifierCourrierHybrideAvisSuspensionUCConverter extends ValueObjectConverterAbst
		implements IMessageException {

	protected Fonctions functions = new Fonctions();
	ConverterTools converterTools = ConverterTools.getInstance();

	public Object doConvertItemsToValueObject(List listeItems) {

		if (listeItems == null || listeItems.isEmpty()) {
			return null;
		}

		CourrierHybrideAvisSuspensionVO ayvo = new CourrierHybrideAvisSuspensionVO();
		try {
			CourrierHybrideAvisSuspension ay = (CourrierHybrideAvisSuspension) listeItems.get(0);
			ayvo = (CourrierHybrideAvisSuspensionVO) converterTools
					.convertToObjectVOWithoutList(ay);

		} catch (Exception e) {
			Logger.getLogger("loggerGSR").fatal("Erreur lors de la conversion !", e);
		}

		return ayvo;
	}

	public List doConvertValueObjectToItems(Object vo)
			throws ValidationUnitaireException {

		List listOut = new ArrayList();
		CourrierHybrideAvisSuspensionVO ayvo = (CourrierHybrideAvisSuspensionVO) vo;
		try {

			CourrierHybrideAvisSuspension ay = (CourrierHybrideAvisSuspension) converterTools.convertToObjectBO(ayvo);
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
