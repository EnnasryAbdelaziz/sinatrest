
package eai.devass.gsr.appli.businessrule;

import java.util.Calendar;

import ma.co.omnidata.framework.services.entites.EntiteException;
import ma.co.omnidata.framework.utile.DateUtile;
import eai.devass.gsr.appli.manager.metier.dossierRente.RentierManager;
import eai.devass.gsr.appli.modele.metier.dossierRente.Rentier;
import eai.devass.gsr.appli.modele.metier.mouvements.Mouvement;
import eai.devass.gsr.appli.modele.parametrage.EtatRentier;

public abstract  class MouvementRG {
	
	private RentierManager rentierManager = new RentierManager();
	
	public void updateEtatRentier (Rentier rentier, Long idEtat) throws EntiteException{
		
		EtatRentier etatRentier = new EtatRentier();
		etatRentier.setId(idEtat);
		rentier.setRefEtatRentier(etatRentier);
		Calendar toDay = DateUtile.dateCalendarCourante();
		rentier.setDateEtat(toDay);
		rentierManager.modifyEntite(rentier);
	}
	
		
		/**Validation du mouvement*/
		public abstract void validerMvt( Mouvement mvt);
		
		
		/** modification du mouvement*/
		public abstract void modifierMvt();
		
		
		
		/**annulation du mouvement*/
		public abstract void annulerMvt(Mouvement mvt);
	
}

