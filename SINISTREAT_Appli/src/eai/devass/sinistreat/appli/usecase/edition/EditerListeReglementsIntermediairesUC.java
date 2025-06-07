package eai.devass.sinistreat.appli.usecase.edition;

import java.util.HashMap;
import java.util.Map;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import eai.devass.edition.Utils.IConstantes;
import eai.devass.edition.valueobjects.EditionVO;

public class EditerListeReglementsIntermediairesUC extends EditerEtatUC{

	@Override
	protected String getCodeRapport() {
		return IConstantes.CODE_RAPPORT_LISTE_REGLEMENTS_INTERMEDIAIRES;
	}

	@Override
	protected Map<String, Object> getRapportParametresValues(IValueObject vo) {
		EditionVO editionVO = (EditionVO) vo;
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("nomDirection", "Direction Prestation AT");
		params.put("dateDebut", editionVO.getDateDebut());
		params.put("dateFin", editionVO.getDateFin());
		String modeReglement = editionVO.getMode() != null ? editionVO.getMode() : "";
		params.put("modeReglement", modeReglement);
		if(modeReglement.equals("1")){
			params.put("titreLigne1", "Etat de contrôle des réglements directs intermédiaires");
			params.put("titreLigne2", "Chèque global hébdomadaire");
			params.put("titreLigne3", "Accidents de Travail");
		}else if(modeReglement.equals("2")){
			params.put("titreLigne1", "Etat de contrôle des règlements intermédiaires Compensation");
			params.put("titreLigne2", "");
			params.put("titreLigne3", "");
		}
		
		return params;
	}

}
