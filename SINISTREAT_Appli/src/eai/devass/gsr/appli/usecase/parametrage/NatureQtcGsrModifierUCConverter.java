package eai.devass.gsr.appli.usecase.parametrage;

import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.core.ServicesFactory;
import ma.co.omnidata.framework.services.log.ILog;
import ma.co.omnidata.framework.services.validation.IValidationUnitaire;
import ma.co.omnidata.framework.services.validation.ValidationUnitaireException;
import eai.devass.gsr.appli.valueobjects.parametrage.NatureQtcGsrVO;
import eai.devass.gsr.appli.valueobjects.parametrage.NatureQtcGsrVOConverter;

/**
 * Converter de modification
 * 
 * @author Nom Prenom (email)
 */
public class NatureQtcGsrModifierUCConverter extends NatureQtcGsrVOConverter {
	/**
	 * Methode de validation de l 'object à modifier
	 * 
	 * @param vo
	 *            L 'objet à valider
	 * @throws ValidationException
	 *             Probleme lors de la validation unitaire d' une entité
	 */
	protected void doValider(NatureQtcGsrVO vo) throws ValidationException {
		IValidationUnitaire validationUnitaire = (IValidationUnitaire) ServicesFactory
				.getService(IValidationUnitaire.class);
		try {
			validationUnitaire.validateField("sinistreAT",
					"natureQuittance.code", vo.getCode());
			validationUnitaire.validateField("sinistreAT",
					"natureQuittance.libelle", vo.getLibelle());
			validationUnitaire.validateField("sinistreAT",
					"natureQuittance.dateCreation", vo.getDateCreation());
		} catch (ValidationUnitaireException e1) {
			// throw new ValidationException(e1.getMessage(), e1.getValues());
			ILog logger = (ILog) ServicesFactory.getService(ILog.class);
			logger.error(e1.getMessage());
		}
	}

}
