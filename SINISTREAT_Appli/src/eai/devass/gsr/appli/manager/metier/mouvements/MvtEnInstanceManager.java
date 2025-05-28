package eai.devass.gsr.appli.manager.metier.mouvements;

import ma.co.omnidata.framework.services.entites.EntiteException;
import ma.co.omnidata.framework.services.entites.IEntite;
import ma.co.omnidata.framework.services.entites.ProcessEntiteException;
import ma.co.omnidata.framework.services.entites.ValidateEntiteException;
import eai.devass.gsr.appli.modele.metier.mouvements.MvtEnInstance;


/**
Manager de l ' entité MvtEnInstance
@author Nom Prenom (email) 
*/
public class MvtEnInstanceManager extends MouvementManager {



/**
Completude de l ' objet avant sa creation
@param entite lentité à completer
@return returns lentité completé
@throws ProcessEntiteException problème lors de la complétude de l' entité
@throws EntiteException 
*/
//	protected IEntite doProcessCreateEntity(IEntite entite) throws ProcessEntiteException, EntiteException {
//		
//		return super.doProcessCreateEntity(entite);
//	}
//	
	
/**
Validation de l'object avant sa suppression
@param entite l' entité à valider avant sa suppression
@throws ValidateEntiteException Probleme lors de la validation de l 'entité
@throws EntiteException Probleme lors de la persistence
*/
	protected void doValidateDeleteEntity(IEntite entite) throws ValidateEntiteException, EntiteException {
	}
	
	
/**
Validation de l'object avant sa creation
@param entite l' entité à valider avant sa creation
@throws ValidateEntiteException Probleme lors de la validation de l 'entité
@throws EntiteException Probleme lors de la persistence
*/
	  protected void doValidateCreateEntity(IEntite entite) throws ValidateEntiteException, EntiteException
	  {
		super.doValidateCreateEntity(entite);

	
	
	  }
/**
Validation de l' objet avant sa modification
@param entite L 'entité à valider avant sa modification
@throws ValidateEntiteException Probleme lors de la validation de l 'entité
@throws EntiteException Probleme lors de la persistence
*/	
	  protected void doValidateModifyEntity(IEntite entite) throws ValidateEntiteException, EntiteException
	  {
		super.doValidateModifyEntity(entite);


	  }	
/**
Methode qui habille une entité avec ces entités refs completes pou ne pas ecraser les valeurs des refs avec des nulls
@param entite lentité à habiller
@throws EntiteException Probleme lors de la persistence
*/
	protected IEntite habiller(IEntite entite) throws EntiteException {
		MvtEnInstance mvtEnInstance = (MvtEnInstance) super.habiller(entite);
		return mvtEnInstance;	
	}
/**
Methode qui habille l' entité avant sa modification
@param entite L 'entite à habiller
@return l' entité habillé
@throws ProcessEntiteException problème lors de la complétude de l' entité
@throws EntiteException Probleme lors de la persistence
*/
	protected IEntite doRevampModifyEntity(IEntite entite) throws ProcessEntiteException, EntiteException {
		return this.habiller(entite);
	}
/**
Methode qui habille l' entité avant sa creation
@param entite L 'entite à habiller
@return l' entité habillé
@throws ProcessEntiteException problème lors de la complétude de l' entité
@throws EntiteException Probleme lors de la persistence 
*/
	protected IEntite doRevampCreateEntity(IEntite entite) throws ProcessEntiteException, EntiteException {
		return this.habiller(entite);
	}
/**
Completude de l' objet avant sa modification
@param entite L' entité à completer avant sa modification
@returns L' entité completé
@throws ProcessEntiteException problème lors de la complétude de l' entité
@throws EntiteException Probleme lors de la persistence
*/	
protected IEntite doProcessModifyEntity(IEntite entite)
	throws ProcessEntiteException, EntiteException {
	MvtEnInstance mvtEnInstance = (MvtEnInstance) entite;
	



	return  mvtEnInstance;

}

}