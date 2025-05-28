package eai.devass.sinistreat.appli.usecase.metier.sinistre;

import java.util.HashMap;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.modele.metier.sinistre.EtatSinistre;
import eai.devass.sinistreat.appli.usecase.BaseUC;
import eai.devass.sinistreat.appli.valueobjects.metier.sinistre.SinistreVO;

public class ListEtatSinistreUC extends BaseUC{

	@Override
	protected void executerUC(IValueObject entite, HashMap params)
			throws Exception {
		SinistreVO sin = (SinistreVO) entite;
		List<EtatSinistre> listEtat = null;
		
	try{
		listEtat = sinistreManager.getListEtatSinistre(
				sin);	
		} catch (Exception e) {
			throw new FonctionnelleException(e.getMessage());
		}

		addResultItem(listEtat);
		
	}
	

}
