package eai.devass.sinistreat.appli.usecase.metier.reglement;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.modele.metier.reglement.Reglement;
import eai.devass.sinistreat.appli.modele.metier.sinistre.Sinistre;
import eai.devass.sinistreat.appli.usecase.BaseUC;
import eai.devass.sinistreat.appli.utils.IConstantes;

@SuppressWarnings("all")
public class CreateReglementUC extends BaseUC {

	private static final String TYPQTC_PRESTATAIRE = "4";
	
	protected void executerUC(IValueObject entite, HashMap params) throws Exception {
		
		try {
			List listRgl = getItems(Reglement.class);
			List listResult = new ArrayList();
			Date dateAccident = null;
			
			if (listRgl != null && !listRgl.isEmpty()) {
				for (Iterator iterator = listRgl.iterator(); iterator.hasNext();) {
					Reglement reglement = (Reglement) iterator.next();
					if(reglement.getRefSinistre()!=null && reglement.getRefSinistre().getReserveOrdinaire()==null){
					reglement.getRefSinistre().setReserveOrdinaire(Double.valueOf(0));
					}
					// Type de la quittance obligatoire
					if(reglement.getRefTypeQuittance() == null) {
						throw new Exception("Le type de la quittance est obligatoire !!");
					}
					
					Reglement rgl = reglementManager.creerReglement(reglement);
					listResult.add(rgl);
					reglementManager.addHistoriqueEtat(rgl, IConstantes.MOTIF_CREATION_PREQUITTANCE);
				}

				Sinistre sinResult = sinistreManager
						.creerMovementReglementCreation((Reglement) listResult
								.get(0));
				
				
				Reglement reg=(Reglement) listResult.get(0);
				
				if(reg.getRefSinistre()!=null){
				sinResult.setReserveGrave(reg.getRefSinistre().getReserveGrave());
				sinResult.setReserveOrdinaire(reg.getRefSinistre().getReserveOrdinaire());
				sinResult.setReserveProthese(reg.getRefSinistre().getReserveProthese());
				}
				
				
				for (Iterator iterator1 = listResult.iterator(); iterator1
						.hasNext();) {
					Reglement reglement = (Reglement) iterator1.next();
					reglement.setRefSinistre(sinResult);
					addResultItem(reglement);
				}

			}

		} catch (Exception e) {
			throw new FonctionnelleException(e.getMessage());
		}
	}
	
	
	private void validerReglementPrestataire(Reglement reglement) throws Exception {
		
		if(reglement.getRefSinistre() == null) {
			throw new Exception("Le sinistre du réglement est oblifatoire !!");
		}
		if(reglement.getRefSinistre().getRefEvenement() == null 
				|| reglement.getRefSinistre().getRefEvenement().getDateAccident() == null) {
			throw new Exception("La date accident du sinistre est oblifatoire !!");
		}
		if(reglement.getDateNote() == null) {
			throw new Exception("La date note du régelment auxiliaire est oblifatoire !!");
		}
		
		if (reglement.getDateNote().before(
				reglement.getRefSinistre().getRefEvenement().getDateAccident())) {
			throw new Exception(
					"La date note du réglement auxiliaire doit être supérieure ou égale à la date d'accident");
		}
	}

	public boolean isTransactionnal() {
		return true;
	}

}
