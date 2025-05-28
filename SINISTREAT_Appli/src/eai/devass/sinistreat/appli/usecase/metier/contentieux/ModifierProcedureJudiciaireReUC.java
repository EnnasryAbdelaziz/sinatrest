//package eai.devass.sinistreat.appli.usecase.metier.contentieux;
//
//import java.util.HashMap;
//
//import ma.co.omnidata.framework.services.businessInterface.IValueObject;
//
//import org.hibernate.exception.ConstraintViolationException;
//
//import eai.devass.sinistreat.appli.exception.FonctionnelleException;
//import eai.devass.sinistreat.appli.modele.metier.contentieux.ProcedureJudiciaire;
//import eai.devass.sinistreat.appli.usecase.BaseUC;
//import eai.devass.sinistreat.appli.valueobjects.metier.contentieux.ProcedureJudiciaireVO;
//
//public class ModifierProcedureJudiciaireReUC extends BaseUC {
//
//	boolean isUpdate;
//
//	protected void executerUC(IValueObject entite, HashMap params)
//			throws Exception {
//		ProcedureJudiciaire procedureJudiciaire=(ProcedureJudiciaire)this.getItem(ProcedureJudiciaire.class);
//		ProcedureJudiciaireVO pR = (ProcedureJudiciaireVO) (entite);
//		ProcedureJudiciaireVO procedureResult = null;
//
//		try {
//			procedureResult = (ProcedureJudiciaireVO) contentieuxManager
//					.modifierProcedureJudiciaire(pR,procedureJudiciaire);
//
//		} catch (ConstraintViolationException e) {
//			throw new FonctionnelleException(e);
//		} catch (Exception e) {
//			throw new FonctionnelleException(e.getMessage());
//		}
//
//		addResultItem(procedureResult);
//
//	}
//
//	public boolean isTransactionnal() {
//		return true;
//	}
//}

package eai.devass.sinistreat.appli.usecase.metier.contentieux;

import java.util.HashMap;
import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import org.hibernate.exception.ConstraintViolationException;
import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.modele.metier.contentieux.ProcedureJudiciaire;
import eai.devass.sinistreat.appli.usecase.BaseUC;
import eai.devass.sinistreat.appli.valueobjects.metier.contentieux.ProcedureJudiciaireVO;

public class ModifierProcedureJudiciaireReUC extends BaseUC {

	protected void executerUC(IValueObject entite, HashMap params)
			throws Exception {
		ProcedureJudiciaireVO pR = (ProcedureJudiciaireVO) (entite);
		ProcedureJudiciaire proc = (ProcedureJudiciaire) this.getItem(ProcedureJudiciaire.class);

		try {
			//ne pas permettre la création d'une procédure judiciare avec 
			//le même numéro dossier tribunal dans le même dossier sinistre
		//	Boolean trouver = contentieuxManager.validerDoubleProcedure(proc);
//			if(trouver){
//				throw new FonctionnelleException(EXP_PROCEDURE_JUDICIAIRE_EXISTANTE);
//			}
			ProcedureJudiciaire procedure = (ProcedureJudiciaire) contentieuxManager
					.modifierProcedureJudiciaire(pR,proc);
			// mouvement de mise a jour du dossier sinistre (defect 305)
		    sinistreManager.creerMovementProcedureModification(procedure);
			addResultItem(procedure);

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