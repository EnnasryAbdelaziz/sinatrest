package eai.devass.sinistreat.appli.usecase.metier.mission;


import java.util.HashMap;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;

import org.hibernate.exception.ConstraintViolationException;

import eai.devass.missionnement.valueobjects.parametrage.NatureMissionVO;
import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.modele.metier.mission.MissionAT;
import eai.devass.sinistreat.appli.usecase.BaseUC;

public class RechercheRefNatMissionUC extends BaseUC  {
	
	protected void executerUC(IValueObject entite, HashMap params) throws Exception {
		
		MissionAT mission = (MissionAT) this.getItem(MissionAT.class);
		NatureMissionVO natureVO = new NatureMissionVO();
		natureVO.setId(mission.getCodeNatureMission());
		
		try{
			List listNatureMissionVO=(List)missionManager.rechercheRefNatMission(natureVO);
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

