package eai.devass.gsr.appli.usecase.metier.dossierRente;

import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import eai.devass.gsr.appli.valueobjects.metier.dossierRente.RentierHistoVO;
import eai.devass.gsr.appli.valueobjects.metier.dossierRente.RentierHistoVOConverter;

/**
 * Converter du Use case d' ajout
 * 
 * @author Nom Prenom (email)
 */
public class RentierHistoAjouterUCConverter extends RentierHistoVOConverter {
	/**
	 * Methode de validation de l 'object à ajouter
	 * 
	 * @param vo
	 *            L 'objet à valider
	 * @throws ValidationException
	 *             Probleme lors de la validation unitaire d' une entité
	 */
	protected void doValider(RentierHistoVO vo) throws ValidationException {
		// IValidationUnitaire validationUnitaire = (IValidationUnitaire)
		// ServicesFactory.getService(IValidationUnitaire.class);

		// try {
		// validationUnitaire.validateField("sinistreAT", "rentier.lienParente",
		// vo.getLienParente());
		// validationUnitaire.validateField("sinistreAT", "rentier.numeroCIN",
		// vo.getNumeroCIN());
		// validationUnitaire.validateField("sinistreAT",
		// "rentier.dateNaissance", vo.getDateNaissance());
		// validationUnitaire.validateField("sinistreAT", "rentier.nationalite",
		// vo.getNationalite());
		// validationUnitaire.validateField("sinistreAT",
		// "rentier.numeroTelephone", vo.getNumeroTelephone());
		// validationUnitaire.validateField("sinistreAT", "rentier.numeroGSM",
		// vo.getNumeroGSM());
		// validationUnitaire.validateField("sinistreAT", "rentier.email",
		// vo.getEmail());
		// validationUnitaire.validateField("sinistreAT", "rentier.adresse",
		// vo.getAdresse());
		// validationUnitaire.validateField("sinistreAT", "rentier.codePostale",
		// vo.getCodePostale());
		// validationUnitaire.validateField("sinistreAT", "rentier.ville",
		// vo.getVille());
		// validationUnitaire.validateField("sinistreAT", "rentier.pays",
		// vo.getPays());
		// validationUnitaire.validateField("sinistreAT", "rentier.prothese",
		// vo.getProthese());
		// validationUnitaire.validateField("sinistreAT",
		// "rentier.situationRentier", vo.getSituationRentier());
		// validationUnitaire.validateField("sinistreAT",
		// "rentier.rentierARisque", vo.getRentierARisque());
		// validationUnitaire.validateField("sinistreAT",
		// "rentier.renteConforme", vo.getRenteConforme());
		// validationUnitaire.validateField("sinistreAT", "rentier.etatRente",
		// vo.getEtatRente());
		// validationUnitaire.validateField("sinistreAT", "rentier.dateEtat",
		// vo.getDateEtat());
		// validationUnitaire.validateField("sinistreAT", "rentier.validation",
		// vo.getValidation());
		// validationUnitaire.validateField("sinistreAT",
		// "rentier.capitalConstitutif", vo.getCapitalConstitutif());
		// validationUnitaire.validateField("sinistreAT",
		// "rentier.dateConstitution", vo.getDateConstitution());
		// validationUnitaire.validateField("sinistreAT",
		// "rentier.dateDepartRente", vo.getDateDepartRente());
		// validationUnitaire.validateField("sinistreAT",
		// "rentier.ippTauxRente", vo.getIppTauxRente());
		// validationUnitaire.validateField("sinistreAT",
		// "rentier.modePayement", vo.getModePayement());
		// validationUnitaire.validateField("sinistreAT",
		// "rentier.reserveMathematique", vo.getReserveMathematique());
		// validationUnitaire.validateField("sinistreAT",
		// "rentier.dateValidation", vo.getDateValidation());
		// validationUnitaire.validateField("sinistreAT",
		// "rentier.salaireUtile", vo.getSalaireUtile());
		// validationUnitaire.validateField("sinistreAT",
		// "rentier.approbationQuittance", vo.getApprobationQuittance());
		// validationUnitaire.validateField("sinistreAT",
		// "rentier.montantRenteTrimestrielle",
		// vo.getMontantRenteTrimestrielle());
		// validationUnitaire.validateField("sinistreAT", "rentier.periodicite",
		// vo.getPeriodicite());
		// validationUnitaire.validateField("sinistreAT", "rentier.tuteur",
		// vo.getTuteur());
		// validationUnitaire.validateField("sinistreAT", "rentier.idRentier",
		// vo.getIdRentier());
		// validationUnitaire.validateField("sinistreAT", "rentier.civilite",
		// vo.getCivilite());
		// validationUnitaire.validateField("sinistreAT", "rentier.nom",
		// vo.getNom());
		// validationUnitaire.validateField("sinistreAT", "rentier.prenom",
		// vo.getPrenom());
		// validationUnitaire.validateField("sinistreAT", "rentier.sexe",
		// vo.getSexe());
		// validationUnitaire.validateField("sinistreAT",
		// "rentier.dateCreation", vo.getDateCreation());
		// } catch (ValidationUnitaireException e1) {
		// throw new ValidationException(e1.getMessage(), e1.getValues());
		// }

	}

}
