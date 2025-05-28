package eai.devass.gsr.appli.usecase.metier.dossierRente;

import java.util.HashMap;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.services.core.ServicesFactory;
import ma.co.omnidata.framework.services.log.ILog;

import org.hibernate.exception.ConstraintViolationException;

import eai.devass.gsr.appli.manager.metier.dossierRente.DossierRenteManager;
import eai.devass.gsr.appli.modele.metier.dossierRente.DossierRente;
import eai.devass.sinistreat.appli.usecase.BaseUC;

/**
 * Service de recherche d' une entité
 * 
 * @author Nom Prenom (email)
 */
public class DossierRenteGetLastNumRenteUC extends BaseUC {
	protected void executerUC(IValueObject entite, HashMap params)
			throws Exception {

		// DossierRenteVO dossierRenteVO = (DossierRenteVO)entite;
		DossierRente dossierRente = (DossierRente) this
				.getItem(DossierRente.class);
		try {
			DossierRenteManager dossierRenteManager = new DossierRenteManager();
			Long result = dossierRenteManager.doGetLastNumRente(dossierRente);
			if (result == null) {
				// result = new Long(0);
				result = Long.valueOf(0);
			}
			addResultItem(result);
		} catch (ConstraintViolationException e) {
			// throw new FonctionnelleException(e);
			ILog logger = (ILog) ServicesFactory.getService(ILog.class);
			logger.error(e.getMessage());
		} catch (Exception e) {
			// throw new FonctionnelleException(e.getMessage());
			ILog logger = (ILog) ServicesFactory.getService(ILog.class);
			logger.error(e.getMessage());
		}
	}

	public boolean isTransactionnal() {
		return false;
	}

}
