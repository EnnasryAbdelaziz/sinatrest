package eai.devass.gsr.appli.reglegestion.annulation;

import java.util.Map;

import eai.devass.commun.appli.exeptions.ExceptionMetier;
import eai.devass.commun.appli.modele.EntiteBO;
import eai.devass.gsr.appli.modele.metier.mouvements.Mouvement;
import eai.devass.gsr.appli.reglegestion.BaseRG;
 


public class MvtMajCapitalRG extends BaseRG {


	public void regleGestion000ValidateMvtMajCapital(EntiteBO entiteBO, Map params) throws ExceptionMetier {
		
		Mouvement mouvement = (Mouvement) entiteBO;
		mouvement.setEmissionQuittance(true);
		// System.out.println("rtetete");
	}
	

}

