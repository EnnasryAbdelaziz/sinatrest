package eai.devass.sinistreat.appli.usecase.metier.reglement;

import java.util.HashMap;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.modele.metier.reglement.EtatReglement;
import eai.devass.sinistreat.appli.usecase.BaseUC;
import eai.devass.sinistreat.appli.valueobjects.metier.reglement.ReglementVO;

@SuppressWarnings("all") 
public class ListEtatReglementUC extends BaseUC {

	
	protected void executerUC(IValueObject entite,HashMap params)
			throws Exception {

		ReglementVO rgl = (ReglementVO) entite;
		List<EtatReglement> listEtat = null;

		try {
			listEtat = reglementManager.getListEtatReglement(rgl);
		} catch (Exception e) {
			throw new FonctionnelleException(e.getMessage());
		}

		addResultItem(listEtat);

	}

}
