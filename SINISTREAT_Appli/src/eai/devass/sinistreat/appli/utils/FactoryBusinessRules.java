package eai.devass.sinistreat.appli.utils;

import ma.co.omnidata.framework.services.entites.IEntite;
import eai.devass.sinistreat.appli.businessrule.BusinessRuleBase;

/*	
 @date   : 2 oct. 10
 @author : kchakib
 */

public class FactoryBusinessRules {
	
	private static FactoryBusinessRules instance;
	public synchronized static FactoryBusinessRules getInstance(){
		if (instance == null) {
			instance = new FactoryBusinessRules();
		}
		
		return instance; 
	}
	
	public BusinessRuleBase instanceOfBusinessRule(IEntite entiteBO) throws Exception {
		
		if(entiteBO == null) {
			return null;
		}
		
		String packageName = entiteBO.getClass().getPackage().getName().replaceAll("modele", "businessrule");
		String objectName = entiteBO.getClass().getSimpleName();
		try {
			return (BusinessRuleBase) (Class.forName(packageName + "."
					+ objectName + "BR").newInstance());
			
		} catch(Exception e) {
			return null;
		}
	
	}

	
	

}


