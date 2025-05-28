package eai.devass.sinistreat.appli.usecase.ihm;


import java.util.HashMap;
import ma.co.omnidata.framework.services.businessInterface.IValueObject;

import org.hibernate.exception.ConstraintViolationException;

import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.usecase.BaseUC;
import eai.devass.sinistreat.appli.valueobjects.metier.sinistre.SinistreVO;

public class InitZoneUC extends BaseUC  {
	
	
	
	protected void executerUC(IValueObject entite, HashMap params) throws Exception {
		
		// Récupérer la class de l'objet BO correspondant à l'objet VO
		SinistreVO sinvo= (SinistreVO)entite;
		try {	
			String codeVille=null;
			if(sinvo!=null && 
					sinvo.getRefEvenement()!=null &&
					sinvo.getRefEvenement().getRefVille()!=null &&
					sinvo.getRefEvenement().getRefVille().getCode()!=null){
				codeVille= sinvo.getRefEvenement().getRefVille().getCode();
			}
			String zone= (String)parametrageManager.getVilleByZoneSQL(codeVille);
			
			this.addResultItem(zone);		
			

			
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

