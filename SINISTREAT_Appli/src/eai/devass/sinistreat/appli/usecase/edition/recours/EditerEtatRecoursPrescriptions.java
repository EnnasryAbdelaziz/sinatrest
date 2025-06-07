package eai.devass.sinistreat.appli.usecase.edition.recours;

import java.util.HashMap;
import java.util.Map;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import eai.devass.edition.Utils.IConstantes;
import eai.devass.sinistreat.appli.usecase.edition.EditerEtatUC;

public class EditerEtatRecoursPrescriptions extends EditerEtatUC{

	@Override
	protected String getCodeRapport() {
		return IConstantes.CODE_ETAT_RECOURS_PRESCRIPTIONS;
	}

	@Override
	protected Map<String, Object> getRapportParametresValues(IValueObject vo) {
		//correction sonar Dead store to local variable.
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("nomDirection", "Direction Prestation AT");
		return params;
	}

}
