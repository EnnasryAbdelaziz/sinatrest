package eai.devass.sinistreat.appli.usecase.edition.bnej;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import eai.devass.edition.Utils.IConstantes;
import eai.devass.edition.valueobjects.EditionVO;
import eai.devass.sinistreat.appli.usecase.edition.EditerJrxmlUC;

public class EditerListBnejUC extends EditerJrxmlUC {

	
	
	
	@Override
	protected String getCodeTemplate() {
		return IConstantes.CODE_TEMPLATE_EDITION_BNEJ_LOTS;
	}

	@Override
	protected Map<String, Object> getRapportParametresValues(IValueObject vo) {
		EditionVO editionVO = (EditionVO) vo;
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("logoPath", editionVO.getLogoPath());
		params.put("DATE_EDITION",dateFormat.format(new Date()) );	
		params.put("DATE_DEBUT",editionVO.getDateDebut() );
		params.put("DATE_FIN",editionVO.getDateFin() );
		return params;
	}

	@Override
	protected boolean imprimer(IValueObject vo) {
		// TODO Auto-generated method stub
		return false;
	}

}
