package eai.devass.gsr.appli.usecase.metier.mouvements;

import java.util.HashMap;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.services.core.ServicesFactory;
import eai.devass.commun.appli.exeptions.ExceptionMetier;
import eai.devass.commun.appli.uc.SimpleBaseUC;
import eai.devass.commun.appli.util.CommonUtils;
import eai.devass.gsr.appli.manager.metier.transverse.TransverseManager;
import eai.devass.gsr.appli.modele.metier.dossierRente.DossierRente;
import eai.devass.gsr.appli.modele.metier.dossierRente.Rentier;
import eai.devass.gsr.appli.valueobjects.metier.dossierRente.RentierVO;
import eai.devass.sinistreat.appli.manager.ParametrageManager;

@SuppressWarnings("all")
public class ChercherRentierUC extends SimpleBaseUC {

	
	protected void execute(IValueObject entite, HashMap params) throws Exception {

		RentierVO rentierVO = (RentierVO) entite;
		if(rentierVO == null) {
			throw new ExceptionMetier("Le rentier est obligatoire");
		}
		
		TransverseManager transverseManager = (TransverseManager) ServicesFactory
				.getService(TransverseManager.class);
		
		Rentier rentier = new Rentier();
		
		
		if(CommonUtils.isNumeric(rentierVO.getId())) {
			rentier.setId(Long.valueOf(rentierVO.getId()));
		}
		
		if(rentierVO.getRefDossierRente() != null 
				&& CommonUtils.isNumeric(rentierVO.getRefDossierRente().getId())) {
			rentier.setRefDossierRente(new DossierRente(
					Long.valueOf(rentierVO.getRefDossierRente().getId())));
		}
		
		List<Rentier> listRentier = transverseManager.getSimilarObject(rentier, "id desc");
		if(!commonUtils.isEmpty(listRentier)) {
			rentier = listRentier.get(0);
		}
		
		this.addResultItem(rentier);
	}
	
}