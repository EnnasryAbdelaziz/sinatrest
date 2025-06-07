package eai.devass.gsr.appli.usecase.parametrage;

import java.util.HashMap;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.services.businessInterface.impl.FacadeServiceUseCase;
import ma.co.omnidata.framework.services.entites.EntiteException;
import eai.devass.gsr.appli.modele.parametrage.TypeVirement;

public class TypeVirementEditerPdfUC extends FacadeServiceUseCase {

	@Override
	protected void doExecuter(IValueObject arg0, HashMap arg1)
			throws EntiteException {
		TypeVirement lToEdit = (TypeVirement) this.getItem(TypeVirement.class);
		// TypeVirement lEdited = (TypeVirement)
		lToEdit.getFactory().newEntiteManager().getEntite(lToEdit);
	}

}
