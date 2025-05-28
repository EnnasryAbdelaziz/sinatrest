package eai.devass.gsr.appli.usecase.parametrage.ihm;

import java.util.HashMap;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.services.core.ServicesFactory;
import ma.co.omnidata.framework.services.log.ILog;

import org.hibernate.exception.ConstraintViolationException;

import eai.devass.sinistreat.appli.usecase.BaseUC;

public class InitRechercheRentierUC extends BaseUC {

	@SuppressWarnings("rawtypes")
	protected void executerUC(IValueObject entite, HashMap params)
			throws Exception {

		// Récupérer la class de l'objet BO correspondant à l'objet VO

		try {

			List listePays = (List) parametrageManager.getListPays(null);
			this.addResultItem(listePays);
			List listeVilles = (List) parametrageManager.getListVille(null);
			this.addResultItem(listeVilles);
			List listDegresParente = (List) parametrageManager
					.getListDegreParente(null);
			this.addResultItem(listDegresParente);
			List listNationalite = (List) parametrageManager
					.getListNationalite(null);
			this.addResultItem(listNationalite);
			List listModePayement = (List) parametrageManager
					.getListModePaiement("REGLEMENT");
			this.addResultItem(listModePayement);
			List listNatureProthese = (List) parametrageManager
					.getListNatureProthese(null);
			this.addResultItem(listNatureProthese);
			List listBanques = (List) parametrageManager.getListBanque(null);
			this.addResultItem(listBanques);
			List listSituationRentier = (List) parametrageManager
					.getListSituationRentier(null);
			this.addResultItem(listSituationRentier);
			List listCoefficientAge = (List) parametrageManager
					.getListCoefficientAge(null);
			this.addResultItem(listCoefficientAge);
			List listDecision = (List) parametrageManager.getListDecision(null);
			this.addResultItem(listDecision);
			List listSociete = (List) parametrageManager.getListSociete(null);
			this.addResultItem(listSociete);
			
			//Début Evo 24/12/2013
			List listEtatRentier = (List) parametrageManager.getListEtatRentier(null);
			this.addResultItem(listEtatRentier);
			//Fin Evo 24/12/2013
			
			//Début Evo Consignation 25/05/2016
			List listSortrentier = (List) parametrageManager.getListSortRentier(null);
			this.addResultItem(listSortrentier);
			//Fin Evo 25/05/2016
			
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
		return true;
	}

}
