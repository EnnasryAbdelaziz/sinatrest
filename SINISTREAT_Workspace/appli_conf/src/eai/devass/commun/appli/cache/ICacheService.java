package eai.devass.commun.appli.cache;

import java.util.Collection;

public interface ICacheService {
	
	public String SUFFIX_CACHE_REFERNTIEL = "refrentiel_";
	public String SUFFIX_CACHE_METIER = "metier_";
	
	public void put(String key, Object object, String context);
	public Object getElement(String key, String context) throws Exception;
	
	public void clearCache();
	public int getCountElements();
	public Collection<String> getListElement();
	

}
