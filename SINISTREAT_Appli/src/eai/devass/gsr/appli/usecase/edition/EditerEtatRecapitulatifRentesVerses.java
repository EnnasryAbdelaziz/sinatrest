package eai.devass.gsr.appli.usecase.edition;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import eai.devass.edition.Utils.IConstantes;
import eai.devass.edition.valueobjects.EditionVO;
import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.usecase.edition.EditerJrxmlDocxUC;
import eai.devass.sinistreat.appli.utils.entites.IDate;
import ma.co.omnidata.framework.utile.DateUtile;



public class EditerEtatRecapitulatifRentesVerses extends EditerJrxmlDocxUC {
	private final SimpleDateFormat dateFormat = new SimpleDateFormat(IDate.FORMAT_SIMPLE);

	@Override
	protected List<String> getCodesTemplate(EditionVO editionVO)
			throws FonctionnelleException {

		List<String> templates = new ArrayList<String>();

		if (editionVO.getRentes().equals("1")) {
			templates.add(IConstantes.CODE_RAPPORT_ETAT_RECAPITULATIF_RENTES_VERSEES_NOUVELLE_LIQUIDATION);
		} else if (editionVO.getRentes().equals("2")){
			templates.add(IConstantes.CODE_RAPPORT_ETAT_RECAPITULATIF_RENTES_VERSEES_AGGRAVATION_OU_REVISION_A_HAUSSE);
		} else if (editionVO.getRentes().equals("3")){
			templates.add(IConstantes.CODE_RAPPORT_ETAT_RECAPITULATIF_RENTES_VERSEES_ATTENUATION_OU_REVISION_A_BAISSE);
		}
		editionVO.setFileName("Etat récap des rentes à " + dateFormat.format(new Date()));
		return templates;
	}

	/*
	 * protected List<String> getCodesTemplate(EditionVO editionVO) {
	 * if(editionVO.getRefReglementVO().getCodeUserModificateur() != null &&
	 * "I".equals(editionVO.getRefReglementVO().getCodeUserModificateur())){ return
	 * Arrays.asList(IConstantes.CODE_TEMPLATE_LETTRE_QUITTANCE_IJ); } return
	 * Arrays.asList(IConstantes.CODE_TEMPLATE_LETTRE_QUITTANCE_IJ,IConstantes.
	 * CODE_TEMPLATE_LETTRE_ENVOI_IJ); }
	 */

	@Override
	protected Map<String, Object> getRapportParametresValues(IValueObject vo) {
		Map<String, Object> params = new HashMap<String, Object>();
		EditionVO editionVO = (EditionVO) vo;
		String journee = DateUtile.calendarToString("dd/MM/yyyy", Calendar.getInstance());
		params.put("DATE_EDITION", journee);
		//params.put("DATE_EDITION", dateFormat.format(new Date()));
		params.put("DATE_DEBUT", editionVO.getDateDebut());
		params.put("DATE_FIN", editionVO.getDateFin());
		return params;
	}

	@Override
	protected String getCodeTemplate(EditionVO editionVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected boolean imprimer(IValueObject vo) {
		// TODO Auto-generated method stub

		return false;
	}

}

