package eai.devass.sinistreat.appli.usecase.metier.bnej;

import java.util.HashMap;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import eai.devass.sinistreat.appli.modele.metier.bnej.DossierBnej;
import eai.devass.sinistreat.appli.usecase.BaseUC;

public class ModificationDossierBnejUC extends BaseUC {

	@Override
	protected void executerUC(IValueObject entite, HashMap params)
			throws Exception {
		DossierBnej dossierBnej = (DossierBnej) this.getItem(DossierBnej.class);
		bnejManager.modificationDossierBnej(dossierBnej);
		//ADD HISTO
		bnejManager.addHistoriqueDecision(dossierBnej);

		this.addResultItem(dossierBnej);

	}

	public boolean isTransactionnal() {
		return true;
	}

}
