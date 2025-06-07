package eai.devass.gsr.appli.usecase.metier.dossierRente;
import java.util.HashMap;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.services.businessInterface.impl.FacadeServiceUseCase;
import eai.devass.gsr.appli.manager.metier.dossierRente.RentierManager;
import eai.devass.gsr.appli.modele.metier.dossierRente.DossierRente;
import eai.devass.gsr.appli.modele.metier.dossierRente.Rentier;

/**
Service de consultation d' une entité
@author Nom Prenom (email)
*/
public class DossierRenteConsulterUC extends FacadeServiceUseCase {
/**
Methode qui execute le use case de consultation d' un objet
@param entite l' objet à consulter
@param params paramatere additionel qu 'on peut passer au Use Case
@throws Exception Probleme lors de l' execution du Use case
*/
	protected void doExecuter(IValueObject entite, HashMap params) throws Exception {
		DossierRente lToEdit = (DossierRente) this.getItem(DossierRente.class);
		DossierRente lEdited = (DossierRente) lToEdit.getFactory().newEntiteManager().getEntite(lToEdit);
		this.addResultItem(lEdited);
		Rentier rentierRentierToEdit = new Rentier();
//Ici J' ajoute au child RentierRentier le ref vers son pere pour pouvoir rechercher tous les Rentier lie a l' entite pereDossierRente
		rentierRentierToEdit.setRefDossierRente(lToEdit);
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

}

