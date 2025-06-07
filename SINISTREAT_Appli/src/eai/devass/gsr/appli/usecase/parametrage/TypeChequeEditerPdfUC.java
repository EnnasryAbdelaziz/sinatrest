package eai.devass.gsr.appli.usecase.parametrage;

import java.util.HashMap;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.services.businessInterface.impl.FacadeServiceUseCase;
import ma.co.omnidata.framework.services.entites.EntiteException;
import eai.devass.gsr.appli.modele.parametrage.TypeCheque;

public class TypeChequeEditerPdfUC extends FacadeServiceUseCase {

	@Override
    protected void doExecuter(IValueObject arg0, HashMap arg1)
            throws EntiteException {
		 TypeCheque lToEdit = (TypeCheque) this.getItem(TypeCheque.class);		 
		 lToEdit.getFactory().newEntiteManager().getEntite(lToEdit);
	}

}
