package eai.devass.gsr.appli.usecase.metier.dossierRente;

import java.util.HashMap;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.services.businessInterface.impl.FacadeServiceUseCase;
import ma.co.omnidata.framework.services.entites.EntiteException;
import eai.devass.gsr.appli.modele.metier.dossierRente.Rentier;

public class RentierEditerPdfUC extends FacadeServiceUseCase {

	@Override
	protected void doExecuter(IValueObject arg0, HashMap arg1) throws EntiteException {
		Rentier lToEdit = (Rentier) this.getItem(Rentier.class);
		lToEdit.getFactory().newEntiteManager().getEntite(lToEdit);

	}

}
