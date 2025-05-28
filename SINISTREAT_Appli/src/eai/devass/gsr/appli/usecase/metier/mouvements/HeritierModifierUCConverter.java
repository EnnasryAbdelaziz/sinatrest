package eai.devass.gsr.appli.usecase.metier.mouvements;

import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.core.ServicesFactory;
import ma.co.omnidata.framework.services.log.ILog;
import ma.co.omnidata.framework.services.validation.IValidationUnitaire;
import ma.co.omnidata.framework.services.validation.ValidationUnitaireException;
import eai.devass.gsr.appli.valueobjects.metier.mouvements.HeritierVO;
import eai.devass.gsr.appli.valueobjects.metier.mouvements.HeritierVOConverter;

/**
 * Converter de modification
 * 
 * @author Nom Prenom (email)
 */
public class HeritierModifierUCConverter extends HeritierVOConverter {
	/**
	 * Methode de validation de l 'object à modifier
	 * 
	 * @param vo
	 *            L 'objet à valider
	 * @throws ValidationException
	 *             Probleme lors de la validation unitaire d' une entité
	 */
	protected void doValider(HeritierVO vo) throws ValidationException {
		IValidationUnitaire validationUnitaire = (IValidationUnitaire) ServicesFactory
				.getService(IValidationUnitaire.class);
		try {
			validationUnitaire.validateField("sinistreAT",
					"heritier.beneficiaire", vo.getBeneficiaire());
			validationUnitaire.validateField("sinistreAT", "heritier.numCIN",
					vo.getNumCIN());
			validationUnitaire.validateField("sinistreAT",
					"heritier.quotePart", vo.getQuotePart());
			validationUnitaire.validateField("sinistreAT",
					"heritier.dateCreation", vo.getDateCreation());
		} catch (ValidationUnitaireException e1) {
			// throw new ValidationException(e1.getMessage(), e1.getValues());
			ILog logger = (ILog) ServicesFactory.getService(ILog.class);
			logger.error(e1.getMessage());
		}
	}

}
