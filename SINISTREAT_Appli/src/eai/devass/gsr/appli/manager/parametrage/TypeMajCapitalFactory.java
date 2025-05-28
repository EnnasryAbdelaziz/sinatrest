package eai.devass.gsr.appli.manager.parametrage;

import ma.co.omnidata.framework.services.entites.EntiteFactory;
import ma.co.omnidata.framework.services.entites.IEntite;
import ma.co.omnidata.framework.services.entites.IEntiteManager;
import eai.devass.gsr.appli.modele.parametrage.TypeMajCapital;

/**
Factory de l' Entité TypeMajCapital
@author Nom Prenom (email)
*/
public class TypeMajCapitalFactory extends EntiteFactory {

/**
Methode qui retourne une instance d' une entité
@returns L' instance de l' entité
*/
	public IEntite newEntite() {
		return new TypeMajCapital();
	}
	
/**
Methode qui retourne une instance du manager
@returns L' instance du manager de l 'entité
*/
	public IEntiteManager newEntiteManager() {
		return new TypeMajCapitalManager();
	}

}
