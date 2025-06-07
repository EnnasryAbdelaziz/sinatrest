package eai.devass.sinistreat.appli.usecase.parametrage;

import java.util.HashMap;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;

import org.hibernate.exception.ConstraintViolationException;

import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.modele.parametrage.MedecinTraitant;
import eai.devass.sinistreat.appli.usecase.BaseUC;
import eai.devass.sinistreat.appli.utils.entites.IParam;
import eai.devass.sinistreat.appli.valueobjects.serialisation.PagerVO;

public class RechercheMedecinTraitantUC extends BaseUC {

	@SuppressWarnings("unchecked")
	protected void executerUC(IValueObject entite, HashMap params)
			throws Exception {

		List<MedecinTraitant> listeMedecin = null;
		PagerVO pagerVO = (PagerVO) params.get(IParam.PAGER);
		MedecinTraitant medecin = (MedecinTraitant) this.getItem(MedecinTraitant.class);

		try {
			listeMedecin = (List) parametrageManager
				.getListMedecinTraitant(medecin, null);
									
		} catch (ConstraintViolationException e) {
			throw new FonctionnelleException(e);
		} catch (Exception e) {
			throw new FonctionnelleException(e.getMessage());
		}
		addResultItem(listeMedecin);
		if (pagerVO != null) {
			this.addResultItem(pagerVO);
		}
	}

	public boolean isTransactionnal() {
		return true;
	}
}
