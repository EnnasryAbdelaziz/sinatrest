package eai.devass.sinistreat.appli.usecase.metier.conciliation;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.businessInterface.impl.ValueObjectConverterAbst;
import ma.co.omnidata.framework.services.validation.ValidationUnitaireException;
import eai.devass.sinistreat.appli.modele.metier.conciliation.Conciliation;
import eai.devass.sinistreat.appli.modele.metier.conciliation.Offre;
import eai.devass.sinistreat.appli.utils.ConverterTools;
import eai.devass.sinistreat.appli.utils.exception.IMessageException;
import eai.devass.sinistreat.appli.valueobjects.metier.conciliation.ConciliationVO;
import eai.devass.sinistreat.appli.valueobjects.metier.conciliation.OffreVO;

@SuppressWarnings("all")
public class CalculerOffreUCConverter extends ValueObjectConverterAbst
implements IMessageException{
	ConverterTools converterTools = ConverterTools.getInstance();
	@Override
	public Object doConvertItemsToValueObject(List listeItems) {
		OffreVO offreVO = (OffreVO) listeItems.get(0);
		return offreVO;
	}

	@Override
	public List doConvertValueObjectToItems(Object vo)
			throws ValidationUnitaireException, ValidationException {
		List listOut = new ArrayList();
		OffreVO offreVO = (OffreVO) vo;
        listOut.add(offreVO);
		return listOut;

	}

}
