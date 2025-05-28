package eai.devass.sinistreat.appli.usecase.ihm.contentieux;


import java.util.HashMap;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;

import org.hibernate.exception.ConstraintViolationException;

import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.usecase.BaseUC;

public class InitModificationAudienceUC extends BaseUC  {
	
	
	
	@SuppressWarnings("rawtypes")
	protected void executerUC(IValueObject entite, HashMap params) throws Exception {
		
		// Récupérer la class de l'objet BO correspondant à l'objet VO
			
		try {			
			List listNatureAudience= (List)parametrageManager.getListNatureAudience(null);
			this.addResultItem(listNatureAudience);		
			List listeNatureDecision = (List)parametrageManager.getListNatureDecision(null);
			this.addResultItem(listeNatureDecision);			
			List listSuiteJugement= (List)parametrageManager.getListSuiteJugement(null);
			this.addResultItem(listSuiteJugement);	
			List listSortJugement= (List)parametrageManager.getListSortJugement(null);
			this.addResultItem(listSortJugement);
			List listTiersSaisi= (List)parametrageManager.getListTiersSaisi(null);
			this.addResultItem(listTiersSaisi);
			List listBanque= (List)parametrageManager.getListBanque(null);
			this.addResultItem(listBanque);
			List listTypeRente= (List)parametrageManager.getListTypeRente(null);
			this.addResultItem(listTypeRente);
			List listCieCondamnee= (List)parametrageManager.getListCieCondamnee(null);
			this.addResultItem(listCieCondamnee);
			List listGestionnaireTraitement= (List)parametrageManager.getListGestionnaireTraitement(null);
			this.addResultItem(listGestionnaireTraitement);
		} catch (ConstraintViolationException e) {
			throw new FonctionnelleException(e);
			
		} catch (Exception e) {
			throw new FonctionnelleException(e.getMessage());
		}
		
		
	}
	
	public boolean isTransactionnal() {
		return true;
	}
	
	

}

