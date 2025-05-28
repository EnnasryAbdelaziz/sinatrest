package eai.devass.gsr.appli.usecase.parametrage;

import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.core.ServicesFactory;
import ma.co.omnidata.framework.services.log.ILog;
import ma.co.omnidata.framework.services.validation.IValidationUnitaire;
import ma.co.omnidata.framework.services.validation.ValidationUnitaireException;
import eai.devass.gsr.appli.valueobjects.parametrage.TypeMvtProtheseVO;
import eai.devass.gsr.appli.valueobjects.parametrage.TypeMvtProtheseVOConverter;

/**
 * Converter du Use case d' ajout
 * 
 * @author Nom Prenom (email)
 */
public class TypeMvtProtheseAjouterUCConverter extends
		TypeMvtProtheseVOConverter {
	/**
	 * Methode de validation de l 'object à ajouter
	 * 
	 * @param vo
	 *            L 'objet à valider
	 * @throws ValidationException
	 *             Probleme lors de la validation unitaire d' une entité
	 */
	protected void doValider(TypeMvtProtheseVO vo) throws ValidationException {
		IValidationUnitaire validationUnitaire = (IValidationUnitaire) ServicesFactory
				.getService(IValidationUnitaire.class);

		try {
			validationUnitaire.validateField("sinistreAT",
					"typeMvtProthese.code", vo.getCode());
			validationUnitaire.validateField("sinistreAT",
					"typeMvtProthese.libelle", vo.getLibelle());
			validationUnitaire.validateField("sinistreAT",
					"typeMvtProthese.dateCreation", vo.getDateCreation());
		} catch (ValidationUnitaireException e1) {
			ILog logger = (ILog) ServicesFactory.getService(ILog.class);
			logger.error(e1.getMessage());
		}

	}

}
