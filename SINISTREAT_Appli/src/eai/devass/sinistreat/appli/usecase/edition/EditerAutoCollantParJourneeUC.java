package eai.devass.sinistreat.appli.usecase.edition;

import java.util.HashMap;
import java.util.Map;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import eai.devass.edition.Utils.IConstantes;
import eai.devass.edition.valueobjects.EditionVO;

public class EditerAutoCollantParJourneeUC extends EditerJrxmlUC {

	@Override
	protected String getCodeTemplate() {
		return IConstantes.CODE_TEMPLATE_AUTO_COLLANT;
	}

	@Override
	protected boolean imprimer(IValueObject vo) {
		return false;
	}
	
	@Override
	protected Map<String, Object> getRapportParametresValues(IValueObject vo){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("afficherRsvInitiales", "false");
		EditionVO editionVO = (EditionVO) vo;
		params.put("logoPath", editionVO.getLogoPath());
		params.put("SUBREPORT_DIR", editionVO.getJaspersPath() + "/");
		params.put("numSinistre", "XXXXXX");
		//Evolution Reedition autocolant 
		params.put("PrameterEtat", "XXX");
		params.put("PrameterEditOrReedit1", "conditionAnnulePourReedition");
		params.put("PrameterEditOrReedit2", 1);
		params.put("PrameterEditOrReedit3", 0);
		params.put("dateDebut", editionVO.getDateJournee());
		params.put("dateFin", editionVO.getDateJournee());
		return params;
	}
}
