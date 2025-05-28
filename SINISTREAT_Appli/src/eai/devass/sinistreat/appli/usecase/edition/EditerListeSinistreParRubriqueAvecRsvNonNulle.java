package eai.devass.sinistreat.appli.usecase.edition;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import eai.devass.edition.Utils.IConstantes;
import eai.devass.edition.valueobjects.EditionVO;

public class EditerListeSinistreParRubriqueAvecRsvNonNulle extends EditerEtatUC{

	@Override
	protected String getCodeRapport() {
		return IConstantes.CODE_RAPPORT_LISTE_SINISTRE_RSV_NON_NULLE;
	}

	@Override
	protected Map<String, Object> getRapportParametresValues(IValueObject vo) {
		EditionVO editionVO = (EditionVO) vo;
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("nomDirection", "Direction Prestation AT");
		List<String> rubriques = editionVO.getCodesRubriques();
		params.put("rubriques", rubriques);
		StringBuffer titre = new StringBuffer("LISTE DES SINISTRES GRAVES AYANT LA RUBRIQUE "+rubriques.get(0));
		for(int i = 1; i < rubriques.size(); i++){
			titre = titre.append(" OU ").append(rubriques.get(i));
		}
		params.put("titre", titre.toString());
		return params;
	}

}
