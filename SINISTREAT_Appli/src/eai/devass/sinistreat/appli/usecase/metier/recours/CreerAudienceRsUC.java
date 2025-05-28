package eai.devass.sinistreat.appli.usecase.metier.recours;

import java.util.HashMap;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;

import org.hibernate.exception.ConstraintViolationException;

import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.modele.metier.contentieux.AudienceJugement;
import eai.devass.sinistreat.appli.usecase.BaseUC;
import eai.devass.sinistreat.appli.valueobjects.metier.contentieux.AudienceJugementVO;

public class CreerAudienceRsUC extends BaseUC {

	protected void executerUC(IValueObject entite, HashMap params)
			throws Exception {
		AudienceJugementVO audienceVO = (AudienceJugementVO)(entite);
		AudienceJugement audience = (AudienceJugement) this.getItem(AudienceJugement.class);
		try {
			audience = (AudienceJugement) recoursManager
											.creerAudience(audienceVO,audience);

		} catch (ConstraintViolationException e) {
			throw new FonctionnelleException(e);
		} catch (Exception e) {
			throw new FonctionnelleException(e.getMessage());
		}

		addResultItem(audience);

	}

	public boolean isTransactionnal() {
		return true;
	}
}