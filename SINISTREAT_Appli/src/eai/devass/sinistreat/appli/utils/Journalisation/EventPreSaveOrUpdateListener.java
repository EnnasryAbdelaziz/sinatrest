package eai.devass.sinistreat.appli.utils.Journalisation;

import ma.co.omnidata.framework.services.core.ServicesFactory;
import ma.co.omnidata.framework.services.dao.IPersistenceService;

import org.hibernate.event.PreInsertEvent;
import org.hibernate.event.PreInsertEventListener;
import org.hibernate.event.PreUpdateEvent;
import org.hibernate.event.PreUpdateEventListener;

import eai.devass.sinistreat.appli.modele.Etat;

/* @author kchakib : 13 oct. 10 */
public class EventPreSaveOrUpdateListener implements PreInsertEventListener, PreUpdateEventListener  {

	public boolean onPreInsert(PreInsertEvent insertEvent) {
		
		IPersistenceService dao = (IPersistenceService) ServicesFactory.getService(IPersistenceService.class);
		Object entityJournalisable = insertEvent.getEntity();
		try {
			if(entityJournalisable instanceof Journalisable) {				
				//dao.createObject(((Journalisable) entityJournalisable).getJournalAction());
				Etat etat = new Etat("5");
				etat.setLibelle("test etat5");
				dao.createObject(etat);
			}
		
		} catch(Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		
		return false;
	}
	
	public boolean onPreUpdate(PreUpdateEvent updateEvent) {
		
		IPersistenceService dao = (IPersistenceService) ServicesFactory.getService(IPersistenceService.class);
		try {
//			if(entityJournalisable instanceof Journalisable)				
//				dao.createObject(((Journalisable) entityJournalisable).getJournalAction());
			Etat etat = new Etat("6");
			etat.setLibelle("test etat6");
			dao.createObject(etat);
			
		
		} catch(Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		
		return false;
	}

	
	

	

	

	

}


