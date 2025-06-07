package eai.devass.sinistreat.appli.usecase.parametrage;


import java.util.HashMap;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;

import org.hibernate.exception.ConstraintViolationException;

import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.modele.metier.sinistre.Sinistre;
import eai.devass.sinistreat.appli.usecase.BaseUC;
import eai.devass.sinistreat.appli.utils.entites.IParam;
import eai.devass.sinistreat.appli.valueobjects.serialisation.PagerVO;
 
public class RechercheParamUC extends BaseUC  {
	
	
protected void executerUC(IValueObject entite, HashMap params) throws Exception {
		
		List<Sinistre> listSinistre = null;
		PagerVO pagerVO = (PagerVO) params.get(IParam.PAGER);
		try{
			
				listSinistre=(List)parametrageManager.getListVille(null);

			

		} catch (ConstraintViolationException e) {
			throw new FonctionnelleException(e);
		} catch (Exception e) {
			throw new FonctionnelleException(e.getMessage());
		}

		addResultItem(listSinistre);
		if (pagerVO != null) {
			this.addResultItem(pagerVO);
		}
	}
	
	
	public boolean isTransactionnal() {
		return true;
	}
	
	


}

