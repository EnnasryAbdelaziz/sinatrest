package eai.devass.gsr.appli.manager.metier.dossierRente;

import ma.co.omnidata.framework.services.entites.EntiteException;
import ma.co.omnidata.framework.services.entites.EntiteManagerAbst;
import ma.co.omnidata.framework.services.entites.IEntite;
import ma.co.omnidata.framework.services.entites.ProcessEntiteException;
import ma.co.omnidata.framework.services.entites.ValidateEntiteException;
import eai.devass.gsr.appli.modele.metier.dossierRente.RentierHisto;

/**
 * Manager de l ' entité RentierHisto
 * 
 * @author Mossab wassim
 */
public class RentierHistoManager extends EntiteManagerAbst {

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
			throws ProcessEntiteException {
		RentierHisto rentierHisto = (RentierHisto) entite;

		// rentierHisto.setDateCreation(DateUtile.dateCalendarCourante());

		return rentierHisto;

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
			throws ValidateEntiteException {
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
			throws ValidateEntiteException {

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
			throws ValidateEntiteException {

	}

	/**
	 * Methode qui habille une entité avec ces entités refs completes pou ne
	 * pas ecraser les valeurs des refs avec des nulls
	 * 
	 * @param entite
	 *            lentité à habiller
	 * @throws Exception
	 */
	protected IEntite habiller(IEntite entite) throws EntiteException {
		RentierHisto rentierHisto = (RentierHisto) entite;

		return rentierHisto;
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
			throws ProcessEntiteException {
		try {
			return this.habiller(entite);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// logger.error("probl�me technique",e);
		}
		return null;
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
	protected IEntite doRevampCreateEntity(IEntite entite) {
		try {
			return this.habiller(entite);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// logger.error("probl�me technique",e);
		}
		return null;
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
			throws ProcessEntiteException {
		RentierHisto rentierHisto = (RentierHisto) entite;

		return rentierHisto;

	}
}