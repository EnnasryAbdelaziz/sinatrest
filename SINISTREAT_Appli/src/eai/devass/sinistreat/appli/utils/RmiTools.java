package eai.devass.sinistreat.appli.utils;

import org.springframework.remoting.rmi.RmiProxyFactoryBean;


public class RmiTools {
	
	
	private static RmiTools instance;
	
	public static RmiTools getInstance(){
		if (instance == null) {
			instance = new RmiTools();
		}
		
		return instance; 
	}
	
	
	public Object callService(Class clazz, String nameService, String host,String port) throws Exception {
		//172.21.100.208
		//10.100.121.115
	 	String serviceURL = "//" + host + ":" + port + "/" + nameService;
        RmiProxyFactoryBean factory = new RmiProxyFactoryBean();
        Object objectProxy = null;
        factory.setServiceInterface(clazz);
        factory.setServiceUrl(serviceURL);
        factory.setRefreshStubOnConnectFailure(true);
        factory.afterPropertiesSet();
        objectProxy = factory.getObject();
        
        return objectProxy;
	
	}
	public Object callService(Class clazz, String nameService, String host) throws Exception {
		//172.21.100.208
		//10.100.121.115
//		host="126.0.100.44:1113";
	 	String serviceURL = "//" + host + "/" + nameService;
        RmiProxyFactoryBean factory = new RmiProxyFactoryBean();
        Object objectProxy = null;
        factory.setServiceInterface(clazz);
        factory.setServiceUrl(serviceURL);
        factory.setRefreshStubOnConnectFailure(true);
        factory.afterPropertiesSet();
        objectProxy = factory.getObject();
        
        return objectProxy;
	
	}
	
	public Object callServiceLocal(Class clazz, String nameService) throws Exception {
		//172.21.100.208
		//10.100.121.115
	 	String serviceURL = "//localhost:1109/" + nameService;
        RmiProxyFactoryBean factory = new RmiProxyFactoryBean();
        Object objectProxy = null;
        factory.setServiceInterface(clazz);
        factory.setServiceUrl(serviceURL);
        factory.setRefreshStubOnConnectFailure(true);
        factory.afterPropertiesSet();
        objectProxy = factory.getObject();
        
        return objectProxy;
	
	}
	
	
}
