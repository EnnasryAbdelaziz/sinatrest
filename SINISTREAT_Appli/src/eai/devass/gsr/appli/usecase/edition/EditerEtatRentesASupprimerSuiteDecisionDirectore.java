package eai.devass.gsr.appli.usecase.edition;

import java.util.HashMap;
import java.util.Map;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import eai.devass.edition.Utils.IConstantes;
import eai.devass.edition.valueobjects.EditionVO;
import eai.devass.sinistreat.appli.usecase.edition.EditerEtatUC;

public class EditerEtatRentesASupprimerSuiteDecisionDirectore extends EditerEtatUC{

	@Override
	protected String getCodeRapport() {
		return IConstantes.CODE_ETAT_RENTES_A_SUPPRIMER_SUITE_DECISION_DIRECTOIRE;
	}

	@Override
	protected Map<String, Object> getRapportParametresValues(IValueObject vo) {
		EditionVO editionVO = (EditionVO) vo;
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("age", Integer.valueOf(editionVO.getAge()));
		params.put("nbre_annee_susp", Integer.valueOf(editionVO.getNbreAnnee()));
		return params;
	}

}
