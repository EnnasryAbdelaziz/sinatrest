package eai.devass.sinistreat.appli.usecase.metier.conciliation;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.modele.metier.conciliation.Conciliation;
import eai.devass.sinistreat.appli.modele.metier.conciliation.SinEditionPV;
import eai.devass.sinistreat.appli.modele.metier.reglement.LettreReglement;
import eai.devass.sinistreat.appli.modele.metier.reglement.Reglement;
import eai.devass.sinistreat.appli.modele.metier.sinistre.Sinistre;
import eai.devass.sinistreat.appli.usecase.BaseUC;
import eai.devass.sinistreat.appli.utils.IConstantes;

@SuppressWarnings("all")
public class EditionPVUC extends BaseUC {

	protected void executerUC(IValueObject entite, HashMap params)
			throws Exception {
		SinEditionPV editionPV = (SinEditionPV) this.getItem(SinEditionPV.class);
		
		editionPV = conciliationManager.creerEditionPV(editionPV);
		
		addResultItem(editionPV);
	}

	public boolean isTransactionnal() {
		return true;
	}

}
