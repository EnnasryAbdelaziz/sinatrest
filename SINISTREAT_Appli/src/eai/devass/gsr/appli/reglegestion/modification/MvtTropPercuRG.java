package eai.devass.gsr.appli.reglegestion.modification;

import java.util.Map;

import ma.co.omnidata.framework.services.entites.EntiteException;
import eai.devass.commun.appli.exeptions.ExceptionMetier;
import eai.devass.commun.appli.modele.EntiteBO;
import eai.devass.gsr.appli.modele.metier.mouvements.MvtTropPercu;



public class MvtTropPercuRG extends MouvementRG {

	private static final String MODE_REMBOURSEMENT_PRELEVEMENT = "1";
	
	
	// Pour le mode par prelevement, vérifier le montant prorata
	public void regleGestion002VeriferMntProrata(EntiteBO entiteBO, Map params)
			throws ExceptionMetier, EntiteException {

		MvtTropPercu mouvement = (MvtTropPercu) entiteBO;
		if(!MODE_REMBOURSEMENT_PRELEVEMENT.equals(mouvement.getModeRemboursement())) {
			return;
		}
		
		Double mntProrata = mouvement.getMntProrata();
		Double mntPeriodiqueRecuperation = mouvement.getMntPeriodiqueRecuperation();
		Double nbrTrim = mouvement.getNbrTrimestre();
		Double mntRecuperation = mntPeriodiqueRecuperation * nbrTrim + mntProrata;
		Double mntControle = Math.abs(mntRecuperation - mouvement.getMntAccord());
		if(mntControle > 10) {
			throw new ExceptionMetier("Le montant à récupérer ............ !!!");
		}
		
	}
	
	
	
	
}
