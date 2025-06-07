package eai.devass.gsr.appli.manager.metier.dossierRente;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import eai.devass.gsr.appli.modele.metier.dossierRente.DossierRente;
import eai.devass.gsr.appli.modele.metier.dossierRente.HistReserveMath;
import eai.devass.gsr.appli.prm.EtatRente;
import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.manager.sinistre.SinistreManager;
import eai.devass.sinistreat.appli.modele.metier.conciliation.Conciliation;
import eai.devass.sinistreat.appli.modele.metier.sinistre.Sinistre;
import eai.devass.sinistreat.appli.modele.metier.sinistre.Victime;
import eai.devass.sinistreat.appli.utils.Fonctions;
import eai.devass.sinistreat.appli.valueobjects.serialisation.PagerVO;
import ma.co.omnidata.framework.services.core.ServicesFactory;
import ma.co.omnidata.framework.services.dao.IPersistenceService;
import ma.co.omnidata.framework.services.dao.PersistenceException;
import ma.co.omnidata.framework.services.entites.EntiteException;
import ma.co.omnidata.framework.services.entites.EntiteManagerAbst;
import ma.co.omnidata.framework.services.entites.IEntite;
import ma.co.omnidata.framework.services.entites.ProcessEntiteException;
import ma.co.omnidata.framework.services.entites.ValidateEntiteException;
import ma.co.omnidata.framework.utile.DateUtile;

/**
 * Manager de l ' entité DossierRente
 * 
 * @author Nom Prenom (email)
 */
public class DossierRenteManager extends EntiteManagerAbst {
	private Logger logger = Logger.getLogger("loggerSINAT");
	//IPersistenceService dao = (IPersistenceService) ServicesFactory.getService(IPersistenceService.class);
	/**
	 * Completude de l ' objet avant sa creation
	 * 
	 * @param entite
	 *            lentité à completer
	 * @return returns lentité completé
	 * @throws ProcessEntiteException
	 *             problème lors de la complétude de l' entité
	 * @throws EntiteException
	 */

	protected IEntite doProcessCreateEntity(IEntite entite)
			throws ProcessEntiteException, EntiteException {
		DossierRente dossierRente = (DossierRente) entite;

		dossierRente.setDateCreation(DateUtile.dateCalendarCourante());

		return dossierRente;

	}
	/**
	 * Validation de l'object avant sa suppression
	 * 
	 * @param entite
	 *            l' entité à valider avant sa suppression
	 * @throws ValidateEntiteException
	 *             Probleme lors de la validation de l 'entité
	 * @throws EntiteException
	 *             Probleme lors de la persistence
	 */
	protected void doValidateDeleteEntity(IEntite entite)
			throws ValidateEntiteException, EntiteException {
	}

	/**
	 * Validation de l'object avant sa creation
	 * 
	 * @param entite
	 *            l' entité à valider avant sa creation
	 * @throws ValidateEntiteException
	 *             Probleme lors de la validation de l 'entité
	 * @throws EntiteException
	 *             Probleme lors de la persistence
	 */
	protected void doValidateCreateEntity(IEntite entite)
			throws ValidateEntiteException, EntiteException {
		if (((DossierRente) entite).getRefSinistre() == null) {
			ValidateEntiteException ve = new ValidateEntiteException(
					"FIELD.OBLIGATOIRE");
			ve.setValues(new String[] { "Sinistre" });
			throw ve;

		}

	}

	/**
	 * Validation de l' objet avant sa modification
	 * 
	 * @param entite
	 *            L 'entité à valider avant sa modification
	 * @throws ValidateEntiteException
	 *             Probleme lors de la validation de l 'entité
	 * @throws EntiteException
	 *             Probleme lors de la persistence
	 */
	protected void doValidateModifyEntity(IEntite entite)
			throws ValidateEntiteException, EntiteException {
		if (((DossierRente) entite).getRefSinistre() == null) {
			ValidateEntiteException ve = new ValidateEntiteException(
					"FIELD.OBLIGATOIRE");
			ve.setValues(new String[] { "Sinistre" });
			throw ve;
		}

	}

	/**
	 * Methode qui habille une entité avec ces entités refs completes pou ne
	 * pas ecraser les valeurs des refs avec des nulls
	 * 
	 * @param entite
	 *            lentité à habiller
	 * @throws EntiteException
	 *             Probleme lors de la persistence
	 */
	protected IEntite habiller(IEntite entite) throws EntiteException {
		DossierRente dossierRente = (DossierRente) entite;

		Sinistre refSinistre1 = dossierRente.getRefSinistre();
		Sinistre refSinistre = new Sinistre();

		if (refSinistre1 != null) {
			((IEntite) refSinistre).setId(refSinistre1.getId());
		}
		if (refSinistre != null && refSinistre1 != null) {
			if (((IEntite) refSinistre).getId() != 0) {
				try {
					refSinistre = new SinistreManager()
							.getSinistreById(refSinistre.getId());
				} catch (Exception e) {
					logger.error("probl�me technique",e);
				}

				dossierRente.setRefSinistre(refSinistre);
			} else {
				dossierRente.setRefSinistre(refSinistre1);
			}
		}
		return dossierRente;
	}

	/**
	 * Methode qui habille l' entité avant sa modification
	 * 
	 * @param entite
	 *            L 'entite à habiller
	 * @return l' entité habillé
	 * @throws ProcessEntiteException
	 *             problème lors de la complétude de l' entité
	 * @throws EntiteException
	 *             Probleme lors de la persistence
	 */
	protected IEntite doRevampModifyEntity(IEntite entite)
			throws ProcessEntiteException, EntiteException {
		return this.habiller(entite);
	}

	/**
	 * Methode qui habille l' entité avant sa creation
	 * 
	 * @param entite
	 *            L 'entite à habiller
	 * @return l' entité habillé
	 * @throws ProcessEntiteException
	 *             problème lors de la complétude de l' entité
	 * @throws EntiteException
	 *             Probleme lors de la persistence
	 */
	protected IEntite doRevampCreateEntity(IEntite entite)
			throws ProcessEntiteException, EntiteException {
		return this.habiller(entite);
	}

	/**
	 * Completude de l' objet avant sa modification
	 * 
	 * @param entite
	 *            L' entité à completer avant sa modification
	 * @returns L' entité completé
	 * @throws ProcessEntiteException
	 *             problème lors de la complétude de l' entité
	 * @throws EntiteException
	 *             Probleme lors de la persistence
	 */
	protected IEntite doProcessModifyEntity(IEntite entite)
			throws ProcessEntiteException, EntiteException {
		DossierRente dossierRente = (DossierRente) entite;

		return dossierRente;

	}

	public int doUpdateEtatDossierRente(DossierRente dossierRente,
			Long numeroRent) throws HibernateException, PersistenceException {
		try {
			String hql = "update DossierRente d set d.refEtatRentier=?, d.dateEtat=?";
			if (dossierRente.getRefEtatRentier().getId() == EtatRente.Valide_En_Cours
					.getCode()) {
				hql += ", d.dateValidation=? , d.validation=?,d.numeroRente=?";
			}
			hql += " where d.id=?";
			IPersistenceService dao = (IPersistenceService) ServicesFactory
					.getService(IPersistenceService.class);
			Query query = ((Session) dao.getSession()).createQuery(hql);
			int i = 0;
			Date toDaye = new Date();
			query.setDouble(i++, dossierRente.getRefEtatRentier().getId());
			query.setDate(i++, toDaye);
			if (dossierRente.getRefEtatRentier().getId() == EtatRente.Valide_En_Cours
					.getCode()) {
				query.setDate(i++, toDaye);
				query.setBoolean(i++, true);

				query.setLong(i++, numeroRent);

			}
			query.setLong(i, dossierRente.getId());
			return query.executeUpdate();
		} catch (Exception e) {
		}
		return -1;
	}

	public List<DossierRente> doGetDossierRenteBySinistre(
			DossierRente dossierRente, int numPage, int pageSize)
			throws ProcessEntiteException, EntiteException, HibernateException,
			PersistenceException {

		IPersistenceService dao = (IPersistenceService) ServicesFactory
				.getService(IPersistenceService.class);

        StringBuilder hql = new StringBuilder(
                getListDossierRentQuery(dossierRente));
		hql.append(" order by this.id");
		Query query = ((Session) dao.getSession()).createQuery(hql.toString());

		if (numPage > 0) {
			query.setFirstResult((numPage - 1) * pageSize).setMaxResults(
					pageSize);
		}

		return query.list();

	}

	public DossierRente doGetDossierRenteValiderBySinistre(
			DossierRente dossierRente) throws ProcessEntiteException,
			EntiteException, HibernateException, PersistenceException {

		IPersistenceService dao = (IPersistenceService) ServicesFactory
				.getService(IPersistenceService.class);
		String hql = getListDossierRentQueryValider(dossierRente);
		hql += " order by this.id";
		Query query = ((Session) dao.getSession()).createQuery(hql);

		return (DossierRente) query.uniqueResult();
	}

	public int doGetNombreDossierRent(DossierRente dossierRente)
			throws HibernateException, ProcessEntiteException, EntiteException,
			PersistenceException {
		String hql = "Select count(this.id) "
                + getListDossierRentQuery(dossierRente).replaceAll(
                        "select this", "");
		IPersistenceService dao = (IPersistenceService) ServicesFactory
				.getService(IPersistenceService.class);
		Query query = ((Session) dao.getSession()).createQuery(hql);
		return ((Long) query.uniqueResult()).intValue();
	}

	public Long doGetLastNumRente(DossierRente dossierRente)
			throws HibernateException, ProcessEntiteException, EntiteException,
			PersistenceException {
		String hql = "select  max(this.numeroRente) "
                + getListDossierRentQuery(dossierRente).replaceAll(
                        "select this", "");
        ;
		IPersistenceService dao = (IPersistenceService) ServicesFactory
				.getService(IPersistenceService.class);
		Query query = ((Session) dao.getSession()).createQuery(hql);
		Long res = (Long) (query.uniqueResult());
		return res;

	}

	public List<DossierRente> doGetRentNoValider(DossierRente dossierRente,
			int numPage, int pageSize) throws HibernateException,
			ProcessEntiteException, EntiteException, PersistenceException {

        StringBuilder hql = new StringBuilder(
                getListDossierRentQuery(dossierRente));
		hql.append(" and this.id in (select r.refDossierRente.id from Rentier r where (r.refEtatRentier.id=1 or r.refEtatRentier.id=2) and r.validation = 0 and r.refDossierRente.id=this.id and (r.refDossierRente.validation = 1 or r.refDossierRente.validation = 0) ) order by this.id");
		IPersistenceService dao = (IPersistenceService) ServicesFactory
				.getService(IPersistenceService.class);
		Query query = ((Session) dao.getSession()).createQuery(hql.toString());
		if (numPage > 0) {
			query.setFirstResult((numPage - 1) * pageSize);
			query.setMaxResults(pageSize);
		}
		List<DossierRente> res = query.list();
		return res;
	}

	public int doGetNbrRentNoValider(DossierRente dossierRente)
			throws HibernateException, ProcessEntiteException, EntiteException,
			PersistenceException {
		String hql = "Select count(this.id) "
                + getListDossierRentQuery(dossierRente).replaceAll(
                        "select this", "");
		hql += " and this.id in (select r.refDossierRente.id from Rentier r where (r.refEtatRentier.id=1 or r.refEtatRentier.id=2) and r.refDossierRente.id=this.id)";
		IPersistenceService dao = (IPersistenceService) ServicesFactory
				.getService(IPersistenceService.class);
		Query query = ((Session) dao.getSession()).createQuery(hql);
		return ((Long) query.uniqueResult()).intValue();
	}

	private String getListDossierRentQuery(DossierRente dossierRente)
			throws ProcessEntiteException, EntiteException,
			PersistenceException, HibernateException {

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		boolean clauseRentier = false;
        StringBuilder hql = new StringBuilder(
                "select this from DossierRente this");
		
		// Clause rentier
		Sinistre refSinistre = dossierRente.getRefSinistre();
		Victime refVictime = null;
		if (refSinistre != null) {
			if (refSinistre.getRefVictime() != null) {
				refVictime = refSinistre.getRefVictime();
				if (Fonctions.isNotEmpty(refVictime.getNom())) {
					
					clauseRentier = true;
				}
				if (Fonctions.isNotEmpty(refVictime.getPrenom())) {
					clauseRentier = true;
				}
				if (Fonctions.isNotEmpty(refVictime.getNumeroCIN())) {
					clauseRentier = true;
				}
				if (dossierRente.getDateCreation()!= null){
					clauseRentier = true;
				}
			}
		}
		
		if(clauseRentier) {
			hql.append(" join this.refsRentier rentier");
		}
		
		// Clause where
		hql.append(" where 1=1");
		
		if (dossierRente.getId() != 0) {
			hql.append(" and this.id=").append(dossierRente.getId());
		}

		if (dossierRente.getNumeroRente() != null) {
            hql.append(" and this.numeroRente = ").append(
                    dossierRente.getNumeroRente());
		}
		if (dossierRente.getRefEtatRentier() != null
				&& dossierRente.getRefEtatRentier().getId() != 0) {
            hql.append(" and this.refEtatRentier.id=").append(
                    dossierRente.getRefEtatRentier().getId());
		}
		if (Fonctions.isNotEmpty(dossierRente.getCompagnieRente())) {
            hql.append(" and this.compagnieRente='")
                    .append(dossierRente.getCompagnieRente()).append("'");
		}
		if (dossierRente.getDateCreation() != null && clauseRentier == false) {
            hql.append(" and this.dateCreation = to_date('")
                    .append(dateFormat.format(dossierRente.getDateCreation()
                            .getTime())).append("', 'dd/MM/yyyy HH24:MI:SS')");
        } else {
            if (dossierRente.getDateCreationDebut() != null
                    && clauseRentier == false) {
                hql.append(" and this.dateCreation BETWEEN to_date('")
                        .append(dossierRente.getDateCreationDebut())
                        .append(" 00:00:00', 'dd/MM/yyyy HH24:MI:SS')");
            } else {
                if (dossierRente.getDateCreationFin() != null
                        && clauseRentier == false) {
                    hql.append(" and this.dateCreation BETWEEN to_date('")
                            .append(dossierRente.getDateCreationFin())
                            .append(" 00:00:00', 'dd/MM/yyyy HH24:MI:SS')");
                }
            }
            if (dossierRente.getDateCreationFin() != null
                    && clauseRentier == false) {
                hql.append(" and to_date('")
                        .append(dossierRente.getDateCreationFin())
                        .append(" 23:59:59', 'dd/MM/yyyy HH24:MI:SS')");
            } else {
                if (dossierRente.getDateCreationDebut() != null
                        && clauseRentier == false) {
                    hql.append(" and to_date('")
                            .append(dossierRente.getDateCreationDebut())
                            .append(" 23:59:59', 'dd/MM/yyyy HH24:MI:SS')");
				}
			}
		}

		if (dossierRente.getValidation() != null) {
            hql.append(" and this.validation = ").append(
                    dossierRente.getValidation());
		}
		if (dossierRente.getDateEtat() != null) {
            hql.append(" and this.dateEtat = to_date('")
                    .append(dateFormat.format(dossierRente.getDateEtat()
                            .getTime())).append("', 'dd/MM/yyyy')");
		}

		if (dossierRente.getDateValidation() != null) {
            hql.append(" and this.dateValidation = to_date('")
                    .append(dateFormat.format(dossierRente.getDateValidation()
                            .getTime())).append("', 'dd/MM/yyyy')");
		}
		if (dossierRente.getIdDossierRente() != null) {
            hql.append(" and this.idDossierRente = ").append(
                    dossierRente.getIdDossierRente());
		}

		if (refSinistre != null) {

			if (refSinistre.getId() != 0) {
                hql.append(" and this.refSinistre.id=").append(
                        dossierRente.getRefSinistre().getId());
			}
			if (Fonctions.isNotEmpty(refSinistre.getNumeroSinistre())) {
                hql.append(" and this.refSinistre.numeroSinistre ='").append(
                        refSinistre.getNumeroSinistre() + "'");
			}
			if (Fonctions.isNotEmpty(refSinistre.getNumeroPolice())) {
                hql.append(" and this.refSinistre.numeroPolice ='").append(
                        refSinistre.getNumeroPolice() + "'");
			}
			if (Fonctions.isNotEmpty(refSinistre.getNumeroGrave())) {
                hql.append(" and this.refSinistre.numeroGrave ='").append(
                        refSinistre.getNumeroGrave() + "'");
			}
			if (Fonctions.isNotEmpty(refSinistre.getCodeIntermediaire())) {
                hql.append(" and this.refSinistre.codeIntermediaire =").append(
                        refSinistre.getCodeIntermediaire() + "'");
			}
			if (refSinistre.getRefEvenement() != null) {
				if (refSinistre.getRefEvenement().getDateAccident() != null) {
                    hql.append(
                            " and this.refSinistre.refEvenement.dateAccident = to_date('")
                            .append(dateFormat.format(refSinistre
                                    .getRefEvenement().getDateAccident()
                                    .getTime())).append("', 'dd/MM/yyyy')");
				}
			}
			
			if(clauseRentier) {
				if (refSinistre.getRefVictime() != null) {
					if (Fonctions.isNotEmpty(refVictime.getNom())) {
                        hql.append(" and upper(rentier.nom) like'%")
                                .append(refVictime.getNom().trim())
                                .append("%'");
					}
					if (Fonctions.isNotEmpty(refVictime.getPrenom())) {
                        hql.append(" and upper(rentier.prenom) like'%")
                                .append(refVictime.getPrenom()).append("%'");
					}
					if (Fonctions.isNotEmpty(refVictime.getNumeroCIN())) {
                        hql.append(" and rentier.numeroCIN = '")
                                .append(refVictime.getNumeroCIN().trim())
                                .append("'");
					}
					
					if (dossierRente.getDateCreation()!=null) {
                        hql.append(" and rentier.dateConstitution = to_date('")
                                .append(dateFormat.format(dossierRente
                                        .getDateCreation().getTime()))
                                .append("', 'dd/MM/yyyy')");
					}
				}
			}
			
		}

		return hql.toString();
	}

	private String getListDossierRentQueryValider(DossierRente dossierRente)
			throws ProcessEntiteException, EntiteException,
			PersistenceException, HibernateException {

		String hql = "from DossierRente this where 1=1";
		if (dossierRente.getId() != 0) {
			hql += " and this.id=" + dossierRente.getId();
		}

		if (dossierRente.getNumeroRente() != null) {
			hql += " and this.numeroRente = " + dossierRente.getNumeroRente();
		}
		if (dossierRente.getRefEtatRentier() != null
				&& dossierRente.getRefEtatRentier().getId() != 0) {
			hql += " and this.refEtatRentier.id="
					+ dossierRente.getRefEtatRentier().getId();
		}
		if (Fonctions.isNotEmpty(dossierRente.getCompagnieRente())) {
			hql += " and this.compagnieRente='"
					+ dossierRente.getCompagnieRente() + "'";
		}

		if (dossierRente.getValidation() != null) {
			hql += " and this.validation = " + dossierRente.getValidation();
		}
		if (dossierRente.getIdDossierRente() != null) {
			hql += " and this.idDossierRente = "
					+ dossierRente.getIdDossierRente();
		}

		if (dossierRente.getRefSinistre() != null) {

			Sinistre refSinistre = dossierRente.getRefSinistre();

			if (refSinistre.getId() != 0) {
				hql += " and this.refSinistre.id="
						+ dossierRente.getRefSinistre().getId();
			}
			if (Fonctions.isNotEmpty(refSinistre.getNumeroSinistre())) {
				hql += " and this.refSinistre.numeroSinistre='"
						+ refSinistre.getNumeroSinistre() + "'";
			}
			if (Fonctions.isNotEmpty(refSinistre.getNumeroPolice())) {
				hql += " and this.refSinistre.numeroPolice ='"
						+ refSinistre.getNumeroPolice() + "'";
			}
			if (Fonctions.isNotEmpty(refSinistre.getNumeroGrave())) {
				hql += " and this.refSinistre.numeroGrave ='"
						+ refSinistre.getNumeroGrave() + "'";
			}
			if (Fonctions.isNotEmpty(refSinistre.getCodeIntermediaire())) {
				hql += " and this.refSinistre.codeIntermediaire ="
						+ refSinistre.getCodeIntermediaire() + "'";
			}
			if (refSinistre.getRefVictime() != null) {
				Victime refVictime = refSinistre.getRefVictime();
				if (Fonctions.isNotEmpty(refVictime.getNom())) {
					hql += " and this.refSinistre.refVictime.nom like '%"
							+ refVictime.getNom().trim() + "%'";
				}
				if (Fonctions.isNotEmpty(refVictime.getNumeroCIN())) {
					hql += " and this.refSinistre.refVictime.numeroCIN = '"
							+ refVictime.getNumeroCIN().trim() + "'";
				}
			}
		}

		return hql;
	}

	public double doSommeArrerageRegle(DossierRente dossierRente)
			throws HibernateException, ProcessEntiteException, EntiteException,
			PersistenceException {
		Long a = doGetIdSinistre(dossierRente.getId());
		if (a != null && a != 0) {
			String hql = "select sum(COALESCE(dr.montant,0)) from Sinistre s,Reglement r,DetailReglement dr where s.id="
					+ a
					+ " and s.id = r.refSinistre and r.id = dr.refReglement and dr.refPrestation.code = 21 group by s.id order by s.id";
			IPersistenceService dao = (IPersistenceService) ServicesFactory
					.getService(IPersistenceService.class);
			Query query = ((Session) dao.getSession()).createQuery(hql);
			if (!query.list().isEmpty() && query.uniqueResult() != null) {
				Double res = ((Double) query.uniqueResult()).doubleValue();
				return res;
			} else {
				return Double.valueOf(0);
			}

		} else {
			return Double.valueOf(0);
		}

	}

	public double doSommeCCDossierRente(DossierRente dossierRente)
			throws HibernateException, ProcessEntiteException, EntiteException,
			PersistenceException {
        // WMOS 21/03/2016:[GSR]Correctif validation rente ajout filtre �tat
        // rentier cr�er ou modifier
		String hql = "select sum(COALESCE(r.capitalConstitutif,0)) from DossierRente d,Rentier r where d.id="
                + dossierRente.getId()
                + " and d.id = r.refDossierRente and r.refEtatRentier.id in (1,2)";
		IPersistenceService dao = (IPersistenceService) ServicesFactory
				.getService(IPersistenceService.class);
		Query query = ((Session) dao.getSession()).createQuery(hql);
		if (!query.list().isEmpty() && query.uniqueResult() != null) {
			Double res = ((Double) query.uniqueResult()).doubleValue();
			return res;
		} else {
			return Double.valueOf(0);
		}

	}

	public double doSommeArrerageDossierRente(DossierRente dossierRente)
			throws HibernateException, ProcessEntiteException, EntiteException,
			PersistenceException {
        // WMOS 21/03/2016:[GSR]Correctif validation rente ajout filtre �tat
        // rentier cr�er ou modifier
		String hql = "select sum(COALESCE(r.arrerageAvantConstitution,0)) from DossierRente d,Rentier r where d.id="
                + dossierRente.getId()
                + " and d.id= r.refDossierRente and r.refEtatRentier.id in (1,2)";
		IPersistenceService dao = (IPersistenceService) ServicesFactory
				.getService(IPersistenceService.class);
		Query query = ((Session) dao.getSession()).createQuery(hql);

		if (!query.list().isEmpty() && query.uniqueResult() != null) {

			Double res = ((Double) query.uniqueResult()).doubleValue();
			return res;
		} else {
			return Double.valueOf(0);
		}
	}

	/*
	 * add methode evo lot1 08/01/2014 Sum Montant nouvelle r�serve grave
	 */
	public double doSommeMontantNouvelleRG(DossierRente dossierRente)
			throws HibernateException, ProcessEntiteException, EntiteException,
			PersistenceException {
        // WMOS 21/03/2016:[GSR]Correctif validation rente ajout filtre �tat
        // rentier cr�er ou modifier
		String hql = "select sum(COALESCE(r.arrerages,0)) from DossierRente d,Rentier r where d.id="
                + dossierRente.getId()
                + " and d.id= r.refDossierRente and r.refEtatRentier.id in (1,2)";
		IPersistenceService dao = (IPersistenceService) ServicesFactory
				.getService(IPersistenceService.class);
		Query query = ((Session) dao.getSession()).createQuery(hql);

		if (!query.list().isEmpty() && query.uniqueResult() != null) {

			Double res = ((Double) query.uniqueResult()).doubleValue();
			return res;
		} else {
			return Double.valueOf(0);
		}
	}

	public Long doGetIdSinistre(Long dossierRente) throws HibernateException,
			ProcessEntiteException, EntiteException, PersistenceException {
		String hql = "Select d.refSinistre from  DossierRente d where d.id ="
				+ dossierRente;
		IPersistenceService dao = (IPersistenceService) ServicesFactory
				.getService(IPersistenceService.class);
		Query query = ((Session) dao.getSession()).createQuery(hql);
		if (!query.list().isEmpty()) {
			Sinistre res = (Sinistre) (query.uniqueResult());
			if (res != null) {
				return res.getId();
			} else {
				return Long.valueOf(0);
			}
		} else {
			return Long.valueOf(0);
		}
	}
	private List getPartCollectionByCondition(Query query, int page,
			int pageSize) throws PersistenceException {
		if (query != null) {
			query.setFirstResult(page * pageSize);
			query.setMaxResults(pageSize);
			return query.list();
		} else {
			return null;
		}
	}
	
	public Integer getNombreHistReserve(HistReserveMath histReserveMath) 
			throws Exception {			
				Object[] objects = this.getQueryListHistReserveMath(histReserveMath);
				String hql = "Select count (c.id) " + (String) objects[0];
				IPersistenceService dao = (IPersistenceService) ServicesFactory
						.getService(IPersistenceService.class);
				Query query = ((Session) dao.getSession()).createQuery(hql);
				Long nombreResultat = (Long) query.uniqueResult();
				return nombreResultat.intValue();
		}
	
	public Object[] getQueryListHistReserveMath(HistReserveMath histReserveMath )
			throws FonctionnelleException, PersistenceException {
			StringBuffer sql = new StringBuffer(" from HistReserveMath c ")
			.append(" where idRente ='" + histReserveMath.getIdRente() + "'order by c.dateActualisation asc");;
			Object[] objects = new Object[2];
			Map<String, Object> values = new HashMap<String, Object>();
			objects[0] = sql.toString();
			objects[1] = values;
			return objects;
			}
	
	public List<HistReserveMath> getListHistReserveMath(PagerVO pagerVO, HistReserveMath histReserveMath ) throws Exception {
		
			
		Object[] objects =	this.getQueryListHistReserveMath(histReserveMath);
		String hql = "Select c  " + (String) objects[0];
		IPersistenceService dao = (IPersistenceService) ServicesFactory
				.getService(IPersistenceService.class);
		Query query = ((Session) dao.getSession()).createQuery(hql);
		if (pagerVO != null) {
			Integer numpage = 0;
			if (Integer.valueOf(pagerVO.getNumPage()).intValue() > 0) {
				numpage = Integer.valueOf(pagerVO.getNumPage()) - 1;
			}
			return this.getPartCollectionByCondition(query, numpage,
					Integer.valueOf(pagerVO.getPageSize()));
		} else {
			return query.list();
		}
	}
}