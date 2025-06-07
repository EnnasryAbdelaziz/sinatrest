package eai.devass.gsr.appli.usecase.metier.dossierRente;

import java.util.HashMap;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.services.businessInterface.impl.FacadeServiceUseCase;
import eai.devass.gsr.appli.modele.metier.dossierRente.Certificats;
/**
Service de suppression d' une entité
@author Nom Prenom (email)
*/
public class CertificatsSupprimerUC extends FacadeServiceUseCase {

/**
Methode qui execute le use case de suppression
@param entite l' objet à supprimer
@param params paramatere additionel qu 'on peut passer au Use Case
@throws Exception Probleme lors de l' execution du Use case
*/

	protected void doExecuter(IValueObject entite, HashMap params) throws Exception {
		Certificats lToEdit = (Certificats) this.getItem(Certificats.class);
		lToEdit.getFactory().newEntiteManager().deleteEntite(lToEdit);
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
