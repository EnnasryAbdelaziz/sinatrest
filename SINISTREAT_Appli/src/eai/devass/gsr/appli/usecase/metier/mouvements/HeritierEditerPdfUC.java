package eai.devass.gsr.appli.usecase.metier.mouvements;

import java.util.HashMap;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.services.businessInterface.impl.FacadeServiceUseCase;
import ma.co.omnidata.framework.services.entites.EntiteException;
import eai.devass.gsr.appli.modele.metier.mouvements.Heritier;

//import eai.devass.gsr.appli.modele.metier.mouvements.Heritier;
public class HeritierEditerPdfUC extends FacadeServiceUseCase {

	@Override
	protected void doExecuter(IValueObject arg0, HashMap arg1) throws EntiteException {
		 Heritier lToEdit = (Heritier) this.getItem(Heritier.class);		 
		 lToEdit.getFactory().newEntiteManager().getEntite(lToEdit);
	}

}
