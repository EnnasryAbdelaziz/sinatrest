package eai.devass.gsr.appli.usecase.metier.dossierRente;

import java.util.HashMap;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.services.businessInterface.impl.FacadeServiceUseCase;
import ma.co.omnidata.framework.services.entites.EntiteException;
import eai.devass.gsr.appli.modele.metier.dossierRente.Certificats;

//import eai.devass.gsr.appli.modele.metier.dossierRente.Certificats;
public class CertificatsEditerPdfUC extends FacadeServiceUseCase {

	@Override
	protected void doExecuter(IValueObject arg0, HashMap arg1) throws EntiteException {
		 Certificats lToEdit = (Certificats) this.getItem(Certificats.class);		 
		 lToEdit.getFactory().newEntiteManager().getEntite(lToEdit);
	}

}
