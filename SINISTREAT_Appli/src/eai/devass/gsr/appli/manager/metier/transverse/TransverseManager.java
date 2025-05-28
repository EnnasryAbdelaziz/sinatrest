package eai.devass.gsr.appli.manager.metier.transverse;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import ma.co.omnidata.framework.services.core.ServicesFactory;
import ma.co.omnidata.framework.services.dao.IPersistenceService;
import ma.co.omnidata.framework.services.dao.PersistenceException;
import ma.co.omnidata.framework.services.entites.EntiteException;
import ma.co.omnidata.framework.services.entites.ProcessEntiteException;
import ma.co.omnidata.framework.utile.DateUtile;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import eai.devass.commun.appli.exeptions.ExceptionMetier;
import eai.devass.commun.appli.historisation.JournalisationUtils;
import eai.devass.commun.appli.modele.HistoryItem;
import eai.devass.commun.appli.modele.IHistorisable;
import eai.devass.commun.appli.util.CommonUtils;
import eai.devass.gsr.appli.manager.CommunManager;
import eai.devass.gsr.appli.manager.metier.dossierRente.DossierRenteManager;
import eai.devass.gsr.appli.manager.metier.dossierRente.RentierManager;
import eai.devass.gsr.appli.modele.metier.dossierRente.DossierRente;
import eai.devass.gsr.appli.modele.metier.dossierRente.Rentier;
import eai.devass.gsr.appli.modele.metier.mouvements.Mouvement;
import eai.devass.gsr.appli.modele.metier.mouvements.MvtSuspension;
import eai.devass.gsr.appli.modele.metier.reglement.QuittanceGsr;
import eai.devass.gsr.appli.modele.parametrage.EtatMvt;
import eai.devass.gsr.appli.modele.parametrage.EtatQtc;
import eai.devass.gsr.appli.modele.parametrage.NatureQtcGsr;
import eai.devass.gsr.appli.modele.parametrage.TypeMouvement;
import eai.devass.gsr.appli.modele.parametrage.TypeRgltGsr;
import eai.devass.gsr.appli.prm.EtatQuittance;
import eai.devass.gsr.appli.prm.NatureQuittance;
import eai.devass.gsr.appli.utile.CommonGsrUtils;
import eai.devass.gsr.appli.utile.TypeConverter;
import eai.devass.sinistreat.appli.authentification.Utilisateur;
import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.manager.ParametrageManager;
import eai.devass.sinistreat.appli.modele.metier.sinistre.Sinistre;
import eai.devass.sinistreat.appli.modele.parametrage.CoefficientAge;
import eai.devass.sinistreat.appli.utils.exception.IMessageException;

@SuppressWarnings("unchecked")
public class TransverseManager extends CommunManager implements
		IMessageException {

	private CommonUtils commonUtils = CommonUtils.getInstance();
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

	/**
	 * Methode de calcul des rentes et taux de rentes et actualisation du
	 * dossier rente
	 * 
	 * @param idDossierRente
	 * @throws FonctionnelleException
	 */
	public void calculRente(Long idDossierRente) throws FonctionnelleException {
		if (idDossierRente == null || idDossierRente.equals(Long.valueOf(0))) {
			logger.error(EXP_OBJECT_ENTREE);
			throw new FonctionnelleException(EXP_STAND_MESS);
		}
		try {
			// Recherche dossierRente
			DossierRente dossierRente = rechercheDossierRente(idDossierRente);
			TransverseManager transverseManager = new TransverseManager();
			List<Rentier> listRentier = transverseManager.getListRentierByRentier(Long.toString(dossierRente.getRefsRentier().get(0).getId()));
			// Calcul taux de rente
			CalculRente calculRente = new CalculRente();
			String dateSurvenu = "22/01/2015";
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date dateSur = sdf.parse(dateSurvenu);
			if (dossierRente.getRefSinistre()
					.getRefEvenement().getDateAccident().compareTo(dateSur) < 0) {
				calculRente.calculTauxRente(listRentier);
			} else if (dossierRente.getRefSinistre()
					.getRefEvenement().getDateAccident().compareTo(dateSur) >= 0) {
				calculRente.calculTauxRenteR(listRentier);
			}
			// Calcul rente trimestrielle
			calculRente.calculRenteTrimestrielle(dossierRente.getRefsRentier());
			// Calcul capital constitutif
			calculRente
					.calculReserveGraveRentier(dossierRente.getRefsRentier());
		} catch (Exception e) {
			logger.error(EXP_STAND_MESS, e);
			throw new FonctionnelleException(EXP_STAND_MESS, e);
		}
	}

	/**
	 * Methode de calcul des rentes et taux de rentes de la liste des rentiers
	 * en entrée
	 * 
	 * @param listRentier
	 * @throws FonctionnelleException
	 */
	public void calculerNouvelleRente(List<Rentier> listRentier)
			throws FonctionnelleException {
		if (listRentier == null || listRentier.size() == 0) {
			logger.error(EXP_OBJECT_ENTREE);
			throw new FonctionnelleException(EXP_STAND_MESS);
		}
		try {
			// Calcul taux de rente
			CalculRente calculRente = new CalculRente();
			//TransverseManager transverseManager = new TransverseManager();
			//listRentier = transverseManager.getListRentierByRentier(Long.toString(listRentier.get(0).getId()));
			String dateSurvenu = "22/01/2015";
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date dateSur = sdf.parse(dateSurvenu);
			if (listRentier.get(0).getRefDossierRente().getRefSinistre()
					.getRefEvenement().getDateAccident().compareTo(dateSur) < 0) {
				calculRente.calculTauxRente(listRentier);
			} else if (listRentier.get(0).getRefDossierRente().getRefSinistre()
					.getRefEvenement().getDateAccident().compareTo(dateSur) >= 0) {
				calculRente.calculTauxRenteR(listRentier);
			}
			// Calcul rente trimestrielle
			calculRente.calculRenteTrimestrielle(listRentier);
			// Calcul capital constitutif
			calculRente.calculReserveGraveRentier(listRentier);
		} catch (Exception e) {
			logger.error(EXP_STAND_MESS, e);
			throw new FonctionnelleException(EXP_STAND_MESS, e);
		}
	}

	/**
	 * Recherche dossier rente.
	 * 
	 * @param idDossierRente
	 * @return DossierRente
	 * @throws FonctionnelleException
	 */
	private DossierRente rechercheDossierRente(Long idDossierRente)
			throws FonctionnelleException {
		try {
			// Recherche dossierRente
			DossierRente dossierRente = new DossierRente();
			dossierRente.setId(idDossierRente);
			DossierRenteManager dossierRenteManager = (DossierRenteManager) dossierRente
					.getFactory().newEntiteManager();
			dossierRente = (DossierRente) dossierRenteManager
					.getEntite(dossierRente);
			if (dossierRente == null || dossierRente.getRefsRentier() == null
					|| dossierRente.getRefsRentier().isEmpty()) {
				logger.error(EXP_OBJECT_SORTIE + ": "
						+ DossierRente.class.getName());
				throw new FonctionnelleException(EXP_STAND_MESS);
			}
			return dossierRente;
		} catch (EntiteException e) {
			logger.error(EXP_RECHERCHE + " " + DossierRente.class.getName(), e);
			throw new FonctionnelleException(EXP_STAND_MESS, e);
		}
	}

	public Double sommeQuittancesReglees(long idRentier, Calendar dateCalcul) throws Exception {

		Double tropPercu = 0D;
		
		StringBuilder hql = new StringBuilder(
				"select sum(COALESCE(qtc.montant,0)) from QuittanceGsr qtc")
				.append(" where qtc.refRentier.id=? ")
				.append(" and qtc.refMouvement is null ")
				.append(" and qtc.refNatureQuittance=?")
				.append(" and qtc.dateFinQtc > ?")
				.append(" and qtc.refEtatQtc = ?");

		Query query = getSession()
				.createQuery(hql.toString())
				.setParameter(0, idRentier)
				.setParameter(
						1,
						new NatureQtcGsr(NatureQuittance.Rente_periodique
								.getId())).setCalendar(2, dateCalcul)
				.setParameter(3, new EtatQtc(EtatQuittance.Reglee.getId()));

		tropPercu =(query.uniqueResult()!=null)? (Double) query.uniqueResult(): tropPercu;
		
		
		
		return tropPercu;

		/**
		 * trop perçu décès(remariage,echue,..)
		 * 
		 * SELECT SUM (NVL (quittanceg0_.montant, 0)) AS col_0_0_ FROM
		 * gsr_quittance quittanceg0_ WHERE quittanceg0_.idsrentier = 78 AND
		 * (quittanceg0_.idsmouvement IS NULL) AND quittanceg0_.idsnature_qtc =
		 * 25 AND quittanceg0_.datfinqtc > to_date('2014/03/31','yyyy/mm/dd')
		 * AND quittanceg0_.idsetat_qtc = 5
		 * 
		 * 
		 */

		/*
		 * String hql =
		 * "select sum(COALESCE(qtc.montant,0)),count(qtc.exercice) from QuittanceGsr qtc "
		 * + " join qtc.refRentier r join qtc.refSituationQuittanceGsr sitQtc" +
		 * " where r.id=:idREnt " + " and qtc.beneficiaire <> :beneficiare" +
		 * " and maxelement(qtc.refSituationQuittanceGsr)=sitQtc and sitQtc.refEtatQtc=:etatRegle"
		 * +
		 * " and qtc.datEtat BETWEEN :dat and (select max (qtc2.datEtat) from QuittanceGsr qtc2"
		 * +
		 * " join qtc2.refSituationQuittanceGsr sitQtc2 where qtc2.refRentier.id=:idREnt"
		 * +
		 * " and maxelement(qtc2.refSituationQuittanceGsr)=sitQtc2 and sitQtc2.refEtatQtc=:etatRegle)"
		 * ; Query query = getSession() .createQuery(hql) .setLong("idREnt",
		 * idRentier) .setString("beneficiare", "AT") .setParameter("etatRegle",
		 * new EtatQtc(EtatQuittance.Reglee.getId())) .setParameter("dat",
		 * nouvDateDepartRente);
		 * 
		 * return (Object[]) query.uniqueResult();
		 */

	}

	/**
	 * Calcul des arrerrages perçu mouvement Rachat.
	 * 
	 * @param idRentier
	 * @param dateCalcul
	 * @return
	 * @throws Exception
	 */

	public Object getArreragePercu(long idRentier, Calendar dateCalcul)
			throws Exception {

		StringBuilder hql = new StringBuilder(
				"select sum(COALESCE(qtc.montant,0)) from QuittanceGsr qtc")
				.append(" where qtc.refRentier.id=:idRentier ")
				.append(" and qtc.refMouvement is null ")
				.append(" and qtc.refNatureQuittance=:natureQtc")
				.append(" and qtc.refEtatQtc =:etatQtc")
				.append(" and qtc.dateFinQtc > :dateCalul")
				.append(" and qtc.dateFinQtc <= (select max (qtc2.dateFinQtc) from QuittanceGsr qtc2")
				.append(" where qtc2.refRentier.id=:idRentier ")
				.append(" and qtc2.refMouvement is null ")
				.append(" and qtc2.refNatureQuittance=:natureQtc")
				.append(" and qtc2.refEtatQtc =:etatQtc)");

		Query query = getSession()
				.createQuery(hql.toString())
				.setLong("idRentier", idRentier)
				.setParameter(
						"natureQtc",
						new NatureQtcGsr(NatureQuittance.Rente_periodique
								.getId()))
				.setCalendar("dateCalul", dateCalcul)
				.setParameter("etatQtc",
						new EtatQtc(EtatQuittance.Reglee.getId()));

		return (Object) query.uniqueResult();

		/**
		 * 
		 * 
		 * arrerage percu rachat SELECT SUM (NVL (quittanceg0_.montant, 0)) AS
		 * col_0_0_ FROM gsr_quittance quittanceg0_ WHERE
		 * quittanceg0_.idsrentier = 78 AND (quittanceg0_.idsmouvement IS NULL)
		 * AND quittanceg0_.idsnature_qtc = 25 AND quittanceg0_.idsetat_qtc = 5
		 * AND quittanceg0_.datfinqtc >to_date('2014/09/29','yyyy/mm/dd') /**
		 * 
		 * 
		 * arrerage percu rachat SELECT SUM (NVL (quittanceg0_.montant, 0)) AS
		 * col_0_0_ FROM gsr_quittance quittanceg0_ WHERE
		 * quittanceg0_.idsrentier = 78 AND (quittanceg0_.idsmouvement IS NULL)
		 * AND quittanceg0_.idsnature_qtc = 25 AND quittanceg0_.idsetat_qtc = 5
		 * AND quittanceg0_.datfinqtc >to_date('2014/09/29','yyyy/mm/dd') AND
		 * quittanceg0_.datfinqtc <= (SELECT MAX (quittanceg1_.datfinqtc) FROM
		 * gsr_quittance quittanceg1_ WHERE quittanceg1_.idsrentier = 78 AND
		 * (quittanceg1_.idsmouvement IS NULL ) AND quittanceg1_.beneficiaire <>
		 * 'AT' AND quittanceg1_.idsnature_qtc = 25 AND quittanceg1_.idsetat_qtc
		 * = 5)
		 */

	}

	/**
	 * retourne la somme du montant des quittances trimestrielles non réglées
	 * 
	 * @param idRentier
	 * @param dateCalcul
	 * @return
	 * @throws Exception
	 */
	public Object sommeQuittancesTrimNonReglees(long idRentier,
			Calendar dateCalcul, Calendar dateDerniereEcheance)
			throws Exception {

		List<EtatQtc> listEtatQtc = new ArrayList<EtatQtc>();
		listEtatQtc.add(new EtatQtc(EtatQuittance.En_instance.getId()));
		listEtatQtc.add(new EtatQtc(EtatQuittance.Attente_validation_supp
				.getId()));

		/*
		 * String hql =
		 * "select sum(COALESCE(qtc.montant,0)) from QuittanceGsr qtc " +
		 * " where qtc.refRentier=:idREnt " + " and qtc.refMouvement is null" +
		 * " and qtc.beneficiaire <> :beneficiare" +
		 * " and qtc.refNatureQuittance=:natureQtc" +
		 * " and qtc.datEtat BETWEEN :dat and :dateEcheance)" +
		 * " and qtc.refEtatQtc in (:listEtatAttente)"; Query query =
		 * getSession() .createQuery(hql) .setLong("idREnt", idRentier)
		 * .setString("beneficiare", "AT") .setParameter("natureQtc",new
		 * NatureQtcGsr(NatureQuittance.Rente_periodique.getId()))
		 * .setParameter("dat", dateCalcul) .setParameter("dateEcheance",
		 * dateDerniereEcheance) .setParameterList("listEtatAttente",
		 * listEtatQtc);
		 */
		/**
		 * arrérrages impayés décès
		 */
		/**
		 * SELECT SUM (NVL (quittanceg0_.montant, 0)) AS col_0_0_ FROM
		 * gsr_quittance quittanceg0_ WHERE quittanceg0_.idsrentier = 78 AND
		 * (quittanceg0_.idsmouvement IS NULL) AND quittanceg0_.idsnature_qtc =
		 * 25 AND quittanceg0_.datfinqtc <= to_date('2014/06/30','yyyy/mm/dd')
		 * AND (quittanceg0_.idsetat_qtc IN (4, 7))
		 */

		StringBuilder hql = new StringBuilder(
				"select sum(COALESCE(qtc.montant,0)) from QuittanceGsr qtc")
				.append(" where qtc.refRentier.id=? ")
				.append(" and qtc.refMouvement is null ")
				.append(" and qtc.refNatureQuittance=?")
				.append(" and qtc.dateFinQtc <= ?")
				.append(" and qtc.refEtatQtc in (:etatQtc)");

		Query query = getSession()
				.createQuery(hql.toString())
				.setParameter(0, idRentier)
				.setParameter(
						1,
						new NatureQtcGsr(NatureQuittance.Rente_periodique
								.getId())).setCalendar(2, dateCalcul)
				.setParameterList("etatQtc", listEtatQtc);

		return (Object) query.uniqueResult();

		/*
		 * (select max (qtc2.datEtat) from QuittanceGsr qtc2" +
		 * " where qtc2.refRentier=:idREnt " + " and qtc2.refMouvement is null"
		 * + " and qtc2.beneficiaire <> :beneficiare" +
		 * " and qtc2.refEtatQtc=:etatRegle)";
		 */

	}

	/**
	 * Retourne la date de la dernière écheance règlée pour un rentier, pour les
	 * quittance trimistrielle , a verifier !!!
	 * 
	 * @param idRentier
	 * @return Calendar
	 * @throws Exception
	 */
	public Date getDateDerniereEcheanceReglee(long idRentier)
			throws Exception {
		Date dateDerniereEcheanceReglee = null;
		StringBuilder hql = new StringBuilder(
				"select max (qtc2.dateFinQtc) from QuittanceGsr qtc2")
				.append(" where qtc2.refRentier=? and qtc2.refEtatQtc=?")
				.append(" and qtc2.refMouvement is null and qtc2.refNatureQuittance =?");

		Query query = getSession()
				.createQuery(hql.toString())
				.setParameter(0, new Rentier(idRentier))
				.setParameter(1, new EtatQtc(EtatQuittance.Reglee.getId()))
				.setParameter(2,new NatureQtcGsr(NatureQuittance.Rente_periodique.getId()));
		dateDerniereEcheanceReglee = (Date) query.uniqueResult();

		return dateDerniereEcheanceReglee;

	}
	/**
	 * Retourne la date de la dernière écheance emise pour un rentier, pour les
	 * quittance trimistrielle , a verifier !!!
	 * 
	 * @param idRentier
	 * @return Calendar
	 * @throws Exception
	 */
	public Date getDateDerniereEcheanceEmise(long idRentier)
			throws Exception {
		Date dateDerniereEcheanceReglee = null;
		StringBuilder hql = new StringBuilder(
				"select max (qtc2.dateFinQtc) from QuittanceGsr qtc2")
				.append(" where qtc2.refRentier=? and qtc2.refEtatQtc in (?,?)")
				.append(" and qtc2.refMouvement is null and qtc2.refNatureQuittance =?");

		Query query = getSession()
				.createQuery(hql.toString())
				.setParameter(0, new Rentier(idRentier))
				.setParameter(1, new EtatQtc(EtatQuittance.En_instance.getId()))
				.setParameter(2, new EtatQtc(EtatQuittance.Reglee.getId()))
				.setParameter(3,new NatureQtcGsr(NatureQuittance.Rente_periodique.getId()));
		dateDerniereEcheanceReglee = (Date) query.uniqueResult();

		return dateDerniereEcheanceReglee;

	}

	/**
	 * Retourne la date de la dernière écheance règlée pour un dossier rente
	 * 
	 * @param idRentier
	 * @return Calendar
	 * @throws Exception
	 */
	public Calendar getDateDerniereEcheanceRegleeParDossier(long idRentier)
			throws Exception {

		Calendar dateDerniereEcheanceReglee = null;
		StringBuilder hql = new StringBuilder(
				"select max (qtc2.datEtat) from QuittanceGsr qtc2")
				.append(" join qtc2.refSituationQuittanceGsr sitQtc2 where qtc2.refRentier.id in (select rentier.id from Rentier rentier"
						+ " where rentier.refDossierRente.id = (select rentier2.refDossierRente.id from Rentier rentier2 where id=:idREnt))")
				.append(" and maxelement(qtc2.refSituationQuittanceGsr)=sitQtc2 and sitQtc2.refEtatQtc=:etatRegle")
				.append(" and qtc2.refMouvement is null and qtc2.refNatureQuittance =:natureQtc")
				.append(" and qtc2.refComplementQuitatnce is null");
		Query query = getSession()
				.createQuery(hql.toString())
				.setLong("idREnt", idRentier)
				.setParameter("etatRegle",new EtatQtc(EtatQuittance.Reglee.getId()))
				.setParameter("natureQtc",new NatureQtcGsr(NatureQuittance.Rente_periodique.getId()));
		dateDerniereEcheanceReglee = (Calendar) query.uniqueResult();
		return dateDerniereEcheanceReglee;

	}

	public List getSimilarObject(Object objectBO, String... orderBy)
			throws Exception {

		// Get query
		Query query = getQuery(objectBO, orderBy);
		return query.list();
	}

	public Query getQuery(Object objectBO, String... orderBy) throws Exception {

		// Recuperer le HQl et les parametres
		Object[] objects = getHqlQuery(objectBO, orderBy);

		TreeMap<Integer, Object> values = (TreeMap) objects[1];
		StringBuilder hql = (StringBuilder) objects[0];
		// Get query
		Query query = getSession().createQuery(hql.toString());
		Set<Entry<Integer, Object>> entrys = values.entrySet();
		for (Entry<Integer, Object> curEntry : entrys) {
			query.setParameter(curEntry.getKey(), curEntry.getValue());
		}

		return query;
	}

	public Long getNbrResult(Object objectBO) throws Exception {

		// Recuperer le HQl et les parametres
		Object[] objects = getHqlQuery(objectBO);

		TreeMap<Integer, Object> values = (TreeMap) objects[1];
		StringBuilder hql = (StringBuilder) objects[0];
		String hqlSt = "select count(*) " + hql.toString();

		// Get query
		Query query = getSession().createQuery(hqlSt);
		Set<Entry<Integer, Object>> entrys = values.entrySet();
		for (Entry<Integer, Object> curEntry : entrys) {
			query.setParameter(curEntry.getKey(), curEntry.getValue());
		}

		return (Long) query.uniqueResult();
	}

	public Object[] getHqlQuery(Object objectBO, String... orderBy)
			throws Exception {

		// Recuperer le HQl
		TreeMap<Integer, Object> values = new TreeMap();
		StringBuilder hql = new StringBuilder("from ").append(
				objectBO.getClass().getName()).append(" where 1=?");
		values.put(0, 1);
		// Order by
		// Todo: à supprimer dans la prochaine livraison
		if (objectBO
				.getClass()
				.getName()
				.equalsIgnoreCase(
						"eai.devass.gsr.appli.modele.parametrage.TypeMouvement")) {
			// hql.append(" and id in (5,6,13,14,15,7)");
			// hql.append(" and id in (2,3,4,5,6,7,8,10,13,14,15,16)");
			//Param DY et NY
			//hql.append(" and id in (1,2,3,4,5,6,7,8,10,11,18,13,14,15,16,19,20)");
			//Param ZY,SY
			hql.append(" and id in (2,3,4,5,6,7,8,10,11,18,13,14,15,16,20)");

		}
		Field[] fields = commonUtils.getAllFields(objectBO.getClass(),
				new Field[] {});
		String property = null;
		boolean isLikeExpr;
		boolean isString;
		for (Field field : fields) {
			isLikeExpr = false;
			isString = false;
			property = field.getName();
			if ("serialVersionUID".equals(property)
					|| Modifier.isTransient(field.getModifiers())) {
				continue;
			}

			Object objetValue = BeanUtilsBean.getInstance().getPropertyUtils()
					.getProperty(objectBO, property);

			if (objetValue == null) {
				continue;
			}

			if (objetValue instanceof String) {
				if (((String) objetValue).equals("")) {
					continue;
				}
				isString = true;
				if (((String) objetValue).indexOf("%") >= 0) {
					isLikeExpr = true;
				}
				objetValue = ((String) objetValue).toUpperCase();
			}

			if ((objetValue instanceof Long)
					&& Long.valueOf((Long) objetValue).equals(0L)) {
				continue;
			}

			if (commonUtils.isCollection(field.getType())) {
				continue;
			}

			if (commonUtils.isReference(field.getType())) {
				hql.append(getClauseReferenceObject(property, objetValue,
						values, objectBO));
				continue;
			}

			if (isLikeExpr) {
				hql.append(" and upper(" + property + ") like ?");

			} else {
				if (isString) {
					hql.append(" and upper(" + property + ") = ?");
				} else {
					hql.append(" and " + property + " = ?");
				}
			}

			values.put(values.lastKey() + 1, objetValue);
		}

		// Order by
		if (orderBy != null && orderBy.length > 0) {
			hql.append(" order by");
			for (String curOrder : orderBy) {
				hql.append(" " + curOrder + ",");
			}
			// Supprimer le dernier ','
			hql.delete(hql.length() - 1, hql.length());

		}

		return new Object[] { hql, values };
	}

	private String getClauseReferenceObject(String property, Object refObject,
			TreeMap<Integer, Object> values, Object objetBO) throws Exception {

		StringBuilder hql = new StringBuilder();
		Field[] fields = commonUtils.getAllFields(refObject.getClass(),
				new Field[] {});
		Object value = null;
		for (Field curField : fields) {

			if ("serialVersionUID".equals(curField.getName())
					|| Modifier.isTransient(curField.getModifiers())) {
				continue;
			}

			if (commonUtils.isCollection(curField.getType())) {
				continue;
			}

			try {
				value = BeanUtilsBean.getInstance().getPropertyUtils()
						.getProperty(refObject, curField.getName());

			} catch (Exception e) {
				continue;
			}

			if (value == null) {
				continue;
			}

			if ((value instanceof Long)
					&& Long.valueOf((Long) value).equals(0L)) {
				continue;
			}

			if (commonUtils.isReference(curField.getType())) {
				// Relation cyclique
				if (objetBO.equals(value)) {
					continue;
				}

				hql.append(getClauseReferenceObject(
						property + "." + curField.getName(), value, values,
						refObject));
				continue;
			}

			hql.append(" and ").append(property).append(".")
					.append(curField.getName()).append("=?");
			values.put(values.lastKey() + 1, value);

		}

		return hql.toString();
	}

	public List<EtatMvt> getListTransitEtatMvt(TypeMouvement typeMouvement,
			Rentier rentier) throws Exception {

		StringBuilder hql = new StringBuilder(
				"select trans.refEtatCible from Rentier rent")
				.append(" join rent.refEtatRentier etatRent,TransitionEtat trans join trans.refEtatActuel etatAct")
				.append(" where rent=? and trans.refTypeMouvement=? and etatRent=etatAct");

		return getSession().createQuery(hql.toString())
				.setParameter(0, rentier).setParameter(1, typeMouvement).list();

	}

	public Utilisateur recupererUtilisateur() {
		return null;
	}

	/**
	 * Retourne le coefficient d'age
	 * 
	 * @param rentier
	 * @param dateCalcul
	 * @return
	 * @throws ExceptionMetier
	 */
	public Double getCoefficientAge(Rentier rentier, Date dateCalcul)
			throws ExceptionMetier {

		RentierManager rentierManager = (RentierManager) ServicesFactory
				.getService(RentierManager.class);
		ParametrageManager parametrageManager = (ParametrageManager) ServicesFactory
				.getService(ParametrageManager.class);
		Double coefficientAge = null;
		CoefficientAge coefficient = null;
		String typeCoefficient = null;
		Integer age = null;
		// correction sonar : Call to equals() comparing different types
		if (rentier.getDateNaissance() == null) {
			throw new ExceptionMetier(IMessageException.EXP_DATE_NAISSANCE_NULL);
		}
		age = getAgeBetweenTwoDates(rentier.getDateNaissance().getTime(), dateCalcul);
		String ageInp = TypeConverter.getInstance().intToString(age);
		try {
			typeCoefficient = rentierManager.getTypeCoefficient(rentier);
			coefficient = parametrageManager.getCoefParType(age,
					typeCoefficient);
		} catch (Exception e) {
			logger.error(EXP_COEFICIENT_AGE_INTROUVABLE, e);
			throw new ExceptionMetier(
					IMessageException.EXP_COEFICIENT_AGE_INTROUVABLE,e);

		}
		if (coefficient != null) {
			coefficientAge = coefficient.getCoefficient();
		} else {
			throw new ExceptionMetier(
					IMessageException.EXP_COEFICIENT_AGE_INTROUVABLE
							+ ", l'âge égale à " + ageInp + " ans !!");
		}
		return coefficientAge;

	}
	
	/**
	 * 
	 * WMOS: 11/11/2015
	 * EVO - formule de calcul de l’âge uniquement pour le mouvement de consignation cnra:
	 * Partie entière[(Date limite de paiement-date de naissance)/360]
	 * 
	 */
	public Double getCoefficientAgeCNRA(Rentier rentier, Date dateCalcul)
			throws ExceptionMetier {

		RentierManager rentierManager = (RentierManager) ServicesFactory
				.getService(RentierManager.class);
		ParametrageManager parametrageManager = (ParametrageManager) ServicesFactory
				.getService(ParametrageManager.class);
		Double coefficientAge = null;
		CoefficientAge coefficient = null;
		String typeCoefficient = null;
		Integer age = null;
		// correction sonar : Call to equals() comparing different types
		if (rentier.getDateNaissance() == null) {
			throw new ExceptionMetier(IMessageException.EXP_DATE_NAISSANCE_NULL);
		}
		age = getAgeBetweenTwoDatesCNRA(rentier.getDateNaissance().getTime(),
				dateCalcul);
		String ageInp = TypeConverter.getInstance().intToString(age);
		try {
			typeCoefficient = rentierManager.getTypeCoefficient(rentier);
			coefficient = parametrageManager.getCoefParType(age,
					typeCoefficient);
		} catch (Exception e) {
			logger.error(EXP_COEFICIENT_AGE_INTROUVABLE, e);
			throw new ExceptionMetier(
					IMessageException.EXP_COEFICIENT_AGE_INTROUVABLE, e);

		}
		if (coefficient != null) {
			coefficientAge = coefficient.getCoefficient();
		} else {
			throw new ExceptionMetier(
					IMessageException.EXP_COEFICIENT_AGE_INTROUVABLE
							+ ", l'âge égale à " + ageInp + " ans !!");
		}
		return coefficientAge;

	}
	private Integer getAgeBetweenTwoDatesCNRA(Date dateNaissance, Date dateCalcul) {

		return CommonGsrUtils.getInstance().getAgeBetweenTwoDatesCNRA(
				dateNaissance, dateCalcul);

	}
	/**
	 * Fin EVO : formule de calcul de l’âge
	 */
	
	/**
	 * Retoune le capitale perçu à partir de la rente trimestrielle et le
	 * coefficient d'age.
	 * 
	 * @param rente
	 * @param coefficientAge
	 * @return
	 * @throws ExceptionMetier
	 */
	public Double calculerCapitalPercu(Double rente, Double coefficientAge)
			throws ExceptionMetier {
		if (rente == null || rente == 0) {
			throw new ExceptionMetier(EXP_MONTANT_RENTE_NULL);
		}
		if (coefficientAge == null || coefficientAge == 0) {
			throw new ExceptionMetier(EXP_COEFICIENT_AGE_NULL);
		}
		return rente * 4 * coefficientAge;
	}

	/**
	 * Calcul l'age à la date de calcul
	 * 
	 * @param dateNaissance
	 * @param DateCalcul
	 * @return
	 */
	private Integer getAgeBetweenTwoDates(Date dateNaissance, Date dateCalcul) {

		return CommonGsrUtils.getInstance().getAgeBetweenTwoDates(
				dateNaissance, dateCalcul);

	}

	/**
	 * Calcule la somme des montants pour une liste de rentier
	 * 
	 * @param idRentier
	 * @param dateDernierEcheance
	 * @return String
	 * @throws ExceptionMetier
	 */
	public Double getCapitalRentiers(String idRentier,
			String dateDernierEcheance) throws ExceptionMetier {
		Double capitalCal = Double.valueOf(0);
		try {
			List<Rentier> listeRentiers = getListRentierByRentier(idRentier);
			if (listeRentiers != null && !listeRentiers.isEmpty()) {
				for (Rentier rentier : listeRentiers) {
					Double coefAge = getCoefficientAge(rentier,
							sdf.parse(dateDernierEcheance));
					capitalCal += calculerCapitalPercu(
							rentier.getMontantRenteTrimestrielle(), coefAge);
				}
			}
			return capitalCal;
		} catch (ParseException e) {
			logger.error(EXP_DATE_FORMAT, e);
			throw new ExceptionMetier(IMessageException.EXP_DATE_FORMAT, e);
		}
	}

	public List<Rentier> getListRentierByRentier(String idRentier)
			throws ExceptionMetier {
		try {
			StringBuilder hql = new StringBuilder(
					"select r from Rentier r where r.refDossierRente.id= ")
					.append("(select r1.refDossierRente.id from Rentier r1 where id=:idRentier) order by r.refAyantDroit.id");		
			Query query = getSession().createQuery(hql.toString()).setLong(
					"idRentier", Long.valueOf(idRentier));
			return query.list();

		} catch (HibernateException e) {
			logger.error(EXP_RECHERCHE, e);
			throw new ExceptionMetier(IMessageException.EXP_RECHERCHE, e);
		} catch (NumberFormatException e) {
			logger.error(EXP_RECHERCHE, e);
			throw new ExceptionMetier(IMessageException.EXP_RECHERCHE, e);
		} catch (Exception e) {
			logger.error(EXP_RECHERCHE, e);
			throw new ExceptionMetier(IMessageException.EXP_RECHERCHE, e);
		}
	}

	public Object[] getCapitalGSR(String idRentier) throws ExceptionMetier {
		try {
			StringBuilder hql = new StringBuilder(
					"select sum(r.capitalConstitutif), r.refDossierRente.id from Rentier r where r.refDossierRente.id= ")
					.append("(select r1.refDossierRente.id from Rentier r1 where id=:idRentier) group by r.refDossierRente.id");
			Query query = getSession().createQuery(hql.toString()).setLong(
					"idRentier", Long.valueOf(idRentier));
			return (Object[]) query.uniqueResult();

		} catch (HibernateException e) {
			logger.error(EXP_RECHERCHE, e);
			throw new ExceptionMetier(IMessageException.EXP_RECHERCHE, e);
		} catch (NumberFormatException e) {
			logger.error(EXP_RECHERCHE, e);
			throw new ExceptionMetier(IMessageException.EXP_RECHERCHE, e);
		} catch (Exception e) {
			logger.error(EXP_RECHERCHE, e);
			throw new ExceptionMetier(IMessageException.EXP_RECHERCHE, e);
		}
	}

	public Double getCapitalGSRyRentier(String idRentier)
			throws ExceptionMetier {
		try {
			StringBuilder hql = new StringBuilder(
					"select r.capitalConstitutif from Rentier r where r.id=?");

			Query query = getSession().createQuery(hql.toString()).setLong(0,
					Long.valueOf(idRentier));
			return (Double) query.uniqueResult();

		} catch (HibernateException e) {
			logger.error(EXP_RECHERCHE, e);
			throw new ExceptionMetier(IMessageException.EXP_RECHERCHE, e);
		} catch (NumberFormatException e) {
			logger.error(EXP_RECHERCHE, e);
			throw new ExceptionMetier(IMessageException.EXP_RECHERCHE, e);
		} catch (Exception e) {
			logger.error(EXP_RECHERCHE, e);
			throw new ExceptionMetier(IMessageException.EXP_RECHERCHE, e);
		}
	}

	public List getListRentierIdsRentiers(String idRentier)
			throws ExceptionMetier {
		try {
			StringBuilder hql = new StringBuilder(
					"select r.id from Rentier r where r.refDossierRente.id= ")
					.append("(select r1.refDossierRente.id from Rentier r1 where id=:idRentier)");
			Query query = getSession().createQuery(hql.toString()).setLong(
					"idRentier", Long.valueOf(idRentier));
			return query.list();
		} catch (HibernateException e) {
			logger.error(EXP_RECHERCHE, e);
			throw new ExceptionMetier(IMessageException.EXP_RECHERCHE, e);
		} catch (NumberFormatException e) {
			logger.error(EXP_RECHERCHE, e);
			throw new ExceptionMetier(IMessageException.EXP_RECHERCHE, e);
		} catch (Exception e) {
			logger.error(EXP_RECHERCHE, e);
			throw new ExceptionMetier(IMessageException.EXP_RECHERCHE, e);
		}
	}

	/**
	 * Permet de récuperer la liste des rentiers à partir de l'id rentier
	 * principal
	 * 
	 * @param idRentier
	 * @return
	 * @throws ExceptionMetier
	 */
	// public List<Rentier> getListRentiers(String idRentier)
	// throws ExceptionMetier {
	// try {
	// StringBuilder hql = new StringBuilder(
	// "select r from Rentier r where r.refDossierRente.id= ")
	// .append("(select r1.refDossierRente.id from Rentier r1 where id=:idRentier)");
	// Query query = getSession().createQuery(hql.toString()).setLong(
	// "idRentier", Long.valueOf(idRentier));
	// return query.list();
	// } catch (HibernateException e) {
	// logger.error(EXP_RECHERCHE, e);
	// throw new ExceptionMetier(IMessageException.EXP_RECHERCHE);
	// } catch (NumberFormatException e) {
	// logger.error(EXP_RECHERCHE, e);
	// throw new ExceptionMetier(IMessageException.EXP_RECHERCHE);
	// } catch (Exception e) {
	// logger.error(EXP_RECHERCHE, e);
	// throw new ExceptionMetier(IMessageException.EXP_RECHERCHE);
	// }
	// }

	public void historiser(IHistorisable object, long numeroVersion)
			throws Exception {

		HistoryItem history = new HistoryItem();

		// Il faut lazy l'objet
		JournalisationUtils.getInstance().lazyObject(object, object);

		history.setSerialisation(serializeObjectToBytes(object));
		history.setIdHistorisable(object.getId());
		history.setNumeroVersion(numeroVersion);
		history.setNomClasse(((Object) object).getClass().getName());
		history.setUtilisateur(object.getOperateur());
		history.setDateVersion(Calendar.getInstance());
		history.setAction(object.getContextRegleGestionEnum().getContext());
		getSession().save(history);
	}

	public List<QuittanceGsr> listQuittancesReglees(Rentier rentier,
			Calendar dateDebut, Calendar dateFin) throws Exception {

		StringBuilder hql = new StringBuilder(
				"select qtc from QuittanceGsr qtc join qtc.refRentier r")
				.append(" join qtc.refEtatQtc etat where r=:rent")
				.append(" and etat=:etatRegle and qtc.datEtat BETWEEN :datD and :datF");

		Query query = getSession()
				.createQuery(hql.toString())
				.setParameter("rent", rentier)
				.setParameter("etatRegle",
						new EtatQtc(EtatQuittance.Reglee.getId()))
				.setParameter("datD", dateDebut).setParameter("datF", dateFin);

		return query.list();

	}
	
	public Double montantQuittancesPeriodiqueReglees(Rentier rentier,
			Calendar dateDebut, Calendar dateFin) throws Exception {

		StringBuilder hql = new StringBuilder(
				"select sum(qtc.montant) from QuittanceGsr qtc join qtc.refRentier r")
				.append(" join qtc.refEtatQtc etat where r=:rent")
				.append(" and etat=:etatRegle and qtc.datEtat BETWEEN :datD and :datF")
				.append(" and qtc.refNatureQuittance=:natQtc");

		Query query = getSession()
				.createQuery(hql.toString())
				.setParameter("rent", rentier)
				.setParameter("etatRegle", new EtatQtc(EtatQuittance.Reglee.getId()))
				.setParameter("datD", dateDebut).setParameter("datF", dateFin)
				.setParameter("natQtc", new NatureQtcGsr(NatureQuittance.Rente_periodique.getId()));

		return (Double) query.uniqueResult();

	}


	/**
	 * Retourne le nombre de quittances règles entre deux dates
	 * 
	 * @param rentier
	 * @param dateDebut
	 * @param dateFin
	 * @return
	 * @throws Exception
	 */
	public Integer getNombreQuittancesReglees(Rentier rentier,
			Calendar dateDebut, Calendar dateFin) throws Exception {

		Integer nbreQuittances = 0;
		StringBuilder hql = new StringBuilder(
				"select count(qtc) from QuittanceGsr qtc join qtc.refRentier r")
				.append(" join qtc.refSituationQuittanceGsr sitQtc where r=:rent")
				.append(" and maxelement(qtc.refSituationQuittanceGsr)=sitQtc and sitQtc.refEtatQtc=:etatRegle")
				.append(" and qtc.datEtat BETWEEN :datD and :datF");

		Query query = getSession()
				.createQuery(hql.toString())
				.setParameter("rent", rentier)
				.setParameter("etatRegle",
						new EtatQtc(EtatQuittance.Reglee.getId()))
				.setParameter("datD", dateDebut).setParameter("datF", dateFin);

		if (query.list() != null) {

			nbreQuittances = query.list().size();

		}

		return nbreQuittances;

	}

	private byte[] serializeObjectToBytes(Object object) {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutput out = null;
		try {
			out = new ObjectOutputStream(bos);
			out.writeObject(object);
			byte[] bytes = bos.toByteArray();
			return bytes;
		} catch (IOException e) {
			logger.error("problème technique",e);
		}
		return null;
	}

	public Date getEcheanceTrimestre(Date date) {
		if (date == null) {
			return null;
		}
		int mois = Integer.valueOf(DateUtile.dateToString("MM", date));
		String annee = DateUtile.dateToString("yyyy", date);
		if (mois >= 1 && mois <= 3) {
			return DateUtile.getDate("dd/MM/yyyy", "31/03/" + annee);
		} else if (mois >= 4 && mois <= 6) {
			return DateUtile.getDate("dd/MM/yyyy", "30/06/" + annee);
		} else if (mois >= 7 && mois <= 9) {
			return DateUtile.getDate("dd/MM/yyyy", "30/09/" + annee);
		} else if (mois >= 10 && mois <= 12) {
			return DateUtile.getDate("dd/MM/yyyy", "31/12/" + annee);
		}
		return null;
	}

	public Mouvement getDernierMvtValiderConsignationCNRA(Long idRentier)
			throws Exception {
		String hql = "select mvt from SituationMouvement situation_mouvement, eai.devass.gsr.appli.modele.metier.mouvements.Mouvement mvt, Rentier rentier "
				+ " where rentier.id in ("
				+ idRentier
				+ ") and rentier.id = mvt.refRentier and mvt.refTypeMouvement = 2 "
				+ " and mvt.refEtatMvt=2 and mvt.id = situation_mouvement.refMouvement and situation_mouvement.dateEtat in "
				+ "(select max(situation.dateEtat)from SituationMouvement situation, eai.devass.gsr.appli.modele.metier.mouvements.Mouvement m, Rentier r "
				+ " where r.id in ("
				+ idRentier
				+ ") and r.id = m.refRentier and m.refTypeMouvement = 2 and m.refEtatMvt=2 "
				+ " and m.id = situation.refMouvement)";
		Query query = getSession().createQuery(hql);
		return (Mouvement) query.uniqueResult();
	}

	/**
	 * Retourne la date de la dernière écheance règlée pour un dossier rente
	 * 
	 * @param idRentier
	 * @return Calendar
	 * @throws Exception
	 */
	public Calendar getDateDerniereEcheanceEmiseParDossier(long idRentier)
			throws Exception {

		Calendar dateDerniereEcheanceEmise = null;
		StringBuilder hql = new StringBuilder(
				"select max (qtc2.datEtat) from QuittanceGsr qtc2")
				.append(" join qtc2.refSituationQuittanceGsr sitQtc2 where qtc2.refRentier.id in (select rentier.id from Rentier rentier"
						+ " where rentier.refDossierRente.id = (select rentier2.refDossierRente.id from Rentier rentier2 where rentier2.id="
						+ idRentier + "))")
				.append(" and maxelement(qtc2.refSituationQuittanceGsr)=sitQtc2 and sitQtc2.refEtatQtc=:etatRegle");
		Query query = getSession().createQuery(hql.toString())
		// .setLong("idREnt", idRentier)
				.setParameter("etatRegle",
						new EtatQtc(EtatQuittance.Reglee.getId()));

		dateDerniereEcheanceEmise = (Calendar) query.uniqueResult();
		return dateDerniereEcheanceEmise;

	}

	public Integer getDaysBetweenTwoDates(Date debut, Date fin) {
		if (debut.after(fin)) {
			return 0;
		}
		String dateDebut = DateUtile.dateToString("dd/MM/yyyy", debut);
		int jourDebut = Integer.valueOf(dateDebut.substring(0, 2));
		int moisDebut = Integer.valueOf(dateDebut.substring(3, 5));
		int anneeDebut = Integer.valueOf(dateDebut.substring(6, 10));

		String dateFin = DateUtile.dateToString("dd/MM/yyyy", fin);
		int jourFin = Integer.valueOf(dateFin.substring(0, 2));
		int moisFin = Integer.valueOf(dateFin.substring(3, 5));
		int anneeFin = Integer.valueOf(dateFin.substring(6, 10));

		int result = (anneeFin - anneeDebut) * 360;

		if (moisDebut > moisFin) {
			result = result - 30 * (moisDebut - moisFin);
		} else {
			result = result + 30 * (moisFin - moisDebut);
		}

		if (jourDebut > jourFin) {
			result = result - (jourDebut - jourFin);
		} else {
			result = result + (jourFin - jourDebut);
		}

		return result;
	}

	public Double sommeQuittancesProrataRegleesParRentier(long listRentier)
			throws Exception {

		String hql = "select sum(COALESCE(qtc.montant,0)) from QuittanceGsr qtc,Rentier r,eai.devass.gsr.appli.modele.metier.mouvements.Mouvement mvt "
				+ " where r.id in (:listRentier) and r.id = mvt.refRentier "
				+ "and mvt.id = qtc.refMouvement and mvt.refTypeMouvement = 4 and mvt.refEtatMvt = 1 and qtc.refEtatQtc = 1)";
		Query query = getSession().createQuery(hql).setLong("listRentier",listRentier);

		return (Double) query.uniqueResult();

	}

	public Double sommeQuittancesProrataReglees(List<Rentier> listRentier)
			throws Exception {

		String hql = "select sum(COALESCE(qtc.montant,0)) from QuittanceGsr qtc,Rentier r,eai.devass.gsr.appli.modele.metier.mouvements.Mouvement mvt "
				+ " where r.id in (:listRentier) and mvt.refRentier in(:listRentier) "
				+ "and mvt.id = qtc.refMouvement and mvt.refTypeMouvement = 4 and mvt.refEtatMvt = 1 and qtc.refEtatQtc = 1)";
		Query query = getSession().createQuery(hql).setParameterList(
				"listRentier", listRentier);

		return (Double) query.uniqueResult();

	}

	public Calendar getDerniereDateSuspension(long idRentier) throws Exception {
		StringBuilder hql = new StringBuilder(
				"select max(id) from eai.devass.gsr.appli.modele.metier.mouvements.Mouvement mvt where mvt.refRentier.id="
						+ String.valueOf(idRentier)
						+ " and mvt.refTypeMouvement = 13 and mvt.refEtatMvt=2");
		Query query = getSession().createQuery(hql.toString());
		Long idMvtSuspension = (Long) query.uniqueResult();
		if (idMvtSuspension != null) {
			MvtSuspension mvtSuspension = (MvtSuspension) getSession().get(
					MvtSuspension.class, idMvtSuspension);
			return mvtSuspension.getDatSuspension();
		}
		return null;
	}

	public Object[] sommeQuittancesTrimReglees(long idRentier,
			Calendar nouvDateDepartRente) throws Exception {

		String hql = "select sum(COALESCE(qtc.montant,0)),count(qtc.exercice) from QuittanceGsr qtc "
				+ " where qtc.refRentier=:idREnt "
				+ " and qtc.refMouvement is null"
				+ " and qtc.beneficiaire <> :beneficiare"
				+ " and qtc.refEtatQtc=:etatRegle"
				+ " and qtc.datEtat BETWEEN :dat and (select max (qtc2.datEtat) from QuittanceGsr qtc2"
				+ " where qtc2.refRentier=:idREnt "
				+ " and qtc2.refMouvement is null"
				+ " and qtc2.beneficiaire <> :beneficiare"
				+ " and qtc2.refEtatQtc=:etatRegle)";
		Query query = getSession()
				.createQuery(hql)
				.setLong("idREnt", idRentier)
				.setString("beneficiare", "AT")
				.setParameter("etatRegle",
						new EtatQtc(EtatQuittance.Reglee.getId()))
				.setParameter("dat", nouvDateDepartRente);

		return (Object[]) query.uniqueResult();

	}

	public Calendar getMaxDateQuittancesTrimReglees(long idRentier)
			throws Exception {
		Calendar maxDateQuittancesTrimReglees = null;
		StringBuilder hql = new StringBuilder(
				"select max (qtc2.datEtat) from QuittanceGsr qtc2"
						+ " where qtc2.refRentier=:idREnt "
						+ " and qtc2.refMouvement is null"
						+ " and qtc2.beneficiaire <> :beneficiare"
						+ " and qtc2.refEtatQtc=:etatRegle");
		Query query = getSession()
				.createQuery(hql.toString())
				.setLong("idREnt", idRentier)
				.setString("beneficiare", "AT")
				.setParameter("etatRegle",
						new EtatQtc(EtatQuittance.Reglee.getId()));
		maxDateQuittancesTrimReglees = (Calendar) query.uniqueResult();
		return maxDateQuittancesTrimReglees;

	}

	public HistoryItem getLastItemHistory(Long idObject, Class clazz)
			throws Exception {

		StringBuilder hql = new StringBuilder(
				"select i from eai.devass.commun.appli.modele.HistoryItem i where i.idHistorisable=?")
				.append(" and i.nomClasse=? order by id desc");

		return (HistoryItem) getSession().createQuery(hql.toString())
				.setMaxResults(1).setLong(0, idObject)
				.setString(1, clazz.getName()).uniqueResult();
	}

	public List<NatureQtcGsr> listNatureQtcManuelle(Long[] natQtc)
			throws Exception {

		StringBuilder hql = new StringBuilder(
				"select nat from eai.devass.gsr.appli.modele.parametrage.NatureQtcGsr nat")
				.append(" where nat.id in (:natQtc)");

		Query query = getSession().createQuery(hql.toString())
				.setParameterList("natQtc", natQtc);
		return query.list();

	}

	public List<TypeRgltGsr> listTypeReglementQtcManuelle(Long[] typeRgl)
			throws Exception {

		StringBuilder hql = new StringBuilder(
				"select t from eai.devass.gsr.appli.modele.parametrage.TypeRgltGsr t")
				.append(" where t.id in (:typeRgl)");

		Query query = getSession().createQuery(hql.toString())
				.setParameterList("typeRgl", typeRgl);
		return query.list();

	}

	/**
	 * Retourne la date de la dernière écheance règlée pour un rentier, pour les
	 * quittance trimistrielle , a verifier !!!
	 * 
	 * @param idRentier
	 * @return Calendar
	 * @throws Exception
	 */
	public Calendar getDerniereEcheanceRegleeAvantUneDate(long idRentier,
			Calendar date) throws Exception {
		Calendar dateDerniereEcheanceReglee = null;
		StringBuilder hql = new StringBuilder(
				"select max (qtc.datEtat) from QuittanceGsr qtc")
				.append(" where qtc.refRentier=? and qtc.refEtatQtc=?")
				.append(" and qtc.datEtat < ?")
				.append(" and qtc.refMouvement is null");

		Query query = getSession().createQuery(hql.toString())
				.setParameter(0, new Rentier(idRentier))
				.setParameter(1, new EtatQtc(EtatQuittance.Reglee.getId()))
				.setParameter(2, date);

		dateDerniereEcheanceReglee = (Calendar) query.uniqueResult();
		return dateDerniereEcheanceReglee;

	}

	public Date getDateAccident(Rentier rentier) throws Exception {

		StringBuilder hql = new StringBuilder(
				"select e.dateAccident from Rentier r join")
				.append(" r.refDossierRente d join d.refSinistre s join s.refEvenement e where r=?");

		return (Date) getSession().createQuery(hql.toString())
				.setParameter(0, rentier).uniqueResult();

	}

	/*
	 * Debut Evo30/01/2014 Mise à jour capital
	 * 
	 * Arrérages perçus= somme des quittances trimestrielles réglées entre la
	 * date de départ de la nouvelle rente et le dernier trimestre émis +
	 * arrérages avant constitution réglés
	 */
	public double doSommeArrerageRegle(long idRentier)
			throws HibernateException, ProcessEntiteException, EntiteException,
			PersistenceException {
		Long idDossierRente = doGetIdDossier(idRentier);
		Long idSinistre = doGetIdSinistre(idDossierRente);
		if (idSinistre != null && idSinistre != 0) {
			String hql = "select sum(COALESCE(dr.montant,0)) from Sinistre s,Reglement r,DetailReglement dr where s.id="
					+ idSinistre
					+ " and s.id = r.refSinistre and r.id = dr.refReglement and dr.refPrestation.code = 21 group by s.id order by s.id";
			IPersistenceService dao = (IPersistenceService) ServicesFactory
					.getService(IPersistenceService.class);
			Query query = ((Session) dao.getSession()).createQuery(hql);
			if (!query.list().isEmpty()) {
				Double res = ((Double) query.uniqueResult()).doubleValue();
				// correction sonar :Redundant nullcheck of value known to be
				// non-null
				return res;
			} else {
				return Double.valueOf(0);
			}

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

	public Long doGetIdDossier(Long idRentier) throws HibernateException,
			ProcessEntiteException, EntiteException, PersistenceException {
		String hql = "Select r.refDossierRente from  Rentier r where r.id ="
				+ idRentier;
		IPersistenceService dao = (IPersistenceService) ServicesFactory
				.getService(IPersistenceService.class);
		Query query = ((Session) dao.getSession()).createQuery(hql);
		if (!query.list().isEmpty()) {
			DossierRente res = (DossierRente) (query.uniqueResult());
			if (res != null) {
				return res.getId();
			} else {
				return Long.valueOf(0);
			}
		} else {
			return Long.valueOf(0);
		}
	}
	/*
	 * Fin Evo30/01/2014 Mise à jour capital
	 */
	
	public Double sommeCapitalCalcule(List<Long> listRentier)
	throws Exception {
		String hql = "select sum(COALESCE(mouvements.capitalCalcule,0)) from eai.devass.gsr.appli.modele.metier.mouvements.Mouvement mouvements"
			+ " where mouvements.id in (select mouvement.id from SituationMouvement situation,eai.devass.gsr.appli.modele.metier.mouvements.Mouvement mouvement,Rentier rentier"
			+ " where (rentier.id in (:listRentier)) and (mouvement.refRentier in (:listRentier)) and mouvement.refTypeMouvement = 2"
			+ " and mouvement.refEtatMvt = 2 and mouvement.id = situation.refMouvement and (situation.dateEtat in (select max (sit.dateEtat)"
			+ " from SituationMouvement sit,eai.devass.gsr.appli.modele.metier.mouvements.Mouvement mvt,Rentier rt where (rt.id in (:listRentier)) and (mvt.refRentier in (:listRentier))"
		    + " and mvt.refTypeMouvement = 2 and mvt.refEtatMvt = 2 and mvt.id = sit.refMouvement))group by mouvement.id)";
		Query query = getSession().createQuery(hql).setParameterList("listRentier", listRentier);
		return (Double) query.uniqueResult();
		
}
	public Double sommeMntCNRA(List<Long> listRentier)
	throws Exception {
		String hql = "select sum(COALESCE(mouvements.mntCNRA,0)) from eai.devass.gsr.appli.modele.metier.mouvements.Mouvement mouvements"
			+ " where mouvements.id in (select mouvement.id from SituationMouvement situation,eai.devass.gsr.appli.modele.metier.mouvements.Mouvement mouvement,Rentier rentier"
			+ " where (rentier.id in (:listRentier)) and (mouvement.refRentier in (:listRentier)) and mouvement.refTypeMouvement = 2"
			+ " and mouvement.refEtatMvt = 2 and mouvement.id = situation.refMouvement and (situation.dateEtat in (select max (sit.dateEtat)"
			+ " from SituationMouvement sit,eai.devass.gsr.appli.modele.metier.mouvements.Mouvement mvt,Rentier rt where (rt.id in (:listRentier)) and (mvt.refRentier in (:listRentier))"
		    + " and mvt.refTypeMouvement = 2 and mvt.refEtatMvt = 2 and mvt.id = sit.refMouvement))group by mouvement.id)";
		Query query = getSession().createQuery(hql).setParameterList("listRentier", listRentier);
		return (Double) query.uniqueResult();
		}

	public Double getCoefficientAgeCNRAReg (Rentier rentier, Date dateCalcul)
	throws ExceptionMetier {		
	RentierManager rentierManager = (RentierManager) ServicesFactory
				.getService(RentierManager.class);
		ParametrageManager parametrageManager = (ParametrageManager) ServicesFactory
				.getService(ParametrageManager.class);
		Double coefficientAge = null;
		CoefficientAge coefficient = null;
		String typeCoefficient = null;
		Integer age = null;
		// correction sonar : Call to equals() comparing different types
		if (rentier.getDateNaissance() == null) {
			throw new ExceptionMetier(IMessageException.EXP_DATE_NAISSANCE_NULL);
		}
		age = getAgeBetweenTwoDatesCNRA(rentier.getDateNaissance().getTime(),
				dateCalcul);
		String ageInp = TypeConverter.getInstance().intToString(age);
		try {
			typeCoefficient = rentierManager.getTypeCoefficientCNRA(rentier);
			coefficient = parametrageManager.getCoefParType(age,
					typeCoefficient);
		} catch (Exception e) {
			logger.error(EXP_COEFICIENT_AGE_INTROUVABLE, e);
			throw new ExceptionMetier(
					IMessageException.EXP_COEFICIENT_AGE_INTROUVABLE, e);

		}
		if (coefficient != null) {
			coefficientAge = coefficient.getCoefficient();
		} else {
			throw new ExceptionMetier(
					IMessageException.EXP_COEFICIENT_AGE_INTROUVABLE
							+ ", l'âge égale à " + ageInp + " ans !!");
		}
		return coefficientAge;

	}
}
