package eai.devass.sinistreat.appli.usecase.metier.reglement;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import eai.devass.sinistreat.appli.modele.metier.reglement.LettreReglement;
import eai.devass.sinistreat.appli.modele.metier.reglement.Reglement;
import eai.devass.sinistreat.appli.utils.ConverterTools;
import eai.devass.sinistreat.appli.utils.Fonctions;
import eai.devass.sinistreat.appli.utils.exception.IMessageException;
import eai.devass.sinistreat.appli.valueobjects.metier.ListVO;
import eai.devass.sinistreat.appli.valueobjects.metier.reglement.LettreReglementVO;
import eai.devass.sinistreat.appli.valueobjects.metier.reglement.ReglementVO;
import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.businessInterface.impl.ValueObjectConverterAbst;
import ma.co.omnidata.framework.services.validation.ValidationUnitaireException;

public class CreateLettreReglementUCConverter extends ValueObjectConverterAbst
		implements IMessageException {

	private Logger logger = Logger.getLogger("loggerSINAT");
	public CreateLettreReglementUCConverter() {
		functions = new Fonctions();
		converterTools = ConverterTools.getInstance();
	}

	public Object doConvertItemsToValueObject(List listeItems) {
		
		if (listeItems == null || listeItems.isEmpty()) {
			return null;
		}

		LettreReglementVO lettreRglvo = new LettreReglementVO();
		try {
			LettreReglement lettreRgl= (LettreReglement) listeItems.get(0);
			lettreRglvo = (LettreReglementVO) converterTools
					.convertToObjectVOWithoutList(lettreRgl);

		} catch (Exception e) {
			logger.error("problème technique",e);
		}

		return lettreRglvo;
	}


	public List doConvertValueObjectToItems(Object vo)
			throws ValidationUnitaireException, ValidationException {
		try {
			List lisrgl = new ArrayList();
			if (vo instanceof ListVO) {
				ListVO list = (ListVO) vo;
				List lisrglvo = list.getListReglement();
				lisrgl = converterTools.convertToListObjectBOBis(lisrglvo);
			} else {
				Reglement rgl = (Reglement) converterTools
						.convertToObjectBO((ReglementVO) vo);
				lisrgl.add(rgl);
			}
			return lisrgl;
		} catch (Exception e) {
			throw new ValidationUnitaireException(e.getMessage());
		}
	}

	protected Fonctions functions;
	ConverterTools converterTools;
}
