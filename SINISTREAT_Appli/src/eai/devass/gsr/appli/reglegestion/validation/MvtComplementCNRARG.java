package eai.devass.gsr.appli.reglegestion.validation;

import java.util.Date;
import java.util.List;
import java.util.Map;

import ma.co.omnidata.framework.services.dao.PersistenceException;
import ma.co.omnidata.framework.services.entites.EntiteException;
import ma.co.omnidata.framework.services.entites.ProcessEntiteException;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.hibernate.HibernateException;

import eai.devass.commun.appli.exeptions.ExceptionMetier;
import eai.devass.commun.appli.modele.EntiteBO;
import eai.devass.gsr.appli.modele.metier.dossierRente.Rentier;
import eai.devass.gsr.appli.modele.metier.mouvements.MvtComplementCNRA;
import eai.devass.gsr.appli.modele.metier.reglement.QuittanceGsr;
import eai.devass.gsr.appli.modele.parametrage.NatureQtcGsr;
import eai.devass.gsr.appli.modele.parametrage.SituationMouvement;
import eai.devass.gsr.appli.prm.NatureQuittance;
import eai.devass.gsr.appli.reglegestion.BaseRG;
import eai.devass.gsr.appli.utile.IMessageException;
import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.modele.metier.sinistre.Sinistre;
import eai.devass.sinistreat.appli.utils.IConstantes;

public class MvtComplementCNRARG extends BaseRG {
	
	private Rentier rentier = null;
	private Sinistre sinistreBD = null;
	public void regleGestion001GererQtcRegl(EntiteBO entiteBO, Map params) throws ExceptionMetier, EntiteException {
		MvtComplementCNRA mouvement = (MvtComplementCNRA)entiteBO;
		mouvement.setEmissionQuittance(true);
		List<QuittanceGsr> listQuittanceGsr = mouvement.getRefsQuittance();
		if(commonGsrUtils.isEmpty(listQuittanceGsr)) {
			throw new ExceptionMetier("Veulliez créer une quitatnce de réglement !!");
		}
		QuittanceGsr quittanceGsr = listQuittanceGsr.get(0);
		List<SituationMouvement> listSituationMvt = mouvement.getRefSituationMouvement();
		String motif = listSituationMvt.get(listSituationMvt.size() - 1).getRefMotifSituationEtat().getCode();
		if(motif.equals("14")){ //Decision judiciare
			if(mouvement.getJugementFinal() != null && mouvement.getJugementFinal().booleanValue()){//decision judiciare finale
				//Dans ce cas génerer deux quittances.
				
			
				//Premiere quittance : Arrérage
				quittanceGsr.setBeneficiaire("CNRA");
				quittanceGsr.setMontant(mouvement.getArrerageARegler());
				NatureQtcGsr natureQtc = new NatureQtcGsr();
				natureQtc.setId(NatureQuittance.Complement_rente.getId());
				quittanceGsr.setRefNatureQuittance(natureQtc);
				quittanceGsr.setCodePrestation(NatureQuittance.Complement_rente.getRubrique());
				
				//Deuxième quittance : complement à régler
				QuittanceGsr quittanceArrerages = new QuittanceGsr();
				try {
					BeanUtilsBean.getInstance().copyProperties(quittanceArrerages, quittanceGsr);
				} catch(Exception e) {
					throw new ExceptionMetier("Impossible de créer la quitatnce GSR du montant trop-perçu !!!");
				}
				quittanceArrerages.setMontant(mouvement.getMntComplementCNRA());
				natureQtc = new NatureQtcGsr();
				natureQtc.setId(NatureQuittance.Complement_Capital_constitutif_CNRA.getId());
				quittanceArrerages.setRefNatureQuittance(natureQtc);
				quittanceArrerages.setCodePrestation(NatureQuittance.Complement_Capital_constitutif_CNRA.getRubrique());
				quittanceArrerages.setId(0);
				quittanceArrerages.setRefSituationQuittanceGsr(null);
				quittanceArrerages.setRefsHeritier(null);
				quittanceArrerages.setRefsProthese(null);
				listQuittanceGsr.add(quittanceArrerages);
				
			}else{//Decision non definitive
				//Generer quittnace
				quittanceGsr.setMontant(mouvement.getNouvMntRente() - mouvement.getRefRentier().getMontantRenteTrimestrielle());
				NatureQtcGsr natureQtc = new NatureQtcGsr();
				natureQtc.setId(NatureQuittance.Complement_rente.getId());
				quittanceGsr.setRefNatureQuittance(natureQtc);
				quittanceGsr.setCodePrestation(NatureQuittance.Complement_rente.getRubrique());
			}
		} else if(motif.equals("15") || motif.equals("16") || motif.equals("17")){ 
			//Premiere quittance
			quittanceGsr.setBeneficiaire("CNRA");
			quittanceGsr.setMontant(mouvement.getArrerageARegler());
			NatureQtcGsr natureQtc = new NatureQtcGsr();
			natureQtc.setId(NatureQuittance.Complement_rente.getId());
			quittanceGsr.setRefNatureQuittance(natureQtc);
			quittanceGsr.setCodePrestation(NatureQuittance.Complement_rente.getRubrique());
			
			//Deuxieme quittance
			QuittanceGsr quittanceArrerages = new QuittanceGsr();
			try {
				BeanUtilsBean.getInstance().copyProperties(quittanceArrerages, quittanceGsr);
			} catch(Exception e) {
				throw new ExceptionMetier(
						"Impossible de créer la quitatnce GSR du montant trop-perçu !!!");
			}
			quittanceArrerages.setMontant(mouvement.getMntComplementCNRA());
			natureQtc = new NatureQtcGsr();
			natureQtc.setId(NatureQuittance.Complement_Capital_constitutif_CNRA.getId());
			quittanceArrerages.setRefNatureQuittance(natureQtc);
			quittanceArrerages.setCodePrestation(NatureQuittance.Complement_Capital_constitutif_CNRA.getRubrique());
			quittanceArrerages.setId(0);
			quittanceArrerages.setRefSituationQuittanceGsr(null);
			quittanceArrerages.setRefsHeritier(null);
			quittanceArrerages.setRefsProthese(null);
			listQuittanceGsr.add(quittanceArrerages);
		}
	}
	
	public void regleGestion003QuittanceEquilibre(EntiteBO entiteBO, Map params) throws ExceptionMetier, EntiteException, HibernateException, FonctionnelleException, PersistenceException {
		MvtComplementCNRA mouvement = (MvtComplementCNRA)entiteBO;
		List<SituationMouvement> listSituationMvt = mouvement.getRefSituationMouvement();
		String motif = listSituationMvt.get(listSituationMvt.size() - 1).getRefMotifSituationEtat().getCode();
		Rentier rentierDB = rentierManager.getRentierByID(mouvement.getRefRentier().getId());
		Double mntReserveGrave = rentierDB.getRefDossierRente().getRefSinistre().getReserveGrave();
		if(motif.equals("14")){ //Decision judiciare
			if(mouvement.getJugementFinal() != null && mouvement.getJugementFinal().booleanValue()){//decision judiciare finale
				Double montantQtc = mouvement.getMntComplementCNRA() + mouvement.getArrerageARegler();
				verifierEtCreerQtcEquilibre(mouvement, mntReserveGrave, montantQtc);
			} else {//Decision judiciare non definitive
				double coefficientAge = transverseManager.getCoefficientAge(rentierDB, mouvement.getDatDepartAugmentation().getTime());
				Double montantQtc = mouvement.getComplementRente().doubleValue() * 4 * coefficientAge;
				verifierEtCreerQtcEquilibre(mouvement, mntReserveGrave, montantQtc);
			}
		} else if(motif.equals("15") || motif.equals("16") || motif.equals("17")) { //Veuve sexagénaire + •	Descendant scolarisé + •	Descendant orphelin
			Double montantQtc = mouvement.getMntComplementCNRA() + mouvement.getArrerageARegler();
			verifierEtCreerQtcEquilibre(mouvement, mntReserveGrave, montantQtc);
		}
	}
	
	private void verifierEtCreerQtcEquilibre(MvtComplementCNRA mouvement, Double mntReserveGrave, Double montantQtc) throws ExceptionMetier, HibernateException, ProcessEntiteException, EntiteException, PersistenceException, FonctionnelleException{
		if(mntReserveGrave.doubleValue() >= montantQtc.doubleValue()){
			QuittanceGsr quittanceGsr = new QuittanceGsr();
			quittanceGsr.setMontant(montantQtc);
			mouvement.addQuittanceEquilibre(quittanceGsr);
			//Diminuer la reserve grave
			mouvement.setMntDiminutionRsvGrave(montantQtc);
			rentier = mouvement.getRefRentier();
			if (rentier == null || rentier.getId() <= 0) {
				throw new ExceptionMetier(IMessageException.EXP_RENTIER_INTROUVALE);
			}
			Long idDossierRente = transverseManager.doGetIdDossier(rentier.getId());
			Long idSinistre = transverseManager.doGetIdSinistre(idDossierRente);
			sinistreBD = sinistreManager.getSinistreById(idSinistre);
			sinistreBD.setReserveGrave(mntReserveGrave - montantQtc);
			sinistreBD.setUserModificateur(mouvement.getOperateur());
			//WMOS: 12/11/2015 add date modification 
			sinistreBD.setDateModification(new Date());
			sinistreManager.gsrCreerMouvement(sinistreBD,
					"Modification",
					IConstantes.MOTIF_GSR_CHANGEMENT_RESERVEGRAVE);
		}else{
			throw new ExceptionMetier(IMessageException.EXP_RESERVE_GRAVE_INSUFFISSANTE);
		}
	}
}
