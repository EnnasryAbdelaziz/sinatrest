package eai.devass.sinistreat.appli.usecase.ihm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.hibernate.exception.ConstraintViolationException;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.modele.metier.sinistre.Sinistre;
import eai.devass.sinistreat.appli.modele.parametrage.EtatSin;
import eai.devass.sinistreat.appli.usecase.BaseUC;
import eai.devass.sinistreat.appli.valueobjects.metier.sinistre.SinistreVO;

public class InitGestionEtatReouvertureUC extends BaseUC {

	@Override
	protected void executerUC(IValueObject entite, HashMap params)
			throws Exception {
		// TODO Auto-generated method stub
		SinistreVO sinvo= (SinistreVO)entite;
		List listEtatSinistre=null;
		try {			

			List listEtatSinistreActuel= new ArrayList();
			listEtatSinistre = (List)parametrageManager.getListEtatSinistre(new EtatSin("3"));
			listEtatSinistreActuel.add(listEtatSinistre.get(0));
			listEtatSinistre = (List)parametrageManager.getListEtatSinistre(new EtatSin("4"));
			listEtatSinistreActuel.add(listEtatSinistre.get(0));			
			this.addResultItem(listEtatSinistreActuel);
			List listEtatSinistreCible= new ArrayList();
			listEtatSinistre = (List)parametrageManager.getListEtatSinistre(new EtatSin("1"));
			listEtatSinistreCible.add(listEtatSinistre.get(0));
			listEtatSinistre = (List)parametrageManager.getListEtatSinistre(new EtatSin("2"));
			listEtatSinistreCible.add(listEtatSinistre.get(0));			
			this.addResultItem(listEtatSinistreCible);			
			Sinistre sin = (Sinistre)sinistreManager.getSinistreByNum(sinvo.getNumeroSinistre());
			this.addResultItem(sin);			
			
		} catch (ConstraintViolationException e) {
			throw new FonctionnelleException(e);
			
		} catch (Exception e) {
			throw new FonctionnelleException(e.getMessage());
		}
		
	}

}
