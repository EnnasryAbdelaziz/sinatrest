package eai.devass.gsr.appli.usecase.metier.dossierRente;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
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
import ma.co.omnidata.framework.services.entites.IEntite;
import org.hibernate.Query;
import org.hibernate.Session;

import eai.devass.gsr.appli.manager.metier.dossierRente.RentierManager;
import eai.devass.gsr.appli.modele.metier.dossierRente.DossierRente;
import eai.devass.gsr.appli.modele.metier.dossierRente.DossierRenteHisto;
import eai.devass.gsr.appli.modele.metier.dossierRente.Prothese;
import eai.devass.gsr.appli.modele.metier.dossierRente.Rentier;
import eai.devass.gsr.appli.modele.metier.dossierRente.RentierHisto;
import eai.devass.gsr.appli.modele.parametrage.EtatRentier;
import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.modele.metier.sinistre.AyantDroit;
import eai.devass.sinistreat.appli.modele.parametrage.CoefficientAge;

/**
 * Service d' ajout d' une entitÃ©
 * 
 * @author Nom Prenom (email)
 */
public class RentierAjouterUC extends FacadeServiceUseCase {

	/**
	 * Methode qui execute le Use case d' ajout d' une entite
	 * 
	 * @param entite
	 *            L' objet Ã  ajouter
	 * @param params
	 *            paramatere additionel qu 'on peut passer au Use Case
	 * @throws Exception
	 *             Probleme lors de l' execution du Use case
	 */

	protected void doExecuter(IValueObject entite, HashMap params)
			throws Exception {

		// Ici je recupere l' Objet provenant de l' IHM
		Rentier lToCreate = (Rentier) this.getItem(Rentier.class);

		// Ici Je persiste l' entite Rentier au niveau de la base de donnees
		if (lToCreate.getRefTuteur() != null) {
			((IEntite) lToCreate.getRefTuteur()).getFactory()
					.newEntiteManager().createEntite(lToCreate.getRefTuteur());
		}

		if (lToCreate.getRefDossierRente() != null && lToCreate.getRefDossierRente().getId() == 0) {
			((IEntite) lToCreate.getRefDossierRente()).getFactory().newEntiteManager().createEntite(lToCreate.getRefDossierRente());
		} else if (lToCreate.getRefDossierRente() != null && lToCreate.getRefDossierRente().getId() != 0) {
			((IEntite) lToCreate.getRefDossierRente()).getFactory().newEntiteManager().modifyEntite(lToCreate.getRefDossierRente());
		}
		Rentier lCreated = (Rentier) lToCreate.getFactory().newEntiteManager().createEntite(lToCreate);
		// Liste de tout les rentiers
		Rentier r = new Rentier();
		r.setRefDossierRente(new DossierRente());
		r.getRefDossierRente().setId(lToCreate.getRefDossierRente().getId());
		List<Rentier> listeRentier = new RentierManager().doGetRentierBySinistre(r, 0, 0);
		if (listeRentier != null) {

			// // calcul Rente (redistribution)
			for (Rentier rentier : listeRentier) {
				if (lToCreate.getLienParente() == 0) {

					if (rentier.getRefVictime().getId() == lToCreate.getRefVictime().getId() && rentier.getRefEtatRentier().getId() == Long.valueOf(16)) {
						rentier.setRefDossierRente(null);
						EtatRentier etat = new EtatRentier();
						etat.setId(Long.valueOf(14));
						rentier.setRefEtatRentier(etat);
						rentier.getFactory().newEntiteManager().modifyEntite(rentier);
					}
				} else {
					if (rentier.getRefAyantDroit() != null) {
						if (rentier.getRefVictime().getId() == lToCreate.getRefVictime().getId()&& rentier.getRefAyantDroit().getId() == lToCreate.getRefAyantDroit().getId() && rentier.getRefEtatRentier().getId() == Long.valueOf(16)) {
							rentier.setRefDossierRente(null);
							EtatRentier etat = new EtatRentier();
							etat.setId(Long.valueOf(14));
							rentier.setRefEtatRentier(etat);
							rentier.getFactory().newEntiteManager().modifyEntite(rentier);
						}
					}
				}

			}

			Rentier nouveau = lToCreate;

			listeRentier.add(nouveau);

			for (Rentier rentier : listeRentier) {
				if (lToCreate.getLienParente() == 0) {

					if (rentier.getRefVictime().getId() == lToCreate.getRefVictime().getId() && (rentier.getRefEtatRentier().getId() == Long.valueOf(5)	|| rentier.getRefEtatRentier().getId() == Long.valueOf(8) || rentier.getRefEtatRentier().getId() == Long.valueOf(9) || rentier.getRefEtatRentier().getId() == Long.valueOf(14))) {
						rentier.setRefDossierRente(null);
						EtatRentier etat = new EtatRentier();
						etat.setId(Long.valueOf(14));
						rentier.setRefEtatRentier(etat);
						rentier.getFactory().newEntiteManager().modifyEntite(rentier);
					}
				} else {
					if (rentier.getRefAyantDroit() != null) {
						if (rentier.getRefVictime().getId() == lToCreate.getRefVictime().getId() && rentier.getRefAyantDroit().getId() == lToCreate.getRefAyantDroit().getId()	&& (rentier.getRefEtatRentier().getId() == Long.valueOf(5)	|| rentier.getRefEtatRentier().getId() == Long.valueOf(8)	|| rentier.getRefEtatRentier().getId() == Long.valueOf(9) || rentier.getRefEtatRentier().getId() == Long.valueOf(14))) {
							rentier.setRefDossierRente(null);
							EtatRentier etat = new EtatRentier();
							etat.setId(Long.valueOf(14));
							rentier.setRefEtatRentier(etat);
							rentier.getFactory().newEntiteManager().modifyEntite(rentier);
						}
					}
				}
			}
			
			// calcul du taux de rente des ayant droits
			//La nouvelle loi pour tout sinistre survenu après le 22/01/2015 :
			if (lToCreate.getLienParente() !=0){
			String dateSurvenu = "22/01/2015";
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date dateSur = sdf.parse(dateSurvenu);
			if (lToCreate.getRefDossierRente().getRefSinistre().getRefEvenement().getDateAccident().compareTo(dateSur)<0){
				((RentierManager) lCreated.getFactory().newEntiteManager()).calculTauxRente(listeRentier);
				}else if (lToCreate.getRefDossierRente().getRefSinistre().getRefEvenement().getDateAccident().compareTo(dateSur)>=0) {
					((RentierManager) lCreated.getFactory().newEntiteManager()).calculTauxRenteR(listeRentier);
					}
			}
			//Fin RG		
			
			for (Rentier rentier : listeRentier) {
				if (rentier.getIppTauxRente() != null && rentier.getRenteConforme().equals(true) && rentier.getRefAyantDroit() != null && (rentier.getRefEtatRentier().getId()!= 14)) {
					Rentier rTomodify = rentier;
					Double Trimestrielle = 0D;
					Trimestrielle = calculRenteTrimestrielle(rTomodify);
					Trimestrielle = new BigDecimal(Trimestrielle).setScale(2,BigDecimal.ROUND_HALF_EVEN).doubleValue();
					rentier.setMontantRenteTrimestrielle(Trimestrielle);
					rentier.setCapitalConstitutif(getReserveGraveRentier(rentier));
					// rTomodify.getFactory().newEntiteManager()
					// .modifyEntite(rentier);
					//
				}
			}
		}
		// listeRentier = null;
		if (lToCreate.getRefsProtheses() != null
				&& !lToCreate.getRefsProtheses().isEmpty()) {
			List<Prothese> list = lToCreate.getRefsProtheses();
			for (Prothese p : list) {
				p.setRefRentier(lCreated);
				p.getFactory().newEntiteManager().createEntite(p);
			}
		}

		if (lToCreate.getRefModePayement() != null) {
			lToCreate.getRefModePayement().setRefRentier(lCreated);
			((IEntite) lToCreate.getRefModePayement()).getFactory().newEntiteManager().createEntite(lToCreate.getRefModePayement());
		}
		setRentierHisto(lCreated);
		setDossierRenteHisto(lCreated);
		// creatQtc(lCreated);

		// Session session = (Session) dao.getSession();
		// session.flush();
		this.addResultItem(lCreated);

	}

	protected void setDossierRenteHisto(Rentier lCreated)
			throws EntiteException {

		DossierRenteHisto historique = new DossierRenteHisto();

		if (lCreated.getRefDossierRente() != null) {

			historique.setIdRente(lCreated.getRefDossierRente().getId());
			historique.setCompagnieRente(lCreated.getRefDossierRente().getCompagnieRente());
			historique.setNumeroRente(lCreated.getRefDossierRente().getNumeroRente());
			if (lCreated.getRefDossierRente().getRefEtatRentier() != null) {
				historique.setEtat(lCreated.getRefDossierRente().getRefEtatRentier().getId());
			}
			historique.setDateEtat(lCreated.getRefDossierRente().getDateEtat());
			historique.setDateValidation(lCreated.getRefDossierRente().getDateValidation());
			historique.setValidation(lCreated.getRefDossierRente().getValidation());
			historique.setDateCreation(lCreated.getRefDossierRente().getDateCreation());
			historique.setRefSinistre(lCreated.getRefDossierRente().getRefSinistre().getId());
			historique.setUserSasCreateur(lCreated.getRefDossierRente().getUserSasCreateur());
			historique.setUserSasModificateur(lCreated.getRefDossierRente().getUserSasModificateur());
			historique.setDateModification(lCreated.getRefDossierRente().getDateModification());
		}
		historique.getFactory().newEntiteManager().createEntite(historique);
	}

	protected void setRentierHisto(Rentier lCreated) throws EntiteException {

		RentierHisto historique = new RentierHisto();
		historique.setIdRentier(lCreated.getId());
		historique.setLienParente(lCreated.getLienParente());
		historique.setNumeroCIN(lCreated.getNumeroCIN());
		historique.setDateNaissance(lCreated.getDateNaissance());
		if (lCreated.getRefSituationRentier() != null) {
			historique.setNationalite(lCreated.getRefSituationRentier().getId());
		}
		if (lCreated.getRefNationalite() != null) {
			historique.setNationalite(lCreated.getRefNationalite().getCode());
		}
		historique.setNumeroTelephone(lCreated.getNumeroTelephone());
		historique.setNumeroGSM(lCreated.getNumeroGSM());
		historique.setEmail(lCreated.getEmail());
		historique.setAdresse(lCreated.getAdresse());
		historique.setCodePostale(lCreated.getCodePostale());
		historique.setVille(lCreated.getVille());
		historique.setPays(lCreated.getPays());
		historique.setProthese(lCreated.getProthese());
		historique.setRentierARisque(lCreated.getRentierARisque());
		historique.setRenteConforme(lCreated.getRenteConforme());
		historique.setDateEtat(lCreated.getDateEtat());
		historique.setMotifEtat(lCreated.getMotifEtat());
		historique.setValidation(lCreated.getValidation());
		historique.setCapitalConstitutif(lCreated.getCapitalConstitutif());
		historique.setDateConstitution(lCreated.getDateConstitution());
		historique.setDateDepartRente(lCreated.getDateDepartRente());
		historique.setIppTauxRente(lCreated.getIppTauxRente());
		historique.setReserveMathematique(lCreated.getReserveMathematique());
		historique.setDateValidation(lCreated.getDateValidation());
		historique.setSalaireUtile(lCreated.getSalaireUtile());
		historique.setApprobationQuittance(lCreated.getApprobationQuittance());
		historique.setMontantRenteTrimestrielle(lCreated.getMontantRenteTrimestrielle());
		historique.setPeriodicite(lCreated.getPeriodicite());
		historique.setTuteur(lCreated.getTuteur());
		historique.setOrphelinPur(lCreated.getOrphelinPur());
		historique.setCivilite(lCreated.getCivilite());
		historique.setNom(lCreated.getNom());
		historique.setPrenom(lCreated.getPrenom());
		historique.setSexe(lCreated.getSexe());
		historique.setObservationConforme(lCreated.getObservationConforme());
		historique.setDateCreation(lCreated.getDateCreation());
		//historique.setDateModification(lCreated.getDateCreation());
		historique.setUserSas(lCreated.getUserSas());

		/**
		 * EVO Lot1
		 */
		historique.setArrerageAvantConstitution(lCreated.getArrerageAvantConstitution());
		historique.setArrerages(lCreated.getArrerages());
		historique.setUserSasModificateur(lCreated.getUserSasModificateur());
		historique.setDonneeConforme(lCreated.getDonneeConforme());
		historique.setObservationDonneeConforme(lCreated.getObservationDonneeConforme());
		/**
		 * Fin EVO
		 */
		if (lCreated.getRefAyantDroit() != null) {
			historique.setRefAyantDroit(lCreated.getRefAyantDroit().getId());
		}
		if (lCreated.getRefDossierRente() != null) {
			historique.setRefDossierRente(lCreated.getRefDossierRente().getId());
		}
		if (lCreated.getRefEtatRentier() != null) {
			historique.setRefEtatRentier(lCreated.getRefEtatRentier().getId());
		}
		if (lCreated.getRefModePayement() != null) {
			historique.setRefModePayement(lCreated.getRefModePayement().getId());
		}
		if (lCreated.getRefTuteur() != null) {
			historique.setRefTuteur(lCreated.getRefTuteur().getId());
		}
		if (lCreated.getRefVictime() != null) {
			historique.setRefVictime(lCreated.getRefVictime().getId());
		}

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

	private Double calculRenteTrimestrielle(Rentier r) {
		Double rt = new Double(0);
		Date dateAccident = r.getRefDossierRente().getRefSinistre().getRefEvenement().getDateAccident();
		Date dateCompare;
		try {
			dateCompare = new SimpleDateFormat("dd/MM/yyyy").parse("19/11/2002");

			Date dateCompare1;
			dateCompare1 = new SimpleDateFormat("dd/MM/yyyy").parse("18/06/2003");

			Long classe = r.getLienParente();

			List<AyantDroit> listAyantDroit = r.getRefDossierRente().getRefSinistre().getRefVictime().getListAyantDroit();

			if (listAyantDroit != null) {
				for (int i = 0; i < listAyantDroit.size(); i++) {
					if (listAyantDroit.get(i).getId() == (r.getRefAyantDroit().getId())) {
						Double ipp = listAyantDroit.get(i).getTauxIPP();
						r.getRefAyantDroit().setTauxIPP(ipp);
					}
				}
			}
			Double ipp = r.getRefAyantDroit().getTauxIPP();
			Double su = r.getRefDossierRente().getRefSinistre().getRefVictime().getSalaireUtile();

			double ippReduit = 0;

			// Début Calcul IPP réduit

			if (classe == 0) {
				if (dateAccident == null || dateCompare.compareTo(dateAccident) >= 0) {
					if (ipp > 50) {
						ippReduit = ((ipp * 1.5) - 50);
					} else {
						ippReduit = ipp / 2;
					}
				} else {
					if (dateCompare.compareTo(dateAccident) <= 0 && dateCompare1.compareTo(dateAccident) >= 0) {
						ippReduit = ipp;
					} else {
						if (dateCompare.compareTo(dateAccident) < 0) {
							if (ipp < 30) {
								ippReduit = ipp / 2;
							} else {
								if (ipp >= 30 && ipp <= 50) {
									ippReduit = ((ipp * 1.5) - 30);
								} else {
									if (ipp > 50) {
										ippReduit = ipp - 5;
									}
								}
							}
						}
					}
				}
			} else {
				ippReduit = ipp;
			}

			// Fin Calcul IPP réduit

			// Début Calcul Rente trimestrielle

			rt = (su * (ippReduit / 100)) / 4;

			// Fin Calcul Rente trimestrielle
		} catch (ParseException e) {
			// // TODO Auto-generated catch block
			// logger.error("problème technique",e);
		}

		return rt;
	}

	private IPersistenceService dao = (IPersistenceService) ServicesFactory.getService(IPersistenceService.class);
	private DateFormat dateFormaty = new SimpleDateFormat("yyyyMMdd");

	public int getAge(Rentier rentier) {
		int age = 0;
		if (rentier.getDateNaissance() != null && rentier.getDateConstitution() != null) {
			Date dateNaissance = (rentier.getDateNaissance()).getTime();
			Calendar naissance = Calendar.getInstance();
			naissance.setTime(dateNaissance);

			Date dateConstitution = (rentier.getDateConstitution()).getTime();
			Calendar constitution = Calendar.getInstance();
			constitution.setTime(dateConstitution);

			// Calendar today = Calendar.getInstance();

			age = constitution.get(Calendar.YEAR) - naissance.get(Calendar.YEAR);
			constitution.add(Calendar.YEAR, -age);
			if (naissance.after(constitution)) {
				age = age - 1;
			}
		}

		return (age >= 0) ? age : 0;
	}

	public CoefficientAge getCoefParType(Integer age, String type) throws Exception {

		Session sessionH = (Session) dao.getSession();
		if (age == null || type == null) {
			throw new Exception("l'objet age/type en entrée ne peut être null");
		}
		String sql = " from CoefficientAge where type='" + type + "' and  age=" + age + "";
		Query q = sessionH.createQuery(sql);

		try {
			if (!q.list().isEmpty()) {
				return (CoefficientAge) q.list().get(0);
			}
		} catch (Exception e) {
			// logger.error("problème technique",e);
		}

		return null;
	}

	public CoefficientAge getCoefParRentier(Rentier rentier) throws Exception {

		CoefficientAge cofAge = null;
		if (rentier == null) {
			return null;
			// throw new
			// Exception("l'objet sinistre en entrée ne peut être null");
		}
		if (rentier.getRefDossierRente().getRefSinistre().getRefEvenement() == null) {
			return null;
			// throw new
			// Exception("l'objet sinistre en entrée ne peut être null");
		}
		if (rentier.getRefDossierRente().getRefSinistre().getRefEvenement().getDateAccident() == null) {
			return null;
			// throw new
			// Exception("l'objet sinistre en entrée ne peut être null");
		}

		long codeDegre = 1;
		try {
			codeDegre = rentier.getLienParente();
		} catch (Exception e) {
			codeDegre = 1;
		}
		String code = "2";
		// La date repère du calcul de la reserve grave est 02/12/2010 
		if (rentier.getRefDossierRente().getRefSinistre().getRefEvenement().getDateAccident().before(dateFormaty.parse("20101202"))) {

			
			if (codeDegre == 0) {
				code = "2";
			} else if (codeDegre >= 1 && codeDegre < 14) {
				code = "2";
			} else if (codeDegre >= 20) {
				code = "4";
			}
			// La date repère du calcul de la reserve grave est 02/12/2010 
		} else if (rentier.getRefDossierRente().getRefSinistre().getRefEvenement().getDateAccident().compareTo(dateFormaty.parse("20101202"))>=0 && rentier.getRefDossierRente().getRefSinistre().getRefEvenement().getDateAccident().compareTo(dateFormaty.parse("20150122"))<0){
			
			if (codeDegre == 0) {
				code = "2";
			} else if (codeDegre >= 1 && codeDegre < 10) {
				code = "2";
			} else if (codeDegre >= 10 && codeDegre < 14) {
				code = "5";
			} else if (codeDegre >= 20) {
				code = "4";// ou 6
			}

		}
		else if(rentier.getRefDossierRente().getRefSinistre().getRefEvenement().getDateAccident().compareTo(dateFormaty.parse("20150122"))>=0){
			
			if (codeDegre >= 0 && codeDegre < 10) {
				code = "7";
			} else if ((codeDegre >= 10 && codeDegre < 14) || (codeDegre >= 60 && codeDegre < 70)) {
				code = "8";
			} else if (codeDegre >= 70) {
				code = "9";
			} else if (codeDegre >= 20 && codeDegre < 60) {
				if (rentier.getRefSituationRentier().getId()==3) {
					code = "11";
				}else{
					code = "10";
				}
			} 
					
		}

		cofAge = getCoefParType(getAge(rentier), code);

		return cofAge;
	}

	public Double getCoefAgeRentier(Rentier rentier) throws Exception {
		if (rentier == null) {
			return Double.valueOf(0);
		}

		CoefficientAge cofage = getCoefParRentier(rentier);
		if (cofage != null) {
			return cofage.getCoefficient();
		} else {
			return Double.valueOf(19.691);
		}
	}

	public Double getReserveGraveRentier(Rentier rentier) throws Exception {
		if (rentier == null) {
			return Double.valueOf(0);
		}
		Double coefAge = getCoefAgeRentier(rentier);
		Double rente = calculRenteTrimestrielle(rentier);

		int v = 4;
		Double reserve = rente * v * coefAge;
		reserve = new BigDecimal(reserve).setScale(2,BigDecimal.ROUND_HALF_EVEN).doubleValue();

		return reserve;
	}
}