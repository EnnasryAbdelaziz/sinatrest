package eai.devass.sinistreat.appli.synchronisationSinistre.factory;

/** @author chak's */

import ma.co.omnidata.framework.services.core.ServicesFactory;
import ma.co.omnidata.framework.services.dao.IPersistenceService;
import eai.devass.gsr.appli.utile.CommonGsrUtils;
import eai.devass.referentiel.services.pub.IReferentielDevAssBDC;
import eai.devass.sinistreat.appli.synchronisationSinistre.manager.SynchronisationConverter;
import eai.devass.sinistreat.appli.utils.sqlquery.GenerateSqlQuery;


@SuppressWarnings("all")
public class FactorySynchro  {

	private static FactorySynchro instance;
	public SynchronisationConverter synchroConverter = new SynchronisationConverter();
	public GenerateSqlQuery generateSqlQuery = GenerateSqlQuery.getInstance();
	public CommonGsrUtils commonGsrUtils = CommonGsrUtils.getInstance();
	
	
	public synchronized static FactorySynchro getInstance() {
		if (instance == null) {
			instance = new FactorySynchro();
		}

		return instance;
	}

	public IReferentielDevAssBDC getReferentielDevAssBDC() {
		return (IReferentielDevAssBDC) ServicesFactory
			.getService("serviceReferentielSynchroSinistre");
	}

	public IPersistenceService getDao() {
		return (IPersistenceService) ServicesFactory
		.getService(IPersistenceService.class);
	}

	
	
	

	
}
