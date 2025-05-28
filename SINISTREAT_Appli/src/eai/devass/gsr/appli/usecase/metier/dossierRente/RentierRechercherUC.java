package eai.devass.gsr.appli.usecase.metier.dossierRente;

import java.util.HashMap;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.services.businessInterface.impl.FacadeServiceUseCase;
import ma.co.omnidata.framework.services.core.ServicesFactory;
import ma.co.omnidata.framework.services.log.ILog;

import org.hibernate.exception.ConstraintViolationException;

import eai.devass.gsr.appli.manager.metier.dossierRente.RentierManager;
import eai.devass.gsr.appli.modele.metier.dossierRente.Rentier;
import eai.devass.gsr.appli.valueobjects.metier.dossierRente.RentierVO;

/**
 * Service de recherche d' une entité
 * 
 * @author Nom Prenom (email)
 */
public class RentierRechercherUC extends FacadeServiceUseCase {

	/**
	 * Methode pour activer le service de trace
	 * 
	 * @returns soit true pour activer le service de trace ou false pour le
	 *          desactiver
	 */
	public boolean isTracable() {
		return false;
	}

	@Override
	protected void doExecuter(IValueObject entite, HashMap params)
			throws Exception {

		RentierVO rentierVO = (RentierVO) entite;
		Rentier rentier = (Rentier) this.getItem(Rentier.class);
		List<Rentier> listDossierRentes = null;

		try {
			RentierManager rentierManager = new RentierManager();
			listDossierRentes = rentierManager.doGetRentierBySinistre(rentier,
					rentierVO.getNumPage(), rentierVO.getPageSize());

			int nbrResultats = rentierVO.getNbResultats();
			if (nbrResultats == 0) {
				nbrResultats = rentierManager.doGetNombreDossierRent(rentier);
			}

			addResultItem(listDossierRentes);
			addResultItem(nbrResultats);
			addResultItem(rentierVO.getNumPage());
			addResultItem(rentierVO.getPageSize());

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
