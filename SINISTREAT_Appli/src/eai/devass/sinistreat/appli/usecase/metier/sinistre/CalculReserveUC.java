package eai.devass.sinistreat.appli.usecase.metier.sinistre;

import java.util.HashMap;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.services.businessInterface.impl.WarnMessageItem;

import org.hibernate.exception.ConstraintViolationException;

import eai.devass.sinistreat.appli.businessrule.SinistreRG;
import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.modele.metier.sinistre.Evenement;
import eai.devass.sinistreat.appli.modele.metier.sinistre.Sinistre;
import eai.devass.sinistreat.appli.modele.metier.sinistre.Victime;
import eai.devass.sinistreat.appli.modele.parametrage.Zone;
import eai.devass.sinistreat.appli.usecase.BaseUC;
import eai.devass.sinistreat.appli.utils.IConstantes;

public class CalculReserveUC extends BaseUC {

	protected void executerUC(IValueObject entite, HashMap params)
			throws Exception {

		Sinistre sinistre = (Sinistre) this.getItem(Sinistre.class);
		SinistreRG sinitreRG = new SinistreRG(sinistre);

		// correction defect 49
		sinitreRG.setSinistreDataBaseByNum();
		boolean verif = sinitreRG.verifierNouvelleDateSurvPostAnt22012015();
		boolean rachat= sinitreRG.verifierSiRachatExiste();
		
		sinitreRG.verifierMiseAZeroReserves();
		Evenement even = sinistre.getRefEvenement();
		Sinistre sinResult = null;
		Victime vic = (Victime) sinistre.getRefVictime();
		try {
			if (even.getDateAccident() != null) {
				sinistre.setDateCalculReserve(even.getDateAccident());
			}
			if (sinistre.getRefVictime() != null) {
				if (sinistre.getRefVictime().getDeces()) {
					
						sinResult = (Sinistre) sinistreManager
							.calculReserveAY(sinistre);
				} else {

					// Evo Prod 95 : lors dmodification d'un dossier migré que
					// sont taux ipp est estimatif, il ne faut pas appliqué la
					// régle du taux par zone.

					if (sinistre.getNumeroSinistre() == null
							|| (sinistre.getMigFlag() != null && sinistre
									.getMigFlag().equals("1"))) {
						if (sinistre.getIppEstime()
								&& sinistre.getIpp() != null
								&& sinistre.getIpp().compareTo(new Double(0)) != 0) {
							if (sinistre.getRefEvenement() != null
									&& sinistre.getRefEvenement().getRefZone() != null) {
								List listZone = parametrageManager
										.getListZone(sinistre.getRefEvenement()
												.getRefZone());
								if (listZone != null && !listZone.isEmpty()) {
									Zone zone = (Zone) listZone.get(0);
									if (sinistre.getIpp().compareTo(
											zone.getIppMin()) < 0) {
										throw new FonctionnelleException(
												"La valeur de l'IPP ne peut être inférieure au taux minimum par zone: "
														+ zone.getIppMin());
									}
								}
							}
						}
					}

					sinResult = (Sinistre) sinistreManager.calculReserve(
							sinistre, elementReservesGraveModifie(sinistre),
							elementReservesProtheseModifie(sinistre),rachat);

				}
			}
			if (vic.getDateNaissance() != null
					&& vic.getAge(even.getDateAccident()) > IConstantes.AGE_MAX) {
				addMessageItem(new WarnMessageItem(EXP_AGE_MAXIMUM_65));
			}
		} catch (ConstraintViolationException e) {
			throw new FonctionnelleException(e);
		} catch (Exception e) {
			throw new FonctionnelleException(e.getMessage());
		}

		if(verif){
            sinResult
                    .setInfoMessage("Suite au changement de la date de survenance Antérieur/postérieur du 22/01/2015, le SI supprimera l’ensemble des ayants droit du dossier avec annulation de la résérve grave Après Confirmation Finale!!!");
		}
		addResultItem(sinResult);

	}

	public boolean isTransactionnal() {
		return true;
	}

	public boolean elementReservesGraveModifie(Sinistre sinistre)
			throws FonctionnelleException {
		Sinistre sinDb = sinistreManager.getSinistreByNum(sinistre
				.getNumeroSinistre());
		if (sinDb == null) {
			return true;
		}
		if (sinistre.getRefVictime() != null && sinDb.getRefVictime() != null
				&& sinistre.getRefVictime().getSalaireAnnuel() != null
				&& sinDb.getRefVictime().getSalaireAnnuel() != null
				&& sinistre.getRefVictime().getDateNaissance() != null
				&& sinDb.getRefVictime().getDateNaissance() != null
				&& sinDb.getRefEvenement().getDateAccident() != null
				&& sinDb.getRefEvenement().getDateAccident() != null) {
			if ((sinistre.getIpp().compareTo(sinDb.getIpp()) != 0
					|| sinistre
							.getRefVictime()
							.getSalaireAnnuel()
							.compareTo(sinDb.getRefVictime().getSalaireAnnuel()) != 0
					|| sinistre
							.getRefVictime()
							.getDateNaissance()
							.compareTo(sinDb.getRefVictime().getDateNaissance()) != 0 || sinistre
					.getRefEvenement().getDateAccident()
					.compareTo(sinDb.getRefEvenement().getDateAccident()) != 0)) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;

		}
	}

	public boolean elementReservesProtheseModifie(Sinistre sinistre)
			throws FonctionnelleException {

		if (!sinistre.getProthese()) {
			return false;
		}
		Sinistre sinDb = sinistreManager.getSinistreByNum(sinistre
				.getNumeroSinistre());
		if (sinDb == null) {
			return true;
		}

		if (sinistre.getRefVictime() != null && sinDb.getRefVictime() != null
				&& sinistre.getRefVictime().getSalaireAnnuel() != null
				&& sinDb.getRefVictime().getSalaireAnnuel() != null
				&& sinistre.getRefVictime().getDateNaissance() != null
				&& sinDb.getRefVictime().getDateNaissance() != null
				&& sinDb.getRefEvenement().getDateAccident() != null
				&& sinDb.getRefEvenement().getDateAccident() != null) {
            if (sinistre.getIpp().compareTo(sinDb.getIpp()) != 0
					|| sinistre
							.getRefVictime()
							.getSalaireAnnuel()
							.compareTo(sinDb.getRefVictime().getSalaireAnnuel()) != 0
					|| sinistre
							.getRefVictime()
							.getDateNaissance()
							.compareTo(sinDb.getRefVictime().getDateNaissance()) != 0
					|| sinistre
							.getRefEvenement()
							.getDateAccident()
							.compareTo(
                                    sinDb.getRefEvenement().getDateAccident()) != 0
                    || sinistre.getPrixProthese().compareTo(
                            sinDb.getPrixProthese()) != 0) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;

		}

	}

}
