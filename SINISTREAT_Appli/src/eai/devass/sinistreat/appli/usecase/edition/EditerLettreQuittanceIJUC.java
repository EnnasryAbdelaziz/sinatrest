package eai.devass.sinistreat.appli.usecase.edition;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import eai.devass.edition.Utils.IConstantes;
import eai.devass.edition.valueobjects.EditionVO;
import eai.devass.gsr.appli.utile.ConvertierMontantEnLettre;
import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.modele.metier.reglement.DetailReglement;
import eai.devass.sinistreat.appli.modele.metier.reglement.Reglement;
import eai.devass.sinistreat.appli.modele.metier.sinistre.Sinistre;
import eai.devass.sinistreat.appli.utils.entites.IDate;

public class EditerLettreQuittanceIJUC extends EditerCombiningJrxmlUC {
	private final SimpleDateFormat dateFormat = new SimpleDateFormat(
			IDate.FORMAT_SIMPLE);
	final long MILLISECONDS_PER_DAY = (1001 * 60 * 60 * 24) + 1;
	public DecimalFormat decimalLongFormat = new DecimalFormat("##########.##");
	private static final Logger logger = Logger.getLogger("loggerSINAT");

	@Override
	protected List<String> getCodesTemplate(EditionVO editionVO) {
		if(editionVO.getRefReglementVO().getCodeUserModificateur() != null && "I".equals(editionVO.getRefReglementVO().getCodeUserModificateur())){
				return Arrays.asList(IConstantes.CODE_TEMPLATE_LETTRE_QUITTANCE_IJ);
		}
		return Arrays.asList(IConstantes.CODE_TEMPLATE_LETTRE_QUITTANCE_IJ,IConstantes.CODE_TEMPLATE_LETTRE_ENVOI_IJ);
	}
	@Override
	protected boolean imprimer(IValueObject vo) {
		return false;
	}
	@Override
	protected Map<String, Object> getRapportParametresValues(IValueObject vo) throws FonctionnelleException {

		Map<String, Object> params = new HashMap<String, Object>();
		EditionVO editionVO = (EditionVO) vo;
		String idReglement = editionVO.getRefReglementVO().getIdReglement();
		Reglement reg = reglementManager.getReglementById(idReglement);
		Sinistre sinistreVO = reg.getRefSinistre();
		Date dateReprise = null;
		Date dateITT = null;
		String delta = "";
		String montantRubrique ="";
		for (DetailReglement dreg : reg.getListDetailReglement()) {
			if("20".equals(dreg.getCodePrestation())){
				dateReprise = dreg.getDateFinPrestation();
				dateITT = dreg.getDateDebutPrestation();
				double mnt = Double.valueOf(dreg.getMontant() - dreg.getMontantRejete());
				montantRubrique = String.valueOf(mnt);
				break;
			}
		}
		
		long nbrjour = (((dateReprise.getTime() - dateITT.getTime())) / MILLISECONDS_PER_DAY);
		nbrjour = nbrjour + 2 ;
		
		delta = Long.toString(nbrjour);
		
		String montantChiffre = decimalLongFormat.format(Double.parseDouble(montantRubrique));
		montantChiffre = montantChiffre.replaceAll("\\s+", "");
		montantChiffre = montantChiffre.replace(',', '.');
		Double mnt = Double.parseDouble(montantRubrique);
		String montantLettre = ConvertierMontantEnLettre.montantToLettre(decimalLongFormat.format(mnt).replaceAll(",", "."), "DIRHAMS","CENTIMES");
		params.put("DATE_EDITION", dateFormat.format(new Date()));
		params.put("DATE_ACCIDENT", sinistreVO.getRefEvenement().getDateAccident());
		params.put("NUM_SIN", sinistreVO.getNumeroSinistre());
		params.put("N_GRAVE", sinistreVO.getNumeroGrave());
		params.put("NOM_ASSURER", sinistreVO.getNomClient());
		params.put("VICTIME", sinistreVO.getRefVictime().getNomprenom());
		params.put("DATE_DEPART",dateFormat.format(dateITT));
		params.put("DATE_FIN", dateFormat.format(dateReprise));
		params.put("SALAIRE_MENS", sinistreVO.getRefVictime().getSalaireMensuel());
		params.put("NBR_JOUR", delta);
		params.put("SALAIRE_JOURNALIER", sinistreVO.getRefVictime().getSalaireJournalier());
		params.put("MONTANT_RUBRIQUE_CHIFFRE", montantChiffre);
		params.put("MONTANT_RUBRIQUE_LETTRE", montantLettre);
		params.put("NBR_EDITION", Integer.parseInt(editionVO.getRefReglementVO().getNbrEdition()));
		params.put("RGL", (reg.getId()).toString());
		params.put("NOM_MODIFICATEUR", editionVO.getRefReglementVO().getNomUserModificateur());
		return params;
	}
}
