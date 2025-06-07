package eai.devass.sinistreat.appli.usecase.metier.contentieux;

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

public class ConsulterProcedureJudiciaireReUCConverter extends
ValueObjectConverterAbst implements IMessageException {
	protected Fonctions functions = new Fonctions();
	ConverterTools converterTools = ConverterTools.getInstance();
	private Logger logger = Logger.getLogger("loggerSINAT");
	@SuppressWarnings({ "unchecked","rawtypes"})
	public Object doConvertItemsToValueObject(List listeItems) {

		if (listeItems == null || listeItems.isEmpty()) {
			return null;
		}
		
		ProcedureJudiciaireVO procvo= new ProcedureJudiciaireVO();
		try {
			ProcedureJudiciaire proc = (ProcedureJudiciaire)listeItems.get(0);
			procvo=(ProcedureJudiciaireVO)converterTools.convertToObjectVO(proc);
			

		} catch (Exception e) {
			logger.error("problème technique",e);
		}

		return procvo;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List doConvertValueObjectToItems(Object vo)
			throws ValidationUnitaireException, ValidationException {
	
		List listOut = new ArrayList();
		ProcedureJudiciaireVO procedure = (ProcedureJudiciaireVO)vo;
		try {
			ProcedureJudiciaire proc=(ProcedureJudiciaire)converterTools.convertToObjectBO(procedure);
			listOut.add(proc);
		} catch (Exception e) {
			Logger.getLogger("loggerGSR").fatal("Erreur lors de la conversion !", e);
			throw new ValidationUnitaireException(e.getMessage());
		}

		return listOut;
	}
}

