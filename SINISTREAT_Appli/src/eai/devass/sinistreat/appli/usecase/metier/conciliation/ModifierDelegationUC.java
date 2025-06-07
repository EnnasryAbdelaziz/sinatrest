package eai.devass.sinistreat.appli.usecase.metier.conciliation;

import java.util.HashMap;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.services.businessInterface.impl.FacadeServiceUseCase;
import ma.co.omnidata.framework.services.core.ServicesFactory;
import ma.co.omnidata.framework.services.dao.IPersistenceService;

import org.hibernate.Session;

import eai.devass.commun.appli.exeptions.ExceptionMetier;
import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.manager.conciliation.ConciliationManager;
import eai.devass.sinistreat.appli.modele.metier.conciliation.Delegation;

public class ModifierDelegationUC extends FacadeServiceUseCase {

	@SuppressWarnings("unchecked")
	protected void doExecuter(IValueObject entite, HashMap param)
			throws Exception {
		ConciliationManager conciliationManager = (ConciliationManager) ServicesFactory
				.getService(ConciliationManager.class);
		Delegation delegation = (Delegation) this.getItem(Delegation.class);
		try {
			
			long id = delegation.getId();
			long newSup = delegation.getRefUser().getId();

			conciliationManager.updateDelegation(id,newSup);

			} catch(Exception e) {
				throw new FonctionnelleException("Erreur GLOBAL lors de la validation", e);				
			}
		addResultItem(null);

	}

	protected Session getSession() throws ExceptionMetier {
		try {
			IPersistenceService dao = (IPersistenceService) ServicesFactory
					.getService(IPersistenceService.class);
			return (Session) dao.getSession();

		} catch (Exception e) {
			throw new ExceptionMetier(e.getMessage());
		}

	}
	/**
	 * Methode pour activer le service de transaction
	 * 
	 * @returns soit true pour activer le service de transaction ou false pour
	 *          le desactiver
	 */
	public boolean isTransactionnal() {
		return true;
	}

	/**
	 * Methode pour activer le service de trace
	 * 
	 * @returns soit true pour activer le service de trace ou false pour le
	 *          desactiver
	 */
	public boolean isTracable() {
		return false;
	}
}
