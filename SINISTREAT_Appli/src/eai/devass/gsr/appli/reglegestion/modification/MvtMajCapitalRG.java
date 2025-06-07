package eai.devass.gsr.appli.reglegestion.modification;

import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import eai.devass.commun.appli.exeptions.ExceptionMetier;
import eai.devass.commun.appli.modele.EntiteBO;
import eai.devass.gsr.appli.modele.metier.mouvements.MvtMajCapital;
import eai.devass.gsr.appli.modele.metier.reglement.QuittanceGsr;
import eai.devass.gsr.appli.modele.parametrage.EtatQtc;
import eai.devass.gsr.appli.modele.parametrage.NatureQtcGsr;
import eai.devass.gsr.appli.modele.parametrage.SituationQuittanceGsr;
import eai.devass.gsr.appli.prm.EtatQuittance;
import eai.devass.gsr.appli.prm.NatureQuittance;

public class MvtMajCapitalRG extends MouvementRG {

	MvtMajCapital mouvement = null;
	//Boolean isQuittancesVide = false;
	//private List<QuittanceGsr> listQuittanceGsr = null;
	//private IAppelService appelService = new AppelServiceManager();

	public void regleGestion001GenererQuittanceReglement(EntiteBO entiteBO,
			Map params) throws ExceptionMetier {

		mouvement = (MvtMajCapital) entiteBO;
		// rentier = mouvement.getRefRentier();

		if (mouvement.getGenererQuittance()) {
			// vérifier les info de qtc et de reglement
			listQtc = mouvement.getRefsQuittance();

			// Boolean isQuittancesVide = false;
//			if (commonGsrUtils.isEmpty(listQtc)) {
//
//				isQuittancesVide = true;
//			}

			mouvement.setGenererQuittance(true);

			for (QuittanceGsr quittanceGsr : listQtc) {

				NatureQtcGsr natureQtc = new NatureQtcGsr(
						NatureQuittance.Complement_rente.getId());
				quittanceGsr.setRefNatureQuittance(natureQtc);
				quittanceGsr.setCodePrestation(NatureQuittance.Complement_rente
						.getRubrique());

			}
		}
		

	}
	
	public void regleGestion803AnnulationQuittances(EntiteBO entiteBO,
			Map params) throws ExceptionMetier {

		if (!mouvement.getGenererQuittance()) {

			listQtc = mouvement.getRefsQuittance();
			mouvement.setGenererQuittance(false);

			if (listQtc != null && listQtc.size() > 0) {
				for (QuittanceGsr quittanceGsr : listQtc) {
					QuittanceGsr quittanceGsrDB = null;

					try {
						quittanceGsrDB = quittanceManager.getQuittanceByID(quittanceGsr.getId());

					} catch (Exception e) {
						throw new ExceptionMetier(
								"Impossible de trouver la quittance");

					}
					SituationQuittanceGsr situationQuittance = new SituationQuittanceGsr();
					EtatQtc refEtatQtc = new EtatQtc();

					situationQuittance.setDateEtat(new GregorianCalendar()
							.getTime());
					refEtatQtc.setId(EtatQuittance.Annulee.getId());
					refEtatQtc.setDateCreation(new GregorianCalendar());
					situationQuittance.setRefEtatQtc(refEtatQtc);
					situationQuittance.setOperateur(mouvement.getOperateur());
					situationQuittance.setRefQuittanceGsr(quittanceGsrDB);

					quittanceGsrDB.setDatEtat(new GregorianCalendar());
					quittanceGsrDB.setRefEtatQtc(refEtatQtc);

					getSession().save(situationQuittance);

				}
				getSession().flush();
			}

		}
	}
//	/**
//	 * Appel au service d'annulation
//	 * @param entiteBO
//	 * @param params
//	 * @throws ExceptionMetier
//	 */
//	public void regleGestion804AnnulationQuittances(EntiteBO entiteBO, Map params) throws ExceptionMetier {
//
//
//		if (!mouvement.getGenererQuittance()) {
//			// Dans le cas d'un rejet, il faut mettre à jour la quittance via un
//			// mvt
//			// d'annulation
//			listQtc = mouvement.getRefsQuittance();
//			mouvement.setGenererQuittance(false);
//			if (listQtc != null && listQtc.size() > 0) {
//				for (QuittanceGsr qtc : listQtc) {
//					DateFormat dateFormat = new SimpleDateFormat(
//							"dd/MM/yyyy HH:mm:ss");
//					String date = dateFormat.format(new Date());
//					MouvementQuittanceVO mouvementQuittanceVO = new MouvementQuittanceVO();
//					mouvementQuittanceVO.setNumQuittance(qtc
//							.getNumeroQuittance());
//					mouvementQuittanceVO.setMotifEtat("Annulation");
//					mouvementQuittanceVO.setDatEtat(date);
//
//					// Appel de service
//					appelService.setConvert(false);
//					appelService
//							.appelService(ServicesExternes.ANNULER_QUITTANCE,
//									mouvementQuittanceVO,
//									IConstantes.PROFIL_ANNULATION);
//
//				}
//			}
//		}
//	}

	/**
	 * Annuler la liste des quittances en entrée.
	 * 
	 * @param quittancesAnnules
	 * @throws ExceptionMetier
	 */
	private void annulerQuittances(List<QuittanceGsr> quittancesAnnules)
			throws ExceptionMetier {

		if (!commonGsrUtils.isEmpty(quittancesAnnules)
				&& quittancesAnnules.size() > 0) {
			for (QuittanceGsr quittanceGsr : quittancesAnnules) {
				QuittanceGsr quittanceGsrDB = null;

				try {
					quittanceGsrDB = quittanceManager.getQuittanceByID(quittanceGsr.getId());

				} catch (Exception e) {
					throw new ExceptionMetier("Impossible de trouver la quittance");

				}
				SituationQuittanceGsr situationQuittance = new SituationQuittanceGsr();
				EtatQtc refEtatQtc = new EtatQtc();

				situationQuittance.setDateEtat(new GregorianCalendar().getTime());
				refEtatQtc.setId(EtatQuittance.Annulee.getId());
				refEtatQtc.setDateCreation(new GregorianCalendar());
				situationQuittance.setRefEtatQtc(refEtatQtc);
				situationQuittance.setOperateur(mouvement.getOperateur());
				situationQuittance.setRefQuittanceGsr(quittanceGsrDB);

				quittanceGsrDB.setDatEtat(new GregorianCalendar());
				quittanceGsrDB.setRefEtatQtc(refEtatQtc);

				getSession().save(situationQuittance);

			}

		}
	}

}
