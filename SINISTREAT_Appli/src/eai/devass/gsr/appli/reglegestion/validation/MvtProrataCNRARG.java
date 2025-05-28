package eai.devass.gsr.appli.reglegestion.validation;

import java.util.Date;
import java.util.List;
import java.util.Map;

import eai.devass.commun.appli.exeptions.ExceptionMetier;
import eai.devass.commun.appli.modele.EntiteBO;
import eai.devass.commun.appli.rg.ASkipRG;
import eai.devass.gsr.appli.modele.metier.dossierRente.Rentier;
import eai.devass.gsr.appli.modele.metier.mouvements.MvtProrataCNRA;
import eai.devass.gsr.appli.modele.metier.reglement.QuittanceGsr;
import eai.devass.gsr.appli.modele.parametrage.EtatRentier;
import eai.devass.gsr.appli.modele.parametrage.NatureQtcGsr;
import eai.devass.gsr.appli.modele.parametrage.SituationEtatRentier;
import eai.devass.gsr.appli.prm.EtatRente;
import eai.devass.gsr.appli.prm.NatureQuittance;
import eai.devass.gsr.appli.reglegestion.BaseRG;
import eai.devass.gsr.appli.utile.IMessageException;
import eai.devass.sinistreat.appli.modele.metier.sinistre.Sinistre;
import eai.devass.sinistreat.appli.utils.IConstantes;

public class MvtProrataCNRARG extends BaseRG {

	private Double montantQtcEquilibre = 0D;

	public void regleGestion001SetEtatRentier(EntiteBO entiteBO, Map params)
			throws ExceptionMetier {
		MvtProrataCNRA mouvement = (MvtProrataCNRA) entiteBO;

		EtatRentier etatRentier = new EtatRentier(
				EtatRente.Versee_CNRA.getCode());
		mouvement.getRefRentier().setRefEtatRentier(etatRentier);
		// Situation rentier
		SituationEtatRentier situationEtatRentier = new SituationEtatRentier();
		situationEtatRentier.setRefEtatRentier(etatRentier);
		situationEtatRentier.setRefRentier(mouvement.getRefRentier());
		situationEtatRentier.setDateEtat(new Date());
		situationEtatRentier.setOperateur(entiteBO.getOperateur());
		getSession().save(situationEtatRentier);
	}

	public void regleGestion003GenererQuittance(EntiteBO entiteBO, Map params)
			throws ExceptionMetier {
		MvtProrataCNRA mouvement = (MvtProrataCNRA) entiteBO;
		List<QuittanceGsr> listQuittanceGsr = mouvement.getRefsQuittance();
		if (commonGsrUtils.isEmpty(listQuittanceGsr)) {
			throw new ExceptionMetier(
					"Veulliez créer une quitatnce de réglement !!");
		}
		QuittanceGsr quittanceGsr = listQuittanceGsr.get(0);
		NatureQtcGsr natureQtc = new NatureQtcGsr();
		natureQtc
				.setId(NatureQuittance.Versement_prorata_capital_constitutif_a_la_CNRA
						.getId());
		quittanceGsr.setRefNatureQuittance(natureQtc);
		quittanceGsr
				.setCodePrestation(NatureQuittance.Versement_prorata_capital_constitutif_a_la_CNRA
						.getRubrique());
		mouvement.setEmissionQuittance(true);
	}

	@ASkipRG(property = "sommeMntQtcEquilibre", isEmty = "true")
	public void regleGestion004GenererQuittanceEquilibre(EntiteBO entiteBO,
			Map params) throws Exception {
		MvtProrataCNRA mouvement = (MvtProrataCNRA) entiteBO;
		Rentier rentier = mouvement.getRefRentier();
		Rentier rentierBD = rentierManager.getRentierByID(rentier.getId());
		/**
		 * Montant de la quittance d'équilibre
		 */
		montantQtcEquilibre = mouvement.getSommeMntQtcEquilibre();
		Double reserveGrave = rentierBD.getRefDossierRente().getRefSinistre()
				.getReserveGrave();
		// correction sonar : Call to equals() comparing different types
		if (reserveGrave == null) {
			throw new ExceptionMetier(IMessageException.EXP_RESERVE_GRAVE_NULL);
		}

		if (reserveGrave >= montantQtcEquilibre) {
			QuittanceGsr quittanceGsr = new QuittanceGsr();
			quittanceGsr.setMontant(montantQtcEquilibre);
			mouvement.addQuittanceEquilibre(quittanceGsr);

			reserveGrave = reserveGrave - montantQtcEquilibre;
			Sinistre sinistrte = rentierBD.getRefDossierRente()
					.getRefSinistre();
			sinistrte.setReserveGrave(reserveGrave);
			sinistrte.setUserModificateur(mouvement.getOperateur());
			//WMOS: 12/11/2015 add date modification 
			sinistrte.setDateModification(new Date());
			// (new SinistreManager()).modifyEntite(sinistrte);
			sinistreManager.gsrCreerMouvement(sinistrte, "Modification",
					IConstantes.MOTIF_GSR_CHANGEMENT_RESERVEGRAVE);
		} else {
			throw new ExceptionMetier(
					IMessageException.EXP_RESERVE_GRAVE_INSUFFISSANTE);
		}

	}

}
