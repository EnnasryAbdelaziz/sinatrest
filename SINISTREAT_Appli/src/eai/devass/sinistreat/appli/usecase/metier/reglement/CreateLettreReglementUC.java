package eai.devass.sinistreat.appli.usecase.metier.reglement;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.modele.metier.reglement.LettreReglement;
import eai.devass.sinistreat.appli.modele.metier.reglement.Reglement;
import eai.devass.sinistreat.appli.modele.metier.sinistre.Sinistre;
import eai.devass.sinistreat.appli.usecase.BaseUC;
import eai.devass.sinistreat.appli.utils.IConstantes;

@SuppressWarnings("all")
public class CreateLettreReglementUC extends BaseUC {

	protected void executerUC(IValueObject entite, HashMap params)
			throws Exception {
		 LettreReglement lettreRgl = new LettreReglement();
		try {
			List listRgl = getItems(Reglement.class);
			List listResult = new ArrayList();
			Boolean create = true;
			if (listRgl != null && !listRgl.isEmpty()) {
				for (Iterator iterator = listRgl.iterator(); iterator.hasNext();) {
					Reglement reglement = (Reglement) iterator.next();
					reglementManager
							.EditerDateEditionIntermediaire(reglement);
					lettreRgl = reglementManager.ajouterLettreReglement(reglement , Boolean.TRUE);
					
				}
			}
		}
		catch (Exception e) {
			throw new FonctionnelleException(e.getMessage(), new String[0]);
		}
		addResultItem (lettreRgl);
	}

	public boolean isTransactionnal() {
		return true;
	}

}
