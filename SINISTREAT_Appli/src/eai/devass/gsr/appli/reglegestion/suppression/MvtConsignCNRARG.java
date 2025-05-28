package eai.devass.gsr.appli.reglegestion.suppression;

import java.util.List;
import java.util.Map;

import ma.co.omnidata.framework.services.core.ServicesFactory;
import ma.co.omnidata.framework.services.dao.PersistenceException;
import ma.co.omnidata.framework.services.entites.EntiteException;
import ma.co.omnidata.framework.services.entites.ProcessEntiteException;

import org.hibernate.HibernateException;

import eai.devass.commun.appli.exeptions.ExceptionMetier;
import eai.devass.commun.appli.modele.EntiteBO;
import eai.devass.gsr.appli.manager.metier.dossierRente.DossierRenteManager;
import eai.devass.gsr.appli.modele.metier.dossierRente.Rentier;
import eai.devass.gsr.appli.modele.metier.mouvements.MvtConsignCNRA;
import eai.devass.gsr.appli.modele.metier.reglement.QuittanceGsr;
import eai.devass.gsr.appli.modele.parametrage.EtatMvt;
import eai.devass.gsr.appli.modele.parametrage.SituationMouvement;
import eai.devass.gsr.appli.modele.parametrage.SituationQuittanceGsr;
import eai.devass.gsr.appli.prm.EtatMouvement;
import eai.devass.gsr.appli.prm.EtatQuittance;
import eai.devass.gsr.appli.reglegestion.BaseRG;
import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.manager.sinistre.SinistreManager;
import eai.devass.sinistreat.appli.modele.metier.sinistre.Sinistre;


@SuppressWarnings("all")
public class MvtConsignCNRARG extends BaseRG { 
	
	private List<Rentier> listRentierDB;
	
	DossierRenteManager dossierRenteManager = (DossierRenteManager) ServicesFactory
	.getService(DossierRenteManager.class);
SinistreManager sinistreManager = (SinistreManager) ServicesFactory
	.getService(SinistreManager.class);
private Sinistre sinistreBD = null;
private Long idSinistre = null;
	
	public void regleGestion000ListeRentier(EntiteBO entiteBO, Map params) throws ExceptionMetier {
		
		MvtConsignCNRA mvtConsignCNRA = (MvtConsignCNRA) entiteBO;
		
		// REcuperer le rentier
		MvtConsignCNRA mvtConsignCNRADB = (MvtConsignCNRA) getSession().get(
				MvtConsignCNRA.class, mvtConsignCNRA.getId());
				
		Rentier rentier = mvtConsignCNRADB.getRefRentier();
		if(rentier == null || rentier.getId() < 0) {
			throw new ExceptionMetier("Le rentier (id) est obligatoire");
		}
		
		listRentierDB = transverseManager.getListRentierByRentier(String
				.valueOf(rentier.getId()));
		
	}
	
	
	public void regleGestion001ListeMvtConsignation(EntiteBO entiteBO, Map params) throws ExceptionMetier {
		
		//MvtConsignCNRA mvtConsignCNRA = (MvtConsignCNRA) entiteBO;
		List<MvtConsignCNRA> listMvtConsignCNRA = null;
		entiteBO.setPersistObject(false);
		MvtConsignCNRA curMvtConsignCNRA = null;
		SituationMouvement situationMouvement = null;
		SituationQuittanceGsr situationQuittanceGsr = null;
		List<QuittanceGsr> listQuittance = null;
		
		try {
			idSinistre = dossierRenteManager.doGetIdSinistre(listRentierDB
					.get(0).getRefDossierRente().getId());
		} catch (HibernateException e) {
			logger.error("problème technique",e);
		} catch (ProcessEntiteException e) {
			logger.error("problème technique",e);
		} catch (EntiteException e) {
			logger.error("problème technique",e);
		} catch (PersistenceException e) {
			logger.error("problème technique",e);
		}
		try {
			sinistreBD = sinistreManager.getSinistreById(idSinistre);
		} catch (FonctionnelleException e) {
			logger.error("problème technique",e);
		}
		
		try {
			
			EtatMvt etatMvt = new EtatMvt(EtatMouvement.Cree.getId());
			for(Rentier curRentier : listRentierDB) {
				if (sinistreBD.getRefVictime().getDeces() != null 
						&& (curRentier.getRefEtatRentier().getId() == 4 
						|| curRentier.getRefEtatRentier().getId() == 6)) {
					if (sinistreBD.getRefVictime().getDeces() == false
							&& curRentier.getLienParente() == 0) {
						traitementMvtConsignCNRA(entiteBO,curRentier,etatMvt);
					} else if (curRentier.getLienParente() != 0) {
						traitementMvtConsignCNRA(entiteBO,curRentier,etatMvt);
					}
				}
			}
		
		} catch(Exception e) {
			throw new ExceptionMetier(e.getMessage());
		}
		
	}
	
	private void traitementMvtConsignCNRA(EntiteBO entiteBO,
			Rentier curRentier, EtatMvt etatMvt) throws Exception {

		List<MvtConsignCNRA> listMvtConsignCNRA = null;
		MvtConsignCNRA curMvtConsignCNRA = null;
		SituationMouvement situationMouvement = null;
		List<QuittanceGsr> listQuittance = null;
		SituationQuittanceGsr situationQuittanceGsr = null;
		// REcuperer le mouvement du rentier
		listMvtConsignCNRA = rentierManager
				.getLastMvtConsignationCnraByRentier(curRentier.getId(),
						etatMvt);

		if (commonGsrUtils.isEmpty(listMvtConsignCNRA)) {
			throw new ExceptionMetier(
					"Aucun mouvement ConsignCNRA trouvé pour le rentier : "
							+ curRentier.getId());
		}

		curMvtConsignCNRA = listMvtConsignCNRA.get(0);
		situationMouvement = curMvtConsignCNRA.getCurSituationMouvement(
				EtatMouvement.Supprimer, null);
		situationMouvement.setOperateur(entiteBO.getOperateur());
		getSession().save(situationMouvement);

		// Annuler les quittances
		listQuittance = curMvtConsignCNRA.getRefsQuittance();
		if (commonGsrUtils.isEmpty(listQuittance)) {
			//continue;
		}

		// On ne peut supprimer que les quittance crees
		for (QuittanceGsr curQuittanceGsr : listQuittance) {
			if (EtatQuittance.Cree.getId() != curQuittanceGsr.getRefEtatQtc()
					.getId()) {
				throw new ExceptionMetier(
						"Impossible de supprimer la quittance [id:"
								+ curQuittanceGsr.getId() + "]");
			}

			situationQuittanceGsr = curQuittanceGsr
					.getCurSituationQuittanceGsr(EtatQuittance.Supprimee);
			situationQuittanceGsr.setOperateur(entiteBO.getOperateur());
			getSession().save(situationQuittanceGsr);

		}
	}

}
