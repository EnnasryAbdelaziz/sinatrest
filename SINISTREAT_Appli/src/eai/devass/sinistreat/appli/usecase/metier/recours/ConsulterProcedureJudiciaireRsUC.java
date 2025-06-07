package eai.devass.sinistreat.appli.usecase.metier.recours;

import java.util.HashMap;
import ma.co.omnidata.framework.services.businessInterface.IValueObject;

import org.hibernate.exception.ConstraintViolationException;

import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.modele.metier.contentieux.ProcedureJudiciaire;
import eai.devass.sinistreat.appli.usecase.BaseUC;

public class ConsulterProcedureJudiciaireRsUC extends BaseUC {

	// Recherche par num. de sinistre
	@SuppressWarnings("unchecked")
	protected void executerUC(IValueObject entite, HashMap params)
			throws Exception {
		ProcedureJudiciaire proc = (ProcedureJudiciaire) this.getItem(ProcedureJudiciaire.class);
		ProcedureJudiciaire procedure = null;
		try {

			procedure = (ProcedureJudiciaire) recoursManager.getProcedureById(proc.getId());			

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
