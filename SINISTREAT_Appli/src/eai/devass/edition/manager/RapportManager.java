package eai.devass.edition.manager;

import java.math.BigDecimal;

import ma.co.omnidata.framework.services.core.ServicesFactory;
import ma.co.omnidata.framework.services.dao.IPersistenceService;
import ma.co.omnidata.framework.services.dao.PersistenceException;
import ma.co.omnidata.framework.services.entites.EntiteException;
import ma.co.omnidata.framework.services.entites.EntiteManagerAbst;
import ma.co.omnidata.framework.services.entites.IEntite;
import ma.co.omnidata.framework.services.entites.ProcessEntiteException;
import ma.co.omnidata.framework.services.entites.ValidateEntiteException;

import org.hibernate.Session;

import eai.devass.edition.modele.Rapport;
import eai.devass.edition.modele.Template;

/**
 * Manager de l ' entité ModePayement
 * 
 * @author Nom Prenom (email)
 */
public class RapportManager extends EntiteManagerAbst {
	IPersistenceService dao = (IPersistenceService) ServicesFactory.getService(IPersistenceService.class);
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
		Rapport rapport = (Rapport) entite;
		return rapport;
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
		Rapport rapport = (Rapport) entite;
		return rapport;
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
		Rapport rapport = (Rapport) entite;

		return rapport;
	}
	
	/**
	 * R�cup�rer la Session hibernate via la DAO
	 * 
	 * @return
	 * @throws PersistenceException
	 */
	public Session getSession() throws PersistenceException {
		return (Session) dao.getSession();
	}
	
	public Rapport getRapportByCode(String codeRapport) throws Exception {
		if (codeRapport == null){
			return null;
		}
		String hql = " from eai.devass.edition.modele.Rapport rapp where rapp.code = '" + codeRapport + "'";
		return (Rapport) getSession().createQuery(hql).uniqueResult();
	}
	
	public Integer getcountRapport(String requeteRapport) throws Exception {
		if (requeteRapport == null){
			return null;
		}
		return ((BigDecimal) getSession().createSQLQuery(requeteRapport).uniqueResult()).intValue();
	}

	public Template getTemplateByCode(String codeTemplate) throws Exception {
		if (codeTemplate == null){
			return null;
		}
		String hql = " from eai.devass.edition.modele.Template templ where templ.code = '" + codeTemplate + "'";
		return (Template) getSession().createQuery(hql).uniqueResult();
	}
}