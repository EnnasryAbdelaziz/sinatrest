package eai.devass.gsr.appli.usecase.metier.dossierRente;

import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import eai.devass.gsr.appli.valueobjects.metier.dossierRente.ProtheseVO;
import eai.devass.gsr.appli.valueobjects.metier.dossierRente.ProtheseVOConverter;

/**
Converter du Use case d' ajout
@author Nom Prenom (email)
*/
public class ProthesesAjouterUCConverter extends ProtheseVOConverter {
/**
Methode de validation de l 'object à ajouter
@param vo L 'objet à valider
@throws ValidationException Probleme lors de la validation unitaire d' une entité
*/
	protected void doValider(ProtheseVO vo) throws ValidationException{
//		IValidationUnitaire validationUnitaire = (IValidationUnitaire) ServicesFactory.getService(IValidationUnitaire.class);
//
//		try {
//			
//			validationUnitaire.validateField("sinistreAT", "protheses.reserveProthese", vo.getReserveProthese());
//			validationUnitaire.validateField("sinistreAT", "protheses.etatProthese", vo.getEtatProthese());
//			validationUnitaire.validateField("sinistreAT", "protheses.dateEtat", vo.getDateEtat());
//			validationUnitaire.validateField("sinistreAT", "protheses.validation", vo.getValidation());
//			validationUnitaire.validateField("sinistreAT", "protheses.dateValidation", vo.getDateValidation());
//			validationUnitaire.validateField("sinistreAT", "protheses.centreProthese", vo.getCentreProthese());
//			validationUnitaire.validateField("sinistreAT", "protheses.dateProthese", vo.getDateProthese());
//			validationUnitaire.validateField("sinistreAT", "protheses.montantProthese", vo.getMontantProthese());
//			validationUnitaire.validateField("sinistreAT", "protheses.idProthese", vo.getIdProthese());
//			validationUnitaire.validateField("sinistreAT", "protheses.numeroProthese", vo.getNumeroProthese());
//			validationUnitaire.validateField("sinistreAT", "protheses.dateCreation", vo.getDateCreation());
//		} catch (ValidationUnitaireException e1) {
//			throw new ValidationException(e1.getMessage(), e1.getValues());
//		}
		
	}

}
