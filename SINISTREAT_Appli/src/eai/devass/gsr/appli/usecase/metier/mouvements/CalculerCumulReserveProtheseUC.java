/**
 * 
 */
package eai.devass.gsr.appli.usecase.metier.mouvements;

import java.util.HashMap;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.services.businessInterface.impl.FacadeServiceUseCase;
import ma.co.omnidata.framework.services.core.ServicesFactory;
import eai.devass.commun.appli.exeptions.ExceptionMetier;
import eai.devass.gsr.appli.manager.metier.dossierRente.RentierManager;
import eai.devass.gsr.appli.modele.metier.dossierRente.Prothese;
import eai.devass.gsr.appli.modele.metier.dossierRente.Rentier;
import eai.devass.gsr.appli.modele.metier.mouvements.MvtProthese;
import eai.devass.gsr.appli.prm.EtatProthese;
import eai.devass.gsr.appli.utile.IMessageException;

/**
 * @author elfaismo
 *
 */
public class CalculerCumulReserveProtheseUC extends FacadeServiceUseCase {
	
	
	
	/* (non-Javadoc)
	 * @see ma.co.omnidata.framework.services.businessInterface.impl.FacadeServiceUseCase#doExecuter(ma.co.omnidata.framework.services.businessInterface.IValueObject, java.util.HashMap)
	 */
	/**
	 *	Cumul réserve prothèse à afficher est à mettre à jour selon la formule suivante : 
	 *   cumul réserve prothèse – ancienne réserve prothèse+nouvelle réserve prothèse
	 **/
	@Override
	protected void doExecuter(IValueObject entite, HashMap params) throws Exception {
	
		RentierManager rentierManager = (RentierManager) ServicesFactory
		.getService(RentierManager.class);

		MvtProthese  mouvement = (MvtProthese) this.getItem(MvtProthese.class);
		if(mouvement!=null){
		Rentier rentier = mouvement.getRefRentier();
		Rentier rentierBD = rentierManager.getRentierByID(rentier.getId());
		if (rentierBD == null || rentierBD.getId() <= 0) {
			throw new ExceptionMetier(IMessageException.EXP_RENTIER_INTROUVALE);
		}
				
		Double cumulReserveProthese = 0.0D;
		
		
		List<Prothese> listAncienneProtheses = rentierBD.getRefsProtheses();
		
		if(listAncienneProtheses!= null && listAncienneProtheses.size()>0 ){
					
			for(Prothese ancienneProthese : listAncienneProtheses)
			{		
				if(ancienneProthese.getRefEtatProthese().getId()==EtatProthese.Validee.getId() ||
						ancienneProthese.getRefEtatProthese().getId()==EtatProthese.Cree.getId()	){
					
					cumulReserveProthese = cumulReserveProthese + ancienneProthese.getReserveProthese();	
				}
			}
		}
		
		mouvement.setCumulReserveProthese(cumulReserveProthese);
		
		}
		this.addResultItem(mouvement);
	}

	

}
