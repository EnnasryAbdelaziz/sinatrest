package eai.devass.gsr.appli.usecase.metier.reglement;

import java.util.ArrayList;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IValueObjectConverter;
import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.validation.IValidator;
import eai.devass.gsr.appli.modele.metier.reglement.QuittanceGsr;
import eai.devass.gsr.appli.valueobjects.metier.reglement.QuittanceGsrVO;
import eai.devass.gsr.appli.valueobjects.metier.reglement.QuittanceGsrVOConverter;

public class QuittanceGsrModifierUCConverter implements IValueObjectConverter {
	
	private QuittanceGsrVOConverter quittanceVOConverter = new QuittanceGsrVOConverter();
	
	public List<QuittanceGsr> convertValueObjectToItems(Object ovo) throws ValidationException {
		
		
		QuittanceGsrVO vo = (QuittanceGsrVO) ovo;
		QuittanceGsr item = (QuittanceGsr) quittanceVOConverter.voToItem(vo);
		List<QuittanceGsr> res = new ArrayList<QuittanceGsr>();
		res.add(item);
		return res;
	}
	
	public Object convertItemsToValueObject(List arg0) {
		
		return null;
	}

	public IValidator getValidator() {
		
		return null;
	}

	
}
