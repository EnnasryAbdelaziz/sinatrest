package eai.devass.sinistreat.appli.utils;

import java.text.MessageFormat;
import java.util.ResourceBundle;


public class BundleTools {
	
	private static BundleTools instance;
	
	private final static String PATH_CONFIG_APPLI = "config";
	private String pathProperties;
	private ResourceBundle resourceBundle;
	
	private BundleTools(){
		
	}
	
	public synchronized static BundleTools getInstance(){
		if (instance == null) {
			instance = new BundleTools();
		}
		
		return instance; 
	}

	public String getDefaultMessage(String key, Object ...args) {
		
		resourceBundle = ResourceBundle.getBundle(PATH_CONFIG_APPLI);
		if (args.length == 0) {
			return resourceBundle.getString(key);
		}
		
		return MessageFormat.format (resourceBundle.getString(key), args);
	}
	
	public String getMessage(String key, Object ... args) {
		
		if(pathProperties == null) {
			return null;
		}
		
		resourceBundle = ResourceBundle.getBundle(pathProperties);
		if (args==null) {
			return resourceBundle.getString(key);
		}
		
		return MessageFormat.format(resourceBundle.getString(key), args);
		
	}
	
	public void setPathProperties(String pathProperties) {
		this.pathProperties = pathProperties;
	}
	

}
