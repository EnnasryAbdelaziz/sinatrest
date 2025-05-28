package eai.devass.gsr.appli.reglegestion.creation;

import java.util.List;
import java.util.Map;

import ma.co.omnidata.framework.services.entites.EntiteException;
import eai.devass.commun.appli.exeptions.ExceptionMetier;
import eai.devass.commun.appli.modele.EntiteBO;
import eai.devass.commun.appli.rg.ASkipRG;
import eai.devass.gsr.appli.modele.metier.mouvements.MvtRemariage;
import eai.devass.gsr.appli.modele.metier.reglement.QuittanceGsr;
import eai.devass.gsr.appli.modele.parametrage.NatureQtcGsr;
import eai.devass.gsr.appli.prm.NatureQuittance;


public class MvtRemariageRG extends MouvementRG {

	
	@ASkipRG(property="vofEnfants", value="false", isEmty="true")
	public void regleGestion002EnfantACharge(EntiteBO entiteBO, Map params)
			throws ExceptionMetier, EntiteException {

		//correction sonar Dead store to local variable.
		//MvtRemariage mouvement = (MvtRemariage) entiteBO;

	}
	
	@ASkipRG(property="vofEnfants", value="true")
	public void regleGestion003AucunEnfantACharge(EntiteBO entiteBO, Map params)
			throws ExceptionMetier, EntiteException {

		MvtRemariage mouvement = (MvtRemariage) entiteBO;
		
		// Il faut generer la quittance de reglement
		List<QuittanceGsr> listQtc = mouvement.getRefsQuittance();
		if(commonGsrUtils.isEmpty(listQtc)) {
			mouvement.setGenererQuittance(false);
			return;
		}
		
		QuittanceGsr quittanceGsr = listQtc.get(0);
		if(quittanceGsr.getRefModeReglement() == null) {
			mouvement.setGenererQuittance(false);
			return;
		}
		
	}
	
	@ASkipRG(property="genererQuittance", value="false", isEmty="true")
	public void regleGestion004NatureQuittance(EntiteBO entiteBO, Map params)
			throws ExceptionMetier, EntiteException {

		MvtRemariage mouvement = (MvtRemariage) entiteBO;
		
		// Specifier la nature quittance
		List<QuittanceGsr> listQtc = mouvement.getRefsQuittance();
		QuittanceGsr quittanceGsr = listQtc.get(0);
		quittanceGsr.setRefNatureQuittance(new NatureQtcGsr(
				NatureQuittance.Capital_remariage.getId()));
		
	}
	
	

	
}
