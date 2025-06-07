package eai.devass.sinistreat.appli.usecase.ihm;


import java.util.HashMap;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;

import org.hibernate.exception.ConstraintViolationException;

import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.usecase.BaseUC;

public class InitConsultationSinistreUC extends BaseUC  {
	
	
	
	protected void executerUC(IValueObject entite, HashMap params) throws Exception {
		
		// Récupérer la class de l'objet BO correspondant à l'objet VO
			
		try {			

			List listSituation= (List)parametrageManager.getlistSituation(null);
			this.addResultItem(listSituation);		
			List listNationalite= (List)parametrageManager.getListNationalite(null);
			this.addResultItem(listNationalite);	
			
		} catch (ConstraintViolationException e) {
			throw new FonctionnelleException(e);
			
		} catch (Exception e) {
			throw new FonctionnelleException(e.getMessage());
		}
		
		
	}
	
	public boolean isTransactionnal() {
		return false;
	}
	
	

}

