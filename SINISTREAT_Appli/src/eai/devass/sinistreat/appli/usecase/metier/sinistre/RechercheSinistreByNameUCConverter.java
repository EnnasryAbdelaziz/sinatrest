package eai.devass.sinistreat.appli.usecase.metier.sinistre;

import java.util.ArrayList;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.businessInterface.impl.ValueObjectConverterAbst;
import ma.co.omnidata.framework.services.validation.ValidationUnitaireException;

import org.apache.log4j.Logger;

import eai.devass.sinistreat.appli.modele.metier.sinistre.Sinistre;
import eai.devass.sinistreat.appli.utils.ConverterTools;
import eai.devass.sinistreat.appli.utils.Fonctions;
import eai.devass.sinistreat.appli.utils.exception.IMessageException;
import eai.devass.sinistreat.appli.valueobjects.metier.sinistre.SinistreVO;

public class RechercheSinistreByNameUCConverter extends
		ValueObjectConverterAbst implements IMessageException {

	protected Fonctions functions = new Fonctions();
	ConverterTools converterTools = ConverterTools.getInstance();
	private Logger logger = Logger.getLogger("loggerSINAT");
	public Object doConvertItemsToValueObject(List listeItems) {

		if (listeItems == null || listeItems.isEmpty()) {
			return null;
		}
		SinistreVO sinvo = null;
		try {
			List<Sinistre> listSin = (List<Sinistre>) listeItems.get(0);
			List<SinistreVO> listSinvo = (List) converterTools
					.convertToListObjectVOWithoutList(listSin);
			if (listSinvo != null && !listSinvo.isEmpty()) {
				sinvo = (SinistreVO) listSinvo.get(0);
			}
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

			listOut.add(sin);
		} catch (Exception e) {
			Logger.getLogger("loggerGSR").fatal("Erreur lors de la conversion !", e);
			throw new ValidationUnitaireException(e.getMessage());
		}

		return listOut;
	}

}
