package eai.devass.gsr.appli.manager.metier.mouvements;

import ma.co.omnidata.framework.services.entites.EntiteFactory;
import ma.co.omnidata.framework.services.entites.IEntite;
import ma.co.omnidata.framework.services.entites.IEntiteManager;
import eai.devass.gsr.appli.modele.metier.mouvements.MvtRemiseEnCours;

/**
Factory de l' Entité MvtRemiseEnCours
@author Nom Prenom (email)
*/
public class MvtRemiseEnCoursFactory extends EntiteFactory {

/**
Methode qui retourne une instance d' une entité
@returns L' instance de l' entité
*/
	public IEntite newEntite() {
		return new MvtRemiseEnCours();
	}
	
/**
Methode qui retourne une instance du manager
@returns L' instance du manager de l 'entité
*/
	public IEntiteManager newEntiteManager() {
		return new MvtRemiseEnCoursManager();
	}

}
