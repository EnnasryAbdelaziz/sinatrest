package eai.devass.sinistreat.appli.usecase.ihm.recours;


import java.util.HashMap;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;

import org.hibernate.exception.ConstraintViolationException;

import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.usecase.BaseUC;

public class InitRechercheRecoursSortantUC extends BaseUC  {
	
	
	
	@SuppressWarnings("rawtypes")
	protected void executerUC(IValueObject entite, HashMap params) throws Exception {
		
		// Récupérer la class de l'objet BO correspondant à l'objet VO
			
		try {			
			
			// Liste etats recours
			List listEtatRecours = (List)parametrageManager.getListeEtatRecours(null);
			this.addResultItem(listEtatRecours);
			// Liste compagnies assurances
			List listCompagnies = (List)parametrageManager.getListAssurance(null);
			this.addResultItem(listCompagnies);	
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

