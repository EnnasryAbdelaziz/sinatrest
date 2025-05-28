package eai.devass.sinistreat.appli.usecase.parametrage.ihm;

import java.util.HashMap;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;

import org.hibernate.exception.ConstraintViolationException;

import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.usecase.BaseUC;

public class InitRechercherSocietesAssuranceUC extends BaseUC {

	@SuppressWarnings("rawtypes")
	protected void executerUC(IValueObject entite, HashMap params)
			throws Exception {


		try {
			List listeSocietes = (List) parametrageManager.getListAssurance(null);
			this.addResultItem(listeSocietes);
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
