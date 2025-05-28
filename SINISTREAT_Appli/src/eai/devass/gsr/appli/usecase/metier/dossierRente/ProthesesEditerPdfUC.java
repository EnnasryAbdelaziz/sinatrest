package eai.devass.gsr.appli.usecase.metier.dossierRente;

import java.util.HashMap;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.services.businessInterface.impl.FacadeServiceUseCase;
import ma.co.omnidata.framework.services.entites.EntiteException;
import eai.devass.gsr.appli.modele.metier.dossierRente.Prothese;

//import eai.devass.gsr.appli.modele.metier.dossierRente.Prothese;
public class ProthesesEditerPdfUC extends FacadeServiceUseCase {

	@Override
	protected void doExecuter(IValueObject arg0, HashMap arg1) throws EntiteException{
		 Prothese lToEdit = (Prothese) this.getItem(Prothese.class);		  
         lToEdit.getFactory().newEntiteManager().getEntite(lToEdit);

	}

}
