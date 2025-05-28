package eai.devass.gsr.appli.reglegestion.validation;

import java.util.Calendar;
import java.util.Map;

import ma.co.omnidata.framework.services.entites.EntiteException;
import eai.devass.commun.appli.exeptions.ExceptionMetier;
import eai.devass.commun.appli.modele.EntiteBO;
import eai.devass.gsr.appli.modele.metier.dossierRente.Rentier;
import eai.devass.gsr.appli.modele.metier.mouvements.MvtRemiseEnCours;
import eai.devass.gsr.appli.modele.parametrage.EtatRentier;
import eai.devass.gsr.appli.prm.EtatRente;
import eai.devass.gsr.appli.reglegestion.BaseRG;

public class MvtRemiseEnCoursRG extends BaseRG {
	public void regleGestion001RemiseEnCoursRente(EntiteBO entiteBO,
			Map params) throws ExceptionMetier, EntiteException {
		
		MvtRemiseEnCours mouvement = (MvtRemiseEnCours)entiteBO;
		
		//Emission quittance
		mouvement.setEmissionQuittance(false);
		
		//Mettre le rentier à l'état en cours
		Rentier rentier = mouvement.getRefRentier();
		EtatRentier etatRentier = new EtatRentier();
		etatRentier.setId(EtatRente.Valide_En_Cours.getCode());
		rentier.setRefEtatRentier(etatRentier);
		rentier.setDateEtat(Calendar.getInstance());
		
	}
}
