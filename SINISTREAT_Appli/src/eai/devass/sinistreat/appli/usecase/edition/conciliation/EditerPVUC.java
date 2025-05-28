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
import eai.devass.sinistreat.appli.modele.metier.conciliation.AyantDroitOffre;
import eai.devass.sinistreat.appli.modele.metier.conciliation.SinAnterieurOffre;
import eai.devass.sinistreat.appli.usecase.edition.EditerJrxmlDocxUC;
import eai.devass.sinistreat.appli.utils.entites.IDate;

public class EditerPVUC extends EditerJrxmlDocxUC {
	private final SimpleDateFormat dateFormat = new SimpleDateFormat(
			IDate.FORMAT_SIMPLE);

	@Override
	protected String getCodeTemplate(EditionVO editionVO) {
		return null;
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
		params.put("INIT_USER_CONNECTED", parametrageManager
				.getInitialesUtilisateur(editionVO.getCodeUserConnected()));
		params.put("ID_OFFRE", editionVO.getIdOffre());
		params.put("DUPLICATA", editionVO.getDuplicate());
		if ("0".equals(editionVO.getDeces())) {
			List<SinAnterieurOffre> sinistreAnterieurs = conciliationManager
					.getSinistresAnterieurs(editionVO.getIdOffre());
			for (int i = 0; i < sinistreAnterieurs.size(); i++) {
				params.put("DAT_ACC"+(i+1), dateFormat.format(sinistreAnterieurs.get(i).getDateAccident()));
				params.put("IPP"+(i+1), sinistreAnterieurs.get(i).getTauxIPP().toString());
			}
				
			params.put("SIN_ANT", sinistreAnterieurs);	
		} else {
			List<AyantDroitOffre> ayantDroit = conciliationManager
					.getListAyantDroitOffre(editionVO.getIdOffre());
			params.put("AYANT_DROIT", ayantDroit);
		}
		
		params.put("backgroundPath", editionVO.getBackgroundPath());
		return params;
	}

	@Override
	protected List<String> getCodesTemplate(EditionVO editionVO) {

		List<String> templates = new ArrayList<String>();
		if ("0".equals(editionVO.getDeces())) {
			templates.add(IConstantes.CODE_TEMPLATE_PV_BLESSURE_1);
			templates.add(IConstantes.CODE_TEMPLATE_PV_BLESSURE_2);
		} else {
			templates.add(IConstantes.CODE_TEMPLATE_PV_DECES_1);
			templates.add(IConstantes.CODE_TEMPLATE_PV_DECES_2);
		}

		return templates;
	}

}
