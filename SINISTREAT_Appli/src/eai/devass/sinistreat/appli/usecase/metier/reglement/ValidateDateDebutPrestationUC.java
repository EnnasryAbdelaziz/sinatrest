package eai.devass.sinistreat.appli.usecase.metier.reglement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.modele.metier.reglement.Reglement;
import eai.devass.sinistreat.appli.usecase.BaseUC;

@SuppressWarnings("all")
public class ValidateDateDebutPrestationUC extends BaseUC {

	protected void executerUC(IValueObject entite, HashMap params)
			throws Exception {
		Reglement rgl = new Reglement();
		try {
			List listRgl = getItems(Reglement.class);
			List listResult = new ArrayList();
			if (listRgl != null && !listRgl.isEmpty()) {
				for (Iterator iterator = listRgl.iterator(); iterator.hasNext();) {
					Reglement reglement = (Reglement) iterator.next();
					reglementManager.validateDateDebutPrestation(reglement);
				}
			}
		}

		catch (Exception e) {
			throw new FonctionnelleException(e.getMessage(), new String[0]);
		}

		addResultItem(rgl);
	}

	public boolean isTransactionnal() {
		return true;
	}

}
