package eai.devass.sinistreat.appli.usecase.metier.recours;

import java.util.HashMap;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.modele.metier.contentieux.ProcedureJudiciaire;
import eai.devass.sinistreat.appli.usecase.BaseUC;
import eai.devass.sinistreat.appli.valueobjects.metier.contentieux.ProcedureJudiciaireVO;

public class CreerProcedureJudiciaireRsUC extends BaseUC {

	public void executerUC(IValueObject entite, HashMap params)
			throws Exception {
		ProcedureJudiciaireVO pRvo = (ProcedureJudiciaireVO) (entite);
		ProcedureJudiciaire proc = (ProcedureJudiciaire) this.getItem(ProcedureJudiciaire.class);
		Object procedureResult = null;
		try {
			procedureResult =  recoursManager.creerProcedureJudiciaire(pRvo,proc);
		}
		catch (Exception e) {
			throw new FonctionnelleException(e.getMessage());
		}

		addResultItem(procedureResult);
	}

	public boolean isTransactionnal() {
		return true;
	}

}
