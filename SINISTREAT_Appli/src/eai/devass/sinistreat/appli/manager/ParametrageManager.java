package eai.devass.sinistreat.appli.manager;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Types;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.eai.org.accesseur.valueObject.ConsultationVO;
import com.eai.org.accesseur.valueObject.PointVenteVO;

import eai.devass.commun.appli.exeptions.ExceptionMetier;
import eai.devass.gsr.appli.modele.parametrage.EtatRentier;
import eai.devass.gsr.appli.modele.parametrage.MotifManuelSuspensionRente;
import eai.devass.gsr.appli.modele.parametrage.Nationalite;
import eai.devass.gsr.appli.modele.parametrage.NatureProthese;
import eai.devass.gsr.appli.modele.parametrage.SituationRentier;
import eai.devass.gsr.appli.modele.parametrage.Societe;
import eai.devass.gsr.appli.modele.parametrage.SortGsr;
import eai.devass.gsr.appli.modele.parametrage.TypeMouvement;
import eai.devass.gsr.appli.modele.parametrage.TypeMvtProthese;
import eai.devass.missionnement.valueobjects.parametrage.PrestataireVO;
import eai.devass.services.IAppelService;
import eai.devass.services.ServicesExternes;
import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.manager.rest.RestManager;
import eai.devass.sinistreat.appli.modele.metier.conciliation.SinMotifOffre;
import eai.devass.sinistreat.appli.modele.metier.instruction.PieceAt;
import eai.devass.sinistreat.appli.modele.metier.instruction.RejetAt;
import eai.devass.sinistreat.appli.modele.metier.sinistre.AyantDroit;
import eai.devass.sinistreat.appli.modele.metier.sinistre.NotificationSms;
import eai.devass.sinistreat.appli.modele.metier.sinistre.Sinistre;
import eai.devass.sinistreat.appli.modele.parametrage.Assurance;
import eai.devass.sinistreat.appli.modele.parametrage.Banque;
import eai.devass.sinistreat.appli.modele.parametrage.Barreau;
import eai.devass.sinistreat.appli.modele.parametrage.Cause;
import eai.devass.sinistreat.appli.modele.parametrage.ChefGreffier;
import eai.devass.sinistreat.appli.modele.parametrage.CieCondamnee;
import eai.devass.sinistreat.appli.modele.parametrage.CoefficientAge;
import eai.devass.sinistreat.appli.modele.parametrage.CompagnieAdverse;
import eai.devass.sinistreat.appli.modele.parametrage.CourrierHybride;
import eai.devass.sinistreat.appli.modele.parametrage.CourrierHybrideAvisContreVisite;
import eai.devass.sinistreat.appli.modele.parametrage.CourrierHybrideAvisSuspension;
import eai.devass.sinistreat.appli.modele.parametrage.CourrierHybrideContreVisite;
import eai.devass.sinistreat.appli.modele.parametrage.CourrierHybrideQuittance;
import eai.devass.sinistreat.appli.modele.parametrage.CourrierHybrideOpposition;
import eai.devass.sinistreat.appli.modele.parametrage.CourrierHybrideOuvertureRente;
import eai.devass.sinistreat.appli.modele.parametrage.CourrierHybrideReclamation;
import eai.devass.sinistreat.appli.modele.parametrage.Decision;
import eai.devass.sinistreat.appli.modele.parametrage.DegreParente;
import eai.devass.sinistreat.appli.modele.parametrage.EmailIntermediaire;
import eai.devass.sinistreat.appli.modele.parametrage.EtatRecours;
import eai.devass.sinistreat.appli.modele.parametrage.EtatSin;
import eai.devass.sinistreat.appli.modele.parametrage.GestionnaireRelance;
import eai.devass.sinistreat.appli.modele.parametrage.GestionnaireTraitement;
import eai.devass.sinistreat.appli.modele.parametrage.ImpactRubriqueReserve;
import eai.devass.sinistreat.appli.modele.parametrage.InitialeUtilisateur;
import eai.devass.sinistreat.appli.modele.parametrage.Intermediaire;
import eai.devass.sinistreat.appli.modele.parametrage.Juridiction;
import eai.devass.sinistreat.appli.modele.parametrage.MedecinTraitant;
import eai.devass.sinistreat.appli.modele.parametrage.NatureAudience;
import eai.devass.sinistreat.appli.modele.parametrage.NatureDossier;
import eai.devass.sinistreat.appli.modele.parametrage.NatureEcheance;
import eai.devass.sinistreat.appli.modele.parametrage.NatureProcedure;
import eai.devass.sinistreat.appli.modele.parametrage.Palier;
import eai.devass.sinistreat.appli.modele.parametrage.ParamMail;
import eai.devass.sinistreat.appli.modele.parametrage.Pays;
import eai.devass.sinistreat.appli.modele.parametrage.PoliceUnivers;
import eai.devass.sinistreat.appli.modele.parametrage.Prestataire;
import eai.devass.sinistreat.appli.modele.parametrage.Situation;
import eai.devass.sinistreat.appli.modele.parametrage.SortJugement;
import eai.devass.sinistreat.appli.modele.parametrage.TiersSaisi;
import eai.devass.sinistreat.appli.modele.parametrage.TransactionEtat;
import eai.devass.sinistreat.appli.modele.parametrage.Tribunal;
import eai.devass.sinistreat.appli.modele.parametrage.TypeAccident;
import eai.devass.sinistreat.appli.modele.parametrage.TypeBeneficiaire;
import eai.devass.sinistreat.appli.modele.parametrage.TypeGarantie;
import eai.devass.sinistreat.appli.modele.parametrage.TypeJuridiction;
import eai.devass.sinistreat.appli.modele.parametrage.TypeMaladie;
import eai.devass.sinistreat.appli.modele.parametrage.TypeReglement;
import eai.devass.sinistreat.appli.modele.parametrage.TypeRente;
import eai.devass.sinistreat.appli.modele.parametrage.TypeTribunal;
import eai.devass.sinistreat.appli.modele.parametrage.Ville;
import eai.devass.sinistreat.appli.modele.parametrage.Zone;
import eai.devass.sinistreat.appli.restClient.IRestClient;
import eai.devass.sinistreat.appli.restClient.RestClient;
import eai.devass.sinistreat.appli.modele.parametrage.PieceReglement;
import eai.devass.sinistreat.appli.utils.ConverterTools;
import eai.devass.sinistreat.appli.utils.Fonctions;
import eai.devass.sinistreat.appli.utils.HibernateTools;
import eai.devass.sinistreat.appli.utils.IConstantes;
import eai.devass.sinistreat.appli.utils.Tools;
import eai.devass.sinistreat.appli.utils.exception.IMessageException;
import eai.devass.sinistreat.appli.utils.exception.IMessageInfo;
import eai.devass.sinistreat.appli.valueobjects.metier.sinistre.SinistreVO;
import eai.devass.sinistreat.appli.valueobjects.parametrage.CourrierHybrideVO;
import eai.devass.sinistreat.appli.valueobjects.serialisation.PagerVO;
import ma.co.omnidata.framework.services.core.ServicesFactory;
import ma.co.omnidata.framework.services.dao.IPersistenceService;
import ma.co.omnidata.framework.services.dao.PersistenceException;
import ma.co.omnidata.framework.services.entites.EntiteException;
import ma.co.omnidata.framework.services.entites.EntiteManagerAbst;
import ma.co.omnidata.framework.services.entites.IEntite;
import ma.co.omnidata.framework.services.log.ILog;

@SuppressWarnings("all")
public class ParametrageManager extends EntiteManagerAbst implements IConstantes, IMessageException, IMessageInfo {

	public IPersistenceService dao = (IPersistenceService) ServicesFactory.getService(IPersistenceService.class);
	private IAppelService servicesExternes = (IAppelService) ServicesFactory.getService(IAppelService.class);
	private RestManager kappaClient = (RestManager) ServicesFactory.getService(RestManager.class);
	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	DateFormat dateFormaty = new SimpleDateFormat("yyyyMMdd");
	ConverterTools converterTools = ConverterTools.getInstance();
	private Logger logger = Logger.getLogger("loggerSINAT");

	private Query getListObjectQuery(Object objectBO, boolean withRef, boolean isDatesIn) throws Exception {

		int indicateur = 0;
		String ClassName = objectBO.getClass().getName();
		if (ClassName.indexOf("$") != -1) {
			ClassName = ClassName.substring(0, ClassName.indexOf("$"));
		}
		StringBuffer bufHQL = new StringBuffer();
		bufHQL.append("from " + ClassName + " where 1=1 ");

		Map values = new HashMap();
		int position = 0;
		// Field[] fields = entiteBO.getClass().getDeclaredFields();
		Field[] fields = converterTools.getAllFields(objectBO.getClass());
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			String property = field.getName();
			if ("serialVersionUID".equals(property)) {
				continue;
			}

			if (property.startsWith("CGLIB")) {
				continue;
			}

			Object ObjetValue = BeanUtilsBean.getInstance().getPropertyUtils().getProperty(objectBO, property);
			if (ObjetValue != null) {
				if (!converterTools.isCollection(field.getType()) && !field.getType().equals(Date.class)) {
					if (field.getType().equals(Boolean.class) || field.getType().equals(Long.class)) {
						bufHQL.append(" and " + property + " = ?");
						values.put(position++, ObjetValue);
						if (!withRef) {
							indicateur = 1;
						}

					} else if (converterTools.isReference(field.getType()) && withRef) {
						Collection col = getListObjectNoRef(ObjetValue, isDatesIn);
						if (col != null && col.size() > 0) {
							bufHQL.append(" and (");
						}
						int comp = 0;
						if (col != null) {
							for (Iterator iter = col.iterator(); iter.hasNext();) {
								Object refObjetValue = (Object) iter.next();
								if (comp > 0) {
									bufHQL.append(" or ");
								}
								bufHQL.append(" " + property + " = ?");
								values.put(position++, refObjetValue);
								comp++;
							}
						}
						if (col != null && col.size() > 0) {
							bufHQL.append(" )");
						} else {
							if (!Tools.isNullObjectFields(ObjetValue)) {
								bufHQL.append("and 1=2 ");
							}

						}

						if (!withRef) {
							indicateur = 1;
						}

					} else if (!converterTools.isReference(field.getType()) && ObjetValue.toString().length() > 0) {
						bufHQL.append(" and " + property + " like '"
								+ ObjetValue.toString().replace('*', '%').replace("'", "''") + "'");
						if (!withRef) {
							indicateur = 1;
						}
					}

				}
			}
		}

		if (objectBO instanceof IEntite) {

		}
		String HQL = bufHQL.toString();
		Query query = getSession().createQuery(HQL);

		for (int i = 0; i < position; i++) {
			System.out.println(i);
			query.setParameter(i, values.get(i));

		}
		if (!withRef && indicateur == 0) {
			query = null;
		}

		return query;
	}

	public Collection getListObjectNoRef(Object objectBO, boolean isDatesIn) throws Exception {

		List<Object> list = new ArrayList<Object>();
		if (objectBO == null) {
			return list;
		}
		Query query = this.getListObjectQuery(objectBO, false, isDatesIn);
		if (query == null) {
			return new ArrayList();
		}

		return query.list();
	}

	public Collection getListObjectSearch(Object... objects) throws Exception {

		Object objectBO = objects[0];

		List<Object> list = new ArrayList<Object>();
		if (objectBO == null) {
			return list;
		}
		Query query = this.getListObjectQuery(objectBO, true, true);

		if (query == null) {
			return new ArrayList();
		}

		// Vérifier si le PagerVO est un parametre d'entré
		PagerVO pagerVO = (objects.length == 2) ? pagerVO = (PagerVO) objects[1] : null;

		if (pagerVO != null) {
			return this.getPartCollectionByCondition(query, Integer.valueOf(pagerVO.getNumPage()),
					Integer.valueOf(pagerVO.getPageSize()));
		} else {
			return query.list();
		}
	}

	public Collection getListObject(Object... objects) throws Exception {

		Object objectBO = objects[0];

		List<Object> list = new ArrayList<Object>();
		if (objectBO == null) {
			return list;
		}
		Query query = this.getListObjectQuery(objectBO, true, false);

		if (query == null) {
			return new ArrayList();
		}

		// Vérifier si le PagerVO est un parametre d'entré
		PagerVO pagerVO = (objects.length == 2) ? pagerVO = (PagerVO) objects[1] : null;

		if (pagerVO != null) {
			return this.getPartCollectionByCondition(query, Integer.valueOf(pagerVO.getNumPage()),
					Integer.valueOf(pagerVO.getPageSize()));
		} else {
			return query.list();
		}
	}

	private List getPartCollectionByCondition(Query query, int page, int pageSize) throws PersistenceException {

		if (query != null) {
			query.setFirstResult(page * pageSize);
			query.setMaxResults(pageSize);
			return query.list();
		} else {
			return null;
		}
	}

	public Integer getNombreObject(Object objectBO, boolean withRef, boolean isDatesIn) throws Exception {
		Query query = this.getListObjectQuery(objectBO, withRef, isDatesIn);
		if (query == null) {
			return Integer.valueOf(0);
		}
		List list = query.list();
		if (list != null && list.size() != 0) {
			return Integer.valueOf(list.size());
		} else {
			return Integer.valueOf(0);
		}
	}

	public IEntite getEntiteByKey(IEntite entiteBO) throws Exception {

		StringBuffer HQL = new StringBuffer();
		HQL.append("from ").append(entiteBO.getClass().getName()).append(" where 1=1 ");

		if (entiteBO.getId() != 0) {
			HQL.append(" and id = " + entiteBO.getId());
		}
		if (entiteBO instanceof Prestataire) {
			Prestataire prestataire = (Prestataire) entiteBO;

			HQL.append(" and code = '" + prestataire.getCode() + "'");

		}

		Query query = getSession().createQuery(HQL.toString());

		IEntite objet = (IEntite) query.uniqueResult();
		return objet;
	}

	public Object findObject(Object object) throws Exception {
		StringBuffer bufHQL = new StringBuffer();
		bufHQL.append("from " + object.getClass().getName() + " where 1=1");

		Map values = new HashMap();
		int position = 0;
		Field[] fields = object.getClass().getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			String property = field.getName();
			if ("serialVersionUID".equals(property)) {
				continue;
			}

			Object refObjet = BeanUtilsBean.getInstance().getPropertyUtils().getProperty(object, property);
			if (refObjet != null) {
				if (!converterTools.isCollection(field.getType())) {
					if (converterTools.isReference(field.getType())) {
						bufHQL.append(" and " + property + " = ?");
						values.put(position, refObjet);
						position++;
					} else {
						if (refObjet.toString().length() > 0) {
							bufHQL.append(" and " + property + " like '" + refObjet.toString().replace('*', '%') + "'");
						}
					}
				}
			}
		}

		Query query = getSession().createQuery(bufHQL.toString());

		for (int i = 0; i < position; i++) {
			query.setParameter(i, values.get(i));
		}
		Object objet = query.uniqueResult();
		return objet;
	}

	/**
	 * Génération du code prestataire via la procedure "SEQ_CODE_PRESTATAIRE"
	 * 
	 * @param codeActivite
	 * @return
	 * @throws PersistenceException
	 */
	public synchronized String getSequenceCodePrestataire(String codeActivite) throws PersistenceException {

		Connection connection = ((Session) dao.getSession()).connection();
		String sql = "{call SEQ_CODE_PRESTATAIRE(?,?)}";
		CallableStatement call = null;

		try {
			call = connection.prepareCall(sql);
			call.setString(1, codeActivite);

			call.registerOutParameter(2, Types.VARCHAR);
			call.execute();
			return call.getString(2);

		} catch (Exception e) {
			logger.error("problème technique", e);
			return null;
		} finally {
			try {
				if (call != null) {
					call.close();
				}
			} catch (Exception e) {
				logger.error("problème technique", e);
			}
			;
		}
	}

	/**
	 * Récupérer la Session hibernate via la DAO
	 * 
	 * @return
	 * @throws PersistenceException
	 */
	public Session getSession() throws PersistenceException {
		return (Session) dao.getSession();
	}

	public Collection getListObjectByPartCriteria(Object objectBO, PagerVO pagerVO) throws Exception {

		Criteria criteria = this.getListObjectCriteria(objectBO);

		List listObjet = this.getPartCollectionByCondition(criteria, Integer.valueOf(pagerVO.getNumPage()),
				Integer.valueOf(pagerVO.getPageSize()));
		return listObjet;
	}

	public Integer getNombreObjectCriteria(Object objectBO) throws Exception {
		Criteria criteria = this.getListObjectCriteria(objectBO);
		List list = criteria.list();
		if (list != null) {
			return Integer.valueOf(list.size());
		} else {
			return null;
		}
	}

	public Criteria getListObjectCriteria(Object objectBO) throws Exception {

		Criteria criteria = getSession().createCriteria(objectBO.getClass());

		Field[] fields = converterTools.getAllFields(objectBO.getClass());
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			String property = field.getName();
			if ("serialVersionUID".equals(property)) {
				continue;
			}

			Object ObjetValue = BeanUtilsBean.getInstance().getPropertyUtils().getProperty(objectBO, property);
			if (ObjetValue != null) {
				if (!converterTools.isCollection(field.getType()) && !field.getType().equals(Date.class)) {
					if (converterTools.isReference(field.getType()) || field.getType().equals(Boolean.class)
							|| field.getType().equals(Long.class)) {

						criteria.add(Restrictions.eq(property, ObjetValue));

					} else {
						if (ObjetValue.toString().length() > 0) {
							criteria.add(Restrictions.like(property, ObjetValue.toString(), MatchMode.ANYWHERE));
						}
					}
				}
			}
		}

		criteria.addOrder(Order.desc(HibernateTools.getInstance().getIdentifierPropertyName(objectBO.getClass())));

		return criteria;
	}

	public List getPartCollectionByCondition(Criteria criteria, int page, int pageSize) throws PersistenceException {

		if (criteria != null) {
			criteria.setFirstResult(page * pageSize);
			criteria.setMaxResults(pageSize);
			return criteria.list();
		} else {
			return null;
		}
	}

	/**
	 * Chercher la liste des entités 'ConventionPrestation' qui ont les mêmes
	 * valeurs que les paramétres d'entrés
	 * 
	 * @param conventionPrestation
	 * @param convention
	 * @return
	 * @throws PersistenceException
	 */

	public List getListeEtat() throws EntiteException, PersistenceException {
		try {

			IPersistenceService dao = (IPersistenceService) ServicesFactory.getService(IPersistenceService.class);

			StringBuffer stringBuffer = new StringBuffer();
			Session sessionH = (Session) dao.getSession();

			stringBuffer.append(" FROM Etat where code <> '0' order by cast(code as integer)  ");
			// stringBuffer.append(" WHERE ");
			// stringBuffer.append(" etat.id IN ('"+ etatsAlertes +"')");

			Query q = sessionH.createQuery(stringBuffer.toString());
			return q.list();

		} catch (Exception e) {
			logger.error("problème technique", e);
			((ILog) ServicesFactory.getService(ILog.class)).error("", e);
			throw new EntiteException("FWK.ENTITE.DOGET.ERROR", e);

		}
	}

	public List geTransactionEtat(String codSitQtc, String codSitCible) throws PersistenceException {

		String sql = "select * from RGL_TRANSC_ETAT where CODSITQTC ='" + codSitQtc + "' and CODSITCIBLE ='"
				+ codSitCible + "'";

		List listObj = (List) dao.executeSqlQuery(sql);

		return listObj;

	}

	public HashMap autorisationMvtEtat(HashMap params) throws Exception {

		String codeEtatCible = String.valueOf(((Long) params.get("IParam.COD_MVT_CIBLE")));
		String codeEtat = String.valueOf(((Long) params.get("COD_MVT")));

		/** Recuperer la quittance, pour avoir son code situation */
		/** Verifier si ce changement d'etat est autorisé */
		// correction sonar Dead store to local variable.
		// List<Profil> listProfil = utilisateur.getRefProfils();
		List listTranscEtat = geTransactionEtat(codeEtat, codeEtatCible);
		if (listTranscEtat == null || listTranscEtat.isEmpty()) {
			throw new Exception("QTC.SERVICE.MOUVEMENT.NOT.AUTORISE");
		}

		/** Verifier si ce profil a le droit d'effectuer ce changement d'etat */
		boolean ok = false;
		for (int i = 0; i < listTranscEtat.size(); i++) {
			TransactionEtat transactionEtat = (TransactionEtat) listTranscEtat.get(i);
			if (transactionEtat.getRefProfil() == null) {
				break;
			}

			if (transactionEtat.getRefProfil() == 0) {
				ok = true;
				break;
			}

			// if(profil != null &&
			// transactionEtat.getRefProfil().equals(profil.getId()) ) {
			// ok = true;
			// break;
			// }
		}
		if (!ok) {
			throw new Exception("QTC.SERVICE.MOUVEMENT.NOT.AUTORISE.PROFIL");
		}

		return params;

	}

	public List getListTribunal() throws PersistenceException {

		String sql = "select * from tribunal";
		// IPersistenceService dao = (IPersistenceService)
		// ServicesFactory.getService(IPersistenceService.class);
		List listObj = (List) dao.executeSqlQuery(sql);

		return listObj;

	}

	public List getListTribunal(Tribunal tribunal, PagerVO pager) throws Exception {
		Query query = getSession().createQuery(getTribunalQuery(tribunal));
		if (query != null && pager != null && pager.getNumPage() != null && pager.getPageSize() != null) {
			int pageSize = Integer.valueOf(pager.getPageSize());
			int numPage = Integer.valueOf(pager.getNumPage());
			query.setFirstResult(pageSize * (numPage - 1));
			query.setMaxResults(pageSize);
		}
		if (query != null) {
			return query.list();
		} else {
			return new ArrayList();
		}
	}

	private String getTribunalQuery(Tribunal tribunal) {
		String hql = " from Tribunal where 1=1";
		if (tribunal != null) {
			if (tribunal.getCode() != null && !StringUtils.isEmpty(tribunal.getCode().trim())) {
				hql += " and code ='" + tribunal.getCode().trim() + "' ";
			}
			if (tribunal.getLibelle() != null && !StringUtils.isEmpty(tribunal.getLibelle().trim())) {
				hql += " and UPPER(libelle) like '%" + tribunal.getLibelle().toUpperCase().trim() + "%' ";
			}
			if (tribunal.getRefType() != null && tribunal.getRefType().getCode() != null) {
				hql += " and refType.code= " + tribunal.getRefType().getCode();
			}
		}
		// hql+="and UPPER(libelle) like 'TRIBUNAL%'";
		hql += "  order by libelle";
		return hql;

	}

	public List getListModePaiement(String type) throws PersistenceException {
		Session sessionH = (Session) dao.getSession();
		String sql = " from TypeModePayement order by libelle";
		if (type != null) {
			sql = " from TypeModePayement where type = '" + type + "' and code in ('2','3','6') order by libelle";
		}
		Query q = sessionH.createQuery(sql);
		return q.list();
	}

	/**
	 * rechercher un intermediaire par son code.
	 * 
	 * @param codeIntermediaire
	 * @return
	 * @throws Exception
	 */
	public Object getIntermediaireByCode(String codeIntermediaire) throws Exception {

		if (codeIntermediaire == null) {
			return null;
			// if (codeIntermediaire.length() > 4) {
			// codeInter = codeIntermediaire.substring(1,5);
			// } else {
			// codeInter = codeIntermediaire;
			// }
		}

		Query query = getSession().createQuery(getIntermedaireByCodeQuery(codeIntermediaire));

		return query.uniqueResult();
	}

	/**
	 * rêquete de recheche par code intermediaire
	 * 
	 * @param codeIntermediaire
	 * @return
	 */
	private String getIntermedaireByCodeQuery(String codeIntermediaire) {
		String hql = " from Intermediaire where 1=1";
		if (codeIntermediaire != null && !StringUtils.isEmpty(codeIntermediaire.trim())) {
			hql += " and code ='" + codeIntermediaire.trim() + "' ";
		}
		return hql;

	}

	public List getListIntermediaire(Intermediaire intermediaire, PagerVO pager) throws FonctionnelleException {
		try {
			Query query = getSession().createQuery(getIntermedaireQuery(intermediaire));
			if (query != null && pager != null && pager.getNumPage() != null && pager.getPageSize() != null) {
				int pageSize = Integer.valueOf(pager.getPageSize());
				int numPage = Integer.valueOf(pager.getNumPage());
				query.setFirstResult(pageSize * (numPage - 1));
				query.setMaxResults(pageSize);
			}
			if (query != null) {
				return query.list();
			} else {
				return new ArrayList();
			}
		} catch (PersistenceException e) {
			logger.error(EXP_RECHERCHE + "INTERMEDIAIRE", e);
			throw new FonctionnelleException(EXP_RECHERCHE + "INTERMEDIAIRE");
		}
	}

	private String getIntermedaireQuery(Intermediaire intermediaire) {
		String hql = " from Intermediaire where 1=1";
		if (intermediaire != null) {
			if (intermediaire.getCode() != null && !StringUtils.isEmpty(intermediaire.getCode().trim())) {
				hql += " and code like '%" + intermediaire.getCode().trim() + "%' ";
			}
			if (intermediaire.getLibelle() != null && !StringUtils.isEmpty(intermediaire.getLibelle().trim())) {
				hql += " and Upper(libelle) like '%" + intermediaire.getLibelle().toUpperCase().trim() + "%' ";
			}
			if (!StringUtils.isEmpty(intermediaire.getCodeTypeIntermediaire())) {
				hql += " and codeTypeIntermediaire ='" + intermediaire.getCodeTypeIntermediaire() + "'";
			}
		}
		hql += " order by libelle";
		return hql;

	}

	public List getListAssurance(Assurance assurance) throws PersistenceException {
		Session sessionH = (Session) dao.getSession();
		String sql = " from Assurance order by libelle";
		if (assurance != null) {
			if (assurance.getCode() != null && !StringUtils.isEmpty(assurance.getCode().trim())) {
				sql = " from Assurance where code = '" + assurance.getCode().trim() + "' order by libelle";
			}
		}
		Query q = sessionH.createQuery(sql);
		return q.list();

	}

	public List getListBanque(Banque banque) throws PersistenceException {
		Session sessionH = (Session) dao.getSession();
		String sql = " from Banque where code=codeMaroc order by libelle";
		if (banque != null) {
			if (banque.getCode() != null && !StringUtils.isEmpty(banque.getCode().trim())) {
				sql = " from Banque where code = '" + banque.getCode().trim() + "' order by libelle";
			}
		}
		Query q = sessionH.createQuery(sql);
		return q.list();

	}

	public List getListPays(Pays pays) throws PersistenceException {
		Session sessionH = (Session) dao.getSession();
		String sql = " from Pays order by libelle";
		if (pays != null) {
			if (pays.getCode() != null) {
				sql = " from Pays where code = '" + pays.getCode() + "' order by libelle";
			}
		}
		Query q = sessionH.createQuery(sql);
		return q.list();

	}

	public List getListVille(Ville ville) throws PersistenceException {

		Session sessionH = (Session) dao.getSession();
		String sql = " from Ville order by libelle";
		if (ville != null) {
			if (ville.getCode() != null) {
				sql = " from Ville where code = '" + ville.getCode() + "' order by libelle";
			}
		}
		Query q = sessionH.createQuery(sql).setCacheable(true);
		return q.list();

	}

	public List getListDomaine() throws PersistenceException {

		Session sessionH = (Session) dao.getSession();
		String sql = " from DomainePrest order by code";
		Query q = sessionH.createQuery(sql);
		return q.list();

	}

	public List getListActivite() throws PersistenceException {

		Session sessionH = (Session) dao.getSession();
		String sql = " from ActivitePrest order by code";
		Query q = sessionH.createQuery(sql);
		return q.list();

	}

	public List getListTypeSuivi() throws PersistenceException {
		Session sessionH = (Session) dao.getSession();
		String sql = " from TypeSuivi order by code";
		Query q = sessionH.createQuery(sql);
		return q.list();
	}

	public List<DegreParente> getListDegreParente(DegreParente degreParente) throws PersistenceException {

		Session sessionH = (Session) dao.getSession();
		String sql = " from DegreParente order by code";
		if (degreParente != null) {
			if (degreParente.getCode() != null) {
				sql = " from DegreParente where code = '" + degreParente.getCode() + "' order by code";
			}
		}
		Query q = sessionH.createQuery(sql);
		return q.list();

	}

	public List getListZone(Zone zone) throws PersistenceException {

		Session sessionH = (Session) dao.getSession();
		String sql = " from Zone order by code";
		if (zone != null) {
			if (zone.getCode() != null) {
				sql = " from Zone where code = '" + zone.getCode() + "' order by code";
			}
		}
		Query q = sessionH.createQuery(sql);
		return q.list();

	}

	public List getListCause(Cause cause) throws PersistenceException {

		Session sessionH = (Session) dao.getSession();
		String sql = " from Cause order by libelle";
		if (cause != null) {
			if (cause.getCode() != null) {
				sql = " from Cause where code = '" + cause.getCode() + "' order by libelle";
			}
		}
		Query q = sessionH.createQuery(sql);
		return q.list();

	}

	public List getListGarantie(TypeGarantie garantie) throws PersistenceException {

		Session sessionH = (Session) dao.getSession();
		String sql = " from TypeGarantie order by code";
		if (garantie != null) {
			if (garantie.getCode() != null) {
				sql = " from TypeGarantie where code = '" + garantie.getCode() + "' order by code";
			}
		}
		Query q = sessionH.createQuery(sql);
		return q.list();

	}

	public List getListTypeAccident(TypeAccident typeAccident) throws PersistenceException {

		Session sessionH = (Session) dao.getSession();
		String sql = " from TypeAccident order by code";
		if (typeAccident != null) {
			if (typeAccident.getCode() != null && !StringUtils.isEmpty(typeAccident.getCode().trim())) {
				sql = " from TypeAccident where code = '" + typeAccident.getCode().trim() + "' order by code";
			}
		}
		Query q = sessionH.createQuery(sql);
		return q.list();

	}

	// 17-02-2023 avolution courrier hybrides
	public List getListCourrierHybrides(CourrierHybride courrierHybride) throws PersistenceException {

		Session sessionH = (Session) dao.getSession();
		String sql = " from CourrierHybride order by id desc";
		if (courrierHybride != null) {
			if (courrierHybride.getId() != null && !StringUtils.isEmpty(courrierHybride.getId().trim())) {
				sql = " from CourrierHybride where id = '" + courrierHybride.getId().trim() + "' order by code";
			}
		}
		Query q = sessionH.createQuery(sql);
		return q.list();

	}

	public Integer getNombreCourrierHybridesRejete(CourrierHybride courrier) throws Exception {
		String hql = getHqlCourrierHybridesRejete(courrier).toString();
		hql = hql.replaceAll("select a", "select COUNT(a)");
		Query query = getSession().createQuery(hql);
		Long nombreResultat = (Long) query.uniqueResult();
		return nombreResultat.intValue();
	}
	
	public Integer getNombreCourrierHybrides(CourrierHybride courrier) throws Exception {
		String hql = getHqlCourrierHybrides(courrier).toString();
		hql = hql.replaceAll("select a", "select COUNT(a)");
		Query query = getSession().createQuery(hql);
		Long nombreResultat = (Long) query.uniqueResult();
		return nombreResultat.intValue();
	}

	private StringBuilder getHqlCourrierHybrides(CourrierHybride courrier) {

		StringBuilder hql = new StringBuilder(" select a from CourrierHybride a");
		hql.append(" where a.numeroSinistre = '" + courrier.getNumeroSinistre() + "' order by a.id desc");
		return hql;
	}
	private StringBuilder getHqlCourrierHybridesRejete(CourrierHybride courrier) {

		StringBuilder hql = new StringBuilder(" select a from CourrierHybride a");
		hql.append(" where a.numeroSinistre = '" + courrier.getNumeroSinistre() + "' and a.refEtatCourrier.idEtat = 3 order by a.id desc");
		return hql;
	}

	public Long getNombreCourrierQuittanceHybrides(CourrierHybrideQuittance courrierQuittance) throws Exception {
		String hql = getHqlCourrierQuittanceHybrides(courrierQuittance).toString();
		hql = hql.replaceAll("select a", "select COUNT(a)");
		Query query = getSession().createQuery(hql);
		return (Long) query.uniqueResult();
	}

	public Long getNombreCourrierOppositionHybrides(CourrierHybrideOpposition courrierOpposition) throws Exception {
		String hql = getHqlCourrierOppositionHybrides(courrierOpposition).toString();
		hql = hql.replaceAll("select a", "select COUNT(a)");
		Query query = getSession().createQuery(hql);
		return (Long) query.uniqueResult();
	}

	public Long getNombreCourrierReclamationHybrides(CourrierHybrideReclamation courrierReclamation) throws Exception {
		String hql = getHqlCourrierReclamationHybrides(courrierReclamation).toString();
		hql = hql.replaceAll("select a", "select COUNT(a)");
		Query query = getSession().createQuery(hql);
		return (Long) query.uniqueResult();
	}

	public Long getNombreCourrierOuvertureRenteHybrides(CourrierHybrideOuvertureRente courrierOuvertureRente)
			throws Exception {
		String hql = getHqlCourrierOuvertureRenteHybrides(courrierOuvertureRente).toString();
		hql = hql.replaceAll("select a", "select COUNT(a)");
		Query query = getSession().createQuery(hql);
		return (Long) query.uniqueResult();
	}

	public Long getNombreCourrierAvisContreVisiteHybrides(CourrierHybrideAvisContreVisite courrierAvisContreVisite)
			throws Exception {
		String hql = getHqlCourrierAvisContreVisiteHybrides(courrierAvisContreVisite).toString();
		hql = hql.replaceAll("select a", "select COUNT(a)");
		Query query = getSession().createQuery(hql);
		return (Long) query.uniqueResult();
	}

	public Long getNombreCourrierAvisSuspensionHybrides(CourrierHybrideAvisSuspension courrierAvisSuspension)
			throws Exception {
		String hql = getHqlCourrierAvisSuspensionHybrides(courrierAvisSuspension).toString();
		hql = hql.replaceAll("select a", "select COUNT(a)");
		Query query = getSession().createQuery(hql);
		return (Long) query.uniqueResult();
	}

	private StringBuilder getHqlCourrierOppositionHybrides(CourrierHybrideOpposition courrierOpposition) {
		StringBuilder hql = new StringBuilder(" select a from CourrierHybrideOpposition a, CourrierHybride b");
		hql.append(" where a.idLRH = b.idLRH and b.refEtatCourrier.idEtat = 3 and b.refModeleLRH.idModele =5");
		hql.append(" order by a.idLRH desc");
		return hql;
	}

	private StringBuilder getHqlCourrierReclamationHybrides(CourrierHybrideReclamation courrierReclamation) {
		StringBuilder hql = new StringBuilder(" select a from CourrierHybrideReclamation a, CourrierHybride b");
		hql.append(" where a.idLRH = b.idLRH and b.refEtatCourrier.idEtat = 3 and b.refModeleLRH.idModele =6");
		hql.append(" order by a.idLRH desc");
		return hql;
	}

	private StringBuilder getHqlCourrierQuittanceHybrides(CourrierHybrideQuittance courrierQuittance) {
		StringBuilder hql = new StringBuilder(" select a from CourrierHybrideQuittance a, CourrierHybride b");
		hql.append(" where a.idLRH = b.idLRH and b.refEtatCourrier.idEtat = 3 and b.refModeleLRH.idModele in (1,2,3,4)");
		hql.append(" order by a.idLRH desc");
		return hql;
	}

	private StringBuilder getHqlCourrierOuvertureRenteHybrides(CourrierHybrideOuvertureRente courrierOuvertureRente) {
		StringBuilder hql = new StringBuilder(" select a from CourrierHybrideOuvertureRente a, CourrierHybride b");
		hql.append(" where a.idLRH = b.idLRH and b.refEtatCourrier.idEtat = 3 and b.refModeleLRH.idModele =7");
		hql.append(" order by a.idLRH desc");
		return hql;
	}

	private StringBuilder getHqlCourrierAvisContreVisiteHybrides(
			CourrierHybrideAvisContreVisite courrieAvisContreVisite) {
		StringBuilder hql = new StringBuilder(" select a from CourrierHybrideAvisContreVisite a, CourrierHybride b");
		hql.append(" where a.idLRH = b.idLRH and b.refEtatCourrier.idEtat = 3 and b.refModeleLRH.idModele = 8");
		hql.append(" order by a.idLRH desc");
		return hql;
	}

	private StringBuilder getHqlCourrierAvisSuspensionHybrides(CourrierHybrideAvisSuspension courrieAvisSuspension) {
		StringBuilder hql = new StringBuilder(" select a from CourrierHybrideAvisSuspension a, CourrierHybride b");
		hql.append(" where a.idLRH = b.idLRH and b.refEtatCourrier.idEtat = 3 and b.refModeleLRH.idModele = 9");
		hql.append(" order by a.idLRH desc");
		return hql;
	}

	public Long getNombreCourrierContreVisiteHybrides(CourrierHybrideContreVisite courrierContreVisite)
			throws Exception {
		String hql = getHqlCourrierContreVisiteHybrides(courrierContreVisite).toString();
		hql = hql.replaceAll("select a", "select COUNT(a)");
		Query query = getSession().createQuery(hql);
		return (Long) query.uniqueResult();
	}

	private StringBuilder getHqlCourrierContreVisiteHybrides(CourrierHybrideContreVisite courrierContreVisite) {
		StringBuilder hql = new StringBuilder(" select a from CourrierHybrideContreVisite a");
		hql.append(" where 1=1 ");
		hql.append(" order by a.id desc");
		return hql;
	}

	public List rechercheCourrierHybrides(Object object, PagerVO pagerVO) throws Exception {
		try {
			if (object == null) {
				return null;
			}

			Query query = getSession().createQuery(getHqlCourrierHybrides((CourrierHybride) object).toString());
			if (pagerVO != null) {
				Integer numpage = 0;
				if (Integer.valueOf(pagerVO.getNumPage()).intValue() > 0) {
					numpage = Integer.valueOf(pagerVO.getNumPage()) - 1;
				}
				return this.getPartCollectionByCondition(query, numpage, Integer.valueOf(pagerVO.getPageSize()));
			} else {
				return query.list();
			}

		} catch (Exception e) {
			throw e;
		}

	}
	
	public List rechercheCourrierHybridesRejete(Object object, PagerVO pagerVO) throws Exception {
		try {
			if (object == null) {
				return null;
			}

			Query query = getSession().createQuery(getHqlCourrierHybridesRejete((CourrierHybride) object).toString());
			if (pagerVO != null) {
				Integer numpage = 0;
				if (Integer.valueOf(pagerVO.getNumPage()).intValue() > 0) {
					numpage = Integer.valueOf(pagerVO.getNumPage()) - 1;
				}
				return this.getPartCollectionByCondition(query, numpage, Integer.valueOf(pagerVO.getPageSize()));
			} else {
				return query.list();
			}

		} catch (Exception e) {
			throw e;
		}

	}


	public List rechercheCourrierQuittanceHybrides(Object object, PagerVO pagerVO) throws Exception {
		try {
			if (object == null) {
				return null;
			}

			Query query = getSession()
					.createQuery(getHqlCourrierQuittanceHybrides((CourrierHybrideQuittance) object).toString());
			if (pagerVO != null) {
				Integer numpage = 0;
				if (Integer.valueOf(pagerVO.getNumPage()).intValue() > 0) {
					numpage = Integer.valueOf(pagerVO.getNumPage()) - 1;
				}
				return this.getPartCollectionByCondition(query, numpage, Integer.valueOf(pagerVO.getPageSize()));
			} else {
				return query.list();
			}

		} catch (Exception e) {
			throw e;
		}

	}

	public List rechercheCourrierOppositionHybrides(Object object, PagerVO pagerVO) throws Exception {
		try {
			if (object == null) {
				return null;
			}

			Query query = getSession()
					.createQuery(getHqlCourrierOppositionHybrides((CourrierHybrideOpposition) object).toString());
			if (pagerVO != null) {
				Integer numpage = 0;
				if (Integer.valueOf(pagerVO.getNumPage()).intValue() > 0) {
					numpage = Integer.valueOf(pagerVO.getNumPage()) - 1;
				}
				return this.getPartCollectionByCondition(query, numpage, Integer.valueOf(pagerVO.getPageSize()));
			} else {
				return query.list();
			}

		} catch (Exception e) {
			throw e;
		}

	}

	public List rechercheCourrierOuvertureRenteHybrides(Object object, PagerVO pagerVO) throws Exception {
		try {
			if (object == null) {
				return null;
			}

			Query query = getSession().createQuery(
					getHqlCourrierOuvertureRenteHybrides((CourrierHybrideOuvertureRente) object).toString());
			if (pagerVO != null) {
				Integer numpage = 0;
				if (Integer.valueOf(pagerVO.getNumPage()).intValue() > 0) {
					numpage = Integer.valueOf(pagerVO.getNumPage()) - 1;
				}
				return this.getPartCollectionByCondition(query, numpage, Integer.valueOf(pagerVO.getPageSize()));
			} else {
				return query.list();
			}

		} catch (Exception e) {
			throw e;
		}

	}

	public List rechercheCourrierAvisContreVisiteHybrides(Object object, PagerVO pagerVO) throws Exception {
		try {
			if (object == null) {
				return null;
			}

			Query query = getSession().createQuery(
					getHqlCourrierAvisContreVisiteHybrides((CourrierHybrideAvisContreVisite) object).toString());
			if (pagerVO != null) {
				Integer numpage = 0;
				if (Integer.valueOf(pagerVO.getNumPage()).intValue() > 0) {
					numpage = Integer.valueOf(pagerVO.getNumPage()) - 1;
				}
				return this.getPartCollectionByCondition(query, numpage, Integer.valueOf(pagerVO.getPageSize()));
			} else {
				return query.list();
			}

		} catch (Exception e) {
			throw e;
		}

	}

	public List rechercheCourrierAvisSuspensionHybrides(Object object, PagerVO pagerVO) throws Exception {
		try {
			if (object == null) {
				return null;
			}

			Query query = getSession().createQuery(
					getHqlCourrierAvisSuspensionHybrides((CourrierHybrideAvisSuspension) object).toString());
			if (pagerVO != null) {
				Integer numpage = 0;
				if (Integer.valueOf(pagerVO.getNumPage()).intValue() > 0) {
					numpage = Integer.valueOf(pagerVO.getNumPage()) - 1;
				}
				return this.getPartCollectionByCondition(query, numpage, Integer.valueOf(pagerVO.getPageSize()));
			} else {
				return query.list();
			}

		} catch (Exception e) {
			throw e;
		}

	}

	public List rechercheCourrierReclamationHybrides(Object object, PagerVO pagerVO) throws Exception {
		try {
			if (object == null) {
				return null;
			}

			Query query = getSession()
					.createQuery(getHqlCourrierReclamationHybrides((CourrierHybrideReclamation) object).toString());
			if (pagerVO != null) {
				Integer numpage = 0;
				if (Integer.valueOf(pagerVO.getNumPage()).intValue() > 0) {
					numpage = Integer.valueOf(pagerVO.getNumPage()) - 1;
				}
				return this.getPartCollectionByCondition(query, numpage, Integer.valueOf(pagerVO.getPageSize()));
			} else {
				return query.list();
			}

		} catch (Exception e) {
			throw e;
		}

	}

	public boolean validerCourrierHybrides(List<CourrierHybrideVO> ListCourrierHybrideVO, Map param) throws Exception {

		if (ListCourrierHybrideVO == null || ListCourrierHybrideVO.isEmpty()) {
			return false;
		}

		for (CourrierHybrideVO CourrierHybrideVO : (List<CourrierHybrideVO>) ListCourrierHybrideVO) {
			CourrierHybride CourrierHybride = null;
			/*
			 * if (CourrierHybrideQuittanceVO.getId() == null) { if
			 * (CourrierHybrideQuittanceVO.getNumeroSinistre() == null ||
			 * "".equals(CourrierHybrideQuittanceVO.getNumeroSinistre())) { continue; }
			 * 
			 * }
			 */
			majCourrierHybrides(CourrierHybrideVO);
		}

		return true;
	}

	public void majCourrierHybrides(CourrierHybrideVO CourrierHybrideVO) throws Exception {
		StringBuilder hql = new StringBuilder("update CourrierHybride set idEtat=:idEtat,").append("renvoie=:renvoie");
		if ("1".equals(CourrierHybrideVO.getRenvoie())
				&& "5".equals(CourrierHybrideVO.getRefEtatCourrier().getIdEtat())) {
			hql.append(",dateRenvoie= sysdate");
		}
		if ("0".equals(CourrierHybrideVO.getRenvoie())
				&& "4".equals(CourrierHybrideVO.getRefEtatCourrier().getIdEtat())) {
			hql.append(",dateAnnulation= sysdate");
		}

		hql.append(" where id=:id");
		Query query = getSession().createQuery(hql.toString())
				.setParameter("idEtat", CourrierHybrideVO.getRefEtatCourrier().getIdEtat())
				.setParameter("renvoie", CourrierHybrideVO.getRenvoie());

		query.setParameter("id", CourrierHybrideVO.getId());
		query.executeUpdate();
		// getSession().save(CourrierHybrideQuittanceVO);
	}

	/*
	 * public void majCourrierHybridesQuittance(CourrierHybrideQuittance
	 * CourrierHybrideQuittance) throws Exception { StringBuilder hql = new
	 * StringBuilder( "update CourrierHybrideQuittance set idEtat=:idEtat,")
	 * .append("renvoie=:renvoie");
	 * 
	 * hql.append(" where id=:id"); Query query =
	 * getSession().createQuery(hql.toString()); //.setParameter("idEtat",
	 * CourrierHybrideVO.getIdEtat()) //.setParameter("renvoie",
	 * CourrierHybrideVO.getRenvoie());
	 * 
	 * //query.setParameter("id", CourrierHybrideVO.getId()); query.executeUpdate();
	 * }
	 */

	public CourrierHybrideQuittance getCourrierHybrideQuittanceByIdLRH(String idLRH) throws PersistenceException {

		if (idLRH.equals(null) || idLRH.isEmpty()) {
			return null;
		}

		String hql = " from CourrierHybrideQuittance courrierHybrideQuittance where courrierHybrideQuittance.idLRH="
				+ idLRH;
		return (CourrierHybrideQuittance) getSession().createQuery(hql).uniqueResult();

	}

	public CourrierHybrideOpposition getCourrierHybrideOppositionByIdLRH(String idLRH) throws PersistenceException {

		if (idLRH.equals(null) || idLRH.isEmpty()) {
			return null;
		}

		String hql = " from CourrierHybrideOpposition courrierHybrideOpposition where courrierHybrideOpposition.idLRH="
				+ idLRH;
		return (CourrierHybrideOpposition) getSession().createQuery(hql).uniqueResult();

	}

	public CourrierHybrideReclamation getCourrierHybrideReclamationByIdLRH(String idLRH) throws PersistenceException {

		if (idLRH.equals(null) || idLRH.isEmpty()) {
			return null;
		}

		String hql = " from CourrierHybrideReclamation courrierHybrideReclamation where courrierHybrideReclamation.idLRH="
				+ idLRH;
		return (CourrierHybrideReclamation) getSession().createQuery(hql).uniqueResult();

	}

	public CourrierHybrideQuittance majCourrierHybridesQuittance(CourrierHybrideQuittance chq)
			throws PersistenceException, FonctionnelleException {

		CourrierHybrideQuittance chqt = getCourrierHybrideQuittanceByIdLRH(chq.getIdLRH());
		/*
		 * if (chqt == null) { throw new FonctionnelleException(EXP_AY_NOT_EXIST); } try
		 * { converterMetier.copyChQ(chqt, chq); } catch (Exception e) {
		 * logger.error(EXP_STAND_MESS, e); throw new
		 * FonctionnelleException(EXP_STAND_MESS, e); }
		 */
		dao.updateObject(chq);
		return chqt;

	}

	public CourrierHybrideOpposition majCourrierHybridesOpposition(CourrierHybrideOpposition chq)
			throws PersistenceException, FonctionnelleException {

		CourrierHybrideOpposition chqt = getCourrierHybrideOppositionByIdLRH(chq.getIdLRH());
		/*
		 * if (chqt == null) { throw new FonctionnelleException(EXP_AY_NOT_EXIST); } try
		 * { converterMetier.copyChQ(chqt, chq); } catch (Exception e) {
		 * logger.error(EXP_STAND_MESS, e); throw new
		 * FonctionnelleException(EXP_STAND_MESS, e); }
		 */
		dao.updateObject(chq);
		return chqt;

	}

	public CourrierHybrideReclamation majCourrierHybridesReclamation(CourrierHybrideReclamation chq)
			throws PersistenceException, FonctionnelleException {

		CourrierHybrideReclamation chqt = getCourrierHybrideReclamationByIdLRH(chq.getIdLRH());
		/*
		 * if (chqt == null) { throw new FonctionnelleException(EXP_AY_NOT_EXIST); } try
		 * { converterMetier.copyChQ(chqt, chq); } catch (Exception e) {
		 * logger.error(EXP_STAND_MESS, e); throw new
		 * FonctionnelleException(EXP_STAND_MESS, e); }
		 */
		dao.updateObject(chq);
		return chqt;

	}

	public CourrierHybrideAvisContreVisite majCourrierHybridesAvisContreVisite(CourrierHybrideAvisContreVisite chq)
			throws PersistenceException, FonctionnelleException {

		CourrierHybrideAvisContreVisite chqt = getCourrierHybrideAvisContreVisiteByIdLRH(chq.getIdLRH());
		/*
		 * if (chqt == null) { throw new FonctionnelleException(EXP_AY_NOT_EXIST); } try
		 * { converterMetier.copyChQ(chqt, chq); } catch (Exception e) {
		 * logger.error(EXP_STAND_MESS, e); throw new
		 * FonctionnelleException(EXP_STAND_MESS, e); }
		 */
		dao.updateObject(chq);
		return chqt;

	}

	public CourrierHybrideAvisSuspension majCourrierHybridesAvisSuspension(CourrierHybrideAvisSuspension chq)
			throws PersistenceException, FonctionnelleException {

		CourrierHybrideAvisSuspension chqt = getCourrierHybrideAvisSuspensionByIdLRH(chq.getIdLRH());
		/*
		 * if (chqt == null) { throw new FonctionnelleException(EXP_AY_NOT_EXIST); } try
		 * { converterMetier.copyChQ(chqt, chq); } catch (Exception e) {
		 * logger.error(EXP_STAND_MESS, e); throw new
		 * FonctionnelleException(EXP_STAND_MESS, e); }
		 */
		dao.updateObject(chq);
		return chqt;

	}

	public List rechercheCourrierContreVisiteHybrides(Object object, PagerVO pagerVO) throws Exception {
		try {
			if (object == null) {
				return null;
			}

			Query query = getSession()
					.createQuery(getHqlCourrierContreVisiteHybrides((CourrierHybrideContreVisite) object).toString());
			if (pagerVO != null) {
				Integer numpage = 0;
				if (Integer.valueOf(pagerVO.getNumPage()).intValue() > 0) {
					numpage = Integer.valueOf(pagerVO.getNumPage()) - 1;
				}
				return this.getPartCollectionByCondition(query, numpage, Integer.valueOf(pagerVO.getPageSize()));
			} else {
				return query.list();
			}

		} catch (Exception e) {
			throw e;
		}

	}

	public CourrierHybrideOuvertureRente getCourrierHybrideOuvertureRenteByIdLRH(String idLRH)
			throws PersistenceException {

		if (idLRH.equals(null) || idLRH.isEmpty()) {
			return null;
		}

		String hql = " from CourrierHybrideOuvertureRente courrierHybrideOuvertureRente where courrierHybrideOuvertureRente.idLRH="
				+ idLRH;
		return (CourrierHybrideOuvertureRente) getSession().createQuery(hql).uniqueResult();

	}

	public CourrierHybrideAvisContreVisite getCourrierHybrideAvisContreVisiteByIdLRH(String idLRH)
			throws PersistenceException {

		if (idLRH.equals(null) || idLRH.isEmpty()) {
			return null;
		}

		String hql = " from CourrierHybrideAvisContreVisite courrierHybrideAvisContreVisite where courrierHybrideAvisContreVisite.idLRH="
				+ idLRH;
		return (CourrierHybrideAvisContreVisite) getSession().createQuery(hql).uniqueResult();

	}

	public CourrierHybrideAvisSuspension getCourrierHybrideAvisSuspensionByIdLRH(String idLRH)
			throws PersistenceException {

		if (idLRH.equals(null) || idLRH.isEmpty()) {
			return null;
		}

		String hql = " from CourrierHybrideAvisSuspension courrierHybrideAvisSuspension where courrierHybrideAvisSuspension.idLRH="
				+ idLRH;
		return (CourrierHybrideAvisSuspension) getSession().createQuery(hql).uniqueResult();

	}

	public CourrierHybrideOuvertureRente majCourrierHybridesOuvertureRente(CourrierHybrideOuvertureRente chq)
			throws PersistenceException, FonctionnelleException {

		CourrierHybrideOuvertureRente chqt = getCourrierHybrideOuvertureRenteByIdLRH(chq.getIdLRH());
		/*
		 * if (chqt == null) { throw new FonctionnelleException(EXP_AY_NOT_EXIST); } try
		 * { converterMetier.copyChQ(chqt, chq); } catch (Exception e) {
		 * logger.error(EXP_STAND_MESS, e); throw new
		 * FonctionnelleException(EXP_STAND_MESS, e); }
		 */
		dao.updateObject(chq);
		return chqt;

	}

	// fin evol

	public List getListEtatSinistre(EtatSin etatSin) throws PersistenceException {

		Session sessionH = (Session) dao.getSession();
		String sql = " from EtatSin order by code";
		if (etatSin != null) {
			if (etatSin.getCode() != null) {
				sql = " from EtatSin where code = '" + etatSin.getCode() + "' order by code";
			}
		}
		Query q = sessionH.createQuery(sql);
		return q.list();
	}

	public List getListEtatSinistreSansInstance(EtatSin etatSin) throws PersistenceException {

		Session sessionH = (Session) dao.getSession();
		String sql = " from EtatSin where code <> '0' and code<>'4' and code <> 5";
		Query q = sessionH.createQuery(sql);
		return q.list();
	}

	public List getListJuridiction(Juridiction jur) throws Exception {

		Session sessionH = (Session) dao.getSession();
		String sql = " from Juridiction order by code";
		if (jur != null) {
			if (jur.getCode() != null && !StringUtils.isEmpty(jur.getCode().trim())) {
				sql = " from Juridiction where code = '" + jur.getCode().trim() + "' order by code";
			}
		}
		Query q = sessionH.createQuery(sql);
		return q.list();
	}

	public List getListTypeJuridiction(TypeJuridiction type) throws Exception {

		Session sessionH = (Session) dao.getSession();
		String sql = " from TypeJuridiction order by code";
		if (type != null) {
			if (type.getCode() != null && !StringUtils.isEmpty(type.getCode().trim())) {
				sql = " from TypeJuridiction where code = '" + type.getCode().trim() + "' order by code";
			}
		}
		Query q = sessionH.createQuery(sql);
		return q.list();
	}

	public List getListNatureDossier(NatureDossier nat) throws Exception {

		Session sessionH = (Session) dao.getSession();
		String sql = " from NatureDossier order by code";
		if (nat != null) {
			if (nat.getCode() != null) {
				sql = " from NatureDossier where code = '" + nat.getCode() + "' order by code";
			}
		}
		Query q = sessionH.createQuery(sql);
		return q.list();
	}

	public List getListNatureDecision(Decision dec) throws Exception {

		Session sessionH = (Session) dao.getSession();
		String sql = " from Decision order by code";
		if (dec != null) {
			if (dec.getCode() != null) {
				sql = " from Decision where code = '" + dec.getCode() + "' order by code";
			}
		}
		Query q = sessionH.createQuery(sql);
		return q.list();
	}

	public List getListSuiteJugement(NatureDossier nat) throws Exception {

		Session sessionH = (Session) dao.getSession();
		String sql = " from SuiteJugement order by code";
		if (nat != null) {
			if (nat.getCode() != null) {
				sql = " from SuiteJugement where code = '" + nat.getCode() + "' order by code";
			}
		}
		Query q = sessionH.createQuery(sql);
		return q.list();
	}

	public List getListSortJugement(SortJugement sj) throws Exception {

		Session sessionH = (Session) dao.getSession();
		String sql = " from SortJugement order by code";
		if (sj != null) {
			if (sj.getCode() != null) {
				sql = " from SortJugement where code = '" + sj.getCode() + "' order by code";
			}
		}
		Query q = sessionH.createQuery(sql);
		return q.list();
	}

	public List getListNatureProcedure(NatureProcedure proc) throws Exception {

		Session sessionH = (Session) dao.getSession();
		String sql = " from NatureProcedure order by code";
		if (proc != null) {
			if (proc.getCode() != null) {
				sql = " from NatureProcedure where code = '" + proc.getCode() + "' order by code";
			}
		}
		Query q = sessionH.createQuery(sql);
		return q.list();
	}

	public List getListNatureAudience(NatureAudience nature) throws Exception {

		Session sessionH = (Session) dao.getSession();
		String sql = " from NatureAudience order by code";
		if (nature != null) {
			if (nature.getCode() != null) {
				sql = " from NatureAudience where code = '" + nature.getCode() + "' order by code";
			}
		}
		Query q = sessionH.createQuery(sql);
		return q.list();
	}

	public List getlistTypeBeneficiaire(TypeBeneficiaire typeBeneficiaire) throws PersistenceException {

		Session sessionH = (Session) dao.getSession();
		StringBuffer sql = new StringBuffer(" from TypeBeneficiaire tb");
		if (typeBeneficiaire != null) {
			if (typeBeneficiaire.getCode() != null) {
				sql.append("  where tb.code <> '" + typeBeneficiaire.getCode() + "'");
			}
		}
		sql.append(" order by tb.code");
		Query q = sessionH.createQuery(sql.toString());
		return q.list();

	}

	public List getListTypeMaladie(TypeMaladie typeMaladie) throws PersistenceException {

		Session sessionH = (Session) dao.getSession();
		String sql = " from TypeMaladie order by code";
		if (typeMaladie != null) {
			if (typeMaladie.getCode() != null) {
				sql = " from TypeMaladie where code = '" + typeMaladie.getCode() + "' order by code";
			}
		}
		Query q = sessionH.createQuery(sql);
		return q.list();

	}

	public List getlistTypeTribunal(TypeTribunal typeTribunal) throws PersistenceException {

		Session sessionH = (Session) dao.getSession();
		String sql = " from TypeTribunal order by code";
		if (typeTribunal != null) {
			if (typeTribunal.getCode() != null) {
				sql = " from TypeTribunal where code = '" + typeTribunal.getCode() + "' order by code";
			}
		}
		Query q = sessionH.createQuery(sql);
		return q.list();

	}

	// public List getlistPrestation(Prestation prestation)
	// throws PersistenceException {
	//
	// Session sessionH = (Session) dao.getSession();
	// String sql = " from Prestation order by libelle";
	// if (prestation != null) {
	// if (prestation.getCode() != null)
	// sql = " from Prestation where code = '" + prestation.getCode()
	// + "' order by libelle";
	// }
	// Query q = sessionH.createQuery(sql);
	// return q.list();
	//
	// }

	public List getlistPrestation(TypeReglement typeReglement) throws PersistenceException, FonctionnelleException {
		Session sessionH = (Session) dao.getSession();
		StringBuffer sql = new StringBuffer("from Prestation p ");
		if (typeReglement != null && !StringUtils.isEmpty(typeReglement.getCode())) {
			List<ImpactRubriqueReserve> listImpactRubriqueReserves = getListImpactReserveReglement(typeReglement);

			if (listImpactRubriqueReserves != null && !listImpactRubriqueReserves.isEmpty()) {
				sql.append(" where p.code = '" + listImpactRubriqueReserves.get(0).getRefPrestation().getCode() + "'");
				for (int i = 1; i < listImpactRubriqueReserves.size(); i++) {
					sql.append(" or p.code = '" + listImpactRubriqueReserves.get(i).getRefPrestation().getCode() + "'");
				}
			}
		} else {
			List<ImpactRubriqueReserve> listImpactRubriqueReserves = getListImpactReserveReglement(null);
			if (listImpactRubriqueReserves != null && !listImpactRubriqueReserves.isEmpty()) {
				sql.append(" where p.code = '" + listImpactRubriqueReserves.get(0).getRefPrestation().getCode() + "'");
				for (int i = 1; i < listImpactRubriqueReserves.size(); i++) {
					sql.append(" or p.code = '" + listImpactRubriqueReserves.get(i).getRefPrestation().getCode() + "'");
				}
			}
		}
		sql.append(" order by code");
		Query q = sessionH.createQuery(sql.toString());
		return q.list();

	}

	public List getlistPresta(String modeReglement) throws PersistenceException, FonctionnelleException {
		Session sessionH = (Session) dao.getSession();
		StringBuffer sql = new StringBuffer("from Prestation p ");
		if (modeReglement != null && !StringUtils.isEmpty(modeReglement)) {
			List<ImpactRubriqueReserve> listImpactRubriqueReserves = getListImpactReserveRegl(modeReglement);
			if (listImpactRubriqueReserves != null && !listImpactRubriqueReserves.isEmpty()) {
				sql.append(" where p.code = '" + listImpactRubriqueReserves.get(0).getRefPrestation().getCode() + "'");
				for (int i = 1; i < listImpactRubriqueReserves.size(); i++) {
					sql.append(" or p.code = '" + listImpactRubriqueReserves.get(i).getRefPrestation().getCode() + "'");
				}
			}
		}
		sql.append(" order by code");
		Query q = sessionH.createQuery(sql.toString());
		return q.list();

	}

	public List getPresta(String etatSinistre) throws PersistenceException, FonctionnelleException {
		Session sessionH = (Session) dao.getSession();
		StringBuffer sql = new StringBuffer("from Prestation p ");
		if (etatSinistre != null && !StringUtils.isEmpty(etatSinistre)) {
			List<ImpactRubriqueReserve> listImpactRubriqueReserves = getListImpactRegl(etatSinistre);
			if (listImpactRubriqueReserves != null && !listImpactRubriqueReserves.isEmpty()) {
				sql.append(" where p.code = '" + listImpactRubriqueReserves.get(0).getRefPrestation().getCode() + "'");
				for (int i = 1; i < listImpactRubriqueReserves.size(); i++) {
					sql.append(" or p.code = '" + listImpactRubriqueReserves.get(i).getRefPrestation().getCode() + "'");
				}
			}
		}
		sql.append(" order by code");
		Query q = sessionH.createQuery(sql.toString());
		return q.list();

	}

	public List getlistPoliceUnivers(PoliceUnivers pol) throws PersistenceException {

		Session sessionH = (Session) dao.getSession();
		String sql = " from PoliceUnivers order by code";
		if (pol != null) {
			if (pol.getCode() != null) {
				sql = " from PoliceUnivers where code = '" + pol.getCode() + "' order by code";
			}
		}
		Query q = sessionH.createQuery(sql);
		return q.list();

	}

	public List getlistSituation(Situation sit) throws PersistenceException {

		Session sessionH = (Session) dao.getSession();
		String sql = " from Situation order by code";
		if (sit != null) {
			if (sit.getCode() != null) {
				sql = " from Situation where code = '" + sit.getCode() + "' order by code";
			}
		}
		Query q = sessionH.createQuery(sql);
		return q.list();

	}

	/**
	 * Obtient liste des etats Recours
	 * 
	 * @return List
	 * @throws Exception
	 */
	public List getListeEtatRecours(EtatRecours etat) throws Exception {

		Session sessionH = (Session) dao.getSession();
		String sql = " from EtatRecours order by code";
		if (etat != null) {
			if (etat.getCode() != null) {
				sql = " from EtatRecours where code = '" + etat.getCode() + "' order by code";
			}
		}
		Query q = sessionH.createQuery(sql);
		return q.list();

	}

	/**
	 * Obtient liste des etats Recours
	 * 
	 * @return List
	 * @throws PersistenceException
	 */
	public List getListePronosticRC() throws PersistenceException {
		Session sessionH = (Session) dao.getSession();
		String sql = " from PronosticRC order by code";
		Query q = sessionH.createQuery(sql);
		return q.list();
	}

	private String getPrestataireQuery(Prestataire prestataire) {
		String query = "";
		StringBuffer HQL = new StringBuffer("from Prestataire prestataire  ");

		HQL.append(" where 1=1 ");
		// code
		if (prestataire.getCode() != null && !StringUtils.isEmpty(prestataire.getCode().trim())
				&& prestataire.getCode() != "0") {
			HQL.append(" and prestataire.code='").append(prestataire.getCode().toUpperCase().trim()).append("' ");
		}

		// type
		if (!StringUtils.isEmpty(prestataire.getType())) {
			if (!StringUtils.isEmpty(prestataire.getType().trim())) {
				HQL.append(" and prestataire.type='").append(prestataire.getType().trim()).append("' ");
			}
		}
		// nomRaisonSocial
		if (!StringUtils.isEmpty(prestataire.getNomRaisonSocial())) {
			if (!StringUtils.isEmpty(prestataire.getNomRaisonSocial().trim())) {
				HQL.append(" and prestataire.nomRaisonSocial like '%").append(prestataire.getNomRaisonSocial().trim())
						.append("%' ");
			}
		}

		// adresse

		if (!StringUtils.isEmpty(prestataire.getAdresse())) {
			if (!StringUtils.isEmpty(prestataire.getAdresse().trim())) {
				HQL.append(" and upper(prestataire.adresse) like '")
						.append(StringUtils.upperCase(prestataire.getAdresse().trim())).append("' ");
			}
		}
		// telephone
		if (!StringUtils.isEmpty(prestataire.getTelephone())) {
			if (!StringUtils.isEmpty(prestataire.getTelephone().trim())) {
				HQL.append(" and upper(prestataire.telephone) like '")
						.append(StringUtils.upperCase(prestataire.getTelephone().trim())).append("' ");
			}
		}
		// refDomaineActivite

		// refActivite
		if (prestataire.getRefActivite() != null) {

			if (!StringUtils.isEmpty(prestataire.getRefActivite().getCode())
					&& !prestataire.getRefActivite().getCode().equals("0")) {
				HQL.append(" and prestataire.refActivite.code='").append(prestataire.getRefActivite().getCode())
						.append("' ");
			}
			if (prestataire.getRefActivite().getRefDomaineActivite() != null
					&& !StringUtils.isEmpty(prestataire.getRefActivite().getRefDomaineActivite().getCode())
					&& !prestataire.getRefActivite().getRefDomaineActivite().getCode().equals("0")) {

				HQL.append(" and prestataire.refActivite.refDomaineActivite.code='")
						.append(prestataire.getRefActivite().getRefDomaineActivite().getCode()).append("' ");
			}
		}
		// ville
		if (prestataire.getRefVille() != null && !StringUtils.isEmpty(prestataire.getRefVille().getCode())
				&& !prestataire.getRefVille().getCode().equals("0")) {
			HQL.append(" and prestataire.refVille.code='").append(prestataire.getRefVille().getCode()).append("' ");
		}
		HQL.append(" order by prestataire.code asc");
		return HQL.toString();
	}
	
	public Prestataire getPrestataireQueryByCode(String codePrestataire) {
		StringBuffer HQL = new StringBuffer("from Prestataire prestataire ");

		HQL.append(" where 1=1 ");
		// code
		if (codePrestataire != null && !StringUtils.isEmpty(codePrestataire.trim())
				&& codePrestataire != "0") {
			HQL.append(" and prestataire.code='").append(codePrestataire.toUpperCase().trim()).append("' ");
		}
		
		try {
			Query query = getSession().createQuery(HQL.toString());

			Prestataire objet = (Prestataire) query.uniqueResult();

			return objet;
			
		} catch (Exception e) {
			logger.error("problème technique", e);

		}
		return null;
	}

	public List getListPrestataire(PrestataireVO prestataire, PagerVO pager) throws FonctionnelleException {
		try {
			if (prestataire == null) {
				logger.error(EXP_OBJECT_ENTREE);
				throw new FonctionnelleException(EXP_RECHERCHE + "PRESTATAIRE");
			}

			servicesExternes.setConvert(false);
			List list = (List) servicesExternes.appelService(ServicesExternes.CHERCHER_PRESTATAIRE, prestataire, null);
			return list;

		} catch (Exception e) {
			logger.error(EXP_RECHERCHE + "PRESTATAIRE", e);
			throw new FonctionnelleException(EXP_RECHERCHE + "PRESTATAIRE");
		}
	}

	public Integer getNombrePrestataire(Prestataire prestataire) {
		String query = "Select count(id) " + getPrestataireQuery(prestataire);
		Integer r = null;
		try {
			r = ((Long) getSession().createQuery(query).uniqueResult()).intValue();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			logger.error("problème technique", e);
		} catch (PersistenceException e) {
			// TODO Auto-generated catch block
			logger.error("problème technique", e);
		}
		return r;
	}

	public Integer getNombreIntermediaire(Intermediaire intermediaire) {
		String query = "Select count(id) " + getIntermedaireQuery(intermediaire);
		Integer r = null;
		try {
			r = ((Long) getSession().createQuery(query).uniqueResult()).intValue();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			logger.error("problème technique", e);
		} catch (PersistenceException e) {
			// TODO Auto-generated catch block
			logger.error("problème technique", e);
		}
		return r;
	}

	public Integer getNombreTribunal(Tribunal tribunal) {
		String query = "Select count(id) " + getTribunalQuery(tribunal);
		Integer r = null;
		try {
			r = ((Long) getSession().createQuery(query).uniqueResult()).intValue();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			logger.error("problème technique", e);
		} catch (PersistenceException e) {
			// TODO Auto-generated catch block
			logger.error("problème technique", e);
		}
		return r;
	}

	public List getlistNatureEcheance(NatureEcheance nat) throws PersistenceException {

		Session sessionH = (Session) dao.getSession();
		String sql = " from NatureEcheance order by code";
		if (nat != null) {
			if (nat.getCode() != null) {
				sql = " from NatureEcheance where code = '" + nat.getCode() + "' order by code";
			}
		}
		Query q = sessionH.createQuery(sql);
		return q.list();

	}

	// List<Palier> listPalier = (List<Palier>ParametrageManager.getAllPalier();
	// this.addResultItem(listPalier);
	public List<Palier> getAllPalier() throws PersistenceException {

		Session sessionH = (Session) dao.getSession();
		String sql = " from Palier order by dateDebut";
		Query q = sessionH.createQuery(sql);
		return q.list();

	}

	public Palier getPalier(Date dateAccident) throws Exception {

		Session sessionH = (Session) dao.getSession();
		if (dateAccident == null) {
			return null;
			// throw new Exception(
			// "l'objet Date Accident en entrée ne peut être null");
		}
		// DateUtile.getDate(DateUtile.DEFAULT_FORMAT, dateAccident.toString())
		// String sql = " from Palier where ?'" +
		// dateFormat.format(dateAccident)
		// + "' between dateDebut and dateFin ";

		// String sql = " from Palier where ? between dateDebut and dateFin ";

		// String sql = " from Palier where '"+ dateFormat.format(dateAccident)+
		// "' between TO_CHAR(dateDebut, 'DD/MM/YYYY') and TO_CHAR(dateFin,
		// 'DD/MM/YYYY') ";
		String sql = " from Palier where ? between dateDebut and dateFin ";
		Query q = sessionH.createQuery(sql);
		q.setDate(0, dateAccident);
		try {
			if (!q.list().isEmpty()) {
				return (Palier) q.list().get(0);
			}
		} catch (Exception e) {
			logger.error("problème technique", e);
		}
		return null;
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
			logger.error("problème technique", e);
		}

		return null;
	}

	public CoefficientAge getCoefParSin(Sinistre sinistre) throws Exception {
		Date date = null;
		CoefficientAge cofAge = null;
		if (sinistre == null) {
			return null;
			// throw new
			// Exception("l'objet sinistre en entrée ne peut être null");
		}
		if (sinistre.getRefEvenement() == null) {
			return null;
			// throw new
			// Exception("l'objet sinistre en entrée ne peut être null");
		}
		if (sinistre.getRefEvenement().getDateAccident() == null) {
			return null;
			// throw new
			// Exception("l'objet sinistre en entrée ne peut être null");
		}
		String code = "1";
		if (sinistre.getRefEvenement().getDateAccident().compareTo(dateFormaty.parse("20150122")) < 0) {

			code = "1";
			if (sinistre.getIpp() < 10) {
				code = "1";
			} else {
				code = "2";
			}
		} else if (sinistre.getRefEvenement().getDateAccident().compareTo(dateFormaty.parse("20150122")) >= 0) {
			code = "7";
			if (sinistre.getIpp() < 10) {
				code = "1";
			} else {
				code = "7";
			}

		}
		// date d'accident
		if (sinistre.getDateCalculReserve() != null) {
			date = sinistre.getDateCalculReserve();
		} else {
			date = new Date();
		}

		cofAge = getCoefParType(sinistre.getRefVictime().getAge(date), code);
		// DateUtile.getDate(DateUtile.DEFAULT_FORMAT,"20101202")
		// else if(DateUtile.dateToString(DateUtile.DEFAULT_FORMAT,
		// sinistre.getRefEvenement().getDateAccident()).equals(DateUtile.getDate(DateUtile.DEFAULT_FORMAT,"20101202"))){

		return cofAge;
	}

	public CoefficientAge getCoefParAY(Sinistre sinistre, AyantDroit ay) throws Exception {
		Date date = null;
		CoefficientAge cofAge = null;
		if (sinistre == null) {
			return null;
			// throw new
			// Exception("l'objet sinistre en entrée ne peut être null");
		}
		if (sinistre.getRefEvenement() == null) {
			return null;
			// throw new
			// Exception("l'objet sinistre en entrée ne peut être null");
		}
		if (sinistre.getRefEvenement().getDateAccident() == null) {
			return null;
			// throw new
			// Exception("l'objet sinistre en entrée ne peut être null");
		}
		if (ay == null) {
			return null;
			// throw new
			// Exception("l'objet sinistre en entrée ne peut être null");
		}
		String codeDegre = "1";
		try {
			codeDegre = ay.getRefDegreParente().getCode();
		} catch (Exception e) {
			codeDegre = "1";
		}
		String code = "2";
		// La date repère du calcul de la reserve grave est 02/12/2010
		if (sinistre.getRefEvenement().getDateAccident().before(dateFormaty.parse("20101202"))) {

			if (codeDegre.equals("1") || ay.getRefDegreParente().getCode().equals("10")) {
				code = "2";
			} else if (codeDegre.equals("20")) {
				code = "4";
			}
		} // La date repère du calcul de la reserve grave est 02/12/2010
		else if (sinistre.getRefEvenement().getDateAccident().compareTo(dateFormaty.parse("20101202")) >= 0
				&& sinistre.getRefEvenement().getDateAccident().compareTo(dateFormaty.parse("20150122")) < 0) {
			if (codeDegre.equals("1")) {
				code = "2";
			} else if (codeDegre.equals("10")) {
				code = "5";
			} else if (codeDegre.equals("20")) {
				code = "6";
			}
		} else if (sinistre.getRefEvenement().getDateAccident().compareTo(dateFormaty.parse("20150122")) >= 0) {

			if (codeDegre.equals("1")) {
				code = "7";
			} else if (codeDegre.equals("10") || codeDegre.equals("60")) {
				code = "8";
			} else if (codeDegre.equals("70")) {
				code = "9";
			} else if (codeDegre.equals("20")) {
				if (ay.getHandicape() != null && ay.getHandicape()) {
					code = "11";
				} else {
					code = "10";
				}
			}
		}

		// date d'accident
		if (sinistre.getDateCalculReserve() != null) {
			date = sinistre.getDateCalculReserve();
		} else {
			date = new Date();
		}
		cofAge = getCoefParType(ay.getAge(date), code);

		return cofAge;
	}

	public Double getIPPMinByZone(Sinistre sinistre) throws Exception {

		if (sinistre == null) {
			return Double.valueOf(0);
		}
		if (sinistre.getRefEvenement() == null) {
			return Double.valueOf(0);
		}
		if (sinistre.getRefEvenement().getRefZone() == null) {
			return Double.valueOf(0);
		}
		if (sinistre.getRefEvenement().getRefZone().getCode() == null) {
			return Double.valueOf(0);
		}

		Session sessionH = (Session) dao.getSession();
		String code = sinistre.getRefEvenement().getRefZone().getCode();
		if (code == null) {
			return Double.valueOf(0);
		}
		String sql = "select ippmin from Zone where code='" + code + "'";
		Query q = sessionH.createQuery(sql);
		try {
			if (!q.list().isEmpty()) {
				return (Double) q.list().get(0);
			}
		} catch (Exception e) {
			logger.error("problème technique", e);
			return Double.valueOf(0);
		}

		return Double.valueOf(0);
	}

	public Double getIPPMinByZoneSQL(Sinistre sinistre) throws Exception {

		if (sinistre == null) {
			return Double.valueOf(0);
		}
		if (sinistre.getRefEvenement() == null) {
			return Double.valueOf(0);
		}
		if (sinistre.getRefEvenement().getRefZone() == null) {
			return Double.valueOf(0);
		}
		if (sinistre.getRefEvenement().getRefZone().getCode() == null) {
			return Double.valueOf(0);
		}

		String code = sinistre.getRefEvenement().getRefZone().getCode();
		if (code == null) {
			return Double.valueOf(0);
		}
		String sql = "select ippmin from PRM_ZONE where code='" + code + "'";

		try {
			List listObj = (List) dao.executeSqlQuery(sql);
			if (!listObj.isEmpty()) {
				return ((BigDecimal) listObj.get(0)).doubleValue();
			}
		} catch (Exception e) {
			logger.error("problème technique", e);
			return Double.valueOf(0);
		}

		return Double.valueOf(0);
	}

	public Double getReserveOrdByZoneSQL(String codeZone, Double ipp) throws Exception {

		String sql = "select reserveordinaire from PRM_RSRV_ORD_SIN_GRAVE where codeZone='" + codeZone + "' and " + ipp
				+ " between ippmin and ippmax";

		try {
			List listObj = (List) dao.executeSqlQuery(sql);
			if (!listObj.isEmpty()) {
				return ((BigDecimal) listObj.get(0)).doubleValue();
			}
		} catch (Exception e) {
			logger.error("problème technique", e);
			return Double.valueOf(0);
		}

		return Double.valueOf(0);
	}

	public Double getAuthorisationEtatSQL(String codetat, String codetatcible, String cas) throws Exception {

		String sql = "select valide from PRM_GESTION_ETAT_SIN where codetat='" + codetat + "' and codetatcible='"
				+ codetatcible + "' and cas='" + cas + "'";

		try {
			List listObj = (List) dao.executeSqlQuery(sql);
			if (!listObj.isEmpty()) {
				return Double.valueOf(((String) listObj.get(0)));
			}
		} catch (Exception e) {
			logger.error("problème technique", e);
			return Double.valueOf(0);
		}

		return Double.valueOf(0);
	}

	public String getVilleByZoneSQL(String codeVille) throws Exception {
		if (codeVille == null || codeVille.trim().equals("")) {
			return "0";
		}
		String sql = "select zone from PRM_VILLE_ZONE where codeville='" + codeVille + "'";

		try {
			List listObj = (List) dao.executeSqlQuery(sql);
			if (listObj != null && !listObj.isEmpty()) {
				return (String) listObj.get(0);
			}
		} catch (Exception e) {
			logger.error("problème technique", e);

		}

		return "0";
	}

	public List getListResultatTransaction() throws PersistenceException {
		Session sessionH = (Session) dao.getSession();
		String sql = " from ResultatTransaction order by libelle";
		Query q = sessionH.createQuery(sql);
		return q.list();
	}

	public List getlistGestionnaire() throws PersistenceException {
		Session sessionH = (Session) dao.getSession();
		String sql = " from Gestionnaire order by libelle";
		Query q = sessionH.createQuery(sql);
		return q.list();
	}

	/** AZAS */

	public List getListNationalite(Nationalite nationalite) throws PersistenceException {

		Session sessionH = (Session) dao.getSession();
		String sql = " from Nationalite order by code";
		if (nationalite != null) {
			if (nationalite.getCode() != 0) {
				sql = " from Nationalite where code = '" + nationalite.getCode() + "' order by code";
			}
		}
		Query q = sessionH.createQuery(sql);
		return q.list();

	}

	public List getListNatureProthese(NatureProthese natProthese) throws PersistenceException {

		Session sessionH = (Session) dao.getSession();
		String sql = " from NatureProthese order by code";
		if (natProthese != null) {
			if (natProthese.getCode() != 0) {
				sql = " from NatureProthese where code = '" + natProthese.getCode() + "' order by code";
			}
		}
		Query q = sessionH.createQuery(sql);
		return q.list();

	}

	public List getListEtatTransaction() throws PersistenceException {
		Session sessionH = (Session) dao.getSession();
		String sql = " from EtatTransaction order by libelle";
		Query q = sessionH.createQuery(sql);
		return q.list();
	}

	public List getListSituationRentier(SituationRentier situationRentier) throws PersistenceException {

		Session sessionH = (Session) dao.getSession();
		String sql = " from SituationRentier order by code";
		if (situationRentier != null) {
			if (situationRentier.getCode() != 0) {
				sql = " from SituationRentier where code = '" + situationRentier.getCode() + "' order by code";
			}
		}
		Query q = sessionH.createQuery(sql);
		return q.list();

	}

	public List getListCoefficientAge(CoefficientAge coefficientAge) throws PersistenceException {

		Session sessionH = (Session) dao.getSession();
		String sql = "from CoefficientAge order by age";
		Query q = sessionH.createQuery(sql);
		return q.list();

	}

	public List getListDecision(Decision decision) throws PersistenceException {
		Session sessionH = (Session) dao.getSession();
		String sql = " from Decision order by libelle";
		if (decision != null) {
			if (decision.getCode() != null) {
				sql = " from Decision where code = '" + decision.getCode() + "' order by libelle";
			}
		}
		Query q = sessionH.createQuery(sql);
		return q.list();

	}

	public List getListeEtatProposition() throws PersistenceException {
		Session sessionH = (Session) dao.getSession();
		String sql = "from EtatProposition order by code";
		Query q = sessionH.createQuery(sql);
		return q.list();
	}

	public List getListeEtatReponse() throws PersistenceException {
		Session sessionH = (Session) dao.getSession();
		String sql = "from EtatReponse order by code";
		Query q = sessionH.createQuery(sql);
		return q.list();
	}

	public List getListelistDecision() throws PersistenceException {
		Session sessionH = (Session) dao.getSession();
		String sql = "from DecisionRecoursAmiable order by code";
		Query q = sessionH.createQuery(sql);
		return q.list();
	}

	public List getListPiece() throws PersistenceException {
		Session sessionH = (Session) dao.getSession();
		String sql = "from Piece order by code";
		Query q = sessionH.createQuery(sql);
		return q.list();
	}

	public List getListEtatMission() throws PersistenceException {
		Session sessionH = (Session) dao.getSession();
		String sql = "from EtatMission order by code";
		Query q = sessionH.createQuery(sql);
		return q.list();
	}

	public List getListDomainePrest() throws PersistenceException {
		Session sessionH = (Session) dao.getSession();
		String sql = "from DomainePrest order by code";
		Query q = sessionH.createQuery(sql);
		return q.list();
	}

	public List getListPresation() throws PersistenceException {
		Session sessionH = (Session) dao.getSession();
		String sql = "from PrestationMission order by code";
		Query q = sessionH.createQuery(sql);
		return q.list();
	}

	/**
	 * AZAS cette methode permet la récupération de la liste des etats de rentier
	 */

	public List getListEtatRentier(EtatRentier etatRentier) throws PersistenceException {
		Session sessionH = (Session) dao.getSession();
		String sql = " from EtatRentier Where typeEtat='1' order by libelle ";
		if (etatRentier != null) {
			if (etatRentier.getCode() != null) {
				sql = " from EtatRentier where code = '" + etatRentier.getCode() + "' order by libelle";
			}
		}
		Query q = sessionH.createQuery(sql);
		return q.list();
	}

	/**
	 * AZAS cette methode permet la récupération de la liste des etats de rente
	 */

	public List getListEtatRente(EtatRentier etatRentier) throws PersistenceException {
		Session sessionH = (Session) dao.getSession();
		String sql = " from EtatRentier Where typeEtat='2' order by libelle ";
		if (etatRentier != null) {
			if (etatRentier.getCode() != null) {
				sql = " from EtatRentier where code = '" + etatRentier.getCode() + "' order by libelle";
			}
		}
		Query q = sessionH.createQuery(sql);
		return q.list();
	}

	/**
	 * AZAS cette methode permet la récupération de la liste des types des
	 * mouvements
	 */

	public List getListTypeMouvement(TypeMouvement typeMouvement) throws PersistenceException {
		Session sessionH = (Session) dao.getSession();
		String sql = " from TypeMouvement";
		if (typeMouvement != null) {
			if (typeMouvement.getCode() != null) {
				sql = " from TypeMouvement where code = '" + typeMouvement.getCode() + "' order by libelle";
			}
		}
		Query q = sessionH.createQuery(sql);
		return q.list();
	}

	/**
	 * AZAS cette methode permet la récupération de la liste des types des
	 * mouvements de prothese
	 */

	public List getListTypeMouvementProthese(TypeMvtProthese typeMvtProthese) throws PersistenceException {
		Session sessionH = (Session) dao.getSession();
		String sql = " from TypeMvtProthese";
		if (typeMvtProthese != null) {
			if (typeMvtProthese.getCode() != null) {
				sql = " from TypeMvtProthese where code = '" + typeMvtProthese.getCode() + "' order by libelle";
			}
		}
		Query q = sessionH.createQuery(sql);
		return q.list();
	}

	public List getListNatureRecuperation(Object object) throws PersistenceException {
		Session sessionH = (Session) dao.getSession();
		// eliminer la nature récupération annulation
		String sql = "from NatureRecuperation where code <> 3 order by code";
		Query q = sessionH.createQuery(sql);
		return q.list();
	}

	public Integer getNombreChefGreffier(ChefGreffier chefGreffier) throws FonctionnelleException {
		String query = "Select count(code) " + getChefGreffierQuery(chefGreffier);
		Integer r = null;
		try {
			r = ((Long) getSession().createQuery(query).uniqueResult()).intValue();
		} catch (PersistenceException e) {
			logger.error(EXP_RECHERCHE + "_CHEFGREFFIER", e);
			throw new FonctionnelleException(EXP_RECHERCHE + "_CHEFGREFFIER");
		}
		return r;
	}

	public List getListChefGreffier(ChefGreffier chefGreffier, PagerVO pager) throws FonctionnelleException {
		try {
			Query query = getSession().createQuery(getChefGreffierQuery(chefGreffier));
			if (query != null && pager != null && pager.getNumPage() != null && pager.getPageSize() != null) {
				int pageSize = Integer.valueOf(pager.getPageSize());
				int numPage = Integer.valueOf(pager.getNumPage());
				query.setFirstResult(pageSize * (numPage - 1));
				query.setMaxResults(pageSize);
			}
			if (query != null) {
				return query.list();
			} else {
				return new ArrayList();
			}
		} catch (PersistenceException e) {
			logger.error(EXP_RECHERCHE + "_CHEFGREFFIER", e);
			throw new FonctionnelleException(EXP_RECHERCHE + "_CHEFGREFFIER");
		}
	}

	public List getListBarreaux(Barreau barreau, PagerVO pager) throws FonctionnelleException {
		try {
			Query query;

			query = getSession().createQuery(getBarreauQuery(barreau));

			if (query != null && pager != null && pager.getNumPage() != null && pager.getPageSize() != null) {
				int pageSize = Integer.valueOf(pager.getPageSize());
				int numPage = Integer.valueOf(pager.getNumPage());
				query.setFirstResult(pageSize * (numPage - 1));
				query.setMaxResults(pageSize);
			}
			if (query != null) {
				return query.list();
			} else {
				return new ArrayList();
			}
		} catch (PersistenceException e) {
			logger.error(EXP_RECHERCHE + "_BARREAU", e);
			throw new FonctionnelleException(EXP_RECHERCHE + "_BARREAU");
		}
	}

	public Integer getNombreBarreaux(Barreau barreau) throws FonctionnelleException {
		String query = "Select count(code) " + getBarreauQuery(barreau);
		Integer r = null;
		try {
			r = ((Long) getSession().createQuery(query).uniqueResult()).intValue();
		} catch (PersistenceException e) {
			logger.error(EXP_RECHERCHE + "_BARREAU", e);
			throw new FonctionnelleException(EXP_RECHERCHE + "_BARREAU");
		}
		return r;
	}

	private String getChefGreffierQuery(ChefGreffier chefGreffier) {
		String hql = " from ChefGreffier where 1=1";
		if (chefGreffier != null) {
			if (chefGreffier.getCode() != null && !StringUtils.isEmpty(chefGreffier.getCode().trim())) {
				hql += " and code ='" + chefGreffier.getCode().trim() + "' ";
			}
			if (chefGreffier.getLibelle() != null && !StringUtils.isEmpty(chefGreffier.getLibelle().trim())) {
				hql += " and Upper(libelle) like '%" + chefGreffier.getLibelle().toUpperCase().trim() + "%' ";
			}
		}
		hql += " order by libelle";
		return hql;

	}

	private String getBarreauQuery(Barreau barreau) {
		String hql = " from Barreau where 1=1";
		if (barreau != null) {
			if (barreau.getCode() != null && !StringUtils.isEmpty(barreau.getCode().trim())) {
				hql += " and code like '%" + barreau.getCode().trim() + "%' ";
			}
			if (barreau.getLibelle() != null && !StringUtils.isEmpty(barreau.getLibelle().trim())) {
				hql += " and Upper(libelle) like '%" + barreau.getLibelle().toUpperCase().trim() + "%' ";
			}
		}
		hql += " order by libelle";
		return hql;

	}

	public List getListSociete(Societe societe) throws PersistenceException {

		Session sessionH = (Session) dao.getSession();
		String sql = " from Societe order by code";
		if (societe != null) {
			if (societe.getId() != 0) {
				sql = " from Societe where id = '" + societe.getId() + "' order by code";
			}
		}
		Query q = sessionH.createQuery(sql);
		return q.list();

	}

	public String getLibelleDecisionByCode(String codeDecision) throws Exception {
		if (codeDecision == null) {
			return null;
		}
		String hql = " from Decision decision where decision.code=" + codeDecision;
		Decision decision = (Decision) getSession().createQuery(hql).uniqueResult();
		if (decision != null) {
			return decision.getLibelle();
		}
		return null;
	}

	public List getListImpactReserveReglement(TypeReglement typeReglement) throws FonctionnelleException {
		try {
			boolean ok = false;
			String hql = " from ImpactRubriqueReserve where 1=1";
			if (typeReglement != null && typeReglement.getCode() != null
					&& !StringUtils.isEmpty(typeReglement.getCode())) {
				hql += " and refTypeReglement=?";
				ok = true;
			}

			Query query = getSession().createQuery(hql);
			if (ok) {
				query.setParameter(0, typeReglement);
			}

			return query.list();

		} catch (PersistenceException e) {
			logger.error(EXP_RECHERCHE + "ImpactRubriqueReserve", e);
			throw new FonctionnelleException(EXP_RECHERCHE + "ImpactRubriqueReserve");
		}
	}

	/**
	 * 
	 * @param modeReglement
	 * @return
	 * @throws FonctionnelleException
	 */
	public List getListImpactReserveRegl(String modeReglement) throws FonctionnelleException {
		try {
			String hql = " from ImpactRubriqueReserve where 1=1";
			if (modeReglement != null && !StringUtils.isEmpty(modeReglement)) {
				hql += " and modeReglement = " + modeReglement;
				hql += " or modeReglement = " + "3";
			}
			return getSession().createQuery(hql).list();
		} catch (PersistenceException e) {
			logger.error(EXP_RECHERCHE + "ImpactRubriqueReserve", e);
			throw new FonctionnelleException(EXP_RECHERCHE + "ImpactRubriqueReserve");
		}
	}

	/**
	 * 
	 * @param etat
	 * @return
	 * @throws FonctionnelleException
	 */
	public List getListImpactRegl(String etat) throws FonctionnelleException {
		try {
			String hql = " from ImpactRubriqueReserve where 1=1";
			if (etat != null && !StringUtils.isEmpty(etat)) {
				hql += " and etatSinistre = " + etat;

			}
			return getSession().createQuery(hql).list();
		} catch (PersistenceException e) {
			logger.error(EXP_RECHERCHE + "ImpactRubriqueReserve", e);
			throw new FonctionnelleException(EXP_RECHERCHE + "ImpactRubriqueReserve");
		}
	}

	/**
	 * Liste Motifs suspension rente
	 * 
	 * @return List
	 * @throws Exception
	 */
	public List getListeMotifsManuelsSuspensionRente(MotifManuelSuspensionRente motif) throws Exception {

		Session sessionH = (Session) dao.getSession();
		String sql = " from MotifManuelSuspensionRente order by code";
		if (motif != null && motif.getCode() != null) {
			sql = " from MotifManuelSuspensionRente where code = '" + motif.getCode() + "' order by code";
		}
		Query q = sessionH.createQuery(sql);
		return q.list();

	}

	/**
	 * 
	 * @param intermediaire
	 * @return
	 * @throws FonctionnelleException
	 */
	public Intermediaire getIntermediaire(Intermediaire intermediaire) throws FonctionnelleException, ExceptionMetier {
		String typeIntermidiaire = "";
		ConsultationVO consultationVO = new ConsultationVO();
		consultationVO.setCodeUtilisateur("test");
		consultationVO.setCodeApplication("test");
		consultationVO.setModeAppel("T");
		consultationVO.setTypeAcces("U");
		consultationVO.setRadObjetInterroge("PDV");
		if (intermediaire.getCodeGuichet() != null && !intermediaire.getCodeGuichet().equals("")) {
			consultationVO.setIdBorneInf(IConstantes.CODE_POINT_VENTE_RMA + intermediaire.getCodeGuichet());
		} else {
			throw new FonctionnelleException("CODEGUICHET_OBLIGATOIRE");
		}
		
		//PointVenteVO pdv = ConsulterOrgService(consultationVO);
		PointVenteVO pdv = kappaClient.ConsulterOrgService(consultationVO);
		if (pdv.getAbrTypePdv().equals("AGG")) {
			typeIntermidiaire = "A";
		}
		if (pdv.getAbrTypePdv().equals("BGD")) {
			typeIntermidiaire = "B";
		}
		if (pdv.getAbrTypePdv().equals("COU")) {
			typeIntermidiaire = "C";
		}
		if (pdv.getAbrTypePdv().equals("GUI")) {
			typeIntermidiaire = "W";
		}

		intermediaire.setCodeTypeIntermediaire(typeIntermidiaire);
		intermediaire.setCode(pdv.getCodeService().substring(1, 5));
		intermediaire.setLibelle(pdv.getAbrObjet());
		return intermediaire;
	}

	public Intermediaire getIntermediaireByCode(Intermediaire intermediaire) throws FonctionnelleException {
		String typeIntermidiaire = "";
		try {
			ConsultationVO consultationVO = new ConsultationVO();
			consultationVO.setCodeUtilisateur("test");
			consultationVO.setCodeApplication("test");
			consultationVO.setModeAppel("T");
			consultationVO.setTypeAcces("U");
			consultationVO.setRadObjetInterroge("PDV");
			// 07092
			if (intermediaire.getCode() != null && !intermediaire.getCode().equals("")) {
				consultationVO.setIdBorneInf(IConstantes.CODE_POINT_VENTE_RMA + "0" + intermediaire.getCode());
			} else {
				throw new FonctionnelleException("CODEINTERMEDIAIRE_OBLIGATOIRE");
			}
			PointVenteVO pdv = ConsulterOrgService(consultationVO);
			if (pdv.getAbrTypePdv().equals("AGG")) {
				typeIntermidiaire = "A";
			}
			if (pdv.getAbrTypePdv().equals("BGD")) {
				typeIntermidiaire = "B";
			}
			if (pdv.getAbrTypePdv().equals("COU")) {
				typeIntermidiaire = "C";
			}
			if (pdv.getAbrTypePdv().equals("GUI")) {
				typeIntermidiaire = "W";
			}

			intermediaire.setCodeTypeIntermediaire(typeIntermidiaire);
			intermediaire.setCode(pdv.getCodeService().substring(1, 5));
			intermediaire.setLibelle(pdv.getAbrObjet());
			intermediaire.setAdresse(pdv.getAdresses().get(0).getLigne1());
			intermediaire.setVille(pdv.getAdresses().get(0).getLigne6());

		} catch (ExceptionMetier e) {
			logger.error("EXP_CONSULTATIONORG", e);
			throw new FonctionnelleException(
					"Impossible de récupérer l'intermedaiare : " + intermediaire.getCodeGuichet());
		}
		return intermediaire;
	}

	private PointVenteVO ConsulterOrgService(ConsultationVO consultationVO) throws ExceptionMetier {
		servicesExternes.setConvert(false);
		PointVenteVO pointVenteVO = (PointVenteVO) servicesExternes.appelService(ServicesExternes.CONSULTATION_ORG,
				consultationVO, "1");
		return pointVenteVO;

	}

	public List getlistPrestationVictime(String typeBenf, String etatSin)
			throws PersistenceException, FonctionnelleException {
		List listRes = null;
		Session sessionH = (Session) dao.getSession();
		StringBuffer sql = new StringBuffer();
		if (typeBenf != null && !StringUtils.isEmpty(typeBenf)) {
			sql.append("select p from Prestation p where p.code in (select q.codeRubrique from PrestationVictime q "
					+ "where q.codeVictime='").append(typeBenf).append("'");
			if (etatSin != null && !StringUtils.isEmpty(etatSin)) {
				sql.append(" and etatSinistre ='").append(etatSin).append("'");
			}
			sql.append(") order by code");
			Query q = sessionH.createQuery(sql.toString());
			listRes = q.list();

		}
		return listRes;
	}

	public List getListPrestVic(String typeBenf, String etatSin) throws FonctionnelleException {
		try {
			String hql = " from PrestationVictime where 1=1";
			if (typeBenf != null && !StringUtils.isEmpty(typeBenf)) {
				hql += " and codeVictime = " + typeBenf;

			}
			if (etatSin != null && !StringUtils.isEmpty(etatSin)) {
				hql += " and etatSinistre = " + etatSin;

			}

			return getSession().createQuery(hql).list();
		} catch (PersistenceException e) {
			logger.error(EXP_RECHERCHE + "ImpactRubriqueReserve", e);
			throw new FonctionnelleException(EXP_RECHERCHE + "ImpactRubriqueReserve");
		}
	}

	public List getListAvocatsConseils() throws PersistenceException {
		Session sessionH = (Session) dao.getSession();
		String sql = "select distinct p.codeAvocatConseil, p.nomAvocatConseil from ProcedureJudiciaire p "
				+ "where p.codeAvocatConseil is not null and p.nomAvocatConseil is not null "
				+ "order by p.nomAvocatConseil";
		Query q = sessionH.createQuery(sql);
		return q.list();
	}

	/**
	 * Recherche des medecins traitant
	 * 
	 * @param prestataire
	 * @param pager
	 * @return
	 * @throws FonctionnelleException
	 */
	public List getListMedecinTraitant(MedecinTraitant medecin, PagerVO pager) throws FonctionnelleException {
		try {

			if (medecin == null) {
				logger.error(EXP_OBJECT_ENTREE);
				throw new FonctionnelleException(EXP_RECHERCHE + "PRESTATAIRE");
			}

			Session sessionH = (Session) dao.getSession();
			StringBuffer sql = new StringBuffer("from MedecinTraitant medecin ");

			sql.append(" where 1=1 ");
			// code
			if (medecin.getCode() != null && !StringUtils.isEmpty(medecin.getCode().trim())
					&& medecin.getCode() != "0") {

				sql.append(" and medecin.code like '%").append(medecin.getCode().toUpperCase().trim()).append("%' ");
			}

			// type
			if (!StringUtils.isEmpty(medecin.getType())) {
				if (!StringUtils.isEmpty(medecin.getType().trim()))
					sql.append(" and medecin.type='").append(medecin.getType().trim()).append("' ");
			}
			// nomRaisonSocial
			if (!StringUtils.isEmpty(medecin.getNomRaisonSocial())) {
				if (!StringUtils.isEmpty(medecin.getNomRaisonSocial().trim()))
					sql.append(" and medecin.nomRaisonSocial like '%").append(medecin.getNomRaisonSocial().trim())
							.append("%' ");
			}

			// adresse

			if (!StringUtils.isEmpty(medecin.getAdresse())) {
				if (!StringUtils.isEmpty(medecin.getAdresse().trim()))
					sql.append(" and upper(medecin.adresse) like '")
							.append(StringUtils.upperCase(medecin.getAdresse().trim())).append("' ");
			}
			// telephone
			if (!StringUtils.isEmpty(medecin.getTelephone())) {
				if (!StringUtils.isEmpty(medecin.getTelephone().trim()))
					sql.append(" and upper(medecin.telephone) like '")
							.append(StringUtils.upperCase(medecin.getTelephone().trim())).append("' ");
			}
			// refDomaineActivite

			// refActivite
			if (medecin.getRefActivite() != null) {

				if (!StringUtils.isEmpty(medecin.getRefActivite().getCode())
						&& !medecin.getRefActivite().getCode().equals("0")) {
					sql.append(" and medecin.refActivite.code='").append(medecin.getRefActivite().getCode())
							.append("' ");
				}
				if (medecin.getRefActivite().getRefDomaineActivite() != null
						&& !StringUtils.isEmpty(medecin.getRefActivite().getRefDomaineActivite().getCode())
						&& !medecin.getRefActivite().getRefDomaineActivite().getCode().equals("0")) {

					sql.append(" and medecin.refDomaineActivite.code='")
							.append(medecin.getRefActivite().getRefDomaineActivite().getCode()).append("' ");
				}
			}
			// ville
			if (medecin.getRefVille() != null
					// && !StringUtils.isEmpty(medecin.getRefVille())
					&& !medecin.getRefVille().getCodeVille().equals("0")) {
				sql.append(" and medecin.refVille='").append(medecin.getRefVille().getCodeVille()).append("' ");
			}
			sql.append(" order by medecin.code asc");

			Query query = sessionH.createQuery(sql.toString());

			if (query != null && pager != null && pager.getNumPage() != null && pager.getPageSize() != null) {
				int pageSize = Integer.valueOf(pager.getPageSize());
				int numPage = Integer.valueOf(pager.getNumPage());
				query.setFirstResult(pageSize * (numPage - 1));
				query.setMaxResults(pageSize);
			}
			if (query != null) {
				return query.list();
			} else {
				return new ArrayList();
			}

		} catch (Exception e) {
			logger.error(EXP_RECHERCHE + "PRESTATAIRE", e);
			throw new FonctionnelleException(EXP_RECHERCHE + "PRESTATAIRE");
		}
	}

	/**
	 * WMOS cette methode permet la récupération de la liste des SORT GSR
	 * (CONSIGNATION) rentier
	 */

	public List getListSortRentier(SortGsr sortGsr) throws PersistenceException {
		Session sessionH = (Session) dao.getSession();
		String sql = " from SortGsr s order by s.id ";
		Query q = sessionH.createQuery(sql);
		return q.list();
	}

	public String getInitialesUtilisateur(String user) {

		try {
			if (Fonctions.isEmpty(user)) {
				return "";
			} else {
				String query = " from InitialeUtilisateur iu where iu.userName='" + user + "'";

				InitialeUtilisateur iu = (InitialeUtilisateur) getSession().createQuery(query).uniqueResult();

				if (iu != null) {
					return iu.getInitiales();
				} else {
					return "";
				}
			}

		} catch (PersistenceException e) {
			logger.error(EXP_RECHERCHE_REGLEMENT, e);
			return "";
		}

	}
	
	public List getListInitialesUtilisateur(InitialeUtilisateur tr) throws Exception {
		Session sessionH = (Session) dao.getSession();
		String sql = " from InitialeUtilisateur order by username";
		if (tr != null) {
			if (tr.getUserName() != null) {
				sql = " from InitialeUtilisateur where code = '" + tr.getUserName() + "' order by code";
			}
		}
		Query q = sessionH.createQuery(sql);
		return q.list();
	}

	public String getEmailUtilisateur(Long idOffre) {

		try {
			String query1 = " from SinMotifOffre m  where m.refMotifOffre = '1' and refOffre.id='" + idOffre + "'";
			SinMotifOffre motif = (SinMotifOffre) getSession().createQuery(query1).uniqueResult();

			String user = motif.getCodeSas();

			if (Fonctions.isEmpty(user)) {
				return "";
			} else {
				String query = " from InitialeUtilisateur iu where iu.userName='" + user + "'";

				InitialeUtilisateur iu = (InitialeUtilisateur) getSession().createQuery(query).uniqueResult();

				if (iu != null) {
					return iu.getEmail();
				} else {
					return "";
				}
			}

		} catch (PersistenceException e) {
			logger.error(EXP_RECHERCHE_REGLEMENT, e);
			return "";
		}

	}
	
	public String getEmailUtilisateurBySas(String sas) {

		try {

				String query = " from InitialeUtilisateur iu where iu.userName='" + sas + "'";

				InitialeUtilisateur iu = (InitialeUtilisateur) getSession().createQuery(query).uniqueResult();

				if (iu != null) {
					return iu.getEmail();
				} else {
					return "";
				}

		} catch (PersistenceException e) {
			logger.error(EXP_RECHERCHE_REGLEMENT, e);
			return "";
		}

	}

	/*public String getEmailIntermediaire(String code, String type) {

		try {
			if (Fonctions.isEmpty(code)) {
				return "";
			} else {
				String query = " from EmailIntermediaire ei where ei.code='" + code + "' and ei.type='" + type + "'";

				EmailIntermediaire ei = (EmailIntermediaire) getSession().createQuery(query).uniqueResult();

				if (ei != null) {
					return ei.getEmail();
				} else {
					return "";
				}
			}

		} catch (PersistenceException e) {
			logger.error("Error :", e);
			return "";
		}

	}*/

	
	public EmailIntermediaire getEmailIntermediaire(String code) throws HibernateException, PersistenceException {

		EmailIntermediaire emailDB = (EmailIntermediaire) getSession().get(EmailIntermediaire.class,
				code);
			if (Fonctions.isEmpty(code)) {
				return null;
			} else {
				if (emailDB != null) {
					return emailDB;
				} 
			}
			return emailDB;

	}
	public String getEmailIntermediaireByCode(String code) {

		try {
			if (Fonctions.isEmpty(code)) {
				return "";
			} else {
				String query = " from EmailIntermediaire ei where ei.code= TO_CHAR("+code+")";

				EmailIntermediaire ei = (EmailIntermediaire) getSession().createQuery(query).uniqueResult();

				if (ei != null) {
					return ei.getEmail();
				} else {
					return "";
				}
			}

		} catch (PersistenceException e) {
			logger.error("Error :", e);
			return "";
		}

	}

	public List getListTiersSaisi(TiersSaisi ts) throws PersistenceException {
		Session sessionH = (Session) dao.getSession();
		String sql = " from TiersSaisi order by libelle";
		if (ts != null) {
			if (ts.getCode() != null) {
				sql = " from TiersSaisi where code = '" + ts.getCode() + "' order by libelle";
			}
		}
		Query q = sessionH.createQuery(sql);
		return q.list();

	}

	public String getLibellePieceByCode(String code) throws PersistenceException {
		Session sessionH = (Session) dao.getSession();
		String sql = "";
		if (code != null) {
			sql = " from PieceAt where code = '" + code + "'";
		}
		Query q = sessionH.createQuery(sql);
		PieceAt pieceAt = (PieceAt) getSession().createQuery(sql).uniqueResult();
		if (pieceAt != null) {
			return pieceAt.getLibelle();
		}
		return null;
	}

	public String getLibelleRejetByCode(String code) throws PersistenceException {
		Session sessionH = (Session) dao.getSession();
		String sql = "";
		if (code != null) {
			sql = " from RejetAt where code = '" + code + "'";
		}
		Query q = sessionH.createQuery(sql);
		RejetAt rejetAt = (RejetAt) getSession().createQuery(sql).uniqueResult();
		if (rejetAt != null) {
			return rejetAt.getLibelle();
		}
		return null;
	}

	public List getListTypeRente(TypeRente tr) throws Exception {

		Session sessionH = (Session) dao.getSession();
		String sql = " from TypeRente order by code";
		if (tr != null) {
			if (tr.getCode() != null) {
				sql = " from TypeRente where code = '" + tr.getCode() + "' order by code";
			}
		}
		Query q = sessionH.createQuery(sql);
		return q.list();
	}

	public List getListCieCondamnee(CieCondamnee cc) throws Exception {

		Session sessionH = (Session) dao.getSession();
		String sql = " from CieCondamnee order by code";
		if (cc != null) {
			if (cc.getCode() != null) {
				sql = " from CieCondamnee where code = '" + cc.getCode() + "' order by code";
			}
		}
		Query q = sessionH.createQuery(sql);
		return q.list();
	}

	public List getListGestionnaireTraitement(GestionnaireTraitement gt) throws Exception {

		Session sessionH = (Session) dao.getSession();
		String sql = " from GestionnaireTraitement order by code";
		if (gt != null) {
			if (gt.getCode() != null) {
				sql = " from GestionnaireTraitement where code = '" + gt.getCode() + "' order by code";
			}
		}
		Query q = sessionH.createQuery(sql);
		return q.list();
	}

	public ParamMail getParamMail() throws PersistenceException {
		Session sessionH = (Session) dao.getSession();
		String sql = " from ParamMail";
		Query q = sessionH.createQuery(sql);
		return (ParamMail) q.uniqueResult();

	}

	public List getListTypeDeclaration() throws PersistenceException {
		Session sessionH = (Session) dao.getSession();
		String sql = " from TypeDeclaration order by code";
		Query q = sessionH.createQuery(sql);
		return q.list();
	}

	public List getListSourceDeclaration() throws PersistenceException {
		Session sessionH = (Session) dao.getSession();
		String sql = " from SourceDeclaration order by code";
		Query q = sessionH.createQuery(sql);
		return q.list();
	}

	public List getlistModeReglementDirect() throws PersistenceException {
		Session sessionH = (Session) dao.getSession();
		String sql = " from ModeReglementDirect order by code";
		Query q = sessionH.createQuery(sql);
		return q.list();
	}

	public List getlistParamPlafondMAD() throws PersistenceException {
		Session sessionH = (Session) dao.getSession();
		String sql = " from PlafondMAD";
		Query q = sessionH.createQuery(sql);
		return q.list();
	}

	public String getEmailUtilisateurInstru(String codeSas) {

		try {

			if (Fonctions.isEmpty(codeSas)) {
				return "";
			} else {
				String query = " from GestionnaireRelance gr where gr.code='" + codeSas + "'";

				GestionnaireRelance gr = (GestionnaireRelance) getSession().createQuery(query).uniqueResult();

				if (gr != null) {
					return gr.getMail();
				} else {
					return "";
				}
			}

		} catch (PersistenceException e) {
			logger.error(EXP_RECHERCHE_REGLEMENT, e);
			return "";
		}

	}

	public Ville getvilleByCode(String code) {
		StringBuffer HQL = new StringBuffer("from Ville ville ");

		HQL.append(" where 1=1 ");
		// code
		if (code != null && !StringUtils.isEmpty(code.trim())
				&& code != "0") {
			HQL.append(" and ville.codeVille='").append(code.toUpperCase().trim()).append("' ");
		}
		
		try {
			Query query = getSession().createQuery(HQL.toString());

			Ville objet = (Ville) query.uniqueResult();

			return objet;
			
		} catch (Exception e) {
			logger.error("problème technique", e);

		}
		return null;
	}
	
	public CompagnieAdverse getCompagnieAdverseByCode(String code) throws PersistenceException {
		StringBuffer HQL = new StringBuffer("from CompagnieAdverse compagnie ");

		HQL.append(" where 1=1 ");
		// code
		if (code != null && !StringUtils.isEmpty(code.trim())
				&& code != "0") {
			HQL.append(" and compagnie.code='").append(code.toUpperCase().trim()).append("' ");
		}
		
		try {
			Query query = getSession().createQuery(HQL.toString());

			CompagnieAdverse objet = (CompagnieAdverse) query.uniqueResult();

			return objet;
			
		} catch (Exception e) {
			logger.error("problème technique", e);

		}
		return null;
	}
	public List<PieceReglement> getAllPieceReglement() {
		List<PieceReglement> listPieceReglement = new ArrayList<PieceReglement>();
		try {
			String requete = new String("from PieceReglement");
			Query query = getSession().createQuery(requete);
			listPieceReglement = query.list();
		} catch (HibernateException e) {
			logger.error("erreur", e);
		} catch (PersistenceException e) {
			logger.error("erreur", e);
		}
		return listPieceReglement;

	}
	public String getLibellePieceReglementByCode(String code) throws PersistenceException {
		Session sessionH = (Session) dao.getSession();
		String sql = "";
		if (code != null) {
			sql = " from PieceReglement where code = '" + code + "'";
		}
		Query q = sessionH.createQuery(sql);
		PieceReglement pieceReglement = (PieceReglement) getSession().createQuery(sql).uniqueResult();
		if (pieceReglement != null) {
			return pieceReglement.getLibelle();
		}
		return null;
	}
	
	public GestionnaireRelance getGestionnaireRelance(String code) throws HibernateException, PersistenceException {

        GestionnaireRelance emailDB = (GestionnaireRelance) getSession().get(GestionnaireRelance.class,
                        code);
                if (Fonctions.isEmpty(code)) {
                        return null;
                } else {
                        if (emailDB != null) {
                               return emailDB;
                        } 
                }
                return emailDB;

}
	public List getListGestionnaireRelance(GestionnaireRelance tr) throws Exception {
        Session sessionH = (Session) dao.getSession();
        String sql = " from GestionnaireRelance order by nomComplet";
        if (tr != null) {
                if (tr.getNomComplet() != null) {
                        sql = " from GestionnaireRelance where code = '" + tr.getNomComplet() + "' order by code";
                }
        }
        Query q = sessionH.createQuery(sql);
        return q.list();
}

	public String getValeurParametrage(final String nomParametrage) {
		Session sessionH;
		try {
			sessionH = (Session) dao.getSession();
			String sql = "select valeur from ParametreKappa where nom='" + nomParametrage + "'";
			Query q = sessionH.createQuery(sql);
			try {
				if (!q.list().isEmpty()) {
					return (String) q.list().get(0);
				}
			} catch (Exception e) {
				logger.error("problème technique", e);
				return "";
			}
		} catch (PersistenceException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return null;
		
 
	    }

}
