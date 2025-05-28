/**
 * 
 */
package eai.devass.gsr.appli.usecase.metier.mouvements;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.services.businessInterface.impl.FacadeServiceUseCase;
import ma.co.omnidata.framework.services.core.ServicesFactory;
import eai.devass.commun.appli.exeptions.ExceptionMetier;
import eai.devass.gsr.appli.manager.metier.dossierRente.RentierManager;
import eai.devass.gsr.appli.manager.metier.transverse.TransverseManager;
import eai.devass.gsr.appli.modele.metier.dossierRente.Prothese;
import eai.devass.gsr.appli.modele.metier.dossierRente.Rentier;
import eai.devass.gsr.appli.modele.metier.mouvements.MvtProthese;
import eai.devass.gsr.appli.utile.IMessageException;

/**
 * @author elfaismo
 *
 */
public class CalculerReserveProtheseUC extends FacadeServiceUseCase {

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
		
		
		MvtProthese  mouvement = (MvtProthese) this.getItem(MvtProthese.class);
		if(mouvement!=null){
		Rentier rentier = mouvement.getRefRentier();
		
		Rentier rentierBD = rentierManager.getRentierByID(rentier.getId());
		Calendar dateCalcul = Calendar.getInstance();
		if (rentierBD == null || rentierBD.getId() <= 0) {
			throw new ExceptionMetier(IMessageException.EXP_RENTIER_INTROUVALE);
		}
		
				
		Double reserveProthese = 0.0D;
		
		Double coefficientAge = transverseManager.getCoefficientAge(rentierBD, dateCalcul.getTime());
		
		
			List<Prothese> prothesesList =  mouvement.getRefsProthese();
			if(prothesesList!= null && prothesesList.size()>0 )
			{
			for(Prothese prothese: prothesesList){
				reserveProthese = ((prothese.getMontantProthese() * coefficientAge)/2 ) * 1.5;
				prothese.setReserveProthese(reserveProthese);
			}
			}
			
		
		}
		this.addResultItem(mouvement);
	}

}
