package eai.devass.sinistreat.appli.usecase.edition.recours;

import java.util.HashMap;
import java.util.Map;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import eai.devass.edition.Utils.IConstantes;
import eai.devass.edition.valueobjects.EditionVO;
import eai.devass.sinistreat.appli.usecase.edition.EditerEtatUC;

public class EditerEtatAssignationsJudiciaires extends EditerEtatUC{

	@Override
	protected String getCodeRapport() {
		return IConstantes.CODE_ETAT_RECOURS_ASSIGNATIONS_JUDICIAIRES;
	}

	@Override
	protected Map<String, Object> getRapportParametresValues(IValueObject vo) {
		EditionVO editionVO = (EditionVO) vo;
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("nomDirection", "Direction Prestation AT");
		params.put("byAvocatConseil", "0");
		params.put("codeAvocatConseil", "");
		params.put("dateDebut", editionVO.getDateDebut());
		params.put("dateFin", editionVO.getDateFin());
		return params;
	}

}
