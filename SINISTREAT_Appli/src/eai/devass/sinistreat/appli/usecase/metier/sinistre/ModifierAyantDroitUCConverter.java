package eai.devass.sinistreat.appli.usecase.metier.sinistre;

import java.util.ArrayList;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.impl.ValueObjectConverterAbst;
import ma.co.omnidata.framework.services.validation.ValidationUnitaireException;

import org.apache.log4j.Logger;

import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.modele.metier.sinistre.AyantDroit;
import eai.devass.sinistreat.appli.utils.ConverterTools;
import eai.devass.sinistreat.appli.utils.Fonctions;
import eai.devass.sinistreat.appli.utils.exception.IMessageException;
import eai.devass.sinistreat.appli.valueobjects.metier.sinistre.AyantDroitVO;

public class ModifierAyantDroitUCConverter extends ValueObjectConverterAbst
		implements IMessageException {

	protected Fonctions functions = new Fonctions();
	ConverterTools converterTools = ConverterTools.getInstance();

	public Object doConvertItemsToValueObject(List listeItems) {

		if (listeItems == null || listeItems.isEmpty()) {
			return null;
		}

		AyantDroitVO ayvo = new AyantDroitVO();
		try {
			AyantDroit ay = (AyantDroit) listeItems.get(0);
			ayvo = (AyantDroitVO) converterTools
					.convertToObjectVOWithoutList(ay);

		} catch (Exception e) {
			Logger.getLogger("loggerGSR").fatal("Erreur lors de la conversion !", e);
		}

		return ayvo;
	}

	public List doConvertValueObjectToItems(Object vo)
			throws ValidationUnitaireException {

		List listOut = new ArrayList();
		AyantDroitVO ayvo = (AyantDroitVO) vo;
		try {

			AyantDroit ay = (AyantDroit) converterTools.convertToObjectBO(ayvo);
			if (ay.getDeces() == true && ay.getDateDeces() == null) {
				throw new FonctionnelleException(EXP_DATE_DECES_OBLIGATOIRE);
			}
			if (ay.getDeces() == false) {
				ay.setDateDeces(null);
			}
			listOut.add(ay);
		} catch (Exception e) {
			Logger.getLogger("loggerGSR").fatal("Erreur lors de la conversion !", e);
			throw new ValidationUnitaireException(e.getMessage());
		}

		return listOut;
	}

}
