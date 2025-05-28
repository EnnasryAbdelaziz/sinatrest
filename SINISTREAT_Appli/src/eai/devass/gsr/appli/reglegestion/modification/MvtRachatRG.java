package eai.devass.gsr.appli.reglegestion.modification;

import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import eai.devass.commun.appli.exeptions.ExceptionMetier;
import eai.devass.commun.appli.modele.EntiteBO;
import eai.devass.commun.appli.rg.ASkipRG;
import eai.devass.gsr.appli.modele.metier.mouvements.MvtRachat;
import eai.devass.gsr.appli.modele.metier.reglement.QuittanceGsr;
import eai.devass.gsr.appli.modele.parametrage.EtatQtc;
import eai.devass.gsr.appli.modele.parametrage.NatureQtcGsr;
import eai.devass.gsr.appli.modele.parametrage.SituationQuittanceGsr;
import eai.devass.gsr.appli.prm.EnumOrigineRachat;
import eai.devass.gsr.appli.prm.EtatQuittance;
import eai.devass.gsr.appli.prm.NatureQuittance;
import eai.devass.gsr.appli.utile.IMessageException;
import eai.devass.sinistreat.appli.utils.Message.IMessage;



public class MvtRachatRG extends MouvementRG       {
	
	
	@ASkipRG(property="refOrigineRachat.id", value="3" )
public void regleGestion000AnnulerPreQuittanceGsr(EntiteBO entiteBO, Map params) throws ExceptionMetier {
		
	   MvtRachat mouvement = (MvtRachat) entiteBO;	
	   Double complement = null;
	   try {
		 complement = mouvement.getMntDiff();
		} catch (Exception e) {
			throw new ExceptionMetier(IMessageException.EXP_COMPLEMENT_RACHAT_NULL);
			
		}
		//Si le comlement est positif ne pas annuler la quittance.
		if(complement >= 0)  {
			return ;
		}
		//vérifier les info de qtc et de reglement
		List<QuittanceGsr> listQtc = mouvement.getRefsQuittance();
		if(commonGsrUtils.isEmpty(listQtc)) {
			return ;
		}
		QuittanceGsr quittanceGsrDB = null;
		for(QuittanceGsr quittanceGsr : listQtc) {
		try {
			quittanceGsrDB = quittanceManager.getQuittanceByID(quittanceGsr.getId());
		} catch (Exception e) {
			throw new ExceptionMetier("Impossible de trouver la quittance");
			
		}
		SituationQuittanceGsr situationQuittance = new SituationQuittanceGsr();
		EtatQtc refEtatQtc=new EtatQtc();
		
		situationQuittance.setDateEtat(new GregorianCalendar().getTime());
		refEtatQtc.setId(EtatQuittance.Annulee.getId());
		refEtatQtc.setDateCreation(new GregorianCalendar());
		situationQuittance.setRefEtatQtc(refEtatQtc);
		situationQuittance.setOperateur(mouvement.getOperateur());
		situationQuittance.setRefQuittanceGsr(quittanceGsrDB);
	
		quittanceGsrDB.setDatEtat(new GregorianCalendar());
		quittanceGsrDB.setRefEtatQtc(refEtatQtc);

		getSession().save(situationQuittance);
		}
			
	}
	
/**
 * RG9/FGSR-10.1 : Générer la quittance en fonction du montant du complément rachat.
 * @param entiteBO
 * @param params
 * @throws ExceptionMetier
 */
public void regleGestion001ComplementRachat(EntiteBO entiteBO, Map params) throws ExceptionMetier {
	
	MvtRachat rachat = (MvtRachat) entiteBO;
	Double complement = rachat.getMntDiff();
	List<QuittanceGsr> listQuittance = rachat.getRefsQuittance();
//	QuittanceGsr quittanceGsrJuje = listQtc.get(0);
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
				
				NatureQuittance nature = new eai.devass.gsr.appli.reglegestion.creation.MvtRachatRG().getNatureQuittance(rachat);
				NatureQtcGsr natureQtc = new NatureQtcGsr(nature.getId());
				quittanceGsr.setRefNatureQuittance(natureQtc);
				quittanceGsr.setCodePrestation(nature.getRubrique());

		}

		}
		
	}else{
			if(complement>=0){
				rachat.setGenererQuittance(true);
				
				for(QuittanceGsr quittanceGsr:listQuittance){
					
					NatureQuittance nature = getNatureQuittance(rachat);
					NatureQtcGsr natureQtc = new NatureQtcGsr(nature.getId());
					quittanceGsr.setRefNatureQuittance(natureQtc);
					quittanceGsr.setCodePrestation(nature.getRubrique());
		
			}
				
		// 		quittanceGsrJuje.setMontant(rachat.getCapitalJuge());
		//		QuittanceGsr quittanceGsrPercu = new QuittanceGsr();
		//		try {
		//			BeanUtilsBean.getInstance().copyProperties(quittanceGsrPercu, quittanceGsrJuje);
		//			
		//		} catch(Exception e) {
		//			throw new ExceptionMetier(
		//					IMessageException.EXP_QUITTANCE_MNT_PERCU);
		//		}
		//		quittanceGsrPercu.setMontant(rachat.getMntPercu());
		//		quittanceGsrPercu.setRefNatureQuittance(new NatureQtcGsr(
		//				NatureQuittance.rachat_apres_constitution.getId()));
		//		listQtc.add(quittanceGsrPercu);
				
				
			}else{
				rachat.setGenererQuittance(false);
				((Map) params.get("outMapMessage")).put(IMessage.INFO,IMessage.EXP_TROP_PRECU_NEGATIF );
		}
	}
}

	private NatureQuittance getNatureQuittance(MvtRachat mouvement){
		
		NatureQuittance natureQuittance = null;
			
		if(mouvement.getRefOrigineRachat().getId()== EnumOrigineRachat.origineCompagnie.getId()){
			
			natureQuittance = NatureQuittance.rachat_apres_constitution_recours_compagnie;
	
		}
		
		if(mouvement.getRefOrigineRachat().getId()== EnumOrigineRachat.origineVictime.getId()){
			
			natureQuittance = NatureQuittance.rachat_apres_constitution_recours_victime;
		
		}
	
		return natureQuittance;
	
	}
	
	

}

