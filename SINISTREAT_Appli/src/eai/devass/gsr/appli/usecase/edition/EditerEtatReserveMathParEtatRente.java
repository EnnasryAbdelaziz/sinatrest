package eai.devass.gsr.appli.usecase.edition;

import java.util.HashMap;
import java.util.Map;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import eai.devass.edition.Utils.IConstantes;
import eai.devass.edition.valueobjects.EditionVO;
import eai.devass.sinistreat.appli.usecase.edition.EditerEtatUC;

public class EditerEtatReserveMathParEtatRente extends EditerEtatUC{

	@Override
	protected String getCodeRapport() {
		return IConstantes.CODE_RAPPORT_ETAT_RESERVE_MATH_PAR_ETAT_RENTE;
	}

	@Override
	protected Map<String, Object> getRapportParametresValues(IValueObject vo) {
		EditionVO editionVO = (EditionVO) vo;
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("dateDebut", editionVO.getDateDebut());
		params.put("dateFin", editionVO.getDateFin());
		String codeEtat = editionVO.getEtat();
		params.put("codeEtatRente", codeEtat);
		if(codeEtat != null){
			if(codeEtat.equals("4")){
				params.put("titreEtatRente", "En cours");
			}else if(codeEtat.equals("5")){
				params.put("titreEtatRente", "Echues");
			}else if(codeEtat.equals("6")){
				params.put("titreEtatRente", "En Attente");
			}else if(codeEtat.equals("7")){
				params.put("titreEtatRente", "Supprimees");
			}else if(codeEtat.equals("8")){
				params.put("titreEtatRente", "Décédés");
			}else if(codeEtat.equals("9")){
				params.put("titreEtatRente", "Rachetées");
			}else if(codeEtat.equals("10")){
				params.put("titreEtatRente", "Versées");
			}else if(codeEtat.equals("11")){
				params.put("titreEtatRente", "Remariés");
			}
		}
		return params;
	}

}
