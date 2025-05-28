package eai.devass.sinistreat.appli.usecase.ihm.contentieux;


import java.util.HashMap;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;

import org.hibernate.exception.ConstraintViolationException;

import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.usecase.BaseUC;

public class InitCreationRecoursEntrantUC extends BaseUC  {
	
	
	
	@SuppressWarnings("rawtypes")
	protected void executerUC(IValueObject entite, HashMap params) throws Exception {
		
		// Récupérer la class de l'objet BO correspondant à l'objet VO
			
		try {			
			List listTypeJuridiction = (List)parametrageManager.getListTypeJuridiction(null);
			this.addResultItem(listTypeJuridiction);	
			List listNatuerDossier = (List)parametrageManager.getListNatureDossier(null);
			this.addResultItem(listNatuerDossier);		
			List listNatureProcedure= (List)parametrageManager.getListNatureProcedure(null);
			this.addResultItem(listNatureProcedure);
			List listGestionnaire = (List)  parametrageManager.getListGestionnaireRelance(null);
			this.addResultItem(listGestionnaire);
		

			
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

