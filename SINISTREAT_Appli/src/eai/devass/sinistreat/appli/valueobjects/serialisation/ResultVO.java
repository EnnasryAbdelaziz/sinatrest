package eai.devass.sinistreat.appli.valueobjects.serialisation;
 
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;

/* @author kchakib : 1 nov. 10 */

@SuppressWarnings("unchecked")
public class ResultVO implements IValueObject {
	
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	private Map result = new HashMap();

	public void addElement(String key, Object value) {
		result.put(key, value);
	}
	
	public Object getElement(String key) {
		if(result == null) {
			return null;
		} else {
			return result.get(key);
		}
	}
	
	public Collection getElemnts() {
		if(result == null) {
			return null;
		} else {
			return result.values();
		}
	}

	public Map getResult() {
		return result;
	}

	public void setResult(Map result) {
		this.result = result;
	}
	
	
	

}


