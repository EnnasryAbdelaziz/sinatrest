package eai.devass.sinistreat.appli.usecase.metier.conciliation;

import java.util.HashMap;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import eai.devass.sinistreat.appli.modele.metier.conciliation.Conciliation;
import eai.devass.sinistreat.appli.usecase.BaseUC;

public class CreerConciliationUC extends BaseUC {

	protected void executerUC(IValueObject entite, HashMap params)
			throws Exception {

		Conciliation conciliation = (Conciliation) this.getItem(Conciliation.class);
		
		// Creer Conciliation
		conciliationManager.creerConciliation(conciliation);
		// Fin
		
		// Historisation Etat Conciliation
		conciliationManager.addEtatConciliation(conciliation);
		// Fin
		
		// Historisation Motif Offre
		conciliationManager.addEtatMotifOffre(conciliation);
		// Fin
		
		addResultItem(null);

	}

	public boolean isTransactionnal() {
		return true;
	}
}
