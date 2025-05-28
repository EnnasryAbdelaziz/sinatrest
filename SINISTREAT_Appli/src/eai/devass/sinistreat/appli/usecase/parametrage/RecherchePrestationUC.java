package eai.devass.sinistreat.appli.usecase.parametrage;

import java.util.HashMap;

import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;

import eai.devass.sinistreat.appli.usecase.BaseUC;
import eai.devass.sinistreat.appli.utils.IConstantes;
import eai.devass.sinistreat.appli.valueobjects.metier.reglement.ReglementVO;
import eai.devass.sinistreat.appli.valueobjects.metier.sinistre.SinistreVO;

public class RecherchePrestationUC extends BaseUC {
	@SuppressWarnings("unchecked")
	protected void executerUC(IValueObject entite, HashMap params)
			throws Exception {
		ReglementVO reglementVO = (ReglementVO) entite;
		SinistreVO sin = reglementVO.getRefSinistre();
		String typeBenf = reglementVO.getTypeBeneficiaire();
		String etatSin = null;
		List listPrestation = null;
		if (sin.getRefEtatSinistre() != null
				&& sin.getRefEtatSinistre().getRefEtat() != null
				&& sin.getRefEtatSinistre().getRefEtat().getCode().equals(IConstantes.ETAT_SINISTRE_JUSTICE)) {
			etatSin = sin.getRefEtatSinistre().getRefEtat().getCode();
		}

		listPrestation = (List) parametrageManager.getlistPrestationVictime(
				typeBenf, etatSin);

		this.addResultItem(listPrestation);
	}
}
