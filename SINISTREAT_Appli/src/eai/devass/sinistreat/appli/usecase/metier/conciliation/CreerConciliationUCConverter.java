package eai.devass.sinistreat.appli.usecase.metier.conciliation;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.businessInterface.impl.ValueObjectConverterAbst;
import ma.co.omnidata.framework.services.validation.ValidationUnitaireException;
import eai.devass.sinistreat.appli.modele.metier.conciliation.Conciliation;
import eai.devass.sinistreat.appli.utils.ConverterTools;
import eai.devass.sinistreat.appli.utils.exception.IMessageException;
import eai.devass.sinistreat.appli.valueobjects.metier.conciliation.ConciliationVO;
import eai.devass.sinistreat.appli.valueobjects.metier.sinistre.SinistreVO;

@SuppressWarnings("all")
public class CreerConciliationUCConverter extends ValueObjectConverterAbst
implements IMessageException{
	
	ConverterTools converterTools = ConverterTools.getInstance();
	
	public Object doConvertItemsToValueObject(List arg0) {
	
		return null;
	}


	public List doConvertValueObjectToItems(Object vo)
			throws ValidationUnitaireException, ValidationException {
	
		List listOut = new ArrayList();
		ConciliationVO conVO = (ConciliationVO) vo;
	

		try {
			Conciliation conciliation = (Conciliation) converterTools.convertToObjectBO(conVO);
			listOut.add(conciliation);
		} catch (Exception e) {
			Logger.getLogger("loggerSinat").fatal("Erreur lors de la conversion !", e);
			throw new ValidationUnitaireException(e.getMessage());
		}

		return listOut;
	}

}
