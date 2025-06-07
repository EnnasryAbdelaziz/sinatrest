package eai.devass.gsr.appli.manager.metier.dossierRente;

import ma.co.omnidata.framework.services.core.ServicesFactory;
import ma.co.omnidata.framework.services.entites.EntiteFactory;
import ma.co.omnidata.framework.services.entites.IEntite;
import ma.co.omnidata.framework.services.entites.IEntiteManager;
import eai.devass.gsr.appli.modele.metier.dossierRente.Rentier;

/**
Factory de l' Entité Rentier
@author Nom Prenom (email)
*/
public class RentierFactory extends EntiteFactory {

/**
Methode qui retourne une instance d' une entité
@returns L' instance de l' entité
*/
	public IEntite newEntite() {
		return new Rentier();
	}
	
/**
Methode qui retourne une instance du manager
@returns L' instance du manager de l 'entité
*/
	public IEntiteManager newEntiteManager() {
		return (IEntiteManager) ServicesFactory.getService(RentierManager.class);
	}

}
