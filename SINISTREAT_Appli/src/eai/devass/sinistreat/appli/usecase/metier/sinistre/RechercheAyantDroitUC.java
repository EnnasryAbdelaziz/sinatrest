package eai.devass.sinistreat.appli.usecase.metier.sinistre;


import java.util.HashMap;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;

import org.hibernate.exception.ConstraintViolationException;

import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.modele.metier.sinistre.AyantDroit;
import eai.devass.sinistreat.appli.usecase.BaseUC;
import eai.devass.sinistreat.appli.utils.entites.IParam;
import eai.devass.sinistreat.appli.valueobjects.metier.sinistre.AyantDroitVO;
import eai.devass.sinistreat.appli.valueobjects.serialisation.PagerVO;
 
public class RechercheAyantDroitUC extends BaseUC  {
	
	
protected void executerUC(IValueObject entite, HashMap params) throws Exception {
		
		AyantDroitVO ayvo= (AyantDroitVO)entite;
		List<AyantDroit> listAY = null;
		PagerVO pagerVO = (PagerVO) params.get(IParam.PAGER);
		this.getItem(HashMap.class);
		try{
			listAY=(List)sinistreManager.getListAyantDroitBySinistre(ayvo.getNumeroSinistre());
		} catch (ConstraintViolationException e) {
			throw new FonctionnelleException(e);
		} catch (Exception e) {
			throw new FonctionnelleException(e.getMessage());
		}

		addResultItem(listAY);
		if (pagerVO != null) {
			this.addResultItem(pagerVO);
		}
	}
	
	
	public boolean isTransactionnal() {
		return true;
	}
	
	


}

