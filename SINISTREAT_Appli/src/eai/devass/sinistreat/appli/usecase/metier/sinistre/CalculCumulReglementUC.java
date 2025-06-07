package eai.devass.sinistreat.appli.usecase.metier.sinistre;

import java.util.ArrayList;
import java.util.HashMap;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;

import org.apache.commons.lang.StringUtils;
import org.hibernate.exception.ConstraintViolationException;

import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.modele.metier.sinistre.SinAnterieurVictime;
import eai.devass.sinistreat.appli.modele.metier.sinistre.Sinistre;
import eai.devass.sinistreat.appli.usecase.BaseUC;

public class CalculCumulReglementUC extends BaseUC {

	protected void executerUC(IValueObject entite, HashMap params)
			throws Exception {

		Sinistre sinistre = (Sinistre) this.getItem(Sinistre.class);
		Sinistre sinResult = null;
		Sinistre sindb = null;
		if (sinistre.getId() == 0) {
			if (sinistre.getNumeroSinistre() == null
					|| "".equals(sinistre.getNumeroSinistre())) {
				throw new FonctionnelleException("EXP_NUM_SIN_REQUIRED");
			}
			sindb = sinistreManager.getSinistreByNum(sinistre
					.getNumeroSinistre());
		} else {
			sindb = sinistreManager.getSinistreById(sinistre.getId());
		}
		if (sindb == null) {
			throw new FonctionnelleException("EXP_SINISTRE_INEXISTANT");
		}
		if (StringUtils.isEmpty(sindb.getNumeroSinistre())) {
			throw new FonctionnelleException("EXP_NUM_SIN_INEXISTANT");
		}
		sindb.setReserveGraveActuel(sinistre.getReserveGraveActuel());
		sindb.setReserveOrdinaireActuel(sinistre.getReserveOrdinaireActuel());
		sindb.setReserveProtheseActuel(sinistre.getReserveProtheseActuel());

		try {
			/*
			if(sinistre.getRefVictime() != null) {
				if(sinistre.getRefVictime().getListSinistreAnterieur() != null) {
			sindb.getRefVictime().setListSinistreAnterieur(new ArrayList<SinAnterieurVictime>());
			//sindb.getRefVictime().setListSinistreAnterieur(sinistre.getRefVictime().getListSinistreAnterieur());
				}else {
					sindb.getRefVictime().setListSinistreAnterieur(new ArrayList<SinAnterieurVictime>());
				}
			}*/
			sinResult = (Sinistre) sinistreManager.calculCumulReglement(sindb);
			if(sinistre.getRefVictime() != null) {
			if(sinistre.getRefVictime().getListSinistreAnterieur() != null) {
				sinResult.getRefVictime().setListSinistreAnterieur(new ArrayList<SinAnterieurVictime>());
			sinResult.getRefVictime().setListSinistreAnterieur(sinistre.getRefVictime().getListSinistreAnterieur());
			} else {
				sinResult.getRefVictime().setListSinistreAnterieur(new ArrayList<SinAnterieurVictime>());
			}
			}
		} catch (ConstraintViolationException e) {
			throw new FonctionnelleException(e);
		} catch (Exception e) {
			throw new FonctionnelleException(e.getMessage());
		}

		addResultItem(sinResult);

	}

	public boolean isTransactionnal() {
		return true;
	}

}
