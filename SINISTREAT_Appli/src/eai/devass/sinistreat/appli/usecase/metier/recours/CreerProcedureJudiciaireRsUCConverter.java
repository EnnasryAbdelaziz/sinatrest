package eai.devass.sinistreat.appli.usecase.metier.recours;

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

public class CreerProcedureJudiciaireRsUCConverter extends
		ValueObjectConverterAbst implements IMessageException {

	protected Fonctions functions = new Fonctions();
	ConverterTools converterTools = ConverterTools.getInstance();
	private Logger logger = Logger.getLogger("loggerSINAT");
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
				throw new FonctionnelleException(
						"EXP_TYPE_JURIDICTION_REQUIRED");
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
