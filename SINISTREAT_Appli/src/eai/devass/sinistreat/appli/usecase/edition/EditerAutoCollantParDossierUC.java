package eai.devass.sinistreat.appli.usecase.edition;

import java.util.HashMap;
import java.util.Map;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import eai.devass.edition.Utils.IConstantes;
import eai.devass.edition.valueobjects.EditionVO;

public class EditerAutoCollantParDossierUC extends EditerJrxmlUC {

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
	 
		params.put("numSinistre", editionVO.getNumeroSinistre());
		//Evolution Reedition autocolant 
		//Evolution Reedition autocolant 
		params.put("PrameterEtat", "XXX");
		params.put("PrameterEditOrReedit1", "conditionAnnulePourReedition");
		params.put("PrameterEditOrReedit2", 1);
		params.put("PrameterEditOrReedit3", 1);
		params.put("dateDebut", "31/12/2099");
		params.put("dateFin", "31/12/2099");
		return params;
	}

}
