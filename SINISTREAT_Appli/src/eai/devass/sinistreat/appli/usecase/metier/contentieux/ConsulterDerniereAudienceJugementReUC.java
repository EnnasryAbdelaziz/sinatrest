package eai.devass.sinistreat.appli.usecase.metier.contentieux;
import java.util.HashMap;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;

import org.hibernate.exception.ConstraintViolationException;

import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.modele.metier.contentieux.ProcedureJudiciaire;
import eai.devass.sinistreat.appli.modele.metier.sinistre.Sinistre;
import eai.devass.sinistreat.appli.usecase.BaseUC;

public class ConsulterDerniereAudienceJugementReUC extends BaseUC {

	// Recherche par num. de sinistre
	@SuppressWarnings("unchecked")
	protected void executerUC(IValueObject entite, HashMap params)
			throws Exception {
		ProcedureJudiciaire procedure = null;
		Sinistre sinistre = (Sinistre) this.getItem(Sinistre.class);
		try {
			procedure = (ProcedureJudiciaire) contentieuxManager.getDerniereAudienceProcedure(sinistre);

		} catch (ConstraintViolationException e) {
			throw new FonctionnelleException(e);
		} catch (Exception e) {
			throw new FonctionnelleException(e.getMessage());
		}

		addResultItem(procedure);

	}

	public boolean isTransactionnal() {
		return false;
	}

}
