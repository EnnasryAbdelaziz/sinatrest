package eai.devass.sinistreat.appli.utils;

import ma.co.omnidata.framework.services.dao.PersistenceException;
import ma.co.omnidata.framework.services.dao.hibernate.HibernatePersistenceService;


public class HibernatePersistenceServiceLocale  extends HibernatePersistenceService{

	private static HibernatePersistenceServiceLocale instance = new HibernatePersistenceServiceLocale();
	
    public synchronized static HibernatePersistenceService getInstance()
    {

        if(instance == null) {
			instance = new HibernatePersistenceServiceLocale();
		}
        return instance;
    }	
	public HibernatePersistenceServiceLocale() {
		super();
		// TODO Auto-generated constructor stub
	}

	public HibernatePersistenceServiceLocale(String configFile) {
		super(configFile);
		// TODO Auto-generated constructor stub
	}
	@Override
	public void createObject(Object object) throws PersistenceException {
		this.createObject(object, this.getSession());
	}	
	
}
