package eai.devass.sinistreat.appli.usecase.metier.sinistre;


import java.util.HashMap;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;

import org.apache.commons.lang.StringUtils;
import org.hibernate.exception.ConstraintViolationException;

import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.modele.metier.sinistre.AyantDroit;
import eai.devass.sinistreat.appli.usecase.BaseUC;
import eai.devass.sinistreat.appli.valueobjects.metier.sinistre.AyantDroitVO;

public class CreateAyantDroitUC extends BaseUC  {
	
	protected void executerUC(IValueObject entite, HashMap params) throws Exception {
		
		AyantDroitVO ayvo= (AyantDroitVO)entite;
		AyantDroit ayantDroit= (AyantDroit)this.getItem(AyantDroit.class);
		AyantDroit ayResult=null;
		//Mouvement mvt=null;
		//Sinistre sinResult=null;
		//Mouvement mvtResult=null;		
		try{
			if(StringUtils.isEmpty(ayvo.getNumeroSinistre())) {
				throw new FonctionnelleException("EXP_NUM_SIN_AY_REQUIRED");
			}	
			if(StringUtils.isEmpty(ayantDroit.getNom()) 
					|| StringUtils.isEmpty(ayantDroit.getPrenom())) {
				throw new FonctionnelleException("EXP_NOM_PRENOM_AY_REQUIRED");
			}	
			
			ayResult=(AyantDroit)sinistreManager.creerAyantDroit(ayantDroit, ayvo.getNumeroSinistre());
			// calcul reserves ayant doit : 		
			ayResult = sinistreManager.calculReserveAyantDroit(ayResult,ayvo.getNumeroSinistre());
//			sinResult = sinistreManager.getSinistreByNum(ayvo.getNumeroSinistre());
//			if(sinResult==null)
//				throw new FonctionnelleException("EXP_SINISTRE_INEXISTANT");	
//			
//			ayResult = sinistreManager.getAyantDroitById(ayResult.getId());
//			if(sinResult==null)
//				throw new FonctionnelleException("EXP_SINISTRE_INEXISTANT");				
//			mvt=(Mouvement)sinistreManager.getMvtByIdSinistre(sinResult);//Le dernier mouvement correspond à ce sinistre
//			if(mvt!=null){
//				
//				if(sinistreManager.isMvt(mvt,sinResult)){
//						mvtResult=(Mouvement)sinistreManager.creerMouvementAY(sinResult,mvt);
//				 }else{
//						mvtResult=(Mouvement)sinistreManager.creerMouvementAY(sinResult);
//				 }
//			}
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

