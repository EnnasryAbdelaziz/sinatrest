package eai.devass.sinistreat.appli.usecase.metier.bnej;

import java.util.HashMap;
import java.util.List;

import org.hibernate.exception.ConstraintViolationException;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.modele.metier.bnej.DossierBnej;
import eai.devass.sinistreat.appli.modele.metier.contentieux.ProcedureJudiciaire;
import eai.devass.sinistreat.appli.modele.metier.contentieux.Recours;
import eai.devass.sinistreat.appli.modele.parametrage.TypeDecisionBnej;
import eai.devass.sinistreat.appli.modele.parametrage.TypeDossierBnej;
import eai.devass.sinistreat.appli.usecase.BaseUC;

public class InitModificationDossierBnejUC extends BaseUC {

	@Override
	protected void executerUC(IValueObject entite, HashMap params)
			throws Exception {
		DossierBnej dossierBnej = (DossierBnej) this.getItem(DossierBnej.class);
		List<TypeDecisionBnej> lst = bnejManager.getListTypeDecisionBnej();
		this.addResultItem(lst);
		// List des procédures judiciaires par numero sinistre
		ProcedureJudiciaire proc = new ProcedureJudiciaire();
		List<ProcedureJudiciaire> procedureList = null;

		try {
			if (dossierBnej.getNumeroSinistre() != null) {
				List list = contentieuxManager
						.rechercheRecoursByNumeroSinistre(dossierBnej
								.getNumeroSinistre());
				if (list == null || list.isEmpty()) {
					logger.info("EXP_RECOURS_REQUIRED");
					throw new FonctionnelleException("EXP_RECOURS_REQUIRED");
				}
				if (list.size() > 1) {
					throw new FonctionnelleException("EXP_RECOURS_PLUS_UN");
				}
				Recours r = (Recours) list.get(0);
				proc.setRefRecours(r);

				procedureList = (List<ProcedureJudiciaire>) contentieuxManager
						.getProcedureByIdRecours(Long.valueOf(r.getId()));
			}
		} catch (ConstraintViolationException e) {
			throw new FonctionnelleException(e);
		} catch (Exception z) {
			throw new FonctionnelleException(z.getMessage());
		}
	
		addResultItem(procedureList);
		

	}

}
