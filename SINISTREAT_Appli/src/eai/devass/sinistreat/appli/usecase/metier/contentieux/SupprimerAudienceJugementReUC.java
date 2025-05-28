package eai.devass.sinistreat.appli.usecase.metier.contentieux;

import java.util.HashMap;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;

import org.hibernate.exception.ConstraintViolationException;

import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.modele.metier.contentieux.AudienceJugement;
import eai.devass.sinistreat.appli.usecase.BaseUC;
import eai.devass.sinistreat.appli.valueobjects.metier.contentieux.AudienceJugementVO;

public class SupprimerAudienceJugementReUC extends BaseUC {

	@SuppressWarnings("rawtypes")
	protected void executerUC(IValueObject entite, HashMap params)
			throws Exception {
		AudienceJugementVO audienceJugementVO = (AudienceJugementVO) (entite);
		boolean ok;
		AudienceJugement audience = (AudienceJugement) this.getItem(AudienceJugement.class);
		try {
			

AudienceJugement audienceDB=sinistreManager.creerMovementAudianceSuppression(audience);
ok = contentieuxManager 
.supprimerAudienceJugement(audienceJugementVO,audienceDB);
//			ok = contentieuxManager 
//					.supprimerAudienceJugement(audienceJugementVO,audienceDB);
			
			
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
