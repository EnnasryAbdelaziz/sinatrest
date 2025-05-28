package eai.devass.gsr.appli.manager.metier.mouvements;

import ma.co.omnidata.framework.services.entites.EntiteFactory;
import ma.co.omnidata.framework.services.entites.IEntite;
import ma.co.omnidata.framework.services.entites.IEntiteManager;
import eai.devass.gsr.appli.modele.metier.mouvements.MvtMajCapital;

/**
Factory de l' Entité MvtMajCapital
@author Nom Prenom (email)
*/
public class MvtMajCapitalFactory extends EntiteFactory {

/**
Methode qui retourne une instance d' une entité
@returns L' instance de l' entité
*/
	public IEntite newEntite() {
		return new MvtMajCapital();
	}
	
/**
Methode qui retourne une instance du manager
@returns L' instance du manager de l 'entité
*/
	public IEntiteManager newEntiteManager() {
		return new MvtMajCapitalManager();
	}

}
