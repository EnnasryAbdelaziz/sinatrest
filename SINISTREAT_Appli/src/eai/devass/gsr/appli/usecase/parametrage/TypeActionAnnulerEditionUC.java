package eai.devass.gsr.appli.usecase.parametrage;

import java.util.HashMap;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.services.businessInterface.impl.FacadeServiceUseCase;
import eai.devass.gsr.appli.modele.parametrage.TypeAction;

/**
Service d annulation d' edition d' une entité
@author Nom Prenom (email)
*/
public class TypeActionAnnulerEditionUC extends FacadeServiceUseCase {
/**
Methode qui execute le Use case d' annulation d' edition
@param entie l' objet à annuler son edition
@param params paramatere additionel qu 'on peut passer au Use Case
@throws Exception Probleme lors de l' execution du Use case
*/
	protected void doExecuter(IValueObject entite, HashMap params) throws Exception {
		TypeAction lToEdit = (TypeAction) this.getItem(TypeAction.class);
		lToEdit = (TypeAction)lToEdit.getFactory().newEntiteManager().getEntite(lToEdit);
		this.addResultItem(lToEdit);	
		

		
		
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
Methode qui retourne le lock mode
@returns le type du lock mode
*/
	public int getLockMode() {
		return LOCKMODE_UNLOCK;
	}

	
}

