package eai.devass.gsr.appli.reglegestion.batch;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import eai.devass.commun.appli.exeptions.ExceptionMetier;
import eai.devass.commun.appli.modele.EntiteBO;
import eai.devass.commun.appli.rg.ContextRegleGestion;
import eai.devass.commun.appli.rg.RegleGestionUtils;
import eai.devass.gsr.appli.modele.metier.mouvements.Mouvement;
import eai.devass.gsr.appli.reglegestion.BaseRG;

public class MouvementRG extends BaseRG {

	public void regleGestion001RunRegleGestion(EntiteBO entiteBO, Map params)
			throws ExceptionMetier {

		Mouvement mouvement = (Mouvement) entiteBO;
		try {
			
			// Run context creation
			mouvement.setContextRegleGestion(ContextRegleGestion.CREATION.getContext());
			RegleGestionUtils.getInstance().runRegleGestionObjet(params);
			
			// Run context validation
			mouvement.setContextRegleGestion(ContextRegleGestion.VALIDATION.getContext());
			RegleGestionUtils.getInstance().runRegleGestionObjet(params);
			

		} catch (InvocationTargetException e) {
			throw new ExceptionMetier(e.getCause().getMessage());
			
		} catch (Exception e) {
			throw new ExceptionMetier(e.getMessage());
		}

	}

	
	
	
	

}
