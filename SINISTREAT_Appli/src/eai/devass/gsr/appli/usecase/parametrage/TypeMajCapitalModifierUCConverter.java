package eai.devass.gsr.appli.usecase.parametrage;

import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.core.ServicesFactory;
import ma.co.omnidata.framework.services.log.ILog;
import ma.co.omnidata.framework.services.validation.IValidationUnitaire;
import ma.co.omnidata.framework.services.validation.ValidationUnitaireException;
import eai.devass.gsr.appli.valueobjects.parametrage.TypeMajCapitalVO;
import eai.devass.gsr.appli.valueobjects.parametrage.TypeMajCapitalVOConverter;

/**
 * Converter de modification
 * 
 * @author Nom Prenom (email)
 */
public class TypeMajCapitalModifierUCConverter extends
		TypeMajCapitalVOConverter {
	/**
	 * Methode de validation de l 'object à modifier
	 * 
	 * @param vo
	 *            L 'objet à valider
	 * @throws ValidationException
	 *             Probleme lors de la validation unitaire d' une entité
	 */
	protected void doValider(TypeMajCapitalVO vo) throws ValidationException {
		IValidationUnitaire validationUnitaire = (IValidationUnitaire) ServicesFactory
				.getService(IValidationUnitaire.class);
		try {
			validationUnitaire.validateField("sinistreAT",
					"typeMajCapital.code", vo.getCode());
			validationUnitaire.validateField("sinistreAT",
					"typeMajCapital.libelle", vo.getLibelle());
			validationUnitaire.validateField("sinistreAT",
					"typeMajCapital.dateCreation", vo.getDateCreation());
		} catch (ValidationUnitaireException e1) {
			ILog logger = (ILog) ServicesFactory.getService(ILog.class);
			logger.error(e1.getMessage());
		}
	}

}
