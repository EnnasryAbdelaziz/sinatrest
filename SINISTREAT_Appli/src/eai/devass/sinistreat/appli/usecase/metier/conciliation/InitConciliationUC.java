package eai.devass.sinistreat.appli.usecase.metier.conciliation;


import java.util.HashMap;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;

import org.hibernate.exception.ConstraintViolationException;

import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.modele.metier.conciliation.User;
import eai.devass.sinistreat.appli.usecase.BaseUC;

public class InitConciliationUC extends BaseUC  {
	
	
	
	protected void executerUC(IValueObject entite, HashMap params) throws Exception {
		
		// Récupérer la class de l'objet BO correspondant à l'objet VO
		User user = (User) this.getItem(User.class);
		long id = user.getId();
		String codeSas = user.getCodeSas();
		try {			

			List listUser = (List)conciliationManager.getListUser(id,codeSas);
			this.addResultItem(listUser);															
			
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

