package eai.devass.sinistreat.appli.usecase.ihm;

import java.util.HashMap;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;

import org.hibernate.exception.ConstraintViolationException;

import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.modele.metier.sinistre.Sinistre;
import eai.devass.sinistreat.appli.usecase.BaseUC;
import eai.devass.sinistreat.appli.valueobjects.metier.sinistre.SinistreVO;

public class InitSinistreUC extends BaseUC {

	@SuppressWarnings("unchecked")
	protected void executerUC(IValueObject entite, HashMap params)
			throws Exception {

		// Récupérer la class de l'objet BO correspondant à l'objet VO
		SinistreVO sinvo = (SinistreVO) entite;
		try {

			Sinistre sin = (Sinistre) sinistreManager.getSinistreByNum(sinvo
					.getNumeroSinistre());
			this.addResultItem(sin);
			List listSituation = (List) parametrageManager
					.getlistSituation(null);
			this.addResultItem(listSituation);

		} catch (ConstraintViolationException e) {
			throw new FonctionnelleException(e);

		} catch (Exception e) {
			throw new FonctionnelleException(e.getMessage());
		}

	}

	public boolean isTransactionnal() {
		return false;
	}

}
