package eai.devass.sinistreat.appli.usecase.metier.contentieux;
import java.util.HashMap;
import ma.co.omnidata.framework.services.businessInterface.IValueObject;

import org.hibernate.exception.ConstraintViolationException;

import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.modele.metier.contentieux.AudienceJugement;
import eai.devass.sinistreat.appli.usecase.BaseUC;

public class ConsulterAudienceJugementReUC extends BaseUC {

	// Recherche par num. de sinistre
	@SuppressWarnings("unchecked")
	protected void executerUC(IValueObject entite, HashMap params)
			throws Exception {
		AudienceJugement audienceres = null;
		AudienceJugement audience = (AudienceJugement) this.getItem(AudienceJugement.class);
		try {
			audienceres = (AudienceJugement) contentieuxManager.getAudienceById(audience);

		} catch (ConstraintViolationException e) {
			throw new FonctionnelleException(e);
		} catch (Exception e) {
			throw new FonctionnelleException(e.getMessage());
		}

		addResultItem(audienceres);

		
	}

	public boolean isTransactionnal() {
		return true;
	}

}
