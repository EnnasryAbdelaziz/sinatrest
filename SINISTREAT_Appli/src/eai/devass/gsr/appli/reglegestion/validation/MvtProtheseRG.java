/**
 * 
 */
package eai.devass.gsr.appli.reglegestion.validation;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import ma.co.omnidata.framework.services.workflow.dbaccess.taskAdmin.TypeConverter;
import eai.devass.commun.appli.exeptions.ExceptionMetier;
import eai.devass.commun.appli.modele.EntiteBO;
import eai.devass.commun.appli.modele.IHistorisable;
import eai.devass.commun.appli.rg.ContextRegleGestion;
import eai.devass.gsr.appli.modele.metier.dossierRente.Prothese;
import eai.devass.gsr.appli.modele.metier.dossierRente.Rentier;
import eai.devass.gsr.appli.modele.metier.mouvements.MvtProthese;
import eai.devass.gsr.appli.modele.metier.reglement.QuittanceGsr;
import eai.devass.gsr.appli.prm.EtatProthese;
import eai.devass.gsr.appli.prm.TypeMvtProthese;
import eai.devass.gsr.appli.reglegestion.BaseRG;
import eai.devass.gsr.appli.utile.IMessageException;
import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.modele.metier.sinistre.Sinistre;
import eai.devass.sinistreat.appli.utils.IConstantes;

/**
 * @author elfaismo
 * 
 */
public class MvtProtheseRG extends BaseRG {

	/**
	 * Phase de la validation implementation de la RG7/FGSR-10.15
	 * 
	 * @param entiteBO
	 * @param params
	 * @throws ExceptionMetier
	 */

	public void regleGestion000VerifierProtheses(EntiteBO entiteBO, Map params)
			throws ExceptionMetier {
		MvtProthese mvtProthese = (MvtProthese) entiteBO;
		Rentier rentier = mvtProthese.getRefRentier();
		Rentier rentierBD = rentierManager.getRentierByID(rentier.getId());
		if (rentierBD == null || rentierBD.getId() <= 0) {
			throw new ExceptionMetier(IMessageException.EXP_RENTIER_INTROUVALE);

		}
		// Liste des protheses crées au moment de la création de la rente
		List<Prothese> listProtheseCreationRente = new ArrayList<Prothese>();
		List<Prothese> listProtheseAvalider = new ArrayList<Prothese>();
		List<Prothese> listAncienneProtheses = rentierBD.getRefsProtheses();
		if (listAncienneProtheses != null && listAncienneProtheses.size() > 0) {
			for (Prothese ancienneProthese : listAncienneProtheses) {
				if (ancienneProthese.getRefEtatProthese().getId() == EtatProthese.Validee
						.getId()
						&& ancienneProthese.getRefMvtProthese() == null) {
					listProtheseCreationRente.add(ancienneProthese);
				}
				if (ancienneProthese.getRefEtatProthese().getId() == EtatProthese.Cree.getId()
						|| ancienneProthese.getRefEtatProthese().getId() == EtatProthese.Modifiee.getId()) {
					listProtheseAvalider.add(ancienneProthese);
				}
			}
		}
		//correction sonar
		getRefTypeMvtProthese (mvtProthese,listProtheseCreationRente,rentierBD,listProtheseAvalider);
		//Fin correction sonar
		if (mvtProthese.getRefTypeMvtProthese().getId() == TypeMvtProthese.Frais_Appareillage
				.getId()) {
			Double reserveProtheseAT = 0D;
			Sinistre sinistre = rentierBD.getRefDossierRente().getRefSinistre();
			reserveProtheseAT = sinistre.getReserveProthese();
			if (reserveProtheseAT == 0) {
				// mettre les protheses crées ou modifies au statut validé
				validerProtheses(listProtheseAvalider, mvtProthese);
			} else {
				throw new ExceptionMetier(
						"Validation impossible : Vérifier la réserve prothèse au niveau du sinistre AT. Différente de 0");
			}

		}
	}

	//correction sonar
	public void getRefTypeMvtProthese(MvtProthese mvtProthese,
			List<Prothese> listProtheseCreationRente, Rentier rentierBD,
			List<Prothese> listProtheseAvalider) throws ExceptionMetier {
		if (mvtProthese.getRefTypeMvtProthese().getId() == TypeMvtProthese.Creation_Prothese
				.getId()) {
			if (listProtheseCreationRente == null
					|| listProtheseCreationRente.size() == 0) {
				Double reserveProtheseAT = 0D;
				Double reserveProtheseGSR = 0D;
				// Suite QC 218.
				reserveProtheseGSR = mvtProthese.getRefsProthese().get(0)
						.getReserveProthese();
				Sinistre sinistre = rentierBD.getRefDossierRente()
						.getRefSinistre();
				reserveProtheseAT = sinistre.getReserveProthese();
				if (reserveProtheseAT.compareTo(reserveProtheseGSR) >= 0) {
					sinistre.setReserveProthese(0D);
					sinistre.setUserModificateur(mvtProthese.getOperateur());
					//WMOS: 12/11/2015 add date modification 
					sinistre.setDateModification(new Date());
					try {
						sinistreManager
								.gsrCreerMouvement(
										sinistre,
										"Modification",
										IConstantes.MOTIF_GSR_CHANGEMENT_RESERVEPROTHESE);
					} catch (FonctionnelleException e) {
						logger.error("regleGestion000VerifierProtheses", e);
						throw new ExceptionMetier(
								"regleGestion000VerifierProtheses", e);
					}
					// mettre les protheses crées ou modifies au statut validé
					validerProtheses(listProtheseAvalider, mvtProthese);
				} else {
					throw new ExceptionMetier(
							"Validation impossible : Réserves prothèses AT et GSR différentes");
				}
			} else {
				// mettre les protheses crées ou modifies au statut validé
				validerProtheses(listProtheseAvalider, mvtProthese);
			}
		}

		if (mvtProthese.getRefTypeMvtProthese().getId() == TypeMvtProthese.Renouvellement
				.getId()) {
			Double reserveProtheseAT = 0D;
			Sinistre sinistre = rentierBD.getRefDossierRente().getRefSinistre();
			reserveProtheseAT = sinistre.getReserveProthese();
			if (reserveProtheseAT == 0) {
				// mettre les protheses crées ou modifies au statut validé
				validerProtheses(listProtheseAvalider, mvtProthese);
			} else {
				throw new ExceptionMetier(
						"Validation impossible : Vérifier la réserve prothèse au niveau du sinistre AT. Différente de 0");
			}
		}
	}
	//fin correction
	
	
	private void validerProtheses(List<Prothese> listProtheses,
			MvtProthese mvtProthese) throws ExceptionMetier {

		if (listProtheses != null && listProtheses.size() > 0) {
			for (Prothese prothese : listProtheses) {

				eai.devass.gsr.appli.modele.parametrage.EtatProthese etatProthese = new eai.devass.gsr.appli.modele.parametrage.EtatProthese();
				etatProthese.setId(EtatProthese.Validee.getId());
				prothese.setRefEtatProthese(etatProthese);

				String idProthese = String.valueOf(mvtProthese.getId())
						+ String.valueOf((prothese.getIdProthese()!=null)?prothese.getIdProthese():"1");

				prothese.setIdProthese(TypeConverter.getInstance()
						.stringToLongObject(idProthese));
				prothese.setDateValidation(new GregorianCalendar());

				prothese.setContextRegleGestion(ContextRegleGestion.VALIDATION
						.getContext());
				prothese.setOperateur(mvtProthese.getOperateur());

				try {

					transverseManager.historiser((IHistorisable) prothese, 1);
				} catch (Exception e) {
					logger.warn("L'historisation de la prothese identifiant ["
							+ prothese.getId() + "] n'a pas pu aboutir");
					throw new ExceptionMetier("L'historisation de la prothese identifiant ["
							+ prothese.getId() + "] n'a pas pu aboutir",e);
				}
			}
		}

	}
	
	public void regleGestion001EmettreQuittances(EntiteBO entiteBO, Map params)
	throws ExceptionMetier {
		MvtProthese mvtProthese = (MvtProthese) entiteBO;
		
		List<QuittanceGsr> listQuittance= mvtProthese.getRefsQuittance();
		
		if(listQuittance!=null && listQuittance.size()>0){
			
		for(QuittanceGsr quittanceGsr:listQuittance){
			
			if(quittanceGsr.getMontant()>0){
				
				mvtProthese.setEmissionQuittance(true);
				
				break;
			}
			

		}
			
		}
		
		
		
		}


}
