package eai.devass.sinistreat.appli.usecase.metier.contentieux;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.hibernate.exception.ConstraintViolationException;

import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.modele.metier.contentieux.AudienceJugement;
import eai.devass.sinistreat.appli.modele.metier.contentieux.ProcedureJudiciaire;
import eai.devass.sinistreat.appli.modele.metier.sinistre.Sinistre;
import eai.devass.sinistreat.appli.usecase.BaseUC;
import eai.devass.sinistreat.appli.utils.entites.IDate;
import eai.devass.sinistreat.appli.valueobjects.metier.contentieux.AudienceJugementVO;
import ma.co.omnidata.framework.services.businessInterface.IValueObject;

public class CalculerRenteJugementReUC extends BaseUC {
	private final SimpleDateFormat dateFormat = new SimpleDateFormat(
			IDate.FORMAT_SIMPLE);
	protected void executerUC(IValueObject entite, HashMap params)
	throws Exception {
			
		
		AudienceJugementVO audienceVO = (AudienceJugementVO)(entite);
		AudienceJugement audienceResult = null;
		AudienceJugement audience = (AudienceJugement) this.getItem(AudienceJugement.class);
		ProcedureJudiciaire refProcedureJudiciaire = null;
		
		
		try {
			audienceResult = (AudienceJugement) contentieuxManager.calculerRenteAudience(audienceVO,audience);
			//refProcedureJudiciaire = sinistreManager.creerMovementAudianceCreation(audience);
		} catch (ConstraintViolationException e) {
			throw new FonctionnelleException(e);
		} catch (Exception e) {
			throw new FonctionnelleException(e.getMessage());
		}
		
		//audienceResult.setRefProcedureJudiciaire(refProcedureJudiciaire);
		addResultItem(audienceResult);
	
	}
	
	

	public boolean isTransactionnal() {
		return true;
	}
}

