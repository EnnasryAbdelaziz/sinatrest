package eai.devass.sinistreat.appli.usecase.metier.sinistre;

import java.util.HashMap;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.services.businessInterface.impl.WarnMessageItem;

import org.hibernate.exception.ConstraintViolationException;

import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.modele.metier.sinistre.Evenement;
import eai.devass.sinistreat.appli.modele.metier.sinistre.Sinistre;
import eai.devass.sinistreat.appli.modele.metier.sinistre.Victime;
import eai.devass.sinistreat.appli.usecase.BaseUC;
import eai.devass.sinistreat.appli.utils.IConstantes;

public class CreateSinistreRepriseUC extends BaseUC {

	protected void executerUC(IValueObject entite, HashMap params)
			throws Exception {

		Sinistre sinistre = (Sinistre) this.getItem(Sinistre.class);
		Sinistre sinResult = null;
		Evenement even = sinistre.getRefEvenement();
		Victime vic = (Victime) sinistre.getRefVictime();
		
		try {
			sinResult = (Sinistre) sinistreManager
					.creerSinistreReprise(sinistre);
			if (sinistre.getRefVictime() != null) {
				if (sinistre.getRefVictime().getDeces()) {
					sinistreManager.creerMouvementAY(sinResult,IConstantes.MOTIF_CREATION_SINISTRE);
				} else {
					sinResult=sinistreManager.calculSalAnnuelEtUtilAvantCreerMouvement(sinResult);
					sinResult=sinistreManager.reCalculResGravProthesAvantCreerMouvement(sinResult,false);
					sinistreManager.creerMouvement(sinResult, null, IConstantes.MOTIF_CREATION_SINISTRE);
				}
			}
			if (vic.getDateNaissance() != null && vic.getAge(even.getDateAccident()) > IConstantes.AGE_MAX) {
				addMessageItem(new WarnMessageItem(EXP_AGE_MAXIMUM_65));
			}

		} catch (ConstraintViolationException e) {
			throw new FonctionnelleException(e);
		} catch (Exception e) {
			throw new FonctionnelleException(e.getMessage());
		}

		addResultItem(sinResult);
	}

	public boolean isTransactionnal() {
		return true;
	}
}
