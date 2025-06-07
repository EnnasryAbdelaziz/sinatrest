package eai.devass.sinistreat.appli.usecase.metier.fichemedicale;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.hibernate.exception.ConstraintViolationException;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import eai.devass.sinistreat.appli.businessrule.SinistreRG;
import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.modele.metier.conciliation.Conciliation;
import eai.devass.sinistreat.appli.modele.metier.conciliation.Offre;
import eai.devass.sinistreat.appli.modele.metier.conciliation.SinResultatOffre;
import eai.devass.sinistreat.appli.modele.metier.fichemedicale.CertificatMedical;
import eai.devass.sinistreat.appli.modele.metier.sinistre.Sinistre;
import eai.devass.sinistreat.appli.usecase.BaseUC;
import eai.devass.sinistreat.appli.utils.IConstantes;
import eai.devass.sinistreat.appli.valueobjects.metier.conciliation.ConciliationVO;

public class ModifierReserveCertificatUC extends BaseUC {
	@SuppressWarnings("unchecked")
	@Override
	protected void executerUC(IValueObject entite, HashMap params) throws Exception {
		CertificatMedical certificatMedical = (CertificatMedical) this.getItem(CertificatMedical.class);
		Sinistre sin = null;
		try {

			sin = (Sinistre) sinistreManager.getSinistreById(certificatMedical.getRefSinistre().getId());
			if (sin == null) {
				throw new FonctionnelleException("EXP_SINISTRE_INEXISTANT");
			}
			SinistreRG sinitreRG = new SinistreRG(sin);
			boolean rachat = sinitreRG.verifierSiRachatExiste();

			Boolean justice = sinistreManager.verifierAudiance(sin.getNumeroSinistre());

			sin.setUserModificateur(certificatMedical.getRefSinistre().getUserModificateur());
			sin.setDateModification(new Date());

			Double ippvalide = certificatMedicalManager.getIppValide(certificatMedical.getRefSinistre().getId());

			// voir si le sinistre contient une conciliation en cours et ayant une offre
			// avec resultat accepte
			Boolean offreTrouve = false;
			List<Conciliation> listConci = conciliationManager.getConciliationByIdSinistre(sin);
			for (Conciliation conciliation : listConci) {

				if (!offreTrouve) {
					ConciliationVO c = new ConciliationVO();
					c.setId(conciliation.getId());
					List<Offre> listOff = conciliationManager.getOffresByIdConciliation(c);
					for (Offre offre : listOff) {
						if (offre.getRefResultatOffre() != null) {
							SinResultatOffre resultat = offre.getRefResultatOffre();
							if (resultat.getRefEtatResultat() != null && resultat.getRefEtatResultat().getId() != null
									&& resultat.getRefEtatResultat().getId().equals("1")) {
								offreTrouve = true;
								break;
							}
						}
					}
				}
			}
			// Fin verification 12/2021 ENNASRY

			// List<CertificatMedical> liste = certificatMedicalManager
			// .getValideCertificatBySin(certificatMedical
			// .getRefSinistre().getId());
			// CertificatMedical certifvalidipp = null;
			// if (liste != null && !liste.isEmpty()) {
			// certifvalidipp = liste.get(0);
			// int i = 1;
			// while (i < liste.size()
			// && certifvalidipp.getRefType() != null
			// && certifvalidipp.getRefType().getCode() != null
			// && !certifvalidipp
			// .getRefType()
			// .getCode()
			// .equals(IConstantes.TYPE_CERTIFICAT_EXPERTISEJUDICIAIRE)) {
			// String typeCertifvalidipp = certifvalidipp.getRefType()
			// .getCode();
			// String typecertificatActuel = liste.get(i).getRefType()
			// .getCode();
			// if (typecertificatActuel
			// .equals(IConstantes.TYPE_CERTIFICAT_EXPERTISEJUDICIAIRE)) {
			// // Cas expertise judicaire
			// certifvalidipp = liste.get(i);
			// } else if (typecertificatActuel
			// .equals(IConstantes.TYPE_CERTIFICAT_GUERISON)) {
			// if (typeCertifvalidipp
			// .equals(IConstantes.TYPE_CERTIFICAT_CONTREVISITE)) {
			// // cas Taux IPP contre visite > Taux guerison on
			// // garde celui de la guerison
			// if (liste.get(i).getIPP()
			// .compareTo(certifvalidipp.getIPP()) < 0) {
			// certifvalidipp = liste.get(i);
			// }
			// } else {
			// certifvalidipp = liste.get(i);
			// }
			// } else if (typecertificatActuel
			// .equals(IConstantes.TYPE_CERTIFICAT_CONTREVISITE)
			// && (typeCertifvalidipp
			// .equals(IConstantes.TYPE_CERTIFICAT_GUERISON))) {
			// // cas Taux IPP contre visite < Taux guerison on
			// // garde celui de la contre visite
			// if (liste.get(i).getIPP()
			// .compareTo(certifvalidipp.getIPP()) < 0) {
			// certifvalidipp = liste.get(i);
			// }
			// }
			// i++;
			// }
			// }
			// if (certifvalidipp != null) {
			// Double ippvalide = certifvalidipp.getIPP();
			// if (ippvalide != null) {
			// if (certifvalidipp.getRefType().getCode()
			// .equals(IConstantes.TYPE_CERTIFICAT_CONTREVISITE)) {
			// // cas Taux IPP contre visite > Estimer, le SI doit
			// // prendre celui de la contre visite
			// if (ippvalide.compareTo(sin.getIpp()) >= 0) {
			// sin.setIpp(ippvalide);
			// }
			// } else {
			// sin.setIpp(ippvalide);
			// }
			if (ippvalide != null) {
				// Si un offre trouve accepte pour un certificat de guerison ne pas setter ipp
				// 12/2021 ENNASRY
				if (!(offreTrouve && certificatMedical.getRefType().getId()
						.equals(new Long(IConstantes.TYPE_CERTIFICAT_GUERISON)))) {
					sin.setIpp(ippvalide);

					sin.setIppEstime(false);
					sin.setDateOuvertureGrave(new Date());
					if (sin.getEvenement() != null && ippvalide.compareTo(Double.valueOf(0)) != 0) {
						if (sin.getNumeroGrave() == null || "".equals(sin.getNumeroGrave())) {
							sin.setNumeroGrave(sinistreManager.getNumeroGrave(sin.getRefEvenement().getDateAccident()));
						}
					}

					if ((justice != null && !justice) && (sin.getNumeroRente() == null)) {
						if (certificatMedical.getRefType() != null && certificatMedical.getRefType().getId() == 2) {
							sin.setDateCalculReserve(certificatMedical.getDateGuerison());
						}

						if (sin.getRefVictime() != null && sin.getRefVictime().getDeces()) {
							sinistreManager.creerMouvementAY(sin, IConstantes.MOTIF_CHANGEMENT_RESERVEGRAVE_CETIFICAT);
						} else {// ne pas mettre à jour la reserve sinistre pour un certif de guerison et le
								// sinistre a une offre accepte 12/2021 ENNSRY
							sin = sinistreManager.calculSalAnnuelEtUtilAvantCreerMouvement(sin);
							sin = sinistreManager.reCalculResGravProthesAvantCreerMouvement(sin, rachat);
							sinistreManager.creerMouvement(sin, null,
									IConstantes.MOTIF_CHANGEMENT_RESERVEGRAVE_CETIFICAT);
						}
					}
				} else {//seulement historiser le mouvment dans le cas de certificat autre que guerison
					sinistreManager.creerMouvement(sin, null,
							IConstantes.MOTIF_CHANGEMENT_RESERVEGRAVE_CETIFICAT);
				}
			}

		} catch (ConstraintViolationException e) {
			throw new FonctionnelleException(e);
		} catch (Exception e) {
			throw new FonctionnelleException(e.getMessage());
		}
		addResultItem(sin);
	}

	public boolean isTransactionnal() {
		return true;
	}

}
