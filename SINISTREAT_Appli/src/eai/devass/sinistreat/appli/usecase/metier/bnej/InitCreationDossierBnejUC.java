package eai.devass.sinistreat.appli.usecase.metier.bnej;

import java.util.HashMap;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import eai.devass.sinistreat.appli.modele.parametrage.TypeDossierBnej;
import eai.devass.sinistreat.appli.usecase.BaseUC;

public class InitCreationDossierBnejUC extends BaseUC {

	@Override
	protected void executerUC(IValueObject entite, HashMap params)
			throws Exception {
		
		List<TypeDossierBnej> lst = bnejManager.getListTypeDossierBnej();
		
		this.addResultItem(lst);	
		
		
		

	}

}
