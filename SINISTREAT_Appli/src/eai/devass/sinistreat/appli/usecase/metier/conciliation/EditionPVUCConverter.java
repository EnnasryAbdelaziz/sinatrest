package eai.devass.sinistreat.appli.usecase.metier.conciliation;

import java.util.ArrayList;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.businessInterface.impl.ValueObjectConverterAbst;
import ma.co.omnidata.framework.services.validation.ValidationUnitaireException;

import org.apache.log4j.Logger;

import eai.devass.sinistreat.appli.modele.metier.conciliation.SinEditionPV;
import eai.devass.sinistreat.appli.modele.metier.reglement.LettreReglement;
import eai.devass.sinistreat.appli.utils.ConverterTools;
import eai.devass.sinistreat.appli.utils.exception.IMessageException;
import eai.devass.sinistreat.appli.valueobjects.metier.conciliation.SinEditionPVVO;
import eai.devass.sinistreat.appli.valueobjects.metier.reglement.LettreReglementVO;

@SuppressWarnings("all")
public class EditionPVUCConverter extends ValueObjectConverterAbst
implements IMessageException{
	
	ConverterTools converterTools = ConverterTools.getInstance();
	
	public Object doConvertItemsToValueObject(List arg0) {
	
		if (arg0 == null || arg0.isEmpty()) {
			return null;
		}

		SinEditionPVVO editvo = new SinEditionPVVO();
		try {
			SinEditionPV edit= (SinEditionPV) arg0.get(0);
			editvo = (SinEditionPVVO) converterTools
					.convertToObjectVOWithoutList(edit);

		} catch (Exception e) {
			Logger.getLogger("loggerSinat").error("problème technique",e);
		}

		return editvo;
	}


	public List doConvertValueObjectToItems(Object vo)
			throws ValidationUnitaireException, ValidationException {
	
		List listOut = new ArrayList();
		SinEditionPVVO ediVO = (SinEditionPVVO) vo;
	

		try {
			SinEditionPV edit = (SinEditionPV) converterTools.convertToObjectBO(ediVO);
			listOut.add(edit);
		} catch (Exception e) {
			Logger.getLogger("loggerSinat").fatal("Erreur lors de la conversion !", e);
			throw new ValidationUnitaireException(e.getMessage());
		}

		return listOut;
	}

}
