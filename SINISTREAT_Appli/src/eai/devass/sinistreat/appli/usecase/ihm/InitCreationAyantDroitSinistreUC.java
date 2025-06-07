package eai.devass.sinistreat.appli.usecase.ihm;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;

import org.hibernate.exception.ConstraintViolationException;

import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.modele.metier.sinistre.AyantDroit;
import eai.devass.sinistreat.appli.modele.metier.sinistre.Sinistre;
import eai.devass.sinistreat.appli.modele.parametrage.DegreParente;
import eai.devass.sinistreat.appli.usecase.BaseUC;
import eai.devass.sinistreat.appli.utils.entites.IParam;
import eai.devass.sinistreat.appli.valueobjects.metier.sinistre.AyantDroitVO;


public class InitCreationAyantDroitSinistreUC extends BaseUC  {
	
	
	
	protected void executerUC(IValueObject entite, HashMap params) throws Exception {
		
		// Récupérer la class de l'objet BO correspondant à l'objet VO
		AyantDroitVO ayvo= (AyantDroitVO)entite;
		AyantDroit ay= new AyantDroit();
		HashMap map = new HashMap();
		map.put(IParam.NUM_SINISTRE, ayvo.getNumeroSinistre());
		try {			

			Sinistre sin = (Sinistre)sinistreManager.getSinistreByNum( ayvo.getNumeroSinistre());
			List<DegreParente> listDegreParenteAll = (List)parametrageManager.getListDegreParente(null);
			List<DegreParente> listDegreParente = new ArrayList<DegreParente>(0);
			for(DegreParente deg : listDegreParenteAll){
				
				if(!"0".equals(deg.getCode())){
					// calcul du taux de rente des ayant droits
					//La nouvelle loi pour tout sinistre survenu après le 22/01/2015 :
					String dateSurvenu = "22/01/2015";
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
					Date dateSur = sdf.parse(dateSurvenu);
					if(sin.getRefEvenement().getDateAccident().compareTo(dateSur)<0){
					if(!"60".equals(deg.getCode()) && !"70".equals(deg.getCode())){
						listDegreParente.add(deg);
					}
					}
					else if (sin.getRefEvenement().getDateAccident().compareTo(dateSur)>=0){
						listDegreParente.add(deg);
					}
				}
			}
			this.addResultItem(listDegreParente);
			List listVille = (List)parametrageManager.getListVille(null);
			this.addResultItem(listVille);
			List listAy= (List) sinistreManager.getListAyantDroit(ay, map, null);
			this.addResultItem(listAy);
			
			this.addResultItem(sin);			
			List listNatureEcheance = (List)parametrageManager.getlistNatureEcheance(null);
			this.addResultItem(listNatureEcheance);
			
		} catch (ConstraintViolationException e) {
			throw new FonctionnelleException(e);
			
		} catch (Exception e) {
			throw new FonctionnelleException(e.getMessage());
		}
		
		
	}
	
	public boolean isTransactionnal() {
		return false;
	}
	
	

}

