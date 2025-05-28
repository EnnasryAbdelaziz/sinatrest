package eai.devass.sinistreat.appli.usecase.metier.sinistre;

import java.util.HashMap;
import java.util.List;

import org.hibernate.exception.ConstraintViolationException;

import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.usecase.BaseUC;
import eai.devass.sinistreat.appli.valueobjects.metier.ListVO;
import eai.devass.sinistreat.appli.valueobjects.parametrage.CourrierHybrideVO;
import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.services.businessInterface.impl.ErrorMessageItem;
import ma.co.omnidata.framework.services.businessInterface.impl.InfoMessageItem;

public class ValiderCourrierHybridesUC extends BaseUC {
	

	protected void executerUC(IValueObject entite, HashMap params)
			throws Exception {

		ListVO list = (ListVO) entite;
		List<CourrierHybrideVO> CourrierHybrideVO = (List<CourrierHybrideVO>) list.getListCourrierHybrideVO();
		HashMap param = new HashMap();
		try {

			if (parametrageManager.validerCourrierHybrides(CourrierHybrideVO, param) == true) {

				addResultItem(new InfoMessageItem("SUUCCES_MODIF ETAT"));

			} else {
				addResultItem(new ErrorMessageItem("ECHEC_MODIF ETAT"));
			}

		} catch (ConstraintViolationException e) {
			throw new FonctionnelleException(e);
		} 
		catch (Exception e) {
			throw new FonctionnelleException(e.getMessage());
		}

	}

	public boolean isTransactionnal() {
		return true;
	}

}
