package eai.devass.gsr.appli.usecase.metier.dossierRente;
import java.util.HashMap;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.services.businessInterface.impl.FacadeServiceUseCase;
import eai.devass.gsr.appli.manager.metier.dossierRente.RentierManager;
import eai.devass.gsr.appli.modele.metier.dossierRente.DossierRente;
import eai.devass.gsr.appli.modele.metier.dossierRente.Rentier;
/**
Service de modification d' une entité
@author Nom Prenom (email)
*/
public class DossierRenteModifierUC extends FacadeServiceUseCase {

/**
Methode qui execute le use case de modification  d' un objet
@param entite l' objet à modifier
@param params paramatere additionel qu 'on peut passer au Use Case
@throws Exception Probleme lors de l' execution du Use case
*/
	protected void doExecuter(IValueObject entite, HashMap params) throws Exception {
		


		DossierRente lToUpdate = (DossierRente) this.getItem(DossierRente.class);
		DossierRente lUpdated = (DossierRente) lToUpdate.getFactory().newEntiteManager().modifyEntite(lToUpdate);
		this.addResultItem(lUpdated);
		
		
		
		DossierRente dossierRenteParent = new DossierRente();
		dossierRenteParent.setId(lToUpdate.getId());
		Rentier rentierRentierToEdit = new Rentier();
		rentierRentierToEdit.setRefDossierRente(dossierRenteParent);
		RentierManager rentierRentierManager = new RentierManager();
		List rentierRentierEditedList =  rentierRentierManager.lookupEntite(rentierRentierToEdit);
		this.addResultItem(rentierRentierEditedList);

	}
/**
Methode pour activer le service de transaction
@returns soit true pour activer le service de transaction ou false pour le desactiver
*/	
	public boolean isTransactionnal() {
		return true;
	}
/**
Methode pour activer le service de trace
@returns soit true pour activer le service de trace ou false pour le desactiver
*/
	public boolean isTracable() {
		return false;
	}
/**
Methode qui retourne le lock mode du Use Case
@returns le lock mode
*/	
	public int getLockMode() {
		return LOCKMODE_UNLOCK;
	}
}


