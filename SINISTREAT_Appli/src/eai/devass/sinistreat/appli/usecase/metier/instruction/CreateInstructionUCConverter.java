package eai.devass.sinistreat.appli.usecase.metier.instruction;

import java.util.ArrayList;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.businessInterface.impl.ValueObjectConverterAbst;
import ma.co.omnidata.framework.services.validation.ValidationUnitaireException;

import org.apache.log4j.Logger;

import eai.devass.sinistreat.appli.modele.metier.instruction.Instruction;
import eai.devass.sinistreat.appli.utils.ConverterTools;
import eai.devass.sinistreat.appli.utils.Fonctions;
import eai.devass.sinistreat.appli.utils.exception.IMessageException;
import eai.devass.sinistreat.appli.valueobjects.metier.instruction.InstructionVO;

public class CreateInstructionUCConverter extends ValueObjectConverterAbst
		implements IMessageException {

	protected Fonctions functions = new Fonctions();
	ConverterTools converterTools = ConverterTools.getInstance();
	private Logger logger = Logger.getLogger("loggerSINAT");

	@Override
	public Object doConvertItemsToValueObject(List listeItems) {
		if (listeItems == null || listeItems.isEmpty()) {
			return null;
		}
		InstructionVO instructionVO = null;
		Instruction instruction = (Instruction) listeItems.get(0);
		try {
			if (instruction == null) {
				return null;
			}
			instructionVO = (InstructionVO) converterTools
					.convertToObjectVOWithoutList(instruction);

		} catch (Exception e) {
			logger.error("problème technique", e);
		}
		return instructionVO;
	}

	@Override
	public List doConvertValueObjectToItems(Object vo)
			throws ValidationUnitaireException, ValidationException {
		List listOut = new ArrayList();
		InstructionVO instructionVO = (InstructionVO) vo;
		try {
			Instruction instruction = (Instruction) converterTools
					.convertToObjectBO(instructionVO);
			listOut.add(instruction);
			
		} catch (Exception e) {
			Logger.getLogger("loggerGSR").fatal(
					"Erreur lors de la conversion !", e);
			throw new ValidationUnitaireException(e.getMessage());
		}

		return listOut;
	}

}
