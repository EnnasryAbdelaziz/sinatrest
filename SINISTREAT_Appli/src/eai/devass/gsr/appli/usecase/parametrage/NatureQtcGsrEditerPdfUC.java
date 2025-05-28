package eai.devass.gsr.appli.usecase.parametrage;

import java.util.HashMap;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.services.businessInterface.impl.FacadeServiceUseCase;
import ma.co.omnidata.framework.services.entites.EntiteException;
import eai.devass.gsr.appli.modele.parametrage.NatureQtcGsr;

//import eai.devass.gsr.appli.modele.parametrage.NatureQtcGsr;
public class NatureQtcGsrEditerPdfUC extends FacadeServiceUseCase {

	@Override
	protected void doExecuter(IValueObject arg0, HashMap arg1)throws EntiteException {
		 NatureQtcGsr lToEdit = (NatureQtcGsr)
		 this.getItem(NatureQtcGsr.class);	
		 lToEdit.getFactory().newEntiteManager().getEntite(lToEdit);
	}
}
