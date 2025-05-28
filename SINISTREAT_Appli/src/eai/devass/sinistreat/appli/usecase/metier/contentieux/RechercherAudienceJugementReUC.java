package eai.devass.sinistreat.appli.usecase.metier.contentieux;

import java.util.HashMap;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;

import org.hibernate.exception.ConstraintViolationException;

import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.modele.metier.contentieux.AudienceJugement;
import eai.devass.sinistreat.appli.usecase.BaseUC;
import eai.devass.sinistreat.appli.valueobjects.metier.contentieux.AudienceJugementVO;

public class RechercherAudienceJugementReUC extends BaseUC {
	
	@SuppressWarnings("unchecked")
	protected void executerUC(IValueObject entite, HashMap params)
			throws Exception {
		List<AudienceJugementVO> audienceList = null;
		AudienceJugement audience = (AudienceJugement) this.getItem(AudienceJugement.class);
		try {
			audienceList = (List<AudienceJugementVO>) contentieuxManager
					.getAudienceByIdProcedure(audience);

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
