package eai.devass.gsr.appli.reglegestion.modification;

import java.util.Map;

import eai.devass.commun.appli.exeptions.ExceptionMetier;
import eai.devass.commun.appli.modele.EntiteBO;
import eai.devass.gsr.appli.modele.metier.mouvements.MvtCnraReglementaire;
import eai.devass.gsr.appli.reglegestion.BaseRG;
import eai.devass.gsr.appli.reglegestion.creation.MouvementRG;



public class MvtCnraReglementaireRG extends BaseRG {
	
	private MouvementRG mouvementRG = new MouvementRG();
	
	public void nonregleGestion000VerifierEtatMouvement(EntiteBO entiteBO, Map params) throws ExceptionMetier {
		
		MvtCnraReglementaire mouvement = (MvtCnraReglementaire) entiteBO;
		mouvementRG.setRentierDB(mouvement.getRefRentier());
		//mouvementRG.regleGestion001VerifyMouvementEnCours(entiteBO, params);
	}
	
	
	
	
	
	
}
