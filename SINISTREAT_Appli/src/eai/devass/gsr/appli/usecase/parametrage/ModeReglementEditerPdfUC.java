package eai.devass.gsr.appli.usecase.parametrage;

import java.util.HashMap;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.services.businessInterface.impl.FacadeServiceUseCase;
import ma.co.omnidata.framework.services.entites.EntiteException;
import eai.devass.gsr.appli.modele.parametrage.ModeReglement;

//import eai.devass.gsr.appli.modele.parametrage.ModeReglement;
public class ModeReglementEditerPdfUC extends FacadeServiceUseCase {

	@Override
	protected void doExecuter(IValueObject arg0, HashMap arg1) throws EntiteException {
		 ModeReglement lToEdit = (ModeReglement)
		 this.getItem(ModeReglement.class);	
		 lToEdit.getFactory().newEntiteManager().getEntite(lToEdit);
	}

}
