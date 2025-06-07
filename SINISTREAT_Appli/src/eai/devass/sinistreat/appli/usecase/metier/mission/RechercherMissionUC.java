package eai.devass.sinistreat.appli.usecase.metier.mission;


import java.util.HashMap;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;

import org.hibernate.exception.ConstraintViolationException;

import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.modele.metier.mission.MissionAT;
import eai.devass.sinistreat.appli.usecase.BaseUC;
import eai.devass.sinistreat.appli.utils.entites.IParam;
import eai.devass.sinistreat.appli.valueobjects.serialisation.PagerVO;

public class RechercherMissionUC extends BaseUC  {
	
	protected void executerUC(IValueObject entite, HashMap params) throws Exception {
		MissionAT mission = (MissionAT) this.getItem(MissionAT.class);
		List<MissionAT> missionList=null;
		PagerVO pagerVO = (PagerVO) params.get(IParam.PAGER);
		try {
			if (pagerVO != null) {
				if (pagerVO.getNumPage() == null) {
					throw new FonctionnelleException(
							EXP_PAGER_NUMERO_PAGE_OBLIGATOIRE);
				} else if (pagerVO.getPageSize() == null) {
					throw new FonctionnelleException(
							EXP_PAGER_PAGE_SIZE_OBLIGATOIRE);
				}

				missionList = (List) missionManager.rechercheMission(mission,pagerVO);
				Integer nbreObject = missionManager
						.getNombreMission(mission);
				pagerVO.setNbrLignes(nbreObject.toString());
				Integer pageSize= Integer.valueOf(pagerVO.getPageSize());
				if(pageSize!=0) {
					pagerVO.setNbrPages(String.valueOf(nbreObject/pageSize+1));
				}
			} else {
				missionList = (List) missionManager.rechercheMission(mission, null);
			}
		} catch (ConstraintViolationException e) {
			throw new FonctionnelleException(e);
		} catch (Exception e) {
			throw new FonctionnelleException(e.getMessage());
		}
		addResultItem(missionList);
		if (pagerVO != null) {
			this.addResultItem(pagerVO);
		}
	}
	
	public boolean isTransactionnal() {
		return true;
	}
	
	


}

