package eai.devass.sinistreat.appli.usecase.metier.sinistre;

import java.util.HashMap;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.services.core.ServicesFactory;
import ma.co.omnidata.framework.services.log.ILog;

import org.hibernate.exception.ConstraintViolationException;

import eai.devass.sinistreat.appli.manager.sinistre.SinistreManager;
import eai.devass.sinistreat.appli.usecase.BaseUC;
import eai.devass.sinistreat.appli.valueobjects.metier.sinistre.SinistreVO;

public class SumArreragesACReglesUC extends BaseUC {
	protected void executerUC(IValueObject entite, HashMap params)
			throws Exception {

		SinistreVO sinistrevo= (SinistreVO)entite;
		try {
			SinistreManager sinistreManager = new SinistreManager();
			Double result = sinistreManager.doSommeArrerageRegle(sinistrevo.getId());
			// correction sonar :Redundant nullcheck of value known to be non-null  
			this.addResultItem(result);
		} catch (ConstraintViolationException e) {
			ILog logger = (ILog) ServicesFactory.getService(ILog.class);
			logger.error(e.getMessage());
		} catch (Exception e) {
			ILog logger = (ILog) ServicesFactory.getService(ILog.class);
			logger.error(e.getMessage());
		}
	}

	public boolean isTransactionnal() {
		return false;
	}

}
