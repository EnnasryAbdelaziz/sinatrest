package eai.devass.gsr.appli.usecase.metier.mouvements;

import java.util.HashMap;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.services.businessInterface.impl.FacadeServiceUseCase;
import eai.devass.gsr.appli.modele.metier.mouvements.Heritier;
/**
Service d' edition d' une entité
@author Nom Prenom (email)
*/
public class HeritierEditerUC extends FacadeServiceUseCase {

/**
Methode qui execute le use case d' Edition d' un objet
@param entite l' objet à editer
@param params paramatere additionel qu 'on peut passer au Use Case
@throws Exception Probleme lors de l' execution du Use case
*/
	protected void doExecuter(IValueObject entite, HashMap params) throws Exception {
	
		Heritier lToEdit = (Heritier) this.getItem(Heritier.class);
		Heritier lEdited = (Heritier) lToEdit.getFactory().newEntiteManager().getEntite(lToEdit);
		this.addResultItem(lEdited);
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
		return LOCKMODE_LOCK;
	}
	
	

}

