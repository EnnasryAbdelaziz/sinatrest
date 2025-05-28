package eai.devass.gsr.appli.reglegestion.modification;

import java.util.Date;
import java.util.Map;

import eai.devass.commun.appli.exeptions.ExceptionMetier;
import eai.devass.commun.appli.modele.EntiteBO;
import eai.devass.gsr.appli.modele.metier.mouvements.Mouvement;
import eai.devass.gsr.appli.modele.parametrage.EtatMvt;
import eai.devass.gsr.appli.modele.parametrage.SituationMouvement;
import eai.devass.gsr.appli.prm.EtatMouvement;

public class MvtSuppressionRG extends MouvementRG {

	public void regleGestion001SetMotifSituationEtat(EntiteBO entiteBO,
			Map params) throws ExceptionMetier {

		Mouvement mouvement = (Mouvement) entiteBO;
		// Situatrion mouvement
		SituationMouvement situationMouvement = new SituationMouvement();
		situationMouvement.setOperateur(entiteBO.getOperateur());
		situationMouvement
				.setRefEtatMvt(new EtatMvt(EtatMouvement.Cree.getId()));
		situationMouvement.setDateEtat(new Date());
		situationMouvement.setRefMouvement(mouvement);
		situationMouvement.setRefMotifSituationEtat(mouvement
				.getRefSituationMouvement().get(0).getRefMotifSituationEtat());
		getSession().save(situationMouvement);

	}

}
