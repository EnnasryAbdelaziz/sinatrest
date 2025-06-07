package eai.devass.gsr.appli.reglegestion.validation;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import ma.co.omnidata.framework.services.entites.EntiteException;
import eai.devass.commun.appli.exeptions.ExceptionMetier;
import eai.devass.commun.appli.modele.EntiteBO;
import eai.devass.gsr.appli.modele.metier.dossierRente.Rentier;
import eai.devass.gsr.appli.modele.metier.mouvements.MvtSuppression;
import eai.devass.gsr.appli.modele.metier.reglement.QuittanceGsr;
import eai.devass.gsr.appli.modele.parametrage.EtatQtc;
import eai.devass.gsr.appli.modele.parametrage.EtatRentier;
import eai.devass.gsr.appli.modele.parametrage.SituationQuittanceGsr;
import eai.devass.gsr.appli.prm.EtatQuittance;
import eai.devass.gsr.appli.prm.EtatRente;
import eai.devass.gsr.appli.prm.MotifEtat;
import eai.devass.gsr.appli.reglegestion.BaseRG;
import eai.devass.gsr.appli.utile.IMessageException;
import eai.devass.sinistreat.appli.utils.Message.IMessage;

public class MvtSuppressionRG extends BaseRG {

	private MvtSuppression suppression = null;
	private Rentier rentier = null;
	private Rentier rentierBD = null;

	
	public void regleGestion001ValiderMvtSuppression(EntiteBO entiteBO,
			Map params) throws ExceptionMetier, EntiteException {
		
		MvtSuppression mouvement = (MvtSuppression) entiteBO;
		if (mouvement.getMntIndu() != null
				&& mouvement.getMntIndu() > 5000) {
			((Map) params.get("outMapMessage")).put(IMessage.INFO,IMessage.MSG_GSR_MNT_INDU );
		}

			// Situatrion mouvement
			motifSituationEtat = mouvement.getRefSituationMouvement().get(0)
					.getRefMotifSituationEtat();
	
			//Mettre le rentier à l'état supprimé
			rentier = mouvement.getRefRentier();

			if (rentier == null || rentier.getId() <= 0) {
				throw new ExceptionMetier(IMessageException.EXP_RENTIER_INTROUVALE);
			}

			rentierBD = rentierManager.getRentierByID(rentier.getId());
			
			EtatRentier etatRentier = new EtatRentier();
			etatRentier.setId(EtatRente.Supprimee_Par_Jugement.getCode());
			rentierBD.setRefEtatRentier(etatRentier);
			rentierBD.setDateEtat(mouvement.getDatEtat());
			//Mettre le motif
			rentierBD.setMotifEtat(mouvement.getMotif());
			
			if (MotifEtat.Decision_directoire.getCode().equals(
				motifSituationEtat.getCode()))
			{
				//Annuler les quittances
				//vérifier les info de qtc et de reglement
				
				List<QuittanceGsr> listQtc = null;
				try {
					listQtc = transverseManager.listQuittancesReglees(rentier,mouvement.getEcheanceEffective(), mouvement.getDatSuppression());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					logger.error("problème technique",e);
				}
		
				if(listQtc!=null){
				for(QuittanceGsr quittanceGsr : listQtc) {
					SituationQuittanceGsr situationQuittance = new SituationQuittanceGsr();
					EtatQtc refEtatQtc=new EtatQtc();
					situationQuittance.setDateEtat(new GregorianCalendar().getTime());
					refEtatQtc.setId(EtatQuittance.Annulee.getId());
					refEtatQtc.setDateCreation(new GregorianCalendar());
					situationQuittance.setRefEtatQtc(refEtatQtc);
					situationQuittance.setOperateur(mouvement.getOperateur());
					situationQuittance.setRefQuittanceGsr(quittanceGsr);
				
					quittanceGsr.setDatEtat(new GregorianCalendar());
					quittanceGsr.setRefEtatQtc(refEtatQtc);

					getSession().save(situationQuittance);
				}
				}
			}
			
			//Générer la quittance d'équilibre
			suppression = (MvtSuppression) entiteBO;
			Double renteAnnuelle = rentierBD.getMontantRenteTrimestrielle()*4;
			Date dateCalcul = suppression.getDatSuppression().getTime();
			Double CoefficientAge = 0D;
			try {
				CoefficientAge = transverseManager.getCoefficientAge(rentierBD,dateCalcul);
				
			} catch(ExceptionMetier e) {
				return;
			}
			
			Double montantCapital = renteAnnuelle * CoefficientAge;
			if(!(montantCapital.compareTo(0D)==0)){
				QuittanceGsr quittanceGsr = new QuittanceGsr();
				quittanceGsr.setMontant(- montantCapital);
				suppression.addQuittanceEquilibre(quittanceGsr);
			}	
	}
}
