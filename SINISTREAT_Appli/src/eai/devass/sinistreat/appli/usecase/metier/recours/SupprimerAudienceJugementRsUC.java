package eai.devass.sinistreat.appli.usecase.metier.recours;

import java.util.HashMap;
import ma.co.omnidata.framework.services.businessInterface.IValueObject;

import org.hibernate.exception.ConstraintViolationException;

import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.modele.metier.contentieux.AudienceJugement;
import eai.devass.sinistreat.appli.usecase.BaseUC;
import eai.devass.sinistreat.appli.valueobjects.metier.contentieux.AudienceJugementVO;

public class SupprimerAudienceJugementRsUC extends BaseUC {

	 
	@SuppressWarnings("unchecked")
	protected void executerUC(IValueObject entite, HashMap params)
			throws Exception {
		AudienceJugementVO audienceJugementVO = (AudienceJugementVO) (entite);
		boolean ok;
		AudienceJugement audience = (AudienceJugement) this.getItem(AudienceJugement.class);
		try {
			ok = recoursManager
					.supprimerAudienceJugement(audienceJugementVO,audience);

		} catch (ConstraintViolationException e) {
			throw new FonctionnelleException(e);
		} catch (Exception z) {
			throw new FonctionnelleException(z.getMessage());
		}

		addResultItem(ok);
	}

	public boolean isTransactionnal() {
		return true;
	}

}
