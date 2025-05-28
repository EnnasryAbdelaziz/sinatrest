package eai.devass.sinistreat.appli.usecase.ihm;


import java.util.HashMap;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;

import org.hibernate.exception.ConstraintViolationException;

import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.usecase.BaseUC;

public class InitCreationSinistreUC extends BaseUC  {
	
	
	
	protected void executerUC(IValueObject entite, HashMap params) throws Exception {
		
		// Récupérer la class de l'objet BO correspondant à l'objet VO
			
		try {			

			List listTypeAccident = (List)parametrageManager.getListTypeAccident(null);
			this.addResultItem(listTypeAccident);
			List listGarantie = (List)parametrageManager.getListGarantie(null);
			this.addResultItem(listGarantie);	
			List listZone = (List)parametrageManager.getListZone(null);
			this.addResultItem(listZone);		
			List listCause= (List)parametrageManager.getListCause(null);
			this.addResultItem(listCause);					
			List listVille = (List)parametrageManager.getListVille(null);
			this.addResultItem(listVille);
			List listTypeMaldie = (List)parametrageManager.getListTypeMaladie(null);
			this.addResultItem(listTypeMaldie);	
			List listPoliceUniv = (List)parametrageManager.getlistPoliceUnivers(null);
			this.addResultItem(listPoliceUniv);					
			List listSituation= (List)parametrageManager.getlistSituation(null);
			this.addResultItem(listSituation);		
			List listNationalite= (List)parametrageManager.getListNationalite(null);
			this.addResultItem(listNationalite);				
			List listPays = (List)parametrageManager.getListPays(null);
			this.addResultItem(listPays);
			List listTypeSuivi = (List)parametrageManager.getListTypeSuivi();
			this.addResultItem(listTypeSuivi);
			List listTypeDeclaration = (List)parametrageManager.getListTypeDeclaration();
			this.addResultItem(listTypeDeclaration);
			List listSourceDeclaration = (List)parametrageManager.getListSourceDeclaration();
			this.addResultItem(listSourceDeclaration);
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

