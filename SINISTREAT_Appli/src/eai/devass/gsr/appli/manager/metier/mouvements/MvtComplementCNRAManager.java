package eai.devass.gsr.appli.manager.metier.mouvements;

import ma.co.omnidata.framework.services.entites.EntiteException;
import ma.co.omnidata.framework.services.entites.IEntite;
import ma.co.omnidata.framework.services.entites.ProcessEntiteException;
import ma.co.omnidata.framework.services.entites.ValidateEntiteException;
import eai.devass.gsr.appli.modele.metier.mouvements.MvtComplementCNRA;


/**
Manager de l ' entité MvtComplementCNRA
@author Nom Prenom (email) 
*/
public class MvtComplementCNRAManager extends  MouvementManager {



/**
Completude de l ' objet avant sa creation
@param entite lentité à completer
@return returns lentité completé
@throws ProcessEntiteException problème lors de la complétude de l' entité
@throws EntiteException 
*/
	/*protected IEntite doProcessCreateEntity(IEntite entite) throws ProcessEntiteException, EntiteException {
		return super.doProcessCreateEntity(entite);

	}*/
	
	
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
//		if (((MvtComplementCNRA)entite).getRefMotifComplementCNRA() == null){
//		    	ValidateEntiteException ve = new ValidateEntiteException ("FIELD.OBLIGATOIRE");
//				ve.setValues(new String []{"motifComplementCNRA"});
//				throw ve;
//		    	
//		  	}
//		if (((MvtComplementCNRA)entite).getRefSituationRentier() == null){
//		    	ValidateEntiteException ve = new ValidateEntiteException ("FIELD.OBLIGATOIRE");
//				ve.setValues(new String []{"situationRentier"});
//				throw ve;
//		    	
//		  	}

	
	
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
		if (((MvtComplementCNRA)entite).getRefMotifComplementCNRA() == null){
			ValidateEntiteException ve = new ValidateEntiteException ("FIELD.OBLIGATOIRE");
			ve.setValues(new String []{"motifComplementCNRA"});
			throw ve;
		}
//		if (((MvtComplementCNRA)entite).getRefSituationRentier() == null){
//			ValidateEntiteException ve = new ValidateEntiteException ("FIELD.OBLIGATOIRE");
//			ve.setValues(new String []{"situationRentier"});
//			throw ve;
//		}


	  }	
/**
Methode qui habille une entité avec ces entités refs completes pou ne pas ecraser les valeurs des refs avec des nulls
@param entite lentité à habiller
@throws EntiteException Probleme lors de la persistence
*/
//	protected IEntite habiller(IEntite entite) throws EntiteException {
//		MvtComplementCNRA mvtComplementCNRA = (MvtComplementCNRA) super.habiller(entite);
//
//
//		
//		MotifComplementCNRA refMotifComplementCNRA1 = mvtComplementCNRA.getRefMotifComplementCNRA();
//		MotifComplementCNRA refMotifComplementCNRA = new MotifComplementCNRA();
//
//		
//		if (refMotifComplementCNRA1 != null)
//		    ((IEntite)refMotifComplementCNRA).setId(refMotifComplementCNRA1.getId());
//		if(refMotifComplementCNRA!=null && refMotifComplementCNRA1 != null){
//			if (((IEntite)refMotifComplementCNRA).getId()!=0){
//				    refMotifComplementCNRA =(MotifComplementCNRA)(new MotifComplementCNRA()).getFactory().newEntiteManager().getEntite((MotifComplementCNRA)refMotifComplementCNRA);
//				mvtComplementCNRA.setRefMotifComplementCNRA(refMotifComplementCNRA);
//			}
//			else {
//				mvtComplementCNRA.setRefMotifComplementCNRA(refMotifComplementCNRA1);
//			}
//		}		
//		SituationRentier refSituationRentier1 = mvtComplementCNRA.getRefSituationRentier();
//		SituationRentier refSituationRentier = new SituationRentier();
//
//		
//		if (refSituationRentier1 != null)
//		    ((IEntite)refSituationRentier).setId(refSituationRentier1.getId());
//		if(refSituationRentier!=null && refSituationRentier1 != null){
//			if (((IEntite)refSituationRentier).getId()!=0){
//				    refSituationRentier =(SituationRentier)(new SituationRentier()).getFactory().newEntiteManager().getEntite((SituationRentier)refSituationRentier);
//				mvtComplementCNRA.setRefSituationRentier(refSituationRentier);
//			}
//			else {
//				mvtComplementCNRA.setRefSituationRentier(refSituationRentier1);
//			}
//		}		
//		return mvtComplementCNRA;	
//	}
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
	MvtComplementCNRA mvtComplementCNRA = (MvtComplementCNRA) entite;
	



	return  mvtComplementCNRA;

}

}