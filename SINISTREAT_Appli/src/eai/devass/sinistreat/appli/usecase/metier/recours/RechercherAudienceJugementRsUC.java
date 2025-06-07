package eai.devass.sinistreat.appli.usecase.metier.recours;

import java.util.HashMap;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;

import org.hibernate.exception.ConstraintViolationException;

import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.modele.metier.contentieux.AudienceJugement;
import eai.devass.sinistreat.appli.usecase.BaseUC;

public class RechercherAudienceJugementRsUC extends BaseUC {

	
	@SuppressWarnings("unchecked")
	protected void executerUC(IValueObject entite, HashMap params)
			throws Exception {
		List<AudienceJugement> audienceList = null;
		AudienceJugement audience = (AudienceJugement) this.getItem(AudienceJugement.class);
		try {
			audienceList = (List) recoursManager.getAudienceByIdProcedure(audience);
		} catch (ConstraintViolationException e) {
			throw new FonctionnelleException(e);
		} catch (Exception e) {
			throw new FonctionnelleException(e.getMessage());
		}
		addResultItem(audienceList);
	}

	public boolean isTransactionnal() {
		return true;
	}
}
