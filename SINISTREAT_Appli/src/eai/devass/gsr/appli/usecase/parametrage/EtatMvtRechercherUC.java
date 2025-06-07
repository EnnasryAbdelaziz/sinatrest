package eai.devass.gsr.appli.usecase.parametrage;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.services.businessInterface.impl.RechListeUseCase;
import ma.co.omnidata.framework.services.entites.IConditionBuilder;
import ma.co.omnidata.framework.services.entites.IEntiteManager;
import ma.co.omnidata.framework.services.entites.impl.DeepDefaultConditionBuilder;
import eai.devass.gsr.appli.manager.parametrage.EtatMvtManager;
/**
Service de recherche d' une entité
@author Nom Prenom (email)
*/
public class EtatMvtRechercherUC extends RechListeUseCase {

/**
Methode qui Récupère le conditionBuilder définit pour le manager, si non spécifié retourne le DefaultConditionBuilder
@param vo le value object
@returns Le condition builder approprié
*/
 
	public IConditionBuilder getConditionBuilder(IValueObject vo) {	
		return new DeepDefaultConditionBuilder();
	
	} 
	
/**
Methode qui retourne une instance du manager
@returns L' instance du manager de l 'entité
*/
	public IEntiteManager getManager() {
		return new EtatMvtManager();
	}

/**
Methode pour activer le service de trace
@returns soit true pour activer le service de trace ou false pour le desactiver
*/
	public boolean isTracable() {
		return false;
	}
	

	
	
}
