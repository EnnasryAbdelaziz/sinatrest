package eai.devass.gsr.appli.reglegestion.validation;

import java.util.Date;
import java.util.List;
import java.util.Map;

import ma.co.omnidata.framework.services.dao.PersistenceException;
import ma.co.omnidata.framework.services.entites.EntiteException;

import org.hibernate.HibernateException;

import eai.devass.commun.appli.exeptions.ExceptionMetier;
import eai.devass.commun.appli.modele.EntiteBO;
import eai.devass.gsr.appli.modele.metier.dossierRente.Rentier;
import eai.devass.gsr.appli.modele.metier.mouvements.MvtMajCapital;
import eai.devass.gsr.appli.modele.metier.reglement.QuittanceGsr;
import eai.devass.gsr.appli.modele.parametrage.NatureQtcGsr;
import eai.devass.gsr.appli.prm.EtatQuittance;
import eai.devass.gsr.appli.prm.NatureQuittance;
import eai.devass.gsr.appli.reglegestion.BaseRG;
import eai.devass.gsr.appli.utile.IMessageException;
import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.modele.metier.sinistre.Sinistre;
import eai.devass.sinistreat.appli.utils.IConstantes;
import eai.devass.sinistreat.appli.utils.Message.IMessage;

public class MvtMajCapitalRG extends BaseRG {

	private MvtMajCapital majCapital = null;
	private Rentier rentier = null;
	private Rentier rentierBD = null;
	private Sinistre sinistreBD = null;
	protected List<QuittanceGsr> listQtc = null;
	Boolean isQuittancesVide = false;

	public void regleGestion001ValiderMvtMajCapital(EntiteBO entiteBO,
			Map params) throws ExceptionMetier, EntiteException,
			HibernateException, PersistenceException, FonctionnelleException {
		MvtMajCapital mouvement = (MvtMajCapital) entiteBO;

		if (mouvement.getMntTropPercu() != null
				&& mouvement.getMntTropPercu() < 0) {
			((Map) params.get("outMapMessage")).put(IMessage.INFO,
					IMessage.MSG_GSR_TROP_PERCU);
		}
		NatureQuittance natureQtcGsr = null;
		// Emission quittance
		if (mouvement.getRefTypeMajCapital().getId() == 1) {
			mouvement.setEmissionQuittance(false);
		} else {
			if (mouvement.getRefsQuittance() != null) {

				listQtc = mouvement.getRefsQuittance();
				if (listQtc != null && listQtc.size() > 0) {
					for (QuittanceGsr quittanceGsr : listQtc) {

						QuittanceGsr quittanceGsrDB = null;

						try {
							quittanceGsrDB = quittanceManager.getQuittanceByID(quittanceGsr.getId());

						} catch (Exception e) {
							throw new ExceptionMetier(
									"Impossible de trouver la quittance");

						}
						if (quittanceGsrDB.getRefEtatQtc().getId() == EtatQuittance.Cree.getId())
						{
							mouvement.setEmissionQuittance(true);
							isQuittancesVide = true;
						}
						else
						{
							mouvement.setEmissionQuittance(false);
							isQuittancesVide = false;
						}
						
					}
				}
			} else {
				mouvement.setEmissionQuittance(false);
			}
		}

		// Générer la quittance d'équilibre
		/**
		 * Cas augmentation du capital : o Montant quittance d’équilibre=
		 * (nouvelle rente * coefficient d’âge à la date de départ de la
		 * nouvelle rente) – (ancienne rente * coefficient d’âge à la date de
		 * départ de la nouvelle rente). Sens AT vers GSR Cas diminution du
		 * capital : o Montant quittance d’équilibre= (nouvelle rente *
		 * coefficient d’âge à la date de départ de la nouvelle rente) –
		 * (ancienne rente * coefficient d’âge à la date de départ de la
		 * nouvelle rente). Sens GSR vers AT
		 */

		rentier = mouvement.getRefRentier();

		if (rentier == null || rentier.getId() <= 0) {
			throw new ExceptionMetier(IMessageException.EXP_RENTIER_INTROUVALE);
		}

		rentierBD = rentierManager.getRentierByID(rentier.getId());

		Long idDossierRente = transverseManager.doGetIdDossier(rentier.getId());
		Long idSinistre = transverseManager.doGetIdSinistre(idDossierRente);
		sinistreBD = sinistreManager.getSinistreById(idSinistre);

		Double reserveGrave = sinistreBD.getReserveGrave();
		majCapital = (MvtMajCapital) entiteBO;
		if (mouvement.getRefTypeMajCapital().getId() != 3) {

			Double ancienneRente = rentierBD.getMontantRenteTrimestrielle();
			Double nouvelleRente = majCapital.getNouvMntRente();
			Date dateCalcul = majCapital.getNouvDateDepartRente().getTime();
			
			Double CoefficientAge = 0D;
			try {
				CoefficientAge = transverseManager.getCoefficientAge(
						rentierBD, dateCalcul);
				
			} catch(ExceptionMetier e) { }
			
			Double montantQuittance = (nouvelleRente
					- ancienneRente ) * 4 * CoefficientAge;
			/*
			 * Si la réserve grave<montant de la quittance d'équilibre,
			 * afficher le message "Réserve grave insuffisante"
			 * 
			 * Sinon Générer la quittance d'équilibre. La réserve grave sera
			 * déduite du montant viré vers la GSR
			 */
			if (reserveGrave < montantQuittance) {

				throw new EntiteException(IMessage.MSG_RG_INSUFFISANTE);

			} else {
				if (!(montantQuittance == 0)) {
					QuittanceGsr quittanceGsr = new QuittanceGsr();
					quittanceGsr.setMontant(montantQuittance);
					if (montantQuittance > 0) {
						natureQtcGsr = NatureQuittance.Augmentation_Capital_constitutif;
											
					} else {
						natureQtcGsr = NatureQuittance.Diminution_Capital_constitutif;
						
					}
					quittanceGsr.setRefNatureQuittance(new NatureQtcGsr(natureQtcGsr.getId()));
					majCapital.addQuittanceEquilibre(quittanceGsr);
					
					// maj reserve grave sinistre
					if (mouvement.getRefTypeMajCapital().getId() == 2){
						Double nouvRG = reserveGrave - montantQuittance;
						sinistreBD.setReserveGrave(nouvRG);
						sinistreBD.setUserModificateur(mouvement.getOperateur());
						//WMOS: 12/11/2015 add date modification 
						sinistreBD.setDateModification(new Date());
						sinistreManager.gsrCreerMouvement(sinistreBD,
								"Modification",
								IConstantes.MOTIF_GSR_CHANGEMENT_RESERVEGRAVE);
					}

				}

				// maj reserve grave sinistre
//				if (!(mouvement.getMntDiff() == 0)) {
////					Double qtc = mouvement.getMntDiff();
//					Double nouvRG = reserveGrave - montantQuittance;
//					sinistreBD.setReserveGrave(nouvRG);
//					//(new SinistreManager()).modifyEntite(sinistreBD);
//					sinistreManager.gsrCreerMouvement(sinistreBD,
//							"Modification",
//							IConstantes.MOTIF_GSR_CHANGEMENT_RESERVEGRAVE);
//				}

			}
		} else {
			Double montantQuittance = (mouvement.getNouvCapitalConstitutif() + mouvement
					.getArreragesRente());
			if (reserveGrave >= montantQuittance) {
				if (!(montantQuittance == 0)) {
					QuittanceGsr quittanceGsr = new QuittanceGsr();
					quittanceGsr.setMontant(montantQuittance);
					if (montantQuittance > 0) {
						natureQtcGsr = NatureQuittance.Augmentation_Capital_constitutif;
											
					} else {
						natureQtcGsr = NatureQuittance.Diminution_Capital_constitutif;
						
					}
					quittanceGsr.setRefNatureQuittance(new NatureQtcGsr(natureQtcGsr.getId()));
					majCapital.addQuittanceEquilibre(quittanceGsr);
					Double nouvRG = reserveGrave - montantQuittance;
					rentierBD.setReserveMathematique(mouvement
							.getNouvCapitalConstitutif());
					sinistreBD.setReserveGrave(nouvRG);
					//sinistreManager.modifyEntite(sinistreBD);
					sinistreManager.gsrCreerMouvement(sinistreBD,
							"Modification",
							IConstantes.MOTIF_GSR_CHANGEMENT_RESERVEGRAVE);
				}

			} else {
				throw new EntiteException(IMessage.MSG_RG_INSUFFISANTE);
			}
		}
		
		// Mise à jour, informations rentier
		if (mouvement.getRefTypeMajCapital().getId() != 3) {
			rentierBD.setIppTauxRente(mouvement.getNouvTaux());
			rentierBD.setSalaireUtile(mouvement.getNouvSalaire());
			rentierBD.setDateNaissance(mouvement.getNouvDatNaissance());
			rentierBD.setDateDepartRente(mouvement.getNouvDateDepartRente());
			rentierBD.setMontantRenteTrimestrielle(mouvement.getNouvMntRente());
		}
		rentierBD.setCapitalConstitutif(mouvement.getNouvCapitalConstitutif());
		rentierBD.setReserveMathematique(mouvement.getNouvCapitalConstitutif());
		// Mettre le motif
		rentierBD.setMotifEtat(mouvement.getMotif());

	}

	public void regleGestion002GenererQuittance(EntiteBO entiteBO, Map params)
			throws ExceptionMetier {
		MvtMajCapital mouvement = (MvtMajCapital) entiteBO;
		List<QuittanceGsr> listQuittanceGsr = mouvement.getRefsQuittance();
		if (mouvement.getRefTypeMajCapital().getId() == 2 && mouvement.getRefsQuittance() != null && isQuittancesVide) {
			
			if (commonGsrUtils.isEmpty(listQuittanceGsr)) {
				throw new ExceptionMetier(
						"Veulliez créer une quitatnce de réglement !!");
			}
			QuittanceGsr quittanceGsr = listQuittanceGsr.get(0);
			NatureQtcGsr natureQtc = new NatureQtcGsr();
			natureQtc.setId(NatureQuittance.Complement_rente.getId());
			quittanceGsr.setRefNatureQuittance(natureQtc);
			quittanceGsr.setCodePrestation(NatureQuittance.Complement_rente
					.getRubrique());
		}
		if (mouvement.getRefTypeMajCapital().getId() == 3
				&& mouvement.getArreragesRente() > 0) {
			if (commonGsrUtils.isEmpty(listQuittanceGsr)) {
				throw new ExceptionMetier(
						"Veulliez créer une quitatnce de réglement !!");
			}
			QuittanceGsr quittanceGsr = listQuittanceGsr.get(0);
			NatureQtcGsr natureQtc = new NatureQtcGsr();
			natureQtc.setId(NatureQuittance.Rente_periodique.getId());
			quittanceGsr.setRefNatureQuittance(natureQtc);
			quittanceGsr.setCodePrestation(NatureQuittance.Rente_periodique
					.getRubrique());
			quittanceGsr.setMontant(mouvement.getArreragesRente());
		}
	}
}
