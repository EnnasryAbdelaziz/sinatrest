package eai.devass.sinistreat.appli.usecase.metier.recours;

import java.util.HashMap;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;

import org.hibernate.exception.ConstraintViolationException;

import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.modele.metier.contentieux.ProcedureJudiciaire;
import eai.devass.sinistreat.appli.usecase.BaseUC;

public class RechercherProcedureJudiciaireRsUC extends BaseUC {

	
	@SuppressWarnings("unchecked")
	protected void executerUC(IValueObject entite, HashMap params)
			throws Exception {
		ProcedureJudiciaire proc = (ProcedureJudiciaire) this.getItem(ProcedureJudiciaire.class);
		List<ProcedureJudiciaire> procedureList = null;
		try {
			procedureList = (List<ProcedureJudiciaire>) recoursManager
					.getProcedureByIdRecours(proc.getRefRecours().getId());

		} catch (ConstraintViolationException e) {
			throw new FonctionnelleException(e);
		} catch (Exception e) {
			throw new FonctionnelleException(e.getMessage());
		}

		addResultItem(procedureList);

	}

	public boolean isTransactionnal() {
		return true;
	}
}
