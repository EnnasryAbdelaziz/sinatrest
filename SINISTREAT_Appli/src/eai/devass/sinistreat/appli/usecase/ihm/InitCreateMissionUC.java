package eai.devass.sinistreat.appli.usecase.ihm;

import java.util.HashMap;
import java.util.List;

import org.hibernate.exception.ConstraintViolationException;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import eai.devass.sinistreat.appli.exception.FonctionnelleException;
import eai.devass.sinistreat.appli.usecase.BaseUC;

public class InitCreateMissionUC extends BaseUC {

	@Override
	protected void executerUC(IValueObject entite, HashMap params)
			throws Exception {
		try {
			List listEtatMission = (List) parametrageManager
					.getListEtatMission();
			this.addResultItem(listEtatMission);
			List listDomainePrest = (List) parametrageManager
					.getListDomainePrest();
			this.addResultItem(listDomainePrest);
			List listPresation = (List) parametrageManager
					.getListPresation();
			this.addResultItem(listPresation);
		} catch (ConstraintViolationException e) {
			throw new FonctionnelleException(e);

		} catch (Exception e) {
			throw new FonctionnelleException(e.getMessage());
		}

	}

}
