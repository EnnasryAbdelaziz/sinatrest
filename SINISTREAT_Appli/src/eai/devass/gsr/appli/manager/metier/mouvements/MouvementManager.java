package eai.devass.gsr.appli.manager.metier.mouvements;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import ma.co.omnidata.framework.services.entites.EntiteException;
import ma.co.omnidata.framework.services.entites.IEntite;
import ma.co.omnidata.framework.services.entites.ProcessEntiteException;
import ma.co.omnidata.framework.services.entites.ValidateEntiteException;
import ma.co.omnidata.framework.utile.DateUtile;

import org.hibernate.Query;

import eai.devass.commun.appli.exeptions.ExceptionMetier;
import eai.devass.commun.appli.rg.ContextRegleGestion;
import eai.devass.gsr.appli.manager.CommunManager;
import eai.devass.gsr.appli.modele.metier.dossierRente.Rentier;
import eai.devass.gsr.appli.modele.metier.mouvements.Mouvement;
import eai.devass.gsr.appli.modele.metier.mouvements.MvtConsignCNRA;
import eai.devass.gsr.appli.modele.parametrage.EtatMvt;
import eai.devass.gsr.appli.modele.parametrage.TypeMouvement;
import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.utils.exception.IMessageException;

/**
 * Manager de l ' entité Mouvement
 * 
 * @author Nom Prenom (email)
 */
public class MouvementManager extends CommunManager {

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
		Mouvement mouvement = (Mouvement) entite;
		EtatMvt etatMvt = new EtatMvt();
		etatMvt.setId(1);
		mouvement.setRefEtatMvt(etatMvt);
		Calendar toDay = DateUtile.dateCalendarCourante();
		mouvement.setDatEtat(toDay);
		mouvement.setDateCreation(toDay);
		return mouvement;

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
		Mouvement mvt = (Mouvement) entite;
		if (mvt.getRefRentier() == null) {
			ValidateEntiteException ve = new ValidateEntiteException(
					"FIELD.OBLIGATOIRE");
			ve.setValues(new String[] { "rentier" });
			throw ve;

		}
		if (mvt.getRefEtatMvt() == null) {
			ValidateEntiteException ve = new ValidateEntiteException(
					"FIELD.OBLIGATOIRE");
			ve.setValues(new String[] { "etatMvt" });
			throw ve;

		}
		if (mvt.getRefTypeMouvement() == null) {
			ValidateEntiteException ve = new ValidateEntiteException(
					"FIELD.OBLIGATOIRE");
			ve.setValues(new String[] { "typeMouvement" });
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
		/*
		 * if (((Mouvement)entite).getRefRentier() == null){
		 * ValidateEntiteException ve = new ValidateEntiteException
		 * ("FIELD.OBLIGATOIRE"); ve.setValues(new String []{"rentier"}); throw
		 * ve; } if (((Mouvement)entite).getRefEtatMvt() == null){
		 * ValidateEntiteException ve = new ValidateEntiteException
		 * ("FIELD.OBLIGATOIRE"); ve.setValues(new String []{"etatMvt"}); throw
		 * ve; } if (((Mouvement)entite).getRefTypeMouvement() == null){
		 * ValidateEntiteException ve = new ValidateEntiteException
		 * ("FIELD.OBLIGATOIRE"); ve.setValues(new String []{"typeMouvement"});
		 * throw ve; }
		 */
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
		Mouvement mouvement = (Mouvement) entite;

		Rentier refRentier1 = mouvement.getRefRentier();
		Rentier refRentier = new Rentier();

		if (refRentier1 != null) {
			((IEntite) refRentier).setId(refRentier1.getId());
		}
		if (refRentier != null && refRentier1 != null) {
			if (((IEntite) refRentier).getId() != 0) {
				refRentier = (Rentier) (new Rentier()).getFactory()
						.newEntiteManager().getEntite((Rentier) refRentier);
				mouvement.setRefRentier(refRentier);
			} else {
				mouvement.setRefRentier(refRentier1);
			}
		}
		EtatMvt refEtatMvt1 = mouvement.getRefEtatMvt();
		EtatMvt refEtatMvt = new EtatMvt();

		if (refEtatMvt1 != null) {
			((IEntite) refEtatMvt).setId(refEtatMvt1.getId());
		}
		if (refEtatMvt != null && refEtatMvt1 != null) {
			if (((IEntite) refEtatMvt).getId() != 0) {
				refEtatMvt = (EtatMvt) (new EtatMvt()).getFactory()
						.newEntiteManager().getEntite((EtatMvt) refEtatMvt);
				mouvement.setRefEtatMvt(refEtatMvt);
			} else {
				mouvement.setRefEtatMvt(refEtatMvt1);
			}
		}
		TypeMouvement refTypeMouvement1 = mouvement.getRefTypeMouvement();
		TypeMouvement refTypeMouvement = new TypeMouvement();

		if (refTypeMouvement1 != null) {
			((IEntite) refTypeMouvement).setId(refTypeMouvement1.getId());
		}
		if (refTypeMouvement != null && refTypeMouvement1 != null) {
			if (((IEntite) refTypeMouvement).getId() != 0) {
				refTypeMouvement = (TypeMouvement) (new TypeMouvement())
						.getFactory().newEntiteManager()
						.getEntite((TypeMouvement) refTypeMouvement);
				mouvement.setRefTypeMouvement(refTypeMouvement);
			} else {
				mouvement.setRefTypeMouvement(refTypeMouvement1);
			}
		}
		return mouvement;
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
		Mouvement mvt = (Mouvement) entite;
		if (mvt.getRefsQuittance() == null) {
			mvt.setRefsQuittance(new ArrayList());
		}
		return this.habiller(mvt);
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
		Mouvement mouvement = (Mouvement) entite;

		return mouvement;

	}

	public EtatMvt getLastEtatMouvement(Rentier rentier) throws Exception {
		StringBuilder hql = new StringBuilder(
				"select mvt.refEtatMvt from eai.devass.gsr.appli.modele.metier.mouvements.Mouvement mvt"
						+ " join mvt.refRentier rent where rent=? and mvt.id=(select max(id) from"
						+ " eai.devass.gsr.appli.modele.metier.mouvements.Mouvement mvt2 where mvt2.refRentier=rent)");

		Query query = getSession().createQuery(hql.toString()).setParameter(0,
				rentier);

		return (EtatMvt) query.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	// En cas d'utilisation de cette m�thode sans aucun �tat de mouvement �
	// exclure, envoyer etatMvtExclure=0
	public List<Mouvement> getMouvementByDossier(String idDossierRente,
			long etatMvtExclure, int page, int pageSize)
			throws FonctionnelleException {
		try {
			Query query = getSession()
					.createQuery(getMouvementByDossierQuery())
					.setLong("idDossier", Long.valueOf(idDossierRente))
					.setLong("etatMvtExclure", etatMvtExclure);
			if (page > 0 && pageSize > 0) {
				query.setFirstResult((page - 1) * pageSize);
				query.setMaxResults(pageSize);
			}
			return query.list();
		} catch (Exception e) {
			logger.error(IMessageException.EXP_RECHERCHE, e);
			throw new FonctionnelleException(IMessageException.EXP_RECHERCHE, e);
		}

	}

	public Long getNombreMouvementByDossier(String idDossierRente,
			long etatMvtExclure) throws FonctionnelleException {
		try {
			String hql = "Select count (mvt) " + getMouvementByDossierQuery();
			Query query = getSession().createQuery(hql)
					.setLong("idDossier", Long.valueOf(idDossierRente))
					.setLong("etatMvtExclure", etatMvtExclure);
			// if (page > 0 && pageSize > 0) {
			// query.setFirstResult((page - 1) * pageSize);
			// query.setMaxResults(pageSize);
			// }
			return (Long) query.uniqueResult();
		} catch (Exception e) {
			logger.error(IMessageException.EXP_RECHERCHE, e);
			throw new FonctionnelleException(IMessageException.EXP_RECHERCHE, e);
		}

	}

	private String getMouvementByDossierQuery() {
		StringBuilder hql = new StringBuilder(
				"from eai.devass.gsr.appli.modele.metier.mouvements.Mouvement mvt"
						+ " where mvt.refRentier.refDossierRente.id=:idDossier and mvt.refEtatMvt.id!=:etatMvtExclure order by mvt.dateCreation desc");
		return hql.toString();
	}

	public Double getCapitalVerseCNRA(String idRentier)
			throws FonctionnelleException {
		try {
			StringBuilder hql = new StringBuilder(
					"select sum(mvt.mntComplementCNRA) from eai.devass.gsr.appli.modele.metier.mouvements.Mouvement mvt "
							+ "where mvt.refRentier.id=:idRentier and mvt.refTypeMouvement.id = 3 and mvt.refEtatMvt.id = 2");
			Query query = getSession().createQuery(hql.toString()).setLong(
					"idRentier", Long.valueOf(idRentier));
			Object result = query.uniqueResult();
			Double sumComplementsCNRA = result != null ? (Double) result : 0D;

			hql = new StringBuilder("select mvt.mntCNRA from eai.devass.gsr.appli.modele.metier.mouvements.Mouvement mvt "
							+ "where mvt.id = (select max(mvt1.id) from eai.devass.gsr.appli.modele.metier.mouvements.Mouvement mvt1"
							+" where mvt1.refRentier.id=:idRentier and mvt1.refTypeMouvement.id = 2 and mvt1.refEtatMvt.id = 2)");
							
			query = getSession().createQuery(hql.toString()).setLong(
					"idRentier", Long.valueOf(idRentier));
			result = query.uniqueResult();
			Double capitalCNRA = result != null ? (Double) result : 0D;
			return sumComplementsCNRA + capitalCNRA;
		} catch (Exception e) {
			logger.error(IMessageException.EXP_RECHERCHE, e);
			throw new FonctionnelleException(IMessageException.EXP_RECHERCHE, e);
		}
	}

	public Mouvement getDernierMvtComplementCNRA(String idRentier)
			throws FonctionnelleException {
		try {
			StringBuilder hql = new StringBuilder(
					"from eai.devass.gsr.appli.modele.metier.mouvements.Mouvement mvt1 where mvt1.id = (select max(mvt.id) from eai.devass.gsr.appli.modele.metier.mouvements.Mouvement mvt"
							+ " where mvt.refRentier.id=:idRentier and mvt.refTypeMouvement.id = 3 and mvt.refEtatMvt.id = 2)");
			Query query = getSession().createQuery(hql.toString()).setLong(
					"idRentier", Long.valueOf(idRentier));
			return (Mouvement) query.uniqueResult();
		} catch (Exception e) {
			logger.error(IMessageException.EXP_RECHERCHE, e);
			throw new FonctionnelleException(IMessageException.EXP_RECHERCHE, e);
		}
	}
	/**
	 * Recuperer le dernier mouvement valid�e
	 * @param idRentier
	 * @return
	 * @throws FonctionnelleException
	 */
	
	public Mouvement getDernierMouvementValidee(Long idRentier) throws FonctionnelleException {
		try {
			StringBuilder hql = new StringBuilder(
					"select mvt1 from eai.devass.gsr.appli.modele.metier.mouvements.Mouvement mvt1 " +
					" where mvt1.id = (select max(mvt.id) from eai.devass.gsr.appli.modele.metier.mouvements.Mouvement mvt"
							+ " where mvt.refRentier.id=:idRentier and mvt.refEtatMvt.id = 2 and mvt.refTypeMouvement.id != 19)");
			Query query = getSession().createQuery(hql.toString()).setLong(
					"idRentier", idRentier);
			return (Mouvement) query.uniqueResult();
		} catch (Exception e) {
			logger.error(IMessageException.EXP_RECHERCHE, e);
			throw new FonctionnelleException(IMessageException.EXP_RECHERCHE, e);
		}
	}
	
	
	/**
	 * Recuperer l'identifiant du dernier mouvement valid�e
	 * @param idRentier
	 * @return
	 * @throws FonctionnelleException
	 */
	
	public Long getLastIDMouvementValidee(Long idRentier) throws FonctionnelleException {
		try {
			StringBuilder hql = new StringBuilder(
					"select mvt1.id from eai.devass.gsr.appli.modele.metier.mouvements.Mouvement mvt1 " +
					" where mvt1.id = (select max(mvt.id) from eai.devass.gsr.appli.modele.metier.mouvements.Mouvement mvt"
							+ " where mvt.refRentier.id=:idRentier and mvt.refEtatMvt.id = 2 and mvt.refTypeMouvement.id != 19)");
			Query query = getSession().createQuery(hql.toString()).setLong(
					"idRentier", idRentier);
			return (Long) query.uniqueResult();
		} catch (Exception e) {
			logger.error(IMessageException.EXP_RECHERCHE, e);
			throw new FonctionnelleException(IMessageException.EXP_RECHERCHE, e);
		}
	}
	
	public Object getAvantDernierMouvement(String idMouvement) throws Exception {
//		StringBuilder hql = new StringBuilder(
//				"Select item.serialisation from eai.devass.commun.appli.util.CommonUtils.HistoryItem item"
//					+ " join eai.devass.gsr.appli.modele.metier.mouvements.Mouvement mvt where mvt.id=:idMouvement and mvt.id=(select max(id) from"
//						+ " eai.devass.commun.appli.util.CommonUtils.HistoryItem item2 where item2.idHistorisable=:idMouvement and action!=:actionValide )");	
		
		StringBuilder hql = new StringBuilder("select item.serialisationBlob from eai.devass.commun.appli.modele.HistoryItem item" +
				" where item.id = (select max(item1.id) from eai.devass.commun.appli.modele.HistoryItem item1"
						+ " where item1.idHistorisable=:idMouvement and action!=:actionValide)");
			Query query = getSession().createQuery(hql.toString())
		.setLong("idMouvement", Long.valueOf(idMouvement))
		.setString("actionValide", ContextRegleGestion.VALIDATION.getContext());

		return (Object) query.uniqueResult();
	}
	
	public Object getMvtArchiveByID(String idMouvement) throws Exception {
	
		StringBuilder hql = new StringBuilder("select item.serialisationBlob from eai.devass.commun.appli.modele.HistoryItem item" +
				" where item.id = (select max(item1.id) from eai.devass.commun.appli.modele.HistoryItem item1"
						+ " where item1.idHistorisable=:idMouvement and action!=:actionValide and nomClasse =:mvtProthese)");
		Query query = getSession().createQuery(hql.toString())
		.setLong("idMouvement", Long.valueOf(idMouvement))
		.setString("actionValide", ContextRegleGestion.VALIDATION.getContext())
		.setString("mvtProthese", "eai.devass.gsr.appli.modele.metier.mouvements.MvtProthese");
		return (Object) query.uniqueResult();
	}
	
	public List<MvtConsignCNRA> getListMvtsConsignation(String idRentier)
	throws FonctionnelleException {
		try {
			StringBuilder hql = new StringBuilder(
					"select mvt.mntCNRA from eai.devass.gsr.appli.modele.metier.mouvements.Mouvement mvt "
							+ "where mvt.refRentier.id=:idRentier and mvt.refTypeMouvement.id = 2 and mvt.refEtatMvt.id = 2");
			Query query = getSession().createQuery(hql.toString()).setLong(
					"idRentier", Long.valueOf(idRentier));
			return query.list();
		} catch (Exception e) {
			logger.error(IMessageException.EXP_RECHERCHE, e);
			throw new FonctionnelleException(IMessageException.EXP_RECHERCHE, e);
		}
}
	
	/**
	 * Retourne le Rentier par son id.
	 * @param id
	 * @return
	 * @throws ExceptionMetier
	 */
	public Mouvement getMouvementByID(long id) throws ExceptionMetier{
		
		Mouvement mouvement = null;
		try {
			Class clazz = Class.forName("eai.devass.gsr.appli.modele.metier.mouvements.Mouvement");
			mouvement = (Mouvement) getSession().get(clazz, id);
			
		} catch (Exception e) {
			throw new ExceptionMetier("Probl�me type: ", e);
		}

		if (mouvement == null) {
			throw new ExceptionMetier("Mouvement introuvable");
		}

		
		return mouvement;
		
	}
	
	/**
	 * Mettre � jour l'�tat de dossier rente
	 * @param idRente
	 * @param etatRente
	 * @throws Exception
	 */
	public void updateMotifAnnulation(Long idMvt, String motifAnnulation) throws Exception {
		
		String hql = "update MvtAnnulation set motifAnnulation=? where id=?";

		Query query = getSession().createQuery(hql);
		
		query.setParameter(0, motifAnnulation).setLong(1, idMvt).executeUpdate();

				
	}
	
	public void updateIdMvtAnnulee(Long idMvtAnnule, Long idMvt) throws Exception {
		
		String hql = "update MvtAnnulation set refMvtAnnule.id=? where id=?";

		Query query = getSession().createQuery(hql);
		
		query.setParameter(0, idMvtAnnule).setLong(1, idMvt).executeUpdate();

				
	}
	
}