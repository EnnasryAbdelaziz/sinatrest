package eai.devass.gsr.appli.usecase.edition;

import java.util.HashMap;
import java.util.Map;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import eai.devass.edition.valueobjects.EditionVO;
import eai.devass.sinistreat.appli.usecase.edition.EditerJrxmlUC;

public class EditerEtatRentesMisesAJour extends EditerJrxmlUC{

	

	@Override
	protected Map<String, Object> getRapportParametresValues(IValueObject vo) {
		EditionVO editionVO = (EditionVO) vo;
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("logoPath", editionVO.getLogoPath());
		params.put("dateDebut", editionVO.getDateDebut());
		params.put("dateFin", editionVO.getDateFin());
		
		
		if ((editionVO.getSituationMouvement()).equals("1")) {
			editionVO.setSituationMouvement("1");
			editionVO.setSituationMouvement1("5");
			editionVO.setSituationMouvement2("6");
		} else {
			editionVO.setSituationMouvement("2");
			editionVO.setSituationMouvement1("3");
			editionVO.setSituationMouvement2("4");
		}
		
		params.put("situationMouvement", editionVO.getSituationMouvement());
		params.put("situationMouvement1", editionVO.getSituationMouvement1());
		params.put("situationMouvement2", editionVO.getSituationMouvement2());
		
		return params;
	}

	@Override
	protected String getCodeTemplate() {
		// TODO Auto-generated method stub
		return "08";
	}

	@Override
	protected boolean imprimer(IValueObject vo) {
		// TODO Auto-generated method stub
		return false;
	}

}
