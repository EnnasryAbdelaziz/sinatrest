package eai.devass.sinistreat.appli.usecase.metier.conciliation;

import java.util.HashMap;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.services.businessInterface.impl.FacadeServiceUseCase;
import ma.co.omnidata.framework.services.core.ServicesFactory;
import ma.co.omnidata.framework.services.dao.IPersistenceService;

import org.hibernate.Session;

import eai.devass.commun.appli.exeptions.ExceptionMetier;
import eai.devass.sinistreat.appli.manager.conciliation.ConciliationManager;
import eai.devass.sinistreat.appli.modele.metier.conciliation.Conciliation;

public class RejeterOffreConciliationUC extends FacadeServiceUseCase {

	@SuppressWarnings("unchecked")
	protected void doExecuter(IValueObject entite, HashMap param)
			throws Exception {

		ConciliationManager conciliationManager = (ConciliationManager) ServicesFactory
				.getService(ConciliationManager.class);
		List<Conciliation> listToUpdateConci = this
				.getItems(Conciliation.class);
		if (listToUpdateConci != null) {
			for (Conciliation conciliation : listToUpdateConci) {

				conciliationManager.updateDossierRejeter(conciliation);
			}
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
