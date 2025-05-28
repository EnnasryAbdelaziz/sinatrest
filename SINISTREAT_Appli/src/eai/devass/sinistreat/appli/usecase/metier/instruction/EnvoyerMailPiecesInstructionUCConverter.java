package eai.devass.sinistreat.appli.usecase.metier.instruction;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import eai.devass.sinistreat.appli.modele.metier.conciliation.RelanceConciliation;
import eai.devass.sinistreat.appli.modele.metier.instruction.Instruction;
import eai.devass.sinistreat.appli.utils.ConverterTools;
import eai.devass.sinistreat.appli.utils.Fonctions;
import eai.devass.sinistreat.appli.utils.exception.IMessageException;
import eai.devass.sinistreat.appli.valueobjects.metier.conciliation.RelanceConciliationVO;
import eai.devass.sinistreat.appli.valueobjects.metier.instruction.InstructionVO;
import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.businessInterface.impl.ValueObjectConverterAbst;
import ma.co.omnidata.framework.services.validation.ValidationUnitaireException;

@SuppressWarnings("all")
public class EnvoyerMailPiecesInstructionUCConverter  extends ValueObjectConverterAbst
implements IMessageException{
	
	ConverterTools converterTools = ConverterTools.getInstance();
	protected Fonctions functions = new Fonctions();
	private Logger logger = Logger.getLogger("loggerSINAT");
	
	public Object doConvertItemsToValueObject(List listeItems) {
		if (listeItems == null || listeItems.isEmpty()) {
			return null;
		}
		InstructionVO instructionVO = new InstructionVO();
		try {
			Instruction instruction = (Instruction) listeItems.get(0);
			instructionVO = (InstructionVO) ConverterTools.getInstance().convertToObjectVO(instruction);

		} catch (Exception e) {
			logger.error("problème technique",e);
		}
		return instructionVO;
	}

	
		public List doConvertValueObjectToItems(Object vo)
		throws ValidationUnitaireException, ValidationException {
				
				List listOut = new ArrayList();
				InstructionVO instructionVO = (InstructionVO) vo;
				
				
				try {
					Instruction instruction = (Instruction) converterTools.convertToObjectBO(instructionVO);
					listOut.add(instruction);
				} catch (Exception e) {
					Logger.getLogger("loggerSinat").fatal("Erreur lors de la conversion !", e);
					throw new ValidationUnitaireException(e.getMessage());
				}
				
				return listOut;
				}

}
