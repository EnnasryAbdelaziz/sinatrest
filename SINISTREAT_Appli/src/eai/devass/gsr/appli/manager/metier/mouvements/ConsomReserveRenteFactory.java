package eai.devass.gsr.appli.manager.metier.mouvements;

import eai.devass.gsr.appli.modele.metier.mouvements.ConsomReserveRente;
import ma.co.omnidata.framework.services.entites.EntiteFactory;
import ma.co.omnidata.framework.services.entites.IEntite;
import ma.co.omnidata.framework.services.entites.IEntiteManager;

/**
Factory de l' Entité ConsomReserveRente
@author Nom Prenom (email)
*/
public class ConsomReserveRenteFactory extends EntiteFactory {

/**
Methode qui retourne une instance d' une entité
@returns L' instance de l' entité
*/
	public IEntite newEntite() {
		return new ConsomReserveRente();
	}
	
/**
Methode qui retourne une instance du manager
@returns L' instance du manager de l 'entité
*/
	public IEntiteManager newEntiteManager() {
		return new ConsomReserveRenteManager();
	}

}
