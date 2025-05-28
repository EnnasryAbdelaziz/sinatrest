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

public class InitGestionEtatSinistreUC extends BaseUC {

	
	@SuppressWarnings("unchecked")
	@Override
	protected void executerUC(IValueObject entite, HashMap params)
			throws Exception {
		// TODO Auto-generated method stub
		SinistreVO sinvo= (SinistreVO)entite;
		List listEtatSinistre=null;
		try {			
			String etatActuel="";
			Sinistre sin = (Sinistre)sinistreManager.getSinistreByNum(sinvo.getNumeroSinistre());
			if(sin!=null && sin.getRefEtatSinistre()!=null &&
						sin.getRefEtatSinistre().getRefEtat()!=null &&
						sin.getRefEtatSinistre().getRefEtat().getCode()!=null){
				etatActuel=sin.getRefEtatSinistre().getRefEtat().getCode();
			}
			List listEtatSinistreActuel= new ArrayList();
			listEtatSinistre = (List)parametrageManager.getListEtatSinistre(new EtatSin("0"));
			listEtatSinistreActuel.add(listEtatSinistre.get(0));
			listEtatSinistre = (List)parametrageManager.getListEtatSinistre(new EtatSin("1"));
			listEtatSinistreActuel.add(listEtatSinistre.get(0));
			listEtatSinistre = (List)parametrageManager.getListEtatSinistre(new EtatSin("2"));
			listEtatSinistreActuel.add(listEtatSinistre.get(0));
			this.addResultItem(listEtatSinistreActuel);
			
			List listEtatSinistreCible= new ArrayList();
			if(!etatActuel.equals("1")){
				listEtatSinistre = (List)parametrageManager.getListEtatSinistre(new EtatSin("1"));
				listEtatSinistreCible.add(listEtatSinistre.get(0));
			}
			if(!etatActuel.equals("2")){
				listEtatSinistre = (List)parametrageManager.getListEtatSinistre(new EtatSin("2"));
				listEtatSinistreCible.add(listEtatSinistre.get(0));
			}
			if(!etatActuel.equals("3")){
				listEtatSinistre = (List)parametrageManager.getListEtatSinistre(new EtatSin("3"));
				listEtatSinistreCible.add(listEtatSinistre.get(0));
			}
			if(!etatActuel.equals("4")){
				listEtatSinistre = (List)parametrageManager.getListEtatSinistre(new EtatSin("4"));
				listEtatSinistreCible.add(listEtatSinistre.get(0));
			}
			this.addResultItem(listEtatSinistreCible);	
			// ajout sinistre pour converter
			this.addResultItem(sin);			
			
		} catch (ConstraintViolationException e) {
			throw new FonctionnelleException(e);
			
		} catch (Exception e) {
			throw new FonctionnelleException(e.getMessage());
		}
		
	}

}
