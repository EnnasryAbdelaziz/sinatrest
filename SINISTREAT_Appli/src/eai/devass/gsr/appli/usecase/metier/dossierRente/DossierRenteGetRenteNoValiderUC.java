package eai.devass.gsr.appli.usecase.metier.dossierRente;

import java.util.HashMap;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.services.businessInterface.impl.FacadeServiceUseCase;
import ma.co.omnidata.framework.services.core.ServicesFactory;
import ma.co.omnidata.framework.services.log.ILog;

import org.hibernate.exception.ConstraintViolationException;

import eai.devass.gsr.appli.manager.metier.dossierRente.DossierRenteManager;
import eai.devass.gsr.appli.modele.metier.dossierRente.DossierRente;
import eai.devass.gsr.appli.valueobjects.metier.dossierRente.DossierRenteVO;

/**
 * Service de recherche d' une entité
 * 
 * @author Nom Prenom (email)
 */
public class DossierRenteGetRenteNoValiderUC extends FacadeServiceUseCase {
	protected void doExecuter(IValueObject entite, HashMap params)
			throws Exception {

		DossierRenteVO dossierRenteVO = (DossierRenteVO) entite;
		DossierRente dossierRente = (DossierRente) this
				.getItem(DossierRente.class);
		List<DossierRente> listDossierRentes = null;
		try {
			DossierRenteManager dossierRenteManager = new DossierRenteManager();
			listDossierRentes = dossierRenteManager.doGetRentNoValider(
					dossierRente, dossierRenteVO.getNumPage(),
					dossierRenteVO.getPageSize());
			int nbrResultat = dossierRenteManager
					.doGetNbrRentNoValider(dossierRente);
			addResultItem(listDossierRentes);
			addResultItem(nbrResultat);
			addResultItem(dossierRenteVO.getNumPage());
			addResultItem(dossierRenteVO.getPageSize());
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
