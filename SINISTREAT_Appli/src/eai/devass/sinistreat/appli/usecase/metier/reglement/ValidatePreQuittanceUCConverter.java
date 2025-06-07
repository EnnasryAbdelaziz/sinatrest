package eai.devass.sinistreat.appli.usecase.metier.reglement;

import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.businessInterface.impl.ValueObjectConverterAbst;
import ma.co.omnidata.framework.services.validation.ValidationUnitaireException;

import org.apache.log4j.Logger;

import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.modele.metier.reglement.Positionnement;
import eai.devass.sinistreat.appli.modele.metier.reglement.Reglement;
import eai.devass.sinistreat.appli.utils.ConverterTools;
import eai.devass.sinistreat.appli.utils.Fonctions;
import eai.devass.sinistreat.appli.utils.exception.IMessageException;
import eai.devass.sinistreat.appli.valueobjects.metier.ListVO;
import eai.devass.sinistreat.appli.valueobjects.metier.reglement.OrdonnancementVO;
import eai.devass.sinistreat.appli.valueobjects.metier.reglement.ReglementVO;

public class ValidatePreQuittanceUCConverter extends ValueObjectConverterAbst
        implements IMessageException {

	protected Fonctions functions = new Fonctions();
	ConverterTools converterTools = ConverterTools.getInstance();
	private Logger logger = Logger.getLogger("loggerSINAT");
	
	public Object doConvertItemsToValueObject(List listeItems) {

		if (listeItems == null || listeItems.isEmpty()) {
			return null;
		}
		List<OrdonnancementVO> lisOrdvo = null;
		try {

		} catch (Exception e) {
			logger.error("problème technique",e);
		}

		return lisOrdvo;
	}

	public List doConvertValueObjectToItems(Object vo)
			throws ValidationUnitaireException, ValidationException {

		ListVO list = (ListVO) vo;
		List<ReglementVO> lisrglvo = list.getListReglement();
		List<Reglement> lisrgl = null;
		try {

			if (lisrglvo == null) {
				throw new FonctionnelleException("EXP_CHAMPS_SERVICE_NULL");
			}

			lisrgl = (List) converterTools.convertToListObjectBOBis(lisrglvo);

		} catch (Exception e) {
            Logger.getLogger("loggerGSR").fatal(
                    "Erreur lors de la conversion !", e);
			throw new ValidationUnitaireException(e.getMessage());
		}

		return lisrgl;
	}

}
