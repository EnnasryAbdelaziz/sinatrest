package eai.devass.sinistreat.appli.usecase.metier.sinistre;

import java.util.ArrayList;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.businessInterface.impl.ValueObjectConverterAbst;
import ma.co.omnidata.framework.services.validation.ValidationUnitaireException;

import org.apache.log4j.Logger;

import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.modele.metier.sinistre.AyantDroit;
import eai.devass.sinistreat.appli.modele.metier.sinistre.Sinistre;
import eai.devass.sinistreat.appli.utils.ConverterTools;
import eai.devass.sinistreat.appli.utils.Fonctions;
import eai.devass.sinistreat.appli.utils.exception.IMessageException;
import eai.devass.sinistreat.appli.valueobjects.metier.sinistre.AyantDroitVO;
import eai.devass.sinistreat.appli.valueobjects.metier.sinistre.SinistreVO;

public class CreateMouvementUCConverter extends ValueObjectConverterAbst
		implements IMessageException {

	protected Fonctions functions = new Fonctions();
	ConverterTools converterTools = ConverterTools.getInstance();

	public Object doConvertItemsToValueObject(List listeItems) {

		if (listeItems == null || listeItems.isEmpty()) {
			return null;
		}

		SinistreVO sinvo = new SinistreVO();
		try {
			Sinistre sin = (Sinistre) listeItems.get(0);
			sinvo = (SinistreVO) converterTools
					.convertToObjectVOWithoutList(sin);

		} catch (Exception e) {
			Logger.getLogger("loggerGSR").fatal("Erreur lors de la conversion !", e);
		}

		return sinvo;
	}

	public List doConvertValueObjectToItems(Object vo)
			throws ValidationUnitaireException, ValidationException {

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
			ay.setId(0);
			listOut.add(ay);

		} catch (Exception e) {
			Logger.getLogger("loggerGSR").fatal("Erreur lors de la conversion !", e);
			throw new ValidationUnitaireException(e.getMessage());
		}

		return listOut;
	}

}
