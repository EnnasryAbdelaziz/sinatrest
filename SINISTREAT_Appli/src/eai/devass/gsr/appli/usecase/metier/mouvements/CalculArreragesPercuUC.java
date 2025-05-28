/**
 * 
 */
package eai.devass.gsr.appli.usecase.metier.mouvements;

import java.util.Calendar;
import java.util.HashMap;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.services.businessInterface.impl.FacadeServiceUseCase;
import ma.co.omnidata.framework.services.core.ServicesFactory;
import eai.devass.commun.appli.exeptions.ExceptionMetier;
import eai.devass.gsr.appli.manager.metier.dossierRente.RentierManager;
import eai.devass.gsr.appli.manager.metier.transverse.TransverseManager;
import eai.devass.gsr.appli.modele.metier.dossierRente.Rentier;
import eai.devass.gsr.appli.modele.metier.mouvements.MvtRachat;
import eai.devass.gsr.appli.utile.IMessageException;

/**
 * @author elfaismo
 *
 */
public class CalculArreragesPercuUC extends FacadeServiceUseCase {

	/* (non-Javadoc)
	 * @see ma.co.omnidata.framework.services.businessInterface.impl.FacadeServiceUseCase#doExecuter(ma.co.omnidata.framework.services.businessInterface.IValueObject, java.util.HashMap)
	 */
	/**
	 * Methode qui execute le Use case d' ajout d' une entite
	 * 
	 * @param entite
	 *            L' objet à ajouter
	 * @param params
	 *            paramatere additionel qu 'on peut passer au Use Case
	 * @throws Exception
	 *             Probleme lors de l' execution du Use case
	 */
	protected void doExecuter(IValueObject entite, HashMap params)
			throws Exception {

		TransverseManager transverseManager = (TransverseManager) ServicesFactory
				.getService(TransverseManager.class);
		
		RentierManager rentierManager = (RentierManager) ServicesFactory
		.getService(RentierManager.class);
		
		MvtRachat  mouvement = (MvtRachat) this.getItem(MvtRachat.class);
		//Correction sonar : Nullcheck of value previously dereferenced 
		if (mouvement != null) {
		Rentier rentier = mouvement.getRefRentier();
		
		rentier = rentierManager.getRentierByID(rentier.getId());

		Calendar dateCalcul =  mouvement.getDateCalcul();
		if (rentier == null || rentier.getId() <= 0) {
			throw new ExceptionMetier(IMessageException.EXP_RENTIER_INTROUVALE);
		}
		
		if (dateCalcul == null) {
			throw new ExceptionMetier(IMessageException.EXP_DATE_CALCUL_NULL);
		}
		
		Double arreragePercu = 0.0D;

		arreragePercu = (Double)transverseManager.getArreragePercu(rentier.getId(),dateCalcul);

		mouvement.setMntPercu((arreragePercu!=null)?arreragePercu:0.0D );
		}
		
		this.addResultItem(mouvement);
	}
	
}