//package eai.devass.sinistreat.appli.usecase.metier.contentieux;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import ma.co.omnidata.framework.services.businessInterface.ValidationException;
//import ma.co.omnidata.framework.services.businessInterface.impl.ValueObjectConverterAbst;
//import ma.co.omnidata.framework.services.validation.ValidationUnitaireException;
//import eai.devass.recoursentrant.application.utils.entites.IConstantes;
//import eai.devass.sinistreat.appli.exception.FonctionnelleException;
//import eai.devass.sinistreat.appli.modele.metier.contentieux.ProcedureJudiciaire;
//import eai.devass.sinistreat.appli.modele.metier.sinistre.Sinistre;
//import eai.devass.sinistreat.appli.utils.ConverterMetier;
//import eai.devass.sinistreat.appli.utils.ConverterTools;
//import eai.devass.sinistreat.appli.utils.Fonctions;
//import eai.devass.sinistreat.appli.utils.exception.IMessageException;
//import eai.devass.sinistreat.appli.valueobjects.metier.contentieux.ProcedureJudiciaireVO;
//import eai.devass.sinistreat.appli.valueobjects.metier.sinistre.SinistreVO;
//
//public class CreerProcedureJudiciaireReUCConverter extends
//		ValueObjectConverterAbst implements IMessageException, IConstantes {
//
//	protected Fonctions functions = new Fonctions();
//	ConverterMetier converterMetier = ConverterMetier.getInstance();
//	ConverterTools converterTools = ConverterTools.getInstance();
//
//	public Object doConvertItemsToValueObject(List listeItems) {
//
//		if (listeItems == null || listeItems.isEmpty()) {
//			return null;
//		}
//		ProcedureJudiciaireVO procedureJudiciaireVO = null;
//		ProcedureJudiciaire pR=(ProcedureJudiciaire)listeItems.get(0);
//		try {
//			if(pR==null)
//				return null;
//			
//			procedureJudiciaireVO = (ProcedureJudiciaireVO)ConverterTools.getInstance().convertToObjectVOWithoutList(pR);
//
//		} catch (Exception e) {
//			logger.error("problème technique",e);
//		}
//
//		return procedureJudiciaireVO;
//	}
//
//	public List doConvertValueObjectToItems(Object vo)
//			throws ValidationUnitaireException, ValidationException {
//		
//		List listOut = new ArrayList();
//		ProcedureJudiciaireVO procedureJudiciaireVO = (ProcedureJudiciaireVO)vo;
//		ProcedureJudiciaire procedureJudiciaire=new ProcedureJudiciaire();
//		
//		try {
//			procedureJudiciaire=(ProcedureJudiciaire)converterTools.convertToObjectBO(procedureJudiciaireVO);
//			if (procedureJudiciaire.getRefJuridiction() == null
//					|| procedureJudiciaire.getRefJuridiction().getCode().isEmpty())
//				throw new FonctionnelleException("EXP_JURIDICTION_REQUIRED");
//			if (procedureJudiciaire.getRefTypeJuridiction() == null
//					|| procedureJudiciaire.getRefTypeJuridiction().getCode().isEmpty())
//				throw new FonctionnelleException(
//						"EXP_TYPE_JURIDICTION_REQUIRED");
//			if (procedureJudiciaire.getRefNatureDossier() == null
//					|| procedureJudiciaire.getRefNatureDossier().getCode().isEmpty())
//				throw new FonctionnelleException("EXP_NATURE_DOSSIER_REQUIRED");
//			listOut.add(procedureJudiciaire);
//		} catch (Exception e) {
//			if (Fonctions.sendMail) {
//				functions.sendMail(e, procedureJudiciaireVO);
//				logger.error("problème technique",e);
//			}
//			throw new ValidationUnitaireException(e.getMessage());
//		}
//
//		return listOut;
//	}
//
//}
package eai.devass.sinistreat.appli.usecase.metier.contentieux;

import java.util.ArrayList;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.businessInterface.impl.ValueObjectConverterAbst;
import ma.co.omnidata.framework.services.validation.ValidationUnitaireException;

import org.apache.log4j.Logger;

import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.modele.metier.contentieux.ProcedureJudiciaire;
import eai.devass.sinistreat.appli.utils.ConverterTools;
import eai.devass.sinistreat.appli.utils.Fonctions;
import eai.devass.sinistreat.appli.utils.exception.IMessageException;
import eai.devass.sinistreat.appli.valueobjects.metier.contentieux.ProcedureJudiciaireVO;

public class CreerProcedureJudiciaireReUCConverter extends
		ValueObjectConverterAbst implements IMessageException {

	protected Fonctions functions = new Fonctions();
	private Logger logger = Logger.getLogger("loggerSINAT");
	ConverterTools converterTools = ConverterTools.getInstance();

	public Object doConvertItemsToValueObject(List listeItems) {

		if (listeItems == null || listeItems.isEmpty()) {
			return null;
		}
		ProcedureJudiciaireVO procedureJudiciaireVO = null;
		try {
			ProcedureJudiciaire proc = (ProcedureJudiciaire) listeItems.get(0);
			procedureJudiciaireVO = (ProcedureJudiciaireVO) converterTools
					.convertToObjectVO(proc);

		} catch (Exception e) {
			logger.error("problème technique",e);
		}

		return procedureJudiciaireVO;
	}

	@SuppressWarnings("unchecked")
	public List doConvertValueObjectToItems(Object vo)
			throws ValidationUnitaireException, ValidationException {

		List listOut = new ArrayList();
		ProcedureJudiciaireVO procedureJudiciaireVO = (ProcedureJudiciaireVO) vo;
		try {
			if (procedureJudiciaireVO.getRefJuridiction() == null
					|| (procedureJudiciaireVO.getRefJuridiction().getId() != null && procedureJudiciaireVO
							.getRefJuridiction().getId().equals(""))) {
				throw new FonctionnelleException("EXP_JURIDICTION_REQUIRED");
			}
			if (procedureJudiciaireVO.getRefTypeJuridiction() == null
					|| (procedureJudiciaireVO.getRefJuridiction().getCode() != null && procedureJudiciaireVO.getRefTypeJuridiction().getCode()
							.equals(""))) {
				throw new FonctionnelleException("EXP_JURIDICTION_REQUIRED");
			}
			if (procedureJudiciaireVO.getRefRecours() == null
					|| procedureJudiciaireVO.getRefRecours().getRefSinistre() == null
					|| procedureJudiciaireVO.getRefRecours().getRefSinistre()
							.getId() == null
					|| procedureJudiciaireVO.getRefRecours().getRefSinistre()
							.getId().equals("")) {
				throw new FonctionnelleException("EXP_NUM_SINISTRE_REQUIRED");
			}

			ProcedureJudiciaire proc = (ProcedureJudiciaire) converterTools
					.convertToObjectBO(procedureJudiciaireVO);
			listOut.add(proc);
		} catch (Exception e) {
			Logger.getLogger("loggerGSR").fatal("Erreur lors de la conversion !", e);
			throw new ValidationUnitaireException(e.getMessage());
		}

		return listOut;
	}

}
