package eai.devass.sinistreat.appli.usecase.edition;

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
import eai.devass.sinistreat.appli.modele.metier.reglement.DetailReglement;
import eai.devass.sinistreat.appli.modele.metier.reglement.Reglement;
import eai.devass.sinistreat.appli.utils.entites.IDate;
import eai.devass.sinistreat.appli.valueobjects.metier.reglement.DetailReglementVO;
import eai.devass.sinistreat.appli.valueobjects.metier.reglement.ReglementVO;

public class EditerLettreDestChequeUC extends EditerJrxmlDocxUC {
	
	

	private final SimpleDateFormat dateFormat = new SimpleDateFormat(
			IDate.FORMAT_SIMPLE);
	

	@Override
	protected List<String> getCodesTemplate(EditionVO editionVO)
			throws FonctionnelleException {
		Reglement reg = reglementManager.getReglementById(editionVO.getRefReglementVO().getIdReglement());
		List<String> templates = new ArrayList<String>();
		if (editionVO.getRefReglementVO().getTypeDestinataireCheque()
				.equals("6")) {
			templates.add(IConstantes.CODE_LETTRE_DESTINATAIRE_CHEQUE_CNRA);
		} else if (editionVO.getRefReglementVO().getTypeDestinataireCheque()
				.equals("4")
				&& editionVO.getRefReglementVO().getTypeBeneficiaire()
						.equals("4")) {
			templates.add(IConstantes.CODE_LETTRE_DESTINATAIRE_CHEQUE_BARREAU);
			
				if(reg.getListDetailReglement().get(0).getContentieux()){
					templates.add(IConstantes.CODE_LETTRE_DESTINATAIRE_CHEQUE_BARREAU_TRUE);
				} else {
				templates.add(IConstantes.CODE_LETTRE_DESTINATAIRE_CHEQUE_BARREAU_FALSE);
				}
		} else if (editionVO.getRefReglementVO().getTypeDestinataireCheque()
				.equals("2")
				|| editionVO.getRefReglementVO().getTypeDestinataireCheque()
						.equals("3")
				|| editionVO.getRefReglementVO().getTypeDestinataireCheque()
						.equals("5")
				|| editionVO.getRefReglementVO().getTypeDestinataireCheque()
						.equals("7")) {
			templates.add(IConstantes.CODE_LETTRE_DESTINATAIRE_CHEQUE_VICTIME);
		}
		editionVO.setFileName(reglementManager.getLibelleDestinataireCheque(reg.getTypeDestinataireCheque())+" "+reg.getNumeroQuittance());
		return templates;
	}

	@Override
	protected String getCodeTemplate(EditionVO editionVO) {
		return null;
	}

	@Override
	protected Map<String, Object> getRapportParametresValues(IValueObject vo)
			throws FonctionnelleException {
		Map<String, Object> params = new HashMap<String, Object>();
		EditionVO editionVO = (EditionVO) vo;
		params.put("DATE_EDITION", dateFormat.format(new Date()));
		params.put("INIT_USER_CONNECTED", parametrageManager
				.getInitialesUtilisateur(editionVO.getCodeUserConnected()));
		params.put("ID_REG", editionVO.getRefReglementVO().getIdReglement());
		return params;
	}

	@Override
	protected boolean imprimer(IValueObject vo) {
		return false;
	}

}
