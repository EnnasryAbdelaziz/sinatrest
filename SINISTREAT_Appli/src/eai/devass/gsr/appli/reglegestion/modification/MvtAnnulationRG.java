/**
 * 
 */
package eai.devass.gsr.appli.reglegestion.modification;

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
			
			getSession().saveOrUpdate(mouvement);

		if(rentierMvt.getRefRentier()!= null && rentierMvt.getRefRentier().getId()!=0){
		
				getSession().saveOrUpdate(rentierMvt);
			}
	}
		
		
		
		
		
		
/*		MvtAnnulation mouvement = (MvtAnnulation)entiteBO;
		MvtAnnulation mvtDB =null;
		RentierMvtManager rentierMvtManager = new RentierMvtManager();
		
		try {
			Class clazz = Class.forName("eai.devass.gsr.appli.modele.metier.mouvements.MvtAnnulation");
			mvtDB = (MvtAnnulation)getSession().get(clazz, mouvement.getId());
			

			//mouvementManager.updateMotifAnnulation(mouvement.getId(),mouvement.getMotifAnnulation());
			
		//	mouvementManager.updateIdMvtAnnulee(mvtDB.getRefMvtAnnule().getId(),mouvement.getId());

		mvtDB.setMotifAnnulation(mouvement.getMotifAnnulation());
		
		
		List<RentierMvt> listeMvtRentier = mouvement.getRefsRentierMvt() ;
		
		for(RentierMvt rentierMvt:listeMvtRentier){

			rentierMvt.setRefMouvement(mouvement);

		if(rentierMvt.getRefRentier()!= null && rentierMvt.getRefRentier().getId()!=0){
		
				getSession().saveOrUpdate(rentierMvt);
			}
	
		
	}*/
		
		
			
			
	/*		
		

//		Long idLastMvt =null;
//		
//		try {
//			 idLastMvt = mouvementManager.getLastIDMouvementValidee(mouvement.getRefRentier().getId());
//		} catch (FonctionnelleException e) {
//			throw new ExceptionMetier("id mouvement introuvable");
//		}
//		
//		if(idLastMvt!=null){
//			
//			mouvement.setRefMvtAnnule(new Mouvement(idLastMvt));
//		}
		
			List<RentierMvt> listeMvtRentier = mouvement.getRefsRentierMvt() ;
		

		
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
			
			getSession().saveOrUpdate(mouvement);

		if(rentierMvt.getRefRentier()!= null && rentierMvt.getRefRentier().getId()!=0){
		
				getSession().saveOrUpdate(rentierMvt);
			}
	
		
	}
		
	*/

	}
}
