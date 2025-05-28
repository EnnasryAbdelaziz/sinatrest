package eai.devass.sinistreat.appli.usecase.metier.sinistre;


import java.util.HashMap;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;

import org.apache.commons.lang.StringUtils;
import org.hibernate.exception.ConstraintViolationException;

import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.modele.metier.sinistre.Evenement;
import eai.devass.sinistreat.appli.modele.metier.sinistre.Sinistre;
import eai.devass.sinistreat.appli.usecase.BaseUC;
import eai.devass.sinistreat.appli.utils.IConstantes;
import eai.devass.sinistreat.appli.valueobjects.metier.sinistre.AyantDroitVO;

public class CreateMouvementAYUC extends BaseUC  {
	
	protected void executerUC(IValueObject entite, HashMap params) throws Exception {
		
		AyantDroitVO ayvo= (AyantDroitVO)entite;
		Sinistre sinResult=null;		
		try{
			if(StringUtils.isEmpty(ayvo.getNumeroSinistre())){
				throw new FonctionnelleException("Numéro sinistre vide");	
			}
			sinResult = sinistreManager.getSinistreByNum(ayvo.getNumeroSinistre());
			
			if(sinResult==null){
				throw new FonctionnelleException("Sinistre inexistant");	
			}
			
			if(sinResult.getRefVictime()!=null){
				Evenement even = sinResult.getRefEvenement();
				if(even.getDateAccident()!=null){
					sinResult.setDateCalculReserve(even.getDateAccident());
				}
				sinistreManager.creerMouvementAY(sinResult,IConstantes.MOTIF_MODIFICATION_SINISTRE);
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

