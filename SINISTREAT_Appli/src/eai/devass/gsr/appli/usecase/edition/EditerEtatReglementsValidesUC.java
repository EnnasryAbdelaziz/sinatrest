package eai.devass.gsr.appli.usecase.edition;

import java.util.HashMap;
import java.util.Map;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import eai.devass.edition.Utils.IConstantes;
import eai.devass.edition.valueobjects.EditionVO;
import eai.devass.sinistreat.appli.usecase.edition.EditerEtatUC;

public class EditerEtatReglementsValidesUC extends EditerEtatUC{

	@Override
	protected String getCodeRapport() {
		return IConstantes.CODE_ETAT_REGLEMENTS_VALIDES;
	}

	@Override
	protected Map<String, Object> getRapportParametresValues(IValueObject vo) {
		EditionVO editionVO = (EditionVO) vo;
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("dateDebut", editionVO.getDateDebut());
		params.put("dateFin", editionVO.getDateFin());
		

		if ((editionVO.getModeReglement()).equals("1"))
		{
			editionVO.setModeReglement1("1");
			editionVO.setModeReglement2("1");
			editionVO.setModeReglementLibelle("Chèque");
		}
		if ((editionVO.getModeReglement()).equals("2"))
		{
			editionVO.setModeReglement1("2");
			editionVO.setModeReglement2("2");
			editionVO.setModeReglementLibelle("Mandat");
		}
		if ((editionVO.getModeReglement()).equals("3"))
		{
			editionVO.setModeReglement1("3");
			editionVO.setModeReglement2("3");
			editionVO.setModeReglementLibelle("Virement");
		}
		if ((editionVO.getModeReglement()).equals("0"))
		{
			editionVO.setModeReglement("1");
			editionVO.setModeReglement1("2");
			editionVO.setModeReglement2("3");
			editionVO.setModeReglementLibelle("Tout");
		}
		params.put("modeReglement", editionVO.getModeReglement());
		params.put("modeReglement1", editionVO.getModeReglement1());
		params.put("modeReglement2", editionVO.getModeReglement2());
		params.put("modeReglementLibelle", editionVO.getModeReglementLibelle());
		return params;
	}

}
