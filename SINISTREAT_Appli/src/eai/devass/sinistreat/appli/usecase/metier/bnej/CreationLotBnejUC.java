package eai.devass.sinistreat.appli.usecase.metier.bnej;

import java.util.HashMap;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import eai.devass.sinistreat.appli.modele.metier.bnej.LotBnej;
import eai.devass.sinistreat.appli.usecase.BaseUC;

public class CreationLotBnejUC extends BaseUC {

	@SuppressWarnings("rawtypes")
	@Override
	protected void executerUC(IValueObject entite, HashMap params)
			throws Exception {

		LotBnej lotBnej = (LotBnej) this.getItem(LotBnej.class);

		lotBnej = (LotBnej) bnejManager.CreationLotBnej(lotBnej);

		addResultItem(lotBnej);

	}

	public boolean isTransactionnal() {
		return true;
	}
}
