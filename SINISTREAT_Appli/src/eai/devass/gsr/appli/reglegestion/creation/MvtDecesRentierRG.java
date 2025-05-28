/**
 * 
 */
package eai.devass.gsr.appli.reglegestion.creation;

import java.util.List;
import java.util.Map;

import eai.devass.commun.appli.exeptions.ExceptionMetier;
import eai.devass.commun.appli.modele.EntiteBO;
import eai.devass.gsr.appli.modele.metier.mouvements.Heritier;
import eai.devass.gsr.appli.modele.metier.mouvements.MvtDecesRentier;
import eai.devass.gsr.appli.modele.metier.reglement.QuittanceGsr;
import eai.devass.gsr.appli.modele.parametrage.NatureQtcGsr;
import eai.devass.gsr.appli.prm.EnumStatutHeritier;
import eai.devass.gsr.appli.prm.NatureQuittance;
import eai.devass.sinistreat.appli.utils.Message.IMessage;



/**
 * @author elfaismo
 *
 */
public class MvtDecesRentierRG extends MouvementRG {

//	public void regleGestion000AlimenterMntRente(EntiteBO entiteBO, Map params) throws ExceptionMetier {
//	
//	MvtDecesRentier lToCreate = (MvtDecesRentier) entiteBO;
//		
//	// renseigner le montant de la rente
//	lToCreate.setMntRente(rentier.getMontantRenteTrimestrielle());
//	
//}
	
	/**
	 * RG11/FGSR-10.7: Quittances d’équilibre à générer à la création du mouvement. 
	 * Le montant correspondant.
	 */
	
	
	public void regleGestion000GenererQuittanceReglement(EntiteBO entiteBO, Map params) throws ExceptionMetier {
		
		
		MvtDecesRentier mouvement = (MvtDecesRentier) entiteBO;
		List<QuittanceGsr> listQuittance = mouvement.getRefsQuittance();
		//rentier = mouvement.getRefRentier();
		
		if(mouvement.getMntGlobalVersee()>0){
			
			mouvement.setGenererQuittance(true);
			
			if(listQuittance!=null && listQuittance.size()>0){
			
				for(QuittanceGsr quittanceGsr:listQuittance){
	
					NatureQtcGsr natureQtc = new NatureQtcGsr(NatureQuittance.Prorata_rente.getId());
					quittanceGsr.setRefNatureQuittance(natureQtc);
					quittanceGsr.setCodePrestation(NatureQuittance.Prorata_rente.getRubrique());
	
					}
			}
		}else if(mouvement.getMntGlobalVersee()==0){
			mouvement.setGenererQuittance(false);
			
			((Map) params.get("outMapMessage")).put(IMessage.INFO,IMessage.MSG_GSR_MNT_VERSEE_ZERO );

		}
		else{
			mouvement.setGenererQuittance(false);
			
			((Map) params.get("outMapMessage")).put(IMessage.INFO,IMessage.MSG_GSR_TROP_PERCU );
			
			
		}

	
	}
	
	
	
	
	public void regleGestion992ViderListHeritier(EntiteBO entiteBO, Map params) throws ExceptionMetier {
		
			MvtDecesRentier mouvement = (MvtDecesRentier) entiteBO;
				List<Heritier> listHeritiers = mouvement.getRefsHeritier();
				if(commonGsrUtils.isEmpty(listHeritiers)) {
					return;
				}	
			for(Heritier heritier:listHeritiers ){
				
				
				if(heritier.getRefStatutHeritier()!= null && heritier.getRefStatutHeritier().getId()==EnumStatutHeritier.Supprimee.getId()){
				getSession().delete(heritier);
				}
				
			}	
		}
	
public void regleGestion993AlimenterListHeritier(EntiteBO entiteBO, Map params) throws ExceptionMetier {
		
	MvtDecesRentier mouvement = (MvtDecesRentier) entiteBO;
		List<Heritier> listHeritiers = mouvement.getRefsHeritier();
		if(commonGsrUtils.isEmpty(listHeritiers)) {
			return;
		}
		
		// Generation des situations quittances !!!
		
//		int i = 0;
//		
//		for(QuittanceGsr quittanceGsr : listQtc) {
//			int j =0;
//			for(Heritier heritier : listHeritiers){
//				
//				heritier.setRefQuittanceGSR(quittanceGsr);
//				getSession().saveOrUpdate(heritier);		
//			}
//	i++;
//		}
		
		if(listQtc!=null && listQtc.size()>0 ){
			for (int i = 0; i < listQtc.size(); i++) {
				//System.out.println("iteration quitance :"+ i);
				QuittanceGsr quittanceGsr = listQtc.get(i);
				
				for (int j = 0; j < listHeritiers.size(); j++) {
	//				System.out.println("iteration Heritier :"+ j);
	//				System.out.println(listHeritiers.get(i));
					
					if(i==j){
						
						Heritier heritier = listHeritiers.get(i);
						heritier.setRefQuittanceGSR(quittanceGsr);
						getSession().saveOrUpdate(heritier);
						getSession().saveOrUpdate(quittanceGsr);
						quittanceGsr = new QuittanceGsr();
						
					}
				}
			}
		}
		
	}

}
