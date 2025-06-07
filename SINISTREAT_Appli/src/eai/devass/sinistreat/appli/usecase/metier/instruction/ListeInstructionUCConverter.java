package eai.devass.sinistreat.appli.usecase.metier.instruction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ma.co.omnidata.framework.services.businessInterface.ValidationException;
import ma.co.omnidata.framework.services.businessInterface.impl.ValueObjectConverterAbst;
import ma.co.omnidata.framework.services.validation.ValidationUnitaireException;

import org.apache.log4j.Logger;

import eai.devass.sinistreat.appli.modele.metier.instruction.Instruction;
import eai.devass.sinistreat.appli.modele.metier.sinistre.Sinistre;
import eai.devass.sinistreat.appli.utils.ConverterTools;
import eai.devass.sinistreat.appli.utils.exception.IMessageException;
import eai.devass.sinistreat.appli.valueobjects.metier.instruction.InstructionVO;
import eai.devass.sinistreat.appli.valueobjects.metier.sinistre.SinistreVO;

public class ListeInstructionUCConverter extends ValueObjectConverterAbst
		implements IMessageException {

	ConverterTools converterTools = ConverterTools.getInstance();
	private Logger logger = Logger.getLogger("loggerSINAT");

	public Object doConvertItemsToValueObject(List listeItems) {
		Map map = new HashMap();
		if (listeItems == null || listeItems.isEmpty()) {
			return null;
		}

		try {
			List<Instruction> listInstruction = (List<Instruction>) listeItems.get(0);
			if (listInstruction != null && !listInstruction.isEmpty()) {
				Object instruction = listInstruction.get(0);

				if (instruction instanceof Instruction) {
					((Instruction) instruction)
							.setPropertiesToConvert(new String[] { "id",
									"vref", "codeIntermediaire",
									"codePrestataire", "nom", "prenom"
									,
									"ville", "adresse", "editer",
									"userEditeur", "userCreation",
									"dateCreation", 
									"instructionPieceAt",
									"instructionRejetAt",
									"destinataire.code",
									"destinataire.libelle",
									"typeLettreInstruction.code",
									"typeLettreInstruction.libelle",
									"typeInstruction.code",
									"typeInstruction.libelle",
									"categorieInstruction.code",
									"categorieInstruction.libelle",
									"procedureJudiciaire.id","commentaire","emailIntermediaire","supplementPieces"
									});
				

					List<InstructionVO> listInstructionVO = (List<InstructionVO>) ConverterTools
							.getInstance().convertToListObjectVO(
									listInstruction);

					if (listInstructionVO != null
							&& !listInstructionVO.isEmpty()) {
						map.put("list"
								+ listInstructionVO.get(0).getClass()
										.getSimpleName(), listInstructionVO);
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
		InstructionVO instVO = (InstructionVO) vo;

		try {

			Instruction instruction = (Instruction) converterTools
					.convertToObjectBO(instVO);
			listOut.add(instruction);
		} catch (Exception e) {
			Logger.getLogger("loggerSinat").fatal(
					"Erreur lors de la conversion !", e);
			throw new ValidationUnitaireException(e.getMessage());
		}

		return listOut;
	}
}
