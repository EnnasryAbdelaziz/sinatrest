package eai.devass.sinistreat.appli.usecase.metier.mission;


import java.util.HashMap;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;

import org.hibernate.exception.ConstraintViolationException;

import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.modele.metier.mission.MissionAT;
import eai.devass.sinistreat.appli.usecase.BaseUC;

public class ModifierMissionUC extends BaseUC  {
	
	protected void executerUC(IValueObject entite, HashMap params) throws Exception {
		MissionAT mission = (MissionAT) this.getItem(MissionAT.class);
		MissionAT missRes=null;
		try{
			missRes=(MissionAT)missionManager.modifierMission(mission);
			addResultItem(missRes);
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

