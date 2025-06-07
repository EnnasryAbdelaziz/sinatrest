package eai.devass.sinistreat.appli.usecase.metier.recours;

import java.util.HashMap;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;

import org.hibernate.exception.ConstraintViolationException;

import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.modele.metier.recours.RecoursAmiable;
import eai.devass.sinistreat.appli.usecase.BaseUC;

public class RechercherRecoursAmiableUC extends BaseUC {

	protected void executerUC(IValueObject entite, HashMap params)
			throws Exception {
		RecoursAmiable recours = (RecoursAmiable) this
				.getItem(RecoursAmiable.class);
		try {
			addResultItem(recoursManager
					.rechercheRecoursAmiable(recours));
		} catch (ConstraintViolationException e) {
			throw new FonctionnelleException(e);
		} catch (Exception e) {
			throw new FonctionnelleException(e.getMessage());
		}

	}

	public boolean isTransactionnal() {
		return true;
	}

}
