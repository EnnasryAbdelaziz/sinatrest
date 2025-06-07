package eai.devass.gsr.appli.usecase.metier.dossierRente;

import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.core.ServicesFactory;
import ma.co.omnidata.framework.services.log.ILog;
import ma.co.omnidata.framework.services.validation.IValidationUnitaire;
import ma.co.omnidata.framework.services.validation.ValidationUnitaireException;
import eai.devass.gsr.appli.valueobjects.metier.dossierRente.TuteurVO;
import eai.devass.gsr.appli.valueobjects.metier.dossierRente.TuteurVOConverter;

/**
 * Converter de modification
 * 
 * @author Nom Prenom (email)
 */
public class TuteurModifierUCConverter extends TuteurVOConverter {
	/**
	 * Methode de validation de l 'object à modifier
	 * 
	 * @param vo
	 *            L 'objet à valider
	 * @throws ValidationException
	 *             Probleme lors de la validation unitaire d' une entité
	 */
	protected void doValider(TuteurVO vo) throws ValidationException {
		IValidationUnitaire validationUnitaire = (IValidationUnitaire) ServicesFactory
				.getService(IValidationUnitaire.class);
		try {
			validationUnitaire.validateField("sinistreAT", "tuteur.idTuteur",
					vo.getIdTuteur());
			validationUnitaire.validateField("sinistreAT", "tuteur.nom",
					vo.getNom());
			validationUnitaire.validateField("sinistreAT", "tuteur.numeroCIN",
					vo.getNumeroCIN());
			validationUnitaire.validateField("sinistreAT",
					"tuteur.lienParente", vo.getLienParente());
			validationUnitaire.validateField("sinistreAT", "tuteur.adresse",
					vo.getAdresse());
			validationUnitaire.validateField("sinistreAT",
					"tuteur.codePostale", vo.getCodePostale());
			validationUnitaire.validateField("sinistreAT", "tuteur.ville",
					vo.getVille());
			validationUnitaire.validateField("sinistreAT", "tuteur.pays",
					vo.getPays());
			validationUnitaire.validateField("sinistreAT", "tuteur.etat",
					vo.getEtat());
			validationUnitaire.validateField("sinistreAT", "tuteur.dateEtat",
					vo.getDateEtat());
			validationUnitaire.validateField("sinistreAT", "tuteur.validation",
					vo.getValidation());
			validationUnitaire.validateField("sinistreAT",
					"tuteur.dateValidation", vo.getDateValidation());
			validationUnitaire.validateField("sinistreAT", "tuteur.prenom",
					vo.getPrenom());
			validationUnitaire.validateField("sinistreAT", "tuteur.sexe",
					vo.getSexe());
			validationUnitaire.validateField("sinistreAT",
					"tuteur.dateCreation", vo.getDateCreation());
		} catch (ValidationUnitaireException e1) {
			// throw new ValidationException(e1.getMessage(), e1.getValues());
			ILog logger = (ILog) ServicesFactory.getService(ILog.class);
			logger.error(e1.getMessage());
		}
	}

}
