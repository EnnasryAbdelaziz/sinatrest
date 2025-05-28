package eai.devass.sinistreat.appli.usecase.metier.sinistre;

import java.util.HashMap;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.services.businessInterface.impl.ErrorMessageItem;
import ma.co.omnidata.framework.services.businessInterface.impl.InfoMessageItem;

import org.hibernate.exception.ConstraintViolationException;

import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.usecase.BaseUC;
import eai.devass.sinistreat.appli.utils.IConstantes;
import eai.devass.sinistreat.appli.valueobjects.metier.ListVO;
import eai.devass.sinistreat.appli.valueobjects.metier.sinistre.SinistreVO;

public class ValidationSinistreUC extends BaseUC {
	

	protected void executerUC(IValueObject entite, HashMap params)
			throws Exception {

		ListVO list = (ListVO) entite;
		List<SinistreVO> listSin = (List<SinistreVO>) list.getListSinistre();
		HashMap param = new HashMap();
		if(list.getCodeSas()!=null && !"".equals(list.getCodeSas())){
		param.put(IConstantes.CONSTANTE_USER_MODIFICATEUR, list.getCodeSas());
		}
		try {

			if (sinistreManager.modifierEtatListSinistre(listSin, param) == true) {

				addResultItem(new InfoMessageItem("SUUCCES_MODIF ETAT"));

			} else {
				addResultItem(new ErrorMessageItem("ECHEC_MODIF ETAT"));
			}

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
