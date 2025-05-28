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
import eai.devass.gsr.appli.manager.metier.transverse.CalculTransverses;
import eai.devass.gsr.appli.manager.metier.transverse.TransverseManager;
import eai.devass.gsr.appli.modele.metier.dossierRente.Rentier;
import eai.devass.gsr.appli.modele.metier.mouvements.MvtDecesRentier;
import eai.devass.gsr.appli.utile.IMessageException;

/**
 * @author elfaismo
 *
 */
public class CalculArreragesRenteUC extends FacadeServiceUseCase {

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
		
		CalculTransverses calculTransverse = new CalculTransverses();
		
		MvtDecesRentier  mouvement = (MvtDecesRentier) this.getItem(MvtDecesRentier.class);
		if (mouvement != null) {
		Rentier rentier = mouvement.getRefRentier();
		
		rentier = rentierManager.getRentierByID(rentier.getId());
		mouvement.getDateDeces();
		Calendar dateDeces =  mouvement.getDateDeces();
		Calendar dateDerniereEcheance = mouvement.getEcheanceEffective();
		if (rentier == null || rentier.getId() <= 0) {
			throw new ExceptionMetier(IMessageException.EXP_RENTIER_INTROUVALE);
		}
		
		if (dateDeces == null) {
			throw new ExceptionMetier(IMessageException.EXP_DATE_DECES_NULL);
		}

		
		Double arrerage = 0.0D;
		Double tropPercu = 0.0D;
		Double prorata = 0.0D;
		arrerage = (Double)transverseManager.sommeQuittancesTrimNonReglees(rentier.getId(),dateDeces,dateDerniereEcheance);
		tropPercu = (Double)transverseManager.sommeQuittancesReglees(rentier.getId(),dateDeces);

				
		prorata = calculTransverse.calculerProrata(rentier.getMontantRenteTrimestrielle(),
				mouvement.getEcheanceEffective(), mouvement.getDateDeces());
		
		arrerage = (arrerage == null) ? 0D : arrerage;
		tropPercu = (tropPercu == null) ? 0D : tropPercu;
		
		Double  mntGlobalVersee = arrerage+ prorata-tropPercu;
		
			mouvement.setArreragesRente(arrerage);
			mouvement.setTropPercu(tropPercu);
			mouvement.setMntProrata(prorata);
			mouvement.setMntGlobalVersee(mntGlobalVersee);
		}
		
		this.addResultItem(mouvement);
	}
	
}