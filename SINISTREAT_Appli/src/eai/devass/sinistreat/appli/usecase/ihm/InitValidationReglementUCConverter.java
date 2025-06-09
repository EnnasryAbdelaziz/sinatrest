package eai.devass.sinistreat.appli.usecase.ihm;

import java.util.ArrayList;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.businessInterface.impl.ValueObjectConverterAbst;
import ma.co.omnidata.framework.services.validation.ValidationUnitaireException;

import org.apache.log4j.Logger;

import eai.devass.commun.appli.util.CommonUtils;
import eai.devass.sinistreat.appli.modele.metier.reglement.Reglement;
import eai.devass.sinistreat.appli.modele.metier.sinistre.Sinistre;
import eai.devass.sinistreat.appli.utils.ConverterTools;
import eai.devass.sinistreat.appli.utils.Fonctions;
import eai.devass.sinistreat.appli.valueobjects.metier.reglement.ReglementVO;


@SuppressWarnings("unchecked")
public class InitValidationReglementUCConverter extends ValueObjectConverterAbst {

	protected Fonctions functions = new Fonctions();
	ConverterTools converterTools = ConverterTools.getInstance();
	private Logger logger = Logger.getLogger("loggerSINAT");
	
	public Object doConvertItemsToValueObject(List listeItems) {
		
		if (listeItems == null || listeItems.isEmpty()) {
			return null;
		}
		List<ReglementVO> listReglementVO = new ArrayList<ReglementVO>();
		//List listrglvo=null;
		try {
			List<Reglement> listReglement = (List<Reglement>)listeItems.get(0);
			if(CommonUtils.getInstance().isEmpty(listReglement)) {
				return listReglementVO;
			}
			
			Reglement reglement = listReglement.get(0);
			reglement.setPropertiesToConvert(new String[] { "id",
					"numeroQuittance", "numeroQuittanceRemplacement",
					"typeBeneficiaire", "nomBeneficiaire", "codeIntermediaire",
					"nomIntermediaire", "dateEmission", "nomUserCreateur",
					"codeUserCreateur", "codeUserModificateur",
					"nomUserModificateur", "montant", "dateCreation",
					"dateModification", "dateReglement", "modeReglement",
					"dateEtablissement", "typeIntermediaireRgl",
					"nomChefGreffier", "nomBarreau", "codeBarreau",
					"nomIntermediaireRgl", "codeIntermediaireRgl",
					"avocatTiers ", "referenceAuxiliaire","dateNote", "dateReception", "nomMandataire",
					"codeMandataire", "nomAuxiliaire", "codeAuxiliaire",
					"numeroBordereau", "dateBordereau", "numCheque",
					"refNatureRecuperation", "numeroRemise", "dateRemise",
					"dateEtat", "emetteur", "codeAvocatAdverse",
					"nomAvocatAdverse", "nomPartieAdverse ",
					"typePartieAdverse", "codePartieAdverse", "motifTropPercu",
					"codeBeneficiaire ", "refTypeQuittance.code",
					"refTypeQuittance.libelle", "sousTypeQuittance",
					"refEtatReglement.code", "refEtatReglement.libelle",
					"refTypeReglement.code", "refTypeReglement.libelle",
					"refSinistre.numeroSinistre", "listDetailReglement",
					"idOrdonnancement","service", "refSinistre.numeroGrave", 
                    "refSinistre.refVictime.nomprenom",
                    "refSinistre.nomClient",
                    "refSinistre.refEvenement.dateAccident",
                    "refSinistre.refVictime.salaireMensuel",
                    "refSinistre.refVictime.salaireJournalier", "adresseBenef",
                    "villeBenef", "refProcedure", "refSinistre.id"});		
		
			listReglementVO = (List<ReglementVO>)ConverterTools.getInstance().convertToListObjectVOWithoutList(listReglement);		
			
		} catch (Exception e) {
			logger.error("problème technique",e);
		}
		return listReglementVO;
		
		
	}

	public List doConvertValueObjectToItems(Object vo) throws ValidationUnitaireException, ValidationException {
		List listOut = new ArrayList();
		ReglementVO reglementVO = (ReglementVO) vo;
		try {
			Reglement reglement = (Reglement) converterTools.convertToObjectBO(reglementVO);
			listOut.add(reglement);
		} catch (Exception e) {
			Logger.getLogger("loggerGSR").fatal("Erreur lors de la conversion !", e);
		}
		return listOut;
	}
}
