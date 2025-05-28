package eai.devass.gsr.appli.manager.parametrage;

import ma.co.omnidata.framework.services.entites.EntiteFactory;
import ma.co.omnidata.framework.services.entites.IEntite;
import ma.co.omnidata.framework.services.entites.IEntiteManager;
import eai.devass.gsr.appli.modele.parametrage.CentreProthese;

/**
Factory de l' Entité CentreProthese
@author Nom Prenom (email)
*/
public class CentreProtheseFactory extends EntiteFactory {

/**
Methode qui retourne une instance d' une entité
@returns L' instance de l' entité
*/
	public IEntite newEntite() {
		return new CentreProthese();
	}
	
/**
Methode qui retourne une instance du manager
@returns L' instance du manager de l 'entité
*/
	public IEntiteManager newEntiteManager() {
		return new CentreProtheseManager();
	}

}
