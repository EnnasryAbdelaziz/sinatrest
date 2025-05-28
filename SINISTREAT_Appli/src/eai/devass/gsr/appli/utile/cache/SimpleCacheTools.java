package eai.devass.gsr.appli.utile.cache;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import eai.devass.commun.appli.util.CommonUtils;

public class SimpleCacheTools implements ICacheService {

	private static SimpleCacheTools instance;
	private Map<String, Object> mapCache = new HashMap<String, Object>();
	private CommonUtils commonUtils = CommonUtils.getInstance();
	
	public synchronized static SimpleCacheTools getInstance(){
		if (instance == null) {
			instance = new SimpleCacheTools();
		}
		
		return instance; 
	}

	public void put(String key, Object object) {
		
		if(object == null) {
			return;
		}
			
		if(commonUtils.isReference(object.getClass())) {
			if(!(object instanceof ICachable)) {
				return;
			}
		}
		
		if(commonUtils.isCollection(object.getClass())) {
			List list = (List) object;
			if(!commonUtils.isEmpty(list)) {
				if(!(list.get(0) instanceof ICachable)) {
					return;
				}
			}
		}
				
		mapCache.put(key, object);
			
	}
	
	public Object getElement(String key) {
		
		return mapCache.get(key);
		
	}

	public void dirtyPut(String key, Object object) {
		mapCache.put(key, object);
		
	}
	
	
}
