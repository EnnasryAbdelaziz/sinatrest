package eai.devass.gsr.appli.usecase.metier.mouvements;

import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.core.ServicesFactory;
import ma.co.omnidata.framework.services.log.ILog;
import ma.co.omnidata.framework.services.validation.IValidationUnitaire;
import ma.co.omnidata.framework.services.validation.ValidationUnitaireException;
import eai.devass.gsr.appli.valueobjects.metier.mouvements.ConsomReserveRenteVO;
import eai.devass.gsr.appli.valueobjects.metier.mouvements.ConsomReserveRenteVOConverter;

/**
 * Converter du Use case d' ajout
 * 
 * @author Nom Prenom (email)
 */
public class ConsomReserveRenteAjouterUCConverter extends
		ConsomReserveRenteVOConverter {
	/**
	 * Methode de validation de l 'object à ajouter
	 * 
	 * @param vo
	 *            L 'objet à valider
	 * @throws ValidationException
	 *             Probleme lors de la validation unitaire d' une entité
	 */
	protected void doValider(ConsomReserveRenteVO vo)
			throws ValidationException {
		IValidationUnitaire validationUnitaire = (IValidationUnitaire) ServicesFactory
				.getService(IValidationUnitaire.class);
        String sinistreAT = "sinistreAT";
		try {
            validationUnitaire.validateField(sinistreAT,
					"consomReserveRente.numeroQuittance",
					vo.getNumeroQuittance());
            validationUnitaire.validateField(sinistreAT,
					"consomReserveRente.reserveApres", vo.getReserveApres());
            validationUnitaire.validateField(sinistreAT,
					"consomReserveRente.reserveAvant", vo.getReserveAvant());
            validationUnitaire.validateField(sinistreAT,
					"consomReserveRente.dateCreation", vo.getDateCreation());
		} catch (ValidationUnitaireException e1) {
			ILog logger = (ILog) ServicesFactory.getService(ILog.class);
			logger.error(e1.getMessage());
		}

	}

}
