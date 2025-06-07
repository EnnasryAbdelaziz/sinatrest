package eai.devass.gsr.appli.valueobjects.customerconverter;

import eai.devass.commun.appli.converter.ICustomerConverter;

public class ConvertTypeFileMouvement implements ICustomerConverter {

	public Object getValuePropertyDest(Object entiteOrig) throws Exception {
		
		
		
		
		return null;
	}

	public Object getValuePropertyOrig(Object entiteDest) throws Exception {
		
		if(entiteDest == null) {
			return null;
			
		} else {
			return entiteDest.getClass().getSimpleName();
		}
		
		
	}

}
