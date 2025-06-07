package eai.devass.gsr.appli.usecase.metier.dossierRente;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.services.businessInterface.impl.FacadeServiceUseCase;
import ma.co.omnidata.framework.services.core.ServicesFactory;
import ma.co.omnidata.framework.services.dao.IPersistenceService;
import ma.co.omnidata.framework.services.entites.EntiteException;
import ma.co.omnidata.framework.services.entites.IEntite;

import org.hibernate.Session;

import eai.devass.commun.appli.exeptions.ExceptionMetier;
import eai.devass.gsr.appli.modele.metier.dossierRente.Prothese;
import eai.devass.gsr.appli.modele.metier.dossierRente.Rentier;
import eai.devass.gsr.appli.modele.metier.dossierRente.RentierHisto;
import eai.devass.gsr.appli.modele.parametrage.EtatRentier;
import eai.devass.gsr.appli.utile.TypeConverter;

/**
 * Service de modification d' une entité
 * 
 * @author Nom Prenom (email)
 */
public class RentierModifierUC extends FacadeServiceUseCase {

	/**
	 * Methode qui execute le use case de modification d' un objet
	 * 
	 * @param entite
	 *            l' objet à modifier
	 * @param params
	 *            paramatere additionel qu 'on peut passer au Use Case
	 * @throws Exception
	 *             Probleme lors de l' execution du Use case
	 */
	protected Session getSession() throws ExceptionMetier {
		try {
			IPersistenceService dao = (IPersistenceService) ServicesFactory
					.getService(IPersistenceService.class);
			return (Session) dao.getSession();

		} catch (Exception e) {
			throw new ExceptionMetier(e.getMessage());
		}

	}
	
	protected void doExecuter(IValueObject entite, HashMap params)
			throws Exception {

		Rentier lUpdated = (Rentier) this.getItem(Rentier.class);

		if (lUpdated.getRefTuteur() != null) {
			if ("false".equals(lUpdated.getRefTuteur().getNewTuteur())) {
				((IEntite) lUpdated.getRefTuteur()).getFactory()
						.newEntiteManager()
						.modifyEntite(lUpdated.getRefTuteur());
			} else {
				if (lUpdated.getRefTuteur().getId() == 0
						&& !"".equals(lUpdated.getRefTuteur().getNom())) {
					((IEntite) lUpdated.getRefTuteur()).getFactory()
							.newEntiteManager()
							.createEntite(lUpdated.getRefTuteur());
				} else {

					lUpdated.setRefTuteur(null);
				}
			}
		}

		// Prothese
		if (lUpdated.getRefsProtheses() != null
				&& !lUpdated.getRefsProtheses().isEmpty()) {
			List<Prothese> list = lUpdated.getRefsProtheses();
			List<Prothese> list2 = new ArrayList<Prothese>();
			for (Iterator iterator = list.iterator(); iterator.hasNext();) {
				Prothese p = (Prothese) iterator.next();
				list2.add(p);

			}

			for (Iterator iterator = list.iterator(); iterator.hasNext();) {
				Prothese p = (Prothese) iterator.next();
				p.setRefRentier(lUpdated);
				if (p.getStatut().equals("CREATE")) {
					p.getFactory().newEntiteManager().createEntite(p);
				} else {
					if (p.getStatut().equals("UPDATE")) {
						p.getFactory().newEntiteManager().modifyEntite(p);

					} else {
						if (p.getStatut().equals("DELETED")) {
							list2.remove(p);
							p.getFactory().newEntiteManager().deleteEntite(p);
						}
					}

				}

			}
			lUpdated.setRefsProtheses(list2);
		}

		if (lUpdated.getRefModePayement() != null) {
			lUpdated.getRefModePayement().setRefRentier(lUpdated);
			((IEntite) lUpdated.getRefModePayement()).getFactory()
					.newEntiteManager()
					.modifyEntite(lUpdated.getRefModePayement());
		}
		if (lUpdated.getRefEtatRentier() != null) {
			EtatRentier etat = new EtatRentier();
			etat.setId(lUpdated.getRefEtatRentier().getId());
			lUpdated.setRefEtatRentier(etat);
		}
		Format forma = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date d = Calendar.getInstance().getTime();
		String datjour = forma.format(d);
		lUpdated.setDateModification(TypeConverter.getInstance()
				.stringToCalendar("dd/MM/yyyy HH:mm:ss", datjour));
		
		
		lUpdated.getFactory().newEntiteManager().modifyEntite(lUpdated);

		setRentierHisto(lUpdated);

		this.addResultItem(lUpdated);

	}

	public void setRentierHisto(Rentier lUpdated) throws EntiteException {
		RentierHisto historique = new RentierHisto();
		historique.setIdRentier(lUpdated.getId());
		historique.setLienParente(lUpdated.getLienParente());
		historique.setNumeroCIN(lUpdated.getNumeroCIN());
		historique.setDateNaissance(lUpdated.getDateNaissance());
		if(lUpdated.getRefSituationRentier() != null) {
			historique.setNationalite(lUpdated.getRefSituationRentier().getId());
		}
		if(lUpdated.getRefNationalite() != null) {
			historique.setNationalite(lUpdated.getRefNationalite().getCode());
		}
		historique.setNumeroTelephone(lUpdated.getNumeroTelephone());
		historique.setNumeroGSM(lUpdated.getNumeroGSM());
		historique.setEmail(lUpdated.getEmail());
		historique.setAdresse(lUpdated.getAdresse());
		historique.setCodePostale(lUpdated.getCodePostale());
		historique.setVille(lUpdated.getVille());
		historique.setPays(lUpdated.getPays());
		historique.setProthese(lUpdated.getProthese());
		historique.setRentierARisque(lUpdated.getRentierARisque());
		historique.setRenteConforme(lUpdated.getRenteConforme());
		historique.setDateEtat(lUpdated.getDateEtat());
		historique.setMotifEtat(lUpdated.getMotifEtat());
		historique.setValidation(lUpdated.getValidation());
		historique.setCapitalConstitutif(lUpdated.getCapitalConstitutif());
		historique.setDateConstitution(lUpdated.getDateConstitution());
		historique.setDateDepartRente(lUpdated.getDateDepartRente());
		historique.setIppTauxRente(lUpdated.getIppTauxRente());
		historique.setReserveMathematique(lUpdated.getReserveMathematique());
		historique.setDateValidation(lUpdated.getDateValidation());
		historique.setSalaireUtile(lUpdated.getSalaireUtile());
		historique.setApprobationQuittance(lUpdated.getApprobationQuittance());
		historique.setMontantRenteTrimestrielle(lUpdated
				.getMontantRenteTrimestrielle());
		historique.setPeriodicite(lUpdated.getPeriodicite());
		historique.setTuteur(lUpdated.getTuteur());
		historique.setOrphelinPur(lUpdated.getOrphelinPur());
		historique.setCivilite(lUpdated.getCivilite());
		historique.setNom(lUpdated.getNom());
		historique.setPrenom(lUpdated.getPrenom());
		historique.setSexe(lUpdated.getSexe());
		historique.setObservationConforme(lUpdated.getObservationConforme());
		historique.setDateCreation(lUpdated.getDateCreation());
		historique.setDateModification(lUpdated.getDateModification());
		historique.setUserSas(lUpdated.getUserSas());
		if (lUpdated.getRefAyantDroit() != null) {
			historique.setRefAyantDroit(lUpdated.getRefAyantDroit().getId());
		}
		if (lUpdated.getRefDossierRente() != null) {
			historique
					.setRefDossierRente(lUpdated.getRefDossierRente().getId());
		}
		if (lUpdated.getRefEtatRentier() != null) {
			historique.setRefEtatRentier(lUpdated.getRefEtatRentier().getId());
		}
		if (lUpdated.getRefModePayement() != null) {
			historique
					.setRefModePayement(lUpdated.getRefModePayement().getId());
		}
		if (lUpdated.getRefTuteur() != null) {
			historique.setRefTuteur(lUpdated.getRefTuteur().getId());
		}
		if (lUpdated.getRefVictime() != null) {
			historique.setRefVictime(lUpdated.getRefVictime().getId());
		}

		/**
		 * EVO Lot1
		 */
		historique.setArrerageAvantConstitution(lUpdated
				.getArrerageAvantConstitution());
		historique.setArrerages(lUpdated.getArrerages());
		historique.setUserSasModificateur(lUpdated.getUserSasModificateur());

		historique.setDonneeConforme(lUpdated.getDonneeConforme());
		historique.setObservationDonneeConforme(lUpdated
				.getObservationDonneeConforme());
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
	/**
	 * Methode qui retourne le lock mode du Use Case
	 * 
	 * @returns le lock mode
	 */
	// public int getLockMode() {
	// return LOCKMODE_UNLOCK;
	// }
}
