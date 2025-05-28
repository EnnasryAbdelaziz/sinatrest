package eai.devass.gsr.appli.manager;

import ma.co.omnidata.framework.services.core.ServicesFactory;
import ma.co.omnidata.framework.services.dao.IPersistenceService;
import ma.co.omnidata.framework.services.entites.EntiteManagerAbst;

import org.apache.log4j.Logger;
import org.hibernate.Session;

public class CommunManager extends EntiteManagerAbst {
	
	protected Logger logger = Logger.getLogger("loggerGSR");
	protected Session getSession() throws Exception {
		 IPersistenceService dao = (IPersistenceService) ServicesFactory
		 	.getService(IPersistenceService.class);
		 return (Session) dao.getSession();
	}

}
