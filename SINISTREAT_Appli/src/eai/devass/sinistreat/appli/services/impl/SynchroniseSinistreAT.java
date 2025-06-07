package eai.devass.sinistreat.appli.services.impl;

import java.util.HashMap;

import ma.co.omnidata.framework.services.businessInterface.IResult;
import ma.co.omnidata.framework.services.businessInterface.OMNIFacade;
import ma.co.omnidata.framework.services.securite.impl.OMNIAction;
import eai.devass.sinistreat.appli.services.pub.ISynchroniseSinistreAT;
import eai.devass.sinistreat.appli.synchronisationSinistre.valueObjects.PstSynchroChargesVO;

@SuppressWarnings("all")
public class SynchroniseSinistreAT implements ISynchroniseSinistreAT {

	private final static String synchroniseSinistreUC = "1031";
	
	public IResult synchroniseSinistre() throws Exception {
		
		OMNIAction action = new OMNIAction();
		action.setActionId(synchroniseSinistreUC);		
		HashMap params = new HashMap();
		
		// INvok UC
		OMNIFacade facade = new OMNIFacade();
		return facade.invokeService(null, action, new PstSynchroChargesVO(), params);
		
	}
	
	
	

}
