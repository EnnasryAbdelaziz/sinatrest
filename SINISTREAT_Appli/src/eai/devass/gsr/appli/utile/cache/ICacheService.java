package eai.devass.gsr.appli.utile.cache;

public interface ICacheService {
	
	public void put(String key, Object object);
	public Object getElement(String key);
	
	@Deprecated
	public void dirtyPut(String key, Object object);

}

