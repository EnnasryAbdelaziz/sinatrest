package eai.devass.gsr.appli.reglegestion.modification;

import java.util.List;
import java.util.Map;

import ma.co.omnidata.framework.services.entites.EntiteException;
import eai.devass.commun.appli.exeptions.ExceptionMetier;
import eai.devass.commun.appli.modele.EntiteBO;
import eai.devass.commun.appli.rg.ASkipRG;
import eai.devass.gsr.appli.modele.metier.mouvements.Mouvement;
import eai.devass.gsr.appli.modele.metier.reglement.QuittanceGsr;
import eai.devass.gsr.appli.modele.parametrage.SituationQuittanceGsr;
import eai.devass.gsr.appli.prm.EtatQuittance;


public class MvtRemariageRG extends MouvementRG {
	
	
	private eai.devass.gsr.appli.reglegestion.creation.MvtRemariageRG mvtRemariageRGCReation = 
				new eai.devass.gsr.appli.reglegestion.creation.MvtRemariageRG();
	
	@ASkipRG(property="vofEnfants", value="false", isEmty="true")
	public void regleGestion002EnfantACharge(EntiteBO entiteBO, Map params)
			throws ExceptionMetier, EntiteException {

		mvtRemariageRGCReation.regleGestion002EnfantACharge(entiteBO, params);

	}
	
	@ASkipRG(property="vofEnfants", value="true")
	public void regleGestion003AucunEnfantACharge(EntiteBO entiteBO, Map params)
			throws ExceptionMetier, EntiteException {

		mvtRemariageRGCReation.regleGestion003AucunEnfantACharge(entiteBO, params);
		
	}
	
	@ASkipRG(property="genererQuittance", value="false", isEmty="true")
	public void regleGestion004NatureQuittance(EntiteBO entiteBO, Map params)
			throws ExceptionMetier, EntiteException {

		mvtRemariageRGCReation.regleGestion004NatureQuittance(entiteBO, params);
		
	}
	
	@ASkipRG(property="genererQuittance", value="true")
	public void regleGestion004AnnulerPreQuittanceGsr(EntiteBO entiteBO, Map params) throws ExceptionMetier {
		
		// Dans le cas ou la quittance GSR est creer, et dans la modification, 
		// on veut la supprimer (annuler).
		Mouvement mouvement = (Mouvement) entiteBO;
				
		//vérifier si le mouvement a deja une quitatnce GSR creer
		List<QuittanceGsr> listQtc = null;
		try {
			listQtc = quittanceManager.getListQuittanceNotAnnuler(mouvement);
			
			
		} catch(Exception e) {
			throw new ExceptionMetier("Impossible de trouver la dérnier quittance GSR");
		}
		
		if(commonGsrUtils.isEmpty(listQtc)) {
			return;
		}
		
		SituationQuittanceGsr situationQuittanceGsr = null;
		for(QuittanceGsr curQuittanceGsr : listQtc) {
		
			// Si non, on doit l'annuller !!!!!
			situationQuittanceGsr = curQuittanceGsr
					.getCurSituationQuittanceGsr(EtatQuittance.Annulee);
			situationQuittanceGsr.setOperateur(entiteBO.getOperateur());
			getSession().save(situationQuittanceGsr);	
		}
		
	}
	
	
	
}
