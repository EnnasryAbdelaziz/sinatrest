package eai.devass.sinistreat.appli.usecase.metier.sinistre;

import java.util.ArrayList;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.businessInterface.impl.ValueObjectConverterAbst;
import ma.co.omnidata.framework.services.validation.ValidationUnitaireException;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.modele.metier.sinistre.Sinistre;
import eai.devass.sinistreat.appli.utils.ConverterTools;
import eai.devass.sinistreat.appli.utils.Fonctions;
import eai.devass.sinistreat.appli.utils.exception.IMessageException;
import eai.devass.sinistreat.appli.valueobjects.metier.sinistre.SinistreVO;

public class GestionEtatResSinistreUCConverter extends ValueObjectConverterAbst
		implements IMessageException {

	protected Fonctions functions = new Fonctions();
	ConverterTools converterTools = ConverterTools.getInstance();
	private Logger logger = Logger.getLogger("loggerSINAT");
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
			logger.error("problème technique",e);
		}

		return sinvo;
	}

	public List doConvertValueObjectToItems(Object vo)
			throws ValidationUnitaireException, ValidationException {

		List listOut = new ArrayList();
		SinistreVO sinvo = (SinistreVO) vo;
		try {

			Sinistre sin = (Sinistre) converterTools.convertToObjectBO(sinvo);
			if (StringUtils.isEmpty(sin.getEtatCible())) {
				throw new FonctionnelleException(EXP_ETAT_CIBLE_OBLIGATOIRE);
			}
			if (StringUtils.isEmpty(sin.getMotifModification())) {
				throw new FonctionnelleException(EXP_MOTIF_OBLIGATOIRE);
			}
			listOut.add(sin);

		} catch (Exception e) {
			Logger.getLogger("loggerGSR").fatal("Erreur lors de la conversion !", e);
			throw new ValidationUnitaireException(e.getMessage());
		}

		return listOut;
	}

}
