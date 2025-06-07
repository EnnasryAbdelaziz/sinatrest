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
public class CalculerCapitalRachatUC extends FacadeServiceUseCase {

	/* (non-Javadoc)
	 * @see ma.co.omnidata.framework.services.businessInterface.impl.FacadeServiceUseCase#doExecuter(ma.co.omnidata.framework.services.businessInterface.IValueObject, java.util.HashMap)
	 */
	/**
	 * RG3/FGSR-10.15 : Pour chaque prothèse créée, 
	 * la réserve prothèse = (montant de la prothèse * coefficient d’âge / 2) * 1,5 
	 **/
	@Override
	protected void doExecuter(IValueObject entite, HashMap params) throws Exception {

		TransverseManager transverseManager = (TransverseManager) ServicesFactory
				.getService(TransverseManager.class);
		
		RentierManager rentierManager = (RentierManager) ServicesFactory
		.getService(RentierManager.class);
		
		MvtRachat  mouvement = (MvtRachat) this.getItem(MvtRachat.class);
		
		Rentier rentier = mouvement.getRefRentier();
		
		Rentier rentierBD = rentierManager.getRentierByID(rentier.getId());
	
		if (rentierBD == null || rentierBD.getId() <= 0) {
			throw new ExceptionMetier(IMessageException.EXP_RENTIER_INTROUVALE);
		}
		
		Calendar dateCalcul = mouvement.getDateCalcul();
		
		Double nouveauIPP = (mouvement.getNouveauIPP()!=null)? mouvement.getNouveauIPP()/100:0D;
		
		Double salaireUtile = mouvement.getSalaireUtile();
		
		Double coefficientAge = transverseManager.getCoefficientAge(rentierBD, dateCalcul.getTime());
		
		Double capitalCalcule = nouveauIPP*salaireUtile*coefficientAge;

		mouvement.setCapitalCalcule(capitalCalcule);

		this.addResultItem(mouvement);
	}

}
