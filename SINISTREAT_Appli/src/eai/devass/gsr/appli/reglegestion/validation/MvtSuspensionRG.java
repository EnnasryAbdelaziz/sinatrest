package eai.devass.gsr.appli.reglegestion.validation;

import java.util.Calendar;
import java.util.Map;

import ma.co.omnidata.framework.services.entites.EntiteException;
import eai.devass.commun.appli.exeptions.ExceptionMetier;
import eai.devass.commun.appli.modele.EntiteBO;
import eai.devass.gsr.appli.modele.metier.dossierRente.Rentier;
import eai.devass.gsr.appli.modele.metier.mouvements.MvtSuspension;
import eai.devass.gsr.appli.modele.parametrage.EtatRentier;
import eai.devass.gsr.appli.prm.EtatRente;
import eai.devass.gsr.appli.reglegestion.BaseRG;

public class MvtSuspensionRG extends BaseRG {

	
	public void regleGestion001ValiderMvtSuspension(EntiteBO entiteBO,
			Map params) throws ExceptionMetier, EntiteException {
		
		MvtSuspension mouvement = (MvtSuspension)entiteBO;
		//mouvement = (MvtSuspension) ((MvtSuspension) entiteBO).getFactory().newEntiteManager().getEntite((MvtSuspension) entiteBO);
		
		//Emission quittance
		mouvement.setEmissionQuittance(false);
		
		//Date Suspension
		mouvement.setDatSuspension(Calendar.getInstance());
		
		//Mettre le rentier à l'état suspendu
		Rentier rentier = mouvement.getRefRentier();
		EtatRentier etatRentier = new EtatRentier();
		etatRentier.setId(EtatRente.Suspendue_Ou_Attente.getCode());
		rentier.setRefEtatRentier(etatRentier);
		rentier.setDateEtat(Calendar.getInstance());
		//Mettre le motif
		rentier.setMotifEtat(mouvement.getMotif());
	}
}
