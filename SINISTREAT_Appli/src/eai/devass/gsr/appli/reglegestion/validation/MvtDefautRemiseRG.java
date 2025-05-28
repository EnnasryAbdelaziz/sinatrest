package eai.devass.gsr.appli.reglegestion.validation;

import java.util.List;
import java.util.Map;

import ma.co.omnidata.framework.services.core.ServicesFactory;
import eai.devass.commun.appli.exeptions.ExceptionMetier;
import eai.devass.commun.appli.modele.EntiteBO;
import eai.devass.gsr.appli.manager.metier.reglement.QuittanceGsrManager;
import eai.devass.gsr.appli.modele.metier.dossierRente.Rentier;
import eai.devass.gsr.appli.modele.metier.mouvements.MvtDefautRemise;
import eai.devass.gsr.appli.modele.metier.reglement.QuittanceGsr;
import eai.devass.gsr.appli.modele.parametrage.MotifManuelSuspensionRente;
import eai.devass.gsr.appli.modele.parametrage.SituationEtatRentier;
import eai.devass.gsr.appli.modele.parametrage.SituationQuittanceGsr;
import eai.devass.gsr.appli.prm.EtatRente;
import eai.devass.gsr.appli.prm.MotifEtat;
import eai.devass.gsr.appli.reglegestion.BaseRG;
import eai.devass.gsr.appli.utile.CommonGsrUtils;

public class MvtDefautRemiseRG extends BaseRG {

	private final static String ACTION_POSITIONNEMENT = "2";
	private final static String ACTION_REGLEMENT = "1";
	private final static String ACTION_SUSPENSSION = "3";
	private QuittanceGsr quittanceGsr = null;
	private QuittanceGsrManager quittanceGsrManager = (QuittanceGsrManager) ServicesFactory
			.getService(QuittanceGsrManager.class);
	
	public void regleGestion001Positionnement(EntiteBO entiteBO, Map params) throws ExceptionMetier {
		
		MvtDefautRemise mouvement = (MvtDefautRemise) entiteBO;
		if(!ACTION_POSITIONNEMENT.equals(mouvement.getAction())) {
			return;
		}
		
	}
	
	public void regleGestion002Reglement(EntiteBO entiteBO, Map params) throws ExceptionMetier {
		
		MvtDefautRemise mouvement = (MvtDefautRemise) entiteBO;
		if(!ACTION_REGLEMENT.equals(mouvement.getAction())) {
			return;
		}
		
		// Mettre à jour les quittance annuler par defaut de remise (BATCH) par le nouveau mode de reglement
		List<QuittanceGsr> listQtcMvt = mouvement.getRefsQuittance();
		if(commonGsrUtils.isEmpty(listQtcMvt)) {
			throw new ExceptionMetier("Impossible de récuperer la liste des quittance du mouvement");
		}
		
		// Recuperer la liste des quittances en question
		quittanceGsr = listQtcMvt.get(0);		
		List<QuittanceGsr> listQtcAnnuler = null;
		try {
			listQtcAnnuler = quittanceGsrManager
					.getListQuittancesDefautRemise(mouvement.getRefRentier());
		
		} catch(Exception e) {
			throw new ExceptionMetier("Impossible de récuperer la liste des quittance annulées " +
					"pour la mise à jour du nouveau mode de réglement : " + e.getMessage());
		}
		
		if(commonGsrUtils.isEmpty(listQtcAnnuler)) {
			throw new ExceptionMetier("La liste des quittances annulées à mettre à jour est vide !!!");
		}
		
		// Mettre à jour les quittances pour le nouveau mode de reglement
		listQtcMvt.remove(quittanceGsr);
		for(QuittanceGsr curQuittanceGsr : listQtcAnnuler) {
			curQuittanceGsr.setRefPrerglt(quittanceGsr.getRefPrerglt());
			curQuittanceGsr.setRefModeReglement(quittanceGsr.getRefModeReglement());
			curQuittanceGsr.setRefTypeReglement(quittanceGsr.getRefTypeReglement());
			curQuittanceGsr.setRefMouvement(mouvement);
			listQtcMvt.add(curQuittanceGsr);
		}
		mouvement.setRefsQuittance(listQtcMvt);
		mouvement.setEmissionQuittance(true);
		
	}
	
	public void regleGestion003DeleteTmpQuittance(EntiteBO entiteBO, Map params) throws ExceptionMetier {
		
		MvtDefautRemise mouvement = (MvtDefautRemise) entiteBO;
		if(!ACTION_REGLEMENT.equals(mouvement.getAction())) {
			return;
		}
		
		List<SituationQuittanceGsr> listSituationQuittanceGsrs = quittanceGsr
				.getRefSituationQuittanceGsr();
		for(SituationQuittanceGsr curSituationQuittanceGsr : listSituationQuittanceGsrs) {
			getSession().delete(curSituationQuittanceGsr);
		}
		
		getSession().delete(quittanceGsr);		
		
	}
	
	public void regleGestion004UpdateDefautRemiseBatchTable(EntiteBO entiteBO, Map params) throws ExceptionMetier {
		
		MvtDefautRemise mouvement = (MvtDefautRemise) entiteBO;
		if(!ACTION_REGLEMENT.equals(mouvement.getAction())) {
			return;
		}
		
		// Recuperer la ref de reglement des quittance, pour mettre à jour la table : BATCH_QUITTANCE_DEFAUTREMISE
		List<QuittanceGsr> listQtcMvt = mouvement.getRefsQuittance();
		QuittanceGsr firstQuittanceGsr = listQtcMvt.get(0);
		String refReglement = firstQuittanceGsr.getRefReglement();
		if(CommonGsrUtils.isEmpty(refReglement)) {
			throw new ExceptionMetier("Impossible de récupérer la réference de reglement de la quittance : " 
					+ firstQuittanceGsr.getNumeroQuittance());
		}
		
		// Mettre a jour la table BATCH_QUITTANCE_DEFAUTREMISE
		try {
			quittanceGsrManager.updateQuittancesDefautRemise(refReglement);
			
		} catch(Exception e) {
			throw new ExceptionMetier("Impossible de mettre à jour la table BATCH_QUITTANCE_DEFAUTREMISE " 
					+ e.getMessage());
		}
	}
	
	public void regleGestion005Suspenssion(EntiteBO entiteBO, Map params) throws ExceptionMetier {
		
		MvtDefautRemise mouvement = (MvtDefautRemise) entiteBO;
		if(!ACTION_SUSPENSSION.equals(mouvement.getAction())) {
			return;
		}
		
		// Changer l'etat du rentier
		Rentier rentierBD = mouvement.getRefRentier();
		SituationEtatRentier situationEtatRentier = rentierBD
				.getCurSituationEtatRentier(EtatRente.Suspendue_Ou_Attente);
		situationEtatRentier.setOperateur(entiteBO.getOperateur());
		
		// Motif
		MotifManuelSuspensionRente motifSituationEtat = new MotifManuelSuspensionRente();
		motifSituationEtat.setCode(MotifEtat.Defaut_remise.getCode());
		situationEtatRentier.setRefMotifSituationEtat(motifSituationEtat);	
		getSession().save(situationEtatRentier);
		
	}
	
}
