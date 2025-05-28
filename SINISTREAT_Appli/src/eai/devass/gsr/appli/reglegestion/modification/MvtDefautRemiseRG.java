package eai.devass.gsr.appli.reglegestion.modification;

import java.util.List;
import java.util.Map;

import ma.co.omnidata.framework.services.entites.EntiteException;
import eai.devass.commun.appli.exeptions.ExceptionMetier;
import eai.devass.commun.appli.modele.EntiteBO;
import eai.devass.commun.appli.rg.ASkipRG;
import eai.devass.gsr.appli.modele.metier.mouvements.MvtDefautRemise;
import eai.devass.gsr.appli.modele.metier.reglement.QuittanceGsr;
import eai.devass.gsr.appli.modele.parametrage.NatureQtcGsr;
import eai.devass.gsr.appli.modele.parametrage.SituationQuittanceGsr;
import eai.devass.gsr.appli.prm.NatureQuittance;

	

public class MvtDefautRemiseRG extends MouvementRG {
	
	
	private static final String ACTION_SUSPENDRERENTIER = "3";
	//private static final String ACTION_REGLER = "1";
	
	
	@ASkipRG(property="genererQuittance", value="false", isEmty="true")
	public void regleGestion004NatureQuittance(EntiteBO entiteBO, Map params)
			throws ExceptionMetier, EntiteException {

		MvtDefautRemise mouvement = (MvtDefautRemise) entiteBO;
		
		// Specifier la nature quittance
		List<QuittanceGsr> listQtc = mouvement.getRefsQuittance();
		if(commonGsrUtils.isEmpty(listQtc)) {
			throw new ExceptionMetier(
					"La liste des quittances est obligatoire pour définir la Nature Quittance");
		}
		
		QuittanceGsr quittanceGsr = listQtc.get(0);
		quittanceGsr.setRefNatureQuittance(new NatureQtcGsr(
				NatureQuittance.Rente_periodique.getId()));
		
	}
	
	public void regleGestion005AnnulerPreRgl(EntiteBO entiteBO, Map params)
			throws ExceptionMetier, EntiteException {

		MvtDefautRemise mouvement = (MvtDefautRemise) entiteBO;
				
		// Cas reglement QUITTANCEs
		if(!ACTION_SUSPENDRERENTIER.equals(mouvement.getAction())) {
			return;
		}
		
		// IL faut verifier si le mouvement a des prereglement !!!, 
		// on a changer l'action de 1(regler) ==> 3(suspendre)
		MvtDefautRemise mvtDefautRemise = (MvtDefautRemise) getSession().get(
				MvtDefautRemise.class, mouvement.getId());
		List<QuittanceGsr> listQtc = mvtDefautRemise.getRefsQuittance();
		if(commonGsrUtils.isEmpty(listQtc)) {
			return;
		}
		
		// Supprimer physiquement la quittance et ces situations !!!
		QuittanceGsr quittanceGsr = listQtc.get(0);
		List<SituationQuittanceGsr> listSituationQuittanceGsrs = quittanceGsr.getRefSituationQuittanceGsr();
		for(SituationQuittanceGsr curSituationQuittanceGsr : listSituationQuittanceGsrs) {
			getSession().delete(curSituationQuittanceGsr);
		}
		
		getSession().delete(quittanceGsr);
		getSession().evict(mvtDefautRemise);
	}
	
	
}
