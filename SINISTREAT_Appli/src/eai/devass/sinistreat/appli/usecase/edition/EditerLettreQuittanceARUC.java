package eai.devass.sinistreat.appli.usecase.edition;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import eai.devass.edition.Utils.IConstantes;
import eai.devass.edition.valueobjects.EditionVO;
import eai.devass.gsr.appli.utile.ConvertierMontantEnLettre;
import eai.devass.sinistreat.appli.utils.entites.IDate;
import eai.devass.sinistreat.appli.valueobjects.metier.reglement.DetailReglementVO;
import eai.devass.sinistreat.appli.valueobjects.metier.reglement.ReglementVO;
import eai.devass.sinistreat.appli.valueobjects.metier.sinistre.SinistreVO;

public class EditerLettreQuittanceARUC extends EditerCombiningJrxmlUC {
	private final SimpleDateFormat dateFormat = new SimpleDateFormat(
			IDate.FORMAT_SIMPLE);
	final long MILLISECONDS_PER_DAY = 1000 * 60 * 60 * 24;
	public DecimalFormat decimalLongFormat = new DecimalFormat("##########.##");

	@Override
	protected List<String> getCodesTemplate(EditionVO editionVO) {
		if(editionVO.getRefReglementVO().getCodeUserModificateur() != null && "I".equals(editionVO.getRefReglementVO().getCodeUserModificateur())){
				return Arrays.asList(IConstantes.CODE_TEMPLATE_LETTRE_QUITTANCE_AR);
		}
		return Arrays.asList(IConstantes.CODE_TEMPLATE_LETTRE_QUITTANCE_AR,IConstantes.CODE_TEMPLATE_LETTRE_ENVOI_AR);
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

		String dateReprise = ((DetailReglementVO) reglementVO
				.getListDetailReglement().get(0)).getDateFinPrestation();
		String dateITT = ((DetailReglementVO) reglementVO
				.getListDetailReglement().get(0)).getDateDebutPrestation();
		String montantRubrique = (((DetailReglementVO) reglementVO
				.getListDetailReglement().get(0)).getMontant()).trim();
		montantRubrique = montantRubrique.replaceAll("\\s+", "");
		montantRubrique = montantRubrique.replace(',', '.');
		Double mnt = Double.parseDouble(montantRubrique);
		String montantLettre = ConvertierMontantEnLettre.montantToLettre(
				decimalLongFormat.format(mnt).replaceAll(",", "."), "DIRHAMS",
				"CENTIMES");
		params.put("DATE_EDITION", dateFormat.format(new Date()));
		params.put("DATE_ACCIDENT", sinistreVO.getRefEvenement()
				.getDateAccident());
		params.put("NUM_SIN", sinistreVO.getNumeroSinistre());
		params.put("N_GRAVE", sinistreVO.getNumeroGrave());
		params.put("NOM_ASSURER", sinistreVO.getNomClient());
		params.put("VICTIME", sinistreVO.getRefVictime().getNomprenom());
		params.put("DATE_DEBUT", dateITT);
		params.put("DATE_FIN", dateReprise);
		params.put("MONTANT_RUBRIQUE_CHIFFRE", ((DetailReglementVO) reglementVO
				.getListDetailReglement().get(0)).getMontant());
		params.put("MONTANT_RUBRIQUE_LETTRE", montantLettre);
		params.put("NBR_EDITION", Integer.parseInt(reglementVO.getNbrEdition()));
		params.put("DATE_JUGEMENT", "");
		params.put("NOM_TRIBUNAL", "");
		params.put("NUM_DOSSIER_TRIBUNAL", "");
		params.put("RENTE_ANNUEL", "");
		params.put("SALAIRE_JUGEMENT", "");
		params.put("IPP_JUGEMENT", "");
		params.put("RGL", reglementVO.getIdReglement());
		return params;
	}
}
