package eai.devass.sinistreat.appli.usecase.metier.instruction;

import java.util.HashMap;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;

import org.hibernate.exception.ConstraintViolationException;

import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.modele.metier.instruction.Instruction;
import eai.devass.sinistreat.appli.usecase.BaseUC;

public class InitCreationInstructionUC extends BaseUC {

	@Override
	protected void executerUC(IValueObject entite, HashMap params)
			throws Exception {
		// inisialisation de la page de creation des instructions support
		try {

			List listTypeLettreInstruction = (List) instructionManager
					.getListTypeLettreInstruction();
			List listDestinataire = (List) instructionManager
					.getListDestinataire();
			List listPieces = (List) instructionManager.getAllPieceAt();
			Instruction inst = new Instruction();
			List listTypeInstruction = (List) instructionManager.getListInstruction();
			List listRejets = (List) instructionManager.getAllRejetsAt();
			List listDelegation = (List) instructionManager.getListDelegation();

			this.addResultItem(listTypeLettreInstruction);
			this.addResultItem(listDestinataire);
			this.addResultItem(listPieces);
			this.addResultItem(inst);
			this.addResultItem(listTypeInstruction);
			this.addResultItem(listRejets);
			this.addResultItem(listDelegation);


		} catch (ConstraintViolationException e) {
			throw new FonctionnelleException(e);

		} catch (Exception e) {
			throw new FonctionnelleException(e.getMessage());
		}

	}

}
