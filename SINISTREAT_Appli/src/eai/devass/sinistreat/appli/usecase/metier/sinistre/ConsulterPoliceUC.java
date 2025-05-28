package eai.devass.sinistreat.appli.usecase.metier.sinistre;

import java.util.HashMap;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;

import org.apache.commons.lang.StringUtils;

import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.usecase.BaseUC;
import eai.devass.sinistreat.appli.valueobjects.metier.sinistre.PoliceVO;

public class ConsulterPoliceUC extends BaseUC {

	protected void executerUC(IValueObject entite, HashMap params)
			throws FonctionnelleException {

		PoliceVO polvo = (PoliceVO) entite;
		try {
			if (polvo == null) {
				throw new FonctionnelleException("EXP_NUM_POLICE_REQUIRED");
			}
			if (StringUtils.isEmpty(polvo.getNumeroPolice().trim())) {
				throw new FonctionnelleException("EXP_NUM_POLICE_REQUIRED");
			}
			PoliceVO polres = null;
			String surv = polvo.getDateSurvenanceSinistre();
		polvo.setDateSurvenanceSinistre(surv);
		
			try {
				polres = (PoliceVO) sinistreManager.getPoliceByNum(polvo);
			polres.setDateSurvenanceSinistre(surv);
			} catch (Exception e) {
				throw new FonctionnelleException(e.getMessage());
			}
			addResultItem(polres);

		} catch (Exception e) {
			throw new FonctionnelleException(e.getMessage());
		}

	}

	private String formaterDate(String surv) {
		String dateSurv = null;
		String yyyySrv = surv.substring(0, 4);
		String mmddSur = surv.substring(4, 8);
		mmddSur = mmddSur.replaceAll("0", "");
		dateSurv = yyyySrv + mmddSur;
		return dateSurv;
	}

	public boolean isTransactionnal() {
		return true;
	}

	private PoliceVO getPolice(String numpol) {
		PoliceVO polres = new PoliceVO();
		polres.setNumeroPolice(numpol);
		polres.setCodeClient("98552");
		polres.setNomClient("MAROCLEAR");
		polres.setCodeIntermediaire("A5814");
		polres.setNomIntermediaire("ASSURAMA");
		polres.setEtat("EN COURS");
		polres.setDateEtat("21/12/2011");
		polres.setDateEffet("05/05/2010");
		polres.setDateEcheance("01/01/2019");
		polres.setDateExpiration("01/01/2019");

		return polres;
	}

}
