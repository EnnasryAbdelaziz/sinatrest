package eai.devass.sinistreat.appli.usecase.metier.reglement;

import java.util.ArrayList;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.businessInterface.impl.ValueObjectConverterAbst;
import ma.co.omnidata.framework.services.validation.ValidationUnitaireException;

import org.apache.log4j.Logger;

import eai.devass.sinistreat.appli.modele.metier.reglement.DetailReglement;
import eai.devass.sinistreat.appli.modele.metier.reglement.Reglement;
import eai.devass.sinistreat.appli.utils.ConverterTools;
import eai.devass.sinistreat.appli.utils.Fonctions;
import eai.devass.sinistreat.appli.utils.exception.IMessageException;
import eai.devass.sinistreat.appli.valueobjects.metier.reglement.ReglementVO;

public class CreateRecuperationUCConverter extends ValueObjectConverterAbst
		implements IMessageException {

	protected Fonctions functions = new Fonctions();
	ConverterTools converterTools = ConverterTools.getInstance();
	private static final Logger LOGGER = Logger.getLogger("loggerSINAT");
	public Object doConvertItemsToValueObject(List listeItems) {

		if (listeItems == null || listeItems.isEmpty()) {
			return null;
		}

		Reglement rgl = null;
		ReglementVO rglvo = new ReglementVO();
		try {
			rgl = (Reglement) listeItems.get(0);

			rglvo = (ReglementVO) converterTools.convertToObjectVO(rgl);

		} catch (Exception e) {
		    LOGGER.error("problème technique",e);
		}

		return rglvo;
	}

	public List doConvertValueObjectToItems(Object vo)
			throws ValidationUnitaireException, ValidationException {

		List listOut = new ArrayList();
		ReglementVO rglvo = (ReglementVO) vo;
		try {
			Reglement rgl = (Reglement) converterTools.convertToObjectBO(rglvo);
				List listDtlRgl = (List) rgl.getListDetailReglement();
				for (int i = 0; i < listDtlRgl.size(); i++) {
					DetailReglement dtl = (DetailReglement) listDtlRgl.get(i);
					dtl.setId(null);
				}
			listOut.add(rgl);

		} catch (Exception e) {
			Logger.getLogger("loggerGSR").fatal("Erreur lors de la conversion !", e);
			throw new ValidationUnitaireException(e.getMessage());
		}

		return listOut;
	}

}
