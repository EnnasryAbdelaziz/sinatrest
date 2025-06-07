package eai.devass.sinistreat.appli.usecase.metier.sinistre;


import java.util.HashMap;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.services.businessInterface.impl.WarnMessageItem;

import org.hibernate.exception.ConstraintViolationException;

import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.modele.metier.sinistre.Evenement;
import eai.devass.sinistreat.appli.modele.metier.sinistre.Sinistre;
import eai.devass.sinistreat.appli.modele.metier.sinistre.Victime;
import eai.devass.sinistreat.appli.modele.parametrage.Zone;
import eai.devass.sinistreat.appli.usecase.BaseUC;
import eai.devass.sinistreat.appli.utils.IConstantes;

public class CalculReserveRepriseUC extends BaseUC  {
	
	protected void executerUC(IValueObject entite, HashMap params) throws Exception {
		
		Sinistre sinistre= (Sinistre)this.getItem(Sinistre.class);
		Evenement even = sinistre.getRefEvenement();
		Sinistre sinResult = null;
		Victime vic = (Victime) sinistre.getRefVictime();

		try{
			if(sinistre.getNumeroSinistre() != null){
				Sinistre sin = sinistreManager.getSinistreByNum(sinistre.getNumeroSinistre());
				if(sin!= null){
					throw new FonctionnelleException(EXP_NUM_SIN_EXISTANT);
				}
			}
			if(sinistre.getRefEvenement()!=null 
					&& sinistre.getRefEvenement().getRefZone()!=null){
				List listZone = parametrageManager.getListZone(sinistre.getRefEvenement().getRefZone());
				if(listZone!=null && !listZone.isEmpty()){
					Zone zone = (Zone) listZone.get(0);
					if(sinistre.getIpp()!=null && sinistre.getIpp().compareTo(zone.getIppMin())<0){
						throw new FonctionnelleException("La valeur de l'IPP ne peut être inférieure au taux minimum par zone: "+zone.getIppMin());
					}
				}
			}
			sinResult=(Sinistre)sinistreManager.calculReserve(sinistre,true,true,false);
			if (vic.getDateNaissance() != null && vic.getAge(even.getDateAccident()) > IConstantes.AGE_MAX) {
				addMessageItem(new WarnMessageItem(EXP_AGE_MAXIMUM_65));
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

