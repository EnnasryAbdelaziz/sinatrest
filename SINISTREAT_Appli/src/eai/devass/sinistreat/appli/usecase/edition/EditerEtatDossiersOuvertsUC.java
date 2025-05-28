package eai.devass.sinistreat.appli.usecase.edition;

import java.util.HashMap;
import java.util.Map;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import eai.devass.edition.Utils.IConstantes;
import eai.devass.edition.valueobjects.EditionVO;

public class EditerEtatDossiersOuvertsUC extends EditerJrxmlUC{

//	@Override
//	protected String getCodeRapport() {
//		return IConstantes.CODE_RAPPORT_ETAT_DOSSIERS_OUVERTS;
//	}

	@Override
	protected Map<String, Object> getRapportParametresValues(IValueObject vo) {
		EditionVO editionVO = (EditionVO) vo;
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("logoPath", editionVO.getLogoPath());
		params.put("nomDirection", "Direction Prestation AT");
		params.put("dateDebut", editionVO.getDateDebut());
		params.put("dateFin", editionVO.getDateFin());
		return params;
	}

	@Override
	protected String getCodeTemplate() {
		// TODO Auto-generated method stub
		return IConstantes.CODE_RAPPORT_ETAT_DOSSIERS_OUVERTS;
	}

	@Override
	protected boolean imprimer(IValueObject vo) {
		// TODO Auto-generated method stub
		return false;
	}
}


