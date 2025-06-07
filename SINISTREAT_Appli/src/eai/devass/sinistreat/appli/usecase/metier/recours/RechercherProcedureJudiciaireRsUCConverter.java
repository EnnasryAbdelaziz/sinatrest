package eai.devass.sinistreat.appli.usecase.metier.recours;

import java.util.ArrayList;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.businessInterface.impl.ValueObjectConverterAbst;
import ma.co.omnidata.framework.services.validation.ValidationUnitaireException;

import org.apache.log4j.Logger;

import eai.devass.sinistreat.appli.modele.metier.contentieux.ProcedureJudiciaire;
import eai.devass.sinistreat.appli.utils.ConverterTools;
import eai.devass.sinistreat.appli.utils.Fonctions;
import eai.devass.sinistreat.appli.utils.exception.IMessageException;
import eai.devass.sinistreat.appli.valueobjects.metier.contentieux.ProcedureJudiciaireVO;

public class RechercherProcedureJudiciaireRsUCConverter extends
		ValueObjectConverterAbst implements IMessageException {
	protected Fonctions functions = new Fonctions();
	ConverterTools converterTools = ConverterTools.getInstance();
	private Logger logger = Logger.getLogger("loggerSINAT");
	@SuppressWarnings({ "unchecked","rawtypes"})
	public Object doConvertItemsToValueObject(List listeItems) {

		if (listeItems == null || listeItems.isEmpty()) {
			return null;
		}
		
		
		List<ProcedureJudiciaireVO> listprocvo= new ArrayList<ProcedureJudiciaireVO>();
		try {
			List<ProcedureJudiciaire> listproc = (List<ProcedureJudiciaire>)listeItems.get(0);
			listprocvo=(List)converterTools.convertToListObjectVOWithoutList(listproc);
			

		} catch (Exception e) {
			logger.error("problème technique",e);
		}

		return listprocvo;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List doConvertValueObjectToItems(Object vo)
			throws ValidationUnitaireException, ValidationException {
	
		List listOut = new ArrayList();
		ProcedureJudiciaireVO procedureJudiciaireVO = (ProcedureJudiciaireVO)vo;
		try {
		
			ProcedureJudiciaire proc=(ProcedureJudiciaire)converterTools.convertToObjectBO(procedureJudiciaireVO);
			listOut.add(proc);
		} catch (Exception e) {
			Logger.getLogger("loggerGSR").fatal("Erreur lors de la conversion !", e);
			throw new ValidationUnitaireException(e.getMessage());
		}

		return listOut;
	}
}
