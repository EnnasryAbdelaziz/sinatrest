package eai.devass.gsr.appli.usecase.metier.dossierRente;

import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.core.ServicesFactory;
import ma.co.omnidata.framework.services.log.ILog;
import ma.co.omnidata.framework.services.validation.IValidationUnitaire;
import ma.co.omnidata.framework.services.validation.ValidationUnitaireException;
import eai.devass.gsr.appli.valueobjects.metier.dossierRente.DossierRenteVO;
import eai.devass.gsr.appli.valueobjects.metier.dossierRente.DossierRenteVOConverter;

/**
 * Converter de modification
 * 
 * @author Nom Prenom (email)
 */
public class DossierRenteModifierUCConverter extends DossierRenteVOConverter {
	/**
	 * Methode de validation de l 'object à modifier
	 * 
	 * @param vo
	 *            L 'objet à valider
	 * @throws ValidationException
	 *             Probleme lors de la validation unitaire d' une entité
	 */
	protected void doValider(DossierRenteVO vo) throws ValidationException {
		IValidationUnitaire validationUnitaire = (IValidationUnitaire) ServicesFactory
				.getService(IValidationUnitaire.class);
		try {
			validationUnitaire.validateField("sinistreAT",
					"dossierRente.compagnieRente", vo.getCompagnieRente());
			validationUnitaire.validateField("sinistreAT",
					"dossierRente.numeroRente", vo.getNumeroRente());
			validationUnitaire.validateField("sinistreAT",
					"dossierRente.idDossierRente", vo.getIdDossierRente());
			validationUnitaire.validateField("sinistreAT", "dossierRente.etat",
					vo.getEtat());
			validationUnitaire.validateField("sinistreAT",
					"dossierRente.dateEtat", vo.getDateEtat());
			validationUnitaire.validateField("sinistreAT",
					"dossierRente.dateValidation", vo.getDateValidation());
			validationUnitaire.validateField("sinistreAT",
					"dossierRente.validation", vo.getValidation());
			validationUnitaire.validateField("sinistreAT",
					"dossierRente.dateCreation", vo.getDateCreation());
		} catch (ValidationUnitaireException e1) {
			// throw new ValidationException(e1.getMessage(), e1.getValues());
			ILog logger = (ILog) ServicesFactory.getService(ILog.class);
			logger.error(e1.getMessage());
		}
	}

}
