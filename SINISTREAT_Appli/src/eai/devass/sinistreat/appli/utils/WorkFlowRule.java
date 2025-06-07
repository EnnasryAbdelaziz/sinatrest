package eai.devass.sinistreat.appli.utils;

import eai.devass.sinistreat.appli.utils.exception.IMessageException;

public class WorkFlowRule implements IMessageException {

	private static WorkFlowRule instance;
	
	public static WorkFlowRule getInstance() {
		if(instance == null) {
			return new WorkFlowRule();
		} else {			
			return instance;
		}
	}
}
