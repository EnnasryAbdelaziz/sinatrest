package eai.devass.gsr.appli.usecase.parametrage;

import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.core.ServicesFactory;
import ma.co.omnidata.framework.services.log.ILog;
import ma.co.omnidata.framework.services.validation.IValidationUnitaire;
import ma.co.omnidata.framework.services.validation.ValidationUnitaireException;
import eai.devass.gsr.appli.valueobjects.parametrage.TypeCertificatVO;
import eai.devass.gsr.appli.valueobjects.parametrage.TypeCertificatVOConverter;

/**
 * Converter de modification
 * 
 * @author Nom Prenom (email)
 */
public class TypeCertificatModifierUCConverter extends
		TypeCertificatVOConverter {
	/**
	 * Methode de validation de l 'object à modifier
	 * 
	 * @param vo
	 *            L 'objet à valider
	 * @throws ValidationException
	 *             Probleme lors de la validation unitaire d' une entité
	 */
	protected void doValider(TypeCertificatVO vo) throws ValidationException {
		IValidationUnitaire validationUnitaire = (IValidationUnitaire) ServicesFactory
				.getService(IValidationUnitaire.class);
		try {
			validationUnitaire.validateField("sinistreAT",
					"typeCertificat.code", vo.getCode());
			validationUnitaire.validateField("sinistreAT",
					"typeCertificat.libelle", vo.getLibelle());
			validationUnitaire.validateField("sinistreAT",
					"typeCertificat.dateCreation", vo.getDateCreation());
		} catch (ValidationUnitaireException e1) {
			ILog logger = (ILog) ServicesFactory.getService(ILog.class);
			logger.error(e1.getMessage());
		}
	}

}
