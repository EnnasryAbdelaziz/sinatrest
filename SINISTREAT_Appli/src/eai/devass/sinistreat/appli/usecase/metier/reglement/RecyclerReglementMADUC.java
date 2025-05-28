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
import eai.devass.sinistreat.appli.modele.parametrage.EtatRgl;
import eai.devass.sinistreat.appli.usecase.BaseUC;
import eai.devass.sinistreat.appli.utils.IConstantes;

public class RecyclerReglementMADUC extends BaseUC {

	@SuppressWarnings("unchecked")
	protected void executerUC(IValueObject entite, HashMap params)
			throws Exception {

		List<Reglement> listRgl = this.getItems(Reglement.class);
		if(listRgl == null || listRgl.isEmpty()) {
			addResultItem(new ArrayList<Reglement>());
		}
		//listRgl = reglementManager.validerListReglement(listRgl);
		ReglementRG reglementRG = null;
		ReglementRG reglementRGInter = null;
		Transaction tx = null;
		Session session = getNewSession();
		List<String> listErrors = new ArrayList<String>();
		
		// try global pour fermeture de la session
		if(listRgl!=null)
			System.out.println(listRgl);
		{
		try {
			for(Reglement reglement : listRgl) {
				
				if(reglement.getId()==null) {
					Reglement reglementInter = reglementManager.getReglementByNum(reglement.getNumeroQuittance());
					reglement.setId(reglementInter.getId());
				}
				reglementRG = new ReglementRG(reglement, session);
				reglementRG.getReglementDataBase();
				reglementRG.getReglementDB().setModeReglement(reglement.getModeReglement());
				reglementRGInter = new ReglementRG(reglementRG.getReglementDB(), session);
				reglementRG = reglementRGInter ;
				tx = reglementRG.beginTransaction();
				try {
					// Recuperer le reglement DB
					reglementRG.getReglementDataBase();
					
					// Vaider le reglement
					reglementRG.validateReglement();
					// Set etat to reglementDB
					//reglementRG.setEtatReglementDB();
					
					// Set information to reglement (preparation emission QTC)
					reglementRG.setInfoReglement();
					
					
				} catch(Exception e) {
					listErrors.add("Probléme lors de la mise à jour de la quittance MAD");
					session.clear();
					continue;
				}
				
				// RePositionnement Quittance
				try {
					reglementRG.emissionNewPositionnement();
				
				} catch (Exception e) {
					// Dans le cas de l'emission de la quittance QTC
					tx.rollback();
					session.clear();
					continue;
				}
				
				// Mise à jour des montants
				try {
					
				
				} catch(Exception e) {
					tx.rollback();
					session.clear();
					continue; 
				}
				
				// OK
				// mise a jour historique etat reglement:
//				int n = Integer.parseInt(reglementRG.getReglementDB().getNbRelance());
//				n +=1;
				reglementRG.getReglementDB().setNbRelance(String.valueOf(Long.valueOf(reglementRG.getReglementDB().getNbRelance())+1));
				
				reglementRG.getReglementDB().setRefEtatReglement(new EtatRgl(IConstantes.ETAT_REGLEMENT_EN_INSTANCE_DE_REGLEMENT));
				//reglementRG.getReglementDB().setModeReglement(reglement.getModeReglement());
				
				//reglementRG.addHistoriqueEtat(IConstantes.MOTIF_RECYCLAGE_REGLEMENT);
				reglementRG.addHistoriqueEtatRecyclage(IConstantes.MOTIF_RECYCLAGE_REGLEMENT);
				
				
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
