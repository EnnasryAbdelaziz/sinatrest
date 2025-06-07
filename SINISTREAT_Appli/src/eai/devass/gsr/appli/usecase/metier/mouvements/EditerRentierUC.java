package eai.devass.gsr.appli.usecase.metier.mouvements;

import java.util.HashMap;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import eai.devass.commun.appli.exeptions.ExceptionMetier;
import eai.devass.commun.appli.uc.SimpleBaseUC;
import eai.devass.commun.appli.util.CommonUtils;
import eai.devass.gsr.appli.modele.metier.dossierRente.Rentier;
import eai.devass.gsr.appli.valueobjects.metier.dossierRente.RentierVO;

public class EditerRentierUC extends SimpleBaseUC {

	protected void execute(IValueObject entite, HashMap params) throws Exception {

		RentierVO rentierVO = (RentierVO) entite;
		if(rentierVO == null) {
			throw new ExceptionMetier("Le rentier est obligatoire");
		}
		
		if(!CommonUtils.isNumeric(rentierVO.getId())) {
			throw new ExceptionMetier("L'identifiant du rentier est obligatoire");
		}
		
		Rentier rentier = (Rentier) getSession().get(Rentier.class,
				Long.valueOf(rentierVO.getId()));
		this.addResultItem(rentier);
	}
	
}