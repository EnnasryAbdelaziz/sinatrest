package eai.devass.sinistreat.appli.usecase.metier.recours;

import java.util.HashMap;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.modele.metier.contentieux.Recours;
import eai.devass.sinistreat.appli.usecase.BaseUC;

public class RecupererRecoursEncoursUC extends BaseUC{

	protected void executerUC(IValueObject entite, HashMap params)
			throws Exception {
		List listRecours = null;
		Recours recours = (Recours) this.getItem(Recours.class);
		listRecours = (List)recoursManager.rechercheRecoursEncours(recours);
		if(!listRecours.isEmpty() && listRecours.size()!= 1){
			throw new  FonctionnelleException(
					EXP_NOMBRE_RECOURS_ENCOURS);
		}
		addResultItem(listRecours);
	}
	public boolean isTransactionnal() {
		return true;
	}
}
