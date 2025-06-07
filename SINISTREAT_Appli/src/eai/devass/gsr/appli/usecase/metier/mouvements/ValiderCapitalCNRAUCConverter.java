package eai.devass.gsr.appli.usecase.metier.mouvements;

import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.businessInterface.impl.ValueObjectConverterAbst;
import ma.co.omnidata.framework.services.validation.ValidationUnitaireException;
import eai.devass.commun.appli.util.CommonUtils;


public class ValiderCapitalCNRAUCConverter extends ValueObjectConverterAbst {
	
	
	public Object doConvertItemsToValueObject(List listeItems) {

		if (CommonUtils.getInstance().isEmpty(listeItems)) {
			return null;
		}

		return listeItems.get(0);

	}

	@Override
	public List doConvertValueObjectToItems(Object arg0)
			throws ValidationUnitaireException, ValidationException {
		
		return null;
	}

}