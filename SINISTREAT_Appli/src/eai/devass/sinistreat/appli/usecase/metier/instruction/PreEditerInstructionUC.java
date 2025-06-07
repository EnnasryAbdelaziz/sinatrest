package eai.devass.sinistreat.appli.usecase.metier.instruction;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.modele.metier.instruction.Instruction;
import eai.devass.sinistreat.appli.modele.metier.reglement.LettreReglement;
import eai.devass.sinistreat.appli.modele.metier.reglement.Reglement;
import eai.devass.sinistreat.appli.modele.metier.sinistre.Sinistre;
import eai.devass.sinistreat.appli.usecase.BaseUC;
import eai.devass.sinistreat.appli.utils.IConstantes;

@SuppressWarnings("all")
public class PreEditerInstructionUC extends BaseUC {

	protected void executerUC(IValueObject entite, HashMap params)
			throws Exception {
		  try
	        {
	            Instruction instruction = (Instruction)getItem(Instruction.class);
	            instructionManager.editerInsruction(instruction);
	        }
	        catch(Exception e)
	        {
	        	throw new FonctionnelleException(e.getMessage(), new String[0]);
	        }
	}

	public boolean isTransactionnal() {
		return true;
	}

}
