package eai.devass.gsr.appli.usecase.edition;

import java.util.HashMap;
import java.util.Map;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import eai.devass.edition.Utils.IConstantes;
import eai.devass.edition.valueobjects.EditionVO;
import eai.devass.sinistreat.appli.usecase.edition.EditerJrxmlUC;

public class EditerAutoCollantVictime extends EditerJrxmlUC {

	@Override
	protected String getCodeTemplate() {
		return IConstantes.CODE_AUTOCOLLANT_VICTIME;
	}

	@Override
	protected Map<String, Object> getRapportParametresValues(IValueObject vo) {
		EditionVO editionVO = (EditionVO) vo;
		String numeroRente = editionVO.getNumRente();
		
		Map<String, Object> params = new HashMap<String, Object>();
		String compagnie = "";
		if(editionVO.getCompagnie() != null){
			if(editionVO.getCompagnie().equals("1")){
				compagnie = "RW";
			}else if(editionVO.getCompagnie().equals("2")){
				compagnie = "W";
			}else if(editionVO.getCompagnie().equals("3")){
				compagnie = "R";
			}else if(editionVO.getCompagnie().equals("4")){
				compagnie = "M";
			}
		}
		params.put("logoPath", editionVO.getLogoPath());
		params.put("titre", "RENTE " + numeroRente + "-00-" + compagnie);
		params.put("titre2", "RENTE " + numeroRente + "-" + compagnie);
		params.put("compagnie", Integer.valueOf(editionVO.getCompagnie()));
		params.put("numeroRente", numeroRente);
		return params;
	}

	@Override
	protected boolean imprimer(IValueObject vo) {
		return false;
	}
}
