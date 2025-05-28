package eai.devass.sinistreat.appli.usecase.ihm.recours;


import java.util.HashMap;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;

import org.hibernate.exception.ConstraintViolationException;

import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.usecase.BaseUC;

public class InitModificationProcJudUC extends BaseUC  {
	
	
	
	@SuppressWarnings("rawtypes")
	protected void executerUC(IValueObject entite, HashMap params) throws Exception {
		
		// R�cup�rer la class de l'objet BO correspondant � l'objet VO
			
		try {			
			List listTypeJuridiction = (List)parametrageManager.getListTypeJuridiction(null);
			this.addResultItem(listTypeJuridiction);			
			List listNatureProcedure= (List)parametrageManager.getListNatureProcedure(null);
			this.addResultItem(listNatureProcedure);					
			List listeNatureDecision = (List)parametrageManager.getListNatureDecision(null);
			this.addResultItem(listeNatureDecision);			
			List listSuiteJugement= (List)parametrageManager.getListSuiteJugement(null);
			this.addResultItem(listSuiteJugement);					
			List listNatureDossier= (List)parametrageManager.getListNatureDossier(null);
			this.addResultItem(listNatureDossier);	
			List listPronosticRC= (List)parametrageManager.getListePronosticRC();
			this.addResultItem(listPronosticRC);
			
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

