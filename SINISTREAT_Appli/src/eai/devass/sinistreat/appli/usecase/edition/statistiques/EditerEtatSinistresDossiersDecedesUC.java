package eai.devass.sinistreat.appli.usecase.edition.statistiques;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import eai.devass.edition.Utils.IConstantes;
import eai.devass.edition.valueobjects.EditionVO;
import eai.devass.sinistreat.appli.usecase.edition.EditerEtatUC;

public class EditerEtatSinistresDossiersDecedesUC extends EditerEtatUC{

	@Override
	protected String getCodeRapport() {
		return IConstantes.CODE_ETAT_SINISTRES_DOSSIERS_DECEDES;
	}

	@Override
	protected Map<String, Object> getRapportParametresValues(IValueObject vo) {
		
		SimpleDateFormat fromUser = new SimpleDateFormat("dd/MM/yyyy");
		Map<String, Object> params = new HashMap<String, Object>();
		EditionVO editionVO = (EditionVO) vo;
		params.put("nomDirection", "Direction Prestation AT");
		params.put("dateDebut", editionVO.getDateDebut());
		params.put("dateFin", editionVO.getDateFin());
		try {
			GregorianCalendar calendar = new GregorianCalendar();
			calendar.setTime(fromUser.parse(editionVO.getDateFin()));
			int jour = calendar.get(Calendar.YEAR);
			params.put("exercice", String.valueOf(jour));
		} catch (ParseException e) {
			logger.error("erreur format date", e);
		}
		return params;
	}

}
