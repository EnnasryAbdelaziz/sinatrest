package eai.devass.sinistreat.appli.usecase.metier.reglement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.businessInterface.impl.ValueObjectConverterAbst;
import ma.co.omnidata.framework.services.validation.ValidationUnitaireException;

import org.apache.log4j.Logger;

import eai.devass.commun.appli.converter.AConverter;
import eai.devass.sinistreat.appli.modele.metier.reglement.Reglement;
import eai.devass.sinistreat.appli.utils.ConverterTools;
import eai.devass.sinistreat.appli.utils.Fonctions;
import eai.devass.sinistreat.appli.utils.exception.IMessageException;
import eai.devass.sinistreat.appli.valueobjects.metier.reglement.ReglementVO;

public class RechercheReglementBisUCConverter extends ValueObjectConverterAbst
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
									"nomBarreau", "codeBarreau", "avocatTiers",
									"codeIntermediaireRgl", "nomIntermediaireRgl",
									"motifRejet", "montant", "dateReglement","numeroRemise","dateRemise",
									"modeReglement", "dateEtablissement","dateBordereau",
									"nomAuxiliaire", "codeAuxiliaire",
									"codeChefGreffier","nomChefGreffier",
									"numCheque", "nomMandataire", "vosReference","dateEtat","dateCreation",
									"codeMandataire", "villeBenef","numeroBordereau",
									"adresseBenef", "referenceAuxiliaire",
									"dateNote", "numeroMission", "dateSignature", "refBanque.code", "Emission", "service", "Etablissement", 
									"nomBeneficiaireLettre", "typeBeneficiaireLettre", "dateReception", "refProcedure", "salaireMensuel", "renteAnnuelle", "dateProchaineEcheance","rappel", "smig", "creatorFirstQtc","emissionITT", "listFraisMedicaux","adresseBeneficiaireLettre","villeBeneficiaireLettre", "codeIntermediaireBL", "codePrestataireBL","nomDestinataireCheque", "typeDestinataireCheque","adresseDestinataireCheque",
									"villeDestinataireCheque","codePrestataireDC","codeIntermediaireDC","zoneARisque","ras","montantRAS"});
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
