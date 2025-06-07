package eai.devass.gsr.appli.manager.parametrage;

import eai.devass.gsr.appli.modele.parametrage.EtatRentier;
import ma.co.omnidata.framework.services.entites.EntiteFactory;
import ma.co.omnidata.framework.services.entites.IEntite;
import ma.co.omnidata.framework.services.entites.IEntiteManager;

/**
Factory de l' Entité EtatRentier
@author Nom Prenom (email)
*/
public class EtatRentierFactory extends EntiteFactory {

/**
Methode qui retourne une instance d' une entité
@returns L' instance de l' entité
*/
	public IEntite newEntite() {
		return new EtatRentier();
	}
	
/**
Methode qui retourne une instance du manager
@returns L' instance du manager de l 'entité
*/
	public IEntiteManager newEntiteManager() {
		return new EtatRentierManager();
	}

}
