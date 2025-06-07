package eai.devass.gsr.appli.usecase.edition;

import java.util.HashMap;
import java.util.Map;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import eai.devass.edition.Utils.IConstantes;
import eai.devass.edition.valueobjects.EditionVO;
import eai.devass.sinistreat.appli.usecase.edition.EditerJrxmlUC;

public class EditerAutoCollantAyantDroit extends EditerJrxmlUC {

	@Override
	protected String getCodeTemplate() {
		return IConstantes.CODE_AUTOCOLLANT_AYANT_DROIT;
	}

	@Override
	protected Map<String, Object> getRapportParametresValues(IValueObject vo) {
		EditionVO editionVO = (EditionVO) vo;
		String numeroRente = editionVO.getNumRente();
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("logoPath", editionVO.getLogoPath());
		params.put("titre", "RENTE " + numeroRente);
		params.put("numeroRente", numeroRente);
		params.put("codeCompagnie", editionVO.getCompagnie());
		
		return params;
	}

	@Override
	protected boolean imprimer(IValueObject vo) {
		return false;
	}
}
