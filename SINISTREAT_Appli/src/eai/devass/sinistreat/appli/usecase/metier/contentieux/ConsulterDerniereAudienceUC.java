/**
 * 
 */
package eai.devass.sinistreat.appli.usecase.metier.contentieux;

import java.util.Calendar;
import java.util.HashMap;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;

import org.hibernate.exception.ConstraintViolationException;

import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.modele.metier.contentieux.AudienceJugement;
import eai.devass.sinistreat.appli.modele.metier.contentieux.ProcedureJudiciaire;
import eai.devass.sinistreat.appli.usecase.BaseUC;

/**
 * @author elfaismo
 * 
 */
public class ConsulterDerniereAudienceUC extends BaseUC {

	// Recherche par num. de sinistre
	@SuppressWarnings("unchecked")
	protected void executerUC(IValueObject entite, HashMap params)
			throws Exception {
		ProcedureJudiciaire procedure = null;

		ProcedureJudiciaire procedureEntree = (ProcedureJudiciaire) this
				.getItem(ProcedureJudiciaire.class);
		try {

			procedure = (ProcedureJudiciaire) contentieuxManager
					.getDerniereAudienceProcedure(procedureEntree.getRefRecours().getRefSinistre(),
							procedureEntree.getNumeroDossierTribunal());

		} catch (ConstraintViolationException e) {
			throw new FonctionnelleException(e);
		} catch (Exception e) {
			throw new FonctionnelleException(e.getMessage());
		}

		
		String numDossierTribunale = null;
		
		String organismeJudiciaire = null;
		
		Calendar dateDecisionJudiciaire  = Calendar.getInstance();
		
		numDossierTribunale = (procedure.getNumeroDossierTribunal()!=null) ? procedure.getNumeroDossierTribunal(): "";
		organismeJudiciaire = (procedure.getRefJuridiction().getLibelle()!=null) ? procedure.getRefJuridiction().getLibelle() : "";
		
		AudienceJugement derniereAudience = procedure.getDerniereAudience();
		
		if(derniereAudience!=null){
		if(derniereAudience.getDateDecision()!=null)
		{
			dateDecisionJudiciaire.setTime(procedure.getDerniereAudience().getDateDecision());
		}else{
			dateDecisionJudiciaire= null;
		}
		
		}else{
			
			dateDecisionJudiciaire= null;
		}
		
		addResultItem(numDossierTribunale);
		addResultItem(organismeJudiciaire);
		addResultItem(dateDecisionJudiciaire);


	}

	public boolean isTransactionnal() {
		return true;
	}

}
