package eai.devass.sinistreat.appli.usecase.metier.sinistre;

import java.net.URLEncoder;
import java.util.HashMap;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;

import org.hibernate.exception.ConstraintViolationException;

import eai.devass.gsr.appli.utile.CommonGsrUtils;
import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.modele.metier.sinistre.Sinistre;
import eai.devass.sinistreat.appli.usecase.BaseUC;
import eai.devass.sinistreat.appli.valueobjects.metier.sinistre.SinistreVO;

public class ConsulterSinistreUC extends BaseUC {

	protected void executerUC(IValueObject entite, HashMap params)
			throws Exception {

		SinistreVO sinistrevo = (SinistreVO) entite;
		try {
			Boolean completeConversion = (Boolean) params
					.get("completeConversion");
			Sinistre sin = getSinistre(sinistrevo.getNumeroSinistre(),
					completeConversion);
			this.addResultItem(sin);

			// Evolution pour le service RecuperationSiniste (besoin de la GED
			// AT)
			this.addResultItem(completeConversion);

		} catch (ConstraintViolationException e) {
			throw new FonctionnelleException(e);
		} catch (Exception e) {
			throw new FonctionnelleException(e.getMessage());
		}

	}

	public boolean isTransactionnal() {
		return true;
	}

	// Recherche dossier sinistre pour le service GED AT
	private Sinistre getSinistre(String numSinistre, Boolean serviceGedAt)
			throws Exception {

		if (CommonGsrUtils.getInstance().isTrue(serviceGedAt)) {

			if (numSinistre == null || numSinistre.length() != 21) {
				throw new Exception(
						"Le numéro de sinistre doit être sur 21 position !!");
			}
			return sinistreManager.getSinistreForGedByNum(numSinistre);
		} else {
			return (Sinistre) sinistreManager.getSinistreByNum(numSinistre);
		}

	}

}