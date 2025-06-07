package eai.devass.sinistreat.appli.usecase.edition.conciliation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.services.dao.PersistenceException;
import eai.devass.edition.Utils.IConstantes;
import eai.devass.edition.valueobjects.EditionVO;
import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.modele.metier.conciliation.AyantDroitOffre;
import eai.devass.sinistreat.appli.modele.metier.conciliation.Offre;
import eai.devass.sinistreat.appli.modele.metier.conciliation.SinAnterieurOffre;
import eai.devass.sinistreat.appli.modele.metier.sinistre.Sinistre;
import eai.devass.sinistreat.appli.usecase.edition.EditerJrxmlDocxUC;
import eai.devass.sinistreat.appli.utils.entites.IDate;

public class EditerFicheOffreUC extends EditerJrxmlDocxUC {
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

		if ("0".equals(editionVO.getDeces())) {
			List<SinAnterieurOffre> sinistreAnterieurs = conciliationManager
					.getSinistresAnterieurs(editionVO.getIdOffre());
			params.put("SIN_ANT", sinistreAnterieurs);
		} else {
			List<AyantDroitOffre> ayantDroit = conciliationManager
					.getListAyantDroitOffre(editionVO.getIdOffre());
			params.put("AYANT_DROIT", ayantDroit);
		}
		Offre offre;
		try {
			offre = conciliationManager.getOffresById(editionVO.getIdOffre());
			params.put("ippoffrereduit", conciliationManager.getIppReduit(offre.getIppOffre(), dateFormat.parse(editionVO.getDateAccident())));
			Sinistre sinistre = sinistreManager.getSinistreById(offre.getRefConciliation().getRefSinistre().getId());
			params.put("ippSinistrereduit", conciliationManager.getIppReduit(sinistre.getIpp(), dateFormat.parse(editionVO.getDateAccident())));
		} catch (PersistenceException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return params;
	}

	@Override
	protected List<String> getCodesTemplate(EditionVO editionVO) {

		List<String> templates = new ArrayList<String>();
		if("0".equals(editionVO.getDeces()))
			templates.add(IConstantes.CODE_TEMPLATE_FICHE_OFFRE_BLESSURE);
		else
			templates.add(IConstantes.CODE_TEMPLATE_FICHE_OFFRE_DECES);

		return templates;
	}

}
