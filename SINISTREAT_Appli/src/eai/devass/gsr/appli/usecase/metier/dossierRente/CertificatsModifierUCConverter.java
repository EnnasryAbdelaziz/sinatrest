package eai.devass.gsr.appli.usecase.metier.dossierRente;

import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.core.ServicesFactory;
import ma.co.omnidata.framework.services.log.ILog;
import ma.co.omnidata.framework.services.validation.IValidationUnitaire;
import ma.co.omnidata.framework.services.validation.ValidationUnitaireException;
import eai.devass.gsr.appli.valueobjects.metier.dossierRente.CertificatsVO;
import eai.devass.gsr.appli.valueobjects.metier.dossierRente.CertificatsVOConverter;

/**
 * Converter de modification
 * 
 * @author Nom Prenom (email)
 */
public class CertificatsModifierUCConverter extends CertificatsVOConverter {
	/**
	 * Methode de validation de l 'object à modifier
	 * 
	 * @param vo
	 *            L 'objet à valider
	 * @throws ValidationException
	 *             Probleme lors de la validation unitaire d' une entité
	 */
	protected void doValider(CertificatsVO vo) throws ValidationException {
		IValidationUnitaire validationUnitaire = (IValidationUnitaire) ServicesFactory
				.getService(IValidationUnitaire.class);
        String sinistreAT = "sinistreAT";
		try {
            validationUnitaire.validateField(sinistreAT,
					"certificats.dateReception", vo.getDateReception());
            validationUnitaire.validateField(sinistreAT,
					"certificats.observation", vo.getObservation());
            validationUnitaire.validateField(sinistreAT,
					"certificats.typeCertificat", vo.getTypeCertificat());
            validationUnitaire.validateField(sinistreAT,
					"certificats.idCertificat", vo.getIdCertificat());
            validationUnitaire.validateField(sinistreAT,
					"certificats.numeroCertificat", vo.getNumeroCertificat());
            validationUnitaire.validateField(sinistreAT,
					"certificats.periodeDU", vo.getPeriodeDU());
            validationUnitaire.validateField(sinistreAT,
					"certificats.periodeAU", vo.getPeriodeAU());
            validationUnitaire.validateField(sinistreAT, "certificats.etat",
					vo.getEtat());
            validationUnitaire.validateField(sinistreAT,
					"certificats.dateEtat", vo.getDateEtat());
            validationUnitaire.validateField(sinistreAT,
					"certificats.validation", vo.getValidation());
            validationUnitaire.validateField(sinistreAT,
					"certificats.dateValidation", vo.getDateValidation());
            validationUnitaire.validateField(sinistreAT,
					"certificats.dateCreation", vo.getDateCreation());
		} catch (ValidationUnitaireException e1) {
			ILog logger = (ILog) ServicesFactory.getService(ILog.class);
			logger.error(e1.getMessage());
		}
	}

}
