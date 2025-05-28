package eai.devass.gsr.appli.manager.metier.dossierRente;

import java.util.List;

import ma.co.omnidata.framework.services.core.ServicesFactory;
import ma.co.omnidata.framework.services.dao.IPersistenceService;
import ma.co.omnidata.framework.services.entites.EntiteException;
import ma.co.omnidata.framework.services.entites.EntiteManagerAbst;
import ma.co.omnidata.framework.services.entites.IEntite;
import ma.co.omnidata.framework.services.entites.ProcessEntiteException;
import ma.co.omnidata.framework.services.entites.ValidateEntiteException;
import ma.co.omnidata.framework.utile.DateUtile;

import org.hibernate.Session;

import eai.devass.commun.appli.exeptions.ExceptionMetier;
import eai.devass.gsr.appli.modele.metier.dossierRente.Prothese;
import eai.devass.gsr.appli.modele.parametrage.NatureProthese;
import eai.devass.gsr.appli.utile.IMessageException;

/**
 * Manager de l ' entité Protheses
 * 
 * @author Nom Prenom (email)
 */
public class ProthesesManager extends EntiteManagerAbst {

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
		Prothese protheses = (Prothese) entite;
		NatureProtheseManager manager = new NatureProtheseManager();
		List result = manager.lookupEntite(protheses.getRefNatureProthese());
		if(result!=null && result.size()>0) {
			protheses.setRefNatureProthese((NatureProthese) result.get(0));
		}
		protheses.setDateCreation(DateUtile.dateCalendarCourante());

		return protheses;

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
		Prothese protheses = (Prothese) entite;
		return protheses;
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
		Prothese protheses = (Prothese) entite;

		return protheses;

	}
	
	
	/**
	 * Retourne la Prothese par son identifiant.
	 * @param id
	 * @return
	 * @throws ExceptionMetier
	 */
	public Prothese getProtheseByID(long id) throws ExceptionMetier{
		
		Prothese prothese = null;
		IPersistenceService dao = (IPersistenceService) ServicesFactory
		.getService(IPersistenceService.class);
		try {
			Class clazz = Class.forName("eai.devass.gsr.appli.modele.metier.dossierRente.Prothese");
			prothese = (Prothese) ((Session) dao.getSession()).get(clazz, id);
			
		} catch (Exception e) {
			throw new ExceptionMetier(e.getMessage());
		}

		if (prothese == null) {
			throw new ExceptionMetier(IMessageException.EXP_PROTHESE_INTROUVALE);
		}

//		if (prothese.getValidation() != true) {
//			throw new ExceptionMetier(IMessageException.EXP_RENTE_NON_VALIDE);
//		}
		
		return prothese;
		
	}

}