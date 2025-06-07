package eai.devass.sinistreat.appli.usecase.metier.reglement;

import java.util.HashMap;
import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import eai.devass.sinistreat.appli.modele.metier.reglement.QuittanceExAAA;
import eai.devass.sinistreat.appli.modele.metier.sinistre.Sinistre;
import eai.devass.sinistreat.appli.usecase.BaseUC;
import eai.devass.sinistreat.appli.utils.IConstantes;
import eai.devass.sinistreat.appli.utils.entites.IParam;
import eai.devass.sinistreat.appli.valueobjects.serialisation.PagerVO;

public class RechercheQuittanceExAAAUC extends BaseUC {

	protected void executerUC(IValueObject entite, HashMap params)
			throws Exception {
		try {
			QuittanceExAAA quittanceExAAA = (QuittanceExAAA) this
					.getItem(QuittanceExAAA.class);
			List<QuittanceExAAA> listQuittance = null;
			//Correction sonar  Dead store to local variable  
			PagerVO pagerVO = (PagerVO) params.get(IParam.PAGER);
			if (quittanceExAAA.getNumeroSinistre() != null
					&& !quittanceExAAA.getNumeroSinistre().equals("")) {
				String numSin = quittanceExAAA.getNumeroSinistre();
	        Sinistre sin = sinistreManager.getSinistreByNum(numSin);
		

					if (sin == null || sin.getMigFlag().equals(IConstantes.SINISTRE_EXAAA)) {
						listQuittance = reglementManager
						.rechercheQuittanceExAAA(numSin, pagerVO);
					}
			

			}
			addResultItem(listQuittance);
		} catch (Exception e) {

		}

	}

	public boolean isTransactionnal() {
		return true;
	}

}
