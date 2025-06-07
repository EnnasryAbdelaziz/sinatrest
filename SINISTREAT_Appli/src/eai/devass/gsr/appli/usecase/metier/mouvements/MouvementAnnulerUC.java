package eai.devass.gsr.appli.usecase.metier.mouvements;
import java.util.HashMap;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.services.businessInterface.impl.FacadeServiceUseCase;
import ma.co.omnidata.framework.services.entites.EntiteException;
import ma.co.omnidata.framework.services.entites.IEntiteManager;
import ma.co.omnidata.framework.utile.DateUtile;
import eai.devass.gsr.appli.modele.metier.mouvements.Mouvement;
import eai.devass.gsr.appli.modele.parametrage.EtatMvt;
/**
Service d'annulation d' une entitÃ©
@author Nom Prenom (email)
*/
public class MouvementAnnulerUC extends FacadeServiceUseCase {

/**
Methode qui execute le use case d'annulation d' un objet
@param entite l' objet à  annuler
@param params paramatere additionel qu 'on peut passer au Use Case
@throws Exception Probleme lors de l' execution du Use case
*/
	protected void doExecuter(IValueObject entite, HashMap params) throws Exception {
		// récupération de l'objet Mouvement provenant de l'HIM
		Mouvement lToUpdate = (Mouvement) this.getItem(Mouvement.class);
		IEntiteManager entiteManager = lToUpdate.getFactory().newEntiteManager();
		// verifier l'existence de l'entité au niveau de la Db
		lToUpdate = (Mouvement)entiteManager.getEntite(lToUpdate);
		if(lToUpdate == null) {
			throw new EntiteException("Mouvement introuvable");
		}
		// mettre l'etat de l'entité en etat annulé ainsi ajouter la dates de l'annulation
		EtatMvt etatMvt = new EtatMvt();
		etatMvt.setId(3);
		lToUpdate.setRefEtatMvt(etatMvt);
		lToUpdate.setDatEtat(DateUtile.dateCalendarCourante());
		Mouvement lUpdated = (Mouvement)entiteManager.modifyEntite(lToUpdate);
		this.addResultItem(lUpdated);
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
//	public int getLockMode() {
//		return LOCKMODE_UNLOCK;
//	}
}


