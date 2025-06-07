package eai.devass.sinistreat.appli.usecase.metier.sinistre;

import java.util.HashMap;

import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.modele.metier.sinistre.Evenement;
import eai.devass.sinistreat.appli.modele.metier.sinistre.Sinistre;
import eai.devass.sinistreat.appli.modele.metier.sinistre.Victime;
import eai.devass.sinistreat.appli.usecase.BaseUC;
import eai.devass.sinistreat.appli.utils.IConstantes;
import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.services.businessInterface.impl.WarnMessageItem;
	
public class ModifierSinistreUC extends BaseUC {

	protected void executerUC(IValueObject entite, HashMap params)
			throws Exception {

		Sinistre sinistre = (Sinistre) this.getItem(Sinistre.class);
		Sinistre sindb = sinistreManager.getSinistreByNum(sinistre.getNumeroSinistre());
		Sinistre sinResult = null;
		String UseCase = "Modification";
		Evenement even = sinistre.getRefEvenement();
		Victime vic = (Victime) sinistre.getRefVictime();
		Victime vicDB = (Victime) sindb.getRefVictime();
		Victime ancienVictime = new Victime();
		ancienVictime.setNumeroCIN(vicDB.getNumeroCIN());
		ancienVictime.setNom(vicDB.getNom());
		ancienVictime.setPrenom(vicDB.getPrenom());
		ancienVictime.setSalaireJournalier(vicDB.getSalaireJournalier());
		if(ancienVictime.getPrenom() == null) {
			ancienVictime.setPrenom("");
		}
		try {
			sinResult = (Sinistre) sinistreManager.modifierSinistre(sinistre);
			
			if (even != null && even.getDateAccident() != null) {
				sinistre.setDateCalculReserve(even
						.getDateAccident());
			}
			if(vic != null && vicDB != null) {
				if((vic.getNumeroCIN() != null && !vic.getNumeroCIN().equals(ancienVictime.getNumeroCIN())) || 
						(vic.getNumeroCIN() == null && ancienVictime.getNumeroCIN() != null)) {
					sinistreManager.creerMouvement(sinResult, UseCase,IConstantes.MOTIF_MODIFICATION_CIN_VICTIME);
				}
				if(!vic.getNom().equals(ancienVictime.getNom()) ||  !ancienVictime.getPrenom().equals(vic.getPrenom())) {
					sinistreManager.creerMouvement(sinResult, UseCase,IConstantes.MOTIF_MODIFICATION_NOMPRENOM_VICTIME);
				}
				
				if(!vic.getSalaireJournalier().equals(ancienVictime.getSalaireJournalier())) {
					sinistreManager.creerMouvement(sinResult, UseCase,IConstantes.MOTIF_MODIFICATION_SALAIREJOURNALIER_VICTIME);
				}

			}
			if (sinResult.getRefVictime() != null) {
				if (sinResult.getRefVictime().getDeces() ) {
					if(sinResult.getRefVictime()!=null && sinResult.getRefVictime().getTraitementSpecialDeletAyDroit())
						sinistreManager.creerMouvement(sinResult, UseCase,IConstantes.MOTIF_MODIFICATION_SINISTRE);
					else
					sinistreManager.creerMouvementAY(sinResult,IConstantes.MOTIF_MODIFICATION_SINISTRE);
				} else {
					sinistreManager.creerMouvement(sinResult, UseCase,IConstantes.MOTIF_MODIFICATION_SINISTRE);
				}
			}
			if (vic.getDateNaissance() != null && vic.getAge(even.getDateAccident()) > IConstantes.AGE_MAX) {
				addMessageItem(new WarnMessageItem(EXP_AGE_MAXIMUM_65));
			}
			if (sinistre.getRefEvenement() != null) {
				even.setNumeroEvenement(sinistreManager.validateEvenement(
						sinistre, even));
				// dao.createObject(even);
				sinResult.setRefEvenement(even);
			}
		} catch (Exception e) {
			throw new FonctionnelleException(e);
		} 
		addResultItem(sinResult);

	}
	
	
	

//	public boolean isTransactionnal() {
//		return true;
//	}

}
