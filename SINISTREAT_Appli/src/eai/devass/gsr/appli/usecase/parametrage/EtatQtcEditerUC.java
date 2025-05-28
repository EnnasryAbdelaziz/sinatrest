package eai.devass.gsr.appli.usecase.parametrage;

import java.util.HashMap;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.services.businessInterface.impl.FacadeServiceUseCase;
import eai.devass.gsr.appli.modele.parametrage.EtatQtc;
/**
Service d' edition d' une entité
@author Nom Prenom (email)
*/
public class EtatQtcEditerUC extends FacadeServiceUseCase {

/**
Methode qui execute le use case d' Edition d' un objet
@param entite l' objet à editer
@param params paramatere additionel qu 'on peut passer au Use Case
@throws Exception Probleme lors de l' execution du Use case
*/
	protected void doExecuter(IValueObject entite, HashMap params) throws Exception {
	
		EtatQtc lToEdit = (EtatQtc) this.getItem(EtatQtc.class);
		EtatQtc lEdited = (EtatQtc) lToEdit.getFactory().newEntiteManager().getEntite(lToEdit);
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

