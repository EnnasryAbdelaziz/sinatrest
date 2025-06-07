package eai.devass.sinistreat.appli.usecase.metier.instruction;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.businessInterface.impl.ValueObjectConverterAbst;
import ma.co.omnidata.framework.services.validation.ValidationUnitaireException;

import org.apache.log4j.Logger;

import eai.devass.sinistreat.appli.modele.metier.instruction.Instruction;
import eai.devass.sinistreat.appli.utils.ConverterTools;
import eai.devass.sinistreat.appli.valueobjects.metier.instruction.InstructionVO;

public class InitCreationInstructionUCConverter extends ValueObjectConverterAbst  {
	private Logger logger = Logger.getLogger("loggerSINAT");
	@Override
	public Object doConvertItemsToValueObject(List listeItems) {
		
		if (listeItems == null || listeItems.isEmpty()) {
			return null;
		}
		Map map= new HashMap();
		try {
			List listResultBO0 = (List)listeItems.get(0);
			List listResultBO1 = (List)listeItems.get(1);
			List listResultBO2 = (List)listeItems.get(2);
			List listResultBO4 = (List)listeItems.get(4);
			List listResultBO5 = (List)listeItems.get(5);
			List listResultBO6 = (List)listeItems.get(6);
			
			List listTypeLettreInstructionVO = ConverterTools.getInstance().convertToListObjectVO(listResultBO0);		
			List listDestinataireVO = ConverterTools.getInstance().convertToListObjectVO(listResultBO1);
			List listPiecesVO = ConverterTools.getInstance().convertToListObjectVO(listResultBO2);
			InstructionVO instructionVO = (InstructionVO) ConverterTools.getInstance().convertToObjectVO((Instruction)listeItems.get(3));
			List listTypeInstructionVO = ConverterTools.getInstance().convertToListObjectVO(listResultBO4);
			List listRejetsVO = ConverterTools.getInstance().convertToListObjectVO(listResultBO5);
			List listDelegationVO = ConverterTools.getInstance().convertToListObjectVO(listResultBO6);
			
			map.put("listTypeLettreInstructionVO", listTypeLettreInstructionVO);
			map.put("listDestinataireVO", listDestinataireVO);	
			map.put("listPiecesVO", listPiecesVO);	
			map.put("Instruction", instructionVO);
			map.put("listTypeInstructionVO", listTypeInstructionVO);
			map.put("listRejetsVO", listRejetsVO);
			map.put("listDelegationVO", listDelegationVO);
			
			} catch (Exception e) {
			logger.error("problème technique",e);
		}	
		return map;
	}

	@Override
	public List doConvertValueObjectToItems(Object arg0)
			throws ValidationUnitaireException, ValidationException {
		
		return null;
	}

}
