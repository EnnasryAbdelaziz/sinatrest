package eai.devass.gsr.appli.businessrule;

import org.apache.log4j.Logger;

import ma.co.omnidata.framework.services.entites.EntiteException;
import eai.devass.gsr.appli.modele.metier.mouvements.Mouvement;

public class MvtComplementCNRARg extends MouvementRG {
	
	private Logger logger = Logger.getLogger("loggerSINAT");

	public void validerMvt(Mouvement mvt) {
		
		try {
			this.updateEtatRentier(mvt.getRefRentier(), 2L);
		} catch (EntiteException e) {
			// TODO Auto-generated catch block
			logger.error("problème technique",e);
		}

	}

	public void modifierMvt() {
		// TODO Auto-generated method stub

	}

	public void annulerMvt(Mouvement mvt) {
		// TODO Auto-generated method stub

	}

}
