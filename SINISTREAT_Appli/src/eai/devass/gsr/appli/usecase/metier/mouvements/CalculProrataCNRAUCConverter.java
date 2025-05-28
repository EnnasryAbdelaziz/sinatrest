/**
 * 
 */
package eai.devass.gsr.appli.usecase.metier.mouvements;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.validation.IValidator;
import eai.devass.gsr.appli.valueobjects.metier.mouvements.CommunMouvementVOConverter;

/**
 * @author elfaismo
 *
 */
public class CalculProrataCNRAUCConverter extends CommunMouvementVOConverter {

    /*
     * (non-Javadoc)
     * 
     * @see eai.devass.gsr.appli.valueobjects.metier.mouvements.
     * CommunMouvementVOConverter
     * #doValider(ma.co.omnidata.framework.services.businessInterface
     * .IValueObject)
	 */
	@Override
	protected void doValider(IValueObject vo) throws ValidationException {

	}

	public IValidator getValidator() {
		return null;
	}

}
