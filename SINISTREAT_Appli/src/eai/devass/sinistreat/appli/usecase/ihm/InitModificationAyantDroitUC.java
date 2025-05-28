package eai.devass.sinistreat.appli.usecase.ihm;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;

import org.hibernate.exception.ConstraintViolationException;

import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.modele.metier.sinistre.AyantDroit;
import eai.devass.sinistreat.appli.modele.metier.sinistre.Sinistre;
import eai.devass.sinistreat.appli.modele.parametrage.DegreParente;
import eai.devass.sinistreat.appli.usecase.BaseUC;
import eai.devass.sinistreat.appli.valueobjects.metier.sinistre.AyantDroitVO;

public class InitModificationAyantDroitUC extends BaseUC  {
	
	
	

	
	protected void executerUC(IValueObject entite, HashMap params) throws Exception {
		
		// Récupérer la class de l'objet BO correspondant à l'objet VO
		AyantDroitVO ayvo= (AyantDroitVO)entite;
		try {			


			List listVille = (List)parametrageManager.getListVille(null);
			this.addResultItem(listVille);
			List listNatureEcheance = (List)parametrageManager.getlistNatureEcheance(null);
			this.addResultItem(listNatureEcheance);	
			List listDegreParenteAll = (List)parametrageManager.getListDegreParente(null);
			List listDegreParente = new ArrayList();
			for(Object deg : listDegreParenteAll){
				
				if(!"0".equals(((DegreParente)deg).getCode())){
					listDegreParente.add(deg);
				}
			}
			this.addResultItem(listDegreParente);			
			AyantDroit ay = (AyantDroit)sinistreManager.getAyantDroitById(Long.valueOf(ayvo.getId()));
			this.addResultItem(ay);	
			Sinistre sin = (Sinistre)sinistreManager.getSinistreByNum(ayvo.getNumeroSinistre());
			this.addResultItem(sin);
			
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

