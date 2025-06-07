package eai.devass.sinistreat.appli.usecase.ihm;

import java.util.HashMap;
import java.util.List;

import org.hibernate.exception.ConstraintViolationException;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.modele.metier.sinistre.Sinistre;
import eai.devass.sinistreat.appli.usecase.BaseUC;
import eai.devass.sinistreat.appli.valueobjects.metier.sinistre.SinistreVO;

public class InitCreateRecuperationUC extends BaseUC {

	@Override
	protected void executerUC(IValueObject entite, HashMap params)
			throws Exception {
		// TODO Auto-generated method stub
		SinistreVO sinvo= (SinistreVO)entite;
	   
		try {			

//			Recours recoursSortant = new Recours();
//			Sinistre sinistre = new Sinistre();
//			Long id = Long.parseLong(sinvo.getId());
//			sinistre.setId(id);
//			recoursSortant.setRefSinistre(sinistre);
//			// initialiser le recours en cours
//			List<Recours> liste = recoursManager.rechercheRecoursEncours(recoursSortant);
//			//if(!liste.isEmpty()&& liste.size() != 0){
//			this.addResultItem(liste);
			//}
			//initialiser les listes
			List listBanque = (List)parametrageManager.getListBanque(null);
			this.addResultItem(listBanque);
			List listeChoixRubrique=(List)parametrageManager.getlistPrestation(null);
			this.addResultItem(listeChoixRubrique);
			Sinistre sin = (Sinistre)sinistreManager.getSinistreByNum(sinvo.getNumeroSinistre());
			this.addResultItem(sin);
			List listNatureRecuperation = (List)parametrageManager.getListNatureRecuperation(null);
			this.addResultItem(listNatureRecuperation);
			List listCompagnie = (List)parametrageManager.getListAssurance(null);
			this.addResultItem(listCompagnie);
			
		
		} catch (ConstraintViolationException e) {
			throw new FonctionnelleException(e);
			
		} catch (Exception e) {
			throw new FonctionnelleException(e.getMessage());
		}
		
	}

}
