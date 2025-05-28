package eai.devass.gsr.appli.reglegestion.annulation;

import java.util.Date;
import java.util.Map;

import eai.devass.commun.appli.exeptions.ExceptionMetier;
import eai.devass.commun.appli.modele.EntiteBO;
import eai.devass.gsr.appli.modele.metier.mouvements.MvtConsignCNRA;
import eai.devass.gsr.appli.modele.parametrage.EtatRentier;
import eai.devass.gsr.appli.modele.parametrage.SituationEtatRentier;
import eai.devass.gsr.appli.prm.EtatRente;
import eai.devass.gsr.appli.reglegestion.BaseRG;

public class MvtConsignCNRARG extends BaseRG {

	public void regleGestion006SetEtatRentier(EntiteBO entiteBO, Map params)
			throws ExceptionMetier {
		MvtConsignCNRA mouvement = (MvtConsignCNRA) entiteBO;
		EtatRentier etatRentier = new EtatRentier(
				EtatRente.Valide_En_Cours.getCode());
		mouvement.getRefRentier().setRefEtatRentier(etatRentier);
		// Situation rentier
		SituationEtatRentier situationEtatRentier = new SituationEtatRentier();
		situationEtatRentier.setRefEtatRentier(etatRentier);
		situationEtatRentier.setRefRentier(mouvement.getRefRentier());
		situationEtatRentier.setDateEtat(new Date());
		situationEtatRentier.setOperateur(entiteBO.getOperateur());
		getSession().save(situationEtatRentier);
	}
}
