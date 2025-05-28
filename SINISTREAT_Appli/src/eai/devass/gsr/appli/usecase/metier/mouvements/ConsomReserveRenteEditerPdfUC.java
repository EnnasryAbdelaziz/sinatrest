package eai.devass.gsr.appli.usecase.metier.mouvements;

import java.util.HashMap;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.services.businessInterface.impl.FacadeServiceUseCase;
import ma.co.omnidata.framework.services.entites.EntiteException;
import eai.devass.gsr.appli.modele.metier.mouvements.ConsomReserveRente;

//import eai.devass.gsr.appli.modele.metier.mouvements.ConsomReserveRente;
public class ConsomReserveRenteEditerPdfUC extends FacadeServiceUseCase {

	@Override
	protected void doExecuter(IValueObject arg0, HashMap arg1) throws EntiteException {
		 ConsomReserveRente lToEdit = (ConsomReserveRente)
		 this.getItem(ConsomReserveRente.class);		 
		 lToEdit.getFactory().newEntiteManager().getEntite(lToEdit);
	}

}
