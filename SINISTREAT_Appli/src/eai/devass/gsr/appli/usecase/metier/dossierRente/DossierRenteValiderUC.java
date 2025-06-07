package eai.devass.gsr.appli.usecase.metier.dossierRente;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.services.businessInterface.impl.FacadeServiceUseCase;
import ma.co.omnidata.framework.services.core.ServicesFactory;
import ma.co.omnidata.framework.services.dao.IPersistenceService;
import ma.co.omnidata.framework.services.entites.EntiteException;
import ma.co.omnidata.framework.utile.DateUtile;

import org.hibernate.Session;

import com.rmawatanya.reglement.application.metier.valueobjects.QuittanceVO;

import eai.devass.commun.appli.exeptions.ExceptionMetier;
import eai.devass.gsr.appli.manager.metier.dossierRente.DossierRenteManager;
import eai.devass.gsr.appli.manager.metier.dossierRente.RentierManager;
import eai.devass.gsr.appli.manager.metier.reglement.PrergltManager;
import eai.devass.gsr.appli.manager.metier.reglement.QuittanceGsrManager;
import eai.devass.gsr.appli.modele.metier.dossierRente.DossierRente;
import eai.devass.gsr.appli.modele.metier.dossierRente.Prothese;
import eai.devass.gsr.appli.modele.metier.dossierRente.Rentier;
import eai.devass.gsr.appli.modele.metier.dossierRente.RentierHisto;
import eai.devass.gsr.appli.modele.metier.reglement.ModePayement;
import eai.devass.gsr.appli.modele.metier.reglement.Prerglt;
import eai.devass.gsr.appli.modele.metier.reglement.QuittanceGsr;
import eai.devass.gsr.appli.modele.parametrage.EtatProthese;
import eai.devass.gsr.appli.modele.parametrage.EtatQtc;
import eai.devass.gsr.appli.modele.parametrage.EtatRentier;
import eai.devass.gsr.appli.modele.parametrage.ModeReglement;
import eai.devass.gsr.appli.modele.parametrage.NatureQtcGsr;
import eai.devass.gsr.appli.modele.parametrage.SituationQuittanceGsr;
import eai.devass.gsr.appli.modele.parametrage.TypeApprobation;
import eai.devass.gsr.appli.modele.parametrage.TypeQuittance;
import eai.devass.gsr.appli.modele.parametrage.TypeRgltGsr;
import eai.devass.gsr.appli.prm.EtatQuittance;
import eai.devass.gsr.appli.prm.NatureQuittance;
import eai.devass.gsr.appli.prm.TypeReglement;
import eai.devass.gsr.appli.utile.CommonGsrUtils;
import eai.devass.gsr.appli.utile.TypeConverter;
import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.manager.sinistre.SinistreManager;
import eai.devass.sinistreat.appli.modele.metier.sinistre.Sinistre;
import eai.devass.sinistreat.appli.utils.IConstantes;

public class DossierRenteValiderUC extends FacadeServiceUseCase {

	@Override
	protected void doExecuter(IValueObject vo, HashMap param) throws Exception {
		SinistreManager sinistreManager = (SinistreManager) ServicesFactory
				.getService(SinistreManager.class);
		RentierManager rentierManager = new RentierManager();
		DossierRente dossierToUpdate = (DossierRente) this
				.getItem(DossierRente.class);

		List<Rentier> rentierToUpdate = (List<Rentier>) dossierToUpdate
				.getRefsRentier();
		dossierToUpdate.setUserSasModificateur(rentierToUpdate.get(0)
				.getUserSasModificateur());
		String userSasModificateur = rentierToUpdate.get(0)
				.getUserSasModificateur();
		// MAJ etat dossier rente
		DossierRenteManager dossierRenteManager = new DossierRenteManager();
		DossierRente ds = (DossierRente) dossierRenteManager
				.getEntite(dossierToUpdate);
		Long numeroRente = ds.getNumeroRente();
		if (dossierToUpdate.getRefEtatRentier() != null && !ds.getValidation()) {
			if (dossierRenteManager.doUpdateEtatDossierRente(dossierToUpdate,
					numeroRente) != 1) {
				throw new FonctionnelleException(
						"Impossible de modifier l'état du dossier rente");
			}
		}
		dossierToUpdate = dossierRenteManager.doGetDossierRenteBySinistre(
				dossierToUpdate, 0, 0).get(0);
		Boolean rejet = false;
		Sinistre sinistrte = dossierToUpdate.getRefSinistre();
		Double resultReserveGrave = sinistrte.getReserveGrave();
		Double sinistrteReserveProthese = sinistrte.getReserveProthese();
		for (Rentier cur : rentierToUpdate) {
			if (cur.getRefEtatRentier().getId() == 3) {
				rejet = true;
			} else {
				rejet = false;
			}
		}
		if (rejet.equals(false)) {

			// somme Capital constitutif GSR
			Double sumCCDossierRente = dossierRenteManager
					.doSommeCCDossierRente(dossierToUpdate);
			// somme Arrerage regle au niveau du sinistre
			Double sumArrerageRegle = dossierRenteManager
					.doSommeArrerageRegle(dossierToUpdate);
			// somme Arrerage regle au niveau de la GSR
			Double sumArrerageDossierRente = dossierRenteManager
					.doSommeArrerageDossierRente(dossierToUpdate);
			// Sum Montant nouvelle réserve grave
			Double SommeMontantNouvelleRG = dossierRenteManager
					.doSommeMontantNouvelleRG(dossierToUpdate);

			// Evo 28/01/2014 --> test reserve grave: negative
			if (resultReserveGrave + sumArrerageRegle < sumCCDossierRente
					+ sumArrerageDossierRente) {
				throw new FonctionnelleException(
						"Réserve grave insuffisante!!!");
			}
			sinistrte.setReserveGrave(SommeMontantNouvelleRG);
			sinistrte.setUserModificateur(dossierToUpdate.getRefsRentier().get(0).getUserSasModificateur());
			//WMOS: 12/11/2015 add date modification 
			sinistrte.setDateModification(new Date());
			// (new SinistreManager()).modifyEntite(sinistrte);

			sinistreManager.gsrCreerMouvement(sinistrte, "Modification",
					IConstantes.MOTIF_GSR_CHANGEMENT_RESERVEGRAVE);

		}

		for (Rentier cur : rentierToUpdate) {
			Rentier tmp = (Rentier) rentierManager.getEntite(cur);
			// verifier si le rentier est toujour en état "crée"
			if (tmp == null) {
				throw new FonctionnelleException("Rentier introuvable");
			}
			if (tmp.getRefEtatRentier().getId() != 1
					&& tmp.getRefEtatRentier().getId() != 2) {
				throw new FonctionnelleException(
						"L'état actuel du rentier ne permet pas la validation");
			}
			// test sur le nombre d'element modifier
			if (rentierManager.doUpdateEtatRentier(cur) != 1
					&& rentierManager.doUpdateEtatRentier(cur) != 2) {
				throw new FonctionnelleException(
						"Impossible de modifier l'état du rentier");
			}
			// calcul du rest Resrve grave pour valider une rente
			if (cur.getRefEtatRentier().getId() == 4) {
				// generation du quittance
				if (tmp.getLienParente() == 0 && tmp.getProthese()!= null && tmp.getProthese()) {
					if (tmp.getRefsProtheses() != null
							&& tmp.getRefsProtheses().size() > 0) {
						Double sumReserveProtheseGsr = 0D;
						List<Prothese> list = tmp.getRefsProtheses();
						for (Prothese p : list) {

							sumReserveProtheseGsr = sumReserveProtheseGsr
									+ p.getReserveProthese();
							p.setRefEtatProthese(new EtatProthese(
									eai.devass.gsr.appli.prm.EtatProthese.Validee
											.getId()));
							p.setDateEtat(Calendar.getInstance());
							p.getFactory().newEntiteManager().modifyEntite(p);
						}
						if (sinistrteReserveProthese < sumReserveProtheseGsr) {
							throw new FonctionnelleException(
									"Erreur réserve prothèse. Merci de vérifier les données au niveau de l’AT!!!");
						}
					}
					////////////////////
					// Mise à jour la réserve prothèse au niveau sinAt(mise à 0)
					sinistrte = tmp.getRefDossierRente().getRefSinistre();
					sinistrte.setReserveProthese(Double.valueOf(0));
					sinistrte.setUserModificateur(tmp.getUserSasModificateur());
					//WMOS: 12/11/2015 add date modification 
					sinistrte.setDateModification(new Date());
					(new SinistreManager()).modifyEntite(sinistrte);
					sinistreManager.gsrCreerMouvement(sinistrte,
							"Modification",
							IConstantes.MOTIF_GSR_CHANGEMENT_RESERVEPROTHESE);

				}
				//update rentier
				tmp.setUserSasModificateur(userSasModificateur);
				Format forma = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
				Date d = Calendar.getInstance().getTime();
				String datjour = forma.format(d);
				tmp.setDateModification(TypeConverter.getInstance()
						.stringToCalendar("dd/MM/yyyy HH:mm:ss", datjour));
				if (cur.getRefEtatRentier() != null
						&& cur.getRefEtatRentier().getId() == 4) {
					tmp.setValidation(true);
					tmp.setDateValidation(TypeConverter.getInstance()
							.stringToCalendar("dd/MM/yyyy HH:mm:ss", datjour));
				}
				if (tmp.getRefEtatRentier() != null) {
					EtatRentier refVO = new EtatRentier();
					refVO.setId(cur.getRefEtatRentier().getId());
					tmp.setRefEtatRentier(refVO);
				}
				//update dossier rente
				dossierToUpdate.setUserSasModificateur(userSasModificateur);
				dossierToUpdate.setDateModification(TypeConverter.getInstance()
						.stringToCalendar("dd/MM/yyyy HH:mm:ss", datjour));
				dossierToUpdate.setDateValidation(TypeConverter.getInstance()
						.stringToCalendar("dd/MM/yyyy HH:mm:ss", datjour));
				if (cur.getRefEtatRentier() != null
						&& cur.getRefEtatRentier().getId() == 4) {
					dossierToUpdate.setValidation(true);
				}
				if (tmp.getRefEtatRentier() != null) {
					EtatRentier refVO = new EtatRentier();
					refVO.setId(cur.getRefEtatRentier().getId());
					dossierToUpdate.setRefEtatRentier(refVO);
				}
				//
				//creatQtc(tmp, cur, Long.toString(numeroRente));
				creatQtcEquilibre(tmp, Long.toString(numeroRente), cur);

			}

			setRentierHisto(tmp, cur);
		}
		if (sinistrte.getRenteCreee() != null) {
			if (sinistrte.getRenteCreee().equals(false)) {
				sinistrte.setRenteCreee(true);
				sinistrte.setUserModificateur(dossierToUpdate.getRefsRentier().get(0).getUserSasModificateur());
				//WMOS: 12/11/2015 add date modification 
				sinistrte.setDateModification(new Date());
				(new SinistreManager()).modifyEntite(sinistrte);
				sinistreManager.gsrCreerMouvement(sinistrte, "Modification",
						IConstantes.MOTIF_CREATION_RENTE_GSR);
			}
		} else {
			sinistrte.setRenteCreee(true);
			sinistrte.setUserModificateur(dossierToUpdate.getRefsRentier().get(0).getUserSasModificateur());
			//WMOS: 12/11/2015 add date modification 
			sinistrte.setDateModification(new Date());
			(new SinistreManager()).modifyEntite(sinistrte);
			sinistreManager.gsrCreerMouvement(sinistrte, "Modification",
					IConstantes.MOTIF_CREATION_RENTE_GSR);
		}
		// envoyer la nouvelle reserve grave a L'ihm
		this.addResultItem(numeroRente);

	}

	protected Session getSession() throws ExceptionMetier {
		try {
			IPersistenceService dao = (IPersistenceService) ServicesFactory
					.getService(IPersistenceService.class);
			return (Session) dao.getSession();

		} catch (Exception e) {
			throw new ExceptionMetier(e.getMessage());
		}

	}

	public void setRentierHisto(Rentier tmp, Rentier cur)
			throws EntiteException {

		/*
		 * Historisation Rentier
		 */
		RentierHisto historique = new RentierHisto();
		historique.setIdRentier(tmp.getId());
		historique.setLienParente(tmp.getLienParente());
		historique.setNumeroCIN(tmp.getNumeroCIN());
		historique.setDateNaissance(tmp.getDateNaissance());
		if (tmp.getRefSituationRentier() != null) {
			historique.setNationalite(tmp.getRefSituationRentier().getId());
		}
		if (tmp.getRefNationalite() != null) {
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
//		Format forma = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
//		Date d = Calendar.getInstance().getTime();
//		String datjour = forma.format(d);
//		historique.setDateModification(TypeConverter.getInstance()
//				.stringToCalendar("dd/MM/yyyy HH:mm:ss", datjour));
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

	protected void creatQtc(Rentier rentier, Rentier rentierSas,
			String numeroRente) throws Exception {

		QuittanceGsrManager quittanceGsrManager = new QuittanceGsrManager();

		// on ajoute Prerglt si l'entité n'existe pas
		ModePayement modePayemet = rentier.getRefModePayement();
		Prerglt preRglt = new Prerglt();
		preRglt.setAdresse(modePayemet.getAdresse());
		preRglt.setCodePays(modePayemet.getPays());
		preRglt.setCodeVille(modePayemet.getVille());
		preRglt.setNumCIN(modePayemet.getNumeroCIN());
		preRglt.setNumRIB(modePayemet.getNumeroRIB());
		preRglt.setPourLeCompte(modePayemet.getBeneficiaire());
		PrergltManager prergltManager = new PrergltManager();
		List<Prerglt> listPreRglt = prergltManager.lookupEntite(preRglt);
		if (listPreRglt == null || listPreRglt.isEmpty()) {
			preRglt = (Prerglt) prergltManager.createEntite(preRglt);
		} else {
			preRglt = listPreRglt.get(0);
		}

		QuittanceGsr qtcGsr = new QuittanceGsr();
		// calcule exercice
		Calendar toDay = DateUtile.dateCalendarCourante();
		String str = toDay.get(Calendar.YEAR) + "t";
		int month = toDay.get(Calendar.MONTH) + 1;
		if (month < 4) {
			str += "1";
		} else if (month < 7) {
			str += "2";
		} else if (month < 10) {
			str += "3";
		} else {
			str += "4";
		}

		// insret qtc apres avoir mettre en reference le rentier, et le
		// PreReglemt ainsi les autres info qtcGsr.*
		qtcGsr.setDateDebutQtc(CommonGsrUtils.getInstance()
				.getDateDebutCurrentTrimestre());
		qtcGsr.setDateFinQtc(CommonGsrUtils.getInstance()
				.getDateFinCurrentTrimestre());
		/*
		 * Ano 454
		 * anomalie batch avec origine émission de la quittance sans date de positionnement.
		 * la date de positionnement doit être renseignée par la date validation de la rente
		 * pour la quittance de rente périodique généré à l'ouverture.
		 * 
		 */
		qtcGsr.setDatePositionnement(rentier.getDateValidation().getTime());
		
		// Evo modeReglement
		ModeReglement modeReglement = getModeReglement(rentier
				.getRefModePayement().getIdModePayement());
		qtcGsr.setRefModeReglement(modeReglement);
		// Fin
		qtcGsr.setExercice(str);
		qtcGsr.setRefPrerglt(preRglt);
		qtcGsr.setMontant(rentier.getMontantRenteTrimestrielle());
		qtcGsr.setRefRentier(rentier);
		qtcGsr.setNumeroSinistre(rentier.getRefDossierRente().getRefSinistre()
				.getNumeroSinistre());
		qtcGsr.setBeneficiaire(rentier.getNom() + " " + rentier.getPrenom());
		qtcGsr.setNumeroRente(numeroRente);
		qtcGsr.setClasse(Long.toString(rentier.getLienParente()));
		TypeQuittance typeQuittance = new TypeQuittance();
		typeQuittance.setId(eai.devass.gsr.appli.prm.TypeQuittance.Reglement
				.getId());
		qtcGsr.setRefTypeQuittance(typeQuittance);
		NatureQuittance natureQtcGsr = null;
		// Evo Nature Qtc
		natureQtcGsr = NatureQuittance.Rente_periodique;
		qtcGsr.setRefNatureQuittance(new NatureQtcGsr(natureQtcGsr.getId()));

		// Generation quittance QTC reglement
		QuittanceVO numQtc = quittanceGsrManager.generateQuittance(qtcGsr, "1");
		if (numQtc == null || CommonGsrUtils.isEmpty(numQtc.getNumQuittance())) {
			throw new ExceptionMetier("Le numéro de la quittance est null "
					+ "lors de la validation du rentier");
		}

		qtcGsr.setNumeroQuittance(numQtc.getNumQuittance());
		qtcGsr.setDatEmission(TypeConverter.getInstance().stringToCalendar(
				numQtc.getDatEmission()));
		qtcGsr.setDateCreation(TypeConverter.getInstance().stringToCalendar(
				numQtc.getDatEmission()));
		qtcGsr.setDatEtat(TypeConverter.getInstance().stringToCalendar(
				numQtc.getDatEtat()));
		EtatQtc etatQtc = new EtatQtc();
		etatQtc.setId(TypeConverter.getInstance().stringToLong(
				numQtc.getEtatQuittance()));
		qtcGsr.setRefEtatQtc(etatQtc);

		// Type approbation
		eai.devass.gsr.appli.prm.TypeApprobation approbation = eai.devass.gsr.appli.prm.TypeApprobation.Sans_approbation;
		if (CommonGsrUtils.getInstance().isTrue(
				rentier.getApprobationQuittance())) {
			approbation = eai.devass.gsr.appli.prm.TypeApprobation.Approuvee;
		}
		qtcGsr.setRefTypeApprobation(new TypeApprobation(approbation.getId()));

		// Type reglement
		qtcGsr.setRefTypeReglement(new TypeRgltGsr(TypeReglement.Direct.getId()));

		SituationQuittanceGsr situationQuittanceGsr = null;
		EtatQuittance etatQuittance = EtatQuittance.En_instance;
		situationQuittanceGsr = qtcGsr
				.getCurSituationQuittanceGsr(etatQuittance);
		situationQuittanceGsr.setOperateur(rentierSas.getUserSasModificateur());
		getSession().saveOrUpdate(qtcGsr);
		getSession().save(situationQuittanceGsr);

		// (new QuittanceGsrManager()).createEntite(qtcGsr);

	}

	protected void creatQtcEquilibre(Rentier rentier, String numeroRente,
			Rentier cur) throws Exception {
		
		//double mntEquilibre = 0;
		//mntEquilibre = rentier.getCapitalConstitutif();
		QuittanceGsrManager quittanceGsrManager = new QuittanceGsrManager();
		QuittanceGsr qtcGsr = new QuittanceGsr();
		qtcGsr.setMontant(rentier.getCapitalConstitutif());
		qtcGsr.setRefRentier(rentier);
		String operateur = cur.getUserSasModificateur();
		quittanceGsrManager.generateQuittanceEquilibre(qtcGsr, "1", operateur);

		// // cumul
		// Sinistre sinistre = getSinistre(rentier);
		// sinistre.setCumulReglementAnne(sinistre.getCumulReglementAnne()
		// + mntEquilibre);
		//
		// // Mouvement de modification du sinistre
		// try {
		// sinistreManager.gsrCreerMouvement(sinistre,
		// ContextRegleGestion.MODIFICATION.getContext(),
		// IConstantes.MOTIF_GSR_CHANGEMENT_CULULEREGLEMENT);
		//
		// } catch (FonctionnelleException e) {
		// throw new ExceptionMetier(
		// "Impossible de mettre à jour le cumule réglement"
		// + ", Un problème est survenue lors de la journalisation.");
		// }
	}

	private Sinistre getSinistre(Rentier rentier) throws ExceptionMetier {

		if (rentier.getRefDossierRente() == null) {
			throw new ExceptionMetier(
					"Impossible de mettre à jour la reserve grave"
							+ ", le dossier sinistre est obligatoire");
		}

		Sinistre sinistre = rentier.getRefDossierRente().getRefSinistre();
		if (sinistre == null) {
			throw new ExceptionMetier(
					"Impossible de mettre à jour la reserve grave"
							+ ", le dossier sinistre est obligatoire");
		}

		return sinistre;
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

	private ModeReglement getModeReglement(long idModeRgl) {
		if (idModeRgl == 6) {
			return new ModeReglement(
					eai.devass.gsr.appli.prm.ModeReglement.Cheque.getId());
		}
		if (idModeRgl == 3) {
			return new ModeReglement(
					eai.devass.gsr.appli.prm.ModeReglement.Virement.getId());
		}
		if (idModeRgl == 2) {
			return new ModeReglement(
					eai.devass.gsr.appli.prm.ModeReglement.Mandat.getId());
		}

		return null;
	}

	/**
	 * Methode qui retourne le lock mode du Use Case
	 * 
	 * @returns le lock mode
	 */
	/*
	 * public int getLockMode() { return LOCKMODE; }
	 */
}
