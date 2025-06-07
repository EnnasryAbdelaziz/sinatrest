package eai.devass.sinistreat.appli.usecase.ihm.recours;

import java.util.HashMap;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;

import org.hibernate.exception.ConstraintViolationException;

import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.usecase.BaseUC;

public class InitRecoursAmiableUC extends BaseUC {

	@SuppressWarnings("rawtypes")
	protected void executerUC(IValueObject entite, HashMap params)
			throws Exception {

		// Récupérer la class de l'objet BO correspondant à l'objet VO

		try {
			// Banque
			List listeBanques = (List) parametrageManager.getListBanque(null);
			this.addResultItem(listeBanques);
			List listPronosticRC = (List) parametrageManager
					.getListePronosticRC();
			this.addResultItem(listPronosticRC);
			List listEtatProposition = (List) parametrageManager
					.getListeEtatProposition();
			this.addResultItem(listEtatProposition);
			List listEtatReponse = (List) parametrageManager
					.getListeEtatReponse();
			this.addResultItem(listEtatReponse);
			List listDecision = (List) parametrageManager
					.getListelistDecision();
			this.addResultItem(listDecision);
		} catch (ConstraintViolationException e) {
			throw new FonctionnelleException(e);

		} catch (Exception e) {
			throw new FonctionnelleException(e.getMessage());
		}

	}

	public boolean isTransactionnal() {
		return true;
	}

}
