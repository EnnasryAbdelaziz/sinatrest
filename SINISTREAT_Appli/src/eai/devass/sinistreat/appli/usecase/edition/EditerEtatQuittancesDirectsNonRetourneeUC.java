package eai.devass.sinistreat.appli.usecase.edition;

import java.util.HashMap;
import java.util.Map;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import eai.devass.edition.Utils.IConstantes;

public class EditerEtatQuittancesDirectsNonRetourneeUC extends EditerEtatUC{

	@Override
	protected String getCodeRapport() {
		return IConstantes.CODE_ETAT_QUITTANCES_NON_RETOURNEE;
	}

	@Override
	protected Map<String, Object> getRapportParametresValues(IValueObject vo) {
		return new HashMap<String, Object>();
	}

}
