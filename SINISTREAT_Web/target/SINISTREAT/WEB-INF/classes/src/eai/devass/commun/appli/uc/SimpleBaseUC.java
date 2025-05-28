package eai.devass.commun.appli.uc;

import java.util.HashMap;
import java.util.Map;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import ma.co.omnidata.framework.services.businessInterface.impl.FacadeServiceUseCase;
import ma.co.omnidata.framework.services.core.ServicesFactory;
import ma.co.omnidata.framework.services.dao.IPersistenceService;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import eai.devass.commun.appli.exeptions.ExceptionMetier;
import eai.devass.commun.appli.modele.EntiteBO;
import eai.devass.commun.appli.util.CommonUtils;

@SuppressWarnings("all")
public abstract class SimpleBaseUC extends FacadeServiceUseCase {

	
	protected Map<String, Object> paramToConverter = new HashMap<String, Object>();
	protected CommonUtils commonUtils = CommonUtils.getInstance();
	protected Logger logger = Logger.getLogger("loggerSINAT");
	protected abstract void execute(IValueObject valueObject, HashMap params) throws Exception;
	
	
	
	@Override
	protected void doExecuter(IValueObject valueObject, HashMap params) throws Exception {
		
		logger.debug("on est dans : " + this.getClass());
		
		try {
		
			// Call UC
			this.execute(valueObject, params);
			
					
		} catch(ExceptionMetier e) {
			logger.fatal(e);
			throw e;
			
		} catch(Exception e) {
			logger.fatal(e);
			throw new Exception("Probléme technique : " 
					+ ((e.getCause() != null) ? e.getCause().getMessage() : e.getMessage()));
		}
		
	}
	
	protected Session getSession() throws Exception {
		
		IPersistenceService dao = (IPersistenceService) ServicesFactory.getService(IPersistenceService.class);
		return (Session) dao.getSession();
		
	}
	
	@Deprecated
	protected Session getNewSession() throws Exception {
		
		IPersistenceService dao = (IPersistenceService) ServicesFactory.getService(IPersistenceService.class);
		return (Session) dao.newSession();
		
	}
	
	protected EntiteBO getTypeObjet() {
		return null;
		
	}
	
	

}
