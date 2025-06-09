package eai.devass.sinistreat.appli.manager.reglement;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.impl.SessionImpl;

import com.rmawatanya.moyenpaiement.application.metier.valueobjects.OrdDelegationVO;
import com.rmawatanya.moyenpaiement.application.metier.valueobjects.OrdOrdonnoncementVO;
import com.rmawatanya.moyenpaiement.application.metier.valueobjects.PositionnementVO;
import com.rmawatanya.reglement.application.metier.valueobjects.MouvementQuittanceVO;
import com.rmawatanya.reglement.application.metier.valueobjects.QuittanceVO;

import eai.devass.commun.appli.exeptions.ExceptionMetier;
import eai.devass.missionnement.valueobjects.parametrage.PrestataireVO;
import eai.devass.services.IAppelService;
import eai.devass.services.ServicesExternes;
import eai.devass.services.impl.AppelServiceManager;
import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.manager.ParametrageManager;
import eai.devass.sinistreat.appli.manager.sinistre.SinistreManager;
import eai.devass.sinistreat.appli.modele.metier.instruction.DestinataireCheque;
import eai.devass.sinistreat.appli.modele.metier.reglement.DetailReglement;
import eai.devass.sinistreat.appli.modele.metier.reglement.EtatReglement;
import eai.devass.sinistreat.appli.modele.metier.reglement.FraisFuneraire;
import eai.devass.sinistreat.appli.modele.metier.reglement.FraisMedicaux;
import eai.devass.sinistreat.appli.modele.metier.reglement.HistorisationDateRappel;
import eai.devass.sinistreat.appli.modele.metier.reglement.LettreReglement;
import eai.devass.sinistreat.appli.modele.metier.reglement.NumQuittance;
import eai.devass.sinistreat.appli.modele.metier.reglement.Reglement;
import eai.devass.sinistreat.appli.modele.metier.reglement.ReglementPieceAt;
import eai.devass.sinistreat.appli.modele.metier.sinistre.Sinistre;
import eai.devass.sinistreat.appli.modele.parametrage.Barreau;
import eai.devass.sinistreat.appli.modele.parametrage.ChefGreffier;
import eai.devass.sinistreat.appli.modele.parametrage.EtatRgl;
import eai.devass.sinistreat.appli.modele.parametrage.ImpactRubriqueReserve;
import eai.devass.sinistreat.appli.modele.parametrage.Intermediaire;
import eai.devass.sinistreat.appli.modele.parametrage.PlafondMAD;
import eai.devass.sinistreat.appli.modele.parametrage.Prestataire;
import eai.devass.sinistreat.appli.modele.parametrage.TypeFrais;
import eai.devass.sinistreat.appli.modele.parametrage.TypeFuneraire;
import eai.devass.sinistreat.appli.modele.parametrage.TypeQuittance;
import eai.devass.sinistreat.appli.modele.parametrage.TypeReglement;
import eai.devass.sinistreat.appli.utils.Fonctions;
import eai.devass.sinistreat.appli.utils.IConstantes;
import eai.devass.sinistreat.appli.utils.entites.IDate;
import eai.devass.sinistreat.appli.utils.entites.IParam;
import eai.devass.sinistreat.appli.utils.exception.IMessageException;
import eai.devass.sinistreat.appli.utils.exception.IMessageInfo;
import eai.devass.sinistreat.appli.valueobjects.metier.reglement.ReglementVO;
import eai.devass.sinistreat.appli.valueobjects.serialisation.PagerVO;
import ma.co.omnidata.framework.services.core.ServicesFactory;
import ma.co.omnidata.framework.services.dao.IPersistenceService;
import ma.co.omnidata.framework.services.dao.PersistenceException;
import ma.co.omnidata.framework.services.entites.EntiteManagerAbst;

@SuppressWarnings("unchecked")
public class ReglementManager extends EntiteManagerAbst implements IConstantes,
		IMessageException, IMessageInfo {

	private final IPersistenceService dao = (IPersistenceService) ServicesFactory
			.getService(IPersistenceService.class);
	private final IAppelService servicesExternes = (IAppelService) ServicesFactory
			.getService(IAppelService.class);
	private final SimpleDateFormat dateFormat = new SimpleDateFormat(
			IDate.FORMAT_SIMPLE);
	private final SimpleDateFormat dateFormatAnnee = new SimpleDateFormat(
			IDate.FORMAT_ANNEE);
	private final SimpleDateFormat dateFormatMois = new SimpleDateFormat(
			IDate.FORMAT_MOIS);
	private final SinistreManager sinistreManager = (SinistreManager) ServicesFactory
			.getService(SinistreManager.class);
	private final ParametrageManager parametrageManager = (ParametrageManager) ServicesFactory
			.getService(ParametrageManager.class);

	public DateFormat format = new SimpleDateFormat(IDate.FORMAT_HHMM);
	private final Logger logger = Logger.getLogger("loggerGSR");

	public List getListReglement(Reglement reglement, Map map, PagerVO pagerVO)
			throws FonctionnelleException {
		try {
			Object[] objects = this.getListReglementQuery(reglement, map);
			Query query = getQuery((String) objects[0],
					(Map<String, Object>) objects[1]);

			if (pagerVO != null) {
				Integer numpage = 0;
				if (Integer.valueOf(pagerVO.getNumPage()).intValue() > 0) {
					numpage = Integer.valueOf(pagerVO.getNumPage()) - 1;
				}
				return this.getPartCollectionByCondition(query, numpage,
						Integer.valueOf(pagerVO.getPageSize()));
			} else {
				query.setMaxResults(Integer.valueOf(IParam.MAX_SINISTRE));
				return query.list();
			}
		} catch (PersistenceException e) {
			logger.error(EXP_RECHERCHE_REGLEMENT, e);
			throw new FonctionnelleException(EXP_RECHERCHE_REGLEMENT, e);
		}
	}

	private Object[] getListReglementQuery(Reglement reglement, Map paramsDate)
			throws FonctionnelleException {

		if (reglement == null) {
			throw new FonctionnelleException(EXP_RECHERCHE_REGLEMENT);
		}

		Map<String, Object> values = new HashMap<String, Object>();
		StringBuffer query = new StringBuffer("from Reglement reglement  ");
		query.append(" where 1=:un ");
		values.put("un", 1);
		// id
		if (reglement.getId() != null) {
			query.append(" and reglement.id=").append(reglement.getId())
					.append(" ");
		}

		// Sinistre
		if (reglement.getRefSinistre() != null) {
			// idSinistre
			if (reglement.getRefSinistre().getId() != 0) {
				query.append(" and reglement.refSinistre=:refSin");
				values.put("refSin", reglement.getRefSinistre());

			} else {
				// numSinistre
				if (reglement.getRefSinistre().getNumeroSinistre() != null) {
					query.append(
							" and reglement.refSinistre.numeroSinistre like'%")
							.append(reglement.getRefSinistre()
									.getNumeroSinistre().replaceAll(" ", ""))
							.append("%' ");
				}

				// numeroGrave
				if (!StringUtils.isEmpty(reglement.getRefSinistre()
						.getNumeroGrave())) {
					query.append(" and reglement.refSinistre.numeroGrave='")
							.append(reglement.getRefSinistre().getNumeroGrave())
							.append("' ");
				}
			}

		}
		// Code intermediaire
		if (reglement.getCodeIntermediaire() != null) {
			query.append(" and reglement.codeIntermediaire='")
					.append(reglement.getCodeIntermediaire()).append("' ");
		}

		// Type intermediaire
		if (reglement.getTypeIntermediaire() != null) {
			query.append(" and reglement.typeIntermediaire='")
					.append(reglement.getTypeIntermediaire()).append("' ");
		}

		// montant
		if (reglement.getMontant() != null) {
			query.append(" and reglement.montant= ")
					.append(reglement.getMontant()).append(" ");
		}
		// typeQuittance
		if (reglement.getRefTypeQuittance() != null
				&& reglement.getRefTypeQuittance().getCode() != null) {
			query.append(" and reglement.refTypeQuittance=:typeQtc");
			values.put("typeQtc", new TypeQuittance(reglement
					.getRefTypeQuittance().getCode()));
		}
		// typeReglement
		if (reglement.isReglement()) {
			query.append(" and reglement.refTypeReglement<>:typeRgl");
			values.put("typeRgl", new TypeReglement(
					IConstantes.TYPE_REGLEMENT_RECUPERATION));

		} else {
			if (reglement.getRefTypeReglement() != null
					&& reglement.getRefTypeReglement().getCode() != null) {
				query.append(" and reglement.refTypeReglement=:typeRgl");
				values.put("typeRgl", new TypeReglement(reglement
						.getRefTypeReglement().getCode()));
			}
		}
		// numeroQuittance
		if (!StringUtils.isEmpty(reglement.getNumeroQuittance())) {
			query.append(" and reglement.numeroQuittance like '%")
					.append(reglement.getNumeroQuittance().trim()
							.replaceAll(" ", "")).append("%' ");

		}
		// codeIntermediaireRgl
		if (!StringUtils.isEmpty(reglement.getCodeIntermediaireRgl())) {
			query.append(" and reglement.codeIntermediaireRgl like'%")
					.append(reglement.getCodeIntermediaireRgl()).append("%' ");
		}
		// codeAuxiliaire
		if (!StringUtils.isEmpty(reglement.getCodeAuxiliaire())) {
			query.append(" and upper(reglement.codeAuxiliaire)='")
					.append(reglement.getCodeAuxiliaire().toUpperCase())
					.append("' ");
		}
		// nomBeneficiaire
		if (!StringUtils.isEmpty(reglement.getNomBeneficiaire())) {
			query.append(" and upper(reglement.nomBeneficiaire) like'")
					.append(reglement.getNomBeneficiaire().toUpperCase())
					.append("' ");
		}
		// numeroBordereau
		if (!StringUtils.isEmpty(reglement.getNumeroBordereau())) {
			query.append(" and reglement.numeroBordereau='")
					.append(reglement.getNumeroBordereau()).append("' ");
		}
		// dateEtablissement
		if (reglement.getDateEtablissement() != null) {
			query.append(" and reglement.dateEtablissement = to_date('"
					+ dateFormat.format(reglement.getDateEtablissement())
					+ "', 'dd/MM/yyyy')");
		}
		// numero cheque
		if (reglement.getNumCheque() != null) {
			query.append(" and reglement.numCheque='")
					.append(reglement.getNumCheque()).append("' ");
		}

		/** Date Debut et Fin */
		if (paramsDate != null) {
			query.append(construireDcDate(
					(String) paramsDate.get(IDate.DATE_DEBUT),
					(String) paramsDate.get(IDate.DATE_FIN),
					"reglement.dateEtablissement"));
		}

		// Etat reglement
		String[] codeEtats = reglement.getCodeEtatRgl();
		if (codeEtats == null && reglement.getRefEtatReglement() != null
				&& reglement.getRefEtatReglement().getCode() != null) {
			codeEtats = new String[] { reglement.getRefEtatReglement()
					.getCode() };
		}

		if (codeEtats != null) {
			query.append(" and reglement.refEtatReglement in (:listEtatRgl)");
			List<EtatRgl> listEtatRgl = new ArrayList<EtatRgl>();
			for (String curCodeEtat : codeEtats) {
				listEtatRgl.add(new EtatRgl(curCodeEtat));
			}
			values.put("listEtatRgl", listEtatRgl);
		}

		query.append(" order by reglement.numeroQuittance desc");

		// Return
		Object[] objects = new Object[2];
		objects[0] = query.toString();
		objects[1] = values;
		return objects;
	}

	private String construireDcDate(String dateDebut, String dateFin,
			String property) throws FonctionnelleException {
		try {
			String whereBetween = "";
			if (dateDebut != null && !StringUtils.isEmpty(dateDebut)
					&& dateFin != null && !StringUtils.isEmpty(dateFin)) {
				// betwen
				whereBetween += " and " + property + " between to_date('"
						+ dateFormat.format(dateFormat.parse(dateDebut))
						+ "  00:00:00','" + IDate.FORMAT_HHMM_SQL + "') "
						+ "and to_date('"
						+ dateFormat.format(dateFormat.parse(dateFin))
						+ " 23:59:59','" + IDate.FORMAT_HHMM_SQL + "')";
			} else {
				if (dateFin != null && !StringUtils.isEmpty(dateFin)) {
					// inferiere a daateFin
					whereBetween += " and " + property + " between to_date('"
							+ IDate.DATE_MIN + " 00:00:00','"
							+ IDate.FORMAT_HHMM_SQL + "') " + "and to_date('"
							+ dateFormat.format(dateFormat.parse(dateFin))
							+ " 23:59:59','" + IDate.FORMAT_HHMM_SQL + "')";

				} else if (dateDebut != null && !StringUtils.isEmpty(dateDebut)) {
					// superier à dateDebut
					whereBetween += " and " + property + " between to_date('"
							+ dateFormat.format(dateFormat.parse(dateDebut))
							+ " 00:00:00','" + IDate.FORMAT_HHMM_SQL + "') "
							+ "and to_date('" + IDate.DATE_MAX + " 23:59:59','"
							+ IDate.FORMAT_HHMM_SQL + "')";
				}
			}
			return whereBetween;
		} catch (ParseException e) {
			logger.error(EXP_RECHERCHE_REGLEMENT, e);
			throw new FonctionnelleException(EXP_RECHERCHE_REGLEMENT, e);
		}
	}

	public Reglement creerReglement(Reglement reglement)
			throws FonctionnelleException {
		try {
			Sinistre sinDB = sinistreManager.getSinistreById(reglement
					.getRefSinistre().getId());
			if (sinDB == null) {
				throw new FonctionnelleException(EXP_SINISTRE_INEXISTANT);
			}
			reglement.setEmissionITT(false);
			reglement.setDateCreation(getDate());
			if (reglement.getDateEmission() == null) {
				reglement.setDateEmission(getDate());
			}
			if (reglement.getDateReglement() == null) {
				reglement.setDateReglement(getDate());
			}
			if (reglement.getDateEtablissement() == null) {
				reglement.setDateEtablissement(getDate());
			}
			if (reglement.getDateEtat() == null) {
				reglement.setDateEtat(getDate());
			}			
			if (reglement.getNbRelance() == null) {
				reglement. setNbRelance("0");
			}
			// code service
			if (sinDB.getNumeroGrave() != null) {
				reglement.setService(IParam.CODE_SERVICE_SINISTRE_GRAVE);
			} else {
				reglement.setService(IParam.CODE_SERVICE_SINISTRE_SIMPLE);
			}
			//EVOL ZONE A RISQUE : verifier si quittance est a zone de risque
			
            Boolean existPrest = false;
            reglement.setZoneARisque("0");
            if(reglement.getListDetailReglement() !=null) {
                  for (DetailReglement dreg : reglement.getListDetailReglement()) {
                          if("20".equals(dreg.getCodePrestation())) {
                                existPrest = true;
                                break;
                          }
                        }
                   if(existPrest)  {
                          if(verifierReglmentZoneARisque(sinDB.getId())) {
                                reglement.setZoneARisque("1");
                          }
                        }
                  }
           //FIN EVOL
			reglement.setNumeroQuittance(genererNumeroQuittance());
			// reglement.setNumeroQuittance("002201612000001");

			for (int i = 0; i < reglement.getListDetailReglement().size(); i++) {
				DetailReglement dtlRgl = reglement.getListDetailReglement()
						.get(i);
				dtlRgl.setRefReglement(reglement);
			}
			if (reglement.getListFraisMedicaux() != null
					&& reglement.getListFraisMedicaux().size() != 0) {
				for (int i = 0; i < reglement.getListFraisMedicaux().size(); i++) {
					FraisMedicaux fraisMedic = reglement.getListFraisMedicaux()
							.get(i);
					fraisMedic.setRefReglement(reglement);
				}
			}
			if (reglement.getListFraisFuneraire() != null
					&& reglement.getListFraisFuneraire().size() != 0) {
				for (int i = 0; i < reglement.getListFraisFuneraire().size(); i++) {
					FraisFuneraire fraisFuner = reglement.getListFraisFuneraire()
							.get(i);
					fraisFuner.setRefReglement(reglement);
				}
			}
			
			dao.createObject(reglement);
			return reglement;
		} catch (PersistenceException e) {
			logger.error(EXP_CREATION_REGLEMENT, e);
			throw new FonctionnelleException(EXP_CREATION_REGLEMENT, e);
		} catch (ParseException e) {
			logger.error(EXP_CREATION_REGLEMENT, e);
			throw new FonctionnelleException(EXP_CREATION_REGLEMENT, e);
		}
	}

	private String getAnnee() {
		return dateFormatAnnee.format(new Date());
	}

	private String getMois() {
		return dateFormatMois.format(new Date());
	}

	public Date getDate() throws ParseException {
		return dateFormat.parse(dateFormat.format(new Date()));
	}

	private List ListNumeroQuittance() throws HibernateException,
			PersistenceException {
		String queryString = ("from NumQuittance numQuittance order by id");
		Query query = getSession().createQuery(queryString);
		return query.list();
	}

	private String numeroQuittance() throws HibernateException,
			PersistenceException {
		// examiner le numéro en lacal
		List listNum = ListNumeroQuittance();
		String compteur = "99999";
		String numero = null;
		NumQuittance quittance = new NumQuittance();
		if (!listNum.isEmpty() && !listNum.isEmpty()) {
			Iterator iterator = listNum.iterator();
			while (iterator.hasNext()) {
				NumQuittance num = (NumQuittance) iterator.next();
				String annee = getAnnee();
				String mois = getMois();
				String numeroQui = num.getNumQuittance();
				String anneeQui = numeroQui.substring(3, 7);
				String moisQuit = numeroQui.substring(7, 9);
				String compteur0 = numeroQui.substring(9, 15);
				if (anneeQui.equals(annee) && moisQuit.equals(mois)
						&& (compteur0.compareTo(compteur) < 0)) {
					compteur = compteur0;
					numero = numeroQui;
					quittance = num;
				}
			}
			dao.delete(quittance);
		}
		return numero;
	}

	private synchronized String genererNumeroQuittance()
			throws FonctionnelleException {
		CallableStatement call = null;
		Connection connection = null;
		try {
			String numero = numeroQuittance();
			if (numero != null) {
				return numero;
			} else {
				String annee = getAnnee();
				String mois = getMois();
				connection = ((SessionImpl) getSession()).connection();
				String sql = "{call SYN_INCREMENT_COMPT_QTC(?,?,?,?,?)}";
				call = connection.prepareCall(sql);
				call.setLong(1, Long.valueOf(IParam.CODE_BRANCHE_AT));
				call.setLong(2, Long.valueOf(annee));
				call.setLong(3, Long.valueOf(mois));
				call.setString(4, IParam.TYPE_QTC_REGLEMENT);
				call.registerOutParameter(5, Types.DOUBLE);
				call.execute();
				String compteurQuittance = String.valueOf(call.getLong(5));
				if (compteurQuittance == null
						|| StringUtils.isEmpty(compteurQuittance)
						|| compteurQuittance.equals("0")) {
					throw new FonctionnelleException(
							EXP_NUM_GENERATION_QUITTANCE);
				}
				return IParam.CODE_SOCIETE_AT + annee + mois
						+ compteurQuittance;
			}

		} catch (SQLException e) {
			logger.error(e.getMessage());
			throw new FonctionnelleException(EXP_NUM_GENERATION_QUITTANCE,
					e.getMessage());
		} catch (PersistenceException e) {
			logger.error(e.getMessage());
			throw new FonctionnelleException(EXP_NUM_GENERATION_QUITTANCE,
					e.getMessage());
		} finally {
			try {
				if (call != null) {
					call.close();
				}
			} catch (SQLException e) {
				logger.error(EXP_SQL, e);
				throw new FonctionnelleException(EXP_SQL);
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e2) {
					logger.error("Erreur base de donnée", e2);
					throw new FonctionnelleException(e2.getMessage(), e2);
				} finally {
					connection = null;
				}
			}
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

	/**
	 * Récupérer la Session hibernate via la DAO
	 * 
	 * @return
	 * @throws PersistenceException
	 */
	public Session getSession() throws PersistenceException {
		return (Session) dao.getSession();
	}

	public Integer getNombreQuittance(Reglement reglement, HashMap map)
			throws Exception {

		if (reglement != null && reglement.getId() != null) {
			return 1;
		}

		Object[] objects = this.getListReglementQuery(reglement, map);
		String hql = "Select count (id) " + (String) objects[0];
		Query query = getQuery(hql, (Map<String, Object>) objects[1]);
		Long nombreResultat = (Long) query.uniqueResult();
		return Integer.valueOf(nombreResultat.intValue());
	}

	public Integer getNombreReglement(Reglement reglement, HashMap map,
			Boolean editer) throws Exception {

		Object[] objects = this.getListReglementByPrestataion20Query(reglement,
				map, editer);
		String hql = "Select count (reglement.id) " + (String) objects[0];
		Query query = getQuery(hql, (Map<String, Object>) objects[1]);
		Long nombreResultat = (Long) query.uniqueResult();
		return nombreResultat.intValue();
	}

	public Reglement getReglementByNum(String numQtc)
			throws FonctionnelleException {
		try {
			if (numQtc == null) {
				return null;
			}
			String query = " from Reglement rgl where rgl.numeroQuittance='"
					+ numQtc + "'";
			return (Reglement) getSession().createQuery(query).uniqueResult();
		} catch (PersistenceException e) {
			logger.error(EXP_RECHERCHE_REGLEMENT, e);
			throw new FonctionnelleException(EXP_RECHERCHE_REGLEMENT, e);
		}

	}

	public List<Reglement> getListReglementReglementAValider(
			Reglement reglement, HashMap map, PagerVO pagerVO,
			BigDecimal seuil, BigDecimal seuilsub) throws Exception {
		if (reglement == null) {
			return null;
		}

		Query query = getSession().createQuery(
				this.getListReglementAValiderQuery(reglement, map, seuil,
						seuilsub));

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

	public List<Reglement> getListReglementITTAValider(
			ReglementVO reglement,List<Date> dates) throws Exception {
		if (reglement == null) {
			return null;
		}

		Query query = getSession().createQuery(
				this.getListReglementITTAValiderQuery(reglement, dates));

		return query.list();
	}

	private String getListReglementITTAValiderQuery(ReglementVO reglement, List<Date> dates) {

		StringBuffer query = new StringBuffer("from Reglement reglement  ");
		query.append(" where 1=1 ");
		// typeReglement
		if (reglement.getRefTypeReglement() != null
				&& reglement.getRefTypeReglement().getCode() != null) {
			query.append(" and reglement.refTypeReglement.code='")
					.append(reglement.getRefTypeReglement().getCode())
					.append("' ");
		}
		if (reglement.getCodeIntermediaire() != null) {
			query.append(" and reglement.codeIntermediaire='")
					.append(reglement.getCodeIntermediaire()).append("' ");
		}

		if (reglement.getTypeIntermediaire() != null) {
			query.append(" and reglement.typeIntermediaire='")
					.append(reglement.getTypeIntermediaire()).append("' ");
		}

		// Etat reglement
		String[] codeEtats = reglement.getCodeEtatRgl();
		if (codeEtats == null && reglement.getRefEtatReglement() != null
				&& reglement.getRefEtatReglement().getCode() != null) {
			codeEtats = new String[] { reglement.getRefEtatReglement()
					.getCode() };
		}

		if (codeEtats != null) {
			query.append(" and reglement.refEtatReglement.code in ('");
			for (String curCodeEtat : codeEtats) {
				query.append(curCodeEtat).append("','");
			}
			query.replace(query.length() - 2, query.length(), ")");
		}
		if ("1".equals(reglement.getRappel())) {
			query.append(" AND reglement.dateProchaineEcheance - 5 <= SYSDATE ");
			query.append(" AND reglement.rappel = true");
		} else {
			query.append(" AND reglement.rappel = false");
		}

		//Date Creation
		if(dates != null && dates.size() !=0){
			query.append(" and trunc (reglement.dateCreation, 'DD')  in (");
			for (Date dateCreation : dates) {
				query.append(" to_date( '"+dateFormat.format(dateCreation)+"', 'DD/MM/YYYY')").append(",");
			}
			query.replace(query.length() - 1, query.length(), ")");
		}
		query.append(" order by reglement.dateCreation");
		return query.toString();
	}

	private String getListReglementAValiderQuery(Reglement reglement,
			HashMap map, BigDecimal seuil, BigDecimal seuilsub) {

		StringBuffer query = new StringBuffer("from Reglement reglement  ");
		query.append(" where 1=1 ");
		//modeReglement
		if (reglement.getModeReglement() != null) {
			if (reglement.getModeReglement().equals("6")) {
			query.append(" and reglement.modeReglement='")
					.append(Integer.parseInt(reglement.getModeReglement()))
					.append("' ");
			}
		}
		
		// typeReglement
		if (reglement.getRefTypeReglement() != null
				&& reglement.getRefTypeReglement().getCode() != null) {
			query.append(" and reglement.refTypeReglement.code='")
					.append(reglement.getRefTypeReglement().getCode())
					.append("' ");
		}
		if (reglement.getCodeIntermediaire() != null) {
			query.append(" and reglement.codeIntermediaire='")
					.append(reglement.getCodeIntermediaire()).append("' ");
		}

		if (reglement.getTypeIntermediaire() != null) {
			query.append(" and reglement.typeIntermediaire='")
					.append(reglement.getTypeIntermediaire()).append("' ");
		}

		// Etat reglement
		String[] codeEtats = reglement.getCodeEtatRgl();
		if (codeEtats == null && reglement.getRefEtatReglement() != null
				&& reglement.getRefEtatReglement().getCode() != null) {
			codeEtats = new String[] { reglement.getRefEtatReglement()
					.getCode() };
		}

		if (codeEtats != null) {
			query.append(" and reglement.refEtatReglement.code in ('");
			for (String curCodeEtat : codeEtats) {
				query.append(curCodeEtat).append("','");
			}
			query.replace(query.length() - 2, query.length(), ")");
		}
		//ModeReglement
		if (reglement.getModeReglement() != null) {
			if (!reglement.getModeReglement().equals("6")) {
			query.append(" and reglement.modeReglement not in (6)");
			}
		}/*
		if (reglement.getModeReglement() == null) {
			query.append(" and reglement.modeReglement not in (6)");
		}*/
		if (reglement.getModeReglement() == null) {
			query.append(" and (reglement.modeReglement not in (6) or reglement.modeReglement is null)");
		}

		/*** limiter le nombre de ligne à 50 ***/
		query.append(" order by reglement.nomUserCreateur ");
		return query.toString();
	}

	public Integer getNombreReglementAValider(Reglement reglement, HashMap map,
			BigDecimal seuil, BigDecimal seuilsub) throws Exception {
		String query = "Select count (id) "
				+ this.getListReglementAValiderQuery(reglement, map, seuil,
						seuilsub);
		Long nombreResultat = (Long) getSession().createQuery(query)
				.uniqueResult();

		return nombreResultat.intValue();
	}

	public List<Reglement> ExtraireOrdonnecement(Reglement reglement) {
		String codeSas = reglement.getCodeUserModificateur();

		List<Reglement> listreg = new ArrayList<Reglement>();
		try {

			List<OrdOrdonnoncementVO> ordonnoncementList = (List) ((List) servicesExternes
					.appelService(ServicesExternes.ORDONNONCEMENT_QUITTANCE,
							reglement, codeSas)).get(0);

			for (OrdOrdonnoncementVO ordOrdonnoncementVO : ordonnoncementList) {

				PositionnementVO positionnement = ordOrdonnoncementVO
						.getPositionnement();

				List<com.rmawatanya.moyenpaiement.application.metier.valueobjects.QuittanceVO> listquittance = positionnement
						.getRefQuittance();

				for (com.rmawatanya.moyenpaiement.application.metier.valueobjects.QuittanceVO quittance : listquittance) {
					// correction sonar Dead store to local variable
					Reglement reg = getReglementByNum(quittance
							.getNumQuittance());
					// Reglement direct et à l'état
					// "EN INSTANCE VALIDATION SUPERIRUR"
					if (reg != null /*
									 * &&
									 * "6".equals(reg.getRefEtatReglement().getCode
									 * ()) &&
									 * "1".equals(reg.getRefTypeReglement(
									 * ).getCode())
									 */) {
						/*if(reglement.getModeReglement().equals("6") || reglement.getModeReglement() == "6") {
							reg.setModeReglement("6");
						}*/
						listreg.add(reg);

					}
				}

			}

		} catch (Exception e) {
			logger.error("erreur", e);
		}
		return listreg;

	}

	public List<Reglement> ExtraireOrdonnecementSup(Reglement reglement,
			String codeSasSup) {
		logger.info("Debut Method ExtraireOrdonnecementSup");
		List<Reglement> listreg = new ArrayList<Reglement>();
		try {
			List<OrdOrdonnoncementVO> ordonnoncementList = (List) ((List) servicesExternes
					.appelService(ServicesExternes.ORDONNONCEMENT_QUITTANCE,
							reglement, codeSasSup)).get(0);

			PositionnementVO positionnement = null;
			Reglement reg = null;
			for (OrdOrdonnoncementVO ordOrdonnoncementVO : ordonnoncementList) {
				positionnement = ordOrdonnoncementVO.getPositionnement();
				List<com.rmawatanya.moyenpaiement.application.metier.valueobjects.QuittanceVO> listquittance = positionnement
						.getRefQuittance();

				for (com.rmawatanya.moyenpaiement.application.metier.valueobjects.QuittanceVO quittance : listquittance) {
					reg = getReglementByNum(quittance.getNumQuittance());
					if (reg != null) {
						listreg.add(reg);
					}
				}

			}
			logger.info("Fin Method ExtraireOrdonnecementSup");
		} catch (Exception e) {
			logger.error("erreur", e);
		}
		return listreg;

	}
	
	public List<Reglement> getListReglementByModeReglment(List<Reglement> listReglement, String modeReglement) {
		List<Reglement> listreg = new ArrayList<Reglement>();
		Reglement reg = null;
		for(Reglement reglement : listReglement) {
			if(reglement.getModeReglement() !=null || !reglement.getModeReglement().isEmpty()) {
			if(reglement.getModeReglement().equals(modeReglement) || reglement.getModeReglement() == modeReglement) {
				reg = reglement;
				listreg.add(reg);
				}
				
			}
		}
		return listreg;
	}

	public Double getCumulQuittanceEmise(Reglement reglement) throws Exception {

		reglement
				.setCodeEtatRgl(new String[] {
						ETAT_REGLEMENT_EN_INSTANCE_DE_REGLEMENT,
						ETAT_REGLEMENT_REGLE });

		Object[] objects = getListReglementQuery(reglement, null);
		String hql = (String) objects[0];
		Map<String, Object> values = (Map<String, Object>) objects[1];
		hql = "select sum(montant) " + hql;
		Query query = getQuery(hql, values);
		Double cumulQuittanceEmise = (Double) query.uniqueResult();
		if (cumulQuittanceEmise == null) {
			cumulQuittanceEmise = 0D;
		}
		return Math.rint(cumulQuittanceEmise * 100) / 100;
	}

	
	
	/**
	 * Montant réglement < paramètre n
	 * 
	 * @param reglement
	 * @return boolean
	 * @throws FonctionnelleException
	 */
	public boolean validerMontantReglement(Reglement reglement)
			throws FonctionnelleException {
		try {
			boolean valide = false;
			Double montantReglement = reglement.getMontant();
			if (montantReglement != null) {
				valide = montantReglement.compareTo(IParam.MONTANTMAXREGLEMENT) <= 0;
			}
			return valide;
		} catch (Exception e) {
			logger.error(EXP_VALIDATION_REGLEMENT, e);
			throw new FonctionnelleException(EXP_VALIDATION_REGLEMENT, e);
		}
	}

	/**
	 * Verifie que le montants du réglement ne dépasse pas les résérves selon
	 * code de rubrique
	 * 
	 * @param reglement
	 * @return boolean
	 * @throws FonctionnelleException
	 */
	public boolean validerMontantReserveReglement(Reglement reglement)
			throws FonctionnelleException {
		boolean valide = false;
		try {
			String numSinistre = reglement.getRefSinistre().getNumeroSinistre();
			if (numSinistre == null || "".equals(numSinistre)) {
				throw new FonctionnelleException(EXP_NUM_DOSSIER_NOT_VALIDE);
			}
			// QC N° 906
			Sinistre sinistre = reglement.getRefSinistre();
			if (sinistre == null) {
				throw new FonctionnelleException(EXP_SINISTRE_INEXISTANT);
			}

			List<ImpactRubriqueReserve> impactReserveListe = parametrageManager
					.getListImpactReserveReglement(null);
			if (impactReserveListe != null
					&& reglement.getListDetailReglement() != null) {
				Double sumMontantOrdinaire = getSumMontant(
						reglement.getListDetailReglement(), impactReserveListe,
						CODE_RESERVE_ORDINAIRE);
				Double sumMontantGrave = getSumMontant(
						reglement.getListDetailReglement(), impactReserveListe,
						CODE_RESERVE_GRAVE);
				Double sumMontantprothese = getSumMontant(
						reglement.getListDetailReglement(), impactReserveListe,
						CODE_RESERVE_PROTHESE);

				// QC prod N°20 : comparer le montant du reglement avec le plus
				// grand des deux valeurs reserves
				Double reserveGra = Math.max(sinistre.getReserveGrave(),
						sinistre.getReserveGraveActuel());

				Double reserveOrd = null;
				Double reservePro = null;
				if (sinistre.getReserveOrdinaire() != null) {
					reserveOrd = Math.max(sinistre.getReserveOrdinaire(),
							sinistre.getReserveOrdinaireActuel());
				} else {
					reserveOrd = sinistre.getReserveOrdinaireActuel();
				}

				reservePro = Math.max(sinistre.getReserveProthese(),
						sinistre.getReserveProtheseActuel());
				valide = sumMontantOrdinaire.compareTo(reserveOrd) <= 0
						&& sumMontantGrave.compareTo(reserveGra) <= 0
						&& sumMontantprothese.compareTo(reservePro) <= 0;

			}
		} catch (Exception e) {
			logger.error(EXP_VALIDATION_REGLEMENT, e);
			throw new FonctionnelleException(EXP_VALIDATION_REGLEMENT, e);
		}
		return valide;
	}

	/**
	 * Calcule le montant des rubriques affectant la résérve donnée comme
	 * pramètre.
	 * 
	 * @param listDetailReglement
	 * @param impactReserveListe
	 * @return double
	 * @throws FonctionnelleException
	 */
	private Double getSumMontant(List<DetailReglement> listDetailReglement,
			List impactReserveListe, String typeReserve)
			throws FonctionnelleException {
		try {
			Double sumMontantOrdinaire = Double.valueOf(0);
			for (DetailReglement detail : listDetailReglement) {

				if (detail == null || detail.getRefPrestation() == null) {
					throw new FonctionnelleException(
							"Le detail réglement ou la prestation ne peut être null !!");
				}
				if (impact(detail.getRefPrestation().getCode(),
						impactReserveListe, typeReserve)) {

					Double montantRejete = detail.getMontantRejete() == null ? 0
							: detail.getMontantRejete();
					sumMontantOrdinaire += detail.getMontant() - montantRejete;
				}
			}
			return sumMontantOrdinaire;
		} catch (Exception e) {
			logger.error(EXP_VALIDATION_REGLEMENT, e);
			throw new FonctionnelleException(EXP_VALIDATION_REGLEMENT, e);
		}
	}

	/**
	 * Verifie si la rubrique impact la reserve donnée comme paramètre
	 * 
	 * @param codePrestation
	 * @param impactReserveListe
	 * @param typeReserve
	 * @return boolean
	 */
	private boolean impact(String codePrestation, List impactReserveListe,
			String typeReserve) {

		for (Object object : impactReserveListe) {
			ImpactRubriqueReserve impactRubRes = (ImpactRubriqueReserve) object;
			if (codePrestation
					.equals(impactRubRes.getRefPrestation().getCode())) {
				return impactRubRes.getTypeReserve().equals(typeReserve);

			}
		}
		return false;
	}

	/**
	 * Verifie s'il existe un réglement avec le meme beneficaire, montant et
	 * détails créé le même jour
	 * 
	 * @param reglement
	 * @return numquittance
	 * @throws FonctionnelleException
	 */
	public String validerDoubleReglement(Reglement reglement)
			throws FonctionnelleException {
		try {
			String numQuittance = "0";
			String hql = "from Reglement r where r.nomBeneficiaire like :nomBeneficaire"
					+ " and r.montant= :montant"
					+ " and r.dateCreation= :dateCreation";
			Query q = getSession().createQuery(hql);
			q.setParameter("nomBeneficaire", reglement.getNomBeneficiaire());
			q.setParameter("montant", reglement.getMontant());
			q.setParameter("dateCreation", getDate());
			List listReglements = q.list();
			if (listReglements != null && !listReglements.isEmpty()) {
				for (Object object : listReglements) {
					Reglement reglementVar = (Reglement) object;
					if (memeDetails(reglementVar.getListDetailReglement(),
							reglement.getListDetailReglement())) {
						numQuittance = reglementVar.getNumeroQuittance();
						break;
					}
				}
			}
			return numQuittance;
		} catch (Exception e) {
			logger.error(EXP_VALIDATION_REGLEMENT, e);
			throw new FonctionnelleException(EXP_VALIDATION_REGLEMENT, e);
		}
	}

	private boolean memeDetails(List<DetailReglement> listDetailReglement,
			List<DetailReglement> listDetailReglement2) {
		boolean memeDetails = false;
		if (listDetailReglement != null && listDetailReglement2 != null
				&& listDetailReglement.size() == listDetailReglement2.size()) {
			int i = 0;
			memeDetails = true;
			while (memeDetails && i < listDetailReglement.size()) {
				memeDetails = contient(listDetailReglement2,
						listDetailReglement.get(i));
				i++;
			}
		}
		return memeDetails;
	}

	private boolean contient(List<DetailReglement> listDetailReglement,
			DetailReglement detailReglement) {

		if (detailReglement == null
				|| detailReglement.getRefPrestation() == null) {
			return false;
		}

		for (DetailReglement curDetailReglement : listDetailReglement) {
			if (curDetailReglement == null
					|| curDetailReglement.getRefPrestation().getCode() == null
					|| curDetailReglement.getRefPrestation() == null) {
				return false;
			}

			if (curDetailReglement.getMontant() == null) {
				return false;
			}

			if (curDetailReglement.getRefPrestation().getCode()
					.equals(detailReglement.getRefPrestation().getCode())
					&& curDetailReglement.getMontant().equals(
							detailReglement.getMontant())) {
				return true;
			}

		}

		return false;
	}

	private boolean contient(List<FraisMedicaux> listFraisMedicaux,
			FraisMedicaux frais) {

		if (frais == null || frais.getRefTypeFrais() == null) {
			return false;
		}

		for (FraisMedicaux curFraisMedicaux : listFraisMedicaux) {
			if (curFraisMedicaux == null
					|| curFraisMedicaux.getRefTypeFrais().getCode() == null
					|| curFraisMedicaux.getRefTypeFrais() == null) {
				return false;
			}

			if (curFraisMedicaux.getMontantFacture() == null) {
				return false;
			}

			if (curFraisMedicaux.getRefTypeFrais().getCode()
					.equals(frais.getRefTypeFrais().getCode())
					&& curFraisMedicaux.getMontantFacture().equals(
							frais.getMontantFacture())) {
				return true;
			}

		}

		return false;
	}
	
	private boolean contient(List<FraisFuneraire> listFraisFuneraire,
			FraisFuneraire funeraire) {

		if (funeraire == null || funeraire.getRefTypeFuneraire() == null) {
			return false;
		}

		for (FraisFuneraire curFraisFuneraire : listFraisFuneraire) {
			if (curFraisFuneraire == null
					|| curFraisFuneraire.getRefTypeFuneraire().getCode() == null
					|| curFraisFuneraire.getRefTypeFuneraire() == null) {
				return false;
			}

			if (curFraisFuneraire.getMontantFacture() == null) {
				return false;
			}

			if (curFraisFuneraire.getRefTypeFuneraire().getCode()
					.equals(funeraire.getRefTypeFuneraire().getCode())
					&& curFraisFuneraire.getMontantFacture().equals(
							funeraire.getMontantFacture())) {
				return true;
			}

		}

		return false;
	}
	
	

	public void updateCumulRegValidation(Reglement rgl)
			throws FonctionnelleException {

		Sinistre sindb = rgl.getRefSinistre();

		// Etat not null
		if (rgl.getRefEtatReglement() == null) {
			throw new FonctionnelleException("");
		}

		// Type not null
		if (rgl.getRefTypeReglement() == null) {
			throw new FonctionnelleException("");
		}

		// Positionnement SUPERIEUER
		if (rgl.isInstanceValidationSup()) {
			return;
		}

		// Error lors du positionnement avec emission de la quittance
		if (rgl.isInstancePositionnement()
				&& IConstantes.TYPE_REGLEMENT_DIRECT.equals(rgl
						.getRefTypeReglement().getCode())) {
			return;
		}

		// Cas RECUPERATION
		if (IConstantes.TYPE_REGLEMENT_RECUPERATION.equals(rgl
				.getRefTypeReglement().getCode())) {
			sindb.setCumulReglementAnne(new BigDecimal(sindb
					.getCumulReglementAnne() - rgl.getMontant()).setScale(2,
					BigDecimal.ROUND_HALF_EVEN).doubleValue());
			return;
		}

		sindb.setCumulReglementAnne(new BigDecimal(sindb
				.getCumulReglementAnne() + rgl.getMontant()).setScale(2,
				BigDecimal.ROUND_HALF_EVEN).doubleValue());

	}

	/**
	 * Verifie que le nom et le code du benificaire correspondent
	 * 
	 * @param reglement
	 * @return boolean
	 * @throws FonctionnelleException
	 */
	public boolean validerBeneficiaire(Reglement reglement)
			throws FonctionnelleException {
		try {
			boolean valide = false;
			String typeBeneficaire = reglement.getTypeBeneficiaire();
			String codeInter = reglement.getCodeIntermediaireRgl();
			String typeInter = reglement.getTypeIntermediaire();
			List listResult = null;
			if (typeBeneficaire != null
					&& !StringUtils.isEmpty(typeBeneficaire)) {
				if (typeBeneficaire.equals(BENEFICIAIRE_INTERMEDIAIRE)) {
					Intermediaire intermediaire = new Intermediaire();
					intermediaire.setCode(codeInter);
					intermediaire.setCodeTypeIntermediaire(typeInter);
					listResult = parametrageManager.getListIntermediaire(
							intermediaire, null);
					if (listResult != null && !listResult.isEmpty()
							&& listResult.size() == 1) {
						valide = ((Intermediaire) listResult.get(0))
								.getLibelle().equalsIgnoreCase(
										reglement.getNomBeneficiaire()
												.toUpperCase());
					}
				} else if (typeBeneficaire.equals(BENEFICIAIRE_CHEF_GREFFIER)) {
					ChefGreffier chefGreffier = new ChefGreffier();
					chefGreffier.setCode(reglement.getCodeChefGreffier());
					listResult = parametrageManager.getListChefGreffier(
							chefGreffier, null);
					if (listResult != null && !listResult.isEmpty()
							&& listResult.size() == 1) {
						valide = ((ChefGreffier) listResult.get(0))
								.getLibelle().equalsIgnoreCase(
										reglement.getNomBeneficiaire()
												.toUpperCase());
					}
				} else if (typeBeneficaire.equals(BENEFICIAIRE_BARREAU)) {
					Barreau barreau = new Barreau();
					barreau.setCode(reglement.getCodeBarreau());
					listResult = parametrageManager.getListBarreaux(barreau,
							null);
					if (listResult != null && !listResult.isEmpty()
							&& listResult.size() == 1) {
						valide = ((Barreau) listResult.get(0)).getLibelle()
								.equalsIgnoreCase(
										reglement.getNomBeneficiaire()
												.toUpperCase());
					}
				} else {
					valide = true;
				}
			}
			return valide;
		} catch (Exception e) {
			logger.error(EXP_VALIDATION_REGLEMENT, e);
			throw new FonctionnelleException(EXP_VALIDATION_REGLEMENT, e);
		}
	}

	/**
	 * Verifie que l'état du sinistre est compatible.
	 * 
	 * @param reglement
	 * @return boolean
	 * @throws FonctionnelleException
	 */
	public boolean validerEtatSinistre(Reglement reglement)
			throws FonctionnelleException {
		try {
			boolean valide = false;
			Sinistre sin = reglement.getRefSinistre();
			String codeetat = null;
			if (sin.getRefEtatSinistre() != null
					&& sin.getRefEtatSinistre().getRefEtat() != null
					&& sin.getRefEtatSinistre().getRefEtat().getCode() != null) {
				codeetat = sin.getRefEtatSinistre().getRefEtat().getCode();
				if (!StringUtils.isEmpty(codeetat)
						&& (!codeetat.equals("3") && !codeetat.equals("4") && !codeetat
								.equals("0"))) {
					valide = true;
				}
			}
			return valide;
		} catch (Exception e) {
			logger.error(EXP_VALIDATION_REGLEMENT, e);
			throw new FonctionnelleException(EXP_VALIDATION_REGLEMENT, e);
		}
	}

	/**
	 * Verifie le numero de chéque.
	 * 
	 * @param reglement
	 * @return boolean
	 * @throws FonctionnelleException
	 */
	public boolean validerNumCheque(Reglement reglement)
			throws FonctionnelleException {
		try {
			return reglement.getNumCheque().length() == 8
					|| reglement.getNumCheque().length() == 10;
		} catch (Exception e) {
			logger.error(EXP_VALIDATION_REGLEMENT, e);
			throw new FonctionnelleException(EXP_VALIDATION_REGLEMENT, e);
		}
	}

	/**
	 * Verifie que la date d'établissement est inférieure à la date règlement
	 * 
	 * @param reglement
	 * @return boolean
	 * @throws FonctionnelleException
	 */
	public boolean validerDateEtablissement(Reglement reglement)
			throws FonctionnelleException {
		try {
			return reglement.getDateEtablissement().compareTo(
					reglement.getDateReglement()) <= 0;
		} catch (Exception e) {
			logger.error(EXP_VALIDATION_REGLEMENT, e);
			throw new FonctionnelleException(EXP_VALIDATION_REGLEMENT, e);
		}
	}

	public Reglement modifierReglement(Reglement reglement)
			throws FonctionnelleException {
		try {
			Reglement reglDB = (Reglement) getSession().get(Reglement.class,
					reglement.getId());
			if (reglDB == null) {
				throw new FonctionnelleException(EXP_REGLEMENT_INEXISTANT);
			}
			reglement.setDateModification(getDate());
			if (reglement.getDateEmission() == null) {
				reglement.setDateEmission(getDate());
			}
			if (reglement.getNbRelance() == null) {
				reglement.setNbRelance("0");
			}

			for (Iterator iterator = reglement.getListDetailReglement()
					.iterator(); iterator.hasNext();) {
				DetailReglement detail = (DetailReglement) iterator.next();
				detail.setRefReglement(reglement);
				if (!contient(reglDB.getListDetailReglement(), detail)) {
					dao.createObject(detail);
				}
			}
			if (reglement.getListFraisMedicaux() != null
					&& reglement.getListFraisMedicaux().size() != 0) {
				for (Iterator iterator = reglement.getListFraisMedicaux()
						.iterator(); iterator.hasNext();) {
					FraisMedicaux fraisMedic = (FraisMedicaux) iterator.next();
					fraisMedic.setRefReglement(reglement);
					if (!contient(reglDB.getListFraisMedicaux(), fraisMedic)) {
						dao.createObject(fraisMedic);
					}
				}
			}else {
				reglement.setListFraisMedicaux(new ArrayList<FraisMedicaux>());
			}
			if (reglement.getListFraisFuneraire() != null
					&& reglement.getListFraisFuneraire().size() != 0) {
				for (Iterator iterator = reglement.getListFraisFuneraire()
						.iterator(); iterator.hasNext();) {
					FraisFuneraire fraisFuner = (FraisFuneraire) iterator.next();
					fraisFuner.setRefReglement(reglement);
					if (!contient(reglDB.getListFraisFuneraire(), fraisFuner)) {
						dao.createObject(fraisFuner);
					}
				}
			}else {
				reglement.setListFraisFuneraire(new ArrayList<FraisFuneraire>());
			}
			dao.updateObject(reglement);
			return reglement;
		} catch (PersistenceException e) {
			logger.error(EXP_MODIFICATION_REGLEMENT, e);
			throw new FonctionnelleException(EXP_MODIFICATION_REGLEMENT, e);
		} catch (ParseException e) {
			logger.error(EXP_MODIFICATION_REGLEMENT, e);
			throw new FonctionnelleException(EXP_MODIFICATION_REGLEMENT, e);
		}
	}

	public Reglement modifierDateRappelQuittance(Reglement reglement)
			throws FonctionnelleException {
		try {
			Reglement reglDB = (Reglement) getSession().get(Reglement.class,
					reglement.getId());
			if (reglDB == null) {
				throw new FonctionnelleException(EXP_REGLEMENT_INEXISTANT);
			}
			reglement.setDateModification(getDate());
			dao.updateObject(reglement);
			return reglement;
		} catch (PersistenceException e) {
			logger.error(EXP_MODIFICATION_REGLEMENT, e);
			throw new FonctionnelleException(EXP_MODIFICATION_REGLEMENT, e);
		} catch (ParseException e) {
			logger.error(EXP_MODIFICATION_REGLEMENT, e);
			throw new FonctionnelleException(EXP_MODIFICATION_REGLEMENT, e);
		}
	}

	public boolean validerAuxilaires(Reglement reglement)
			throws FonctionnelleException {
		try {
			boolean valide = false;
			PrestataireVO auxilaire = new PrestataireVO();
			auxilaire.setCode(reglement.getCodeAuxiliaire());
			List listResult = parametrageManager.getListPrestataire(auxilaire,
					null);
			if (listResult != null && !listResult.isEmpty()
					&& listResult.size() == 1) {
				valide = ((Prestataire) listResult.get(0)).getNomRaisonSocial()
						.equalsIgnoreCase(reglement.getNomAuxiliaire());
			}
			auxilaire.setCode(reglement.getCodeMandataire());
			listResult = parametrageManager.getListPrestataire(auxilaire, null);
			if (listResult != null && !listResult.isEmpty()
					&& listResult.size() == 1) {
				valide = valide
						&& ((Prestataire) listResult.get(0))
								.getNomRaisonSocial().equalsIgnoreCase(
										reglement.getNomMandataire());
			} else {
				valide = false;
			}
			return valide;
		} catch (Exception e) {
			logger.error(EXP_VALIDATION_REGLEMENT, e);
			throw new FonctionnelleException(EXP_VALIDATION_REGLEMENT, e);
		}
	}

	public Reglement supprimerReglement(Reglement reglement)
			throws FonctionnelleException {
		NumQuittance numQuittance = new NumQuittance();
		try {
			Reglement reglDB = (Reglement) getSession().get(Reglement.class,
					reglement.getId());
			if (reglDB == null) {
				throw new FonctionnelleException(EXP_ANNULATION_REGLEMENT);
			} else {
				if (reglDB.getNumeroQuittance() == null
						|| reglDB.getNumeroQuittance() == "") {
					throw new FonctionnelleException(EXP_ANNULATION_REGLEMENT);
				} else {
					numQuittance.setNumQuittance(reglDB.getNumeroQuittance());
					dao.createObject(numQuittance);
				}
				reglDB.setCodeUserModificateur(reglement
						.getCodeUserModificateur());
				reglDB.setNomUserModificateur(reglement
						.getNomUserModificateur());
				reglDB.setDateModification(getDate());
				reglDB.setNumeroQuittanceRemplacement(reglDB
						.getNumeroQuittance());
				reglDB.setNumeroQuittance(null);
				reglDB.setRefEtatReglement(new EtatRgl(ETAT_REGLEMENT_SUPPRIME));
				dao.updateObject(reglDB);

				return reglement;
			}
		} catch (PersistenceException e) {
			logger.error(EXP_ANNULATION_REGLEMENT, e);
			throw new FonctionnelleException(EXP_ANNULATION_REGLEMENT, e);
		} catch (ParseException e) {
			logger.error(EXP_ANNULATION_REGLEMENT, e);
			throw new FonctionnelleException(EXP_ANNULATION_REGLEMENT, e);
		}

	}

	public Reglement supprimerQuittanceITT(Reglement reglement)
			throws FonctionnelleException {
		try {
			Reglement reglDB = (Reglement) getSession().get(Reglement.class,
					reglement.getId());
			if (reglDB == null) {
				throw new FonctionnelleException(EXP_SUPPRESSION_QUITTANCE_ITT);
			} else {
				reglDB.setCodeUserModificateur(reglement
						.getCodeUserModificateur());
				reglDB.setNomUserModificateur(reglement
						.getNomUserModificateur());
				reglDB.setDateModification(getDate());
				reglDB.setRefEtatReglement(new EtatRgl(ETAT_REGLEMENT_SUPPRIME));
				dao.updateObject(reglDB);
				return reglement;
			}
		} catch (PersistenceException e) {
			logger.error(EXP_SUPPRESSION_QUITTANCE_ITT, e);
			throw new FonctionnelleException(EXP_SUPPRESSION_QUITTANCE_ITT, e);
		} catch (ParseException e) {
			logger.error(EXP_SUPPRESSION_QUITTANCE_ITT, e);
			throw new FonctionnelleException(EXP_SUPPRESSION_QUITTANCE_ITT, e);
		}

	}

	public Reglement annulerReglement(Reglement reglement)
			throws FonctionnelleException {

		IAppelService appelService = new AppelServiceManager();

		Reglement regUpdate = null;
		try {
			Reglement reglDB = (Reglement) getSession().get(Reglement.class,
					reglement.getId());

			if (reglDB == null) {
				throw new FonctionnelleException(EXP_ANNULATION_REGLEMENT);
			}

			// Dans le cas d'un rejet, il faut mettre à jour la quittance via un
			// mvt
			// d'annulation
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			String date = dateFormat.format(new Date());
			MouvementQuittanceVO mouvementQuittanceVO = new MouvementQuittanceVO();
			mouvementQuittanceVO.setNumQuittance(reglDB.getNumeroQuittance());
			mouvementQuittanceVO.setMotifEtat("Annulation");
			mouvementQuittanceVO.setDatEtat(date);

			// Appel de service d'annulation de quittance.
			appelService.setConvert(false);

			appelService.appelService(ServicesExternes.ANNULER_QUITTANCE,
					mouvementQuittanceVO, IConstantes.PROFIL_ANNULATION);

			reglDB.setCodeUserModificateur(reglement.getCodeUserModificateur());
			reglDB.setNomUserModificateur(reglement.getNomUserModificateur());
			reglDB.setDateModification(getDate());
			reglDB.setDateEtat(getDate());
			reglDB.setRefEtatReglement(new EtatRgl(ETAT_REGLEMENT_QTC_ANNULEE));
			updateCumulRegAnnulation(reglDB);
			regUpdate = (Reglement) dao.updateObject(reglDB);

		} catch (PersistenceException e) {
			logger.error(EXP_ANNULATION_REGLEMENT, e);
			throw new FonctionnelleException(EXP_ANNULATION_REGLEMENT, e);
		} catch (ParseException e) {
			logger.error(EXP_ANNULATION_REGLEMENT, e);
			throw new FonctionnelleException(EXP_ANNULATION_REGLEMENT, e);
		} catch (ExceptionMetier e) {
			logger.error("problème technique", e);
		}

		return regUpdate;

	}

	public void updateCumulRegAnnulation(Reglement rgl)
			throws FonctionnelleException {

		Sinistre sindb = rgl.getRefSinistre();

		if (rgl.getRefEtatReglement() != null
				&& !rgl.getRefTypeReglement().getCode()
						.equals(IConstantes.TYPE_REGLEMENT_RECUPERATION)) {
			sindb.setCumulReglementAnne(new BigDecimal(sindb
					.getCumulReglementAnne() - rgl.getMontant()).setScale(2,
					BigDecimal.ROUND_HALF_EVEN).doubleValue());
		} else if (rgl.getRefTypeReglement().getCode()
				.equals(IConstantes.TYPE_REGLEMENT_RECUPERATION)) {
			sindb.setCumulReglementAnne(new BigDecimal(sindb
					.getCumulReglementAnne() + rgl.getMontant()).setScale(2,
					BigDecimal.ROUND_HALF_EVEN).doubleValue());

		}
	}

	/**
	 * Mettre a jour les quittance emise
	 * 
	 * @param listQuittance
	 * @return
	 * @throws PersistenceException
	 * @throws FonctionnelleException
	 */
	public List getListQuittance(List<Reglement> listQuittance)
			throws FonctionnelleException {
		List listeQuittance = new ArrayList();

		if (listQuittance != null && !listQuittance.isEmpty()) {
			for (Reglement reglement : listQuittance) {
				if (reglement.getRefEtatReglement() != null
						&& reglement
								.getRefEtatReglement()
								.getCode()
								.equals(IConstantes.ETAT_REGLEMENT_EN_INSTANCE_DE_REGLEMENT)) {
					// appel service recherche quittance
					try {
						List listQtc = rechercheQuittanceService(reglement);
						if (listQtc != null && !listQtc.isEmpty()) {
							QuittanceVO qtc = (QuittanceVO) listQtc.get(0);

							if (!qtc.getEtatQuittance().equals(
									reglement.getRefEtatReglement().getCode())) {
								reglement.setDateModification(dateFormat
										.parse(qtc.getDatEtat()));
								EtatRgl etatReg = new EtatRgl();
								etatReg.setCode(qtc.getEtatQuittance());
								reglement.setDateEtat(dateFormat.parse(qtc
										.getDatEtat()));
								reglement.setRefEtatReglement(etatReg);
								reglement.setCodeUserModificateur(qtc
										.getCodUtilisateur());
								dao.updateObject(reglement);
							}
						}
					} catch (ParseException e) {
						logger.error(EXP_PARSE_DATE, e);
						throw new FonctionnelleException(EXP_PARSE_DATE, e);
					} catch (PersistenceException e) {
						logger.error(EXP_UPDATE_REGLEMENT, e);
						throw new FonctionnelleException(EXP_UPDATE_REGLEMENT,
								e);
					} catch (ExceptionMetier e) {
						logger.error(EXP_MAJ_REGLEMENT, e);
					}
				}
				listeQuittance.add(reglement);
			}
		}
		return listeQuittance;
	}

	/**
	 * Appel service pour recherche quittance
	 * 
	 * @param reglement
	 * @return
	 * @throws ExceptionMetier
	 */
	private List rechercheQuittanceService(Reglement reglement)
			throws ExceptionMetier {
		QuittanceVO quittanceVO = new QuittanceVO();

		quittanceVO.setNumQuittance(reglement.getNumeroQuittance());
		servicesExternes.setConvert(false);
		return (List) servicesExternes.appelService(
				ServicesExternes.CHERCHER_QUITTANCE, quittanceVO, "1");
	}

	public String getCodeSasSup(Reglement reglement) throws ExceptionMetier {
//		String codeSas = reglement.getCodeUserModificateur();
//		List<OrdDelegation> listDelegation = (List) ((List) servicesExternes
//				.appelService(ServicesExternes.RECUPERER_SEUIL, reglement,
//						codeSas)).get(0);
//
//		if (Fonctions.isEmpty(listDelegation)) {
//			return null;
//		}
//
//		return listDelegation.get(0).getOrdUser().getCodesas();
		
		String codeSas = reglement.getCodeUserModificateur();
		List<OrdDelegationVO> listDelegation =   ((List<OrdDelegationVO>) servicesExternes
				.appelService(ServicesExternes.RECUPERER_SEUIL, reglement,
						codeSas));

		if (Fonctions.isEmpty(listDelegation)) {
			return null;
		}

		return listDelegation.get(0).getOrdUser().getCodesas();
	}

		public Boolean isSuperieur(Reglement reglement) throws ExceptionMetier {
	
//			String codeSas = reglement.getCodeUserModificateur();
//			List<OrdDelegation> listDelegation = (List) ((List) servicesExternes
//					.appelService(ServicesExternes.RECUPERER_SEUIL_SUB, reglement,
//							codeSas)).get(0);
//			if (listDelegation == null || listDelegation.isEmpty()) {
//				return false;
//			} else {
//				// si un seul utilisateur n'a pas le pouvoir 0, alors l'onglet
//				// quittance subordonné s'affiche
//				for (OrdDelegation ordDelegation : listDelegation) {
//					if (!ordDelegation.getSeuiloperation().equals(
//							BigDecimal.valueOf(0))) {
//						return true;
//					}
//				}
//			}
//			return false;
			String codeSas = reglement.getCodeUserModificateur();
			List<OrdDelegationVO> lst =(List) servicesExternes
					.appelService(ServicesExternes.RECUPERER_SEUIL_SUB, reglement,
							codeSas);
			if (lst == null || lst.isEmpty()) {
				return false;
			} else {
				// si un seul utilisateur n'a pas le pouvoir 0, alors l'onglet
				// quittance subordonné s'affiche
				for (OrdDelegationVO ordDelegation : lst) {
					if (!ordDelegation.getSeuiloperation().equals(
							BigDecimal.valueOf(0))) {
						return true;
					}
				}
			}
			return false;
		}

	public Reglement creerRecuperation(Reglement reglement)
			throws FonctionnelleException {
		try {
			Sinistre sinDB = sinistreManager.getSinistreById(reglement
					.getRefSinistre().getId());
			if (sinDB == null) {
				throw new FonctionnelleException(EXP_SINISTRE_INEXISTANT);
			}
			reglement.setDateCreation(getDate());
			if (reglement.getDateEmission() == null) {
				reglement.setDateEmission(getDate());
			}
			if (reglement.getDateReglement() == null) {
				reglement.setDateReglement(getDate());
			}
			if (reglement.getDateCreation() == null) {
				reglement.setDateCreation(getDate());
			}
			reglement.setNumeroQuittance(genererNumeroQuittance());
			if (reglement.getListDetailReglement() != null
					&& !reglement.getListDetailReglement().isEmpty()) {
				for (int i = 0; i < reglement.getListDetailReglement().size(); i++) {
					DetailReglement dtlRgl = reglement.getListDetailReglement()
							.get(i);
					dtlRgl.setRefReglement(reglement);
				}
			}
			dao.createObject(reglement);
			return reglement;
		} catch (PersistenceException e) {
			logger.error(EXP_CREATION_REGLEMENT, e);
			throw new FonctionnelleException(EXP_CREATION_REGLEMENT, e);
		} catch (ParseException e) {
			logger.error(EXP_CREATION_REGLEMENT, e);
			throw new FonctionnelleException(EXP_CREATION_REGLEMENT, e);
		}
	}

	public List rechercheQuittanceExAAA(String numeroSinistre, PagerVO pagerVO)
			throws Exception {
		// récupérer le sinistre

		try {
			StringBuffer hql = new StringBuffer("from QuittanceExAAA");

			hql.append(" where 1=1 ");
			hql.append(" and numeroSinistre like'%")
					.append(numeroSinistre.replaceAll(" ", "")).append("%' ");
			Query query = getSession().createQuery(hql.toString());

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

		} catch (Exception e) {
			throw e;
		}
	}

	public void addHistoriqueEtat(Object... objects) throws ExceptionMetier,
			ParseException {
		Reglement rgl = null;
		Session session = null;
		String motifReglement = null;
		if (objects == null) {
			return;
		}

		try {
			if (objects.length == 3) {
				rgl = (Reglement) objects[0];
				motifReglement = (String) objects[1];
				session = (Session) objects[2];

			} else {
				rgl = (Reglement) objects[0];
				motifReglement = (String) objects[1];
				session = getSession();
			}

			if (rgl == null) {
				throw new ExceptionMetier("le réglement est non renseigné");
			}
			EtatReglement etatRgl = new EtatReglement();
			etatRgl.setDateEtat(getDate());
			etatRgl.setMotifEtat(motifReglement);
			etatRgl.setRefEtat(rgl.getRefEtatReglement());
			etatRgl.setUtilisateurCreateur(rgl.getNomUserCreateur());
			etatRgl.setReference(rgl.getReference());
			etatRgl.setModeReglement(rgl.getModeReglement());
			if (rgl.getNomUserModificateur() != null) {
				etatRgl.setUtilisateurCreateur(rgl.getNomUserModificateur());
			}
			etatRgl.setRefReglement(rgl);

			session.save(etatRgl);
		} catch (Exception e) {
			throw new ExceptionMetier(
					"Une erreur est survenue lors de la mise a jour de l'etat réglement",
					e);

		}

	}

	public List<EtatReglement> getListEtatReglement(ReglementVO rgl)
			throws FonctionnelleException, HibernateException,
			PersistenceException {
		if (rgl.getId() == null) {
			throw new FonctionnelleException(
					"Impossible de récuopérer le reglement");
		}
		StringBuffer hql = new StringBuffer(" from EtatReglement");

		hql.append(" where refReglement.id='").append(rgl.getId()).append("' ");

		hql.append("order by id desc");
		Query query = getSession().createQuery(hql.toString());

		return query.list();

	}

	private Query getQuery(String hql, Map<String, Object> values)
			throws FonctionnelleException {

		// Query
		Query querySql = null;
		try {
			querySql = getSession().createQuery(hql);
			Set<Entry<String, Object>> params = values.entrySet();
			for (Entry<String, Object> curEntry : params) {
				if (curEntry.getKey().startsWith("list")) {
					querySql.setParameterList(curEntry.getKey(),
							(List) curEntry.getValue());

				} else {
					querySql.setParameter(curEntry.getKey(),
							curEntry.getValue());
				}
			}
		} catch (Exception e) {
			logger.error("Exception :", e);
			throw new FonctionnelleException(e.getMessage());
		}

		return querySql;
	}

	public LettreReglement ajouterLettreReglement(Reglement reglement,
			Boolean bool) throws FonctionnelleException {
		try {

			LettreReglement lettreReglement = getLettreByIdRgl(reglement
					.getId());
			if (bool == false && lettreReglement.getId() != null) {
				return null;
			}

			String codePrestation = "";
			for (DetailReglement dreg : reglement.getListDetailReglement()) {
				
					codePrestation = codePrestation +dreg.getCodePrestation()+ "/";
			}
			lettreReglement.setTypeLettre(reglement.getTypeLettre());
			lettreReglement.setNbrReedition(bool == false ? lettreReglement
					.getNbrReedition() : lettreReglement.getNbrReedition() + 1);
			lettreReglement.setRefReglement(reglement);
			lettreReglement.setEditer(bool);
			lettreReglement.setRubrique(codePrestation);
			lettreReglement.setTypeBeneficiaire(reglement
					.getTypeBeneficiaireLettre());
			lettreReglement.setNomBeneficiaire(reglement
					.getNomBeneficiaireLettre());
			lettreReglement.setUserCreation(reglement.getNomUserModificateur());
			lettreReglement.setDateCreation(getDate());
			lettreReglement.setDateReedition(getDate());
			getSession().saveOrUpdate(lettreReglement);
			return lettreReglement;
		} catch (PersistenceException e) {
			logger.error(EXP_CREATION_LETTRE_REGLEMENT, e);
			throw new FonctionnelleException(EXP_CREATION_LETTRE_REGLEMENT, e);
		} catch (ParseException e) {
			logger.error(EXP_CREATION_LETTRE_REGLEMENT, e);
			throw new FonctionnelleException(EXP_CREATION_LETTRE_REGLEMENT, e);
		}
	}

	private LettreReglement getLettreByIdRgl(Long id)
			throws HibernateException, PersistenceException {
		StringBuffer hql = new StringBuffer(
				" from LettreReglement where refReglement.id ='").append(id)
				.append("'");

		Query query = getSession().createQuery(hql.toString());
		LettreReglement lettre = (LettreReglement) query.uniqueResult();
		if (lettre == null) {
			return new LettreReglement();
		}

		return lettre;
	}

	public void validerPreQuittance(Reglement reglement)
			throws FonctionnelleException {
		try {
			Reglement reglDB = (Reglement) getSession().get(Reglement.class,
					reglement.getId());
			if (reglDB == null) {
				throw new FonctionnelleException(EXP_VALIDATION_PRE_QUITTANCE);
			} else {
				reglDB.setCodeUserModificateur(reglement
						.getCodeUserModificateur());
				reglDB.setNomUserModificateur(reglement
						.getNomUserModificateur());
				reglDB.setDateModification(getDate());
				reglDB.setRefEtatReglement(new EtatRgl(
						ETAT_PRE_QUITTANCE_VALIDEE));
				dao.updateObject(reglDB);

			}
		} catch (PersistenceException e) {
			logger.error(EXP_ANNULATION_REGLEMENT, e);
			throw new FonctionnelleException(EXP_VALIDATION_PRE_QUITTANCE, e);
		} catch (ParseException e) {
			logger.error(EXP_ANNULATION_REGLEMENT, e);
			throw new FonctionnelleException(EXP_VALIDATION_PRE_QUITTANCE, e);
		}
	}

	public void validateDateDebutPrestation(Reglement reglement)
			throws FonctionnelleException {
		String numeroSinistre = reglement.getRefSinistre().getNumeroSinistre();
		String codePrestation = reglement.getListDetailReglement().get(0)
				.getCodePrestation();
		Date dateDebut = reglement.getListDetailReglement().get(0)
				.getDateDebutPrestation();
		Date dateFin = reglement.getListDetailReglement().get(0)
				.getDateFinPrestation();
		Long idDetailReglementCur = reglement.getListDetailReglement().get(0)
				.getId();

		List<DetailReglement> detailReglements = listReglementBySinAndCodePrestation(
				numeroSinistre, codePrestation);
		for (DetailReglement dRgl : detailReglements) {
			if (dRgl.getDateFinPrestation() != null
					&& dRgl.getDateDebutPrestation() != null
					&& (idDetailReglementCur == null || dRgl.getId()
							.longValue() != idDetailReglementCur.longValue())) {
				if (!(((dRgl.getDateFinPrestation()).compareTo(dateDebut) < 0 && (dRgl
						.getDateFinPrestation()).compareTo(dateFin) < 0) || ((dRgl
						.getDateDebutPrestation()).compareTo(dateDebut) > 0 && (dRgl
						.getDateDebutPrestation()).compareTo(dateFin) > 0))) {
					throw new FonctionnelleException(EXP_DATE_DEBUT_REGLEMENT);
				}
			}
		}
	}

	private List<DetailReglement> listReglementBySinAndCodePrestation(
			String numeroSinistre, String codePrestation)
			throws FonctionnelleException {
		try {

			StringBuffer hql = new StringBuffer(
					" from DetailReglement where refReglement.refSinistre.numeroSinistre ='")
					.append(numeroSinistre).append("' and codePrestation ='")
					.append(codePrestation).append("'");
			Query query = getSession().createQuery(hql.toString());
			return query.list();
		} catch (PersistenceException e) {
			logger.error(EXP_RECHERCHE_REGLEMENT, e);
			throw new FonctionnelleException(EXP_RECHERCHE_REGLEMENT, e);
		}
	}

	private Object[] getListReglementByPrestataion20Query(Reglement reglement,
			Map paramsDate, Boolean editer) throws FonctionnelleException {

		if (reglement == null) {
			throw new FonctionnelleException(EXP_RECHERCHE_REGLEMENT);
		}

		Map<String, Object> values = new HashMap<String, Object>();
		StringBuffer query = new StringBuffer(
				"from Reglement reglement LEFT JOIN reglement.listDetailReglement d ");
		query.append(" where 1=:un ");
		values.put("un", 1);
		// id
		if (reglement.getId() != null) {
			query.append(" and reglement.id=").append(reglement.getId())
					.append(" ");
		}

		// Sinistre
		if (reglement.getRefSinistre() != null) {
			// idSinistre
			if (reglement.getRefSinistre().getId() != 0) {
				query.append(" and reglement.refSinistre=:refSin");
				values.put("refSin", reglement.getRefSinistre());

			} else {
				// numSinistre
				if (reglement.getRefSinistre().getNumeroSinistre() != null) {
					query.append(
							" and reglement.refSinistre.numeroSinistre like'%")
							.append(reglement.getRefSinistre()
									.getNumeroSinistre().replaceAll(" ", ""))
							.append("%' ");
				}

				// numeroGrave
				if (!StringUtils.isEmpty(reglement.getRefSinistre()
						.getNumeroGrave())) {
					query.append(" and reglement.refSinistre.numeroGrave='")
							.append(reglement.getRefSinistre().getNumeroGrave())
							.append("' ");
				}
			}

		}
		// Code intermediaire
		if (reglement.getCodeIntermediaire() != null) {
			query.append(" and reglement.codeIntermediaire='")
					.append(reglement.getCodeIntermediaire()).append("' ");
		}

		// Type intermediaire
		if (reglement.getTypeIntermediaire() != null) {
			query.append(" and reglement.typeIntermediaire='")
					.append(reglement.getTypeIntermediaire()).append("' ");
		}

		// typeQuittance
		if (reglement.getRefTypeQuittance() != null
				&& reglement.getRefTypeQuittance().getCode() != null) {
			query.append(" and reglement.refTypeQuittance=:typeQtc");
			values.put("typeQtc", new TypeQuittance(reglement
					.getRefTypeQuittance().getCode()));
		}
		// typeReglement
		if (reglement.isReglement()) {
			query.append(" and reglement.refTypeReglement<>:typeRgl");
			values.put("typeRgl", new TypeReglement(
					IConstantes.TYPE_REGLEMENT_RECUPERATION));

		} else {
			if (reglement.getRefTypeReglement() != null
					&& reglement.getRefTypeReglement().getCode() != null) {
				query.append(" and reglement.refTypeReglement=:typeRgl");
				values.put("typeRgl", new TypeReglement(reglement
						.getRefTypeReglement().getCode()));
			}
		}
		// numeroQuittance
		if (!StringUtils.isEmpty(reglement.getNumeroQuittance())) {
			query.append(" and reglement.numeroQuittance like '%")
					.append(reglement.getNumeroQuittance().trim()
							.replaceAll(" ", "")).append("%' ");

		}
		// codeIntermediaireRgl
		if (!StringUtils.isEmpty(reglement.getCodeIntermediaireRgl())) {
			query.append(" and reglement.codeIntermediaireRgl like'%")
					.append(reglement.getCodeIntermediaireRgl()).append("%' ");
		}

		// nomBeneficiaire
		if (!StringUtils.isEmpty(reglement.getNomBeneficiaire())) {
			query.append(" and upper(reglement.nomBeneficiaire) like'")
					.append(reglement.getNomBeneficiaire().toUpperCase())
					.append("' ");
		}

		String[] codeEtats = reglement.getCodeEtatRgl();
		if (codeEtats == null && reglement.getRefEtatReglement() != null
				&& reglement.getRefEtatReglement().getCode() != null) {
			codeEtats = new String[] { reglement.getRefEtatReglement()
					.getCode() };
		}

		if (codeEtats != null) {
			query.append(" and reglement.refEtatReglement in (:listEtatRgl)");
			List<EtatRgl> listEtatRgl = new ArrayList<EtatRgl>();
			for (String curCodeEtat : codeEtats) {
				listEtatRgl.add(new EtatRgl(curCodeEtat));
			}
			values.put("listEtatRgl", listEtatRgl);
		}
		// pour passage en recette le 17/07/2017
		query.append(" and d.refPrestation in ('20','24') and d.contentieux = '0' ");
		// query.append(" and d.refPrestation in ('20')");
		if (editer) {
			query.append(" and reglement.id not in (select l.refReglement.id from LettreReglement l)");
		}

		query.append(" order by reglement.numeroQuittance desc");

		// Return
		Object[] objects = new Object[2];
		objects[0] = query.toString();
		objects[1] = values;
		return objects;
	}

	public List getListQuittance(Reglement reglement, Map map, PagerVO pagerVO,
			Boolean editer) throws FonctionnelleException {
		try {
			Object[] objects = this.getListReglementByPrestataion20Query(
					reglement, map, editer);
			Query query = getQuery((String) objects[0],
					(Map<String, Object>) objects[1]);

			if (pagerVO != null) {
				Integer numpage = 0;
				if (Integer.valueOf(pagerVO.getNumPage()).intValue() > 0) {
					numpage = Integer.valueOf(pagerVO.getNumPage()) - 1;
				}
				return this.getPartCollectionByCondition(query, numpage,
						Integer.valueOf(pagerVO.getPageSize()));
			} else {
				query.setMaxResults(Integer.valueOf(IParam.MAX_SINISTRE));
				return query.list();
			}
		} catch (PersistenceException e) {
			logger.error(EXP_RECHERCHE_REGLEMENT, e);
			throw new FonctionnelleException(EXP_RECHERCHE_REGLEMENT, e);
		}
	}

	public Reglement getReglementById(String idReglement)
			throws FonctionnelleException {
		try {
			if (idReglement == null) {
				return null;
			}
			String query = " from Reglement rgl where rgl.id='" + idReglement
					+ "'";
			return (Reglement) getSession().createQuery(query).uniqueResult();
		} catch (PersistenceException e) {
			logger.error(EXP_RECHERCHE_REGLEMENT, e);
			throw new FonctionnelleException(EXP_RECHERCHE_REGLEMENT, e);
		}
	}

	public List<Date> getListDateReglementAValider(ReglementVO reglement, PagerVO pagerVO)
			throws HibernateException, PersistenceException, FonctionnelleException {
//		if (reglement == null) {
//			return null;
//		}
//
//		Query query = getSession().createQuery(
//				this.getListDateCreReglementAValiderQuery(reglement, map,
//						seuil, seuilsub));
//		
//		if (pagerVO != null) {
//			Integer numpage = 0;
//			if (Integer.valueOf(pagerVO.getNumPage()).intValue() > 0) {
//				numpage = Integer.valueOf(pagerVO.getNumPage()) - 1;
//			}
//			return this.getPartCollectionByCondition(query, numpage,
//					Integer.valueOf(pagerVO.getPageSize()));
//		} else {
//			query.setMaxResults(Integer.valueOf(IParam.MAX_SINISTRE));
//			return query.list();
//		}
//
//		return query.list();
		
		try {
			Object[] objects = this.getListDateCreReglementAValiderQuery(reglement);
			Query query = getSession().createQuery((String) objects[0]);

			if (pagerVO != null) {
				Integer numpage = 0;
				if (Integer.valueOf(pagerVO.getNumPage()).intValue() > 0) {
					numpage = Integer.valueOf(pagerVO.getNumPage()) - 1;
				}
				return this.getPartCollectionByCondition(query, numpage,
						Integer.valueOf(pagerVO.getPageSize()));
			} else {
				query.setMaxResults(Integer.valueOf("300"));
				return query.list();
			}
		} catch (PersistenceException e) {
			logger.error(EXP_RECHERCHE_REGLEMENT, e);
			throw new FonctionnelleException(EXP_RECHERCHE_REGLEMENT, e);
		}
	}

	private Object[] getListDateCreReglementAValiderQuery(ReglementVO reglement) {

		StringBuffer query = new StringBuffer(
				"select distinct TRUNC( reglement.dateCreation,'DD') from Reglement reglement  ");
		query.append(" where 1=1 ");
		// typeReglement
		if (reglement.getRefTypeReglement() != null
				&& reglement.getRefTypeReglement().getCode() != null) {
			query.append(" and reglement.refTypeReglement.code='")
					.append(reglement.getRefTypeReglement().getCode())
					.append("' ");
		}

		// Etat reglement
		String[] codeEtats = reglement.getCodeEtatRgl();
		if (codeEtats == null && reglement.getRefEtatReglement() != null
				&& reglement.getRefEtatReglement().getCode() != null) {
			codeEtats = new String[] { reglement.getRefEtatReglement()
					.getCode() };
		}

		if (codeEtats != null) {
			query.append(" and reglement.refEtatReglement.code in ('");
			for (String curCodeEtat : codeEtats) {
				query.append(curCodeEtat).append("','");
			}
			query.replace(query.length() - 2, query.length(), ")");
		}
		query.append(" AND reglement.rappel = false");
		query.append(" order by TRUNC( reglement.dateCreation,'DD')  DESC");
//		return query.toString();
		
		Object[] objects = new Object[2];
		objects[0] = query.toString();
//		objects[1] = values;
		return objects;
	}

	public void addHistoriqueDateRappel(Reglement rgl) throws ExceptionMetier {
		try {
			Session session = getSession();
			HistorisationDateRappel histo = new HistorisationDateRappel();
			histo.setDateCreation(new Date());
			histo.setDateRappel(rgl.getDateProchaineEcheance());
			histo.setReglement(rgl);
			histo.setUserCreation(rgl.getNomUserModificateur());
			session.save(histo);
		} catch (Exception e) {
			throw new ExceptionMetier(
					"Une erreur est survenue lors de l'historisation de la date rappel",
					e);

		}
	}

	public void validerQuittanceITT(Reglement reglement)
			throws FonctionnelleException {
		try {
			Reglement reglDB = (Reglement) getSession().get(Reglement.class,
					reglement.getId());
			if (reglDB == null) {
				throw new FonctionnelleException(EXP_REGLEMENT_INEXISTANT);
			} else {
				reglDB.setNumeroQuittance(genererNumeroQuittance());
				reglDB.setCodeUserModificateur(reglement
						.getCodeUserModificateur());
				reglDB.setNomUserModificateur(reglement
						.getNomUserModificateur());
				reglDB.setDateModification(getDate());
				reglDB.setDateProchaineEcheance(reglement
						.getDateProchaineEcheance());
				reglDB.setMontant(reglement.getMontant());
				for (DetailReglement dreg : reglDB.getListDetailReglement()) {
					dreg.setMontant(reglement.getMontant());
					dreg.setDateFinPrestation(reglement
							.getListDetailReglement().get(0)
							.getDateFinPrestation());
				}
				reglDB.setRefEtatReglement(new EtatRgl(
						ETAT_PRE_QUITTANCE_EN_INSTANCE));
				dao.updateObject(reglDB);

			}
		} catch (PersistenceException e) {
			logger.error(EXP_VALIDATION_QUITTANCE_ITT, e);
			throw new FonctionnelleException(EXP_VALIDATION_QUITTANCE_ITT, e);
		} catch (ParseException e) {
			logger.error(EXP_VALIDATION_QUITTANCE_ITT, e);
			throw new FonctionnelleException(EXP_VALIDATION_QUITTANCE_ITT, e);
		}

	}

	public Reglement getReglementEnDouble(Reglement reglement)
			throws FonctionnelleException {
		try {
			if (reglement == null) {
				return null;
			}

			String query = " from Reglement rgl where rgl.codeAuxiliaire='"
					+ reglement.getCodeAuxiliaire() + "'";
			;
			if (reglement.getReferenceAuxiliaire() == null) {
				query = query + " and rgl.referenceAuxiliaire is null";
			} else {
				query = query + " and rgl.referenceAuxiliaire='"
						+ reglement.getReferenceAuxiliaire() + "'";
			}
			query = query
					+ " and rgl.montant="
					+ reglement.getMontant()
					+ " and rgl.refSinistre.id = '"
					+ reglement.getRefSinistre().getId()
					+ "' and rgl.refEtatReglement.code not in (3,4) order by rgl.dateCreation DESC";
			List<Reglement> listResult = getSession().createQuery(query).list();
			if (listResult != null && listResult.size() > 0) {
				return listResult.get(0);
			} else {
				return new Reglement();
			}
		} catch (PersistenceException e) {
			logger.error(EXP_RECHERCHE_REGLEMENT, e);
			throw new FonctionnelleException(EXP_RECHERCHE_REGLEMENT, e);
		}
	}

	public List<Date> getListDateEcheanceReglementAValider(
			ReglementVO reglement, HashMap map, BigDecimal seuil,
			BigDecimal seuilsub, PagerVO pagerVO) throws HibernateException,
			PersistenceException, FonctionnelleException {
		if (reglement == null) {
			return null;
		}

		
		Query query = getSession().createQuery(
				this.getListDateEcheanceReglementAValiderQuery(reglement, map,
						seuil, seuilsub));

		
		return query.list();
	}

	private String getListDateEcheanceReglementAValiderQuery(
			ReglementVO reglement, HashMap map, BigDecimal seuil,
			BigDecimal seuilsub) {

		StringBuffer query = new StringBuffer(
				"select distinct TRUNC( reglement.dateProchaineEcheance,'DD')  from Reglement reglement  ");
		query.append(" where 1=1 ");
		// typeReglement
		if (reglement.getRefTypeReglement() != null
				&& reglement.getRefTypeReglement().getCode() != null) {
			query.append(" and reglement.refTypeReglement.code='")
					.append(reglement.getRefTypeReglement().getCode())
					.append("' ");
		}

		// Etat reglement
		String[] codeEtats = reglement.getCodeEtatRgl();
		if (codeEtats == null && reglement.getRefEtatReglement() != null
				&& reglement.getRefEtatReglement().getCode() != null) {
			codeEtats = new String[] { reglement.getRefEtatReglement()
					.getCode() };
		}

		if (codeEtats != null) {
			query.append(" and reglement.refEtatReglement.code in ('");
			for (String curCodeEtat : codeEtats) {
				query.append(curCodeEtat).append("','");
			}
			query.replace(query.length() - 2, query.length(), ")");
		}
		query.append(" AND reglement.dateProchaineEcheance - 5 <= SYSDATE ");
		query.append(" AND reglement.rappel = true");
		query.append(" order by TRUNC( reglement.dateProchaineEcheance,'DD')  DESC");
		return query.toString();

	}

	public List getListTypeFrais() {
		List<TypeFrais> listTypeFrais = new ArrayList<TypeFrais>();
		try {
			String requete = new String("from TypeFrais");
			Query query = getSession().createQuery(requete);
			listTypeFrais = query.list();
		} catch (HibernateException e) {
			logger.error("erreur", e);
		} catch (PersistenceException e) {
			logger.error("erreur", e);
		}
		return listTypeFrais;
	}
	
	public List getListTypeFuneraire() {
		List<TypeFuneraire> listTypeFuneraire = new ArrayList<TypeFuneraire>();
		try {
			String requete = new String("from TypeFuneraire");
			Query query = getSession().createQuery(requete);
			listTypeFuneraire = query.list();
		} catch (HibernateException e) {
			logger.error("erreur", e);
		} catch (PersistenceException e) {
			logger.error("erreur", e);
		}
		return listTypeFuneraire;
	}

	public Integer getNombreDateCreation(ReglementVO reglement) 
		throws Exception {

			if (reglement != null && reglement.getId() != null) {
				return 1;
			}

			Object[] objects = this.getListDateCreReglementAValiderQuery(reglement);
			Query query = getSession().createQuery((String) objects[0]);
			Integer nombreResultat =  query.list().size();
			return Integer.valueOf(nombreResultat.intValue());
	}
	public List<DestinataireCheque> getListDestinataireCheque() {

		List<DestinataireCheque> listDestinataireCheque = new ArrayList<DestinataireCheque>();
		try {
			String requete = new String("from DestinataireCheque");
			Query query = getSession().createQuery(requete);
			listDestinataireCheque = query.list();
		} catch (HibernateException e) {
			logger.error("erreur", e);
		} catch (PersistenceException e) {
			logger.error("erreur", e);
		}
		return listDestinataireCheque;
	}
	
	public List getListReglementDirect(Reglement reglement, Map map)
			throws FonctionnelleException {
		Object[] objects = this.getListReglementDirectQuery(reglement, map);
		Query query = getQuery((String) objects[0],
				(Map<String, Object>) objects[1]);
		query.setMaxResults(Integer.valueOf(IParam.MAX_SINISTRE));
		return query.list();
		}

	private Object[] getListReglementDirectQuery(Reglement reglement, Map paramsDate)
			throws FonctionnelleException {
		
		
		if (reglement == null) {
			throw new FonctionnelleException(EXP_RECHERCHE_REGLEMENT);
		}
		
		Map<String, Object> values = new HashMap<String, Object>();
		StringBuffer query = new StringBuffer("from Reglement reg join  reg.refEtatReglement e " +
				"join reg.refTypeReglement t " +
				"where e.code ='10' and t.code ='1' and reg.typeDestinataireCheque is not null and (reg.typeDestinataireCheque != 1  and (reg.typeDestinataireCheque != 4 or reg.typeBeneficiaire = 4 )) and typeBeneficiaire is not null ");
	
		if (reglement.getDateEtat() != null) {
			query.append(" and to_char(reg.dateEtat,'dd/MM/yyyy')=:dateEdition");
			values.put("dateEdition",dateFormat.format(reglement.getDateEtat()));
		}
		
	
		query.append(" order by reg.dateEtat desc");
		
		// Return
		Object[] objects = new Object[2];
		objects[0] = query.toString();
		objects[1] = values;
		return objects;
		}
	
public String  getLibelleDestinataireCheque(String code){
		
		try {
			if (Fonctions.isEmpty(code)) {
				return "";
			}else{
				String query = " from DestinataireCheque dest where dest.code='"
					+ code+ "'";
			
				DestinataireCheque d=(DestinataireCheque) getSession().createQuery(query).uniqueResult();
				
				if(d!=null){
					return d.getLibelle();
				}else{
					return "";
				}
			}
			
		} catch (PersistenceException e) {
			logger.error(EXP_RECHERCHE_REGLEMENT, e);
			return "";
		}
		
		
	}
	
public void EditerDateEditionIntermediaire(Reglement reglement)
		throws FonctionnelleException {
	try {
		Reglement reglDB = (Reglement) getSession().get(Reglement.class,
				reglement.getId());
		if (reglDB == null) {
			throw new FonctionnelleException(EXP_REGLEMENT_INEXISTANT);
		}

		for (Iterator iterator = reglement.getListDetailReglement()
				.iterator(); iterator.hasNext();) {
			DetailReglement detail = (DetailReglement) iterator.next();
			detail.setRefReglement(reglement);
			if (!contient(reglDB.getListDetailReglement(), detail)) {
				dao.createObject(detail);
			}
		}
		if (reglement.getListFraisMedicaux() != null
				&& reglement.getListFraisMedicaux().size() != 0) {
			for (Iterator iterator = reglement.getListFraisMedicaux()
					.iterator(); iterator.hasNext();) {
				FraisMedicaux fraisMedic = (FraisMedicaux) iterator.next();
				fraisMedic.setRefReglement(reglement);
				if (!contient(reglDB.getListFraisMedicaux(), fraisMedic)) {
					dao.createObject(fraisMedic);
				}
			}
		}else {
			reglement.setListFraisMedicaux(new ArrayList<FraisMedicaux>());
		}
		if (reglement.getListFraisFuneraire() != null
				&& reglement.getListFraisFuneraire().size() != 0) {
			for (Iterator iterator = reglement.getListFraisFuneraire()
					.iterator(); iterator.hasNext();) {
				FraisFuneraire fraisFuner = (FraisFuneraire) iterator.next();
				fraisFuner.setRefReglement(reglement);
				if (!contient(reglDB.getListFraisFuneraire(), fraisFuner)) {
					dao.createObject(fraisFuner);
				}
			}
		}else {
			reglement.setListFraisFuneraire(new ArrayList<FraisFuneraire>());
		}
		if(reglement.getTypeUtilisateurConnecte() != null || !StringUtils.isEmpty(reglement.getTypeUtilisateurConnecte())) {
			if (!"W".equals(reglement.getTypeUtilisateurConnecte())) {
				reglement.setDateEditionIntermediaire(new Date());
			}
		}
		dao.updateObject(reglement);
	} catch (PersistenceException e) {
		logger.error(EXP_MODIFICATION_REGLEMENT, e);
		throw new FonctionnelleException(EXP_MODIFICATION_REGLEMENT, e);
	}
}

		/*public List<Reglement> getListSortsMAD()
				throws FonctionnelleException, HibernateException,
				PersistenceException {
			StringBuffer hql = new StringBuffer(" from Reglement");
		
			hql.append(" where modeReglement = '6' and refEtatReglement.code = '12' ");
		
			hql.append("order by id desc");
			Query query = getSession().createQuery(hql.toString());
		
			return query.list();
		
		}*/


		public List getlistParamPlafondMAD() throws PersistenceException {
			Session sessionH = (Session) dao.getSession();
			String sql = " from PlafondMAD";
			Query q = sessionH.createQuery(sql);
			return q.list();
		}
		
		public Object[] getQueryListSortsMAD(Reglement reglement, String zoneARisque )
				throws FonctionnelleException, PersistenceException {
			
			//String rejetMAD = getRejetMAD();
			List paramMAD = getlistParamPlafondMAD();
			PlafondMAD plafondMADobject = (PlafondMAD) paramMAD.get(0);
			
				StringBuffer sql = new StringBuffer(" from Reglement c");
				sql.append(" where modeReglement = '6' and refEtatReglement.code = '12' ");
				if("0".equals(zoneARisque))
				sql.append(" and (zoneARisque = '").append(zoneARisque).append("' or zoneARisque is null )");
				if("1".equals(zoneARisque))
					sql.append(" and zoneARisque = '").append(zoneARisque).append("' ");
				sql.append("order by id desc");
				//sql.append(" where modeReglement = '6' and refEtatReglement.code = '12' and nbRelance <='" + plafondMADobject.getRejetMAD() + "' order by id asc");		
				Object[] objects = new Object[2];
				Map<String, Object> values = new HashMap<String, Object>();
				objects[0] = sql.toString();
				objects[1] = values;
				return objects;
				}
				
				public Integer getNombreSortsMAD(Reglement reglement) 
				throws Exception {
					Session sessionH = (Session) dao.getSession();			
					Object[] objects = this.getQueryListSortsMAD(reglement, reglement.getZoneARisque());
					String hql = "Select count (c.id) " + (String) objects[0];
					Query query = sessionH.createQuery(hql);
					Long nombreResultat = (Long) query.uniqueResult();
					return nombreResultat.intValue();
			}
				
				
				public List<Reglement> getListSortsMAD(PagerVO pagerVO, Reglement reglement) throws Exception {
					
					Session sessionH = (Session) dao.getSession();			
					Object[] objects =	this.getQueryListSortsMAD(reglement, reglement.getZoneARisque());
					String hql = "Select c  " + (String) objects[0];
					Query query = sessionH.createQuery(hql);
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

				private boolean contientFraisFuneraire(List<FraisFuneraire> listFraisFuneraire) {

					for (FraisFuneraire curFraisFuneraire : listFraisFuneraire) {
						if (curFraisFuneraire == null
								|| curFraisFuneraire.getRefTypeFuneraire().getCode() == null
								|| curFraisFuneraire.getRefTypeFuneraire() == null) {
							return false;
						}

						if (curFraisFuneraire.getRefTypeFuneraire().getCode()
								.equals("1")) {
							return true;
						}

					}

					return false;
				}

				public boolean checkFraisFuneraire(Reglement reg) {
					// TODO Auto-generated method stub
					return contientFraisFuneraire(reg.getListFraisFuneraire());
				
				}
/*EVOL ZONE A RISQUE*/
				
				public Boolean verifierReglmentZoneARisque(Long idSinistre)
			            throws FonctionnelleException {
			     Boolean decisionRisque = false;
			     try {
			            if (idSinistre == null) {
			                   return null;
			            }
			            StringBuffer hql = new StringBuffer(
			                          " select s from Sinistre s, Evenement e LEFT JOIN e.refVille vi,Victime v LEFT JOIN v.refVille vil ");

			            hql.append(" where 1=1 ");
			            
			            hql.append(" and s.id = ").append(idSinistre);
			            hql.append(" and s.refVictime.id=v.id ");
			            hql.append(" and s.refEvenement.id=e.id ");
			            hql.append(" and  s.id NOT IN ("
			                  + "SELECT s1.id FROM Sinistre    s1,Recours rec,ProcedureJudiciaire rp WHERE rec.refSinistre.id= s1.id AND rp.refRecours.id = rec.id)");


			            hql.append(" and s.numeroGrave is not null and s.refVictime.adresse is not null ");
			         hql.append(" and (vi.code in (164,167,65,202,221,11,10,22,29,298,303,308,97,157,159,99,235,17,27,292,41,302,53,312,93,94,3,163,4,183,205,239,15,25,309,33,92,34)");
			         hql.append(" or vil.code in (164,167,65,202,221,11,10,22,29,298,303,308,97,157,159,99,235,17,27,292,41,302,53,312,93,94,3,163,4,183,205,239,15,25,309,33,92,34))");
			          

			            Query query = getSession().createQuery(hql.toString());
			            Integer nombreResult = query.list().size();
			            
			            if (nombreResult != null && nombreResult > 0) {
			                                decisionRisque = true;
			                   }
			     } catch (Exception e) {
			            logger.error(EXP_RECHERCHE_SINISTRE, e);
			            throw new FonctionnelleException(EXP_RECHERCHE_SINISTRE, e);
			     }
			     return decisionRisque;
			}
				
		public void updateAttenteComplement(Reglement reg) throws FonctionnelleException {
						
						try { 
							Reglement regDB = (Reglement) getSession().get(Reglement.class, reg.getId());
							if(regDB == null) {
								throw new FonctionnelleException(EXP_RECHERCHE_REGLEMENT);
							}else {
								regDB.setIsComplement(true);
								regDB.setCodeUserModificateur(reg.getCodeUserModificateur());
								regDB.setNomUserModificateur(reg.getNomUserModificateur());
								if (reg.getReglementPieceAt() != null) {
									for (int i = 0; i < reg.getReglementPieceAt().size(); i++) {
										ReglementPieceAt reglementPieceAt = reg
												.getReglementPieceAt().get(i);
														
										reglementPieceAt.setReglement(reg);
									}
									regDB.setReglementPieceAt(reg.getReglementPieceAt());
								}
								dao.updateObject(regDB);
							}
							
								
							
						
							} catch (PersistenceException e) {
								logger.error(EXP_RECHERCHE_REGLEMENT, e);
								throw new FonctionnelleException(EXP_RECHERCHE_REGLEMENT, e);
						}
					}
				  public void updateNePasRegler(Reglement reg) throws FonctionnelleException{
				    	try {
				    		Reglement regDB = (Reglement) getSession().get(Reglement.class,  reg.getId());
				    		
				    		if(regDB==null) {
				    			throw new FonctionnelleException(EXP_RECHERCHE_REGLEMENT);
				    		}else {
				    			regDB.setRegler(false);
				    			dao.updateObject(regDB);
				    		}
				    	}catch (PersistenceException e) {
							logger.error(EXP_VALIDATION_PRE_QUITTANCE, e);
							throw new FonctionnelleException(EXP_VALIDATION_PRE_QUITTANCE, e);
					}
				    }
	 private Object[]  getListPreQuittanceARisqueQuery (Reglement reglement,
				            Map paramsDate, Boolean editer) throws FonctionnelleException {

				     if (reglement == null) {
				            throw new FonctionnelleException(EXP_RECHERCHE_REGLEMENT);
				     }

				     Map<String, Object> values = new HashMap<String, Object>();
				     StringBuffer query = new StringBuffer(
				    	        " from Reglement reg , Sinistre s, Evenement e LEFT JOIN e.refVille vi,"
						                   + "Victime v LEFT JOIN v.refVille vil, DetailReglement d ");
			                   
			                   //+ " LEFT JOIN Victime v ON s.refVictime.idVictime=v.id ");
			                   //+ " LEFT JOIN s.refVictime.refVille ville ON ville.codeVille=v.refVille.codeVille ");
			     query.append(" where 1=1 ");
			     // id
			     query.append("and s.id=reg.refSinistre.id ");
			     query.append("and s.refVictime.id=v.id ");
			     query.append("and s.refEvenement.id=e.id ");
			     query.append(" and d.refReglement=reg.id ");
			     query.append(" and reg.zoneARisque = '1' and reg.isComplement ='0' and reg.regler IS NULL");
			     
			     query.append(" and  s.id NOT IN ("
		                   + "SELECT s1.id FROM Sinistre    s1,Recours rec,ProcedureJudiciaire rp WHERE rec.refSinistre.id= s1.id AND rp.refRecours.id = rec.id)");

			     
			     //Type Beneficiaire
			     if (reglement.getTypeBeneficiaire() != null) {
			            query.append(" and reg.typeBeneficiaire='")
			                          .append(reglement.getTypeBeneficiaire()).append("' ");
			     }
			     
			     //Is Complement
			     if (reglement.getIsComplement() != false) {
			            query.append(" and reg.isComplement=")
			                          .append(reglement.getIsComplement());
			     }

			     String[] codeEtats = reglement.getCodeEtatRgl();
			     if (codeEtats == null && reglement.getRefEtatReglement() != null
			                   && reglement.getRefEtatReglement().getCode() != null) {
			            codeEtats = new String[] { reglement.getRefEtatReglement()
			                          .getCode() };
			     }

			     if (codeEtats != null) {
			            query.append(" and reg.refEtatReglement in (:listEtatRgl)");
			            List<EtatRgl> listEtatRgl = new ArrayList<EtatRgl>();
			            for (String curCodeEtat : codeEtats) {
			                   listEtatRgl.add(new EtatRgl(curCodeEtat));
			            }
			            values.put("listEtatRgl", listEtatRgl);
			     }
			     // pour passage en recette le 17/07/2017
			     // pour passage en recette le 17/07/2017
			     query.append(" and reg.dateEtat >= TO_DATE('01/01/2023','dd/mm/yyyy') and TRUNC(TO_DATE(sysdate,'dd/mm/yyyy')) - TO_DATE(reg.dateEtat,'dd/mm/yyyy') >=60 ");
			     query.append(" and d.refPrestation in ('20') and  s.numeroGrave is not null and s.refVictime.adresse is not null ");
			     query.append(" and (vi.code in (164,167,65,202,221,11,10,22,29,298,303,308,97,157,159,99,235,17,27,292,41,302,53,312,93,94,3,163,4,183,205,239,15,25,309,33,92,34)");
			     query.append(" or vil.code in (164,167,65,202,221,11,10,22,29,298,303,308,97,157,159,99,235,17,27,292,41,302,53,312,93,94,3,163,4,183,205,239,15,25,309,33,92,34))");
			     
			     // query.append(" and d.refPrestation in ('20')");
			     

			     query.append(" order by reg.dateEtat desc");

			     // Return
			     Object[] objects = new Object[2];
			     objects[0] = query.toString();
			     objects[1] = values;
			     return objects;
				}

				    public Object[]  getListPreQuittanceARisqueAttenteComplementQuery (Reglement reglement, 
				            Map paramsDate, Boolean editer) throws FonctionnelleException {

				     if (reglement == null) {
				            throw new FonctionnelleException(EXP_RECHERCHE_REGLEMENT);
				     }

				     Map<String, Object> values = new HashMap<String, Object>();
				     StringBuffer query = new StringBuffer(
				    		 " from Reglement reg , Sinistre s, Evenement e LEFT JOIN e.refVille vi,"
					                   + "Victime v LEFT JOIN v.refVille vil, DetailReglement d ");
				                   
				                   //+ " LEFT JOIN Victime v ON s.refVictime.idVictime=v.id ");
				                   //+ " LEFT JOIN s.refVictime.refVille ville ON ville.codeVille=v.refVille.codeVille ");
				     query.append(" where 1=1 ");
				     // id
				     query.append("and s.id=reg.refSinistre.id ");
				     query.append("and s.refVictime.id=v.id ");
				     query.append("and s.refEvenement.id=e.id ");
				     query.append(" and d.refReglement=reg.id ");
				     query.append(" and reg.zoneARisque = '1' and reg.isComplement = '1' and reg.regler IS NULL");
				    				     //Type Beneficiaire
				     if (reglement.getTypeBeneficiaire() != null) {
				            query.append(" and reg.typeBeneficiaire='")
				                          .append(reglement.getTypeBeneficiaire()).append("' ");
				     }
				     
				     //Is Complement
				     if (reglement.getIsComplement() != false) {
				            query.append(" and reg.isComplement=")
				                          .append(reglement.getIsComplement());
				     }

				     String[] codeEtats = reglement.getCodeEtatRgl();
				     if (codeEtats == null && reglement.getRefEtatReglement() != null
				                   && reglement.getRefEtatReglement().getCode() != null) {
				            codeEtats = new String[] { reglement.getRefEtatReglement()
				                          .getCode() };
				     }

				     if (codeEtats != null) {
				            query.append(" and reg.refEtatReglement in (:listEtatRgl)");
				            List<EtatRgl> listEtatRgl = new ArrayList<EtatRgl>();
				            for (String curCodeEtat : codeEtats) {
				                   listEtatRgl.add(new EtatRgl(curCodeEtat));
				            }
				            values.put("listEtatRgl", listEtatRgl);
				     }
				     // pour passage en recette le 17/07/2017
				     query.append(" and reg.dateEtat >= TO_DATE('01/01/2023','dd/mm/yyyy') and TRUNC(TO_DATE(sysdate,'dd/mm/yyyy')) - TO_DATE(reg.dateEtat,'dd/mm/yyyy') >=60 ");
				     query.append(" and d.refPrestation in ('20') and  s.numeroGrave is not null and s.refVictime.adresse is not null ");
				     query.append(" and (vi.code in (164,167,65,202,221,11,10,22,29,298,303,308,97,157,159,99,235,17,27,292,41,302,53,312,93,94,3,163,4,183,205,239,15,25,309,33,92,34)");
				     query.append(" or vil.code in (164,167,65,202,221,11,10,22,29,298,303,308,97,157,159,99,235,17,27,292,41,302,53,312,93,94,3,163,4,183,205,239,15,25,309,33,92,34))");
				     
				     // query.append(" and d.refPrestation in ('20')");
				     

				     query.append(" order by reg.dateEtat desc");

				     // Return
				     Object[] objects = new Object[2];
				     objects[0] = query.toString();
				     objects[1] = values;
				     return objects;
				}
				    public void validateEtatReglement(Reglement reg) throws FonctionnelleException, ParseException {
						
						try {
							Reglement regDB = (Reglement) getSession().get(Reglement.class, reg.getId());
							if(regDB == null) {
								throw new FonctionnelleException(EXP_RECHERCHE_REGLEMENT);
							}else {
								//regDB.setRefEtatReglement(new EtatRgl(ETAT_PRE_QUITTANCE_VALIDEE));
								regDB.setDateModification(getDate());
								regDB.setCodeUserModificateur(reg.getCodeUserModificateur());
								regDB.setNomUserModificateur(reg.getNomUserModificateur());
								regDB.setDateEtat(getDate());
								regDB.setRegler(true);
								dao.updateObject(regDB);
							}
							
						 
							} catch (PersistenceException e) {
								logger.error(EXP_VALIDATION_PRE_QUITTANCE, e);
								throw new FonctionnelleException(EXP_VALIDATION_PRE_QUITTANCE, e);
						}
					}
					public Integer getNombrePreQuittanceARisqueAttenteComplement(Reglement reglement, HashMap map, Boolean editer) throws Exception {

						Object[] objects = this.getListPreQuittanceARisqueAttenteComplementQuery(reglement, map, editer);
						String hql = "Select count (reg.id) " + (String) objects[0];
						Query query = getQuery(hql, (Map<String, Object>) objects[1]);
						Long nombreResultat = (Long) query.uniqueResult();
						return nombreResultat.intValue();
					}
					
					public List getListPreQuittanceARisqueAttenteComplement(Reglement reglement, Map map, PagerVO pagerVO, Boolean editer)
							throws FonctionnelleException {
						try {
							Object[] objects = this.getListPreQuittanceARisqueAttenteComplementQuery(reglement, map, editer);
							Query query = getQuery((String) objects[0], (Map<String, Object>) objects[1]);

							if (pagerVO != null) {
								Integer numpage = 0;
								if (Integer.valueOf(pagerVO.getNumPage()).intValue() > 0) {
									numpage = Integer.valueOf(pagerVO.getNumPage()) - 1;
								}
								return this.getPartCollectionByCondition(query, numpage, Integer.valueOf(pagerVO.getPageSize()));
							} else {
								query.setMaxResults(Integer.valueOf(IParam.MAX_SINISTRE));
								return query.list();
							}
						} catch (PersistenceException e) {
							logger.error(EXP_RECHERCHE_REGLEMENT, e);
							throw new FonctionnelleException(EXP_RECHERCHE_REGLEMENT, e);
						}
					}
					public List getListPreQuittanceARisque(Reglement reglement, Map map, PagerVO pagerVO, Boolean editer)
							throws FonctionnelleException {
						try {
							Object[] objects = this.getListPreQuittanceARisqueQuery(reglement, map, editer);
							Query query = getQuery((String) objects[0], (Map<String, Object>) objects[1]);

							if (pagerVO != null) {
								Integer numpage = 0;
								if (Integer.valueOf(pagerVO.getNumPage()).intValue() > 0) {
									numpage = Integer.valueOf(pagerVO.getNumPage()) - 1;
								}
								return this.getPartCollectionByCondition(query, numpage, Integer.valueOf(pagerVO.getPageSize()));
							} else {
								query.setMaxResults(Integer.valueOf(IParam.MAX_SINISTRE));
								return query.list();
							}
						} catch (PersistenceException e) {
							logger.error(EXP_RECHERCHE_REGLEMENT, e);
							throw new FonctionnelleException(EXP_RECHERCHE_REGLEMENT, e);
						}
					}

					public Integer getNombrePreQuittanceARisque(Reglement reglement, HashMap map, Boolean editer) throws Exception {

						Object[] objects = this.getListPreQuittanceARisqueQuery(reglement, map, editer);
						String hql = "Select count (reg.id) " + (String) objects[0];
						Query query = getQuery(hql, (Map<String, Object>) objects[1]);
						Long nombreResultat = (Long) query.uniqueResult();
						return nombreResultat.intValue();
					}
					
					
					public String getRasAuxilliaireByCode(String codeAuxilliaire)
							throws FonctionnelleException {
						try {
							String ras ="0";

							Prestataire prest = parametrageManager.getPrestataireQueryByCode(codeAuxilliaire);
							if (prest != null ) {
								 ras = prest.getRas();
							}
							
							return ras;
						} catch (Exception e) {
							logger.error(EXP_VALIDATION_REGLEMENT, e);
							throw new FonctionnelleException(EXP_VALIDATION_REGLEMENT, e);
						}
					}
}
