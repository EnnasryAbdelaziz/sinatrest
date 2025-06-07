package eai.devass.sinistreat.appli.usecase.edition;

import java.util.HashMap;
import java.util.Map;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import eai.devass.edition.Utils.IConstantes;
import eai.devass.edition.valueobjects.EditionVO;

public class EditerEtatDecisionsJudiciairesUC extends EditerEtatUC{

	@Override
	protected String getCodeRapport() {
		return IConstantes.CODE_RAPPORT_ETAT_DECISIONS_JUDICIAIRES;
	}

	@Override
	protected Map<String, Object> getRapportParametresValues(IValueObject vo){
		Map<String, Object> params = new HashMap<String, Object>();
		String libelleCode = new String();
		try {
			EditionVO editionVO = (EditionVO) vo;
			params.put("nomDirection", "Direction Prestation AT");
			params.put("decision", editionVO.getDecision());
			params.put("dateDebut", editionVO.getDateDebut());
			params.put("dateFin", editionVO.getDateFin());
			if (editionVO.getDecision()!=null){
				if (editionVO.getDecision().equals("6")){
					libelleCode = "Conciliation";
				}
				if (editionVO.getDecision().equals("7")){
					libelleCode = "Jugement";
				}
				if (editionVO.getDecision().equals("8")){
					libelleCode = "Arrêts";
				}
				params.put("codeDecision", libelleCode);
			}
			String periodeEtat = editionVO.getMode();
			if(periodeEtat != null){
				if(periodeEtat.equals("J")){//journalier
                    params.put("periodeEtat",
                            "Journée du : " + editionVO.getDateDebut());
				}else if(periodeEtat.equals("M")){//Mensuel
                    params.put("periodeEtat", "Mois : "
                            + editionVO.getDateDebut().substring(3));
				}else if(periodeEtat.equals("A")){//Annuel
                    params.put("periodeEtat", "Exercice : "
                            + editionVO.getDateDebut().substring(6));
				}
			}
		} catch (Exception e) {
			
		}
		return params;
	}

}
