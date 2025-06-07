package eai.devass.sinistreat.appli.usecase.metier.mission;


import java.util.HashMap;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;

import org.hibernate.exception.ConstraintViolationException;

import eai.devass.missionnement.valueobjects.parametrage.NatureMissionVO;
import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.usecase.BaseUC;

public class RechercheNatureMissionUC extends BaseUC  {
	
	protected void executerUC(IValueObject entite, HashMap params) throws Exception {
		
		//suppresion sonar Dead store to local variable.
		NatureMissionVO natureVO = new NatureMissionVO();
		//natureVO.setRefDomaineActivite(new DomaineActiviteVO(mission.getRefPrestataire().getRefDomaineActivite().getCode()));
		
		try{
			List listNatureMissionVO=(List)missionManager.rechercheNatureMission(natureVO);
			if(listNatureMissionVO!=null) {
				addResultItem(listNatureMissionVO);
			}
		} catch (ConstraintViolationException e) {
			throw new FonctionnelleException(e);
		} catch (Exception e) {
			throw new FonctionnelleException(e.getMessage());
		}

		
		
	}
	
	
	public boolean isTransactionnal() {
		return true;
	}
	
	


}

