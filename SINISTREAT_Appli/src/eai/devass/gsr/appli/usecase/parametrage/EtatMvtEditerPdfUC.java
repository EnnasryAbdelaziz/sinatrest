package eai.devass.gsr.appli.usecase.parametrage;

import java.util.HashMap;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.services.businessInterface.impl.FacadeServiceUseCase;
import ma.co.omnidata.framework.services.entites.EntiteException;
import eai.devass.gsr.appli.modele.parametrage.EtatMvt;

//import eai.devass.gsr.appli.modele.parametrage.EtatMvt;
public class EtatMvtEditerPdfUC extends FacadeServiceUseCase {

	@Override
	protected void doExecuter(IValueObject arg0, HashMap arg1) throws EntiteException {
		 EtatMvt lToEdit = (EtatMvt) this.getItem(EtatMvt.class);		 
		 lToEdit.getFactory().newEntiteManager().getEntite(lToEdit);
	}

}
