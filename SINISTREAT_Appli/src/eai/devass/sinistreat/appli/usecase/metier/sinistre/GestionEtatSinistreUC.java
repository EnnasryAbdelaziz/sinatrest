package eai.devass.sinistreat.appli.usecase.metier.sinistre;


import java.util.HashMap;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;

import org.hibernate.exception.ConstraintViolationException;

import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.modele.metier.sinistre.Sinistre;
import eai.devass.sinistreat.appli.usecase.BaseUC;
import eai.devass.sinistreat.appli.utils.IConstantes;

public class GestionEtatSinistreUC extends BaseUC  {
	
	protected void executerUC(IValueObject entite, HashMap params) throws Exception {
		
		Sinistre sin = (Sinistre)this.getItem(Sinistre.class); 
		try{
			
			Sinistre sinResult= sinistreManager.modifierEtatSinistre(sin);
			sinistreManager.creerMouvementEtat(sinResult,sin,IConstantes.MOTIF_MODIFICATION_ETAT_SINISTRE);
			addResultItem(sinResult);

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

