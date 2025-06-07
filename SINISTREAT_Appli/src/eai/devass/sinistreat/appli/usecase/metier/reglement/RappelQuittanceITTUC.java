package eai.devass.sinistreat.appli.usecase.metier.reglement;

import java.util.HashMap;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.modele.metier.reglement.Reglement;
import eai.devass.sinistreat.appli.usecase.BaseUC;

public class RappelQuittanceITTUC extends BaseUC {
	public RappelQuittanceITTUC() {
	}
	protected void executerUC(IValueObject entite, HashMap params)
			throws Exception {
		try {
			Reglement rgl = (Reglement) getItem(Reglement.class);
			rgl.setRappel(Boolean.TRUE);
			rgl = reglementManager.modifierReglement(rgl);
			reglementManager.addHistoriqueDateRappel(rgl);
			addResultItem(null);
		} catch (Exception e) {
			throw new FonctionnelleException(e.getMessage(), new String[0]);
		}
	}

	public boolean isTransactionnal() {
		return true;
	}
}
