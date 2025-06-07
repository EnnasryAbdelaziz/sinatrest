package eai.devass.sinistreat.appli.usecase.parametrage;

import java.util.HashMap;
import ma.co.omnidata.framework.services.businessInterface.IValueObject;

import eai.devass.sinistreat.appli.modele.parametrage.Intermediaire;
import eai.devass.sinistreat.appli.usecase.BaseUC;

public class RechercheInfoIntermediaireUC extends BaseUC{
	@SuppressWarnings("unchecked")
	protected void executerUC(IValueObject entite, HashMap params)
			throws Exception {

		Intermediaire intermediaire = (Intermediaire) this.getItem(Intermediaire.class);

		intermediaire = parametrageManager.getIntermediaireByCode(intermediaire);

		addResultItem(intermediaire);
		
	}
	public boolean isTransactionnal() {
		return true;
	}
}