package eai.devass.gsr.appli.valueobjects.metier.mouvements;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.validation.IValidator;

/**
 * Value Object Converter de MvtAnnulation
 * 
 * @author Nom Prenom (email)
 */
public class MvtAnnulationVOConverter extends CommunMouvementVOConverter {

	public IValidator getValidator() {
		return null;
	}



	@Override
	protected void doValider(IValueObject vo) throws ValidationException {
		// TODO Auto-generated method stub
		
	}
}
