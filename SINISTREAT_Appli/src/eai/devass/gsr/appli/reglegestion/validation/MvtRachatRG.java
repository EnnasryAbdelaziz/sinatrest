package eai.devass.gsr.appli.reglegestion.validation;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtilsBean;

import eai.devass.commun.appli.exeptions.ExceptionMetier;
import eai.devass.commun.appli.modele.EntiteBO;
import eai.devass.gsr.appli.modele.metier.dossierRente.DossierRente;
import eai.devass.gsr.appli.modele.metier.dossierRente.Rentier;
import eai.devass.gsr.appli.modele.metier.mouvements.Mouvement;
import eai.devass.gsr.appli.modele.metier.mouvements.MvtRachat;
import eai.devass.gsr.appli.modele.metier.reglement.QuittanceGsr;
import eai.devass.gsr.appli.modele.parametrage.EtatRentier;
import eai.devass.gsr.appli.modele.parametrage.MotifRachatRente;
import eai.devass.gsr.appli.modele.parametrage.NatureQtcGsr;
import eai.devass.gsr.appli.modele.parametrage.SituationDossierRentier;
import eai.devass.gsr.appli.modele.parametrage.SituationEtatRentier;
import eai.devass.gsr.appli.modele.parametrage.TypeQuittance;
import eai.devass.gsr.appli.prm.EnumOrigineRachat;
import eai.devass.gsr.appli.prm.EtatRente;
import eai.devass.gsr.appli.prm.MotifEtat;
import eai.devass.gsr.appli.prm.NatureQuittance;
import eai.devass.gsr.appli.reglegestion.BaseRG;
import eai.devass.gsr.appli.utile.IMessageException;
import eai.devass.sinistreat.appli.utils.Message.IMessage;

 


public class MvtRachatRG extends BaseRG{
	
	private Rentier rentier = null;
	private Rentier rentierBD = null;
	private MvtRachat rachat = null;

	
	/**
	 * RG9/FGSR-10.1 : Générer la quittance en fonction du montant du complément rachat.
	 * @param entiteBO
	 * @param params
	 * @throws ExceptionMetier
	 */
	public void regleGestion000ComplementRachat(EntiteBO entiteBO, Map params) throws ExceptionMetier {
		
		MvtRachat rachat = (MvtRachat) entiteBO;
		Double complement = rachat.getMntDiff();
		List<QuittanceGsr> listQtc = rachat.getRefsQuittance();
		QuittanceGsr quittanceGsrJuje = null;
		if(listQtc != null && listQtc.size()>0)
		{
			quittanceGsrJuje = listQtc.get(0);
		
			/*
			 * 
			 * Générer 2 quittances
			 *		Quittance Q1 avec le montant du capital jugé
			 *      Quittance Q2 avec le montant des arrérages
			 *		Q1-Q2 est le montant à régler au rentier
			 */
			
			if(rachat.getRefOrigineRachat().getId()==EnumOrigineRachat.origineConciliation.getId()){
				
				rachat.setGenererQuittance(true);
				rachat.setEmissionQuittance(true);
				if(rachat.getCapitalJuge()>0){
	     		quittanceGsrJuje.setMontant(rachat.getCapitalJuge());
	     		quittanceGsrJuje.setRefNatureQuittance(new NatureQtcGsr(
						NatureQuittance.rachat_apres_constitution_recours_victime.getId()));
	     		//quittanceGsrJuje.setRefsProthese(null);
				}
				
			}else{
			
			if(complement>=0){
				rachat.setGenererQuittance(true);
				rachat.setEmissionQuittance(true);
	     		//MFBO@Evolution 374(suite problème positionnement Quittance).
				quittanceGsrJuje.setMontant(complement);
				
	     		//quittanceGsrJuje.setRefsProthese(null);
				
	     		if(rachat.getMntPercu()>0){
	     		
	     		QuittanceGsr quittanceGsrPercu = new QuittanceGsr();
				try {
					BeanUtilsBean.getInstance().copyProperties(quittanceGsrPercu, quittanceGsrJuje);
					
				} catch(Exception e) {
					throw new ExceptionMetier(
							IMessageException.EXP_QUITTANCE_MNT_PERCU);
				}
				quittanceGsrPercu.setMontant(rachat.getMntPercu());
				quittanceGsrPercu.setRefTypeQuittance(new TypeQuittance(eai.devass.gsr.appli.prm.TypeQuittance.Recuperation.getId()));
				quittanceGsrPercu.setRefNatureQuittance(new NatureQtcGsr(
						NatureQuittance.Rembourssement.getId()));
				quittanceGsrPercu.setId(0);
				quittanceGsrPercu.setRefSituationQuittanceGsr(null);
			    quittanceGsrPercu.setRefsHeritier(null);
			    quittanceGsrPercu.setRefsProthese(null);
				listQtc.add(quittanceGsrPercu);
	     		}
				
			}else{
				rachat.setGenererQuittance(false);
				rachat.setEmissionQuittance(false);
				rachat.setMntTropPercu(Math.abs(complement));
				((Map) params.get("outMapMessage")).put(IMessage.INFO,IMessage.EXP_TROP_PRECU_NEGATIF );
		}
			}
			
			}else{
			rachat.setGenererQuittance(false);
			rachat.setEmissionQuittance(false);
			rachat.setMntTropPercu(Math.abs(complement));
			((Map) params.get("outMapMessage")).put(IMessage.INFO,IMessage.EXP_TROP_PRECU_NEGATIF );
	}
	}
	/**
	 * RG9/FGSR-10.1 Mise à jour du Rentier avec :
	 * Etat du rentier : rachat
	 * @param entiteBO
	 * @param params
	 * @throws ExceptionMetier
	 */
	
	public void regleGestion001SetEtatRentier(EntiteBO entiteBO, Map params) throws ExceptionMetier {
		
		Mouvement mouvement = (Mouvement) entiteBO;

		rentier = mouvement.getRefRentier();

		if (rentier == null || rentier.getId() <= 0) {
			throw new ExceptionMetier(IMessageException.EXP_RENTIER_INTROUVALE);
		}

		rentierBD = rentierManager.getRentierByID(rentier.getId());
		
		EtatRentier etatRentier = new EtatRentier();
		etatRentier.setId(EtatRente.Rachat.getCode());
		rentierBD.setRefEtatRentier(etatRentier);
		rentierBD.setDateEtat(mouvement.getDatEtat());
		
		SituationEtatRentier situationEtatRentier = new SituationEtatRentier();
		
		situationEtatRentier.setRefEtatRentier(etatRentier);
		situationEtatRentier.setRefRentier(rentierBD);
		situationEtatRentier.setDateEtat(new GregorianCalendar().getTime());
		situationEtatRentier.setOperateur(entiteBO.getOperateur());	
		getSession().save(situationEtatRentier);
				
	}


	/**
	 * RG9/FGSR-10.1 Mise à jour du dossier Rente avec :
	 * Etat du dossier GSR « Clôturé » avec motif « Rachat »
	 * @param entiteBO
	 * @param params
	 * @throws ExceptionMetier
	 */
	public void regleGestion002SetEtatDossierRente(EntiteBO entiteBO, Map params) throws ExceptionMetier {
	
		DossierRente dossierRente = null;
		
		dossierRente = rentierBD.getRefDossierRente();
		
		dossierRente.setRefEtatRentier(new EtatRentier(EtatRente.Cloture.getCode()));		
		rentierBD.setRefDossierRente(dossierRente);		
		SituationDossierRentier situationDossierRentier = new SituationDossierRentier();
		MotifRachatRente motifRente = new MotifRachatRente();
		
	
		
		EtatRentier etatRentier = new EtatRentier();
		etatRentier.setId(EtatRente.Cloture.getCode());
		//rentierBD.setRefEtatRentier(etatRentier);
		
		motifRente.setCode(String.valueOf(MotifEtat.Rachat_rente.getCode()));
		
		situationDossierRentier.setRefMotifSituationEtat(motifRente);
		situationDossierRentier.setRefEtatRentier(etatRentier);
		situationDossierRentier.setRefDossierRente(dossierRente);
		situationDossierRentier.setDateEtat(new GregorianCalendar().getTime());
		situationDossierRentier.setOperateur(entiteBO.getOperateur());

		getSession().save(situationDossierRentier);

	}
	

	
	/**
	 * RG9/FGSR-10.1 : Générer la quittance d'équilibre
	 * @param entiteBO
	 * @param params
	 * @throws ExceptionMetier
	 */
	public void regleGestion004GenererQuittanceEquilibre(EntiteBO entiteBO, Map params) throws ExceptionMetier {
		
		rachat = (MvtRachat) entiteBO;
		Double capitaleJuge = rachat.getCapitalJuge();
		Double rente = rentier.getMontantRenteTrimestrielle();
		Date dateCalcul = rachat.getDateCalcul().getTime();
		Double capitalPercu = 0D;
		
		try {
			capitalPercu = transverseManager.calculerCapitalPercu(rente,
					transverseManager.getCoefficientAge(rentierBD, dateCalcul));
		
		} catch(ExceptionMetier e) {
			return;
		}
		
		Double montantCapital = capitalPercu - capitaleJuge;
		if(!(montantCapital.compareTo(0D)==0)){
			QuittanceGsr quittanceGsr = new QuittanceGsr();
			quittanceGsr.setMontant(-montantCapital);
			rachat.addQuittanceEquilibre(quittanceGsr);
		}

		
	}

}

