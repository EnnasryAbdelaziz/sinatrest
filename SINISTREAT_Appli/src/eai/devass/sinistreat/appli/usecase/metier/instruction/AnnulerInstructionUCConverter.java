package eai.devass.sinistreat.appli.usecase.metier.instruction;

import java.util.ArrayList;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.businessInterface.impl.ValueObjectConverterAbst;
import ma.co.omnidata.framework.services.validation.ValidationUnitaireException;

import org.apache.log4j.Logger;

import eai.devass.sinistreat.appli.modele.metier.instruction.Instruction;
import eai.devass.sinistreat.appli.utils.ConverterTools;
import eai.devass.sinistreat.appli.utils.exception.IMessageException;
import eai.devass.sinistreat.appli.valueobjects.metier.instruction.InstructionVO;

public class AnnulerInstructionUCConverter extends ValueObjectConverterAbst
		implements IMessageException {
	private Logger logger = Logger.getLogger("loggerSINAT");

	public AnnulerInstructionUCConverter() {
		converterTools = ConverterTools.getInstance();
	}

	public Object doConvertItemsToValueObject(List listeItems) {
		if (listeItems == null || listeItems.isEmpty()) {
			return null;
		}
		Instruction instruction = null;
		InstructionVO instVo = new InstructionVO();
		try {
			instruction = (Instruction) listeItems.get(0);
			instVo = (InstructionVO) converterTools
					.convertToObjectVO(instruction);
		} catch (Exception e) {
			logger.error("problème technique", e);
		}
		return instVo;
	}

	public List doConvertValueObjectToItems(Object vo)
			throws ValidationUnitaireException, ValidationException {
		try {
			List lisrgl = new ArrayList();
			Instruction inst = (Instruction) converterTools
					.convertToObjectBO((InstructionVO) vo);
			lisrgl.add(inst);
			return lisrgl;
		} catch (Exception e) {
			throw new ValidationUnitaireException(e.getMessage());
		}
	}

	ConverterTools converterTools;
}
