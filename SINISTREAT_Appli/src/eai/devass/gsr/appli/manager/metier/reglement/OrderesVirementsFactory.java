package eai.devass.gsr.appli.manager.metier.reglement;

import eai.devass.gsr.appli.modele.metier.reglement.OrderesVirements;
import ma.co.omnidata.framework.services.entites.EntiteFactory;
import ma.co.omnidata.framework.services.entites.IEntite;
import ma.co.omnidata.framework.services.entites.IEntiteManager;

/**
Factory de l' Entité OrderesVirements
@author Nom Prenom (email)
*/
public class OrderesVirementsFactory extends EntiteFactory {

/**
Methode qui retourne une instance d' une entité
@returns L' instance de l' entité
*/
	public IEntite newEntite() {
		return new OrderesVirements();
	}
	
/**
Methode qui retourne une instance du manager
@returns L' instance du manager de l 'entité
*/
	public IEntiteManager newEntiteManager() {
		return new OrderesVirementsManager();
	}

}
