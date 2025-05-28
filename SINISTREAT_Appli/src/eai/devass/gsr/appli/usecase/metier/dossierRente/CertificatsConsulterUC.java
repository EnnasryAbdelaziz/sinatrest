package eai.devass.gsr.appli.usecase.metier.dossierRente;
import java.util.HashMap;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.services.businessInterface.impl.FacadeServiceUseCase;
import eai.devass.gsr.appli.modele.metier.dossierRente.Certificats;

/**
Service de consultation d' une entité
@author Nom Prenom (email)
*/
public class CertificatsConsulterUC extends FacadeServiceUseCase {
/**
Methode qui execute le use case de consultation d' un objet
@param entite l' objet à consulter
@param params paramatere additionel qu 'on peut passer au Use Case
@throws Exception Probleme lors de l' execution du Use case
*/
	protected void doExecuter(IValueObject entite, HashMap params) throws Exception {
		Certificats lToEdit = (Certificats) this.getItem(Certificats.class);
		Certificats lEdited = (Certificats) lToEdit.getFactory().newEntiteManager().getEntite(lToEdit);
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

}

