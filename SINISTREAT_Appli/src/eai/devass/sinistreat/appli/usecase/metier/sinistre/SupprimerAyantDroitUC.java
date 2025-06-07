package eai.devass.sinistreat.appli.usecase.metier.sinistre;


import java.util.HashMap;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;

import org.hibernate.exception.ConstraintViolationException;

import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.modele.metier.sinistre.AyantDroit;
import eai.devass.sinistreat.appli.usecase.BaseUC;

public class SupprimerAyantDroitUC extends BaseUC  {
	
	protected void executerUC(IValueObject entite, HashMap params) throws Exception {
		
		AyantDroit ayantDroit= (AyantDroit)this.getItem(AyantDroit.class);
		AyantDroit ayResult=null;
		try{
			if(ayantDroit.getId()==0) {
				throw new FonctionnelleException("EXP_ID_AY_REQUIRED");
			}	 
			ayResult=(AyantDroit)sinistreManager.supprimerAyantDroit(ayantDroit);

		} catch (ConstraintViolationException e) {
			throw new FonctionnelleException(e);
		} catch (Exception e) {
			throw new FonctionnelleException(e.getMessage());
		}

		addResultItem(ayResult);
		
	}
	
	

	
	public boolean isTransactionnal() {
		return true;
	}
	
	


}

