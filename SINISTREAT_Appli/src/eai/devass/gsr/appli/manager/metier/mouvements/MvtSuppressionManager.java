package eai.devass.gsr.appli.manager.metier.mouvements;

import ma.co.omnidata.framework.services.entites.EntiteException;
import ma.co.omnidata.framework.services.entites.IEntite;
import ma.co.omnidata.framework.services.entites.ProcessEntiteException;
import ma.co.omnidata.framework.services.entites.ValidateEntiteException;
import eai.devass.gsr.appli.modele.metier.mouvements.MvtSuppression;
import eai.devass.gsr.appli.modele.parametrage.TypeAction;


/**
Manager de l ' entité MvtSuppression
@author Nom Prenom (email) 
*/
public class MvtSuppressionManager extends MouvementManager {



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
		if (((MvtSuppression)entite).getRefTypeAction() == null){
		    	ValidateEntiteException ve = new ValidateEntiteException ("FIELD.OBLIGATOIRE");
				ve.setValues(new String []{"typeAction"});
				throw ve;
		    	
		  	}

	
	
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
		if (((MvtSuppression)entite).getRefTypeAction() == null){
			ValidateEntiteException ve = new ValidateEntiteException ("FIELD.OBLIGATOIRE");
			ve.setValues(new String []{"typeAction"});
			throw ve;
		}


	  }	
/**
Methode qui habille une entité avec ces entités refs completes pou ne pas ecraser les valeurs des refs avec des nulls
@param entite lentité à habiller
@throws EntiteException Probleme lors de la persistence
*/
	protected IEntite habiller(IEntite entite) throws EntiteException {
		MvtSuppression mvtSuppression = (MvtSuppression) super.habiller(entite);


		
		TypeAction refTypeAction1 = mvtSuppression.getRefTypeAction();
		TypeAction refTypeAction = new TypeAction();

		
		if (refTypeAction1 != null) {
			((IEntite)refTypeAction).setId(refTypeAction1.getId());
		}
		if(refTypeAction!=null && refTypeAction1 != null){
			if (((IEntite)refTypeAction).getId()!=0){
				    refTypeAction =(TypeAction)(new TypeAction()).getFactory().newEntiteManager().getEntite((TypeAction)refTypeAction);
				mvtSuppression.setRefTypeAction(refTypeAction);
			}
			else {
				mvtSuppression.setRefTypeAction(refTypeAction1);
			}
		}		
		return mvtSuppression;	
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
	MvtSuppression mvtSuppression = (MvtSuppression) entite;
	



	return  mvtSuppression;

}

}