package eai.devass.sinistreat.appli.usecase.edition;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import eai.devass.edition.Utils.IConstantes;
import eai.devass.edition.valueobjects.EditionVO;
import eai.devass.gsr.appli.utile.ConvertierMontantEnLettre;
import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.modele.metier.contentieux.AudienceJugement;
import eai.devass.sinistreat.appli.modele.metier.contentieux.ProcedureJudiciaire;
import eai.devass.sinistreat.appli.modele.metier.reglement.DetailReglement;
import eai.devass.sinistreat.appli.modele.metier.reglement.Reglement;
import eai.devass.sinistreat.appli.modele.metier.sinistre.AyantDroit;
import eai.devass.sinistreat.appli.modele.metier.sinistre.Sinistre;
import eai.devass.sinistreat.appli.utils.Fonctions;
import eai.devass.sinistreat.appli.utils.entites.IDate;

public class EditerLettreQuittancesAutomatiqueUC extends EditerJrxmlDocxUC {

	private final SimpleDateFormat dateFormat = new SimpleDateFormat(
			IDate.FORMAT_SIMPLE);
	final long MILLISECONDS_PER_DAY = (1001 * 60 * 60 * 24) + 1;
	private DecimalFormat decimalLongFormat = new DecimalFormat("##########.00");
	private DecimalFormat decimalFormat = new DecimalFormat("##########.##");

	private Map<String, Object> params = new HashMap<String, Object>();

	public Map<String, Object> getParams() {
		return params;
	}

	public void setParams(Map<String, Object> params) {
		this.params = params;
	}

	@Override
	protected List<String> getCodesTemplate(EditionVO editionVO)
			throws FonctionnelleException {

		List<String> templates = new ArrayList<String>();

		// get Id rglt

		Reglement rglt = null;

		rglt = reglementManager.getReglementById(editionVO.getRefReglementVO()
				.getIdReglement());

		buildCommonParameters(params, editionVO, rglt);

		// get list rglt details

		if (rglt != null && Fonctions.isNotEmpty(rglt.getListDetailReglement())) {
			List<DetailReglement> details = rglt.getListDetailReglement();

			for (DetailReglement d : details) {
				String code = getTemplateForDetailReglement(d,
						editionVO.getContentieux());

				builDetailsParameters(editionVO, rglt, rglt.getRefSinistre(), d);

				if (Fonctions.isNotEmpty(code)) {
					templates.add(code);
				}

			}
			if (hasITTConciliation(details, editionVO) && details.size()==1) {
				templates.add(IConstantes.CODE_TEMPLATE_LETTRE_ENVOI_IJ);
			}else{
				 templates.add(IConstantes.CODE_TEMPLATE_LETTRE_ACCOMPAGNEMENT);
			}
			

		}
		

		return templates;
	}

	@Override
	protected Map<String, Object> getRapportParametresValues(IValueObject vo) {

		return getParams();
	}

	@Override
	protected boolean imprimer(IValueObject vo) {
		// TODO Auto-generated method stub
		return false;
	}

	private String getTemplateForDetailReglement(DetailReglement detail,
			String contentieux) {
		if (detail == null) {
			return null;
		} else if (eai.devass.sinistreat.appli.utils.IConstantes.RUBRIQUE_RACHAT
				.equals(detail.getCodePrestation())) {
			return IConstantes.CODE_TEMPLATE_LETTRE_QTC_RACHAT;
		} else if (eai.devass.sinistreat.appli.utils.IConstantes.RUBRIQUE_ARRERAGE_AVANT_CONSTITUTION
				.equals(detail.getCodePrestation())) {
			return IConstantes.CODE_TEMPLATE_LETTRE_QTC_ARRERAGES_AVANT_CONSTITUTION;
		} else if (eai.devass.sinistreat.appli.utils.IConstantes.RUBRIQUE_ITT
				.equals(detail.getCodePrestation())) {
			if ("1".equals(contentieux)) {
				return IConstantes.CODE_TEMPLATE_LETTRE_QTC_ITT;
			} else {
				return IConstantes.CODE_TEMPLATE_LETTRE_QUITTANCE_IJ;
			}
		} else if (eai.devass.sinistreat.appli.utils.IConstantes.RUBRIQUE_FRAIS_MEDICAUX
				.equals(detail.getCodePrestation())) {
			return IConstantes.CODE_TEMPLATE_LETTRE_QTC_FRAIS_MEDICAUX;
		} else if (eai.devass.sinistreat.appli.utils.IConstantes.RUBRIQUE_FRAIS_FUNERAIRE
				.equals(detail.getCodePrestation())) {
			return IConstantes.CODE_TEMPLATE_LETTRE_QTC_FRAIS_FUNERAIRE;
		}
		else {
			return null;
		}

	}

	private void buildCommonParameters(Map<String, Object> params,
			EditionVO vo, Reglement rglt) throws FonctionnelleException {

		Sinistre sinistre = rglt.getRefSinistre();

		params.put("DATE_EDITION", dateFormat.format(new Date()));

		if (sinistre.getRefEvenement() != null) {
			params.put("DATE_ACCIDENT",

			dateFormat.format(sinistre.getRefEvenement().getDateAccident()));
		}

		params.put("NUMERO_SINISTRE", sinistre.getNumeroSinistre());
		params.put("NUMERO_GRAVE", sinistre.getNumeroGrave());
		params.put("NOM_ASSURE", sinistre.getNomClient());
		if (sinistre.getRefVictime() != null) {
			params.put("VICTIME", sinistre.getRefVictime().getNomprenom());
		}

		params.put("IPP",
				decimalFormat.format(sinistre.getIpp() == null ? 0.0
						: sinistre.getIpp()));
		params.put("SALAIRE", decimalLongFormat.format(sinistre
				.getSalaireJugement() == null ? 0.0 : sinistre
				.getSalaireJugement()));
		params.put("SALAIRE_ANNUEL", decimalLongFormat.format(sinistre
				.getRefVictime().getSalaireAnnuel() == null ? 0.0 : sinistre
				.getRefVictime().getSalaireAnnuel()));

		params.put("RGLT", String.valueOf(rglt.getId()));
		params.put("USER_CONNECTED", rglt.getNomUserCreateur());
		params.put("INIT_USER_CONNECTED", parametrageManager
				.getInitialesUtilisateur(rglt.getCodeUserCreateur()));

		// contentieux
		params.put("CONTENTIEUX", vo.getContentieux());
		if ("1".equals(vo.getContentieux())) {
			ProcedureJudiciaire pj = recoursManager.getProcedureById(rglt
					.getRefProcedure());

			params.put("JURIDICTION",
					(pj == null || pj.getRefJuridiction()== null) ? "ND"
							: pj.getRefJuridiction().getLibelle());

			params.put("NUMERO_PROCEDURE",
					pj == null ? "ND" : pj.getNumeroDossierTribunal());

			if(pj==null || pj.getId()==0){
				params.put("DATE_DECISION", "ND");
			}else{
				AudienceJugement aj=recoursManager.getLastAudienceJugementByIdProcedure(pj.getId());
				params.put("DATE_DECISION", aj==null?"ND":aj.getDateDecision()==null?"ND":dateFormat.format(aj.getDateDecision()));
			}
			
			

		}

		// arrerage
		params.put("MONTANTRENTEANNUELLE", decimalLongFormat.format(rglt
				.getRenteAnnuelle() == null ? 0.0 : rglt.getRenteAnnuelle()));
		// lettre d'accompagnement
		params.put("NOM_BENEFICIAIRE", rglt.getNomBeneficiaireLettre());
		params.put("ADRESSE_BENEFICIAIRE", rglt.getAdresseBeneficiaireLettre());
		params.put("VILLE_BENEFICIAIRE", rglt.getVilleBeneficiaireLettre());

	}

	private void builDetailsParameters(EditionVO vo, Reglement rglt,
			Sinistre sinistre, DetailReglement d) {
		if (d != null) {
			String m = ConvertierMontantEnLettre.montantToLettre(
					decimalLongFormat.format(d.getMontant()-d.getMontantRejete()).replaceAll(",",
							"."), "DIRHAMS", "CENTIMES");
			if (eai.devass.sinistreat.appli.utils.IConstantes.RUBRIQUE_RACHAT
					.equals(d.getCodePrestation())) {
				params.put("MONTANTREGLELETTRERACHAT", m);
			} else if (eai.devass.sinistreat.appli.utils.IConstantes.RUBRIQUE_ARRERAGE_AVANT_CONSTITUTION
					.equals(d.getCodePrestation())) {
				params.put("MONTANTREGLELETTREARRERAGE", m);
				params.put("DATEDEBUTARRERAGE",dateFormat.format(d.getDateDebutPrestation()));
				params.put("DATEFINARRERAGE", dateFormat.format(d.getDateFinPrestation()));
			} else if (eai.devass.sinistreat.appli.utils.IConstantes.RUBRIQUE_FRAIS_MEDICAUX
					.equals(d.getCodePrestation())) {
				
				params.put("MONTANTREGLELETTREFRAISMEDICAUX", m);
			} else if (eai.devass.sinistreat.appli.utils.IConstantes.RUBRIQUE_FRAIS_FUNERAIRE
					.equals(d.getCodePrestation())) {

				params.put("MONTANTREGLELETTREFRAISFUNERAIRE", m);
			} else if (eai.devass.sinistreat.appli.utils.IConstantes.RUBRIQUE_ITT
					.equals(d.getCodePrestation())) {

				if ("0".equals(vo.getContentieux())) {
					buildQtcITTConciliation(vo, rglt, sinistre, d);
				} else {
					params.put("MONTANTREGLELETTREITT", m);
				}

			}

		}
	}

	private void buildQtcITTConciliation(EditionVO vo, Reglement rglt,
			Sinistre sinistre, DetailReglement d) {

		Date dateReprise = null;
		Date dateITT = null;
		String delta = "";
		String montantRubrique = "";
		double mnt = 0.0;
		long nbrjour = 0;

		dateReprise = d.getDateFinPrestation();
		dateITT = d.getDateDebutPrestation();
		mnt = Double.valueOf((d.getMontant()==null?0.0:d.getMontant())-(d.getMontantRejete()==null?0.0:d.getMontantRejete()));
		montantRubrique = String.valueOf(mnt);
		if (dateReprise != null && dateITT != null) {
			nbrjour = (((dateReprise.getTime() - dateITT.getTime())) / MILLISECONDS_PER_DAY);
			nbrjour = nbrjour + 2;
		}

		delta = Long.toString(nbrjour);

		String montantChiffre = decimalLongFormat.format(Double
				.parseDouble(montantRubrique));
		montantChiffre = montantChiffre.replaceAll("\\s+", "");
		montantChiffre = montantChiffre.replace(',', '.');
		mnt = Double.parseDouble(montantRubrique);
		String montantLettre = ConvertierMontantEnLettre.montantToLettre(
				decimalLongFormat.format(mnt).replaceAll(",", "."), "DIRHAMS",
				"CENTIMES");
		params.put("DATE_EDITION", dateFormat.format(new Date()));
		if (sinistre.getRefEvenement() != null) {
			params.put("DATE_ACCIDENT", dateFormat.format(sinistre
					.getRefEvenement().getDateAccident()));
		}

		params.put("NUM_SIN", sinistre.getNumeroSinistre());
		params.put("N_GRAVE", sinistre.getNumeroGrave());
		params.put("NOM_ASSURER", sinistre.getNomClient());
		params.put("VICTIME", sinistre.getRefVictime().getNomprenom());
		if (dateITT != null) {
			params.put("DATE_DEPART", dateFormat.format(dateITT));
		}
		if (dateReprise != null) {
			params.put("DATE_FIN", dateFormat.format(dateReprise));
		}
		if (sinistre.getRefVictime() != null) {
			params.put("SALAIRE_MENS", sinistre.getRefVictime()
					.getSalaireMensuel());
			params.put("SALAIRE_JOURNALIER", sinistre.getRefVictime()
					.getSalaireJournalier());
		}

		params.put("NBR_JOUR", delta);

		params.put("MONTANT_RUBRIQUE_CHIFFRE", montantChiffre);
		params.put("MONTANT_RUBRIQUE_LETTRE", montantLettre);
		params.put("NBR_EDITION",
				Integer.parseInt(vo.getRefReglementVO().getNbrEdition()));
		params.put("RGL", (rglt.getId()).toString());
		params.put("NOM_MODIFICATEUR", vo.getRefReglementVO()
				.getNomUserModificateur());

	}

	private boolean hasITTConciliation(List<DetailReglement> details,
			EditionVO vo) {

		if ("0".equals(vo.getContentieux()) && Fonctions.isNotEmpty(details)
				&& details.size() == 1) {
			DetailReglement d = details.get(0);
			if (d != null
					&& eai.devass.sinistreat.appli.utils.IConstantes.RUBRIQUE_ITT
							.equals(d.getCodePrestation())
					&& vo.getRefReglementVO() != null
					&& (vo.getRefReglementVO().getCodeUserModificateur() == null || !"I"
							.equals(vo.getRefReglementVO()
									.getCodeUserModificateur()))) {
				return true;
			}

		}
		return false;

	}

	@Override
	protected String getCodeTemplate(EditionVO editionVO) {
		return null;
	}

}
