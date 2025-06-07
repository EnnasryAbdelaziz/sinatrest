package eai.devass.sinistreat.appli.usecase.metier.reglement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.services.core.ServicesFactory;
import ma.co.omnidata.framework.services.dao.IPersistenceService;

import org.hibernate.Session;
import org.hibernate.Transaction;

import eai.devass.sinistreat.appli.businessrule.ReglementRG;
import eai.devass.sinistreat.appli.modele.metier.reglement.Reglement;
import eai.devass.sinistreat.appli.usecase.BaseUC;
import eai.devass.sinistreat.appli.utils.IConstantes;

public class ValidateReglementUC extends BaseUC {

	@SuppressWarnings("unchecked")
	protected void executerUC(IValueObject entite, HashMap params)
			throws Exception {

		List<Reglement> listRgl = this.getItems(Reglement.class);
		if(listRgl == null || listRgl.isEmpty()) {
			addResultItem(new ArrayList<Reglement>());
		}
		//listRgl = reglementManager.validerListReglement(listRgl);
		ReglementRG reglementRG = null;
		Transaction tx = null;
		Session session = getNewSession();
		List<String> listErrors = new ArrayList<String>();
		// try global pour fermeture de la session
		if(listRgl!=null)
		{
		try {
			for(Reglement reglement : listRgl) {
				reglementRG = new ReglementRG(reglement, session);
				tx = reglementRG.beginTransaction();
				try {
					// Recuperer le reglement DB
					reglementRG.getReglementDataBase();
					
					// Vaider le reglement
					reglementRG.validateReglement();
					
					// Set etat to reglementDB
					reglementRG.setEtatReglementDB();
					
					// Set information to reglement (preparation emission QTC)
					reglementRG.setInfoReglement();
					
					// Mise a jour reserve
					if (reglement.getRefTypeReglement() != null
							&& reglement.getRefTypeReglement().getCode()
									.equals(IConstantes.TYPE_REGLEMENT_BGD)) {
						reglementRG.miseAjourReserveRegBGD();
					} else {
						reglementRG.miseAjourReserve();
					}
					// flush session
					session.flush();
					
				} catch(Exception e) {
					listErrors.add("Probléme lors de la mise à jour du Sinistre");
					session.clear();
					continue;
				}
				
				// Emission quittance et positionnement
				try {
					reglementRG.emissionPositionnement();
				
				} catch (IllegalAccessException e) {
					// Dans le cas ou l'exp est au niveau du Positionnement, en continue sans rollback
					// Il faut mettre à jour l'etat du reglement (en instance positionnement)
					tx.commit();
					session.clear();
					continue;
					 
				} catch (Exception e) {
					// Dans le cas de l'emission de la quittance QTC
					tx.rollback();
					session.clear();
					continue;
				}
				
				// Mise à jour des montants
				try {
					reglementRG.updateCumulRegValidation();
					
						// RG 7.1.8 et RG 7.2.7		
						//reglementRG.miseAjourReserve();
						
						// mise a jour historique etat reglement:
						reglement.setRefEtatReglement(reglementRG.getReglementDB().getRefEtatReglement());
						reglementRG.addHistoriqueEtat(IConstantes.MOTIF_VALIDATION_REGLEMENT);
					
				
				} catch(Exception e) {
					// Cas exeptionnel, le positionnement et l'emission OK , ms la mise à jour KO !!!
					// 
				}
				
				// OK
				reglementRG.getReglementDB().setNomUserModificateur(reglement.getNomUserModificateur());
				reglementRG.getReglementDB().getRefSinistre().setUserModificateur(reglement.getNomUserModificateur());
				tx.commit();
				session.clear();
				listErrors.addAll(reglementRG.getListErrors());
			}
		
		} catch(Exception e) {
			logger.fatal("Erreur GLOBAL lors du validation", e);
			reglementRG.getListErrors().add("Erreur GLOBAL lors du validation");
			
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}
//		// Add error messages
		if (!listErrors.isEmpty()) {
			StringBuilder errors = new StringBuilder();
			for (String curString : listErrors) {
				errors.append(" * ").append(curString).append("|");
			}
			throw new Exception(errors.toString());
		}
		
//		for (Reglement reglement : listRgl) {
//			
//			// MFBO@817
//			
//			 Sinistre sinDB = sinistreManager.getSinistreById(reglement.getRefSinistre()
//					.getId());
//			if (sinDB == null) {
//				throw new FonctionnelleException(EXP_SINISTRE_INEXISTANT);
//			}
//			sinDB.setUserModificateur(reglement.getNomUserModificateur());
//			//dao.updateObject(sinDB);
//			
//
//		}

		//addResultItem(listRgl);

	}

	public boolean isTransactionnal() {
		return true;
	}
	
	private Session getNewSession() throws Exception {

		IPersistenceService dao = (IPersistenceService) ServicesFactory
				.getService(IPersistenceService.class);
		return (Session) dao.newSession();

	}
}
