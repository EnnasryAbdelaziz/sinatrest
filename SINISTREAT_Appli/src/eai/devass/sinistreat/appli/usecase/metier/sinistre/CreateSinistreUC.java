package eai.devass.sinistreat.appli.usecase.metier.sinistre;

import java.util.HashMap;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.services.businessInterface.impl.WarnMessageItem;
import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.modele.metier.sinistre.Evenement;
import eai.devass.sinistreat.appli.modele.metier.sinistre.Sinistre;
import eai.devass.sinistreat.appli.modele.metier.sinistre.Victime;
import eai.devass.sinistreat.appli.usecase.BaseUC;
import eai.devass.sinistreat.appli.utils.IConstantes;

public class CreateSinistreUC extends BaseUC {

	public CreateSinistreUC() {
		super();
	}

	protected void executerUC(IValueObject entite, HashMap params)
			throws FonctionnelleException {
		//Mouvement mvt = null;
		Sinistre sinistre = (Sinistre) this.getItem(Sinistre.class);
		Sinistre sinResult = null;
		
		
		try {
			if (sinistre != null) {
				//correction Sonar :
				Evenement even = sinistre.getRefEvenement();
				Victime vic = (Victime) sinistre.getRefVictime();
				
				//Evenement even = sinistre.getRefEvenement();
				// correction ano 15/05/2015 
				if (sinistre.getRefEvenement() != null) {
					even.setNumeroEvenement(sinistreManager.validateEvenement(
							sinistre, even));
				}
				reservesInit(sinistre);
				sinResult = (Sinistre) sinistreManager.creerSinistre(sinistre);
				if (even != null && even.getDateAccident() != null) {
					sinistre.setDateCalculReserve(even
							.getDateAccident());
				}
				if (sinistre.getRefVictime() != null) {

					if (sinistre.getRefVictime().getDeces()) {
						sinistreManager.creerMouvementAY(sinResult,
								IConstantes.MOTIF_CREATION_SINISTRE);
					} else {
						
						sinResult = sinistreManager
								.calculSalAnnuelEtUtilAvantCreerMouvement(sinResult);
						sinResult = sinistreManager
								.reCalculResGravAvantCreerMouvement(sinResult);
						sinResult = sinistreManager
								.reCalculResProthesAvantCreerMouvement(sinResult);

						sinistreManager.creerMouvement(sinResult, null,
								IConstantes.MOTIF_CREATION_SINISTRE);
					}
				}
		
			
			if (vic.getDateNaissance() != null && vic.getAge(even.getDateAccident()) > IConstantes.AGE_MAX) {
				addMessageItem(new WarnMessageItem(EXP_AGE_MAXIMUM_65));
			}
			
			// suite correction ano 15/05/2015 
			sinResult.setRefEvenement(even);
			}
		} catch (Exception e) {
			throw new FonctionnelleException(e.getMessage());
		}

		addResultItem(sinResult);

	}

	public boolean isTransactionnal() {
		return true;
	}

	/**
	 * initier les reserves pour les traitements GRC
	 * 
	 * @param sinistre
	 */

	private void reservesInit(Sinistre sinistre) {
		if (sinistre.getReserveGrave() != null)
			sinistre.setReserveGraveInit(sinistre.getReserveGrave());
		if (sinistre.getReserveOrdinaire() != null)
			sinistre.setReserveOrdinaireInit(sinistre.getReserveOrdinaire());
		if (sinistre.getReserveProthese() != null)
			sinistre.setReserveProtheseInit(sinistre.getReserveProthese());
	}

}
