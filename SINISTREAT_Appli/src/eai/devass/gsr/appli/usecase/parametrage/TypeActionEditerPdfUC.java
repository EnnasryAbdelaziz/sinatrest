package eai.devass.gsr.appli.usecase.parametrage;

import java.util.HashMap;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.services.businessInterface.impl.FacadeServiceUseCase;
import ma.co.omnidata.framework.services.entites.EntiteException;
import eai.devass.gsr.appli.modele.parametrage.TypeAction;

//import eai.devass.gsr.appli.modele.parametrage.TypeAction;
public class TypeActionEditerPdfUC extends FacadeServiceUseCase {

	@Override
	protected void doExecuter(IValueObject arg0, HashMap arg1) throws EntiteException {
		 TypeAction lToEdit = (TypeAction) this.getItem(TypeAction.class);		 
		 lToEdit.getFactory().newEntiteManager().getEntite(lToEdit);
	}

}
