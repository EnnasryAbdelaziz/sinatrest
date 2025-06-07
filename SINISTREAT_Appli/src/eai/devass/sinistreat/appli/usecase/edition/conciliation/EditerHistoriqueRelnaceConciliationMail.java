package eai.devass.sinistreat.appli.usecase.edition.conciliation;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import eai.devass.edition.Utils.IConstantes;
import eai.devass.edition.valueobjects.EditionVO;
import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.usecase.edition.EditerJrxmlDocxUC;
import eai.devass.sinistreat.appli.utils.entites.IDate;

public class EditerHistoriqueRelnaceConciliationMail  extends EditerJrxmlDocxUC {
	private final SimpleDateFormat dateFormat = new SimpleDateFormat(
			IDate.FORMAT_SIMPLE);

	@Override
	protected String getCodeTemplate(EditionVO editionVO) {
		return null;
	}
	@Override
	protected List<String> getCodesTemplate(EditionVO editionVO) {

		List<String> templates = new ArrayList<String>();
			templates.add(IConstantes.CODE_TEMPLATE_RELANCE_MAIL);
			editionVO.setFileName(IConstantes.FILE_NAME_RELANCE_MAIL);
		return templates;
	}

	@Override
	protected boolean imprimer(IValueObject vo) {
		return false;
	}

	@Override
	protected Map<String, Object> getRapportParametresValues(IValueObject vo)
			throws FonctionnelleException {
		Map<String, Object> params = new HashMap<String, Object>();
		EditionVO editionVO = (EditionVO) vo;
		params.put("DATE_EDITION", dateFormat.format(new Date()));
		params.put("USER_CONNECTED", editionVO.getUserConnected());
		params.put("INIT_USER_CONNECTED",editionVO.getUserConnected());
		params.put("ID_REL", editionVO.getRefRelanceConciliation().getId());
		return params;
	}


}

