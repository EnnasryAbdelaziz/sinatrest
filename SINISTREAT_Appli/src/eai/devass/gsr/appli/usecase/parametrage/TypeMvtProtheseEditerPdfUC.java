package eai.devass.gsr.appli.usecase.parametrage;

import java.util.HashMap;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.services.businessInterface.impl.FacadeServiceUseCase;
import ma.co.omnidata.framework.services.entites.EntiteException;
import eai.devass.gsr.appli.modele.parametrage.TypeMvtProthese;

public class TypeMvtProtheseEditerPdfUC extends FacadeServiceUseCase {

	@Override
	protected void doExecuter(IValueObject arg0, HashMap arg1) throws EntiteException {
		 TypeMvtProthese lToEdit = (TypeMvtProthese)
		 this.getItem(TypeMvtProthese.class);		 
		 lToEdit.getFactory().newEntiteManager().getEntite(lToEdit);
	}

}
