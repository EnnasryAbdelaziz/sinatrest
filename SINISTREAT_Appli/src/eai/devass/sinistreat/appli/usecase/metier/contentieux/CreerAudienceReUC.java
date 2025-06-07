package eai.devass.sinistreat.appli.usecase.metier.contentieux;

import java.util.HashMap;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;

import org.hibernate.exception.ConstraintViolationException;

import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.modele.metier.contentieux.AudienceJugement;
import eai.devass.sinistreat.appli.usecase.BaseUC;
import eai.devass.sinistreat.appli.valueobjects.metier.contentieux.AudienceJugementVO;

public class CreerAudienceReUC extends BaseUC {

	protected void executerUC(IValueObject entite, HashMap params)
			throws Exception {
		AudienceJugementVO audienceVO = (AudienceJugementVO) (entite);
		Object audienceResult = null;
		AudienceJugement audience = (AudienceJugement) this
				.getItem(AudienceJugement.class);
		try {
			// ne pas permettre la création de deux audiences avec la même date
			// d'audience dans la même procédure judiciaire
			Boolean audiance = contentieuxManager.validerDoubleAudiance(audience);
			if(audiance){
				throw new FonctionnelleException(EXP_AUDIANCE_EXISTANTE);
			}
			audienceResult = contentieuxManager.creerAudience(audienceVO,
					audience);
			//sinistreManager.creerMovementAudianceCreation(audience);
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