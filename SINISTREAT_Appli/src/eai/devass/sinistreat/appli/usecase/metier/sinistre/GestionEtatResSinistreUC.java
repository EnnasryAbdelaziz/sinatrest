package eai.devass.sinistreat.appli.usecase.metier.sinistre;


import java.util.HashMap;
import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import org.hibernate.exception.ConstraintViolationException;

import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.modele.metier.sinistre.Sinistre;
import eai.devass.sinistreat.appli.usecase.BaseUC;

public class GestionEtatResSinistreUC extends BaseUC  {
	
	protected void executerUC(IValueObject entite, HashMap params) throws Exception {
		
		Sinistre sin = (Sinistre)this.getItem(Sinistre.class); 
		HashMap param = new HashMap();
		Sinistre sinResult=null;
		try{
			
			sinResult= sinistreManager.verifierReserveSinistre(sin);
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

