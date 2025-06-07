package eai.devass.gsr.appli.usecase.edition;

import java.util.HashMap;
import java.util.Map;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import eai.devass.edition.Utils.IConstantes;
import eai.devass.sinistreat.appli.usecase.edition.EditerEtatUC;

public class EditerEtatDescendantsEchus extends EditerEtatUC {

	@Override
	protected String getCodeRapport() {
		return IConstantes.CODE_RAPPORT_ETAT_DESCENDANTS_ECHUS;
	}

	@Override
	protected Map<String, Object> getRapportParametresValues(IValueObject vo) {
		// EditionVO editionVO = (EditionVO) vo;
		Map<String, Object> params = new HashMap<String, Object>();
		return params;
	}

}
