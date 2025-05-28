package eai.devass.gsr.appli.reglegestion.creation;

import java.util.List;
import java.util.Map;

import eai.devass.commun.appli.exeptions.ExceptionMetier;
import eai.devass.commun.appli.modele.EntiteBO;
import eai.devass.gsr.appli.modele.metier.mouvements.MvtRachat;
import eai.devass.gsr.appli.modele.metier.reglement.QuittanceGsr;
import eai.devass.gsr.appli.modele.parametrage.NatureQtcGsr;
import eai.devass.gsr.appli.prm.EnumOrigineRachat;
import eai.devass.gsr.appli.prm.NatureQuittance;
import eai.devass.sinistreat.appli.utils.Message.IMessage;

 


public class MvtRachatRG extends MouvementRG       {


	/**
	 * RG9/FGSR-10.1 : Générer la quittance en fonction du montant du complément rachat.
	 * @param entiteBO
	 * @param params
	 * @throws ExceptionMetier
	 */
	public void regleGestion000ComplementRachat(EntiteBO entiteBO, Map params) throws ExceptionMetier {
		
		MvtRachat rachat = (MvtRachat) entiteBO;
		Double complement = rachat.getMntDiff();
		List<QuittanceGsr> listQuittance = rachat.getRefsQuittance();
//		QuittanceGsr quittanceGsrJuje = listQtc.get(0);
		/*
		 * 
		 * Générer 2 quittances
		 *		Quittance Q1 avec le montant du capital jugé
		 *      Quittance Q2 avec le montant des arrérages
		 *		Q1-Q2 est le montant à régler au rentier
		 */
		
		
		
		if(rachat.getRefOrigineRachat().getId()==EnumOrigineRachat.origineConciliation.getId()){
			
			rachat.setGenererQuittance(true);
			if(rachat.getCapitalJuge()!= null && rachat.getCapitalJuge()>=0){
				
				for(QuittanceGsr quittanceGsr:listQuittance){
					
					NatureQuittance nature = getNatureQuittance(rachat);
					NatureQtcGsr natureQtc = new NatureQtcGsr(nature.getId());
					quittanceGsr.setRefNatureQuittance(natureQtc);
					quittanceGsr.setCodePrestation(nature.getRubrique());
	
			}
  
			}
			
		}else{
				if(complement != null && complement.doubleValue() >= 0){
					rachat.setGenererQuittance(true);
		
				for(QuittanceGsr quittanceGsr:listQuittance){
			
						NatureQuittance nature = getNatureQuittance(rachat);
						NatureQtcGsr natureQtc = new NatureQtcGsr(nature.getId());
						quittanceGsr.setRefNatureQuittance(natureQtc);
						quittanceGsr.setCodePrestation(nature.getRubrique());
		
				}
					
					
					
		//     		quittanceGsrJuje.setMontant(rachat.getCapitalJuge());
		//			QuittanceGsr quittanceGsrPercu = new QuittanceGsr();
		//			try {
		//				BeanUtilsBean.getInstance().copyProperties(quittanceGsrPercu, quittanceGsrJuje);
		//				
		//			} catch(Exception e) {
		//				throw new ExceptionMetier(
		//						IMessageException.EXP_QUITTANCE_MNT_PERCU);
		//			}
		//			quittanceGsrPercu.setMontant(rachat.getMntPercu());
		//			quittanceGsrPercu.setRefNatureQuittance(new NatureQtcGsr(
		//					NatureQuittance.rachat_apres_constitution.getId()));
		//			listQtc.add(quittanceGsrPercu);
					
					
				}else{
					rachat.setGenererQuittance(false);
				//	throw new ExceptionMetier(IMessageException.EXP_COMPLEMENT_RACHAT_NULL);
		
		//			Map<String, IMessageItem> message = (Map<String, IMessageItem>) params
		//				.get("outMapMessage");
		//			message.put("001", new ErrorMessageItem(IMessageException.EXP_TROP_PRECU_NEGATIF));
			
					((Map) params.get("outMapMessage")).put(IMessage.INFO,IMessage.EXP_TROP_PRECU_NEGATIF );
			}
		}

	}

	public NatureQuittance getNatureQuittance(MvtRachat mouvement){
		
		NatureQuittance natureQuittance = null;
			
		if(mouvement.getRefOrigineRachat().getId()== EnumOrigineRachat.origineCompagnie.getId()){
			
			natureQuittance = NatureQuittance.rachat_apres_constitution_recours_compagnie;

		}
		
		if(mouvement.getRefOrigineRachat().getId()== EnumOrigineRachat.origineVictime.getId()){
			
			natureQuittance = NatureQuittance.rachat_apres_constitution_recours_victime;
		
		}
		
		if(mouvement.getRefOrigineRachat().getId()== EnumOrigineRachat.origineConciliation.getId()){
			
			natureQuittance = NatureQuittance.rachat_apres_constitution_recours_victime;
		
		}
	
		return natureQuittance;

	}
public void noRegleGestion001ComplementRachat(EntiteBO entiteBO, Map params) throws ExceptionMetier {
		
//		MvtRachat rachat = (MvtRachat) entiteBO;
//		List<QuittanceGsr> listQtc = rachat.getRefsQuittance();
//		for(QuittanceGsr quittanceGsr:listQtc){
//		}
	}
}

