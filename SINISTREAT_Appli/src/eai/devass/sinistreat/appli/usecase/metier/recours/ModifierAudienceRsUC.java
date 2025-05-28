package eai.devass.sinistreat.appli.usecase.metier.recours;

import java.util.HashMap;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;

import org.hibernate.exception.ConstraintViolationException;

import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.modele.metier.contentieux.AudienceJugement;
import eai.devass.sinistreat.appli.usecase.BaseUC;
import eai.devass.sinistreat.appli.valueobjects.metier.contentieux.AudienceJugementVO;

public class ModifierAudienceRsUC extends BaseUC {

	protected void executerUC(IValueObject entite, HashMap params)
			throws Exception {
		AudienceJugementVO audienceVO = (AudienceJugementVO)(entite);
		AudienceJugement audienceResult = null;
		AudienceJugement audience = (AudienceJugement) this.getItem(AudienceJugement.class);

		try {
			audienceResult = (AudienceJugement) recoursManager
					.modifierAudience(audienceVO,audience);

		} catch (ConstraintViolationException e) {
			throw new FonctionnelleException(e);
		} catch (Exception e) {
			throw new FonctionnelleException(e.getMessage());
		}

		addResultItem(audienceResult);
	}

	public boolean isTransactionnal() {
		return true;
	}

}
