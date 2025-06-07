package eai.devass.gsr.appli.manager.parametrage;

import eai.devass.gsr.appli.modele.parametrage.MotifEtat;
import ma.co.omnidata.framework.services.entites.EntiteFactory;
import ma.co.omnidata.framework.services.entites.IEntite;
import ma.co.omnidata.framework.services.entites.IEntiteManager;

/**
Factory de l' Entité MotifEtat
@author Nom Prenom (email)
*/
public class MotifEtatFactory extends EntiteFactory {

/**
Methode qui retourne une instance d' une entité
@returns L' instance de l' entité
*/
	public IEntite newEntite() {
		return new MotifEtat();
	}
	
/**
Methode qui retourne une instance du manager
@returns L' instance du manager de l 'entité
*/
	public IEntiteManager newEntiteManager() {
		return new MotifEtatManager();
	}

}
