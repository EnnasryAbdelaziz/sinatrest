
package eai.devass.gsr.appli.usecase.metier.mouvements;

import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.businessInterface.impl.ValueObjectConverterAbst;
import ma.co.omnidata.framework.services.validation.ValidationUnitaireException;
import eai.devass.commun.appli.util.CommonUtils;
import eai.devass.gsr.appli.valueobjects.metier.mouvements.MvtConsignCNRAVO;




@SuppressWarnings("all")
public class CalculCapitalCNRAUCConverter extends ValueObjectConverterAbst {
	
	

	public Object doConvertItemsToValueObject(List listeItems) {

		if (CommonUtils.getInstance().isEmpty(listeItems)) {
			return null;
		}

		MvtConsignCNRAVO consignCNRAVO = (MvtConsignCNRAVO) listeItems.get(0);
		return consignCNRAVO;

	}

	@Override
	public List doConvertValueObjectToItems(Object arg0)
			throws ValidationUnitaireException, ValidationException {
		
		return null;
	}
	
		
		
}
