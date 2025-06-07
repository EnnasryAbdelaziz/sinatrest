package eai.devass.sinistreat.appli.usecase.metier.contentieux;

import java.util.HashMap;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;

import org.hibernate.exception.ConstraintViolationException;

import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.modele.metier.contentieux.ProcedureJudiciaire;
import eai.devass.sinistreat.appli.modele.metier.contentieux.Recours;
import eai.devass.sinistreat.appli.usecase.BaseUC;

public class RechercherProcedureJudiciaireReUC extends BaseUC {
	
	@SuppressWarnings("unchecked")
	protected void executerUC(IValueObject entite, HashMap params)
			throws Exception {
		
		ProcedureJudiciaire proc = (ProcedureJudiciaire) this.getItem(ProcedureJudiciaire.class);
		List<ProcedureJudiciaire> procedureList = null;
		try {
			List list =contentieuxManager.rechercheRecours(proc.getRefRecours());
			if(list==null || list.isEmpty()) {
				throw new FonctionnelleException("EXP_RECOURS_REQUIRED");
			}	
			if(list.size()>1) {
				throw new FonctionnelleException("EXP_RECOURS_PLUS_UN");
			}	
			Recours r=(Recours)list.get(0);
			proc.setRefRecours(r);
			
			procedureList = (List<ProcedureJudiciaire>) contentieuxManager.
				getProcedureByIdRecours(Long.valueOf(r.getId()));
		} catch (ConstraintViolationException e) {
			throw new FonctionnelleException(e);
		} catch (Exception z) {
			logger.info(z.getMessage());
//			throw new FonctionnelleException(z.getMessage());
		}

		addResultItem(procedureList);

	}

	public boolean isTransactionnal() {
		return true;
	}
}
