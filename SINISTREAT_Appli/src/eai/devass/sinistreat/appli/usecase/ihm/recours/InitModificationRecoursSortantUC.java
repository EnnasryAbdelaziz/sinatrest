package eai.devass.sinistreat.appli.usecase.ihm.recours;


import java.util.HashMap;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;

import org.hibernate.exception.ConstraintViolationException;

import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.modele.parametrage.Assurance;
import eai.devass.sinistreat.appli.usecase.BaseUC;

public class InitModificationRecoursSortantUC extends BaseUC  {
	
	
	
	@SuppressWarnings("rawtypes")
	protected void executerUC(IValueObject entite, HashMap params) throws Exception {
		
		// Récupérer la class de l'objet BO correspondant à l'objet VO
			
		try {			
			Assurance assurance = (Assurance) this.getItem(Assurance.class);
			// Liste etats recours
			List listEtatRecours = (List)parametrageManager.getListeEtatRecours(null);
			this.addResultItem(listEtatRecours);
			// Liste compagnies assurances
			List listCompagnies = (List)parametrageManager.getListAssurance(assurance);
			
			this.addResultItem(listCompagnies);	
			// Liste types accident
			List listTypeAccident = (List)parametrageManager.getListTypeAccident(null);
			
			this.addResultItem(listTypeAccident);	
			//Pronostic responsabilite
			List listPronosticRC= (List)parametrageManager.getListePronosticRC();
			
			this.addResultItem(listPronosticRC);
			
			//Banque
			List listeBanques= (List)parametrageManager.getListBanque(null);
			
			this.addResultItem(listeBanques);	
		

			
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

