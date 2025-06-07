package eai.devass.gsr.appli.businessrule;

import org.apache.log4j.Logger;

import ma.co.omnidata.framework.services.entites.EntiteException;
import eai.devass.gsr.appli.modele.metier.dossierRente.Rentier;
import eai.devass.gsr.appli.modele.metier.mouvements.Mouvement;
import eai.devass.gsr.appli.modele.parametrage.EtatRentier;

public class MvtConsignCNRARg extends MouvementRG {
	private Logger logger = Logger.getLogger("loggerSINAT");
	public void validerMvt(Mouvement mvt) {
		/** Modifier l'état de rentier**/
		Rentier rentier = mvt.getRefRentier();
		EtatRentier etatRentier = new EtatRentier();
		etatRentier.setId(10); // etat consignation CNRA
		rentier.setRefEtatRentier(etatRentier);
		try {
			rentier.getFactory().newEntiteManager().modifyEntite(rentier);
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
