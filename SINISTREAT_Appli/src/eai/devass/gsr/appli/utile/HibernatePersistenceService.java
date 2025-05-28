package eai.devass.gsr.appli.utile;

import ma.co.omnidata.framework.services.dao.PersistenceException;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class HibernatePersistenceService extends ma.co.omnidata.framework.services.dao.hibernate.HibernatePersistenceService {

	private Logger logger = Logger.getLogger("loggerSINAT");

	@Override
	public void commitTransaction(long id, Object tplSession) throws PersistenceException {
//		HibernateTransaction trx = getTransaction(id);
//		trx.getTransaction().commit();
//		((org.hibernate.Session) trx.getSession()).flush();
//		((org.hibernate.Session) trx.getSession()).clear();
//		this.trxs.remove(Long.valueOf(id));
		
		Session session = (Session) tplSession;
		try {
			super.commitTransaction(id, tplSession);
			
		} catch(Exception e) {
			rollbackTransaction(id, tplSession);
			throw new PersistenceException(getClass(), e.getMessage(), 1);
			
		} finally {
			session.clear();
		}
		
	}
	
	@Override
	public void rollbackTransaction(long id, Object tplSession) throws PersistenceException
	  {
	    try
	    {
	      Session session = (Session) tplSession;
	      Transaction tx = session.getTransaction();
	      tx.rollback();
	      
	    } catch (Exception e) {
	      logger.error("problème technique",e);
	    }
	  }

	@Override
	public void commitTransaction(long id, short commitMode, Object tplSession)
			throws PersistenceException {
		commitTransaction(id, tplSession);
	}
	
	@Override
	public void createObject(Object object) throws PersistenceException {
		//this.createObject(object, this.getSession());
		((Session) this.getSession()).save(object);
	}	

	

}
