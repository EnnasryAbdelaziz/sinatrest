package eai.devass.sinistreat.appli.usecase.edition.statistiques;

import java.util.HashMap;
import java.util.Map;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import eai.devass.edition.Utils.IConstantes;
import eai.devass.sinistreat.appli.usecase.edition.EditerEtatUC;

public class EditerEtatDossiersMaintenantRsvGraveMalgreSaisieRub21UC extends EditerEtatUC{

	@Override
	protected String getCodeRapport() {
		return IConstantes.CODE_ETAT_DOSSIERS_MAINTENANT_RSV_GRAVE_MALGRE_SAISIE_RUB21;
	}

	@Override
	protected Map<String, Object> getRapportParametresValues(IValueObject vo) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("nomDirection", "Direction Prestation AT");
		return params;
	}

}
