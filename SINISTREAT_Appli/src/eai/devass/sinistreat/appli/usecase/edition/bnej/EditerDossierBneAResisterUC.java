package eai.devass.sinistreat.appli.usecase.edition.bnej;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import eai.devass.edition.Utils.IConstantes;
import eai.devass.edition.valueobjects.EditionVO;
import eai.devass.sinistreat.appli.usecase.edition.EditerJrxmlUC;

public class EditerDossierBneAResisterUC extends EditerJrxmlUC {

	@Override
	protected String getCodeTemplate() {
		return IConstantes.CODE_TEMPLATE_EDITION_BNEJ_A_RESISTER;
	}

	@Override
	protected Map<String, Object> getRapportParametresValues(IValueObject vo) {
		EditionVO editionVO = (EditionVO) vo;
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("logoPath", editionVO.getLogoPath());
		params.put("DATE_EDITION", dateFormat.format(new Date()));

		params.put("DATE_EDITION", editionVO.getDateDebut());
		params.put("DATE_DEBUT", editionVO.getDateDebut());

		params.put("DATE_FIN", editionVO.getDateFin());

		params.put("LIBELLE_DECISION",
				bnejManager.getTypeDecisionBnejLibelle(editionVO.getDecision()));

		params.put("TYPE_DECISION", editionVO.getDecision());

		return params;
	}

	@Override
	protected boolean imprimer(IValueObject vo) {
		// TODO Auto-generated method stub
		return false;
	}

}
