package eai.devass.gsr.appli.manager.metier.mouvements;

import ma.co.omnidata.framework.services.entites.EntiteException;
import ma.co.omnidata.framework.services.entites.IEntite;
import ma.co.omnidata.framework.services.entites.ProcessEntiteException;
import ma.co.omnidata.framework.services.entites.ValidateEntiteException;
import eai.devass.gsr.appli.modele.metier.mouvements.MvtProthese;
import eai.devass.gsr.appli.modele.parametrage.CentreProthese;
import eai.devass.gsr.appli.modele.parametrage.TypeMvtProthese;


/**
Manager de l ' entité MvtProthese
@author Nom Prenom (email) 
*/
public class MvtProtheseManager extends MouvementManager {



/**
Completude de l ' objet avant sa creation
@param entite lentité à completer
@return returns lentité completé
@throws ProcessEntiteException problème lors de la complétude de l' entité
@throws EntiteException 
*/
//	protected IEntite doProcessCreateEntity(IEntite entite) throws ProcessEntiteException, EntiteException {
//		return super.doProcessCreateEntity(entite);
//
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
		if (((MvtProthese)entite).getRefCentreProthese() == null){
		    	ValidateEntiteException ve = new ValidateEntiteException ("FIELD.OBLIGATOIRE");
				ve.setValues(new String []{"centreProthese"});
				throw ve;
		    	
		  	}
		if (((MvtProthese)entite).getRefTypeMvtProthese() == null){
		    	ValidateEntiteException ve = new ValidateEntiteException ("FIELD.OBLIGATOIRE");
				ve.setValues(new String []{"typeMvtProthese"});
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
		if (((MvtProthese)entite).getRefCentreProthese() == null){
			ValidateEntiteException ve = new ValidateEntiteException ("FIELD.OBLIGATOIRE");
			ve.setValues(new String []{"centreProthese"});
			throw ve;
		}
		if (((MvtProthese)entite).getRefTypeMvtProthese() == null){
			ValidateEntiteException ve = new ValidateEntiteException ("FIELD.OBLIGATOIRE");
			ve.setValues(new String []{"typeMvtProthese"});
			throw ve;
		}


	  }	
/**
Methode qui habille une entité avec ces entités refs completes pou ne pas ecraser les valeurs des refs avec des nulls
@param entite lentité à habiller
@throws EntiteException Probleme lors de la persistence
*/
	protected IEntite habiller(IEntite entite) throws EntiteException {
		MvtProthese mvtProthese = (MvtProthese) super.habiller(entite);


		
		CentreProthese refCentreProthese1 = mvtProthese.getRefCentreProthese();
		CentreProthese refCentreProthese = new CentreProthese();

		
		if (refCentreProthese1 != null) {
			((IEntite)refCentreProthese).setId(refCentreProthese1.getId());
		}
		if(refCentreProthese!=null && refCentreProthese1 != null){
			if (((IEntite)refCentreProthese).getId()!=0){
				    refCentreProthese =(CentreProthese)(new CentreProthese()).getFactory().newEntiteManager().getEntite((CentreProthese)refCentreProthese);
				mvtProthese.setRefCentreProthese(refCentreProthese);
			}
			else {
				mvtProthese.setRefCentreProthese(refCentreProthese1);
			}
		}		
		TypeMvtProthese refTypeMvtProthese1 = mvtProthese.getRefTypeMvtProthese();
		TypeMvtProthese refTypeMvtProthese = new TypeMvtProthese();

		
		if (refTypeMvtProthese1 != null) {
			((IEntite)refTypeMvtProthese).setId(refTypeMvtProthese1.getId());
		}
		if(refTypeMvtProthese!=null && refTypeMvtProthese1 != null){
			if (((IEntite)refTypeMvtProthese).getId()!=0){
				    refTypeMvtProthese =(TypeMvtProthese)(new TypeMvtProthese()).getFactory().newEntiteManager().getEntite((TypeMvtProthese)refTypeMvtProthese);
				mvtProthese.setRefTypeMvtProthese(refTypeMvtProthese);
			}
			else {
				mvtProthese.setRefTypeMvtProthese(refTypeMvtProthese1);
			}
		}		
		return mvtProthese;	
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
	MvtProthese mvtProthese = (MvtProthese) entite;
	



	return  mvtProthese;

}

}