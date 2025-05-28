package eai.devass.sinistreat.appli.usecase.metier.recours;

import java.util.HashMap;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;

import org.hibernate.exception.ConstraintViolationException;

import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.modele.metier.recours.RecoursAmiable;
import eai.devass.sinistreat.appli.usecase.BaseUC;

public class ModifierRecoursAmiableUC extends BaseUC {

	protected void executerUC(IValueObject entite, HashMap params)
			throws Exception {
		RecoursAmiable recours = (RecoursAmiable) this
				.getItem(RecoursAmiable.class);

		RecoursAmiable recoursResult = null;
		try {
			recoursResult = (RecoursAmiable) recoursManager
					.modifierRecoursAmiable(recours);

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
