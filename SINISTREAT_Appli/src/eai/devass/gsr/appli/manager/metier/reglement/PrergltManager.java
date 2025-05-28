package eai.devass.gsr.appli.manager.metier.reglement;

import ma.co.omnidata.framework.services.entites.EntiteException;
import ma.co.omnidata.framework.services.entites.EntiteManagerAbst;
import ma.co.omnidata.framework.services.entites.IEntite;
import ma.co.omnidata.framework.services.entites.ProcessEntiteException;
import ma.co.omnidata.framework.services.entites.ValidateEntiteException;
import ma.co.omnidata.framework.utile.DateUtile;
import eai.devass.gsr.appli.modele.metier.reglement.Prerglt;
import eai.devass.gsr.appli.modele.parametrage.TypeCheque;
import eai.devass.gsr.appli.modele.parametrage.TypeVirement;


/**
Manager de l ' entité Prerglt
@author Nom Prenom (email) 
*/
public class PrergltManager extends EntiteManagerAbst {



/**
Completude de l ' objet avant sa creation
@param entite lentité à completer
@return returns lentité completé
@throws ProcessEntiteException problème lors de la complétude de l' entité
@throws EntiteException 
*/
	protected IEntite doProcessCreateEntity(IEntite entite) throws ProcessEntiteException, EntiteException {
		Prerglt prerglt = (Prerglt) entite;

		 prerglt.setDateCreation(DateUtile.dateCalendarCourante());



		return prerglt;

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
		  
//		if (((Prerglt)entite).getRefTypeCheque() == null){
//		    	ValidateEntiteException ve = new ValidateEntiteException ("FIELD.OBLIGATOIRE");
//				ve.setValues(new String []{"typeCheque"});
//				throw ve;
//		    	
//		  	}
//		if (((Prerglt)entite).getRefTypeVirement() == null){
//		    	ValidateEntiteException ve = new ValidateEntiteException ("FIELD.OBLIGATOIRE");
//				ve.setValues(new String []{"typeVirement"});
//				throw ve;
//		    	
//		  	}
//		if (((Prerglt)entite).getRefTypeReglement() == null){
//		    	ValidateEntiteException ve = new ValidateEntiteException ("FIELD.OBLIGATOIRE");
//				ve.setValues(new String []{"typeReglement"});
//				throw ve;
//		    	
//		  	}
//		if (((Prerglt)entite).getRefModeReglement() == null){
//		    	ValidateEntiteException ve = new ValidateEntiteException ("FIELD.OBLIGATOIRE");
//				ve.setValues(new String []{"modeReglement"});
//				throw ve;
//		    	
//}
//		if (((Prerglt)entite).getRefPays() == null){
//		    	ValidateEntiteException ve = new ValidateEntiteException ("FIELD.OBLIGATOIRE");
//				ve.setValues(new String []{"pays"});
//				throw ve;
//		    	
//		  	}
//		if (((Prerglt)entite).getRefVille() == null){
//		    	ValidateEntiteException ve = new ValidateEntiteException ("FIELD.OBLIGATOIRE");
//				ve.setValues(new String []{"ville"});
//				throw ve;
//		    	
//		  	}
//		if (((Prerglt)entite).getRefIntermediaire() == null){
//		    	ValidateEntiteException ve = new ValidateEntiteException ("FIELD.OBLIGATOIRE");
//				ve.setValues(new String []{"intermediaire"});
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
//		if (((Prerglt)entite).getRefTypeCheque() == null){
//			ValidateEntiteException ve = new ValidateEntiteException ("FIELD.OBLIGATOIRE");
//			ve.setValues(new String []{"typeCheque"});
//			throw ve;
//		}
//		if (((Prerglt)entite).getRefTypeVirement() == null){
//			ValidateEntiteException ve = new ValidateEntiteException ("FIELD.OBLIGATOIRE");
//			ve.setValues(new String []{"typeVirement"});
//			throw ve;
//		}
//		if (((Prerglt)entite).getRefTypeReglement() == null){
//			ValidateEntiteException ve = new ValidateEntiteException ("FIELD.OBLIGATOIRE");
//			ve.setValues(new String []{"typeReglement"});
//			throw ve;
//		}
//		if (((Prerglt)entite).getRefModeReglement() == null){
//			ValidateEntiteException ve = new ValidateEntiteException ("FIELD.OBLIGATOIRE");
//			ve.setValues(new String []{"modeReglement"});
//			throw ve;
//		}
//		if (((Prerglt)entite).getRefPays() == null){
//			ValidateEntiteException ve = new ValidateEntiteException ("FIELD.OBLIGATOIRE");
//			ve.setValues(new String []{"pays"});
//			throw ve;
//		}
//		if (((Prerglt)entite).getRefVille() == null){
//			ValidateEntiteException ve = new ValidateEntiteException ("FIELD.OBLIGATOIRE");
//			ve.setValues(new String []{"ville"});
//			throw ve;
//		}
//		if (((Prerglt)entite).getRefIntermediaire() == null){
//			ValidateEntiteException ve = new ValidateEntiteException ("FIELD.OBLIGATOIRE");
//			ve.setValues(new String []{"intermediaire"});
//			throw ve;
//		}


	  }	
/**
Methode qui habille une entité avec ces entités refs completes pou ne pas ecraser les valeurs des refs avec des nulls
@param entite lentité à habiller
@throws EntiteException Probleme lors de la persistence
*/
	protected IEntite habiller(IEntite entite) throws EntiteException {
		Prerglt prerglt = (Prerglt) entite;


		
		TypeCheque refTypeCheque1 = prerglt.getRefTypeCheque();
		TypeCheque refTypeCheque = new TypeCheque();

		
		if (refTypeCheque1 != null) {
			((IEntite)refTypeCheque).setId(refTypeCheque1.getId());
		}
		if(refTypeCheque!=null && refTypeCheque1 != null){
			if (((IEntite)refTypeCheque).getId()!=0){
				    refTypeCheque =(TypeCheque)(new TypeCheque()).getFactory().newEntiteManager().getEntite((TypeCheque)refTypeCheque);
				prerglt.setRefTypeCheque(refTypeCheque);
			}
			else {
				prerglt.setRefTypeCheque(refTypeCheque1);
			}
		}
		
		TypeVirement refTypeVirement1 = prerglt.getRefTypeVirement();
		TypeVirement refTypeVirement = new TypeVirement();

		
		if (refTypeVirement1 != null) {
			((IEntite)refTypeVirement).setId(refTypeVirement1.getId());
		}
		if(refTypeVirement!=null && refTypeVirement1 != null){
			if (((IEntite)refTypeVirement).getId()!=0){
				    refTypeVirement =(TypeVirement)(new TypeVirement()).getFactory().newEntiteManager().getEntite((TypeVirement)refTypeVirement);
				prerglt.setRefTypeVirement(refTypeVirement);
			}
			else {
				prerglt.setRefTypeVirement(refTypeVirement1);
			}
		}	
		
//		TypeReglement refTypeReglement1 = prerglt.getRefTypeReglement();
//		TypeReglement refTypeReglement = new TypeReglement();
//
//		
//		if (refTypeReglement1 != null)
//		    ((IEntite)refTypeReglement).setId(refTypeReglement1.getId());
//		if(refTypeReglement!=null && refTypeReglement1 != null){
//			if (((IEntite)refTypeReglement).getId()!=0){
//				    refTypeReglement =(TypeReglement)(new TypeReglement()).getFactory().newEntiteManager().getEntite((TypeReglement)refTypeReglement);
//				prerglt.setRefTypeReglement(refTypeReglement);
//			}
//			else {
//				prerglt.setRefTypeReglement(refTypeReglement1);
//			}
//		}		
//		ModeReglement refModeReglement1 = prerglt.getRefModeReglement();
//		ModeReglement refModeReglement = new ModeReglement();
//
//		
//		if (refModeReglement1 != null)
//		    ((IEntite)refModeReglement).setId(refModeReglement1.getId());
//		if(refModeReglement!=null && refModeReglement1 != null){
//			if (((IEntite)refModeReglement).getId()!=0){
//				    refModeReglement =(ModeReglement)(new ModeReglement()).getFactory().newEntiteManager().getEntite((ModeReglement)refModeReglement);
//				prerglt.setRefModeReglement(refModeReglement);
//			}
//			else {
//				prerglt.setRefModeReglement(refModeReglement1);
//			}
//		}		
//		Pays refPays1 = prerglt.getRefPays();
//		Pays refPays = new Pays();
//
//		
//		if (refPays1 != null)
//		    refPays.setCode(refPays1.getCode());
//		if(refPays!=null && refPays1 != null){
//			if (((IEntite)refPays).getId()!=0){
//				    //refPays =(Pays)(new Pays()).getFactory().newEntiteManager().getEntite((Pays)refPays);
//				prerglt.setRefPays(refPays);
//			}
//			else {
//				prerglt.setRefPays(refPays1);
//			}
//		}		
//		Ville refVille1 = prerglt.getRefVille();
//		Ville refVille = new Ville();
//
//		
//		if (refVille1 != null)
//		    refVille.setCode(refVille1.getCode());
//		if(refVille!=null && refVille1 != null){
//			if (((IEntite)refVille).getId()!=0){
//				    //refVille =(Ville)(new Ville()).getFactory().newEntiteManager().getEntite((Ville)refVille);
//				prerglt.setRefVille(refVille);
//			}
//			else {
//				prerglt.setRefVille(refVille1);
//			}
//		}		
//		Intermediaire refIntermediaire1 = prerglt.getRefIntermediaire();
//		Intermediaire refIntermediaire = new Intermediaire();
//
//		
//		if (refIntermediaire1 != null)
//		    refIntermediaire.setCode(refIntermediaire1.getCode());
//		if(refIntermediaire!=null && refIntermediaire1 != null){
//			if (((IEntite)refIntermediaire).getId()!=0){
//				    //refIntermediaire =(Intermediaire)(new Intermediaire()).getFactory().newEntiteManager().getEntite((Intermediaire)refIntermediaire);
//				prerglt.setRefIntermediaire(refIntermediaire);
//			}
//			else {
//				prerglt.setRefIntermediaire(refIntermediaire1);
//			}
//		}		
		return prerglt;	
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
	Prerglt prerglt = (Prerglt) entite;
	



	return  prerglt;

}

}