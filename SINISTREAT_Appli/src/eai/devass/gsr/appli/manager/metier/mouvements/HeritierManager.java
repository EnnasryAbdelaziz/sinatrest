package eai.devass.gsr.appli.manager.metier.mouvements;

import ma.co.omnidata.framework.services.entites.EntiteException;
import ma.co.omnidata.framework.services.entites.IEntite;
import ma.co.omnidata.framework.services.entites.ProcessEntiteException;
import ma.co.omnidata.framework.services.entites.ValidateEntiteException;
import ma.co.omnidata.framework.utile.DateUtile;
import eai.devass.commun.appli.exeptions.ExceptionMetier;
import eai.devass.gsr.appli.manager.CommunManager;
import eai.devass.gsr.appli.modele.metier.mouvements.Heritier;
import eai.devass.gsr.appli.modele.metier.mouvements.MvtDecesRentier;


/**
Manager de l ' entité Heritier
@author Nom Prenom (email) 
*/
public class HeritierManager extends CommunManager {



/**
Completude de l ' objet avant sa creation
@param entite lentité à completer
@return returns lentité completé
@throws ProcessEntiteException problème lors de la complétude de l' entité
@throws EntiteException 
*/
	protected IEntite doProcessCreateEntity(IEntite entite) throws ProcessEntiteException, EntiteException {
		Heritier heritier = (Heritier) entite;

		 heritier.setDateCreation(DateUtile.dateCalendarCourante());



		return heritier;

	}
	
	
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

	
	
	  }
/**
Validation de l' objet avant sa modification
@param entite L 'entité à valider avant sa modification
@throws ValidateEntiteException Probleme lors de la validation de l 'entité
@throws EntiteException Probleme lors de la persistence
*/	
	  protected void doValidateModifyEntity(IEntite entite) throws ValidateEntiteException, EntiteException
	  {


	  }	
/**
Methode qui habille une entité avec ces entités refs completes pou ne pas ecraser les valeurs des refs avec des nulls
@param entite lentité à habiller
@throws EntiteException Probleme lors de la persistence
*/
	protected IEntite habiller(IEntite entite) throws EntiteException {
		Heritier heritier = (Heritier) entite;


		
		MvtDecesRentier refMvtDecesRentier1 = heritier.getRefMvtDecesRentier();
		MvtDecesRentier refMvtDecesRentier = new MvtDecesRentier();
		
		if (refMvtDecesRentier1 != null) {
			((IEntite)refMvtDecesRentier).setId(refMvtDecesRentier1.getId());
		}
			
		if(refMvtDecesRentier!=null && refMvtDecesRentier1 != null){
			if (((IEntite)refMvtDecesRentier).getId()!= 0){
				refMvtDecesRentier =(MvtDecesRentier)refMvtDecesRentier.getFactory().newEntiteManager().getEntite(refMvtDecesRentier);
				heritier.setRefMvtDecesRentier(refMvtDecesRentier);
			}
			else {
				heritier.setRefMvtDecesRentier(refMvtDecesRentier1);
			}
			
		}	
		return heritier;	
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
	Heritier heritier = (Heritier) entite;
	



	return  heritier;

}


/**
 * Retoure un heritier par son identifiant
 * @param id
 * @return Heritier
 * @throws ExceptionMetier
 */
public Heritier getHeritierById(long id) throws ExceptionMetier {
	Heritier heritier = null;
	try {

		Class clazz = Class
				.forName("eai.devass.gsr.appli.modele.metier.mouvements.Heritier");
		heritier = (Heritier) getSession().get(clazz, id);

	} catch (Exception e) {
		throw new ExceptionMetier(e.getMessage());
	}

	if (heritier == null) {
		throw new ExceptionMetier(
				eai.devass.gsr.appli.utile.IMessageException.EXP_HERITIER_INTROUVALE);
	}

	return heritier;
}
}