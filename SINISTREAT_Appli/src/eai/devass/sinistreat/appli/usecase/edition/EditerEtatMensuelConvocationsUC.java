package eai.devass.sinistreat.appli.usecase.edition;

import java.util.HashMap;
import java.util.Map;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import eai.devass.edition.Utils.IConstantes;
import eai.devass.edition.valueobjects.EditionVO;

public class EditerEtatMensuelConvocationsUC extends EditerEtatUC{

	@Override
	protected String getCodeRapport() {
		return IConstantes.CODE_ETAT_CONVOCATIONS;
	}

	@Override
	protected Map<String, Object> getRapportParametresValues(IValueObject vo) {
		EditionVO editionVO = (EditionVO) vo;
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("nomDirection", "Direction Prestation AT");
		params.put("titre", "ETAT MENSUEL DES CONVOCATIONS");
		params.put("paramTraitement", "MOIS DU TRAITEMENT : " + editionVO.getDateDebut().substring(3));
		params.put("dateDebut", editionVO.getDateDebut());
		params.put("dateFin", editionVO.getDateFin());
		return params;
	}

}
