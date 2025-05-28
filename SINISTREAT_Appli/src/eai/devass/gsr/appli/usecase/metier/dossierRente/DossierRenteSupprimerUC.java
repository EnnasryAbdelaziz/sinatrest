package eai.devass.gsr.appli.usecase.metier.dossierRente;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.services.businessInterface.impl.FacadeServiceUseCase;
import ma.co.omnidata.framework.services.entites.EntiteException;
import eai.devass.gsr.appli.manager.metier.dossierRente.DossierRenteManager;
import eai.devass.gsr.appli.manager.metier.dossierRente.RentierManager;
import eai.devass.gsr.appli.modele.metier.dossierRente.DossierRente;
import eai.devass.gsr.appli.modele.metier.dossierRente.Rentier;
import eai.devass.gsr.appli.modele.metier.dossierRente.RentierHisto;
import eai.devass.gsr.appli.modele.parametrage.EtatRentier;
import eai.devass.gsr.appli.prm.EtatRente;
import eai.devass.gsr.appli.utile.TypeConverter;
import eai.devass.sinistreat.appli.exception.FonctionnelleException;

/**
 * Service de suppression d' une entitÃ©
 * 
 * @author Nom Prenom (email)
 */
public class DossierRenteSupprimerUC extends FacadeServiceUseCase {

	/**
	 * Methode qui execute le use case de suppression
	 * 
	 * @param entite
	 *            l' objet Ã  supprimer
	 * @param params
	 *            paramatere additionel qu 'on peut passer au Use Case
	 * @throws Exception
	 *             Probleme lors de l' execution du Use case
	 */

	@Override
	protected void doExecuter(IValueObject vo, HashMap param) throws Exception {
		RentierManager rentierManager = new RentierManager();
		DossierRente dossierToUpdate = (DossierRente) this
				.getItem(DossierRente.class);
		List<Rentier> rentierToUpdate = (List<Rentier>) dossierToUpdate
				.getRefsRentier();

		// traitement date du jour
		Format forma = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date d = Calendar.getInstance().getTime();
		String datjour = forma.format(d);
		int i = 0;
		for (Rentier cur : rentierToUpdate) {
			Rentier tmp = (Rentier) rentierManager.getEntite(cur);
			// verifier si le rentier est toujour en état "crée"
			if (tmp == null) {
				throw new FonctionnelleException("Rentier introuvable");
			}
			// suppression logique du rentier
			if (cur.getRefEtatRentier().getId() == 14) {

				setRentierHisto(tmp, cur);
				tmp.setRefDossierRente(null);
				tmp.setRefEtatRentier(cur.getRefEtatRentier());

				// suppression logique du ModePayement
				if (tmp.getRefModePayement() != null) {
					tmp.getRefModePayement().setEtat(14.0);
					tmp.getRefModePayement().setDateEtat(
							(TypeConverter.getInstance().stringToCalendar(
									"dd/MM/yyyy HH:mm:ss", datjour)));
				}

				// suppression logique du tuteur
				if (tmp.getTuteur() && tmp.getRefTuteur() != null) {
					tmp.getRefTuteur().setEtat(14.0);
					tmp.getRefTuteur().setDateEtat(
							(TypeConverter.getInstance().stringToCalendar(
									"dd/MM/yyyy HH:mm:ss", datjour)));
				}

				i++;
			}

		}

		dossierToUpdate = (DossierRente) new DossierRenteManager()
				.getEntite(dossierToUpdate);
		// MAJ etat dossier rente
		if (i == rentierToUpdate.size()) {
			dossierToUpdate.setRefEtatRentier(new EtatRentier(EtatRente.Cloture.getCode()));
			dossierToUpdate.setDateEtat((TypeConverter.getInstance()
					.stringToCalendar("dd/MM/yyyy HH:mm:ss", datjour)));
			dossierToUpdate.getFactory().newEntiteManager()
					.modifyEntite(dossierToUpdate);
		}
		// calcul Rente (redistribution)
//		TransverseManager tm = new TransverseManager();
//		tm.calculRente(dossierToUpdate.getId());

	}

	/*
	 * Historisation Rentier
	 */
	public void setRentierHisto(Rentier tmp, Rentier cur)
			throws EntiteException {

		RentierHisto historique = new RentierHisto();
		historique.setIdRentier(tmp.getId());
		historique.setLienParente(tmp.getLienParente());
		historique.setNumeroCIN(tmp.getNumeroCIN());
		historique.setDateNaissance(tmp.getDateNaissance());
		if(tmp.getRefSituationRentier() != null) {
			historique.setNationalite(tmp.getRefSituationRentier().getId());
		}
		if(tmp.getRefNationalite() != null) {
			historique.setNationalite(tmp.getRefNationalite().getCode());
		}
		historique.setNumeroTelephone(tmp.getNumeroTelephone());
		historique.setNumeroGSM(tmp.getNumeroGSM());
		historique.setEmail(tmp.getEmail());
		historique.setMotifEtat(tmp.getMotifEtat());
		historique.setAdresse(tmp.getAdresse());
		historique.setCodePostale(tmp.getCodePostale());
		historique.setVille(tmp.getVille());
		historique.setPays(tmp.getPays());
		historique.setProthese(tmp.getProthese());
		historique.setRentierARisque(tmp.getRentierARisque());
		historique.setRenteConforme(tmp.getRenteConforme());
		historique.setDateEtat(tmp.getDateEtat());
		if (cur.getRefEtatRentier() != null
				&& cur.getRefEtatRentier().getId() == 4) {
			historique.setValidation(true);
		} else {
			historique.setValidation(tmp.getValidation());
		}
		historique.setCapitalConstitutif(tmp.getCapitalConstitutif());
		historique.setDateConstitution(tmp.getDateConstitution());
		historique.setDateDepartRente(tmp.getDateDepartRente());
		historique.setIppTauxRente(tmp.getIppTauxRente());
		historique.setReserveMathematique(tmp.getReserveMathematique());
		historique.setDateValidation(tmp.getDateValidation());
		historique.setSalaireUtile(tmp.getSalaireUtile());
		historique.setApprobationQuittance(tmp.getApprobationQuittance());
		historique.setMontantRenteTrimestrielle(tmp
				.getMontantRenteTrimestrielle());
		historique.setPeriodicite(tmp.getPeriodicite());
		historique.setTuteur(tmp.getTuteur());
		historique.setOrphelinPur(tmp.getOrphelinPur());
		historique.setCivilite(tmp.getCivilite());
		historique.setNom(tmp.getNom());
		historique.setPrenom(tmp.getPrenom());
		historique.setSexe(tmp.getSexe());
		historique.setObservationConforme(tmp.getObservationConforme());
		historique.setDateCreation(tmp.getDateCreation());
		Format forma = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date d = Calendar.getInstance().getTime();
		String datjour = forma.format(d);
		historique.setDateModification(TypeConverter.getInstance()
				.stringToCalendar("dd/MM/yyyy HH:mm:ss", datjour));
		historique.setUserSas(cur.getUserSas());
		if (tmp.getRefAyantDroit() != null) {
			historique.setRefAyantDroit(tmp.getRefAyantDroit().getId());
		}
		if (tmp.getRefDossierRente() != null) {
			historique.setRefDossierRente(tmp.getRefDossierRente().getId());
		}
		if (tmp.getRefEtatRentier() != null) {
			historique.setRefEtatRentier(cur.getRefEtatRentier().getId());
		}
		if (tmp.getRefModePayement() != null) {
			historique.setRefModePayement(tmp.getRefModePayement().getId());
		}
		if (tmp.getRefTuteur() != null) {
			historique.setRefTuteur(tmp.getRefTuteur().getId());
		}
		if (tmp.getRefVictime() != null) {
			historique.setRefVictime(tmp.getRefVictime().getId());
		}

		/**
		 * EVO Lot1
		 */
		historique.setArrerageAvantConstitution(tmp
				.getArrerageAvantConstitution());
		historique.setArrerages(tmp.getArrerages());
		historique.setUserSasModificateur(tmp.getUserSasModificateur());
		/**
		 * Fin EVO
		 */

		historique.getFactory().newEntiteManager().createEntite(historique);
	}

	/**
	 * Methode pour activer le service de transaction
	 * 
	 * @returns soit true pour activer le service de transaction ou false pour
	 *          le desactiver
	 */
	public boolean isTransactionnal() {
		return true;
	}

	/**
	 * Methode pour activer le service de trace
	 * 
	 * @returns soit true pour activer le service de trace ou false pour le
	 *          desactiver
	 */
	public boolean isTracable() {
		return false;
	}
}
