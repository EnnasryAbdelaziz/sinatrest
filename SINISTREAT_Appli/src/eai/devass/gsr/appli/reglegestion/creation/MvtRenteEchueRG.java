package eai.devass.gsr.appli.reglegestion.creation;

import java.util.Map;

import ma.co.omnidata.framework.services.entites.EntiteException;
import eai.devass.commun.appli.exeptions.ExceptionMetier;
import eai.devass.commun.appli.modele.EntiteBO;
import eai.devass.gsr.appli.modele.metier.dossierRente.Rentier;
import eai.devass.gsr.appli.modele.metier.mouvements.MvtRenteEchue;


public class MvtRenteEchueRG extends MouvementRG {
	public void regleGestion000RentierDescendant(EntiteBO entiteBO,
			Map params) throws ExceptionMetier, EntiteException {

		MvtRenteEchue lToCreate = (MvtRenteEchue) entiteBO;
		Rentier rentier = lToCreate.getRefRentier();
		rentier = (Rentier) rentier.getFactory().newEntiteManager().getEntite(rentier);
		if(rentier.getLienParente() == null || rentier.getLienParente() < 20){
			throw new ExceptionMetier("Rentier non descendant !! ce mouvement s'applique uniquement sur les descendants");
		}
	}
}
