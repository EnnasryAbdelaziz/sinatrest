
package eai.devass.sinistreat.appli.usecase.metier.sinistre;


import java.util.HashMap;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;

import org.hibernate.exception.ConstraintViolationException;

import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.usecase.BaseUC;
import eai.devass.sinistreat.appli.valueobjects.metier.sinistre.PoliceVO;
 
public class RecherchePoliceUC extends BaseUC  {
	
	
protected void executerUC(IValueObject entite, HashMap params) throws Exception {
		
		PoliceVO polvo=(PoliceVO)entite;
		List<PoliceVO> listPolice = null;
		HashMap mapDates = (HashMap) this.getItem(HashMap.class);
		try{
			if(polvo==null){
				throw new FonctionnelleException("EXP_POLICE_REQUIRED");
			}

			
			listPolice=(List)sinistreManager.getListPolice(polvo,mapDates);

		} catch (ConstraintViolationException e) {
			throw new FonctionnelleException(e);
		} catch (Exception e) {
			throw new FonctionnelleException(e.getMessage());
		}

		addResultItem(listPolice);
	}
	
	
	public boolean isTransactionnal() {
		return true;
	}
	
	


}

