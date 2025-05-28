package eai.devass.sinistreat.appli.usecase.metier.sinistre;


import java.util.HashMap;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;

import org.apache.commons.lang.StringUtils;
import org.hibernate.exception.ConstraintViolationException;

import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.modele.metier.sinistre.Sinistre;
import eai.devass.sinistreat.appli.usecase.BaseUC;

public class CreateMouvementUC extends BaseUC  {
	
	protected void executerUC(IValueObject entite, HashMap params) throws Exception {
		
		Sinistre sinistre= (Sinistre)this.getItem(Sinistre.class);
		Sinistre sinResult=null;
		try{
			if(StringUtils.isEmpty(sinistre.getNumeroSinistre())) {
				throw new FonctionnelleException("EXP_NUM_SIN_AY_REQUIRED");
			}	
			
			sinResult = sinistreManager.getSinistreByNum(sinistre.getNumeroSinistre());
			if(sinResult==null){
				throw new FonctionnelleException("EXP_SINISTRE_INEXISTANT");	
			}
			
		} catch (ConstraintViolationException e) {
			throw new FonctionnelleException(e);
		} catch (Exception e) {
			throw new FonctionnelleException(e.getMessage());
		}

		addResultItem(sinResult);
		
	}
	
	

	
	public boolean isTransactionnal() {
		return true;
	}
	
	


}

