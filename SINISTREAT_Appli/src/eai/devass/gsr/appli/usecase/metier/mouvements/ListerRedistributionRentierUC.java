package eai.devass.gsr.appli.usecase.metier.mouvements;

import java.util.HashMap;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.services.core.ServicesFactory;
import eai.devass.commun.appli.exeptions.ExceptionMetier;
import eai.devass.commun.appli.uc.SimpleBaseUC;
import eai.devass.commun.appli.util.CommonUtils;
import eai.devass.gsr.appli.manager.metier.dossierRente.RentierManager;
import eai.devass.gsr.appli.modele.metier.dossierRente.Rentier;
import eai.devass.gsr.appli.valueobjects.parametrage.ParamVO;

public class ListerRedistributionRentierUC extends SimpleBaseUC {

	protected void execute(IValueObject entite, HashMap params) throws Exception {

		ParamVO sinistreVO = (ParamVO) entite;
		if(sinistreVO == null || CommonUtils.isEmpty(sinistreVO.getCode())) {
			throw new ExceptionMetier("Le numéro de sinistre est obligatoire");
		}
		
		RentierManager rentierManager = (RentierManager) ServicesFactory
				.getService(RentierManager.class);
		
		// Recuperer la liste des renties par num sinistre
		List<Rentier> listRentier = rentierManager
				.getListRentierByNumSinistre(sinistreVO.getCode());
		
		this.addResultItem(listRentier);
	}
	
}