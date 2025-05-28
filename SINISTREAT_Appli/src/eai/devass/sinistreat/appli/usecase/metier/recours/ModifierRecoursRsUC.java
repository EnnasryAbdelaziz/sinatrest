package eai.devass.sinistreat.appli.usecase.metier.recours;

import java.util.HashMap;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;

import org.hibernate.exception.ConstraintViolationException;

import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.modele.metier.contentieux.Recours;
import eai.devass.sinistreat.appli.usecase.BaseUC;
import eai.devass.sinistreat.appli.valueobjects.metier.contentieux.RecoursVO;

public class ModifierRecoursRsUC extends BaseUC {

	protected void executerUC(IValueObject entite, HashMap params)
			throws Exception {
		Recours recoursSortant = (Recours) this
				.getItem(Recours.class);
		RecoursVO recoursVo=  (RecoursVO) entite;
		Recours recoursResult = null;
		try {
			recoursResult = (Recours) recoursManager
					.modifierRecours(recoursSortant,recoursVo);

		} catch (ConstraintViolationException e) {
			throw new FonctionnelleException(e);
		} catch (Exception e) {
			throw new FonctionnelleException(e.getMessage());
		}

		addResultItem(recoursResult);
	}

	public boolean isTransactionnal() {
		return true;
	}

}
