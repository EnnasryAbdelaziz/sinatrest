package eai.devass.gsr.appli.usecase.metier.dossierRente;

import java.util.HashMap;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.services.businessInterface.impl.FacadeServiceUseCase;
import ma.co.omnidata.framework.services.entites.EntiteException;
import eai.devass.gsr.appli.modele.metier.dossierRente.Tuteur;

//import eai.devass.gsr.appli.modele.metier.dossierRente.Tuteur;
public class TuteurEditerPdfUC extends FacadeServiceUseCase {

	@Override
	protected void doExecuter(IValueObject arg0, HashMap arg1) {
		 Tuteur lToEdit = (Tuteur) this.getItem(Tuteur.class);		 
		 try {
			lToEdit.getFactory().newEntiteManager().getEntite(lToEdit);
		} catch (EntiteException e) {
//			// TODO Auto-generated catch block
//			logger.error("problème technique",e);
		}
	}

}
