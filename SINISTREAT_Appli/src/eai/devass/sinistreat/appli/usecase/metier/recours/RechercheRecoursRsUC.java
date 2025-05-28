package eai.devass.sinistreat.appli.usecase.metier.recours;

import java.util.HashMap;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;

import org.hibernate.exception.ConstraintViolationException;

import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.modele.metier.contentieux.Recours;
import eai.devass.sinistreat.appli.usecase.BaseUC;

public class RechercheRecoursRsUC extends BaseUC {

	protected void executerUC(IValueObject entite, HashMap params)
			throws Exception {
		Recours recoursSortant = (Recours) this.getItem(Recours.class);

		List resultList = null;

		try {
			
			resultList = (List) recoursManager.rechercheRecours(recoursSortant);

		} catch (ConstraintViolationException e) {
			throw new FonctionnelleException(e);
		} catch (Exception e) {
			throw new FonctionnelleException(e.getMessage());
		}

		addResultItem(resultList);

	}

	public boolean isTransactionnal() {
		return true;
	}

}
