package eai.devass.gsr.appli.manager.parametrage;

import ma.co.omnidata.framework.services.entites.EntiteFactory;
import ma.co.omnidata.framework.services.entites.IEntite;
import ma.co.omnidata.framework.services.entites.IEntiteManager;
import eai.devass.gsr.appli.modele.parametrage.NatureQtcGsr;

/**
Factory de l' Entité NatureQuittance
@author Nom Prenom (email)
*/
public class NatureQtcGsrFactory extends EntiteFactory {

/**
Methode qui retourne une instance d' une entité
@returns L' instance de l' entité
*/
	public IEntite newEntite() {
		return new NatureQtcGsr();
	}
	
/**
Methode qui retourne une instance du manager
@returns L' instance du manager de l 'entité
*/
	public IEntiteManager newEntiteManager() {
		return new NatureQtcGsrManager();
	}

}
