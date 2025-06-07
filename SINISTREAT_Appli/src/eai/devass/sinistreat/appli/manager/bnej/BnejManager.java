package eai.devass.sinistreat.appli.manager.bnej;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import ma.co.omnidata.framework.services.core.ServicesFactory;
import ma.co.omnidata.framework.services.dao.IPersistenceService;
import ma.co.omnidata.framework.services.dao.PersistenceException;
import ma.co.omnidata.framework.services.entites.EntiteException;
import ma.co.omnidata.framework.services.entites.EntiteManagerAbst;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.impl.SessionImpl;

import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.modele.metier.bnej.DossierBnej;
import eai.devass.sinistreat.appli.modele.metier.bnej.DossierBnejHisto;
import eai.devass.sinistreat.appli.modele.metier.bnej.LotBnej;
import eai.devass.sinistreat.appli.modele.parametrage.TypeDecisionBnej;
import eai.devass.sinistreat.appli.modele.parametrage.TypeDossierBnej;
import eai.devass.sinistreat.appli.utils.Fonctions;
import eai.devass.sinistreat.appli.utils.IConstantes;
import eai.devass.sinistreat.appli.utils.entites.IDate;
import eai.devass.sinistreat.appli.utils.entites.IParam;
import eai.devass.sinistreat.appli.utils.exception.IMessageException;
import eai.devass.sinistreat.appli.valueobjects.serialisation.PagerVO;

public class BnejManager extends EntiteManagerAbst implements IConstantes,
		IMessageException {
	private IPersistenceService dao = (IPersistenceService) ServicesFactory
			.getService(IPersistenceService.class);
	private SimpleDateFormat dateFormat = new SimpleDateFormat(
			IDate.FORMAT_SIMPLE);
	private static final Logger logger = Logger.getLogger("loggerSINAT");
	// private final KeyLockManager lockManager = KeyLockManagers.newLock();
	private Session session;

	private static final Object lockLotbnej = new Object();
	private static final Object lockCompteur = new Object();
	private Boolean nombreDossierBnej;
	private Boolean nombreLotBnej;

	public int getCompteurLot(Calendar date) throws Exception {
		synchronized (lockCompteur) {
			CallableStatement call = null;
			Connection connection = null;
			int counter = 0;

			try {
				System.out.println("starting procedure for "
						+ Thread.currentThread().getName());
				String sql = "{call LOTCOUNTER(?,?)}";
				connection = ((SessionImpl) getSession()).connection();
				call = connection.prepareCall(sql);
				if (date != null) {
					call.setInt(1, date.get(Calendar.YEAR));
				} else {
					call.setInt(1, Calendar.getInstance().get(Calendar.YEAR));
				}
				call.registerOutParameter(2, Types.INTEGER);
				call.execute();
				counter = call.getInt(2);
				call.close();
				connection.close();
			} catch (Exception e) {
				logger.error("", e);
				throw new FonctionnelleException(EXP_INIT_BNEJ, e);
			} finally {

				if (call != null) {
					try {
						call.close();
					} catch (SQLException e) {
						logger.error(EXP_SQL, e);
						throw new FonctionnelleException(EXP_SQL, e);
					} finally {
						call = null;

					}
				}
				if (connection != null) {
					try {
						connection.close();
					} catch (SQLException e) {
						logger.error(EXP_SQL, e);
						throw new FonctionnelleException(EXP_SQL, e);
					} finally {
						connection = null;
					}
				}

			}
			System.out
					.println("finishing procedure for "
							+ Thread.currentThread().getName() + "counter>>>"
							+ counter);
			return counter;
		}

	}
	
	public void incrementCompteurLot(Calendar date) throws Exception {
		synchronized (lockCompteur) {
			CallableStatement call = null;
			Connection connection = null;
			int counter = 0;

			try {
				System.out.println("starting procedure for "
						+ Thread.currentThread().getName());
				String sql = "{call INCREMENT_LOTCOUNTER(?,?)}";
				connection = ((SessionImpl) getSession()).connection();
				call = connection.prepareCall(sql);
				if (date != null) {
					call.setInt(1, date.get(Calendar.YEAR));
				} else {
					call.setInt(1, Calendar.getInstance().get(Calendar.YEAR));
				}
				call.registerOutParameter(2, Types.INTEGER);
				call.execute();
				counter = call.getInt(2);
				call.close();
				connection.close();
			} catch (Exception e) {
				logger.error("", e);
				throw new FonctionnelleException(EXP_INIT_BNEJ, e);
			} finally {

				if (call != null) {
					try {
						call.close();
					} catch (SQLException e) {
						logger.error(EXP_SQL, e);
						throw new FonctionnelleException(EXP_SQL, e);
					} finally {
						call = null;

					}
				}
				if (connection != null) {
					try {
						connection.close();
					} catch (SQLException e) {
						logger.error(EXP_SQL, e);
						throw new FonctionnelleException(EXP_SQL, e);
					} finally {
						connection = null;
					}
				}

			}
			System.out
					.println("finishing procedure for "
							+ Thread.currentThread().getName() + "increment counter>>>"
							+ counter);
		}

	}

	public Object CreationLotBnej(LotBnej lotBnej)
			throws Exception {

		try {
			this.createEntite(lotBnej);
			incrementCompteurLot(Calendar.getInstance());
		} catch (EntiteException e) {
			throw new FonctionnelleException(EXP_SQL, e);
		}

		return lotBnej;
	}

	public LotBnej getLotBnej(String numero) throws Exception {
		LotBnej bnej = null;
		if (Fonctions.isEmpty(numero)) {
			throw new FonctionnelleException(EXP_SEARCH_BNEJ);
		} else {
			StringBuffer hql = new StringBuffer(" from LotBnej b ");
			hql.append(" where b.numero='").append(numero).append("'");
			bnej = (LotBnej) getSession().createQuery(hql.toString())
					.uniqueResult();
			if (bnej == null) {
				throw new FonctionnelleException(EXP_NORESULT_BNEJ);
			}

			return bnej;
		}

	}

//	public List<LotBnej> getlistLotBnej(String numero) throws Exception {
//		List<LotBnej> lstBnej = new ArrayList<LotBnej>();
//		if (Fonctions.isEmpty(numero)) {
//			throw new FonctionnelleException(EXP_SEARCH_BNEJ);
//		} else {
//			StringBuffer hql = new StringBuffer(" from LotBnej b ");
//			hql.append(" where b.numero like '%").append(numero).append("%'")
//					.append(" order by id");
//			lstBnej = getSession().createQuery(hql.toString()).list();
//			if (Fonctions.isEmpty(lstBnej)) {
//				throw new FonctionnelleException(EXP_NORESULT_BNEJ);
//			}
//			return lstBnej;
//
//		}
//
//	}
	public List getlistLotBnej(LotBnej bnej,
			PagerVO pagerVO) throws FonctionnelleException {
		if (bnej == null) {
			return new ArrayList<LotBnej>();
		}
		nombreLotBnej = false;
		Query query = null;
		try {
			query = getSession().createQuery(
					getListLotBnejSql(bnej, nombreLotBnej))
					.setMaxResults(
							Long.valueOf(IParam.MAX_SINISTRE).intValue() + 1);
		} catch (PersistenceException e) {
			logger.error(EXP_STAND_MESS, e);
			throw new FonctionnelleException(EXP_STAND_MESS, e);
		}

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

	public String buildNumeroLot(int n, int year) {

		StringBuilder s = new StringBuilder();
		s.append(n);
		s.append(BNEJ_NUMERO_SEPARATOR);
		s.append(year);
		return s.toString();
	}

	public Object creationDossierBnej(DossierBnej dossierBnej)
			throws PersistenceException {

		synchronized (lockLotbnej) {

			// create a new transaction
			Session s = (Session) dao.newSession();
			Transaction trx = s.beginTransaction();
			LotBnej bnej = new LotBnej();
			bnej.setId(dossierBnej.getRefLotBnej().getId());
			s.refresh(bnej);
			System.out.println("Starting BnejManager>>>>>>>>>>>>"
					+ Thread.currentThread().getName());
			System.out.println(Thread.currentThread().getName() + "||lot::"
					+ bnej);
			// bnej.increment();
			s.saveOrUpdate(bnej);
			dossierBnej.setRefLotBnej(bnej);
			dossierBnej.setDateCreation(new Date());
			s.save(dossierBnej);
			// dossierBnej.getRefLotBnej().increment();// from dossierbnej
			// this.doRevampModifyEntity(bnej);
			// this.modifyEntite(dossierBnej.getRefLotBnej());
			// dossierBnej.setRefLotBnej(bnej);
			// this.createEntite(dossierBnej);
			// commit current transaction
			trx.commit();
			s.close();
			System.out.println(Thread.currentThread().getName() + "||lot::"
					+ dossierBnej.getRefLotBnej().getId() + " *****  dossier::"
					+ dossierBnej);
			System.out.println(Thread.currentThread().getName() + "||lot::"
					+ bnej);

			System.out.println("Finishing BnejManager>>>>>>>>>>>>"
					+ Thread.currentThread().getName());
			return dossierBnej;

		}

	}

	// public Object synchronizedCreationDossierBnej(final DossierBnej
	// dossierBnej){
	//
	// lockManager.executeLocked(dossierBnej.getRefLotBnej().getClass().getName(),
	// new LockCallback() {
	//
	// public void doInLock() {
	//
	// try {
	// LotBnej lotbnej=dossierBnej.getRefLotBnej();
	// System.out.println("lot::"+lotbnej.getId()+" *****  nombre de dossiers::"+lotbnej.getNombreDossiers());
	// CreationDossierBnej(dossierBnej);
	// System.out.println("lot::"+lotbnej.getId()+" *****  dossier::"+dossierBnej);
	// System.out.println("lot::"+lotbnej.getId()+" *****  nombre de dossiers::"+dossierBnej.getRefLotBnej().getNombreDossiers());
	// } catch (FonctionnelleException e) {
	// e.printStackTrace();
	// } catch (PersistenceException e) {
	// e.printStackTrace();
	// }
	//
	// }
	// });
	// return dossierBnej;
	//
	// }

	public void modificationDossierBnej(DossierBnej dossierBnej)
			throws FonctionnelleException {

		try {
			if(dossierBnej.getListDossierBnejHisto() != null && dossierBnej.getListDossierBnejHisto().size()!= 0)
			{
				for (DossierBnejHisto d : dossierBnej.getListDossierBnejHisto()) {
				d.setRefDossierBnej(dossierBnej);
				}
			}
			this.modifyEntite(dossierBnej);
		} catch (EntiteException e) {
			// TODO Auto-generated catch block
			throw new FonctionnelleException(EXP_SQL, e);
		}

	}

	
	

	public List<TypeDossierBnej> getListTypeDossierBnej()
			throws FonctionnelleException {

		List<TypeDossierBnej> lstDossierBnej = new ArrayList<TypeDossierBnej>();

		try {

			String sql = new String("from TypeDossierBnej");
			Query q = getSession().createQuery(sql.toString());
			lstDossierBnej = (List<TypeDossierBnej>) q.list();

		} catch (Exception e) {
			throw new FonctionnelleException("Erreur lors du validation", e);
		}
		return lstDossierBnej;

	}

	public List<TypeDecisionBnej> getListTypeDecisionBnej()
			throws FonctionnelleException {

		List<TypeDecisionBnej> lstDecisonBnej = new ArrayList<TypeDecisionBnej>();

		try {
			String sql = new String("from TypeDecisionBnej");
			Query q = getSession().createQuery(sql.toString());
			lstDecisonBnej = (List<TypeDecisionBnej>) q.list();

		} catch (Exception e) {
			throw new FonctionnelleException("Erreur lors du validation", e);
		}
		return lstDecisonBnej;

	}

	public String getTypeDecisionBnejLibelle(String code) {

		TypeDecisionBnej decision = null;

		try {
			StringBuilder sql = new StringBuilder(
					"from TypeDecisionBnej  where code=");
			sql.append(code);
			Query q = getSession().createQuery(sql.toString());
			decision = (TypeDecisionBnej) q.uniqueResult();

		} catch (Exception e) {
			return "ND";
		}

		if (decision != null) {
			return decision.getLibelle();
		} else {
			return "ND";
		}

	}

	private Session getSession() throws PersistenceException {
		if (session != null) {
			return session;
		} else {
			return (Session) dao.getSession();
		}
	}

	public Long getNombreDossierBnej(DossierBnej dossierBnej)
			throws HibernateException, PersistenceException,
			FonctionnelleException {
		nombreDossierBnej = true;
		String hql = getListDossierBnejSql(dossierBnej, nombreDossierBnej);
		return (Long) getSession().createQuery(hql).uniqueResult();
	}

	private String getListDossierBnejSql(DossierBnej dossierBnej,
			Boolean nombreDossierBnej) throws FonctionnelleException {

		if (dossierBnej == null) {
			throw new FonctionnelleException(EXP_DOSSIER_BNEJ_NULL);
		}
		StringBuffer hql = null;
		if (nombreDossierBnej == true) {
			hql = new StringBuffer(
					"select count(*) from DossierBnej dossierBnej  ");
		} else {
			hql = new StringBuffer("from DossierBnej dossierBnej  ");
		}

		hql.append(" where 1=1 ");
		// id
		if (dossierBnej.getId() != 0) {
			hql.append(" and dossierBnej.id=").append(dossierBnej.getId())
					.append(" ");
		}
		// numBnej
		if (!StringUtils.isEmpty(dossierBnej.getNumero())
				&& !StringUtils.isEmpty(dossierBnej.getNumero().replaceAll(" ",
						""))) {
			hql.append(" and dossierBnej.numero like '%")
					.append((dossierBnej.getNumero().trim())
							.replaceAll(" ", "")).append("%' ");
		}
		// numSinistre
		if (!StringUtils.isEmpty(dossierBnej.getNumeroSinistre())
				&& !StringUtils.isEmpty(dossierBnej.getNumeroSinistre()
						.replaceAll(" ", ""))) {
			hql.append(" and dossierBnej.numeroSinistre like '%")
					.append((dossierBnej.getNumeroSinistre().trim())
							.replaceAll(" ", "")).append("%' ");
		}
		// numGrave
		if (!StringUtils.isEmpty(dossierBnej.getNumeroGrave())
				&& !StringUtils.isEmpty(dossierBnej.getNumeroGrave().trim())) {
			hql.append(" and dossierBnej.numeroGrave='")
					.append(dossierBnej.getNumeroGrave().trim()).append("' ");
		}
		// Num Lot
		if (dossierBnej.getRefLotBnej() != null) {
			if (!StringUtils.isEmpty(dossierBnej.getRefLotBnej().getNumero())
					&& !StringUtils.isEmpty(dossierBnej.getRefLotBnej()
							.getNumero().trim())) {

				hql.append(" and upper(dossierBnej.refLotBnej.numero) like '%")
						.append(StringUtils.upperCase(dossierBnej
								.getRefLotBnej().getNumero())).append("%' ");

			}
		}
		// Lot valide
		hql.append(" and dossierBnej.refLotBnej.valide = true");

		hql.append(" order by dossierBnej.numero DESC");
		return hql.toString();
	}

	public List getPartCollectionByCondition(Query query, int page, int pageSize) {
		if (query != null) {
			query.setFirstResult(page * pageSize);
			query.setMaxResults(pageSize);
			return query.list();
		} else {
			return new ArrayList();
		}
	}

	public List getListDossierBnej(DossierBnej dossierBnej, HashMap mapDates,
			PagerVO pagerVO) throws FonctionnelleException {
		if (dossierBnej == null) {
			return new ArrayList<DossierBnej>();
		}
		nombreDossierBnej = false;
		Query query = null;
		try {
			query = getSession().createQuery(
					getListDossierBnejSql(dossierBnej, nombreDossierBnej))
					.setMaxResults(
							Long.valueOf(IParam.MAX_SINISTRE).intValue() + 1);
		} catch (PersistenceException e) {
			logger.error(EXP_STAND_MESS, e);
			throw new FonctionnelleException(EXP_STAND_MESS, e);
		}

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

	public Long getNombreLotBnej(LotBnej bnej) throws HibernateException, PersistenceException,
			FonctionnelleException {
		nombreLotBnej = true;
		String hql = getListLotBnejSql(bnej, nombreLotBnej);
		return (Long) getSession().createQuery(hql).uniqueResult();
	}
	
	private String getListLotBnejSql(LotBnej bnej,
			Boolean nombreLotBnej) throws FonctionnelleException {

		if (bnej == null) {
			throw new FonctionnelleException(EXP_LOT_BNEJ_NULL);
		}
		
		StringBuffer hql = null;
		if (nombreLotBnej == true) {
			hql = new StringBuffer(
					"select count(*) from LotBnej b  ");
		} else {
			hql = new StringBuffer("from LotBnej b  ");
		}

		hql.append(" where b.numero like '%").append(bnej.getNumero()).append("%'")
		.append(" order by id");
		return hql.toString();
	}
	
//	private boolean contient(List<DossierBnej> listDossierBnej,
//			DossierBnej dossierBnej) {
//
//		if (dossierBnej == null) {
//			return false;
//		}
//
//		for (DossierBnej curDossierBnej : listDossierBnej) {
//			if (curDossierBnej == null
//					|| curFraisMedicaux.getRefTypeFrais().getCode() == null
//					|| curFraisMedicaux.getRefTypeFrais() == null) {
//				return false;
//			}
//
//			if (curFraisMedicaux.getMontantFacture() == null) {
//				return false;
//			}
//
//			if (curDossierBnej.getNumero()
//					.equals(frais.getRefTypeFrais().getCode())
//					&& curDossierBnej.getTypeDossier().equals(
//							frais.getMontantFacture())) {
//				return true;
//			}
//
//		}
//
//		return false;
//	}


	public LotBnej modifierLotBnej(LotBnej lotBnej)
			throws FonctionnelleException, ParseException {
		try {
			LotBnej lotDB = (LotBnej) getSession().get(LotBnej.class,
					lotBnej.getId());
			if (lotDB == null) {
				throw new FonctionnelleException(EXP_LOT_BNEJ_NULL);
			}
			lotDB.setValide(lotBnej.getValide());
			dao.deleteAll(lotDB.getListDossierBnej());
			if (lotBnej.getListDossierBnej() != null
					&& lotBnej.getListDossierBnej().size() != 0) {
				for (Iterator iterator = lotBnej.getListDossierBnej()
						.iterator(); iterator.hasNext();) {
					DossierBnej dossier = (DossierBnej) iterator.next();
					dossier.setRefLotBnej(lotBnej);
					dossier.setDateCreation(new Date());
				}
			} else {
				lotDB.setListDossierBnej(new ArrayList<DossierBnej>());
			}
			lotDB.setListDossierBnej(lotBnej.getListDossierBnej());
			dao.updateObject(lotDB);
			return lotBnej;
		} catch (PersistenceException e) {
			logger.error(EXP_MODIFICATION_REGLEMENT, e);
			throw new FonctionnelleException(EXP_MODIFICATION_REGLEMENT, e);
		}
	}

	public void addHistoriqueDecision(DossierBnej dossierBnej) throws PersistenceException {
		session = getSession();
		DossierBnejHisto dossierBnejHisto = new DossierBnejHisto();
		dossierBnejHisto.setCommentaire(dossierBnej.getCommentaire());
		dossierBnejHisto.setDateTraitement(new Date());
		dossierBnejHisto.setRefDecisionBnej(dossierBnej.getDecision());
		dossierBnejHisto.setRefDossierBnej(dossierBnej);
		dossierBnejHisto.setOperateur(dossierBnej.getOperateurTraitement());
		session.save(dossierBnejHisto);
		
	}

	public List<DossierBnej> getlistDossierBnej(long idLotBnej, PagerVO pagerVO)
			throws Exception {
		
		Query query = null;
		try {
			query = getSession().createQuery(
					getDossierBnejByIdLot(idLotBnej))
					.setMaxResults(
							Long.valueOf(IParam.MAX_SINISTRE).intValue() + 1);
		} catch (PersistenceException e) {
			logger.error(EXP_STAND_MESS, e);
			throw new FonctionnelleException(EXP_STAND_MESS, e);
		}

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

	public Long getNombreDossierBnej(long idLotBnej) throws HibernateException, PersistenceException {
		String hql = "Select count (id) " +getDossierBnejByIdLot(idLotBnej);
		return (Long) getSession().createQuery(hql).uniqueResult();
	}
	
	public String getDossierBnejByIdLot(long idLotBnej){
		StringBuffer hql = new StringBuffer(" from DossierBnej d ");
		hql.append(" where d.refLotBnej.id=").append(idLotBnej)
				.append(" order by id");
		return hql.toString();
	}
}
