package eai.devass.sinistreat.appli.utils.Journalisation;

import java.util.Map;

import ma.co.omnidata.framework.services.core.ServicesFactory;
import ma.co.omnidata.framework.services.dao.IPersistenceService;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.event.PersistEvent;
import org.hibernate.event.PersistEventListener;
import org.hibernate.event.PreUpdateEvent;
import org.hibernate.event.PreUpdateEventListener;

import eai.devass.sinistreat.appli.modele.Etat;


/* @author kchakib : 13 oct. 10 */
public class EventSaveOrUpdateListener implements PersistEventListener,PreUpdateEventListener   {

	private static final long serialVersionUID = 1L;
	
	private transient Logger logger = Logger.getLogger("loggerGSR");
	
	public void onPersist(PersistEvent event) throws HibernateException {
		
		try {
//			if(entityJournalisable instanceof Journalisable)				
//				dao.createObject(((Journalisable) entityJournalisable).getJournalAction());
			
			Etat etat = new Etat("10");
			etat.setLibelle("test etat10");
			event.getSession().save(etat);
		
		} catch(Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		
	}

	public void onPersist(PersistEvent event, Map createdAlready) throws HibernateException {

		//System.out.println("");
		logger.info("");
		//org.hibernate.event.def.DefaultUpdateEventListener
//		org.hibernate.event.def.DefaultPersistEventListener
		//org.hibernate.event.EventListeners eventListeners = new EventListeners();
		
	}

	public boolean onPreUpdate(PreUpdateEvent event) {
		
		IPersistenceService dao = (IPersistenceService) ServicesFactory.getService(IPersistenceService.class);
		try {
		
			Etat etat = new Etat("9");
			etat.setLibelle("test etat9");
			dao.createObject(etat);
			
		} catch(Exception e) {
			logger.error("problème technique",e);
		}
		
		return false;
	}

	
	

}


