package eai.devass.sinistreat.appli.usecase.edition;


import java.util.HashMap;
import java.util.Map;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import eai.devass.edition.valueobjects.EditionVO;
import eai.devass.sinistreat.appli.usecase.edition.EditerJrxmlUC;

public class EditerEtatReglementIttUC extends EditerJrxmlUC{

	

	@Override
	protected Map<String, Object> getRapportParametresValues(IValueObject vo) {
		EditionVO editionVO = (EditionVO) vo;
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("logoPath", editionVO.getLogoPath());
		params.put("dateDebut", editionVO.getDateDebut());
		params.put("dateFin", editionVO.getDateFin());
		return params;
	}

	@Override
	protected String getCodeTemplate() {
		return "10";
	}

	@Override
	protected boolean imprimer(IValueObject vo) {
		return false;
	}

}
