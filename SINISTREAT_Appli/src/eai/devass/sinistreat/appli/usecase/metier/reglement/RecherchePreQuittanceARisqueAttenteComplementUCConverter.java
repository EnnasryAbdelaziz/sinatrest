package eai.devass.sinistreat.appli.usecase.metier.reglement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import eai.devass.sinistreat.appli.modele.metier.reglement.Reglement;
import eai.devass.sinistreat.appli.utils.ConverterTools;
import eai.devass.sinistreat.appli.utils.Fonctions;
import eai.devass.sinistreat.appli.utils.exception.IMessageException;
import eai.devass.sinistreat.appli.valueobjects.metier.reglement.ReglementVO;
import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.businessInterface.impl.ValueObjectConverterAbst;
import ma.co.omnidata.framework.services.validation.ValidationUnitaireException;

public class RecherchePreQuittanceARisqueAttenteComplementUCConverter extends ValueObjectConverterAbst
implements IMessageException {

protected Fonctions functions = new Fonctions();
ConverterTools converterTools = ConverterTools.getInstance();
private Logger logger = Logger.getLogger("loggerSINAT");

public Object doConvertItemsToValueObject(List listeItems) {
Map map = new HashMap();
if (listeItems == null || listeItems.isEmpty()) {
	return null;
}
try {
	List listReglement = (List) listeItems.get(0);
	// Liste entite reglement
	if (listReglement != null && !listReglement.isEmpty()) {
		Object reglement = listReglement.get(0);
		if (reglement instanceof Reglement) {
			((Reglement) reglement)
					.setPropertiesToConvert(new String[] {
							"id",
							"refSinistre.numeroSinistre",
							"numeroQuittance",
							"refTypeReglement.libelle",
							"nomBeneficiaire", "dateEtablissement",
							"dateReglement", "refTypeReglement.code",
							"refEtatReglement.code", "refSinistre.id",
							"montant", "typeBeneficiaire","idOrdonnancement",
							"nomBeneficiaire", "listDetailReglement",
							"codeIntermediaire", "nomIntermediaire",
							"codeIntermediaireRgl", "nomIntermediaireRgl",
							"motifRejet", "montant", "dateReglement","numeroRemise","dateRemise",
							"modeReglement", "dateEtablissement","dateBordereau",
							"nomAuxiliaire", "codeAuxiliaire",
							"numCheque", "nomMandataire", "vosReference","dateEtat","dateCreation",
							"codeMandataire", "villeBenef","numeroBordereau","refSinistre.nomClient",
							"adresseBenef", "referenceAuxiliaire","refSinistre.refVictime.refVille.libelle","refSinistre.refEvenement.refVille.libelle",
							"dateNote", "numeroMission", "dateSignature", "refBanque.code", "Emission", "service", "Etablissement", 
							"nomBeneficiaireLettre", "typeBeneficiaireLettre", "dateReception", "refProcedure","codeUserCreateur","nomUserCreateur","zoneARisque"});
			List listReglementvo = (List) converterTools
					.convertToListObjectVO(listReglement);
			if (listReglementvo != null && !listReglementvo.isEmpty()) {
				map.put("list"
						+ listReglementvo.get(0).getClass()
								.getSimpleName(), listReglementvo);
			}
			return map;
		}

	}
} catch (Exception e) {
	logger.error("problème technique", e);
}
return null;

}

public List doConvertValueObjectToItems(Object vo)
	throws ValidationUnitaireException, ValidationException {

List listOut = new ArrayList();
ReglementVO rglvo = (ReglementVO) vo;
try {
	Reglement rgl = (Reglement) converterTools.convertToObjectBO(rglvo);
	listOut.add(rgl);
} catch (Exception e) {
	Logger.getLogger("loggerGSR").fatal(
			"Erreur lors de la conversion !", e);
	throw new ValidationUnitaireException(e.getMessage());
}

return listOut;
}

}
