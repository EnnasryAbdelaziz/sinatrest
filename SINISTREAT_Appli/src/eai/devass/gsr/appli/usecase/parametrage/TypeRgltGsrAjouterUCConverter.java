package eai.devass.gsr.appli.usecase.parametrage;

import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.core.ServicesFactory;
import ma.co.omnidata.framework.services.log.ILog;
import ma.co.omnidata.framework.services.validation.IValidationUnitaire;
import ma.co.omnidata.framework.services.validation.ValidationUnitaireException;
import eai.devass.gsr.appli.valueobjects.parametrage.TypeRgltGsrVO;
import eai.devass.gsr.appli.valueobjects.parametrage.TypeRgltGsrVOConverter;

/**
 * Converter du Use case d' ajout
 * 
 * @author Nom Prenom (email)
 */
public class TypeRgltGsrAjouterUCConverter extends TypeRgltGsrVOConverter {
	/**
	 * Methode de validation de l 'object à ajouter
	 * 
	 * @param vo
	 *            L 'objet à valider
	 * @throws ValidationException
	 *             Probleme lors de la validation unitaire d' une entité
	 */
	protected void doValider(TypeRgltGsrVO vo) throws ValidationException {
		IValidationUnitaire validationUnitaire = (IValidationUnitaire) ServicesFactory
				.getService(IValidationUnitaire.class);

		try {
			validationUnitaire.validateField("sinistreAT", "typergltgsr.code",
					vo.getCode());
			validationUnitaire.validateField("sinistreAT",
					"typergltgsr.libelle", vo.getLibelle());
			validationUnitaire.validateField("sinistreAT",
					"typergltgsr.dateCreation", vo.getDateCreation());
		} catch (ValidationUnitaireException e1) {
			ILog logger = (ILog) ServicesFactory.getService(ILog.class);
			logger.error(e1.getMessage());
		}

	}

}
