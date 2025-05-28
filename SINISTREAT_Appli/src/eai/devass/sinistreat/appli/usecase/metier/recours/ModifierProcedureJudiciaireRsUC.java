package eai.devass.sinistreat.appli.usecase.metier.recours;

import java.util.HashMap;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;

import org.hibernate.exception.ConstraintViolationException;

import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.modele.metier.contentieux.ProcedureJudiciaire;
import eai.devass.sinistreat.appli.usecase.BaseUC;
import eai.devass.sinistreat.appli.valueobjects.metier.contentieux.ProcedureJudiciaireVO;

public class ModifierProcedureJudiciaireRsUC extends BaseUC {

	protected void executerUC(IValueObject entite, HashMap params)
			throws Exception {
		ProcedureJudiciaireVO pR = (ProcedureJudiciaireVO) (entite);
		ProcedureJudiciaire proc = (ProcedureJudiciaire) this.getItem(ProcedureJudiciaire.class);
//		NatureDossierVO nat = new NatureDossierVO("2");
//		pR.setRefNatureDossier(nat);
//		if (pR.getDerniereAudience()!=null){
//			pR.getDerniereAudience().setNatureAudience("1");
//			pR.getDerniereAudience().setTypeConvocation("1");
//		}
		try {
			ProcedureJudiciaire procedure = (ProcedureJudiciaire) recoursManager
					.modifierProcedureJudiciaire(pR,proc);
			addResultItem(procedure);
//			AudienceJugementVO derniereAudience = pR.getDerniereAudience();
//			if (derniereAudience != null && derniereAudience.getId() != null
//					&& !derniereAudience.getId().isEmpty()) {
//				recoursManager.modifierAudience(derniereAudience);
//			} else if (derniereAudience != null
//					&& (derniereAudience.getId() == null || derniereAudience
//							.getId().isEmpty())) {
//				recoursManager.creerAudience(derniereAudience);
//			}
		} catch (ConstraintViolationException e) {
			throw new FonctionnelleException(e);
		} catch (Exception e) {
			throw new FonctionnelleException(e.getMessage());
		}

		

	}

	public boolean isTransactionnal() {
		return true;
	}
}