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
public class DossierRenteRechercherBySinistreUC extends FacadeServiceUseCase {

	@Override
	protected void doExecuter(IValueObject entite, HashMap params)
			throws Exception {

		DossierRenteVO dossierRenteVO = (DossierRenteVO) entite;
		DossierRente dossierRente = (DossierRente) this	.getItem(DossierRente.class);
		List<DossierRente> listDossierRentes = null;
		
		try {
			DossierRenteManager dossierRenteManager = new DossierRenteManager();
			listDossierRentes = dossierRenteManager.doGetDossierRenteBySinistre(dossierRente,
							dossierRenteVO.getNumPage(), dossierRenteVO.getPageSize());
			
			int nbResultats = dossierRenteVO.getNbResultats();
			if(nbResultats == 0) {
				nbResultats = dossierRenteManager.doGetNombreDossierRent(dossierRente);
			}
			
			addResultItem(listDossierRentes);
			addResultItem(nbResultats);
			addResultItem(dossierRenteVO.getNumPage());
			addResultItem(dossierRenteVO.getPageSize());
			
		} catch (ConstraintViolationException e) {
			ILog logger = (ILog) ServicesFactory.getService(ILog.class);
			logger.error(e.getMessage());
			
		} catch (Exception e) {
			ILog logger = (ILog) ServicesFactory.getService(ILog.class);
			logger.error(e.getMessage());
		}
	}

	public boolean isTransactionnal() {
		return false;
	}

}
