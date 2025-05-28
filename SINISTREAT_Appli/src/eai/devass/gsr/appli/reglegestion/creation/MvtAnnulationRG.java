/**
 * 
 */
package eai.devass.gsr.appli.reglegestion.creation;

import java.util.List;
import java.util.Map;

import eai.devass.commun.appli.exeptions.ExceptionMetier;
import eai.devass.commun.appli.modele.EntiteBO;
import eai.devass.gsr.appli.modele.metier.mouvements.Mouvement;
import eai.devass.gsr.appli.modele.metier.mouvements.MvtAnnulation;
import eai.devass.gsr.appli.modele.metier.mouvements.RentierMvt;
import eai.devass.sinistreat.appli.exception.FonctionnelleException;



/**
 * @author elfaismo
 *
 */
public class MvtAnnulationRG extends MouvementRG {
	
	public void regleGestion993Annulation(EntiteBO entiteBO, Map params) throws ExceptionMetier {
	
		MvtAnnulation mouvement = (MvtAnnulation)entiteBO;
		
		Long idLastMvt =null;
		
		try {
			 idLastMvt = mouvementManager.getLastIDMouvementValidee(mouvement.getRefRentier().getId());
		} catch (FonctionnelleException e) {
			throw new ExceptionMetier("id mouvement introuvable");
		}
		
			List<RentierMvt> listeMvtRentier = mouvement.getRefsRentierMvt() ;
		
		if(idLastMvt!=null){
			
			mouvement.setRefMvtAnnule(new Mouvement(idLastMvt));
		}
		
	//	String idRentier =String.valueOf(listeMvtRentier.get(0).getRefRentier().getId());
		
		//Mouvement refMouvement = mouvementManager.getDernierMouvementValidee(idRentier);
		
		for(RentierMvt rentierMvt:listeMvtRentier){

			
			//Rentier rentier = rentierMvt.getRefRentier();
			rentierMvt.setRefMouvement(mouvement);
//			
//			
//			if (rentier == null || rentier.getId() <= 0) {
//				throw new ExceptionMetier(IMessageException.EXP_RENTIER_INTROUVALE);
//			}
//
//			Rentier rentierBD = rentierManager.getRentierByID(rentier.getId());
			
			getSession().save(mouvement);

		if(rentierMvt.getRefRentier()!= null && rentierMvt.getRefRentier().getId()!=0){
		
				getSession().save(rentierMvt);
			}
	}
		
		
	}
}
