package eai.devass.sinistreat.appli.usecase.ihm;

import java.util.HashMap;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;

import org.hibernate.exception.ConstraintViolationException;

import eai.devass.missionnement.valueobjects.parametrage.NatureMissionVO;
import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.usecase.BaseUC;

public class InitModifierMissionUC extends BaseUC {

	@Override
	protected void executerUC(IValueObject entite, HashMap params)
			throws Exception {
		//correction sonar Dead store to local variable.
		NatureMissionVO natureVO = new NatureMissionVO();
		try {
			List listEtatMission = (List) parametrageManager
					.getListEtatMission();
			this.addResultItem(listEtatMission);
			List listDomainePrest = (List) parametrageManager
					.getListDomainePrest();
			this.addResultItem(listDomainePrest);
			List listPresation = (List) parametrageManager.getListPresation();
			this.addResultItem(listPresation);
			// RechercheNatureMissionUC
//			natureVO.setRefDomaineActivite(new DomaineActiviteVO(mission
//					.getRefPrestataire().getRefDomaineActivite().getCode()));
			List listNatureMissionVO = (List) missionManager
					.rechercheNatureMission(natureVO);
			if (listNatureMissionVO != null) {
				addResultItem(listNatureMissionVO);
			}
		} catch (ConstraintViolationException e) {
			throw new FonctionnelleException(e);

		} catch (Exception e) {
			throw new FonctionnelleException(e.getMessage());
		}

	}

}
