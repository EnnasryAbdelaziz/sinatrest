package eai.devass.gsr.appli.reglegestion.validation;

import java.util.Date;
import java.util.List;
import java.util.Map;

import ma.co.omnidata.framework.services.businessInterface.IMessageItem;
import ma.co.omnidata.framework.services.businessInterface.impl.InfoMessageItem;
import ma.co.omnidata.framework.services.entites.EntiteException;

import org.apache.commons.beanutils.BeanUtilsBean;

import eai.devass.commun.appli.exeptions.ExceptionMetier;
import eai.devass.commun.appli.modele.EntiteBO;
import eai.devass.gsr.appli.modele.metier.dossierRente.Rentier;
import eai.devass.gsr.appli.modele.metier.mouvements.MvtRenteEchue;
import eai.devass.gsr.appli.modele.metier.reglement.QuittanceGsr;
import eai.devass.gsr.appli.modele.parametrage.NatureQtcGsr;
import eai.devass.gsr.appli.modele.parametrage.SituationEtatRentier;
import eai.devass.gsr.appli.prm.EtatRente;
import eai.devass.gsr.appli.prm.NatureQuittance;
import eai.devass.gsr.appli.prm.TypeQuittance;
import eai.devass.gsr.appli.reglegestion.BaseRG;
import eai.devass.gsr.appli.utile.IMessageException;

public class MvtRenteEchueRG extends BaseRG {

	
	public void regleGestion001ValiderMvtRenteEchue(EntiteBO entiteBO,
			Map params) throws ExceptionMetier, EntiteException {
		
		MvtRenteEchue mouvement = (MvtRenteEchue)entiteBO;
		//Mettre le rentier à l'état echu
		Rentier rentier = mouvement.getRefRentier();
		SituationEtatRentier situationEtatRentier = rentier.getCurSituationEtatRentier(EtatRente.Echue);
		situationEtatRentier.setOperateur(mouvement.getOperateur());
		getSession().save(situationEtatRentier);
	}
	
	public void regleGestion002GererQtcRegl(EntiteBO entiteBO, Map params) throws ExceptionMetier, EntiteException {
		MvtRenteEchue mouvement = (MvtRenteEchue)entiteBO;
		Double mntProrata = mouvement.getMntProrata();
		Double mntTropPercu = mouvement.getMntTropPercu();
		Double mntARegle = mntProrata - mntTropPercu;
		if(mntARegle.doubleValue() > 0){
			// Generation quittance avec mnt prorata
			List<QuittanceGsr> listQuittanceGsr = mouvement.getRefsQuittance();
			if(commonGsrUtils.isEmpty(listQuittanceGsr)) {
				throw new ExceptionMetier("Veulliez créer une quitatnce de réglement !!");
			}
			mouvement.setEmissionQuittance(true);
			QuittanceGsr quittanceGsr = listQuittanceGsr.get(0);
			quittanceGsr.setMontant(mntARegle);//MFBO@Evolution 389
			quittanceGsr.setRefNatureQuittance(new NatureQtcGsr(NatureQuittance.Prorata_rente.getId()));
			
			if(mntTropPercu.doubleValue() != 0){
				// Generation quittance recuperation avec mnt trop-perçu
				QuittanceGsr quittanceTropPercu = new QuittanceGsr();
				try {
					BeanUtilsBean.getInstance().copyProperties(quittanceTropPercu, quittanceGsr);
					
				} catch(Exception e) {
					throw new ExceptionMetier(
							"Impossible de créer la quitatnce GSR du montant trop-perçu !!!");
				}
				quittanceTropPercu.setMontant(mntTropPercu);
				quittanceTropPercu.setRefNatureQuittance(new NatureQtcGsr(
						NatureQuittance.Rembourssement.getId()));
				quittanceTropPercu.setRefTypeQuittance(
						new eai.devass.gsr.appli.modele.parametrage.TypeQuittance(
								TypeQuittance.Recuperation.getId()));
				quittanceTropPercu.setId(0);
				quittanceTropPercu.setRefSituationQuittanceGsr(null);
				quittanceTropPercu.setRefsHeritier(null);
				quittanceTropPercu.setRefsProthese(null);
				listQuittanceGsr.add(quittanceTropPercu);
			}
		} else if(mntARegle.doubleValue() < 0){
			Map<String, IMessageItem> message = (Map<String, IMessageItem>) params.get("outMapMessage");
			message.put("001", new InfoMessageItem("Le montant indument payé est  de " + String.valueOf(Math.abs(mntARegle))
				+ ". Procédez à la récupération du trop-perçu"));
			mouvement.setMntTropPercu(Math.abs(mntARegle));
		}
	}
	
	
	
	
	/**
	 * Générer la quittance d'équilibre
	 * @param entiteBO
	 * @param params
	 * @throws ExceptionMetier
	 */
	public void regleGestion003GenererQuittanceEquilibre(EntiteBO entiteBO, Map params) throws ExceptionMetier {
		
		MvtRenteEchue mouvement = (MvtRenteEchue) entiteBO;	
		Rentier rentier = mouvement.getRefRentier();

		if (rentier == null || rentier.getId() <= 0) {
			throw new ExceptionMetier(IMessageException.EXP_RENTIER_INTROUVALE);
		}
		rentierDB = rentierManager.getRentierByID(rentier.getId());
		Double rente = rentierDB.getMontantRenteTrimestrielle();
		Date dateCalcul = null;
		if(mouvement.getDateFinScolarite() != null){
			dateCalcul = mouvement.getDateFinScolarite().getTime();
		}else{
			dateCalcul = mouvement.getDateMariage().getTime();
		}
	
		Double capitalPercu = 0D;
		try {
			capitalPercu = transverseManager.calculerCapitalPercu(rente,
					transverseManager.getCoefficientAge(rentierDB, dateCalcul));
			
		} catch (ExceptionMetier e) {
			return;
		}
		
		if(!(capitalPercu.compareTo(0D)==0)){
			QuittanceGsr quittanceGsr = new QuittanceGsr();
			quittanceGsr.setMontant(-capitalPercu);
			mouvement.addQuittanceEquilibre(quittanceGsr);
		}

	}
	public void regleGestion004Redistribution(EntiteBO entiteBO, Map params) throws ExceptionMetier, EntiteException {
		
		MvtRenteEchue mouvement = (MvtRenteEchue) entiteBO;

		if(mouvement.getDateFinScolarite() != null){
			mouvement.setDateCalculRedistribution(mouvement.getDateFinScolarite());
		}else{
			mouvement.setDateCalculRedistribution(mouvement.getDateMariage());
		}
	}
}
