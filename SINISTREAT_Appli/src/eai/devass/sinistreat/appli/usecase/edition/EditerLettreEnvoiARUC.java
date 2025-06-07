package eai.devass.sinistreat.appli.usecase.edition;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import eai.devass.edition.Utils.IConstantes;
import eai.devass.edition.valueobjects.EditionVO;
import eai.devass.sinistreat.appli.utils.entites.IDate;
import eai.devass.sinistreat.appli.valueobjects.metier.reglement.DetailReglementVO;
import eai.devass.sinistreat.appli.valueobjects.metier.reglement.ReglementVO;
import eai.devass.sinistreat.appli.valueobjects.metier.sinistre.SinistreVO;

public class EditerLettreEnvoiARUC extends EditerJrxmlUC {
	private final SimpleDateFormat dateFormat = new SimpleDateFormat(
			IDate.FORMAT_SIMPLE);
	@Override
	protected String getCodeTemplate() {
		return IConstantes.CODE_TEMPLATE_LETTRE_ENVOI_AR;
	}

	@Override
	protected boolean imprimer(IValueObject vo) {
		return false;
	}

	@Override
	protected Map<String, Object> getRapportParametresValues(IValueObject vo) {

		Map<String, Object> params = new HashMap<String, Object>();
		EditionVO editionVO = (EditionVO) vo;
		ReglementVO reglementVO = editionVO.getRefReglementVO();
		SinistreVO sinistreVO = reglementVO.getRefSinistre();
		
		String dateReprise = ((DetailReglementVO)reglementVO.getListDetailReglement().get(0)).getDateFinPrestation();
		String dateITT = ((DetailReglementVO)reglementVO.getListDetailReglement().get(0)).getDateDebutPrestation();
	
		params.put("DATE_EDITION", dateFormat.format(new Date()));
		params.put("DATE_ACCIDENT",sinistreVO.getRefEvenement().getDateAccident());
		params.put("NUM_SIN",sinistreVO.getNumeroSinistre());
		params.put("N_GRAVE",sinistreVO.getNumeroGrave());
		params.put("NOM_ASSURER",sinistreVO.getNomClient());
		params.put("VICTIME",sinistreVO.getRefVictime().getNomprenom());
		params.put("ADRESSE", reglementVO.getAdresseBenef());
		params.put("VILLE", reglementVO.getVilleBenef());
		params.put("DATE_DEBUT",dateITT);
		params.put("DATE_FIN",dateReprise);
		params.put("MONTANT_QUITTANCE",reglementVO.getMontant());
		params.put("NBR_EDITION", Integer.parseInt(reglementVO.getNbrEdition()));
		params.put("RGL", reglementVO.getIdReglement());
		return params;
	}
}
