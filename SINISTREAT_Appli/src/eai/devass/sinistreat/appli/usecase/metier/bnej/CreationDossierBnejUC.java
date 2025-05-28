package eai.devass.sinistreat.appli.usecase.metier.bnej;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import eai.devass.sinistreat.appli.modele.metier.bnej.DossierBnej;
import eai.devass.sinistreat.appli.usecase.BaseUC;

public class CreationDossierBnejUC extends BaseUC {

	@SuppressWarnings("rawtypes")
	@Override
	protected void executerUC(IValueObject entite, HashMap params)
			throws Exception {
		List listDossierBnej = getItems(DossierBnej.class);
		if (listDossierBnej != null && !listDossierBnej.isEmpty()) {
			for (Iterator iterator = listDossierBnej.iterator(); iterator
					.hasNext();) {
				DossierBnej dossierBnej = (DossierBnej) iterator.next();
				bnejManager.creationDossierBnej(dossierBnej);
				this.addResultItem(dossierBnej);
			}
		}
	}

	public boolean isTransactionnal() {
		return true;
	}

}
