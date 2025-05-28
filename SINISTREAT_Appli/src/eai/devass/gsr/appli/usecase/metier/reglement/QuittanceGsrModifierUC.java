package eai.devass.gsr.appli.usecase.metier.reglement;
import java.util.GregorianCalendar;
import java.util.HashMap;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import eai.devass.commun.appli.uc.SimpleBaseUC;
import eai.devass.gsr.appli.businessrule.ModeReglementBR;
import eai.devass.gsr.appli.modele.metier.reglement.Prerglt;
import eai.devass.gsr.appli.modele.metier.reglement.QuittanceGsr;

public class QuittanceGsrModifierUC extends SimpleBaseUC {


	protected void execute(IValueObject entite, HashMap params) throws Exception {
		
		QuittanceGsr quittanceGsr = (QuittanceGsr) getItem(QuittanceGsr.class);
		ModeReglementBR modeReglementBR = new ModeReglementBR(quittanceGsr);
		modeReglementBR.valideModeReglement();
		
		// Mettre à jour le pre-reglement
		Prerglt prerglt = quittanceGsr.getRefPrerglt();
		if(prerglt != null) {
		prerglt.setDateCreation(new GregorianCalendar());
		
			if(prerglt.getId() != 0) {
				getSession().merge(prerglt);
				
			} else {
				getSession().save(prerglt);
			}
		}
		
		// Mettre à jour la quittance !!!
		getSession().merge(quittanceGsr);
		

	}

	public boolean isTransactionnal() {
		return true;
	}

}


