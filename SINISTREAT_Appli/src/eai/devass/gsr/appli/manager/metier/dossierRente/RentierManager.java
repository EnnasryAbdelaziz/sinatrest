package eai.devass.gsr.appli.manager.metier.dossierRente;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import ma.co.omnidata.framework.services.core.ServicesFactory;
import ma.co.omnidata.framework.services.dao.IPersistenceService;
import ma.co.omnidata.framework.services.dao.PersistenceException;
import ma.co.omnidata.framework.services.entites.EntiteException;
import ma.co.omnidata.framework.services.entites.IEntite;
import ma.co.omnidata.framework.services.entites.IEntiteManager;
import ma.co.omnidata.framework.services.entites.ProcessEntiteException;
import ma.co.omnidata.framework.services.entites.ValidateEntiteException;
import ma.co.omnidata.framework.utile.DateUtile;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.hibernate.Session;

import eai.devass.commun.appli.exeptions.ExceptionMetier;
import eai.devass.gsr.appli.manager.CommunManager;
import eai.devass.gsr.appli.modele.metier.dossierRente.DossierRente;
import eai.devass.gsr.appli.modele.metier.dossierRente.Prothese;
import eai.devass.gsr.appli.modele.metier.dossierRente.Rentier;
import eai.devass.gsr.appli.modele.metier.dossierRente.Tuteur;
import eai.devass.gsr.appli.modele.metier.mouvements.Mouvement;
import eai.devass.gsr.appli.modele.metier.reglement.ModePayement;
import eai.devass.gsr.appli.modele.parametrage.EtatMvt;
import eai.devass.gsr.appli.modele.parametrage.EtatRentier;
import eai.devass.gsr.appli.modele.parametrage.MvtEtatRentier;
import eai.devass.gsr.appli.prm.TypeMouvementGsr;
import eai.devass.gsr.appli.utile.IMessageException;
import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.manager.sinistre.SinistreManager;
import eai.devass.sinistreat.appli.modele.metier.sinistre.AyantDroit;
import eai.devass.sinistreat.appli.modele.metier.sinistre.Sinistre;
import eai.devass.sinistreat.appli.modele.metier.sinistre.Victime;
import eai.devass.sinistreat.appli.modele.parametrage.Intermediaire;
import eai.devass.sinistreat.appli.utils.Fonctions;

/**
 * Manager de l ' entit√© Rentier
 * 
 * @author Nom Prenom (email)
 */

@SuppressWarnings("unchecked")
public class RentierManager extends CommunManager {

	private final int DEUX = 2;
	private final int TROIS = 3;
	private final int QUATRE = 4;
	private final int DIX = 10;
	private final int QUINZE = 15;
	private final int VINGT = 20;
	private final int TRENTE = 30;
	private final int CINQUANTE = 50;
	private final int SOIXANTE = 60;
	private final int QUATRE_VINGT_CINQ = 85;

	/**
	 * Completude de l ' objet avant sa creation
	 * 
	 * @param entite
	 *            lentit√© √† completer
	 * @return returns lentit√© complet√©
	 * @throws ProcessEntiteException
	 *             probl√®me lors de la compl√©tude de l' entit√©
	 * @throws EntiteException
	 */
	protected IEntite doProcessCreateEntity(IEntite entite)
			throws ProcessEntiteException {
		Rentier rentier = (Rentier) entite;

		rentier.setDateCreation(DateUtile.dateCalendarCourante());

		return rentier;

	}

	/**
	 * Validation de l'object avant sa suppression
	 * 
	 * @param entite
	 *            l' entit√© √† valider avant sa suppression
	 * @throws ValidateEntiteException
	 *             Probleme lors de la validation de l 'entit√©
	 * @throws EntiteException
	 *             Probleme lors de la persistence
	 */
	protected void doValidateDeleteEntity(IEntite entite)
			throws ValidateEntiteException {
	}

	/**
	 * Validation de l'object avant sa creation
	 * 
	 * @param entite
	 *            l' entit√© √† valider avant sa creation
	 * @throws ValidateEntiteException
	 *             Probleme lors de la validation de l 'entit√©
	 * @throws EntiteException
	 *             Probleme lors de la persistence
	 */
	protected void doValidateCreateEntity(IEntite entite)
			throws ValidateEntiteException {

	}

	/**
	 * Validation de l' objet avant sa modification
	 * 
	 * @param entite
	 *            L 'entit√© √† valider avant sa modification
	 * @throws ValidateEntiteException
	 *             Probleme lors de la validation de l 'entit√©
	 * @throws EntiteException
	 *             Probleme lors de la persistence
	 */
	protected void doValidateModifyEntity(IEntite entite)
			throws ValidateEntiteException {

	}

	/**
	 * Methode qui habille une entit√© avec ces entit√©s refs completes pou ne
	 * pas ecraser les valeurs des refs avec des nulls
	 * 
	 * @param entite
	 *            lentit√© √† habiller
	 * @throws Exception
	 */
	protected IEntite habiller(IEntite entite) {
		Rentier rentier = (Rentier) entite;
		try {
			// rentier.setMotifEtat(new MotifEtat());
			// rentier.getMotifEtat().setId(1);
			EtatRentier etat = new EtatRentier();
			if (rentier.getRefEtatRentier().getCode() == 3
					|| rentier.getRefEtatRentier().getCode() == 2) {
				etat.setId(2);
				// rentier.setMotifEtat("".trim());
			} else {
				if (rentier.getRefEtatRentier().getCode() == 4) {
					etat.setId(4);
				} else {
					if (rentier.getRefEtatRentier().getCode() == 16) {
						etat.setId(16);
					}
					if (rentier.getRefEtatRentier().getCode() == 14) {
						etat.setId(14);
					}
					if (rentier.getRefEtatRentier().getCode() == 9) {
						etat.setId(9);
					}
					if (rentier.getRefEtatRentier().getCode() == 8) {
						etat.setId(8);
					}
					if (rentier.getRefEtatRentier().getCode() == 5) {
						etat.setId(5);
					}
					if (rentier.getRefEtatRentier().getCode() == 1) {
						etat.setId(1);
					}
				}

			}
			rentier.setRefEtatRentier(etat);
			Victime refVictime1 = rentier.getRefVictime();
			Victime refVictime = new Victime();

			if (refVictime1 != null) {
				((IEntite) refVictime).setId(refVictime1.getId());
			}
			if (refVictime != null && refVictime1 != null) {
				if (((IEntite) refVictime).getId() != 0) {

					refVictime = new SinistreManager()
							.getVictimeById(refVictime.getId());

					rentier.setRefVictime(refVictime);
				} else {
					rentier.setRefVictime(refVictime1);
				}
			}

			AyantDroit ad1 = rentier.getRefAyantDroit();
			AyantDroit ad = new AyantDroit();

			if (ad1 != null) {
				((IEntite) ad).setId(ad1.getId());
			}
			if (ad != null && ad1 != null) {
				if (((IEntite) ad).getId() != 0) {
					ad = new SinistreManager().getAyantDroitById(ad.getId());

					rentier.setRefAyantDroit(ad);
				} else {
					rentier.setRefAyantDroit(ad1);
				}
			}
			Tuteur refTuteur1 = rentier.getRefTuteur();
			Tuteur refTuteur = new Tuteur();

			if (refTuteur1 != null) {
				((IEntite) refTuteur).setId(refTuteur1.getId());
			}
			if (refTuteur != null && refTuteur1 != null) {
				if (((IEntite) refTuteur).getId() != 0) {
					refTuteur = (Tuteur) (new Tuteur()).getFactory()
							.newEntiteManager().getEntite((Tuteur) refTuteur);
					if (refTuteur != null) {
						rentier.setRefTuteur(refTuteur);
					}
				} else {
					rentier.setRefTuteur(refTuteur1);
				}
			}
			EtatRentier refEtatRentier1 = rentier.getRefEtatRentier();
			EtatRentier refEtatRentier = new EtatRentier();
			if (refEtatRentier1 != null) {
				((IEntite) refEtatRentier).setId(refEtatRentier1.getId());
			}
			if (refEtatRentier != null && refEtatRentier1 != null) {
				if (((IEntite) refEtatRentier).getId() != 0) {
					refEtatRentier = (EtatRentier) (new EtatRentier())
							.getFactory().newEntiteManager()
							.getEntite((EtatRentier) refEtatRentier1);
					rentier.setRefEtatRentier(refEtatRentier);
				} else {
					rentier.setRefEtatRentier(refEtatRentier1);
				}
			}
			// }

			/*
			 * MotifEtat refMotifEtat1 = rentier.getRefMotifEtat(); MotifEtat
			 * refMotifEtat = new MotifEtat();
			 * 
			 * if (refMotifEtat1 != null) ((IEntite)
			 * refMotifEtat).setId(refMotifEtat1.getId()); if (refMotifEtat !=
			 * null && refMotifEtat1 != null) { if (((IEntite)
			 * refMotifEtat).getId() != 0) { refMotifEtat = (MotifEtat) (new
			 * MotifEtat()).getFactory()
			 * .newEntiteManager().getEntite((MotifEtat) refMotifEtat);
			 * rentier.setRefMotifEtat(refMotifEtat); } else {
			 * rentier.setRefMotifEtat(refMotifEtat1); } }
			 */
			ModePayement refModePayement = new ModePayement();
			ModePayement refModePayement1 = rentier.getRefModePayement();
			if (refModePayement1 != null) {
				((IEntite) refModePayement).setId(refModePayement1.getId());
			}
			if (refModePayement != null && refModePayement1 != null) {
				if (((IEntite) refModePayement).getId() != 0) {
					refModePayement = (ModePayement) (new ModePayement())
							.getFactory().newEntiteManager()
							.getEntite((ModePayement) refModePayement1);
					if (refModePayement != null) {
						rentier.setRefModePayement(refModePayement);
					}

				} else {
					rentier.setRefModePayement(refModePayement1);
				}
			}
			// }
			List<IEntite> refsProtheses = rentier.getRefsProtheses();
			if (refsProtheses != null) {
				List<IEntite> refsProthesesResult = new ArrayList<IEntite>();
				IEntiteManager prothesesManager = (new Prothese()).getFactory()
						.newEntiteManager();
				for (int i = 0; i < refsProtheses.size(); i++) {
					if (((IEntite) refsProtheses.get(i)).getId() != 0) {
						refsProthesesResult.add(prothesesManager
								.getEntite((IEntite) refsProtheses.get(i)));
					} else {
						refsProthesesResult.add(refsProtheses.get(i));
					}
				}
				rentier.setRefsProtheses(refsProthesesResult);
			}

			DossierRente refDossierRente1 = rentier.getRefDossierRente();
			DossierRente refDossierRente = new DossierRente();

			if (refDossierRente != null && refDossierRente1 != null) {
				if (((IEntite) refDossierRente).getId() != 0) {
					refDossierRente = (DossierRente) (new DossierRente())
							.getFactory().newEntiteManager()
							.getEntite((DossierRente) refDossierRente1);
					if (refDossierRente != null) {
						rentier.setRefDossierRente(refDossierRente);
					}

				} else {
					rentier.setRefDossierRente(refDossierRente1);
				}
			}

		} catch (Exception e) {
			// // TODO Auto-generated catch block
			// logger.error("problËme technique",e);
		}
		return rentier;
	}

	/**
	 * Methode qui habille l' entit√© avant sa modification
	 * 
	 * @param entite
	 *            L 'entite √† habiller
	 * @return l' entit√© habill√©
	 * @throws ProcessEntiteException
	 *             probl√®me lors de la compl√©tude de l' entit√©
	 * @throws EntiteException
	 *             Probleme lors de la persistence
	 */
	protected IEntite doRevampModifyEntity(IEntite entite)
			throws ProcessEntiteException {
		try {
			return this.habiller(entite);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// logger.error("problËme technique",e);
		}
		return null;
	}

	/**
	 * Methode qui habille l' entit√© avant sa creation
	 * 
	 * @param entite
	 *            L 'entite √† habiller
	 * @return l' entit√© habill√©
	 * @throws ProcessEntiteException
	 *             probl√®me lors de la compl√©tude de l' entit√©
	 * @throws EntiteException
	 *             Probleme lors de la persistence
	 */
	protected IEntite doRevampCreateEntity(IEntite entite) {
		try {
			return this.habiller(entite);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// logger.error("problËme technique",e);
		}
		return null;
	}

	/**
	 * Completude de l' objet avant sa modification
	 * 
	 * @param entite
	 *            L' entit√© √† completer avant sa modification
	 * @returns L' entit√© complet√©
	 * @throws ProcessEntiteException
	 *             probl√®me lors de la compl√©tude de l' entit√©
	 * @throws EntiteException
	 *             Probleme lors de la persistence
	 */
	protected IEntite doProcessModifyEntity(IEntite entite)
			throws ProcessEntiteException {
		Rentier rentier = (Rentier) entite;

		return rentier;

	}

	public List<Rentier> doGetRentierBySinistre(Rentier rentier, int numPage,
			int pageSize) throws ProcessEntiteException, PersistenceException,
			Exception {

		IPersistenceService dao = (IPersistenceService) ServicesFactory
				.getService(IPersistenceService.class);
		String hql = getListRentierQuery(rentier);
		if (rentier.getLienParente() != null
				&& rentier.getLienParente().equals(0L)) {
			hql += " order by id ";
		} else {
			hql += " order by refAyantDroit.id";
		}

		Query query = ((Session) dao.getSession()).createQuery(hql);
		if (numPage > 0) {
			query.setFirstResult((numPage - 1) * pageSize).setMaxResults(
					pageSize);
		}
		// List<Rentier> res = query.list();
		return query.list();

		// Rentier rentier = (Rentier) entite;
		// TransverseManager transverseManager = new TransverseManager();
		// List<Rentier> result = transverseManager.getSimilarObject(rentier,
		// "id");
		// return result;
	}

	private String getListRentierQuery(Rentier rentier)
			throws ProcessEntiteException, PersistenceException {

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		String hql = "from Rentier  this where 1=1";
		// String hql =
		// "from Rentier  this where 1=1 and this.validation != '1'";

		if (rentier.getId() != 0) {
			hql += " and this.id= '" + rentier.getId() + "'";
		}

		if (rentier.getNom() != null && !"".equals(rentier.getNom())) {
			hql += " and this.nom like '%" + rentier.getNom() + "%'";
		}

		if (rentier.getPrenom() != null && !"".equals(rentier.getPrenom())) {
			hql += " and this.prenom like '%" + rentier.getPrenom() + "%'";
		}

		if (rentier.getNumeroCIN() != null
				&& !"".equals(rentier.getNumeroCIN())) {
			hql += " and this.numeroCIN like '%" + rentier.getNumeroCIN()
					+ "%'";
		}
		if (rentier.getDateConstitution() != null) {
			hql += " and this.dateConstitution = to_date('"
					+ dateFormat
							.format(rentier.getDateConstitution().getTime())
					+ "', 'dd/MM/yyyy')";
		}

		if (rentier.getLienParente() != null) {
			hql += " and this.lienParente = " + rentier.getLienParente();
		}

		if (rentier.getRefDossierRente() != null) {
			if (rentier.getRefDossierRente().getNumeroRente() != null) {
				hql += " and this.refDossierRente.numeroRente = "
						+ rentier.getRefDossierRente().getNumeroRente();
			}
			if (rentier.getRefDossierRente().getNumeroOrdre() != null) {
				hql += " and this.refDossierRente.numeroOrdre = "
						+ rentier.getRefDossierRente().getNumeroOrdre();
			}
			if (rentier.getRefDossierRente().getId() != 0) {
				hql += " and this.refDossierRente.id = "
						+ rentier.getRefDossierRente().getId();
			}
			if (Fonctions.isNotEmpty(rentier.getRefDossierRente()
					.getCompagnieRente())) {
				hql += " and this.refDossierRente.compagnieRente='"
						+ rentier.getRefDossierRente().getCompagnieRente()
						+ "'";
			}

			if (rentier.getRefDossierRente().getRefSinistre() != null) {

				Sinistre refSinistre = rentier.getRefDossierRente()
						.getRefSinistre();

				// if (Fonctions.isNotEmpty(refSinistre.getNumeroSinistre())) {
				// hql +=
				// " and this.refDossierRente.refSinistre.numeroSinistre like '%"
				// + refSinistre.getNumeroSinistre() + "%'";
				// }

				// numSinistre
				if (!StringUtils.isEmpty(refSinistre.getNumeroSinistre())
						&& !StringUtils.isEmpty(refSinistre.getNumeroSinistre()
								.replaceAll(" ", ""))) {
					hql += " and this.refDossierRente.refSinistre.numeroSinistre like '%"
							+ ((refSinistre.getNumeroSinistre().trim())
									.replaceAll(" ", "")) + "%'";
				}

				if (refSinistre.getRefEvenement() != null) {
					if (refSinistre.getRefEvenement().getDateAccident() != null) {
						hql += " and this.refDossierRente.refSinistre.refEvenement.dateAccident = to_date('"
								+ dateFormat.format(refSinistre
										.getRefEvenement().getDateAccident()
										.getTime()) + "', 'dd/MM/yyyy')";
					}
				}
			}
		}

		return hql;
	}

	public int doGetNombreDossierRent(Rentier rentier)
			throws ProcessEntiteException, PersistenceException {
		String hql = "Select count(id) " + getListRentierQuery(rentier);
		IPersistenceService dao = (IPersistenceService) ServicesFactory
				.getService(IPersistenceService.class);
		Query query = ((Session) dao.getSession()).createQuery(hql);
		return ((Long) query.uniqueResult()).intValue();
	}

	public int doUpdateEtatRentier(Rentier rentier) throws PersistenceException {
		String hql = "update Rentier r set r.refEtatRentier.id=?, r.dateEtat=?";
		if (rentier.getRefEtatRentier().getId() == QUATRE) {
			hql += ", r.dateValidation=? , r.validation=?";
		}
		if (Fonctions.isNotEmpty(rentier.getMotifEtat())) {
			hql += ", r.motifEtat=?";
		}
		hql += " where r.id=?";
		IPersistenceService dao = (IPersistenceService) ServicesFactory
				.getService(IPersistenceService.class);
		Query query = ((Session) dao.getSession()).createQuery(hql);
		query.setLong(0, rentier.getRefEtatRentier().getId());
		Date toDaye = new Date();
		query.setDate(1, toDaye);
		short i = 2;
		if (rentier.getRefEtatRentier().getId() == QUATRE) {
			query.setDate(2, toDaye);
			query.setBoolean(TROIS, true);
			i = QUATRE;
		}

		if (Fonctions.isNotEmpty(rentier.getMotifEtat())) {
			query.setString(i, rentier.getMotifEtat());
			i++;
		}
		query.setLong(i, rentier.getId());
		return query.executeUpdate();
	}

	public List calculTauxRente(List<Rentier> listRentier) {
		int nbrAsc = 0;
		int nbrCjn = 0;
		int nbrDsc = 0;
		Double totTauxAsc = Double.valueOf(0);
		Double totTauxCjn = Double.valueOf(0);
		Double totTauxDsc = Double.valueOf(0);
		Calendar curr = Calendar.getInstance();

		for (int i = 0; i < listRentier.size(); i++) {
			// Calcul age
			Rentier rentier = (Rentier) listRentier.get(i);
			int age = curr.get(Calendar.YEAR)
					- rentier.getDateNaissance().get(Calendar.YEAR);
			curr.add(Calendar.YEAR, -age);
			if (rentier.getDateNaissance().after(curr)) {
				age = age - 1;
			}
			// calcul age
			long code = 1;
			try {
				code = rentier.getLienParente();
			} catch (Exception e) {
				code = 1;
			}

			if (code >= 1 && code < 10) {
				nbrAsc++;
				if (nbrAsc >= TROIS) {
					totTauxAsc = Double.valueOf(TRENTE);
				} else {
					totTauxAsc += Double.valueOf(DIX);
				}
			} else if (code >= 10 && code < 14) {// Conjoint
				nbrCjn++;
				if (totTauxCjn != CINQUANTE) {
					if (age < SOIXANTE) {
						totTauxCjn = Double.valueOf(TRENTE);
					} else {
						totTauxCjn = Double.valueOf(CINQUANTE);
					}
				}
			} else if (code >= 20) {// Descendant
				nbrDsc++;
				if (rentier.getOrphelinPur().equals(true)) {
					totTauxDsc += Double.valueOf(VINGT);
				} else if (nbrDsc < TROIS) {
					totTauxDsc += Double.valueOf(QUINZE);
				} else {
					totTauxDsc += Double.valueOf(DIX);
				}
			}
		}
		Double totTaux = totTauxAsc + totTauxCjn + totTauxDsc;
		Double coefTotTaux = Double.valueOf(0);
		if (totTaux <= QUATRE_VINGT_CINQ) {
			coefTotTaux = Double.valueOf(1);
		} else {
			coefTotTaux = QUATRE_VINGT_CINQ / totTaux;
		}

		for (int i = 0; i < listRentier.size(); i++) {
			Rentier rentier = (Rentier) listRentier.get(i);
			long code = 1;
			try {
				code = rentier.getLienParente();
			} catch (Exception e) {
				code = 1;
			}
			if (code >= 1 && code < 10) {
				Double tauxRente = (totTauxAsc / nbrAsc) * coefTotTaux;
				tauxRente = new BigDecimal(tauxRente).setScale(2,
						BigDecimal.ROUND_HALF_EVEN).doubleValue();
				rentier.setIppTauxRente(tauxRente);
			} else if (code >= 10 && code < 14) {
				Double tauxRente = (totTauxCjn / nbrCjn) * coefTotTaux;
				tauxRente = new BigDecimal(tauxRente).setScale(2,
						BigDecimal.ROUND_HALF_EVEN).doubleValue();
				rentier.setIppTauxRente(tauxRente);
			} else if (code >= 20) {
				Double tauxRente = (totTauxDsc / nbrDsc) * coefTotTaux;
				tauxRente = new BigDecimal(tauxRente).setScale(2,
						BigDecimal.ROUND_HALF_EVEN).doubleValue();
				rentier.setIppTauxRente(tauxRente);
			}
		}

		return listRentier;
	}

	/**
	 * Retourne le code de type de coefficient selon la classe du rentier et la
	 * date d'accident.
	 * 
	 * @param rentier
	 * @return String
	 * @throws FonctionnelleException
	 * @throws ExceptionMetier
	 */
	public String getTypeCoefficient(Rentier rentier)
			throws FonctionnelleException, ExceptionMetier {

		// ANO VERSEMENT DES CAPITAUX A LA CNRA 23/06/2016
		rentier = getRentierByID(rentier.getId());
		// Fin
		long codeDegre = 1;
		String codeTypeCoefficient = "";
		try {
			codeDegre = rentier.getLienParente();
		} catch (Exception e) {
			codeDegre = 1;
		}
		codeTypeCoefficient = "2";

		Date dateAccident = Calendar.getInstance().getTime();
		// gÈrer le cas o˘ la date d'accident est null ou vide dans certains
		// dossier AT.
		if (rentier.getRefDossierRente().getRefSinistre().getRefEvenement() != null
				&& rentier.getRefDossierRente().getRefSinistre()
						.getRefEvenement().getDateAccident() != null) {
			dateAccident = rentier.getRefDossierRente().getRefSinistre()
					.getRefEvenement().getDateAccident();
		}
		try {
			// La date repËre du calcul de la reserve grave est 02/12/2010
			if (dateAccident.before(new SimpleDateFormat("yyyyMMdd")
					.parse("20101202"))) {

				if (codeDegre == 0) {
					codeTypeCoefficient = "2";
				} else if (codeDegre >= 1 && codeDegre < 14) {
					codeTypeCoefficient = "2";
				} else if (codeDegre >= 20) {
					codeTypeCoefficient = "4";
				}
			} else if (dateAccident
					.compareTo((new SimpleDateFormat("yyyyMMdd"))
							.parse("20101202")) >= 0
					&& dateAccident
							.compareTo((new SimpleDateFormat("yyyyMMdd"))
									.parse("20150122")) < 0) {

				if (codeDegre == 0) {
					codeTypeCoefficient = "2";
				} else if (codeDegre >= 1 && codeDegre < 10) {
					codeTypeCoefficient = "2";
				} else if (codeDegre >= 10 && codeDegre < 14) {
					codeTypeCoefficient = "5";
				} else if (codeDegre >= 20) {
					codeTypeCoefficient = "4";// ou 6
				}

			} else if (dateAccident
					.compareTo((new SimpleDateFormat("yyyyMMdd"))
							.parse("20150122")) >= 0) {

				if (codeDegre >= 0 && codeDegre < 10) {
					codeTypeCoefficient = "7";
				} else if ((codeDegre >= 10 && codeDegre < 14)
						|| (codeDegre >= 60 && codeDegre < 70)) {
					codeTypeCoefficient = "8";
				} else if (codeDegre >= 70) {
					codeTypeCoefficient = "9";
				} else if (codeDegre >= 20 && codeDegre < 60) {
					if (rentier.getRefSituationRentier().getId() == 3) {
						codeTypeCoefficient = "11";
					} else {
						codeTypeCoefficient = "10";
					}
				}

			}
		} catch (ParseException e) {
			logger.error("problËme technique", e);
			throw new FonctionnelleException(
					eai.devass.sinistreat.appli.utils.exception.IMessageException.EXP_STAND_MESS);
		}

		return codeTypeCoefficient;

	}

	/**
	 * Retourne le Rentier par son id.
	 * 
	 * @param id
	 * @return
	 * @throws ExceptionMetier
	 */
	public Rentier getRentierByID(long id) throws ExceptionMetier {

		Rentier rentier = null;
		IPersistenceService dao = (IPersistenceService) ServicesFactory
				.getService(IPersistenceService.class);
		try {
			Class clazz = Class
					.forName("eai.devass.gsr.appli.modele.metier.dossierRente.Rentier");
			rentier = (Rentier) ((Session) dao.getSession()).get(clazz, id);

		} catch (Exception e) {
			throw new ExceptionMetier(e.getMessage());
		}

		if (rentier == null) {
			throw new ExceptionMetier(IMessageException.EXP_RENTIER_INTROUVALE);
		}

		if (rentier.getValidation() != true) {
			throw new ExceptionMetier(IMessageException.EXP_RENTE_NON_VALIDE);
		}

		return rentier;

	}

	public Intermediaire getIntermediaire(Rentier rentier) {

		Intermediaire intermediaire = new Intermediaire();

		try {
			StringBuilder hql = new StringBuilder(
					"select s.codeIntermediaire,s.nomIntermediaire from Rentier r")
					.append(" join r.refDossierRente d join d.refSinistre s where r=?");

			Object[] infoInterm = (Object[]) getSession()
					.createQuery(hql.toString()).setParameter(0, rentier)
					.uniqueResult();
			if (infoInterm != null) {
				intermediaire.setCode((String) infoInterm[0]);
				intermediaire.setLibelle((String) infoInterm[1]);
			}
		} catch (Exception e) {
			return null;
		}

		return intermediaire;
	}

	public Rentier getRentierByAyaantDroit(AyantDroit ayantDroit)
			throws Exception {

		StringBuilder hql = new StringBuilder(
				"select r from Rentier r join r.refAyantDroit ad")
				.append(" where ad = ?");

		return (Rentier) getSession().createQuery(hql.toString())
				.setParameter(0, ayantDroit).uniqueResult();
	}

	/**
	 * Mettre ‡ jour l'Ètat du rentier
	 * 
	 * @param idRentier
	 * @param etatRentier
	 * @throws Exception
	 */

	public void updateEtatRentier(Long idRentier, MvtEtatRentier mvtEtatRentier)
			throws Exception {

		String hql = "update Rentier set refEtatRentier=? where id=?";

		Query query = getSession().createQuery(hql);

		query.setParameter(0, mvtEtatRentier).setLong(1, idRentier)
				.executeUpdate();

	}

	/**
	 * Mettre ‡ jour l'Ètat de dossier rente
	 * 
	 * @param idRente
	 * @param etatRente
	 * @throws Exception
	 */
	public void updateEtatDossierRente(Long idRente, long etatRente)
			throws Exception {

		String hql = "update DossierRente set etat=? where id=?";

		Query query = getSession().createQuery(hql);

		query.setLong(0, etatRente).setLong(1, idRente).executeUpdate();

	}

	/**
	 * Mettre ‡ jour la rÈserve mathÈmatique
	 * 
	 * @param idRentier
	 * @param reserve
	 * @throws Exception
	 */
	public void updateReserveMathematique(Long idRentier, Double reserve)
			throws Exception {

		String hql = "update Rentier set reserveMathematique=? where id=?";

		Query query = getSession().createQuery(hql);

		query.setParameter(0, reserve).setLong(1, idRentier).executeUpdate();

	}

	/**
	 * Mettre ‡ jour la rente trimestrielle
	 * 
	 * @param idRentier
	 * @param rente
	 * @throws Exception
	 */

	public void updateRenteTrimestrielle(Long idRentier, Double rente)
			throws Exception {

		String hql = "update Rentier set montantRenteTrimestrielle=? where id=?";

		Query query = getSession().createQuery(hql);

		query.setParameter(0, rente).setLong(1, idRentier).executeUpdate();

	}

	public List<Rentier> getListRentierByNumSinistre(String numSinistre)
			throws Exception {

		StringBuilder hql = new StringBuilder(
				"select r from Rentier r join r.refDossierRente rente")
				.append(" join rente.refSinistre sin where sin.numeroSinistre=?");

		return getSession().createQuery(hql.toString())
				.setString(0, numSinistre).list();
	}

	public List getLastMvtConsignationCnraByRentier(Long idRentier,
			EtatMvt etatRentier) throws Exception {

		StringBuilder hql = new StringBuilder(
				"select MVT.* from GSR_MOUVEMENT MVT join GSR_RENTIER R on MVT.IDSRENTIER=R.ID where R.ID=?")
				.append(" and MVT.IDSTYPEMVT=? and MVT.IDSETATMVT=? order by MVT.id desc");

		return getSession().createSQLQuery(hql.toString())
				.addEntity(Mouvement.class).setLong(0, idRentier)
				.setLong(1, TypeMouvementGsr.CONSIGNATION_CNRA.getId())
				.setLong(2, etatRentier.getId()).list();
	}

	public List getLastMvtCnraReglementaireByRentier(Long idRentier,
			EtatMvt etatRentier) throws Exception {

		StringBuilder hql = new StringBuilder(
				"select MVT.* from GSR_MOUVEMENT MVT join GSR_RENTIER R on MVT.IDSRENTIER=R.ID where R.ID=?")
				.append(" and MVT.IDSTYPEMVT=? and MVT.IDSETATMVT=? order by MVT.id desc");

		return getSession().createSQLQuery(hql.toString())
				.addEntity(Mouvement.class).setLong(0, idRentier)
				.setLong(1, TypeMouvementGsr.CNRA_REGLEMENTAIRE.getId())
				.setLong(2, etatRentier.getId()).list();
	}

	public Double getTatalTaux(DossierRente dossierRente) throws Exception {

		StringBuilder hql = new StringBuilder(
				"select sum(ippTauxRente) from Rentier r where r.refDossierRente=?");

		return (Double) getSession().createQuery(hql.toString())
				.setParameter(0, dossierRente).uniqueResult();

	}

	/**
	 * Evo RÈglementaire 29/06/2015
	 */
	public List calculTauxRenteR(List<Rentier> listRentier) {

		int nbrAsc = 0;
		int nbrCjn = 0;
		int nbrDsc = 0;
		int nbrDscOr = 0;
		int nbrDivo = 0;

		Double totTauxAsc = Double.valueOf(0);
		Double totTauxCjn = Double.valueOf(0);
		Double totTauxDsc = Double.valueOf(0);
		Double totTauxDivo = Double.valueOf(0);

		Calendar curr = Calendar.getInstance();

		for (int i = 0; i < listRentier.size(); i++) {
			// Calcul age
			Rentier rentier = (Rentier) listRentier.get(i);
			int age = curr.get(Calendar.YEAR)
					- rentier.getDateNaissance().get(Calendar.YEAR);
			curr.add(Calendar.YEAR, -age);
			if (rentier.getDateNaissance().after(curr)) {
				age = age - 1;
			}
			// fin calcul age

			long code = 1;
			try {
				code = rentier.getLienParente();
			} catch (Exception e) {
				code = 1;
			}

			if ((code >= 1 && code < 10) || (code >= 70)) {// Ascendant et Kafel
				nbrAsc++;
				if (nbrAsc >= DEUX) {
					totTauxAsc = Double.valueOf(TRENTE);
				} else {
					totTauxAsc += Double.valueOf(QUINZE);
				}
			} else if (code >= 10 && code < 14) {// Conjoint
				nbrCjn++;
				totTauxCjn = Double.valueOf(CINQUANTE);

			} else if (code >= 60 && code < 70) {// DivorcÈ
				nbrDivo++;
				totTauxDivo = Double.valueOf(VINGT);

			} else if (code >= 40 && code < 60) {// Descendant OrphelinPur
				nbrDscOr++;
				if (rentier.getOrphelinPur()) {
					totTauxDsc += Double.valueOf(TRENTE);
				}
			} else if (code >= 20 && code < 40) {// Descendant
				nbrDsc++;
				if (nbrDsc < DEUX) {
					totTauxDsc = Double.valueOf(VINGT);
				} else {
					totTauxDsc += Double.valueOf(DIX);
				}
			}
		}

		Double totTaux = totTauxAsc + totTauxCjn + totTauxDsc + totTauxDivo;
		Double coefTotTaux = Double.valueOf(1);
		if (totTaux > QUATRE_VINGT_CINQ) {
			coefTotTaux = QUATRE_VINGT_CINQ / totTaux;
		}

		for (int i = 0; i < listRentier.size(); i++) {
			Rentier rentier = (Rentier) listRentier.get(i);
			Double tauxRente = 0D;
			long code = 1;
			try {
				code = rentier.getLienParente();
			} catch (Exception e) {
				code = 1;
			}
			if ((code >= 1 && code < 10) || (code >= 70)) {
				tauxRente = (totTauxAsc / nbrAsc) * coefTotTaux;
				tauxRente = new BigDecimal(tauxRente).setScale(2,
						BigDecimal.ROUND_HALF_EVEN).doubleValue();
			} else if (code >= 10 && code < 14) {
				tauxRente = (totTauxCjn / nbrCjn) * coefTotTaux;
				tauxRente = new BigDecimal(tauxRente).setScale(2,
						BigDecimal.ROUND_HALF_EVEN).doubleValue();
			} else if (code >= 60 && code < 70) {
				tauxRente = (totTauxDivo / nbrDivo) * coefTotTaux;
				tauxRente = new BigDecimal(tauxRente).setScale(2,
						BigDecimal.ROUND_HALF_EVEN).doubleValue();
			} else if (code >= 20 && code < 60) {
				tauxRente = (totTauxDsc / (nbrDsc + nbrDscOr)) * coefTotTaux;
				tauxRente = new BigDecimal(tauxRente).setScale(2,
						BigDecimal.ROUND_HALF_EVEN).doubleValue();
			}
			rentier.setIppTauxRente(tauxRente);
		}

		return listRentier;
	}

	public String getTypeCoefficientCNRA(Rentier rentier)
			throws FonctionnelleException, ExceptionMetier {

		// ANO VERSEMENT DES CAPITAUX A LA CNRA 23/06/2016
		rentier = getRentierByID(rentier.getId());
		// Fin
		long codeDegre = 1;
		String codeTypeCoefficient = "";
		try {
			codeDegre = rentier.getLienParente();
		} catch (Exception e) {
			codeDegre = 1;
		}
		codeTypeCoefficient = "2";

		Date dateAccident = Calendar.getInstance().getTime();
		// gÈrer le cas o˘ la date d'accident est null ou vide dans certains
		// dossier AT.
		if (rentier.getRefDossierRente().getRefSinistre().getRefEvenement() != null
				&& rentier.getRefDossierRente().getRefSinistre()
						.getRefEvenement().getDateAccident() != null) {
			dateAccident = rentier.getRefDossierRente().getRefSinistre()
					.getRefEvenement().getDateAccident();
		}
		try {
			// La date repËre du calcul de la reserve grave est 02/12/2010
			if (dateAccident.before(new SimpleDateFormat("yyyyMMdd")
					.parse("20101202"))) {

				if (codeDegre == 0) /* victime */{
					codeTypeCoefficient = "1";
				}else if (codeDegre >= 1 && codeDegre < 14){  /* Ascendant et* conjoint*/
					codeTypeCoefficient = "12";
				}else if (codeDegre >= 20 && codeDegre < 60){  /* Descendant */
					codeTypeCoefficient = "13";
				}
			} else if (dateAccident
					.compareTo((new SimpleDateFormat("yyyyMMdd"))
							.parse("20101202")) >= 0
					&& dateAccident
							.compareTo((new SimpleDateFormat("yyyyMMdd"))
									.parse("20150122")) < 0) {

				if (codeDegre >= 0 && codeDegre < 10){  /* victime && ascendant */
					codeTypeCoefficient = "14";
				} else if (codeDegre >= 10 && codeDegre < 14){  /* conjoint */
					codeTypeCoefficient = "15";
				} 
				else if (codeDegre >= 20 && codeDegre < 60){  /* orphelin/ infirme et non infirme */
					if (rentier.getRefSituationRentier().getId() == 3) {/* descendant handicapÈ*/
						codeTypeCoefficient = "14";
					} else {/* descendant non handicapÈ*/
						codeTypeCoefficient = "16";
					}				
				}

			} else if (dateAccident
					.compareTo((new SimpleDateFormat("yyyyMMdd"))
							.parse("20150122")) >= 0) {

				if (codeDegre >= 0 && codeDegre < 10) /* victime && ascendant */{
					codeTypeCoefficient = "14";
				} else if (codeDegre >= 10 && codeDegre < 14) {/* conjoint */
					codeTypeCoefficient = "14";
				} else if (codeDegre >= 20 && codeDegre < 60) { 
					if (rentier.getRefSituationRentier().getId() == 3) {/* descendant handicapÈ*/
						codeTypeCoefficient = "14";
					} else {/* descendant non handicapÈ*/
						codeTypeCoefficient = "13";
					}
				}
			}
		} catch (ParseException e) {
			logger.error("problËme technique", e);
			throw new FonctionnelleException(
					eai.devass.sinistreat.appli.utils.exception.IMessageException.EXP_STAND_MESS);
		}

		return codeTypeCoefficient;

	}
}