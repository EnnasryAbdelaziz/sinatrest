package eai.devass.sinistreat.appli.usecase.edition;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.utile.DateUtile;
import eai.devass.edition.Utils.IConstantes;
import eai.devass.edition.valueobjects.EditionVO;

public class EditerAccuseReceptionUC extends EditerJrxmlUC {

	@Override
	protected String getCodeTemplate() {
		return IConstantes.CODE_TEMPLATE_ACCUSE_RECEPTION;
	}

	@Override
	protected boolean imprimer(IValueObject vo) {
		EditionVO editionVO = (EditionVO) vo;
		return Boolean.valueOf(editionVO.getImprimer());
	}
	
	@Override
	protected Map<String, Object> getRapportParametresValues(IValueObject vo){
		Map<String, Object> params = new HashMap<String, Object>();
		String journee = DateUtile.calendarToString("dd/MM/yyyy", Calendar.getInstance());
		params.put("dateDebut", journee);
		params.put("dateFin", journee);
		return params;
	}

}
