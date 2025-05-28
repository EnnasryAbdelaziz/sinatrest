package eai.devass.gsr.appli.valueobjects.metier.reglement;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.validation.IValidator;
import eai.devass.gsr.appli.valueobjects.metier.mouvements.CommunMouvementVOConverter;

/**
 * Value Object Converter de Quittance
 * 
 * @author Nom Prenom (email)
 */
public class QuittanceGsrVOConverter extends CommunMouvementVOConverter {

	public IValidator getValidator() {
		return null;
	}

	protected void doValider(IValueObject vo) throws ValidationException {

	}
}
