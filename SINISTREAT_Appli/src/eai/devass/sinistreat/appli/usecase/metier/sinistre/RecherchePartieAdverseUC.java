package eai.devass.sinistreat.appli.usecase.metier.sinistre;

import java.util.HashMap;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.modele.metier.sinistre.AyantDroit;
import eai.devass.sinistreat.appli.modele.metier.sinistre.Victime;
import eai.devass.sinistreat.appli.usecase.BaseUC;
import eai.devass.sinistreat.appli.utils.entites.IParam;
import eai.devass.sinistreat.appli.valueobjects.metier.sinistre.AyantDroitVO;
import eai.devass.sinistreat.appli.valueobjects.serialisation.PagerVO;

public class RecherchePartieAdverseUC extends BaseUC {

	protected void executerUC(IValueObject entite, HashMap params)
			throws FonctionnelleException {

		AyantDroitVO ayvo = (AyantDroitVO) entite;
		List<AyantDroit> listAY = null;
		PagerVO pagerVO = (PagerVO) params.get(IParam.PAGER);
		try {
			Victime v = sinistreManager.getVictime(ayvo.getNumeroSinistre());
			if (v.getDeces() != null && v.getDeces()) {
				listAY = (List) sinistreManager
						.getListAyantDroitBySinistre(ayvo.getNumeroSinistre());
				addResultItem(listAY);
			} else {
				addResultItem(v);
			}
		} catch (Exception e) {
			throw new FonctionnelleException(e.getMessage());
		}

		if (pagerVO != null) {
			this.addResultItem(pagerVO);
		}
	}

	public boolean isTransactionnal() {
		return true;
	}

}
