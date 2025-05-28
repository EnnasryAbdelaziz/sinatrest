package eai.devass.sinistreat.appli.usecase.parametrage.ihm;

import java.util.HashMap;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;

import org.hibernate.exception.ConstraintViolationException;

import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.usecase.BaseUC;

public class InitRecherchePrestataireUC extends BaseUC {

	@SuppressWarnings("rawtypes")
	protected void executerUC(IValueObject entite, HashMap params)
			throws Exception {

		// Récupérer la class de l'objet BO correspondant à l'objet VO

		try {
			// Banque
			List listeDomaines = (List) parametrageManager.getListDomaine();
			this.addResultItem(listeDomaines);
			List listeActivites = (List) parametrageManager.getListActivite();
			this.addResultItem(listeActivites);
			List listeVilles = (List) parametrageManager.getListVille(null);
			this.addResultItem(listeVilles);
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
