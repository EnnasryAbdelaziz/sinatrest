
package eai.devass.sinistreat.appli.usecase.metier.contentieux;

import java.util.HashMap;
import ma.co.omnidata.framework.services.businessInterface.IValueObject;

import org.hibernate.exception.ConstraintViolationException;

import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.modele.metier.contentieux.ProcedureJudiciaire;
import eai.devass.sinistreat.appli.usecase.BaseUC;

public class ConsulterProcedureJudiciaireReUC  extends BaseUC {

	// Recherche par num. de sinistre
	protected void executerUC(IValueObject entite, HashMap params)
			throws Exception {
		ProcedureJudiciaire proc = (ProcedureJudiciaire) this.getItem(ProcedureJudiciaire.class);
		ProcedureJudiciaire procedure = null;
		try {
//			procedureList = (List<ProcedureJudiciaireVO>) recoursManager
//					.consulterProcedureJudiciaire(procedureJudiciaireVO);
			procedure = (ProcedureJudiciaire) contentieuxManager.consulterProcedureJudiciaire(proc);		
//			for (ProcedureJudiciaire pProcedureJudiciaire : procedureList) {
//				AudienceJugementVO audience = new AudienceJugementVO();
//				audience.setIdProcedureJudiciaire(pProcedureJudiciaire
//						.getId());
//				AudienceJugementVO audienceResult = (AudienceJugementVO) recoursManager
//						.rechercheDerniereAudienceJugement(audience);
//				pProcedureJudiciaireVO.setDerniereAudience(audienceResult);
//			}

		} catch (ConstraintViolationException e) {
			throw new FonctionnelleException(e);
		} catch (Exception e) {
			throw new FonctionnelleException(e.getMessage());
		}

		addResultItem(procedure);

	}

	public boolean isTransactionnal() {
		return true;
	}
}

